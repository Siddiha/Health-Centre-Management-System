package com.healthcentre.exception;

public class DuplicateIdException extends CustomException {
    public DuplicateIdException(String staffId) {
        super("Staff member with ID '" + staffId + "' already exists.");
    }
}
