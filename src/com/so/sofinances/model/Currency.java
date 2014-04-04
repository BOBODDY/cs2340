package com.so.sofinances.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Currency {

    private static NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
    
    public static String format(double value) {
        return fmt.format(value);
    }
    public static void changeLocale(Locale loc) {
        fmt = NumberFormat.getCurrencyInstance(loc);
    }
}
