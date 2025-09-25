# JavaProjectVityarthi
Campus Course & Records Manager (CCRM)

A console-based Java SE application for managing students, courses, enrollments, grades, and transcripts in a university setting.
This project demonstrates core Java concepts (OOP pillars, exception handling, collections, streams, NIO.2 file operations, recursion, enums, lambdas, and design patterns like Singleton & Builder) while offering practical features like data import/export, backup utilities, and transcript generation.

✨ Features

Student Management – Create, update, list, and deactivate students; generate profiles & transcripts.

Course Management – Add, update, search, and filter courses by department, semester, or instructor.

Enrollment & Grading – Enroll/unenroll students with rules (e.g., max credits/semester), compute GPA, and record letter grades.

File Operations – Import/export data using CSV/text files, create timestamped backups, and recursive directory utilities.

Reports – Stream-based GPA distribution and top student reports.

CLI Workflow – Menu-driven interface covering all operations.

🛠️ Tech Stack

Language: Java SE

Architecture: Modular packages (cli, domain, service, io, util, config)

Concepts: Encapsulation, Inheritance, Abstraction, Polymorphism, Interfaces, Nested Classes, Lambdas, Enums

APIs & Utilities: Java Date/Time API, NIO.2, Streams, Assertions

# Java Concepts & Project Guide

## 📌 Evolution of Java
- **1995**: Java introduced by Sun Microsystems (James Gosling).  
- **1996**: JDK 1.0 released.  
- **1998**: J2SE (Java 2 Platform Standard Edition) introduced.  
- **2004**: J2EE renamed as Java EE, J2SE as Java SE.  
- **2006**: Java becomes open-source under GPL.  
- **2009**: Oracle acquires Sun Microsystems.  
- **2011 onwards**: Regular 6-month release cycle introduced.  
- **Current**: Java 17 (LTS) and Java 21 (LTS) widely used.  

---

## 📌 Java Editions: ME vs SE vs EE

| Edition | Full Form | Use Case | Features |
|---------|-----------|----------|----------|
| **Java ME** | Micro Edition | Embedded systems, IoT, mobile devices | Lightweight, small footprint |
| **Java SE** | Standard Edition | Desktop & Core Java programming | Core APIs (OOP, Collections, Threads, JDBC, etc.) |
| **Java EE** | Enterprise Edition | Enterprise-level web applications | Servlets, JSP, EJB, JPA, Web Services |

---

## 📌 JDK, JRE, JVM Explanation
- **JVM (Java Virtual Machine)**: Executes Java bytecode (.class files). Platform-independent runtime.  
- **JRE (Java Runtime Environment)**: JVM + core libraries + components to run Java applications. (No compiler).  
- **JDK (Java Development Kit)**: JRE + compiler (`javac`) + developer tools (debugger, javadoc). Required for development.  

---

## 📌 Windows Installation & Eclipse Setup

### Install Java JDK
1. Download JDK (17/21) from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://jdk.java.net/).  
2. Run installer and follow the prompts.  
3. Set environment variables:  
   - **JAVA_HOME** → `C:\Program Files\Java\jdk-17`  
   - Update **PATH** → Add `%JAVA_HOME%\bin`  
4. Verify installation:
   ```bash
   java -version
   javac -version


