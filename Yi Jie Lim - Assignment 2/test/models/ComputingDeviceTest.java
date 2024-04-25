import models.Manufacturer;
import models.Tablet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ComputingDeviceTest {
    private Tablet validTablet, invalidTablet;
    @After
    public void tearDown() {
        validTablet=invalidTablet=null;
    }

    @Before
    public void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validTablet = new Tablet("Galaxy Tab S7", 799.99, manufacturer, "123456", "Snapdragon 865", 64, "Android");
        invalidTablet = new Tablet("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 8655678920", 19, "Android");

    }

    @Test
    public void testGetProcessor() {
        assertEquals("Snapdragon 865", validTablet.getProcessor());
        assertEquals("Snapdragon 865567892", invalidTablet.getProcessor());
    }

    @Test
    public void testSetProcessor() {
        validTablet.setProcessor("Snapdragon 888");
        assertEquals("Snapdragon 888", validTablet.getProcessor());
        validTablet.setProcessor("Snapdragon 8655678920");
        assertEquals("Snapdragon 888", validTablet.getProcessor());
    }

    @Test
    public void testGetStorage() {
        assertEquals(64, validTablet.getStorage());
        invalidTablet.setStorage(8);
        assertEquals(8, invalidTablet.getStorage());
    }

    @Test
    public void testSetStorage() {
        validTablet.setStorage(128);
        assertEquals(128, validTablet.getStorage());
        validTablet.setStorage(127);
        assertEquals(128, validTablet.getStorage());
        validTablet.setStorage(256);
        assertEquals(128, validTablet.getStorage());
    }
    @Test
    public void testToString() {
        String expected = "Processor: Snapdragon 865, Storage: 64GB";
        Assert.assertTrue( validTablet.toString().contains(expected));
        invalidTablet.setStorage(8);
        expected = "Processor: Snapdragon 865567892, Storage: 8GB";
        Assert.assertTrue( invalidTablet.toString().contains(expected));
    }

}