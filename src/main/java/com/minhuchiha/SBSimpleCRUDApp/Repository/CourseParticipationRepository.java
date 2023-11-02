package com.minhuchiha.SBSimpleCRUDApp.Repository;

import com.minhuchiha.SBSimpleCRUDApp.Model.CourseParticipation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseParticipationRepository extends JpaRepository<CourseParticipation, UUID> {
}
