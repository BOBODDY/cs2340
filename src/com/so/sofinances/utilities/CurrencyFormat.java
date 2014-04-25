package com.so.sofinances.utilities;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import com.so.sofinances.controllers.UserHandler;

/**
 * A text formatter that takes numbers and converts them to local currency.
 *
 * @author  Joseph Rossi
 * @version 1.0 4/3/2014
 */
public class CurrencyFormat {

	public static String currencySymbol;
	
	private static final double USD_USD = 1;
	private static final double GBP_USD = 0.6;
	private static final double EURO_USD = 0.72;
	
	private static final List<Double> D = new ArrayList<Double>(Arrays.asList(USD_USD, GBP_USD, EURO_USD));
	public static final List<Locale> L = new ArrayList<Locale>(Arrays.asList(Locale.US, Locale.UK, Locale.FRANCE));
	public static final int US = 0;
	public static final int GBP = 1;
	public static final int EU = 2;
	
    /**
     * number format for US currency.
     */
    private static NumberFormat fmt = NumberFormat.getCurrencyInstance(UserHandler.getCurrentLocale());

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
    	Locale currentLocale = UserHandler.getCurrentLocale();
    	double currentRate = D.get(L.indexOf(currentLocale));
    	double newRate = D.get(L.indexOf(loc));
    	double exchangeRate = newRate / currentRate;
    	UserHandler.adjustAmounts(exchangeRate, loc);
    	currencySymbol = c.getSymbol();
    	currentLocale = loc;
    }
}
