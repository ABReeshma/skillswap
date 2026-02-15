package com.skillswap.skillswap.repository;

import com.skillswap.skillswap.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findByStudentIdAndSkillId(Long studentId, Long skillId);
    Page<Request> findByMentorId(Long mentorId, Pageable pageable);
    List<Request> findByStudentIdAndStatus(Long studentId, String status);
    List<Request> findByMentorIdAndStatus(Long mentorId, String status);
    List<Request> findByMentorId(Long mentorId);
}
