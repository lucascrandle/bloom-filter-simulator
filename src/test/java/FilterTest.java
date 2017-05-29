import Model.Filter;
import com.sangupta.murmur.Murmur1;
import com.sangupta.murmur.Murmur2;
import com.sangupta.murmur.Murmur3;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by lucas.crandle on 5/22/2017.
 */
public class FilterTest {
    Filter f;
    long randomSeed;

    @Before
    public void createSeed(){
        Random r = new Random();
        this.randomSeed = r.nextLong();
    }
    @Test
    public void AddItemToFilter() {
        this.f = new Filter(1000, true, true, true,100, 100);
        this.f.insert(127127127);
        assertTrue(this.f.testFilter(127127127));
        assertFalse(this.f.contains(111111111));
    }

    @Test
    public void checkMurmur1hash(){
        int testVal = 111111111;
        int filterSize = 10;

        byte[] bytes = BigInteger.valueOf(testVal).toByteArray();
        f = new Filter(filterSize, true, false, false,100,100);
        f.insert(testVal);
        assertTrue(f.getBloomFilter()[(int)(Murmur1.hash(bytes, bytes.length, randomSeed) % filterSize)]);

    }

    @Test
    public void checkMurmur2hash(){
        int testVal = 111111111;
        int filterSize = 10;
        byte[] bytes = BigInteger.valueOf(testVal).toByteArray();
        f = new Filter(filterSize, false, true, false,100,100);
        f.insert(testVal);
        assertTrue(f.getBloomFilter()[(int)(Murmur2.hash(bytes, bytes.length, randomSeed) % filterSize)]);
    }

    @Test
    public void checkMurmur3hash(){
        int testVal = 111111111;
        int filterSize = 10;
        byte[] bytes = BigInteger.valueOf(testVal).toByteArray();
        f = new Filter(filterSize, false, false, true,100,100);
        f.insert(testVal);
        assertTrue(f.getBloomFilter()[(int)(Murmur3.hash_x86_32(bytes, bytes.length, randomSeed) % filterSize)]);

    }
}