package com.minhuchiha.SBSimpleCRUDApp.Service.Implementations;

import com.minhuchiha.SBSimpleCRUDApp.DTO.StudentDTO;
import com.minhuchiha.SBSimpleCRUDApp.Exception.NonExistingCourseException;
import com.minhuchiha.SBSimpleCRUDApp.Exception.NonExistingStudentException;
import com.minhuchiha.SBSimpleCRUDApp.Model.Student;
import com.minhuchiha.SBSimpleCRUDApp.Repository.CourseRepository;
import com.minhuchiha.SBSimpleCRUDApp.Repository.StudentRepository;
import com.minhuchiha.SBSimpleCRUDApp.Service.Interfaces.IStudentService;
import com.minhuchiha.SBSimpleCRUDApp.Utils.NullAwareBeanUtilsBean;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements IStudentService, UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private NullAwareBeanUtilsBean utilsBean;

    @Override
    public StudentDTO createNewStudent(Student student) {
        StudentDTO studentDTO = mapper.map(studentRepository.saveAndFlush(student), StudentDTO.class);
        return studentDTO;
    }

    @Override
    public void deleteStudentById(UUID id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) throws InvocationTargetException, IllegalAccessException, NonExistingStudentException {
        if (studentRepository.findById(studentDTO.getStudentId()) == null) throw new NonExistingStudentException("" + studentDTO.getStudentId());
        Student student = studentRepository.findById(studentDTO.getStudentId()).get();
        utilsBean.copyProperties(student, studentDTO);
        studentRepository.saveAndFlush(student);
        return mapper.map(student, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTOS = mapper.map(studentRepository.findAll(), new TypeToken<List<StudentDTO>>() {}.getType());
        return studentDTOS;
    }

    @Override
    public UUID registerACourse(UUID courseId) throws NonExistingCourseException {
        if (courseRepository.findById(courseId) == null) throw new NonExistingCourseException("" + courseId);

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return studentRepository.findByEmail(userEmail).map(student -> student).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
