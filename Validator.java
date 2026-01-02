import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Validator {
    
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) return false;
        return phone.matches("\\d{10,15}");
    }
    
    public static boolean isValidDOB(String dob) {
        if (dob == null || dob.trim().isEmpty()) {
            return false;
        }
        
        try {
            if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return false;
            }
            
            LocalDate date = LocalDate.parse(dob);
            LocalDate today = LocalDate.now();
            
            // Check if date is in the past and reasonable (after 1900)
            // Also ensure age is reasonable (between 18 and 100)
            if (date.isAfter(today)) {
                return false;
            }
            
            int age = Period.between(date, today).getYears();
            return age >= 18 && age <= 100 && date.getYear() > 1900;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public static boolean isPositiveInteger(int value) {
        return value > 0;
    }
    
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z\\s-']+");
    }
    
    public static boolean isValidStaffId(String id) {
        return id != null && !id.trim().isEmpty() && id.matches("[A-Za-z0-9]{3,20}");
    }
    
    public static boolean isValidLicenceNumber(String licence) {
        return licence != null && !licence.trim().isEmpty() && licence.matches("[A-Za-z0-9]{5,20}");
    }
    
    public static boolean isValidSpecialization(String specialization) {
        return specialization != null && !specialization.trim().isEmpty() && 
               specialization.matches("[a-zA-Z\\s]+");
    }
    
    public static boolean isValidDeskNumber(int deskNumber) {
        return deskNumber > 0 && deskNumber <= 999;
    }
    
    public static boolean isValidHoursPerWeek(int hours) {
        return hours > 0 && hours <= 80;
    }
    
    public static boolean isValidConsultations(int consultations) {
        return consultations >= 0 && consultations <= 100;
    }
}