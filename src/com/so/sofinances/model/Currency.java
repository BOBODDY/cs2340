package com.so.sofinances.model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * A text formatter that takes numbers and converts them to local currency.
 *
 * @author  Joseph Rossi
 * @version 1.0 4/3/2014
 */
public class Currency {

    /**
     * number format for US currency.
     */
    private static NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * Converts a decimal amount to a currency String (ie $xx.xx).
     *
     * @param value the amount to be formatted
     * @return  the amount formatted as currency
     */
    public static String format(double value) {
    	return fmt.format(value);
    }

    /**
     * Changes from one currency formatting to another (ie USD to Euro).
     *
     * @param loc a new Locale from which we create a new NumberFormat
     */
    public static void changeLocale(Locale loc) {
    	fmt = NumberFormat.getCurrencyInstance(loc);
    }
}
