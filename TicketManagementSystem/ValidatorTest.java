import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {
    
    @Test
    public void testValidPhoneNumber() {
        assertTrue(Validator.isValidPhone("1234567890"));
        assertTrue(Validator.isValidPhone("123456789012345"));
        assertFalse(Validator.isValidPhone("12345")); // Too short
        assertFalse(Validator.isValidPhone("abcdefghij")); // Not digits
        assertFalse(Validator.isValidPhone(null)); // Null
    }
    
    @Test
    public void testValidDOB() {
        assertTrue(Validator.isValidDOB("1990-05-15"));
        assertFalse(Validator.isValidDOB("2030-12-31")); // Future date
        assertFalse(Validator.isValidDOB("15/05/1990")); // Wrong format
        assertFalse(Validator.isValidDOB("invalid-date")); // Invalid
        assertFalse(Validator.isValidDOB(null)); // Null
    }
    
    @Test
    public void testValidName() {
        assertTrue(Validator.isValidName("John"));
        assertTrue(Validator.isValidName("John Smith"));
        assertFalse(Validator.isValidName(""));
        assertFalse(Validator.isValidName("   "));
        assertFalse(Validator.isValidName("John123"));
        assertFalse(Validator.isValidName(null));
    }
    
    @Test
    public void testValidStaffId() {
        assertTrue(Validator.isValidStaffId("D001"));
        assertTrue(Validator.isValidStaffId("R123"));
        assertTrue(Validator.isValidStaffId("123ABC"));
        assertFalse(Validator.isValidStaffId(""));
        assertFalse(Validator.isValidStaffId("   "));
        assertFalse(Validator.isValidStaffId(null));
    }
    
    @Test
    public void testPositiveInteger() {
        assertTrue(Validator.isPositiveInteger(1));
        assertTrue(Validator.isPositiveInteger(100));
        assertFalse(Validator.isPositiveInteger(0));
        assertFalse(Validator.isPositiveInteger(-1));
    }
}