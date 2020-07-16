package com.qa.ims.controller;
import com.qa.ims.utils.Utils;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Itemline;
import com.qa.ims.persistence.domain.OrderLine;

public class OrderUpdateController {
	
	public static final Logger LOGGER = Logger.getLogger(OrderUpdateController.class);
	private OrderDaoMysql updateServices;
	
	public OrderUpdateController(OrderDaoMysql updateService) {
		this.updateServices = updateService;
	}
	
	String getInput() {
		return Utils.getInput();
	}
	
	long getValidId() {
		return Utils.getValidId();
	}
	
	long getLong() {
		return Long.parseLong(getInput());
	}
	
	int getInt() {
		return Integer.parseInt(getInput());
	}
	
	public Itemline add() {
		LOGGER.info("Please enter an order ID");
		long order_iD = getValidId();
		LOGGER.info("Please enter an item ID");
		long item_id = getValidId();
		LOGGER.info("Please enter the quantity to add to the order");
		int quantity = getInt();
		Itemline itemLine= new Itemline(order_iD, item_id, quantity);
		Itemline itemLineV = updateServices.checkQuant(itemLine); 
		Itemline line=updateServices.addItem(itemLineV);
	    return line;
	}
	
	public List<OrderLine> readAll() {
		LOGGER.info("Please enter an order ID");
		long order_iD = getLong();
		List <OrderLine> order = updateServices.readAllitems(order_iD);
		for (OrderLine line: order) {
			LOGGER.info(line.toString());
		}
	return order;
	}
	public void del() {
		LOGGER.info("Please enter an order ID");
		long order_iD = getLong();
		LOGGER.info("Please enter an item ID");
		long item_id = getLong();
		Itemline itemLine = new Itemline(order_iD, item_id);
		updateServices.delItem(itemLine);
		}
	
	public Itemline change() {
		
		LOGGER.info("Please enter an order ID");
		long order_iD = getValidId();
		LOGGER.info("Please enter an item ID");
		long item_id = getValidId();
		LOGGER.info("Please enter the new item quantity for the order");
		int quantity = getInt();
		Itemline itemLine= new Itemline(order_iD, item_id, quantity);
		updateServices.changeQuant(itemLine);
		return itemLine;
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
