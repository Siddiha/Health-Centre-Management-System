public class DuplicateIdException extends CustomException {
    public DuplicateIdException(String id) {
        super("Staff member with ID '" + id + "' already exists!");
    }
}