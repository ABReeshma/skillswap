package com.skillswap.skillswap.service;

import com.skillswap.skillswap.model.Skill;
import com.skillswap.skillswap.model.User;
import com.skillswap.skillswap.repository.SkillRepository;
import com.skillswap.skillswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('MENTOR')")
    public Skill addSkill(Skill skill, String email) {

        User mentor = userRepository.findByEmail(email);

        skill.setMentorId(mentor.getId());
        skill.setStatus("AVAILABLE");

        return skillRepository.save(skill);
    }

    public List<Skill> getAvailableSkills() {
        return skillRepository.findByStatus("AVAILABLE");
    }
    public Page<Skill> getAvailableSkills(int page, int size) {
        return skillRepository.findByStatus(
                "AVAILABLE",
                PageRequest.of(page, size)
        );
    }
}
