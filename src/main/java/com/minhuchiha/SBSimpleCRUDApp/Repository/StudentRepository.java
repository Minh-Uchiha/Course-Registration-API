package com.minhuchiha.SBSimpleCRUDApp.Repository;

import com.minhuchiha.SBSimpleCRUDApp.Model.Student;
import com.minhuchiha.SBSimpleCRUDApp.Service.Implementations.StudentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByEmail(String email);
}
