package pl.mysior;


import org.junit.Before;
import org.junit.Test;
import pl.mysior.Logging.KeySearcher;

import static org.junit.Assert.*;

public class KeySearcherTest {
    private KeySearcher keySearcher;
    @Before
    public void before(){
        keySearcher = new KeySearcher();
        Main.synchronizedAuthKeySet.add("test1");
        Main.synchronizedAuthKeySet.add("test2");
    }

    @Test
    public void checkIfReturnTrue(){
        assertTrue(keySearcher.search("test1"));
    }

    @Test
    public void checkIfReturnFalse(){

        assertFalse(keySearcher.search("test3"));
    }
}
