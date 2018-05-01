package ru.capper2.sale;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.*;

public class TestPointOfSale {

    PointOfSaleImplInMemory pointOfSale;

    @Before
    public void init() throws URISyntaxException, IOException {
        pointOfSale = new PointOfSaleImplInMemory(ClassLoader.getSystemResource("offices.txt").toURI());
    }

    @Test
    public void testReadFile() {
        List<String> all = pointOfSale.getAll();
        assertNotNull(all);
        assertTrue(all.size() > 0);
        assertTrue(all.contains("юю34.53"));
        assertTrue(all.contains(""));
    }


    @Test
    public void testGetRandomLineinFile() {
        String random1 = pointOfSale.getRandomPointOfSale();
        assertNotNull(random1);
        String random2 = pointOfSale.getRandomPointOfSale();
        assertNotNull(random2);
        String random3 = pointOfSale.getRandomPointOfSale();
        assertNotNull(random3);
        String random4 = pointOfSale.getRandomPointOfSale();
        assertNotNull(random4);
        String random5 = pointOfSale.getRandomPointOfSale();
        assertNotNull(random5);

        assertFalse(
                random1.equals(random2) &&
                        random2.equals(random3) &&
                        random3.equals(random4) &&
                        random4.equals(random5) &&
                        random2.equals(random4) &&
                        random2.equals(random5) &&
                        random3.equals(random5)
        );
    }
}
