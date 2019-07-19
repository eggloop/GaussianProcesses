package io.github.eggloop.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    private FileUtils() {
        //Utility class
    }

    public static String readFileToString(String location) throws IOException {
        return new String(Files.readAllBytes(Paths.get(location)));
    }
}