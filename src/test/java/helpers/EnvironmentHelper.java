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
                .directory("./src/test/resources/") //specify directory with .env file
                .ignoreIfMalformed() //Do not throw when .env entries are malformed. Malformed entries are skipped.
                .ignoreIfMissing() //Do not throw when .env does not exist.
                // Dotenv will continue to retrieve environment variables that are set in the environment e.g. dotenv["HOME"]
                .load();

        firstEmailLogin = dotenv.get("firstEmailLogin");
        firstEmailPassword=dotenv.get("firstEmailPassword");
        secondEmailLogin=dotenv.get("secondEmailLogin");
        secondEmailPassword=dotenv.get("secondEmailPassword");

        browser = System.getProperty("browser", DEFAULT_BROWSER);
    }
}
