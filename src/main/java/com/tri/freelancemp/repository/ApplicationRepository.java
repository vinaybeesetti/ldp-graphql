package com.tri.freelancemp.repository;

import com.tri.freelancemp.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> findByIdAndApplicantId(Long id, Integer applicantId);
}
