package utils;

import java.util.Random;

public class RandomUtils {
    public static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String getRandomString(int length) {
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            result.append(CHARS.charAt(index));
        }

        return result.toString();
    }
}
