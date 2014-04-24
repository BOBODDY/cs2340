package com.so.sofinances.model;

/**
 * Interface for constructing lists with main titles and subtitles
 * 
 * @author Joseph Rossi
 * @version	1.0 4/23/2014
 */
public interface Listable {

	/**
	 * @return	the main title
	 */
	public String getMainTitle();
	
	/**
	 * @return	the subtitle
	 */
	public String getSubTitle();
}
