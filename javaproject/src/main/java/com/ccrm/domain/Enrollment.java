package com.ccrm.domain;

import com.ccrm.domain.enums.Grade;

public class Enrollment {
    private final String studentId;
    private final String courseCode;
    private Integer marks;
    private Grade grade;

    public Enrollment(String studentId, String courseCode){
        this.studentId=studentId; this.courseCode=courseCode;
    }
    public String getStudentId(){ return studentId; }
    public String getCourseCode(){ return courseCode; }
    public Integer getMarks(){ return marks; }
    public Grade getGrade(){ return grade; }
    public void recordMarks(int m){ this.marks=m; this.grade = GradeScale.of(m); }

    // Static nested helper for marks->grade mapping (nested class usage)
    public static class GradeScale {
        public static Grade of(int m){
            if(m>=90) return Grade.S; if(m>=80) return Grade.A; if(m>=70) return Grade.B;
            if(m>=60) return Grade.C; if(m>=50) return Grade.D; if(m>=40) return Grade.E; return Grade.F;
        }
    }
}