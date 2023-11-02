package com.minhuchiha.SBSimpleCRUDApp.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class CourseParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "FK_Student_CourseParticipation"))
    private Student student;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "FK_Course_CourseParticipation"))
    private Course course;

    @Min(0)
    @Max(100)
    @Column(name = "grade", nullable = false, updatable = true, columnDefinition = "INTEGER DEFAULT 100")
    private Integer grade;

    @NotBlank(message = "Professor's name must be provided")
    private String professorName;

    @Past
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    @Column(name = "registered date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateTaken;

    @Past
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss a")
    @Column(name = "finished date", nullable = true, updatable = true)
    private LocalDateTime dateFinished;

}
