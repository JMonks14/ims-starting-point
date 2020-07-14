package com.qa.ims.persistence.domain;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum Continue {
	
	YES("Return to the first menu"), NO("Stop the application");
	
	private String description;
	
	public static final Logger LOGGER = Logger.getLogger(Domain.class);

	Continue(String description) {
		this.description=description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}
	
	public static void print() {
		for (Continue option : Continue.values()) {
			LOGGER.info(option.getDescription());
		}
	}
	
	public static Continue getOption() {
		Continue option;
		while (true) {
			try {
				option = Continue.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return option;
	}

}
