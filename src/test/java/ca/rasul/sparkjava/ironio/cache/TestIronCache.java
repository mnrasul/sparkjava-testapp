package ca.rasul.sparkjava.ironio.cache;

import com.github.mrcritical.ironcache.CacheItem;
import com.github.mrcritical.ironcache.DefaultIronCache;
import com.github.mrcritical.ironcache.HTTPException;
import com.github.mrcritical.ironcache.IronCache;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by nrasul on 9/28/14.
 */
public class TestIronCache{

    @Test
    public void testPutInCache() throws IOException {
        IronCache cache = new DefaultIronCache("RrywQQNWksgkNpTrHElHxAYWMhU", "542870ea8374440005000057", "loggedinusers");
        String key = "hello";
        String value = "sesioi";
        cache.put(key, value);
        Assert.assertTrue(value.equals(cache.get(key).getValue()));

        cache.delete(key);

    }

    @Test (expected = HTTPException.class)
    public void testEmptyGet() throws IOException {
        IronCache cache = new DefaultIronCache("RrywQQNWksgkNpTrHElHxAYWMhU", "542870ea8374440005000057", "loggedinusers");
        String key = "hello";
        String value = "sesioi";
        CacheItem cacheItem = cache.get(key);
    }
}
