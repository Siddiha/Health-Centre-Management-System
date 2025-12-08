public class InvalidInputException extends CustomException {
    public InvalidInputException(String field, String value) {
        super("Invalid " + field + ": " + value);
    }
}