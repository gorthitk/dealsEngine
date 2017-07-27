package com.tripadvisor.hotel.deals.data;

import java.util.List;
import java.util.Map;

import com.tripadvisor.hotel.deals.model.Deal;

/**
 * Title: DataLoader.java<br>
 * Description: Interface that returns to load raw data<br>
 * Created: 26-Jul-2017<br>
 * @author Teja Sasank Gorthi (gorthitk@gmail.com)
 */
public interface DataLoader {

	
	/**
	 * Method to load the information to memory/persistent DB.
	 */
	public boolean loadData();
	
	
	/**
	 * Returns a Map (hotelName, list of available deals)
	 * @return Map
	 */
	public Map<String, List<Deal>> getAvailableDeals();
	
	/**
	 * Used when using flat files, XML, CSV files.
	 * Sets the path to the source file.
	 * @param filePath
	 * @return boolean (returns true if new source is configured)
	 */
	public boolean setFilePath(String filePath);
}
