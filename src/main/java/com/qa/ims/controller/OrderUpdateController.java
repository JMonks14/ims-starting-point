package com.qa.ims.controller;
import com.qa.ims.utils.Utils;
import org.apache.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Itemline;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderUpdateServices;

public class OrderUpdateController {
	
	public static final Logger LOGGER = Logger.getLogger(OrderUpdateController.class);
	private OrderDaoMysql updateServices;
	
	public OrderUpdateController(OrderDaoMysql updateService) {
		this.updateServices = updateService;
	}
	
	String getInput() {
		return Utils.getInput();
	}
	
	public void add() {
		LOGGER.info("Please enter an order ID");
		long order_iD = Long.parseLong(getInput());
		LOGGER.info("Please enter an item ID");
		long item_id = Long.parseLong(getInput());
		LOGGER.info("Please enter the quantity to add to the order");
		int quantity = Integer.parseInt(getInput());
		Itemline itemLine= new Itemline(order_iD, item_id, quantity);
		updateServices.addItem(itemLine);
		LOGGER.info("Item added to order");
	}
	
	public void del() {
		LOGGER.info("Please enter an order ID");
		long order_iD = Long.parseLong(getInput());
		LOGGER.info("Please enter an item ID");
		long item_id = Long.parseLong(getInput());
		Itemline itemLine = new Itemline(order_iD, item_id);
		updateServices.delItem(itemLine);
		LOGGER.info("Item removed from order");
	}
	
	public void change() {
		
		LOGGER.info("Please enter an order ID");
		long order_iD = Long.parseLong(getInput());
		LOGGER.info("Please enter an item ID");
		long item_id = Long.parseLong(getInput());
		LOGGER.info("Please enter the new item quanity for the order");
		int quantity = Integer.parseInt(getInput());
		Itemline itemLine= new Itemline(order_iD, item_id, quantity);
		updateServices.changeQuant(itemLine);
		LOGGER.info("Quantity updated");
	}
	
//	public Order newCust() {
//		LOGGER.info("Please enter an order ID");
//		long order_iD = Long.parseLong(getInput());
//		LOGGER.info("Please enter an customer ID");
//		long cust_id = Long.parseLong(getInput());
//		Order order = new Order(order_iD, cust_id);		
//		return order;
//	}

}
