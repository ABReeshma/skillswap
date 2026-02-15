package com.skillswap.skillswap.dto;

public class StudentCourseResponse {

    private String skillTitle;
    private String mentorName;
    private String status;

    public StudentCourseResponse(String skillTitle, String mentorName, String status) {
        this.skillTitle = skillTitle;
        this.mentorName = mentorName;
        this.status = status;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public String getMentorName() {
        return mentorName;
    }

    public String getStatus() {
        return status;
    }
}

