package com.ccrm.service;

import com.ccrm.domain.Course;
import com.ccrm.domain.enums.Semester;
import com.ccrm.repo.DataStore;
import java.util.*;
import java.util.stream.*;

public class CourseService {
    private final DataStore db = DataStore.get();

    public Course add(Course c){ db.courses().put(c.getCode(), c); return c; }
    public List<Course> list(){ return new ArrayList<>(db.courses().values()); }

    public List<Course> search(Optional<String> instructorId, Optional<String> dept, Optional<Semester> sem){
        return db.courses().values().stream()
            .filter(Course::isActive)
            .filter(c -> instructorId.map(i -> i.equalsIgnoreCase(c.getInstructorId())).orElse(true))
            .filter(c -> dept.map(d -> d.equalsIgnoreCase(c.getDepartment())).orElse(true))
            .filter(c -> sem.map(s -> s == c.getSemester()).orElse(true))
            .sorted(Comparator.comparing(Course::getCode))
            .collect(Collectors.toList());
    }
}