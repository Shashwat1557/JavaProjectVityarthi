package com.ccrm.domain;

import java.time.LocalDateTime;

public abstract class Person {
    protected String id;
    protected String fullName;
    protected String email;
    protected boolean active = true;
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime updatedAt = LocalDateTime.now();

    protected Person(String id, String fullName, String email) {
        this.id = id; this.fullName = fullName; this.email = email;
    }
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; this.updatedAt = LocalDateTime.now(); }
    @Override public String toString() { return fullName + " <" + email + ">"; }
}