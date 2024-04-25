import models.Manufacturer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerTest {
    Manufacturer manValid, manInvalid, manBorder, manBelowBorder, manEmpty;

    @BeforeEach
    public void setup(){
        manValid = new Manufacturer("Samsung", 1000);
        manInvalid = new Manufacturer("Samsungs901234567890X", -1);
        manBorder = new Manufacturer("Samsungs901234567890",  1);
        manBelowBorder = new Manufacturer("Samsungs90123456789" , 0);
        manEmpty = new Manufacturer("", -10);
    }

    @Test
    void constructorTests() {
        //testing manufacturerName - at <20, 20, 21 chars
        Assertions.assertEquals("Samsung", manValid.getManufacturerName());  //value accepted - under 20 length limit
        Assertions.assertEquals("Samsungs901234567890", manInvalid.getManufacturerName());  //value truncated to  20 length limit
        Assertions.assertEquals("Samsungs901234567890", manBorder.getManufacturerName());  //value accepted - at 20 length limit
        Assertions.assertEquals("Samsungs90123456789", manBelowBorder.getManufacturerName());//value accepted - at 10 length limit
        Assertions.assertEquals("", manEmpty.getManufacturerName()); // value accepted - empty string
        //testing numEmployees (>=1)  - at valid and invalid,
        Assertions.assertEquals(1000, manValid.getNumEmployees());  //valid value accepted correctly
        Assertions.assertEquals(1, manInvalid.getNumEmployees());   // check that default is set when invalid input given
        Assertions.assertEquals(1, manBorder.getNumEmployees());    // check that 1 is accepted as valid input
        Assertions.assertEquals(1, manBelowBorder.getNumEmployees());   // check that default set when 0 is input (invalid)
        Assertions.assertEquals(1, manEmpty.getNumEmployees());      // check that default is set when negative value is input.

    }

    @Test
    void manufacturerNameGetAndSetWorkingCorrectly() {
        Assertions.assertEquals("Samsung", manValid.getManufacturerName());
        manValid.setManufacturerName("Apple");  //valid change
        Assertions.assertEquals("Apple", manValid.getManufacturerName());
        manValid.setManufacturerName("Samsungs901234567890");  //valid change
        Assertions.assertEquals("Samsungs901234567890", manValid.getManufacturerName());
        manValid.setManufacturerName("Samsungs90123456789");  //valid change
        Assertions.assertEquals("Samsungs90123456789", manValid.getManufacturerName());
        manValid.setManufacturerName("XXXXXXXX901234567890XXX");  //invalid - no change
        Assertions.assertEquals("Samsungs90123456789", manValid.getManufacturerName());
        manValid.setManufacturerName("");  //valid - no change
        Assertions.assertEquals("", manValid.getManufacturerName());
    }




    @Test
    void numEmployeesGetAndSetWorkingCorrectly() {
        Assertions.assertEquals(1000, manValid.getNumEmployees());
        manValid.setNumEmployees(999); //valid change
        Assertions.assertEquals(999, manValid.getNumEmployees());
        manValid.setNumEmployees(1); //valid change
        Assertions.assertEquals(1, manValid.getNumEmployees());
        manValid.setNumEmployees(10);
        Assertions.assertEquals(10, manValid.getNumEmployees());
        manValid.setNumEmployees(0); //invalid change
        Assertions.assertEquals(10, manValid.getNumEmployees());
        manValid.setNumEmployees(-1); //invalid change
        Assertions.assertEquals(10, manValid.getNumEmployees());

    }

    @Test
    void validatingTheEqualsMethod() {
        //checking that equals works when the objects are at the same location
        Manufacturer copyManInvalid = manValid;
        Assertions.assertEquals(manValid, manValid);
        //now checking that true is returned when the values in separate objects are the same
        Assertions.assertEquals(manValid, new Manufacturer("Samsung", 1000));
        //checking that false is returned  when one or both fields are different
        Assertions.assertNotEquals(manValid, new Manufacturer("Tesla", 1000));
        Assertions.assertNotEquals(manValid, new Manufacturer("Samsung", 1999));
        Assertions.assertNotEquals(manValid, new Manufacturer("Tesla", 1999));
    }
    @Nested
    class ToString {
        @Test
        void toStringContainsAllFieldsInObject() {
            //checking a Manufacturer contains manufacturer name and number of employees
            String manuStringpluralEmployees = manValid.toString();
            Assertions.assertTrue(manuStringpluralEmployees.contains("Samsung"));
            Assertions.assertTrue(manuStringpluralEmployees.contains("1000"));

            //checking a Manufacturer contains manufacturer name and number of employees
            String manuStringSingleEmployee = manBorder.toString();
            Assertions.assertTrue(manuStringSingleEmployee.contains("Samsungs901234567890"));
            Assertions.assertTrue(manuStringSingleEmployee.contains("1"));
        }
        @Test
        void toStringAddsEmployeesToTheString() {
            //checking a Manufacturer contains "employees" when number of employees is plural, 1 otherwise.
            //checking for plural
            String manuStringpluralEmployees = manValid.toString();
            Assertions.assertTrue(manuStringpluralEmployees.contains("1000 employees"));
            //checking for singular
            String manuStringSingleEmployee = manBorder.toString();
            Assertions.assertTrue(manuStringSingleEmployee.contains("1 employee"));

        }
    }
}