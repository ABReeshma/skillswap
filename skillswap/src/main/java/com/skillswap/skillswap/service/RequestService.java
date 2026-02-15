package com.skillswap.skillswap.service;

import com.skillswap.skillswap.dto.MentorStudentDTO;
import com.skillswap.skillswap.dto.MentorStudentResponse;
import com.skillswap.skillswap.dto.StudentCourseResponse;
import com.skillswap.skillswap.model.Request;
import com.skillswap.skillswap.model.Skill;
import com.skillswap.skillswap.model.User;
import com.skillswap.skillswap.repository.RequestRepository;
import com.skillswap.skillswap.repository.SkillRepository;
import com.skillswap.skillswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    // ---------- STUDENT : REQUEST ----------
    @PreAuthorize("hasRole('STUDENT')")
    public String requestMentorship(Long skillId, String email) {

        User student = userRepository.findByEmail(email);

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        Request request = new Request();
        request.setStudentId(student.getId());
        request.setMentorId(skill.getMentorId());
        request.setSkillId(skill.getId());
        request.setStatus("PENDING");

        requestRepository.save(request);

        return "Mentorship request sent";
    }

    // ---------- MENTOR : VIEW REQUESTS ----------
    @PreAuthorize("hasRole('MENTOR')")
    public List<Request> getMentorRequests(String email) {

        User mentor = userRepository.findByEmail(email);
        return requestRepository.findByMentorId(mentor.getId());
    }

    // ---------- MENTOR : ACCEPT ----------
    @PreAuthorize("hasRole('MENTOR')")
    public String acceptRequest(Long id, String email) {

        User mentor = userRepository.findByEmail(email);

        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!request.getMentorId().equals(mentor.getId())) {
            throw new RuntimeException("You are not authorized");
        }

        request.setStatus("ACCEPTED");
        requestRepository.save(request);

        Skill skill = skillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        skill.setStatus("OCCUPIED");
        skillRepository.save(skill);

        return "Request accepted";
    }
    public List<StudentCourseResponse> getStudentCourses(String email) {

        User student = userRepository.findByEmail(email);

        List<Request> requests =
                requestRepository.findByStudentIdAndStatus(
                        student.getId(), "ACCEPTED");

        return requests.stream().map(req -> {

            Skill skill = skillRepository.findById(req.getSkillId())
                    .orElseThrow(() -> new RuntimeException("Skill not found"));

            User mentor = userRepository.findById(req.getMentorId())
                    .orElseThrow(() -> new RuntimeException("Mentor not found"));

            String mentorName = (mentor.getName() != null)
                    ? mentor.getName()
                    : "Unknown Mentor";

            return new StudentCourseResponse(
                    skill.getTitle(),
                    mentorName,
                    req.getStatus()
            );


        }).toList();
    }

    // ---------- MENTOR : REJECT ----------
    @PreAuthorize("hasRole('MENTOR')")
    public String rejectRequest(Long id, String email) {

        User mentor = userRepository.findByEmail(email);

        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (!request.getMentorId().equals(mentor.getId())) {
            throw new RuntimeException("You are not authorized");
        }

        request.setStatus("REJECTED");
        requestRepository.save(request);

        return "Request rejected";
    }
    @PreAuthorize("hasRole('MENTOR')")
    public Page<Request> getMentorRequests(String email, int page, int size) {

        User mentor = userRepository.findByEmail(email);

        return requestRepository.findByMentorId(
                mentor.getId(),
                PageRequest.of(page, size)
        );
    }
    public List<MentorStudentResponse> getMentorStudents(String email) {

        User mentor = userRepository.findByEmail(email);

        List<Request> requests =
                requestRepository.findByMentorIdAndStatus(
                        mentor.getId(), "ACCEPTED");

        return requests.stream().map(req -> {

            User student = userRepository.findById(req.getStudentId()).orElse(null);
            Skill skill = skillRepository.findById(req.getSkillId()).orElse(null);

            return new MentorStudentResponse(
                    req.getId(),                                  // ✅ ID
                    student != null ? student.getName() : "Unknown Student",
                    skill != null ? skill.getTitle() : "Unknown Skill",
                    req.getStatus()
            );

        }).toList();
    }

    public List<MentorStudentDTO> getMentorDashboard(String email) {

        User mentor = userRepository.findByEmail(email);

        List<Request> requests = requestRepository.findByMentorId(mentor.getId());

        return requests.stream().map(r -> {
            User student = userRepository.findById(r.getStudentId()).orElseThrow();
            Skill skill = skillRepository.findById(r.getSkillId()).orElseThrow();

            return new MentorStudentDTO(
                    r.getId(),                 // 👈 requestId
                    student.getName(),
                    skill.getTitle(),
                    skill.getDurationWeeks(),
                    r.getStatus()
            );

        }).toList();
    }
    public List<MentorStudentResponse> getMentorRequestsWithDetails(String email) {

        User mentor = userRepository.findByEmail(email);

        List<Request> requests =
                requestRepository.findByMentorId(mentor.getId());

        return requests.stream().map(req -> {

            User student = userRepository.findById(req.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            Skill skill = skillRepository.findById(req.getSkillId())
                    .orElseThrow(() -> new RuntimeException("Skill not found"));

            return new MentorStudentResponse(
                    req.getId(),          // ✅ request id
                    student.getName(),    // ✅ student name
                    skill.getTitle(),     // ✅ skill title
                    req.getStatus()
            );

        }).toList();
    }

    }


