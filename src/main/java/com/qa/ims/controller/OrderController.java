package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{
	
public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	private CrudServices<Order> orderService;
	
	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}
	
	String getInput() {
		return Utils.getInput();
	}
	
	long getValidId () {
		return Utils.getValidId();
	}
	
	double getDouble() {
		return Double.parseDouble(getInput());
	}
	
	long getLong() {
		return Long.parseLong(getInput());
	}
	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for(Order order: orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please a customer ID");
		long cust_iD = getValidId();	
		Order order = orderService.create(new Order(cust_iD));
		return order;
	}

	/**
	 * Updates an order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please a the ID of the order you would like to update the customer for");
		long Id = getValidId();
		LOGGER.info("Please a customer ID");
		long custID = getLong();
		Order order = orderService.update(new Order(Id, custID));
		return order;
	}
	/**
	 * Deletes an existing customer by the id of the customer
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = getValidId();
		orderService.delete(id);
	}

}
