package com.healthcentre.exception;

public class StaffNotFoundException extends CustomException {
    public StaffNotFoundException(String staffId) {
        super("Staff member with ID '" + staffId + "' not found.");
    }
}
