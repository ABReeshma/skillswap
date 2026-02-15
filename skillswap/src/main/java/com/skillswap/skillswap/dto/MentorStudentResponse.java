package com.skillswap.skillswap.dto;

public class MentorStudentResponse {

    private Long id;   // ✅ ADD THIS
    private String studentName;
    private String skillTitle;
    private String status;

    public MentorStudentResponse(Long id, String studentName, String skillTitle, String status) {
        this.id = id;
        this.studentName = studentName;
        this.skillTitle = skillTitle;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getSkillTitle() { return skillTitle; }
    public String getStatus() { return status; }
}
