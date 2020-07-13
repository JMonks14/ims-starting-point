package com.qa.ims.persistence.domain;

public class Order {
	
	private long ID;
	private long cust_ID;
	private double cost;
	
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

	public Order() {
		// TODO Auto-generated constructor stub
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
	
	@Override
	public String toString() {
		return "Order ID: " + this.getID() + ", Customer Id: " + this.getCust_ID() + ", Cost: " + this.getCost();
	}
	

}
