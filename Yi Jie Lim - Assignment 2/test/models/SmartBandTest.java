import models.Manufacturer;
import models.SmartBand;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SmartBandTest {
    private SmartBand validDevices1;
    private SmartBand invalidDevices1;
    @After
    public void tearDown() {
        validDevices1=invalidDevices1=null;
    }

    @Before
    public void setUp() {
        Manufacturer manufacturer = new Manufacturer("Samsung", 333);
        Manufacturer invalidManufacturer = new Manufacturer("ABCDEFGHIJKLMNOPQRSTU", 0);
        validDevices1 = new SmartBand("Galaxy Watch 3", 799.99, manufacturer,"123456", "Silicon", "120", true);
        invalidDevices1 = new SmartBand("Galaxy Tab S7 version 1 c.09462b", 19, invalidManufacturer, "12345678910", "Snapdragon 8655678920", "1234567898", false);
    }

    @Test
    public void testValidHeartRateMonitor() {
        assertEquals(true, validDevices1.isHeartRateMonitor());
    }

    @Test
    public void testInvalidHeartRateMonitor() {
        assertEquals(false, invalidDevices1.isHeartRateMonitor());
    }

    @Test
    public void testSetValidHeartRateMonitor() {
        assertEquals(true, validDevices1.isHeartRateMonitor());
        validDevices1.setHeartRateMonitor(false);
        assertEquals(false, validDevices1.isHeartRateMonitor());
    }

    @Test
    public void testToString() {
        String expected = "Heart Rate Monitor: true, Insurance Premium: €55.999300000000005, ConnectToInternet: Connects to the internet via Companion App";
        System.out.println(validDevices1.toString());
        Assert.assertTrue( validDevices1.toString().contains(expected));
        expected = "Heart Rate Monitor: false, Insurance Premium: €0.6, ConnectToInternet: Connects to the internet via Companion App";
        Assert.assertFalse( invalidDevices1.toString().contains(expected));

    }
}
