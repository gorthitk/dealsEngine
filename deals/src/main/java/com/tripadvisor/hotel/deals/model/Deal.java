package com.tripadvisor.hotel.deals.model;

import java.time.LocalDate;

/**
 * Title: Deal.java<br>
 * Description: Simple POJO class to hold required Deal Information<br>
 * Created: 26-Jul-2017<br>
 * @author Teja Sasank Gorthi (gorthitk@gmail.com)
 */
/**
 * @author jet
 *
 */
public class Deal {

	private DealType dealType;
	private double nightlyRate;
	private String promoDescription;
	private double dealValue;
	private LocalDate startDate;
	private LocalDate endDate;

	
	/**
	 * @param nightlyRate
	 * @param promoDescription
	 * @param dealValue
	 * @param startDate
	 * @param endDate
	 * @param dealType
	 */
	public Deal(double nightlyRate, String promoDescription, double dealValue, LocalDate startDate,
			LocalDate endDate, DealType dealType) {
		super();
		this.nightlyRate = nightlyRate;
		this.promoDescription = promoDescription;
		this.dealValue = dealValue;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dealType = dealType;
	}

	/**
	 * @return
	 */
	public DealType getDealType() {
		return dealType;
	}

	/**
	 * @param dealType
	 */
	public void setDealType(DealType dealType) {
		this.dealType = dealType;
	}

	/**
	 * @return
	 */
	public double getNightlyRate() {
		return nightlyRate;
	}

	/**
	 * @param nightlyRate
	 */
	public void setNightlyRate(double nightlyRate) {
		this.nightlyRate = nightlyRate;
	}

	/**
	 * @return
	 */
	public String getPromoDescription() {
		return promoDescription;
	}

	/**
	 * @param promoDescription
	 */
	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	/**
	 * @return
	 */
	public double getDealValue() {
		return dealValue;
	}

	/**
	 * @param dealValue
	 */
	public void setDealValue(double dealValue) {
		this.dealValue = dealValue;
	}

	/**
	 * @return
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Checks if the Deal is application based on the Length of Stay and Check-In Date.
	 * Verifies if the checkInDate falls between the start and end date inclusive.
	 * 
	 * @param checkInDate LocalDate
	 * @param los int
	 * @return boolean returns true if the deal can be applied.
	 */
	public boolean isDealApplicable(LocalDate checkInDate, int los) {
		boolean dateInRange = (checkInDate.isAfter(startDate) || startDate.isEqual(checkInDate)) && (endDate.isEqual(checkInDate) || endDate.isAfter(checkInDate));
		return dateInRange && (dealType.equals(DealType.REBATE_3PLUS) ? los >=3 : true);
	}
}
