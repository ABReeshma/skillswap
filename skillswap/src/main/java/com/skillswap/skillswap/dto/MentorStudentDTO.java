package com.skillswap.skillswap.dto;

public class MentorStudentDTO {
    private Long requestId;
    private String studentName;
    private String skillTitle;
    private Integer durationWeeks;
    private String status;

    public MentorStudentDTO( Long requestId,String studentName, String skillTitle, Integer durationWeeks, String status) {
        this.requestId = requestId;
        this.studentName = studentName;
        this.skillTitle = skillTitle;
        this.durationWeeks = durationWeeks;
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }
    public Long getRequestId() {
        return requestId;
    }
    public String getSkillTitle() {
        return skillTitle;
    }

    public Integer getDurationWeeks() {
        return durationWeeks;
    }

    public String getStatus() {
        return status;
    }
}
