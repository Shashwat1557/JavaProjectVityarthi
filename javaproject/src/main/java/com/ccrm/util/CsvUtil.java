package com.ccrm.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public final class CsvUtil {
    private CsvUtil(){}

    public static List<String[]> read(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)){
            return lines.filter(l -> !l.isBlank() && !l.startsWith("#"))
                        .map(l -> l.split(","))
                        .collect(Collectors.toList());
        }
    }

    // Overloaded read method to optionally skip the header row
    public static List<String[]> read(Path path, boolean skipHeader) throws IOException {
        List<String[]> rows = read(path);
        if (skipHeader && !rows.isEmpty()) {
            rows.remove(0);
        }
        return rows;
    }

    public static void write(Path path, List<String[]> rows) throws IOException {
        Files.createDirectories(path.getParent());
        try(BufferedWriter w = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            for(String[] r: rows){ 
                w.write(String.join(",", r)); 
                w.newLine(); 
            }
        }
    }

    // 🔹 New method: write to default exports folder inside project
    public static void export(String fileName, List<String[]> rows) throws IOException {
        Path exportPath = Paths.get("exports", fileName);
        write(exportPath, rows);
        System.out.println("✅ Data exported to: " + exportPath.toAbsolutePath());
    }

    // 🔹 Optional: append data instead of overwriting
    public static void append(String fileName, List<String[]> rows) throws IOException {
        Path exportPath = Paths.get("exports", fileName);
        Files.createDirectories(exportPath.getParent());
        try(BufferedWriter w = Files.newBufferedWriter(exportPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
            for(String[] r: rows){ 
                w.write(String.join(",", r)); 
                w.newLine(); 
            }
        }
        System.out.println("✅ Data appended to: " + exportPath.toAbsolutePath());
    }
}