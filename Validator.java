import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validator {
    
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("\\d{10,15}");
    }
    
    public static boolean isValidDOB(String dob) {
        if (dob == null || dob.trim().isEmpty()) {
            return false;
        }
        
        try {
            // Check format YYYY-MM-DD
            if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return false;
            }
            
            LocalDate date = LocalDate.parse(dob);
            LocalDate today = LocalDate.now();
            
            // Check if date is in the past and reasonable (after 1900)
            return date.isBefore(today) && date.getYear() > 1900;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean isPositiveInteger(int value) {
        return value > 0;
    }
    
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z\\s]+");
    }
    
    public static boolean isValidStaffId(String id) {
        return id != null && !id.trim().isEmpty() && id.matches("[A-Za-z0-9]+");
    }
}