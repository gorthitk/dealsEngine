package com.tripadvisor.hotel.deals.data;

import java.time.LocalDate;
import java.util.List;

import com.tripadvisor.hotel.deals.model.Deal;

/**
 * Title: DataFetcher.java<br>
 * Description: Interface that returns requested persisitent Data<br>
 * Created: 26-Jul-2017<br>
 * @author Teja Sasank Gorthi (gorthitk@gmail.com)
 */
public interface DataFetcher {

	/**
	 * Returns the list of available Deals for a given hotelName
	 * @param hotelName
	 * @return List
	 */
	public List<Deal> getAvailableDeals(String hotelName);

	
	/**
	 * Returns the list of available Deals for a given hotelName, LOS, CheckInDate
	 * @param hotelName
	 * @param lengthOfStay
	 * @param checkInDate
	 * @return List
	 */
	public List<Deal> getAvailableDeals(String hotelName, int lengthOfStay, LocalDate checkInDate);
}
