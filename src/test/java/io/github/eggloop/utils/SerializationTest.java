package io.github.eggloop.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

class SerializationTest {

    @Test
    void testSerialize(@TempDir Path tempDir) throws IOException, ClassNotFoundException {
        String filePath = tempDir.resolve("file").toString();

        Serialization.serialize(new double[]{1, 3, 4}, filePath);
        double[] deSerialize = (double[]) Serialization.deSerialize(filePath);

        Assertions.assertArrayEquals(new double[]{1, 3, 4}, deSerialize);
    }
}