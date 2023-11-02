package com.minhuchiha.SBSimpleCRUDApp.Enum;

public enum CourseDifficulty {

    EASY ("E"), MEDIUM ("M"), HARD ("H");

    private String code;

    private CourseDifficulty(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
