package com.tripadvisor.hotel.deals.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import com.tripadvisor.hotel.deals.service.DealsCLIService;

/**
 * Title: DealsCLI.java<br>
 * Description: CLI controller<br>
 * Created: 26-Jul-2017<br>
 * @author Teja Sasank Gorthi (gorthitk@gmail.com)
 */
@Component
public class DealsCLI implements CommandMarker {

	@Autowired
	DealsCLIService dealsService;

	@CliCommand(value = { "findDeal" }, help = "Searches for the best hotel deal")
	public String getBestHotelDeal(@CliOption(key = { "filePath" }, mandatory = true) String filePath,
			@CliOption(key = { "hotelName" }, mandatory = true) String hotelName,
			@CliOption(key = { "checkInDate" }, mandatory = true) String checkInDate,
			@CliOption(key = { "los" }, mandatory = true) String los) {
		return dealsService.findBestHotelDeal(filePath, hotelName, checkInDate, los);
	}

	@CliCommand(value = { "BestHotelDeal" }, help = "Searches for the best hotel deal")
	public String getBestHotelDeal(@CliOption(key = { "" }) String argument) {
		return dealsService.findBestHotelDeal(argument);
	}
	
	@CliCommand(value = { "reload" }, help = "Force reloads the data")
	public String reloadData(@CliOption(key = { "filePath" }, mandatory = true) String filePath) {
		return dealsService.reloadData(filePath);
	}
}
