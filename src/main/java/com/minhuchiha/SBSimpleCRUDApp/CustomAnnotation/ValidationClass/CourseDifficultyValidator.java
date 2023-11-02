package com.minhuchiha.SBSimpleCRUDApp.CustomAnnotation.ValidationClass;

import com.minhuchiha.SBSimpleCRUDApp.CustomAnnotation.Annotation.ValidateCourseDifficulty;
import com.minhuchiha.SBSimpleCRUDApp.Enum.CourseDifficulty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class CourseDifficultyValidator implements ConstraintValidator<ValidateCourseDifficulty, CourseDifficulty> {
    private List<CourseDifficulty> valueList;

    @Override
    public void initialize(ValidateCourseDifficulty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        valueList = new ArrayList<CourseDifficulty>();
        for (CourseDifficulty acceptedValue : constraintAnnotation.acceptedValues())  valueList.add(acceptedValue);
    }

    @Override
    public boolean isValid(CourseDifficulty courseName, ConstraintValidatorContext constraintValidatorContext) {
        return valueList.contains(courseName);
    }
}
