package io.github.eggloop.utils.string;

public class StringUtils {

    public static String replace(String word, String[] keys, double[] values) {
        for (int i = 0; i < keys.length; i++) {
            word = word.replace(keys[i], String.valueOf(values[i]));
        }
        return word;
    }

    public static String formulaHash(String word, String[] keys) {
        for (String key : keys) {
            word = word.replace(key, "");
        }
        return word;
    }
}
