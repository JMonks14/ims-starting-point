package com.qa.ims.persistence.domain;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

public enum Continuer {
	
	YES("Return to the first menu"), NO("Stop the application");
	
	private String description;
	
	public static final Logger LOGGER = Logger.getLogger(Domain.class);

	Continuer(String description) {
		this.description=description;
	}

	public String getDescription() {
		return this.name() + ": " + this.description;
	}
	
	public static void print() {
		for (Continuer option : Continuer.values()) {
			LOGGER.info(option.getDescription());
		}
	}
	
	public static Continuer getOption() {
		Continuer option;
		while (true) {
			try {
				option = Continuer.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return option;
	}

}
