package com.epam.university.java.core.task059;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileVisitor extends DefaultFileVisitor {

    private final List<String> result;
    private final String target;

    public FileVisitor(String target) {
        result = new ArrayList<>();
        this.target = target;
    }

    @Override
    public List<String> getResult() {
        return result;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        final List<String> bytes = Files.readAllLines(file);
        for (String s : bytes) {
            if (s.contains(target)) {
                result.add(file.toAbsolutePath().toString());
            }
        }

        return FileVisitResult.CONTINUE;
    }
}
