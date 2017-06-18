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
    int filterSize, randomSize,uniqueInserts,HashCount;
    boolean useMurmur1;
    boolean useMurmur2;
    boolean useMurmur3;
    long seed;

    //Allow seed to be set for testing
    public Filter(int filterSize, int randomSize, boolean useMurmur1, boolean useMurmur2, boolean useMurmur3, long seed, int uniqueInserts) throws Exception {
        if(uniqueInserts > randomSize){
            this.moreUniqueInsertsThanAllowed();
        }
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
        if(HashCount == 0){
            this.throwNoMurmorSelected();
        }
        actualList = new ArrayList<Integer>();
        bloomFilter = new boolean[filterSize];

        this.randomSize = randomSize;

        this.filterSize = filterSize;
        this.seed = seed;

        this.uniqueInserts = uniqueInserts;

        System.out.println("Created filter with Size: " + filterSize);
    }
    public Filter(int filterSize, int randomSize, boolean useMurmur1, boolean useMurmur2, boolean useMurmur3, int uniqueInserts) throws Exception {
        this(filterSize, randomSize, useMurmur1, useMurmur2,useMurmur3,(new Random()).nextLong(), uniqueInserts);
    }

    /**
     * Throw exception when the user hasn't selected any hashes.
     * @throws Exception
     */
    private void throwNoMurmorSelected() throws Exception {
        throw new Exception("Need to Select at least one murmor function.");
    }

    /**
     * Throws exception for when the amount of unique inserts is greater than random size.
     * @throws Exception
     */
    private void moreUniqueInsertsThanAllowed() throws Exception {
        throw new Exception("Make Unique Inserts =< Random Size");
    }

    /**
     * Gets statistical chance that a random item will be a hit for the filter
     * @return int based on a percentage.
     */
    public double getHitPercentage(){
        double k = this.HashCount;
        double upExp = -1 * this.HashCount * this.uniqueInserts;
        double Eexp = upExp/this.filterSize;
        double base = 1 - Math.pow(Math.E, Eexp);
        double result = Math.pow(base, this.HashCount);
        return result;
    }
    
    public double getFalsePositivePercentage(){
        double ranSize = this.randomSize;
        double uInserts = this.uniqueInserts;
        double chanceOfNotInSet = (ranSize - uInserts)/ranSize;
        double result = chanceOfNotInSet * this.getHitPercentage();
        return result;
    }

    /**
     * Helper method to add value to actual list
     * @param value to add
     * @return true if it was added, false if it was already in it.s
     */
    private boolean addValueToActualList(int value){
//        System.out.println("Inserting Int: " + value);
        //leverage bloom filter to skip checking for multiple inserts.
        if(this.testFilter(value)){
            if(!this.contains(value)){
//                System.out.println("Adding Value");
                this.actualList.add(value);
                return true;
            }
        }else{
//            System.out.println("Not in filter so add");
            this.actualList.add(value);
            return true;
        }
//        System.out.println("Returning False");
        return false;
    }

    /**
     * Handler for input checking that the user can use.
     *
     * @param input value to check
     * @return String representation of if the value was in the filter or not.
     */
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

    /**
     * Inserts randomized ints into the filter based on what was setup.
     */
    public void runSimulation(){
        int x = 0;
        Random r = new Random(System.currentTimeMillis());
        int newRandom;
        while(x < this.uniqueInserts){
            newRandom = r.nextInt(randomSize);
//            System.out.println("Trying Int: " + newRandom);
            if(this.insert(newRandom)){
//                System.out.println("Incrementing");
                x++;
                if(x % 100000 == 0){
                    System.out.println("Inserted: " + x);
                }
            }

        }
    }

    /**
     * Checks filter for an amount of items based on a random set.
     * @param tryCount Amount of random numbers to try and see if they are in the set.
     * @return int array containing [Amount of numbers that tested positive for the filter, the amount that were false positives, percentage that were false positives]
     */
    public statisticValues runCheckSimulation(int tryCount){
        Random r = new Random(System.currentTimeMillis());
        int countInSet = 0;
        int falsePositive = 0;
        for(int x = 0; x<tryCount;x++){
            int nextIntTest = r.nextInt(randomSize);
//            System.out.println("Trying Int: " + nextIntTest);
            if(this.testFilter(nextIntTest)){
                countInSet++;
                /** Print Progress */
                if(!this.contains(nextIntTest)){
                    falsePositive++;
//                    System.out.println("contains");
                }
            }
        }
        return (new statisticValues(countInSet, falsePositive, (tryCount - countInSet)));
    }

    /**
     * Insert value into the filter as well as the actual list.
     * @param value Value to insert
     * @return false when the value has already been inserted.
     */
    public boolean insert(int value){
        /** If value has already been inserted than don't bother.*/
        if(addValueToActualList(value)){
//                    System.out.println("Size: " + this.actualList.size());
            byte[] bytes;

            bytes = BigInteger.valueOf(value).toByteArray();
            if(useMurmur1)
                setFilterFlag((int)(Murmur1.hash(bytes, bytes.length, seed) % this.filterSize));
            if(useMurmur2)
                setFilterFlag((int)(Murmur2.hash(bytes, bytes.length, seed) % this.filterSize));
            if(useMurmur3)
                setFilterFlag((int)(Murmur3.hash_x86_32(bytes, bytes.length, seed) % this.filterSize));
            return true;
        }
        return false;


    }

    /**
     * Sets the location of the bloom filter to 1
     * @param location location to set the filter.
     */
    private void setFilterFlag(int location){
        bloomFilter[location] = true;
    }

    /**
     * Returns if the item is actually in the list or not.
     * @param value value to check
     * @return true if the value is in the list, false if not.
     */
    public boolean contains(int value){
        return actualList.contains(value);
    }

    /**
     * Checks if the value is in the filter or not.
     * @param value Value to check
     * @return true if bloom filter tested positive, false if not.
     */
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
                (murmur1Result || !useMurmur1) &&
                (murmur2Result || !useMurmur2) &&
                (murmur3Result || !useMurmur3);
    }

    /**
     * Generic getter
     * @return filter.
     */
    public boolean[] getBloomFilter(){
        return bloomFilter;
    }

    /**
     * For debugging.
     * @return
     */
    public String filterToString(){
        return bloomFilter.toString();
    }
}
