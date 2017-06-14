package Model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import com.sangupta.murmur.Murmur1;
import com.sangupta.murmur.Murmur2;
import com.sangupta.murmur.Murmur3;

/**
 * Created by lucas.crandle on 5/22/2017.
 */
public class Filter {
    ArrayList<Integer> actualList;
    boolean[] bloomFilter;
    int filterSize, randomSize,uniqueInserts;
    boolean useMurmur1;
    boolean useMurmur2;
    boolean useMurmur3;
    long seed;
    int HashCount;

    //Allow seed to be set for testing
    public Filter(int filterSize, int randomSize, boolean useMurmur1, boolean useMurmur2, boolean useMurmur3, long seed, int uniqueInserts){
        actualList = new ArrayList<Integer>();
        bloomFilter = new boolean[filterSize];

        this.randomSize = randomSize;

        this.filterSize = filterSize;

        this.useMurmur1 = useMurmur1;
        this.useMurmur2 = useMurmur2;
        this.useMurmur3 = useMurmur3;

        HashCount=0;
        if(this.useMurmur1){
            HashCount ++;
        }
        if(this.useMurmur2){
            HashCount ++;
        }
        if(this.useMurmur3){
            HashCount ++;
        }

        this.seed = seed;

        this.uniqueInserts = uniqueInserts;

        System.out.println("Created filter with Size: " + filterSize);
    }
    public Filter(int filterSize, int randomSize, boolean useMurmur1, boolean useMurmur2, boolean useMurmur3, int uniqueInserts){
        this(filterSize, randomSize, useMurmur1, useMurmur2,useMurmur3,(new Random()).nextLong(), uniqueInserts);
    }

    public double getFalsePositiveCount(){
        return Math.pow(1 - Math.pow(Math.E , ((this.HashCount * this.actualList.size())/this.bloomFilter.length)), this.HashCount);
    }

    private void add(int value){
//        System.out.println("Inserting Int: " + value);
        //leverage bloom filter to skip checking for multiple inserts.
        if(!this.testFilter(value)){
            if(!this.contains(value)){
                this.actualList.add(value);
            }
        }else{
            this.actualList.add(value);
        }
    }

    public String checkInput(String input){
        try{
            if(this.testFilter(Integer.parseInt(input))){
                return "Possibly Contains Word";
            }else{
                return "Does Not Contain";
            }
        }catch(Exception e){
            return "Invalid";
        }
    }

    public void runSimulation(){
        int x = 0;
        Random r = new Random(System.currentTimeMillis());
        while(x < this.uniqueInserts){
            this.insert(r.nextInt(randomSize));
            x++;
        }
    }

    public int[] runCheckSimulation(int tryCount){
        Random r = new Random(System.currentTimeMillis());
        int countInSet = 0;
        int falsePositive = 0;
        for(int x = 0; x<tryCount;x++){
            int nextIntTest = r.nextInt(randomSize);
//            System.out.println("Trying Int: " + nextIntTest);
            if(this.testFilter(nextIntTest)){
                countInSet++;
                if(!this.contains(nextIntTest)){
                    falsePositive++;
//                    System.out.println("contains");
                }
            }
        }
        return (new int[]{countInSet, falsePositive, (falsePositive/tryCount)});
    }

    public void insert(int value){
        add(value);
//        System.out.println("Size: " + this.actualList.size());
        byte[] bytes;

        bytes = BigInteger.valueOf(value).toByteArray();
        if(useMurmur1)
            setFilterFlag((int)(Murmur1.hash(bytes, bytes.length, seed) % this.filterSize));
        if(useMurmur2)
            setFilterFlag((int)(Murmur2.hash(bytes, bytes.length, seed) % this.filterSize));
        if(useMurmur3)
            setFilterFlag((int)(Murmur3.hash_x86_32(bytes, bytes.length, seed) % this.filterSize));

    }

    private void setFilterFlag(int location){
        bloomFilter[location] = true;
    }

    public boolean contains(int value){
        return actualList.contains(value);
    }

    public boolean testFilter(int value){
        byte[] bytes = BigInteger.valueOf(value).toByteArray();
//        System.out.println("Actual Contains: " + this.actualList.contains(value));
        boolean murmur1Result = false;
        boolean murmur2Result = false;
        boolean murmur3Result = false;

        if(useMurmur1)
            murmur1Result = this.bloomFilter[(int)(Murmur1.hash(bytes, bytes.length, seed) % this.filterSize)];
        if(useMurmur2)
            murmur2Result = this.bloomFilter[(int)(Murmur2.hash(bytes, bytes.length, seed) % this.filterSize)];
        if(useMurmur3)
            murmur3Result = this.bloomFilter[(int)(Murmur3.hash_x86_32(bytes, bytes.length, seed) % this.filterSize)];

        return
                ((useMurmur1 && murmur1Result)|| !useMurmur1 ) &&
                ((useMurmur2 && murmur2Result)|| !useMurmur2) &&
                ((useMurmur3 && murmur3Result) || !useMurmur3);
    }

    public boolean[] getBloomFilter(){
        return bloomFilter;
    }

    public String filterToString(){
        return bloomFilter.toString();
    }
}
