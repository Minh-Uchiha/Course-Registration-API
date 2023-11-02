package com.minhuchiha.SBSimpleCRUDApp.Config;

import com.minhuchiha.SBSimpleCRUDApp.DTO.CourseDTO;
import com.minhuchiha.SBSimpleCRUDApp.DTO.StudentDTO;
import com.minhuchiha.SBSimpleCRUDApp.Model.Course;
import com.minhuchiha.SBSimpleCRUDApp.Model.Student;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.attribute.standard.Destination;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldMatchingEnabled(true).setSkipNullEnabled(true);
        mapper.typeMap(CourseDTO.class, Course.class).addMappings(m -> {
            m.map(src -> src.getCourseId(), Course::setCourseId);
            m.map(src -> src.getCourseName(), Course::setCourseName);
            m.map(src -> src.getDifficulty(), Course::setDifficulty);
        });
        mapper.typeMap(Student.class, StudentDTO.class).addMappings(m -> {
            m.map(src -> src.getStudentId(), StudentDTO::setStudentId);
            m.map(src -> src.getName(), StudentDTO::setName);
            m.map(src -> src.getDob(), StudentDTO::setDob);
            m.map(src -> src.getGpa(), StudentDTO::setGpa);
            m.map(src -> src.getEmail(), StudentDTO::setEmail);
        });
        return mapper;
    }
}
