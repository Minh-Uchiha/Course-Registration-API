package com.minhuchiha.SBSimpleCRUDApp.Service.Interfaces;

import com.minhuchiha.SBSimpleCRUDApp.DTO.StudentDTO;
import com.minhuchiha.SBSimpleCRUDApp.Exception.NonExistingCourseException;
import com.minhuchiha.SBSimpleCRUDApp.Exception.NonExistingStudentException;
import com.minhuchiha.SBSimpleCRUDApp.Model.Student;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

public interface IStudentService {
    StudentDTO createNewStudent(Student student);
    void deleteStudentById(UUID id);
    StudentDTO update(StudentDTO studentDTO) throws InvocationTargetException, IllegalAccessException, NonExistingStudentException;
    List<StudentDTO> getAll();
    UUID registerACourse(UUID courseId) throws NonExistingCourseException;

}
