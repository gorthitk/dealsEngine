package com.tripadvisor.hotel.deals.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tripadvisor.hotel.deals.data.DataFetcher;
import com.tripadvisor.hotel.deals.data.DataLoader;
import com.tripadvisor.hotel.deals.model.Deal;
import com.tripadvisor.hotel.deals.service.helper.FindDealHelper;

/**
 * Title: DealsCLIService.java<br>
 * Description: Service Class to handle all CLI based Requests<br>
 * Created: 26-Jul-2017<br>
 * 
 * @author Teja Sasank Gorthi (gorthitk@gmail.com)
 */

@Component
public class DealsCLIService {
	private static final String INVALID_ARGUMENTS = "Invalid Input";
	private static final String ERROR_LOADING = "Error Loading data from the file, Please check the file path";
	private static final String NO_DEAL_AVAILABLE = "no deal available";
	@Autowired
	DataLoader dataloader;
	@Autowired
	DataFetcher dataFetcher;

	/**
	 * Returns the Best Hotel Deal for a given criteria String
	 * 
	 * @param arguments
	 * @return
	 */
	public String findBestHotelDeal(String arguments) {
		if (arguments == null || arguments.isEmpty())
			return INVALID_ARGUMENTS;
		String[] parameters = arguments.split("\\s+");
		if (parameters.length < 4)
			return INVALID_ARGUMENTS;

		// Not the smartest code, but a work around as Spring Shell currently
		// doesn't support positional arguments.
		int hotelNameStartIdx = arguments.indexOf("\"");
		int hotelNameEndIdx = arguments.lastIndexOf("\"");
		if (hotelNameStartIdx == -1 || hotelNameEndIdx == -1 || hotelNameEndIdx == hotelNameStartIdx)
			return INVALID_ARGUMENTS + " - Invalid Hotel Name";

		return findBestHotelDeal(parameters[0], arguments.substring(hotelNameStartIdx+1, hotelNameEndIdx),
				parameters[parameters.length - 2], parameters[parameters.length - 1]);
	}

	/**
	 * Returns the Best Hotel Deal for a given criteria
	 * 
	 * @param filePath
	 * @param hotelName
	 * @param checkInDate
	 * @param los
	 * @return
	 */
	public String findBestHotelDeal(String filePath, String hotelName, String checkInDate, String los) {
		String error = loadDataFromFile(filePath, false);

		if (error != null) {
			return error;
		}
		int lengthOfStay;
		LocalDate checkInDt;

		try {
			lengthOfStay = Integer.parseInt(los);
		} catch (Exception exp) {
			return INVALID_ARGUMENTS + " - Invalid Length of Stay";
		}

		try {
			checkInDt = LocalDate.parse(checkInDate);
		} catch (Exception exp) {
			return INVALID_ARGUMENTS + " - Invalid Check In Date";
		}

		List<Deal> deals = dataFetcher.getAvailableDeals(hotelName, lengthOfStay, checkInDt);

		return deals == null || deals.isEmpty() ? NO_DEAL_AVAILABLE : FindDealHelper.findBestDeal(deals, lengthOfStay);
	}

	/**
	 * Checks if the file path provided is new. If new, it refreshed the memory
	 * with data from the new CSV files. Synchronized to avoid multiple loading
	 * of CSV files or to avoid serving data from wrong files.
	 * 
	 * @param filePath
	 */
	private synchronized String loadDataFromFile(String filePath, boolean forceRefresh) {
		if (dataloader.setFilePath(filePath) || forceRefresh) {
			if (!dataloader.loadData()) {
				return ERROR_LOADING;
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public DataLoader getDataloader() {
		return dataloader;
	}

	/**
	 * @param dataloader
	 */
	public void setDataloader(DataLoader dataloader) {
		this.dataloader = dataloader;
	}

	/**
	 * @return
	 */
	public DataFetcher getDataFetcher() {
		return dataFetcher;
	}

	/**
	 * @param dataFetcher
	 */
	public void setDataFetcher(DataFetcher dataFetcher) {
		this.dataFetcher = dataFetcher;
	}

	/**
	 * Method to reload the data in-memory
	 * @param filePath
	 * @return
	 */
	public String reloadData(String filePath) {
		String error = loadDataFromFile(filePath, true);
		return error == null ? "Data has been refreshed" : error;
	}

}
