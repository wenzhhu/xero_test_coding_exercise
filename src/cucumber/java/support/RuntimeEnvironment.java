package support;

public final class RuntimeEnvironment {
    private RuntimeEnvironment() {}

    public static boolean isWindows() {
        String osName = System.getProperty("os.name");
        return osName.toUpperCase().startsWith("WINDOWS");
    }

    public static boolean isMac() {
        String osName = System.getProperty("os.name");
        return osName.toUpperCase().contains("MAC");
    }

    public static boolean isLinux() {
        return !(isWindows() || isMac());
    }

}
