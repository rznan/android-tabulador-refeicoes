package br.com.renan.trabalho_semestral.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author: renan santos carvalho
 */
public abstract class SafeParser {

    public static int safeParseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double safeParseDouble(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static float safeParseFloat(String value, float defaultValue) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long safeParseLong(String value, long defaultValue) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static LocalDate safeParseLocalDate(String value, LocalDate defaultValue) {
        LocalDate res = safeParseLocalDate(value, "yyyy/MM/dd");
        if (res != null) {
            return res;
        }

        res = safeParseLocalDate(value, "dd/MM/yyyy");
        if (res != null) {
            return res;
        }

        return defaultValue;
    }

    private static LocalDate safeParseLocalDate(String value, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(value, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
