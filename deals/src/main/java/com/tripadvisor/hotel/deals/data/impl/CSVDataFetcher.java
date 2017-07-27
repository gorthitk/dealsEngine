package com.tripadvisor.hotel.deals.data.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripadvisor.hotel.deals.data.DataFetcher;
import com.tripadvisor.hotel.deals.data.DataLoader;
import com.tripadvisor.hotel.deals.model.Deal;

/**
 * Title: CSVDataFetcher.java<br>
 * Description: Returns information stored in-memory<br>
 * Created: 26-Jul-2017<br>
 * @author Teja Sasank Gorthi (gorthitk@gmail.com)
 */
@Component
public class CSVDataFetcher implements DataFetcher {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataLoader dataLoader;

	/* (non-Javadoc)
	 * @see com.tripadvisor.hotel.deals.data.DataFetcher#getAvailableDeals(java.lang.String)
	 */
	@Override
	public List<Deal> getAvailableDeals(String hotelName) {
		Map<String, List<Deal>> availableDeals = dataLoader.getAvailableDeals();
		return availableDeals.containsKey(hotelName) ? availableDeals.get(hotelName) : new ArrayList<>();
	}

	/* (non-Javadoc)
	 * @see com.tripadvisor.hotel.deals.data.DataFetcher#getAvailableDeals(java.lang.String, int, java.time.LocalDate)
	 */
	@Override
	public List<Deal> getAvailableDeals(String hotelName, int lengthOfStay, LocalDate checkInDate) {
		List<Deal> availableDeals = getAvailableDeals(hotelName);
		if (!availableDeals.isEmpty()) {
			logger.info("Collecting available deals for hotelName : {}, Length of Stay : {}, Check In Date : {}", hotelName, lengthOfStay, checkInDate);
			return availableDeals.parallelStream().filter(s -> s.isDealApplicable(checkInDate, lengthOfStay))
					.collect(Collectors.toList());
		}
		logger.trace("No deals available for hotelName : {}, Length of Stay : {}, Check In Date : {}", hotelName, lengthOfStay, checkInDate);
		return null;
	}

	/**
	 * @return
	 */
	public DataLoader getDataLoader() {
		return dataLoader;
	}

	/**
	 * @param dataLoader
	 */
	public void setDataLoader(DataLoader dataLoader) {
		this.dataLoader = dataLoader;
	}

}
