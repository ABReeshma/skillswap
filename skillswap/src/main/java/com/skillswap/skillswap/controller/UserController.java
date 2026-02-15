package com.skillswap.skillswap.controller;

import com.skillswap.skillswap.dto.*;
import com.skillswap.skillswap.model.Skill;
import com.skillswap.skillswap.model.Request;
import com.skillswap.skillswap.model.User;
import com.skillswap.skillswap.service.SkillService;
import com.skillswap.skillswap.service.RequestService;
import com.skillswap.skillswap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private RequestService requestService;

    // ---------- REGISTER ----------
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    // ---------- LOGIN ----------
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    // ---------- PROFILE (JWT TEST) ----------
    @GetMapping("/profile")
    public String profile() {
        return "This is protected profile API";
    }

    // ---------- MENTOR : ADD SKILL ----------
    @PreAuthorize("hasRole('MENTOR')")
    @PostMapping("/mentor/add-skill")
    public Skill addSkill(@RequestBody Skill skill) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return skillService.addSkill(skill, email);
    }

    // ---------- VIEW AVAILABLE SKILLS ----------
    @GetMapping("/skills")
    public Page<Skill> getAvailableSkills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return skillService.getAvailableSkills(page, size);
    }
    @GetMapping("/student/my-courses")
    @PreAuthorize("hasRole('STUDENT')")
    public List<StudentCourseResponse> getMyCourses() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return requestService.getStudentCourses(email);
    }


    // ---------- STUDENT : REQUEST ----------
    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/student/request/{skillId}")
    public String requestMentorship(@PathVariable Long skillId) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return requestService.requestMentorship(skillId, email);
    }

    // ---------- MENTOR : VIEW REQUESTS ----------
    @GetMapping("/mentor/requests")
    @PreAuthorize("hasRole('MENTOR')")
    public List<MentorStudentResponse> getMentorRequests() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return requestService.getMentorRequestsWithDetails(email);
    }


    // ---------- MENTOR : ACCEPT ----------
    @PreAuthorize("hasRole('MENTOR')")
    @PostMapping("/mentor/request/{id}/accept")
    public String acceptRequest(@PathVariable Long id) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return requestService.acceptRequest(id, email);
    }

    // ---------- MENTOR : REJECT ----------
    @PreAuthorize("hasRole('MENTOR')")
    @PostMapping("/mentor/request/{id}/reject")
    public String rejectRequest(@PathVariable Long id) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return requestService.rejectRequest(id, email);
    }
    @GetMapping("/mentor/students")
    @PreAuthorize("hasRole('MENTOR')")
    public List<MentorStudentResponse> getSelectedStudents() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return requestService.getMentorStudents(email);
    }
    @GetMapping("/mentor/dashboard")
    @PreAuthorize("hasRole('MENTOR')")
    public List<MentorStudentDTO> mentorDashboard() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return requestService.getMentorDashboard(email);
    }

}
