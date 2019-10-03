package com.nfc.resturant.util;

import java.util.UUID;

/**
 * @author Gaurav Oli
 * @Update 11th May
 */
public abstract class NFCUtil {

	/**
	 * This method is used to generate new OrderId
	 */
	public static String generateOrderID() {
		return UUID.randomUUID().toString();
	}

}
