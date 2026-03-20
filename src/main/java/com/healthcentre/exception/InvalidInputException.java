package com.healthcentre.exception;

public class InvalidInputException extends CustomException {
    public InvalidInputException(String field, String reason) {
        super("Invalid " + field + ": " + reason);
    }
}
