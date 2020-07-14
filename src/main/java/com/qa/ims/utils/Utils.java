package com.qa.ims.utils;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Utils {

	public static final String MYSQL_URL = "34.105.244.227:3306";
	public static final Scanner SCANNER = new Scanner(System.in);
	public static final Logger LOGGER = Logger.getLogger(Utils.class);

	private Utils() {

	}

	public static String getInput() {
		return SCANNER.nextLine();
	}
	
	public static long getValidId() {
		long id =0;
		while (id <= 0) {
			id = Long.parseLong(getInput());
			if (id <=0)
				LOGGER.info("Invalid input, please choose a number greater than 0");
		} 
		return id;
	}

}
