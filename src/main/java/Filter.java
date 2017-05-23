import java.math.BigInteger;
import java.util.ArrayList;
import com.sangupta.*;
import com.sangupta.murmur.Murmur1;

/**
 * Created by lucas.crandle on 5/22/2017.
 */
public class Filter {
    ArrayList<Integer> actualList;
    boolean[] bloomFilter;
    int size;
    boolean useMurmur1;
    boolean useMurmur2;
    boolean useMurmur3;

    public Filter(int size, boolean useMurmur1, boolean useMurmur2, boolean useMurmur3){
        actualList = new ArrayList<Integer>();
        bloomFilter = new boolean[size];

        this.size = size;

        this.useMurmur1 = useMurmur1;
        this.useMurmur2 = useMurmur2;
        this.useMurmur3 = useMurmur3;

        System.out.println("Created filter with Size: " + size);
    }

    public void insert(int value){
        ;
        actualList.add(value);

        //TODO implement hash functions
    }

    public boolean contains(int value){
        return actualList.contains(value);
    }

    public boolean testFilter(int value){
        //TODO Implement Hash Functions
        return false;
    }

    public void resetFilter(){
        bloomFilter = new boolean[this.size];
    }

    public boolean[] getBloomFilter(){
        return bloomFilter;
    }
}
