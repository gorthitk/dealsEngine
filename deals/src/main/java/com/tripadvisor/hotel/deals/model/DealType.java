package com.tripadvisor.hotel.deals.model;

/**
 * Title: DealType.java<br>
 * Description: Enum Class to Store Deal Types<br>
 * Created: 26-Jul-2017<br>
 * 
 * @author Teja Sasank Gorthi (gorthitk@gmail.com)
 */
public enum DealType {
	REBATE("rebate", 1), REBATE_3PLUS("rebate_3plus", 2), PCT("pct", 3);

	private final String dealTypeName;
	private final int dealTypeId;

	private DealType(String dealTypeName, int dealTypeId) {
		this.dealTypeName = dealTypeName;
		this.dealTypeId = dealTypeId;
	}

	public String getDealTypeName() {
		return dealTypeName;
	}

	public int getDealTypeId() {
		return dealTypeId;
	}

	
	/**
	 * Returns the Enum DealType based on the dealType String 
	 * @param dealType
	 * @return
	 */
	public static DealType getDealType(String dealType) {
		if (dealType.equals(DealType.REBATE.getDealTypeName())) {
			return DealType.REBATE;
		} else if (dealType.equals(DealType.REBATE_3PLUS.getDealTypeName())) {
			return DealType.REBATE_3PLUS;
		} else if (dealType.equals(DealType.PCT.getDealTypeName())) {
			return DealType.PCT;
		}
		return null;
	}
}
