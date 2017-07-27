package com.tripadvisor.hotel.deals.data.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tripadvisor.hotel.deals.data.DataLoader;
import com.tripadvisor.hotel.deals.model.Deal;
import com.tripadvisor.hotel.deals.model.DealType;

/**
 * Title: CSVDataLoader.java<br>
 * Description: Parses CSV files and loads the required information
 * in-memory<br>
 * Created: 26-Jul-2017<br>
 * 
 * @author Teja Sasank Gorthi (gorthitk@gmail.com)
 */
@Component
public class CSVDataLoader implements DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String CURRENT_FILE_PATH;
	private Map<String, List<Deal>> availableDeals;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tripadvisor.hotel.deals.data.DataLoader#loadData()
	 */
	@Override
	public boolean loadData() {
		availableDeals = new HashMap<>();
		try (Stream<String> stream = Files.lines(Paths.get(CURRENT_FILE_PATH))) {
			stream.skip(1).parallel().forEach(line -> parseToDeal(line.split(",")));
			return true;
		} catch (IOException e) {
			CURRENT_FILE_PATH = null;
			logger.trace("Unable to load data, error : {}", e.getMessage());
		}
		return false;
	}

	/**
	 * @param split
	 *            Helper method to parse each row of the CSV to defined Deal
	 *            POJO
	 */
	private void parseToDeal(String[] split) {
		if (split.length != 7) {
			logger.trace("Invalid row for information : {}",
					Arrays.asList(split).parallelStream().collect(Collectors.joining(",")));
			return;
		}
		try {
			String hotelName = split[0].trim();
			double nightlyRate = Double.parseDouble(split[1].trim());
			String promoDescription = split[2].trim();
			double dealValue = Double.parseDouble(split[3].trim());
			DealType dealType = DealType.getDealType(split[4].trim());
			LocalDate startDate = LocalDate.parse(split[5].trim());
			LocalDate endDate = LocalDate.parse(split[6].trim());

			if (dealType == null) {
				logger.trace("Invalid Deal Type information : {}",
						Arrays.asList(split).parallelStream().collect(Collectors.joining(",")));
				return;
			}
			Deal deal = new Deal(nightlyRate, promoDescription, dealValue, startDate, endDate, dealType);

			availableDeals.putIfAbsent(hotelName, new ArrayList<>());
			availableDeals.get(hotelName).add(deal);
		} catch (Exception exp) {
			logger.trace("Error parsing information from row : {}",
					Arrays.asList(split).parallelStream().collect(Collectors.joining(",")));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tripadvisor.hotel.deals.data.DataLoader#setFilePath(java.lang.String)
	 */
	public boolean setFilePath(String filePath) {
		if (CURRENT_FILE_PATH == null || !CURRENT_FILE_PATH.equals(filePath)) {
			CURRENT_FILE_PATH = filePath;
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tripadvisor.hotel.deals.data.DataLoader#getAvailableDeals()
	 */
	public Map<String, List<Deal>> getAvailableDeals() {
		return availableDeals;
	}
}
