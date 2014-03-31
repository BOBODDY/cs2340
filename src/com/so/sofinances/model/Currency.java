package com.so.sofinances.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Currency {

	private static NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.US);
	
	public static String format(double d) {
		return fmt.format(d);
	}
	public static void changeLocale(Locale l) {
		fmt = NumberFormat.getCurrencyInstance(l);
	}
}
