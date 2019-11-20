package io.github.eggloop.application;

public enum EggloopOptions {
    HELP("-help", "show available options"),
    FEATURE_SELECTION("-featureSelection", "<trajectories file path> <storage file name>"),
    EVALUATION("-evaluation", "<storage file path> <trajectory file path>"),
    PREDICTION("-prediction", "<storage file path> <weight file path> <trajectories input path> <prediction output path> ");

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