package com.skillswap.skillswap.repository;

import com.skillswap.skillswap.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByStatus(String status);
    Page<Skill> findByStatus(String status, Pageable pageable);
}

