package com.zs.comm;

import java.math.BigDecimal;

public class NumberUtil {

    public static int parseInt(String value) {
        return parseInt(value, 0);
    }

    public static int parseInt(String value, int defValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static double parseLong(String value) {
        return parseLong(value, 0l);
    }

    public static long parseLong(String value, long defValue) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static double parseDouble(String value) {
        return parseDouble(value, 0d);
    }

    public static double parseDouble(String value, double defValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static double parseFloat(String value) {
        return parseFloat(value, 0f);
    }

    public static float parseFloat(String value, float defValue) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static double roundHalfUp(double value, int scale) {
        BigDecimal bg = new BigDecimal(value + "").setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bg.doubleValue();
    }

    public static float roundHalfUp(float value, int scale) {
        BigDecimal bg = new BigDecimal(value + "").setScale(scale, BigDecimal.ROUND_HALF_UP);
        return bg.floatValue();

    }
}
