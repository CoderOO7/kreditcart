package com.kredicart.order.Utils;

import java.security.SecureRandom;

// Todo: create strategy to generateOrderCode based on country/currency
public class OrderCodeGenerator {
    private static final String COUNTRY_PREFIX = "IND";
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    private static final SecureRandom random = new SecureRandom();

    public static String generate() {
        StringBuilder sb = new StringBuilder(COUNTRY_PREFIX).append("-");
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }
}
