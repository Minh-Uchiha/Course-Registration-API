package com.minhuchiha.SBSimpleCRUDApp.DTO;

import com.minhuchiha.SBSimpleCRUDApp.Enum.CourseDifficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseDTO {
    private UUID courseId;
    private String courseName;
    private CourseDifficulty difficulty;
}
