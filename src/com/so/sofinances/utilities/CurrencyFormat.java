package com.so.sofinances.utilities;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * A text formatter that takes numbers and converts them to local currency.
 *
 * @author  Joseph Rossi
 * @version 1.0 4/3/2014
 */
public class CurrencyFormat {

	public static String currencySymbol;
	
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
    	Currency c = Currency.getInstance(loc);
    	currencySymbol = c.getSymbol();
    }
}
