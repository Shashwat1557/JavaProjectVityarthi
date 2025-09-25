package com.ccrm.util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public final class FileBackupUtil {
    private FileBackupUtil(){}

    public static Path backupDirectory(Path source, Path backupsRoot) throws IOException {
        if (!Files.exists(source)) throw new IOException("Source does not exist: " + source);
        String stamp = java.time.LocalDateTime.now().toString().replace(":", "-");
        Path target = backupsRoot.resolve("backup-" + stamp);
        Files.createDirectories(target);
        Files.walkFileTree(source, new SimpleFileVisitor<>() {
            @Override public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path rel = source.relativize(dir);
                Files.createDirectories(target.resolve(rel));
                return FileVisitResult.CONTINUE;
            }
            @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, target.resolve(source.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
        return target;
    }

    // Recursively compute total size of a directory
    public static long recursiveSize(Path dir) throws IOException {
        final long[] total = {0L};
        Files.walkFileTree(dir, new SimpleFileVisitor<>() {
            @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                total[0] += attrs.size(); return FileVisitResult.CONTINUE;
            }
        });
        return total[0];
    }
}