/*
 * This code contains copyright information which is the proprietary property of Tarang Software
 * Technologies Pvt Ltd . No part of this code may be reproduced, stored or transmitted in any form
 * without the prior written permission.
 * 
 * Copyright (C) Tarang Software Technologies Pvt Ltd 2012. All rights reserved.
 * ------------------------------------------------------------------------------ Version : 1.0
 * Created on : 01 August 2012 Author : Saravanan P Description : This Class will load all the
 * Properties file. ------------------------------------------------------------------------------
 * Change History ------------------------------------------------------------------------------
 * 
 * ------------------------------------------------------------------------------
 */
package com.nfc.resturant.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import com.nfc.resturant.constants.Constants;

/**
 * This Class will load all the Properties file.include property to be loaded as
 * private field and create method to get key
 */
public class PropertiesUtil {

	private static Properties socialProps;
	private static Properties payProps;
	private static ResourceBundle resourceBundle=ResourceBundle.getBundle(Constants.MESSAGE_FILE);

	/**
	 * Load all project properties.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void loadAllProjectProperties() throws IOException {

		if (payProps == null) {
			InputStream payment = PropertiesUtil.class.getClassLoader()
					.getResourceAsStream("payment.properties");
			payProps = new Properties();
			payProps.load(payment);
		}

		if (socialProps == null) {
			InputStream payment = PropertiesUtil.class.getClassLoader()
					.getResourceAsStream("social.properties");
			socialProps = new Properties();
			socialProps.load(payment);
		}

	}

	/**
	 * Gets the controller error code.
	 * 
	 * @param key
	 *            the key
	 * @return the controller error code
	 * @throws IOException
	 */
	public static String getPaymentProp(String key) throws IOException {
		if (payProps == null)
			loadAllProjectProperties();
		return payProps.getProperty(key);
	}

	public static String getSocialProp(String key) throws IOException {
		if (socialProps == null)
			loadAllProjectProperties();
		return socialProps.getProperty(key);
	}
	
	public static String getMessageProperty(String key){
		return resourceBundle.getString(key);
	}

}
