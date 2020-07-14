package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item>{
	
public static final Logger LOGGER = Logger.getLogger(ItemController.class);
	
	private CrudServices<Item> itemService;
	
	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	
	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemService.readAll();
		for(Item item: items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter an item name");
		String name = getInput();
		LOGGER.info("Please enter a price");
		double price = Double.parseDouble(getInput());
		LOGGER.info("Please enter a stock quantity");
		int quant_in_stock = Integer.parseInt(getInput());		
		Item item = itemService.create(new Item(name, price, quant_in_stock));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = Utils.getValidId();
		LOGGER.info("Please enter an item name");
		String name = getInput();
		LOGGER.info("Please enter a price");
		double price = Double.parseDouble(getInput());
		LOGGER.info("Please enter a stock quantity");
		int quant_in_stock = Integer.parseInt(getInput());		
		Item item = itemService.update(new Item(id, name, price, quant_in_stock));
		LOGGER.info("Item Updated");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = Utils.getValidId();
		itemService.delete(id);
		LOGGER.info("Item deleted");
	}

}
