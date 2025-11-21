package poly.edu.java6.utils;

import java.util.UUID;

public class IdUtils {
    public static String generateNewId() {
        return UUID.randomUUID().toString();
    }
}
