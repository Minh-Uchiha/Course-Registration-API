package com.minhuchiha.SBSimpleCRUDApp.Repository;

import com.minhuchiha.SBSimpleCRUDApp.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Course findByCourseNameIgnoreCase(String courseName);
}
