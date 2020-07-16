package com.qa.ims.persistence.domain;

public class OrderLine {
	private long itemID;
	private long order_id;
	private String itemName;
	private int quantity;
	private double cost;
	
	public OrderLine(long order_id, long itemID, String itemName, int quantity, double cost) {
		super();
		this.order_id = order_id;
		this.itemID=itemID;
		this.itemName = itemName;
		this.quantity = quantity;
		this.cost = cost;
	}
	
	public OrderLine(long itemID, String itemName, int quantity, double cost) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.quantity = quantity;
		this.cost = cost;
	}

	public OrderLine(String itemName, int quantity, double cost) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
		this.cost = cost;
	}



	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public long getItemID() {
		return itemID;
	}



	public void setItemID(long itemID) {
		this.itemID = itemID;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine itemLine = (OrderLine) obj;
		if (!(this.getItemID()==itemLine.getItemID() && this.getQuantity() == itemLine.getQuantity() && this.getCost() == this.getCost())) {
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public String toString() {
		return "Item ID: " + this.getItemID() + ", Item name: " + this.getItemName() + 
				", Quantity: " + this.getQuantity() + ", Total cost: " + this.getCost();
	}

}
