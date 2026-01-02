public class StaffNotFoundException extends CustomException {
    public StaffNotFoundException(String id) {
        super("Staff member with ID '" + id + "' not found.");
    }
}