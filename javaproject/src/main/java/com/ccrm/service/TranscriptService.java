package com.ccrm.service;

import com.ccrm.domain.*;
import com.ccrm.domain.enums.Grade;
import com.ccrm.repo.DataStore;
import java.util.*;

public class TranscriptService {
    private final DataStore db = DataStore.get();

    public Transcript buildTranscript(String studentId){
        Map<String, Grade> grades = new LinkedHashMap<>();
        Map<String, Integer> credits = new LinkedHashMap<>();
        db.enrollments().values().stream()
            .filter(e -> e.getStudentId().equals(studentId))
            .forEach(e -> {
                grades.put(e.getCourseCode(), e.getGrade());
                credits.put(e.getCourseCode(), db.courses().get(e.getCourseCode()).getCredits());
            });
        return new Transcript(studentId, grades, credits);
    }
}