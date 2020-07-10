package com.qa.ims.persistence.domain;
import java.util.HashMap;
import java.util.Map;

public class Order {
	
	private long ID;
	private long cust_ID;
	private double cost;
	private Map<Long, Integer> ordercontents = new HashMap<>();
	
	public Order(long cust_ID) {
		super();
		this.cust_ID = cust_ID;
	}

	public Order(long iD, long cust_ID, double cost) {
		super();
		ID = iD;
		this.cust_ID = cust_ID;
		this.cost = cost;
	}
	
	public Order(long iD, long cust_ID) {
		super();
		ID = iD;
		this.cust_ID = cust_ID;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getCust_ID() {
		return cust_ID;
	}

	public void setCust_ID(long cust_ID) {
		this.cust_ID = cust_ID;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void addItem(long iD, int quantity) {
		ordercontents.put(iD, quantity);
	}
	
	

}
