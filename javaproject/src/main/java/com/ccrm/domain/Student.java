package com.ccrm.domain;

import com.ccrm.domain.iface.Identifiable;
import com.ccrm.domain.iface.Printable;
import java.time.LocalDate;
import java.util.*;

public class Student extends Person implements Identifiable, Printable {
    private final String regNo;
    private final LocalDate joinedOn;
    private final Set<String> enrolledCourseCodes = new HashSet<>();

    private Student(Builder b) {
        super(b.id, b.fullName, b.email);
        this.regNo = b.regNo;
        this.joinedOn = b.joinedOn != null ? b.joinedOn : LocalDate.now();
    }

    public static class Builder {
        private String id, regNo, fullName, email; private LocalDate joinedOn;
        public Builder id(String id){ this.id=id; return this; }
        public Builder regNo(String regNo){ this.regNo=regNo; return this; }
        public Builder fullName(String fullName){ this.fullName=fullName; return this; }
        public Builder email(String email){ this.email=email; return this; }
        public Builder joinedOn(LocalDate d){ this.joinedOn=d; return this; }
        public Student build(){ return new Student(this); }
    }

    public String getRegNo(){ return regNo; }
    public LocalDate getJoinedOn(){ return joinedOn; }
    public Set<String> getEnrolledCourseCodes(){ return Collections.unmodifiableSet(enrolledCourseCodes); }

    public void enroll(String courseCode){ enrolledCourseCodes.add(courseCode); }
    public void unenroll(String courseCode){ enrolledCourseCodes.remove(courseCode); }

   @Override 
    public String prettyPrint(){
    return "Student[" + regNo + "] " + fullName + " (" + id + "), email=" + email;
    }
}