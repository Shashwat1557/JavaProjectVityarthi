package com.ccrm.service;

import com.ccrm.domain.Student;
import com.ccrm.repo.DataStore;
import com.ccrm.util.Validators;
import java.util.*;

public class StudentService {
    private final DataStore db = DataStore.get();

    public Student add(Student s){
        Validators.nonEmpty(s.getId(), "Student.id");
        Validators.nonEmpty(s.getRegNo(), "Student.regNo");
        Validators.email(s.getEmail());
        db.students().put(s.getId(), s); return s;
    }

    public Optional<Student> get(String id){ return Optional.ofNullable(db.students().get(id)); }
    public List<Student> list(){ return new ArrayList<>(db.students().values()); }
    public void deactivate(String id){ get(id).ifPresent(Student::deactivate); }
}