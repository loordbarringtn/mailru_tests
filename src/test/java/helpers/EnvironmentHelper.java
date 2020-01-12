package helpers;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentHelper {
    public static String firstEmailLogin;
    public static String firstEmailPassword;
    public static String secondEmailLogin;
    public static String secondEmailPassword;

    private static String DEFAULT_BROWSER = "chrome";
    public static String browser;

    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                .directory("./src/test/resources/")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        firstEmailLogin = dotenv.get("firstEmailLogin");
        firstEmailPassword=dotenv.get("firstEmailPassword");
        secondEmailLogin=dotenv.get("secondEmailLogin");
        secondEmailPassword=dotenv.get("secondEmailPassword");

        browser = System.getProperty("browser", DEFAULT_BROWSER);
    }
}
