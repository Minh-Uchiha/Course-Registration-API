package com.minhuchiha.SBSimpleCRUDApp.Service.Interfaces;

import com.minhuchiha.SBSimpleCRUDApp.DTO.CourseDTO;
import com.minhuchiha.SBSimpleCRUDApp.Exception.NonExistingCourseException;
import com.minhuchiha.SBSimpleCRUDApp.Model.Course;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

public interface ICourseService {
    Course createNewCourse(CourseDTO courseDTO);
    Course getCourseById(UUID id);
    List<Course> getAllCourses();
    Course getCourseByName(String courseName);
    Course update(CourseDTO courseDTO) throws NonExistingCourseException, InvocationTargetException, IllegalAccessException;
    void deleteById(UUID id);
}
