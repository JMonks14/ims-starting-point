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
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please a customer ID");
		long cust_iD = Utils.getValidId();	
		Order order = orderService.create(new Order(cust_iD));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please a the ID of the order you would like to update the customer for");
		long Id = Utils.getValidId();
		LOGGER.info("Please a customer ID");
		long custID = Long.parseLong(getInput());
		Order order = orderService.update(new Order(Id, custID));
		LOGGER.info("Order Updated");
		return order;
	}


	/**
	 * Deletes an existing customer by the id of the customer
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Utils.getValidId();
		orderService.delete(id);
		LOGGER.info("Order deleted");
	}

}
