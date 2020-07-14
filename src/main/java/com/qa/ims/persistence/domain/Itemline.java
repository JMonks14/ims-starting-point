package com.qa.ims.persistence.domain;

public class Itemline {
	
	private long ID;
	private long itemID;
	private int quantity;
	
	
	public Itemline(long ID, long itemID, int quantity) {
		this.ID=ID;
		this.itemID = itemID;
		this.quantity = quantity;
	}
	
	


	public Itemline(long ID, long itemID) {
		this.ID=ID;
		this.itemID=itemID;
	}


	public long getID() {
		return ID;
	}


	public void setID(long iD) {
		ID = iD;
	}


	public long getItemID() {
		return itemID;
	}


	public void setItemID(long itemID) {
		this.itemID = itemID;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}