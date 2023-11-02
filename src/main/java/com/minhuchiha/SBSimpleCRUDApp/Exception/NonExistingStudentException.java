package com.minhuchiha.SBSimpleCRUDApp.Exception;

public class NonExistingStudentException extends Exception {
    public NonExistingStudentException(String errorMessage) {
        super("No student with id: " + errorMessage);
    }
}
