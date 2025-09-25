package com.ccrm;

import com.ccrm.cli.Menu;
import com.ccrm.domain.Course;
import com.ccrm.domain.Student;
import com.ccrm.domain.enums.Semester;
import com.ccrm.repo.DataStore;
import com.ccrm.service.*;
import com.ccrm.util.CsvUtil;
import com.ccrm.util.FileBackupUtil;
import java.nio.file.Path;
import java.util.*;

public class App {
    public static void main(String[] args) {
        var menu = new Menu();
        var students = new StudentService();
        var courses = new CourseService();
        var enroll = new EnrollmentService();
        var transcripts = new TranscriptService();

        mainLoop: while (true) { // labeled loop
            try {
                switch (menu.mainMenu()) {
                    case 1 -> { // Students
                        System.out.println("1) Add  2) List  3) Deactivate  4) Print Profile");
                        int op = Integer.parseInt(menu.prompt("Option"));
                        if (op == 1) {
                            var s = new Student.Builder()
                                    .id(menu.prompt("ID"))
                                    .regNo(menu.prompt("Reg No"))
                                    .fullName(menu.prompt("Full Name"))
                                    .email(menu.prompt("Email"))
                                    .build();
                            students.add(s);
                            System.out.println("Added: " + s.prettyPrint());
                        } else if (op == 2) {
                            for (Student s : students.list()) System.out.println(s.prettyPrint());
                        } else if (op == 3) {
                            students.deactivate(menu.prompt("Student ID"));
                            System.out.println("Deactivated.");
                        } else if (op == 4) {
                            var id = menu.prompt("Student ID");
                            students.get(id).ifPresentOrElse(
                                s -> System.out.println(s.prettyPrint() + " courses=" + s.getEnrolledCourseCodes()),
                                () -> System.out.println("Not found"));
                        } else { System.out.println("Unknown"); }
                    }
                    case 2 -> { // Courses
                        System.out.println("1) Add  2) List  3) Search  4) Deactivate");
                        int op = Integer.parseInt(menu.prompt("Option"));
                        if (op == 1) {
                            var c = new Course.Builder()
                                    .code(menu.prompt("Code"))
                                    .title(menu.prompt("Title"))
                                    .credits(Integer.parseInt(menu.prompt("Credits")))
                                    .instructorId(menu.prompt("Instructor ID"))
                                    .semester(Semester.valueOf(menu.prompt("Semester [SPRING/SUMMER/FALL]").toUpperCase()))
                                    .department(menu.prompt("Department"))
                                    .build();
                            courses.add(c);
                            System.out.println("Added: " + c.prettyPrint());
                        } else if (op == 2) {
                            courses.list().forEach(c -> System.out.println(c.prettyPrint()));
                        } else if (op == 3) {
                            var instr = menu.prompt("Instructor ID (blank=any)");
                            var dept = menu.prompt("Department (blank=any)");
                            var sem = menu.prompt("Semester (blank=any)");
                            var res = courses.search(
                                instr.isBlank()?Optional.empty():Optional.of(instr),
                                dept.isBlank()?Optional.empty():Optional.of(dept),
                                sem.isBlank()?Optional.empty():Optional.of(Semester.valueOf(sem.toUpperCase()))
                            );
                            res.forEach(c -> System.out.println(c.prettyPrint()));
                        } else if (op == 4) {
                            var code = menu.prompt("Course Code");
                            Optional.ofNullable(DataStore.get().courses().get(code)).ifPresent(Course::deactivate);
                            System.out.println("Deactivated.");
                        }
                    }
                    case 3 -> { // Enrollment & Grades
                        System.out.println("1) Enroll  2) Unenroll  3) Record Marks");
                        int op = Integer.parseInt(menu.prompt("Option"));
                        switch (op) {
                            case 1 -> enroll.enroll(menu.prompt("Student ID"), menu.prompt("Course Code"));
                            case 2 -> enroll.unenroll(menu.prompt("Student ID"), menu.prompt("Course Code"));
                            case 3 -> enroll.recordMarks(menu.prompt("Student ID"), menu.prompt("Course Code"), Integer.parseInt(menu.prompt("Marks")));
                            default -> System.out.println("Unknown");
                        }
                    }
                    case 4 -> { // Transcripts
                        var t = transcripts.buildTranscript(menu.prompt("Student ID"));
                        System.out.println(t);
                    }
                    case 5 -> { // Import/Export/Backup
                        System.out.println("1) Export  2) Import  3) Backup dir  4) Dir size");
                        int op = Integer.parseInt(menu.prompt("Option"));
                        if (op == 1) {
                            var rows = new java.util.ArrayList<String[]>();
                            for (var s : new StudentService().list()) {
                                rows.add(new String[]{s.getId(), s.getRegNo(), s.getFullName(), s.getEmail()});
                            }
                            try {
                                CsvUtil.write(Path.of("/Users/shashwat/Documents/javaproject/students.csv"), rows);
                                System.out.println("Exported to exports/students.csv");
                            } catch (Exception e){ System.out.println("Export failed: " + e.getMessage()); }
                        } else if (op == 2) {
                            try {
                                // Read CSV and skip the header row so the header is not treated as data
                                var rows = CsvUtil.read(Path.of(menu.prompt("CSV path")), true);
                                for (String[] r : rows) {
                                    if (r.length < 4) continue; // skip malformed lines
                                    new StudentService().add(
                                        new Student.Builder().id(r[0]).regNo(r[1]).fullName(r[2]).email(r[3]).build()
                                    );
                                }
                                System.out.println("Imported " + rows.size() + " rows");
                            } catch (Exception e){ System.out.println("Import failed: " + e.getMessage()); }
                        } else if (op == 3) {
                            try {
                                var src = Path.of(menu.prompt("Source dir"));
                                var dstRoot = Path.of(menu.prompt("Backups root"));
                                var created = FileBackupUtil.backupDirectory(src, dstRoot);
                                System.out.println("Backup created at: " + created);
                            } catch (Exception e){ System.out.println("Backup failed: " + e.getMessage()); }
                        } else if (op == 4) {
                            try {
                                var size = FileBackupUtil.recursiveSize(Path.of(menu.prompt("Dir")));
                                System.out.println("Total bytes: " + size);
                            } catch (Exception e){ System.out.println("Failed: " + e.getMessage()); }
                        }
                    }
                    case 0 -> {
                        System.out.println("Bye!");
                        break mainLoop; // labeled break
                    }
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}