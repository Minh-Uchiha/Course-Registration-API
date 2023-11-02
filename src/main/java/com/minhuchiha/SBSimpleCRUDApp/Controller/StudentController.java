package com.minhuchiha.SBSimpleCRUDApp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhuchiha.SBSimpleCRUDApp.DTO.StudentDTO;
import com.minhuchiha.SBSimpleCRUDApp.Model.Student;
import com.minhuchiha.SBSimpleCRUDApp.Service.Interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> createNewStudent(@RequestBody Student student) {
        StudentDTO studentDTO;
        try {
            studentDTO = studentService.createNewStudent(student);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("A new student was added with the id: " + studentDTO.getStudentId(), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<String> getAllStudents() {
        String students;
        try {
            students = objectMapper.writeValueAsString(studentService.getAll());
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(students, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAStudent(@PathVariable(name = "id") UUID id) {
        try {
            studentService.deleteStudentById(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The student with id: " + id + " was deleted", HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody StudentDTO studentDTO) {
        String updatedStudent;
        try {
            updatedStudent = objectMapper.writeValueAsString(studentService.update(studentDTO));
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("The student with id: " + studentDTO.getStudentId() + " was updated to: " + updatedStudent, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerCourse(@RequestParam(name = "course-id", required = true) UUID courseId) {
        return null;
    }

}

