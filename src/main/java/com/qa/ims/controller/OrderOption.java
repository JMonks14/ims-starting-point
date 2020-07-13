package com.qa.ims.controller;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum OrderOption {

	CREATE("Add an item to an order"), DELETE("Delete an item form an order"), UPDATE("Change the quantity of an item in an order"), 
	READ("Display a list of all items in an order");
	
	public static final Logger LOGGER = Logger.getLogger(OrderOption.class);

	private String description;
	
	OrderOption(String description) {
		this.description = description;
	}

	/**
	 * Describes the action
	 */
	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	/**
	 * Prints out all possible actions
	 */
	public static void printOrderOptions() {
		for (OrderOption option : OrderOption.values()) {
			LOGGER.info(option.getDescription());
		}
	}

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 * 
	 * @return Action type
	 */
	public static OrderOption getOption() {
		OrderOption option;
		while (true) {
			try {
				option = OrderOption.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return option;
	}
}
