package com.minhuchiha.SBSimpleCRUDApp.Service.Implementations;

import com.minhuchiha.SBSimpleCRUDApp.DTO.CourseDTO;
import com.minhuchiha.SBSimpleCRUDApp.Enum.CourseDifficulty;
import com.minhuchiha.SBSimpleCRUDApp.Exception.NonExistingCourseException;
import com.minhuchiha.SBSimpleCRUDApp.Model.Course;
import com.minhuchiha.SBSimpleCRUDApp.Repository.CourseRepository;
import com.minhuchiha.SBSimpleCRUDApp.Service.Interfaces.ICourseService;
import com.minhuchiha.SBSimpleCRUDApp.Utils.NullAwareBeanUtilsBean;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private NullAwareBeanUtilsBean utilsBean;

    @Override
    public Course createNewCourse(CourseDTO courseDTO) {
        System.out.println("Course difficulty:" + courseDTO.getDifficulty());
        Course c = mapper.map(courseDTO, Course.class);
        return courseRepository.saveAndFlush(c);
    }

    @Override
    public Course getCourseById(UUID id) throws EntityNotFoundException {
        return courseRepository.findById(id).get();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseRepository.findByCourseNameIgnoreCase(courseName);
    }

    @Override
    public Course update(CourseDTO courseDTO) throws NonExistingCourseException, InvocationTargetException, IllegalAccessException {
        if (courseRepository.findById(courseDTO.getCourseId()) == null) throw new NonExistingCourseException("" + courseDTO.getCourseId());
        Course course = courseRepository.findById(courseDTO.getCourseId()).get();
        utilsBean.copyProperties(course, courseDTO);
        courseRepository.saveAndFlush(course);
        return course;
    }

    @Override
    public void deleteById(UUID id) {
        courseRepository.deleteById(id);
    }
}
