package io.github.eggloop.utils;

import java.io.*;

public class Serialization {

    public static void serialize(Object object, String outputLocation) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(outputLocation)))) {
            oos.writeObject(object);
        }
    }

    public static Object deSerialize(String path) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path);
        try (ObjectInputStream iis = new ObjectInputStream(fis)) {
            return iis.readObject();
        }
    }
}
