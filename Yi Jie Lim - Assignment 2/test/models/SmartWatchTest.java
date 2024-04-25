import models.Manufacturer;
import models.SmartWatch;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SmartWatchTest {
    private SmartWatch validDevices;
    private SmartWatch invalidDevices;
    @After
    public void tearDown() {
        validDevices=invalidDevices=null;
    }

    @Before
    public void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validDevices = new SmartWatch("Galaxy Watch 3", 799.99, manufacturer, "123456", "Silicon", "120", "LCD");
        invalidDevices = new SmartWatch("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 8655678920", "1234567898", "TNT");
    }

    @Test
    public void testValidDisplayType() {
        assertEquals("LCD", validDevices.getDisplayType());
    }

    @Test
    public void testInvalidDisplayType() {
        assertEquals("LCD", invalidDevices.getDisplayType());
    }
    @Test
    public void testSetValidDisplayType() {
        assertEquals("LCD", validDevices.getDisplayType());
        validDevices.setDisplayType("AMOLED");
        assertEquals("AMOLED", validDevices.getDisplayType());
        validDevices.setDisplayType("LED");
        assertEquals("LED", validDevices.getDisplayType());
        validDevices.setDisplayType("TFT");
        assertEquals("TFT", validDevices.getDisplayType());


    }

    @Test
    public void testSetInvalidDisplayType() {
        assertEquals("LCD", validDevices.getDisplayType());
        validDevices.setDisplayType("AMOLCD");
        assertEquals("LCD", validDevices.getDisplayType());
        validDevices.setDisplayType("LBD");
        assertEquals("LCD", validDevices.getDisplayType());
        validDevices.setDisplayType("LDD");
        assertEquals("LCD", validDevices.getDisplayType());
        validDevices.setDisplayType("TNT");
        assertEquals("LCD", validDevices.getDisplayType());
    }

    @Test
    public void testToString() {
        String expected = "Display Type: LCD, Insurance Premium: €47.9994, ConnectToInternet: Connects to the internet via bluetooth";
        Assert.assertTrue( validDevices.toString().contains(expected));
        expected = "Display Type: TNT, Insurance Premium: €0.6, ConnectToInternet: Connects to the internet via bluetooth";
        Assert.assertFalse( invalidDevices.toString().contains(expected));

    }
}
