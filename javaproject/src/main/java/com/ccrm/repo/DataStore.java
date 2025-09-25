package com.ccrm.repo;

import com.ccrm.domain.*;
import java.util.*;

public class DataStore {
    private static final DataStore INSTANCE = new DataStore();
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Instructor> instructors = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private final Map<String, Enrollment> enrollments = new HashMap<>(); // key: studentId|courseCode

    private DataStore() {}
    public static DataStore get(){ return INSTANCE; }

    public Map<String, Student> students(){ return students; }
    public Map<String, Course> courses(){ return courses; }
    public Map<String, Enrollment> enrollments(){ return enrollments; }
}