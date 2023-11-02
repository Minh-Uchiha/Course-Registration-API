package com.minhuchiha.SBSimpleCRUDApp.CustomAnnotation.Annotation;

import com.minhuchiha.SBSimpleCRUDApp.CustomAnnotation.ValidationClass.CourseDifficultyValidator;
import com.minhuchiha.SBSimpleCRUDApp.Enum.CourseDifficulty;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseDifficultyValidator.class)
public @interface ValidateCourseDifficulty {

    public CourseDifficulty[] acceptedValues();
    public String message() default "Invalid course difficulty";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};

}
