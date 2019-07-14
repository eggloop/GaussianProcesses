package io.github.eggloop.application;

public enum EggloopOptions {
    HELP("-help", "show alla available options"),
    TRAJECTORY_FILE_PATH("-trajectory", "path of the file storing the trajectory"),
    FORMULAS("-formula", "<ALL> write all STL formula results, this is the unique parameter"),
    OUTPUT_FILE_PATH("-output", "path of the file where store results");


    private String key;
    private String value;

    EggloopOptions(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public boolean is(String value) {
        return key.equals(value);
    }

    public static String printAll() {
        StringBuilder builder = new StringBuilder();
        builder.append("Available Options:").append("\n");
        for (EggloopOptions options : EggloopOptions.values()) {
            builder.append(options.key);
            builder.append(" : ");
            builder.append(options.value);
            builder.append("\n");
        }
        return builder.toString();
    }
}
