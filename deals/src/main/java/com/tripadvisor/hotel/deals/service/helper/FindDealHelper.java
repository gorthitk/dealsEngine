package com.tripadvisor.hotel.deals.service.helper;

import java.util.List;

import com.tripadvisor.hotel.deals.model.Deal;
import com.tripadvisor.hotel.deals.model.DealType;

/**
 * Title: DealsCLIService.java<br>
 * Description: Helper class to support the Service Class<br>
 * Created: 26-Jul-2017<br>
 * @author Teja Sasank Gorthi (gorthitk@gmail.com)
 */
public class FindDealHelper {


	/**
	 * Returns the best deal for a given hotel by calculating the minimum final price to be paid.
	 * @param deals Deal
	 * @param lengthOfStay lengthOfStay
	 * @return
	 */
	public static String findBestDeal(List<Deal> deals, int lengthOfStay) {
		double finalValue = Double.MAX_VALUE;
		Deal bestDeal = null;
		for (Deal deal : deals) {
			double rate = deal.getNightlyRate()*lengthOfStay;
			if (deal.getDealType().equals(DealType.PCT)) {
				rate = rate * (1+(deal.getDealValue()/100));
			} else {
				rate = rate + deal.getDealValue();
			}
			if (rate < finalValue) {
				finalValue = rate;
				bestDeal = deal;
			}
		}
		return bestDeal.getPromoDescription();
	}
}
