package helpers;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentHelper {
    public static String firstEmailLogin;
    public static String firstEmailPassword;
    public static String secondEmailLogin;
    public static String secondEmailPassword;

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
        System.out.println(firstEmailLogin);
        System.out.println(firstEmailPassword);
        System.out.println(secondEmailLogin);
        System.out.println(secondEmailPassword);

    }
}
