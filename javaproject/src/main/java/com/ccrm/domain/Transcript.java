package com.ccrm.domain;

import com.ccrm.domain.enums.Grade;
import java.util.*;

public class Transcript {
    private final String studentId;
    private final Map<String, Grade> courseGrades;
    private final Map<String, Integer> courseCredits;

    public Transcript(String studentId, Map<String, Grade> grades, Map<String, Integer> credits){
        this.studentId=studentId; this.courseGrades=grades; this.courseCredits=credits;
    }

    public double computeGPA(){
        int totalCredits = 0; int weighted = 0;
        for(var e: courseGrades.entrySet()){
            int cr = courseCredits.getOrDefault(e.getKey(), 0);
            totalCredits += cr; weighted += cr * e.getValue().getPoints();
        }
        return totalCredits==0 ? 0.0 : (double) weighted / totalCredits;
    }

    @Override public String toString(){
        StringBuilder sb = new StringBuilder("Transcript for " + studentId + "\n");
        courseGrades.forEach((c,g)-> sb.append(c).append(": ").append(g).append("\n"));
        sb.append(String.format("GPA: %.2f\n", computeGPA()));
        return sb.toString();
    }
}