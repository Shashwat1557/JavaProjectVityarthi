package com.ccrm.domain;

import com.ccrm.domain.enums.Semester;
import com.ccrm.domain.iface.Printable;

public class Course implements Printable {
    private final String code;
    private String title;
    private int credits;
    private String instructorId;
    private Semester semester;
    private String department;
    private boolean active = true;

    private Course(Builder b){
        this.code=b.code; this.title=b.title; this.credits=b.credits;
        this.instructorId=b.instructorId; this.semester=b.semester; this.department=b.department;
    }

    public static class Builder {
        private String code, title, instructorId, department; private int credits; private Semester semester;
        public Builder code(String code){ this.code=code; return this; }
        public Builder title(String title){ this.title=title; return this; }
        public Builder credits(int credits){ this.credits=credits; return this; }
        public Builder instructorId(String id){ this.instructorId=id; return this; }
        public Builder semester(Semester s){ this.semester=s; return this; }
        public Builder department(String d){ this.department=d; return this; }
        public Course build(){ return new Course(this); }
    }

    public String getCode(){ return code; }
    public String getTitle(){ return title; }
    public int getCredits(){ return credits; }
    public String getInstructorId(){ return instructorId; }
    public Semester getSemester(){ return semester; }
    public String getDepartment(){ return department; }
    public boolean isActive(){ return active; }
    public void deactivate(){ this.active=false; }

    @Override public String prettyPrint(){
        return code + ": " + title + " [" + credits + " cr, " + semester + ", " + department + "]";
    }
}