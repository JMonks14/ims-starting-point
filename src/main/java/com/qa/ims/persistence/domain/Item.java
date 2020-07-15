package com.qa.ims.persistence.domain;

public class Item {
	
	private long item_ID;
	private String item_name;
	private double item_price;
	private int quant_in_stock;
	
	
	public Item(String item_name, double item_price, int quant_in_stock) {
		super();
		this.item_name = item_name;
		this.item_price = item_price;
		this.quant_in_stock = quant_in_stock;
	}

	public Item(long item_ID, String item_name, double item_price, int quant_in_stock) {
		super();
		this.item_ID = item_ID;
		this.item_name = item_name;
		this.item_price = item_price;
		this.quant_in_stock = quant_in_stock;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getItem_price() {
		return item_price;
	}

	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}

	public int getQuant_in_stock() {
		return quant_in_stock;
	}

	public void setQuant_in_stock(int quant_in_stock) {
		this.quant_in_stock = quant_in_stock;
	}

	public long getItem_ID() {
		return item_ID;
	}
	
	public String toString() {
		return "ID:" + this.getItem_ID() + ", Name:" + this.getItem_name() + ", Price:" + this.getItem_price() + ", Quantity in stock:" + this.getQuant_in_stock(); 
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item item = (Item) obj;
		if (this.item_ID!=item.getItem_ID() || this.getItem_name()!= item.getItem_name() || this.getItem_price()!= item.getItem_price() || this.getQuant_in_stock()!=item.getQuant_in_stock()) {
			return false;
		} else {
			return true;
		}
		
	}

}
