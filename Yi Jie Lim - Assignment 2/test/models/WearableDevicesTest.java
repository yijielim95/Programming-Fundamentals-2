import models.Manufacturer;
import models.SmartWatch;
import models.SmartBand;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WearableDevicesTest {
    private SmartWatch validDevices, invalidDevices;
    private SmartBand validDevices1, invalidDevices1;
    @After
    public void tearDown() {
        validDevices=invalidDevices=null;
    }

    @Before
    public void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validDevices = new SmartWatch("Galaxy Watch 3", 799.99, manufacturer, "123456", "Silicon", "120", "LCD");
        invalidDevices = new SmartWatch("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 8655678920", "1234567898", "SDN");
        validDevices1 = new SmartBand("Galaxy Watch 3", 799.99, manufacturer,"123456", "Silicon", "120", true);
        invalidDevices1 = new SmartBand("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 8655678920", "1234567898", false);
    }

    @Test
    public void testGetMaterial(){
        assertEquals("Silicon", validDevices.getMaterial());
        assertEquals("Snapdragon 865567892", invalidDevices.getMaterial());
        assertEquals("Silicon", validDevices1.getMaterial());
        assertEquals("Snapdragon 865567892", invalidDevices1.getMaterial());
    }

    @Test
    public void testSetMaterial(){
        validDevices.setMaterial("Silicon");
        assertEquals("Silicon", validDevices.getMaterial());
        validDevices.setMaterial("Snapdragon 8655678920");
        assertEquals("Silicon", validDevices.getMaterial());
        validDevices1.setMaterial("Silicon");
        assertEquals("Silicon", validDevices1.getMaterial());
        validDevices1.setMaterial("Snapdragon 8655678920");
        assertEquals("Silicon", validDevices1.getMaterial());
    }

    @Test
    public void testGetSize(){
        assertEquals("120", validDevices.getSize());
        assertEquals("1234567898", invalidDevices.getSize());
        assertEquals("120", validDevices1.getSize());
        assertEquals("1234567898", invalidDevices1.getSize());
    }

    @Test
    public void testSetSize(){
        validDevices.setSize("120");
        assertEquals("120", validDevices.getSize());
        validDevices.setSize("12345678989");
        assertEquals("120", validDevices.getSize());
        validDevices1.setSize("120");
        assertEquals("120", validDevices1.getSize());
        validDevices1.setSize("12345678989");
        assertEquals("120", validDevices1.getSize());
    }

    @Test
    public void testToString() {
        String expected = "Material: Silicon, Size: 120";
        Assert.assertTrue( validDevices.toString().contains(expected));
        expected = "Material: Snapdragon 8655678920, Size: 12345678989";
        Assert.assertFalse( invalidDevices.toString().contains(expected));
        expected = "Material: Silicon, Size: 120";
        Assert.assertTrue( validDevices1.toString().contains(expected));
        expected = "Material: Snapdragon 8655678920, Size: 12345678989";
        Assert.assertFalse( invalidDevices1.toString().contains(expected));
    }

}
