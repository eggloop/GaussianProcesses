package io.github.eggloop.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUtilsTest {

    @Test
    void readFileToString(@TempDir Path tempdir) throws IOException {
        File file = tempdir.resolve("file.txt").toFile();
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("aaa");
        }

        String actual = FileUtils.readFileToString(file.getAbsolutePath());

        assertEquals("aaa", actual);
    }
}