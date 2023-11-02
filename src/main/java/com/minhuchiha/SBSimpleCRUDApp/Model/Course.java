package com.minhuchiha.SBSimpleCRUDApp.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minhuchiha.SBSimpleCRUDApp.CustomAnnotation.Annotation.ValidateCourseDifficulty;
import com.minhuchiha.SBSimpleCRUDApp.Enum.CourseDifficulty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID courseId;
    @NotBlank(message = "Course name is mandatory")
    private String courseName;
    @ValidateCourseDifficulty(acceptedValues = {CourseDifficulty.EASY, CourseDifficulty.MEDIUM, CourseDifficulty.HARD})
    private CourseDifficulty difficulty;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseParticipation> registrations;

}
