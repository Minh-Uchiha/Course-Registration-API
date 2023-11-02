package com.minhuchiha.SBSimpleCRUDApp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhuchiha.SBSimpleCRUDApp.DTO.CourseDTO;
import com.minhuchiha.SBSimpleCRUDApp.Model.Course;
import com.minhuchiha.SBSimpleCRUDApp.Service.Interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    @Autowired
    ICourseService courseService;
    @Autowired
    ObjectMapper objectMapper;

    // Add a new course to the database
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createNewCourse(@RequestBody CourseDTO courseDTO) {
        Course savedCourse;
        try {
            savedCourse = courseService.createNewCourse(courseDTO);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("A new course was added with an id: " + savedCourse.getCourseId(), HttpStatus.CREATED);
    }

    // Get a course by some properties
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<String> getCourse(@RequestParam(name = "id", required = false) UUID id, @RequestParam(name = "name", required = false) String courseName) {
        String course;
        try {
            if (id != null) course = objectMapper.writeValueAsString(courseService.getCourseById(id));
            else if (courseName != null )course = objectMapper.writeValueAsString(courseService.getCourseByName(courseName));
            else throw new Exception("Error: no parameters passed in");
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(course, HttpStatus.ACCEPTED);
    }

    // Get all courses
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<String> getAllCourses() {
        String courses;
        try {
            courses = objectMapper.writeValueAsString(courseService.getAllCourses());
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(courses, HttpStatus.ACCEPTED);
    }

    // Delete a course by id
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable(name = "id") UUID id) {
        courseService.deleteById(id);
        return new ResponseEntity<>("The course with id: " + id + " has been deleted", HttpStatus.ACCEPTED);
    }

    // Update a course
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody CourseDTO courseDTO) {
        String updatedCourse;
        try {
            updatedCourse = objectMapper.writeValueAsString(courseService.update(courseDTO));
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("The course with id " + courseDTO.getCourseId() + " has been updated to:\n" + updatedCourse, HttpStatus.ACCEPTED);
    }

}
