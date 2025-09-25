package com.ccrm.service;

import com.ccrm.domain.*;
import com.ccrm.repo.DataStore;
import com.ccrm.util.Exceptions;
import java.util.*;

public class EnrollmentService {
    private final DataStore db = DataStore.get();
    private static final int MAX_CREDITS_PER_SEM = 24; // example rule

    public void enroll(String studentId, String courseCode){
        Student s = Optional.ofNullable(db.students().get(studentId))
            .orElseThrow(() -> new Exceptions.NotFoundException("Student not found"));
        Course c = Optional.ofNullable(db.courses().get(courseCode))
            .orElseThrow(() -> new Exceptions.NotFoundException("Course not found"));

        int currentCredits = db.enrollments().values().stream()
            .filter(e -> e.getStudentId().equals(studentId))
            .mapToInt(e -> db.courses().get(e.getCourseCode()).getCredits())
            .sum();
        if(currentCredits + c.getCredits() > MAX_CREDITS_PER_SEM)
            throw new Exceptions.BusinessRuleException("Max credits exceeded");

        String key = studentId + "|" + courseCode;
        db.enrollments().putIfAbsent(key, new Enrollment(studentId, courseCode));
        s.enroll(courseCode);
    }

    public void unenroll(String studentId, String courseCode){
        String key = studentId + "|" + courseCode; db.enrollments().remove(key);
        Optional.ofNullable(db.students().get(studentId)).ifPresent(st -> st.unenroll(courseCode));
    }

    public void recordMarks(String studentId, String courseCode, int marks){
        String key = studentId + "|" + courseCode;
        Enrollment e = Optional.ofNullable(db.enrollments().get(key))
            .orElseThrow(() -> new Exceptions.NotFoundException("Enrollment not found"));
        e.recordMarks(marks);
    }
}