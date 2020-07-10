package com.qa.ims.utils;

import java.util.Scanner;

public class Utils {

	public static final String MYSQL_URL = "34.105.244.227:3306";
	public static final Scanner SCANNER = new Scanner(System.in);

	private Utils() {

	}

	public static String getInput() {
		return SCANNER.nextLine();
	}

}
