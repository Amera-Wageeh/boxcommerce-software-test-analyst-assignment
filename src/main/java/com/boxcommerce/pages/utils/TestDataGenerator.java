package com.boxcommerce.pages.utils;

import java.util.Random;

public class TestDataGenerator {

    private static final Random random = new Random();

    // This is made to Generate unique email
    public static String generateEmail() {
        long timestamp = System.currentTimeMillis();
        return "user" + timestamp + "@example.com";
    }

    // This is made to Generate random 9-digit phone number (starting with non-zero)
    public static String generatePhoneNumber() {
        // Valid UAE operator prefixes
        String[] prefixes = {"50", "52", "54", "55", "56", "58"};
        String prefix = prefixes[random.nextInt(prefixes.length)];

        // Generate remaining 7 digits
        int number = 1000000 + random.nextInt(9000000);
        return prefix + number;
    }
}