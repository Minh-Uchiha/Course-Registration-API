package com.minhuchiha.SBSimpleCRUDApp.Exception;

public class NonExistingCourseException extends Exception {
    public NonExistingCourseException(String errorMessage) {
        super("No course with id: " + errorMessage);
    }
}
