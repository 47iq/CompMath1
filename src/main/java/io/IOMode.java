package io;

public enum IOMode {
    CONSOLE,
    FILE;

    public static IOMode get(String readLine) {
        switch (readLine) {
            case "1":
                return CONSOLE;
            default:
                return FILE;
        }
    }
}
