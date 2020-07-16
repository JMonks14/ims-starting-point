package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class OrderLineTest {
	
	private OrderLine orderLine;
	private OrderLine other;
	private OrderLine other2;

	@Before
	public void setUp() {
		
		orderLine = new OrderLine("athing",1,1);
		other = new OrderLine(2,"anotherthing",2,2);
		other2 = new OrderLine(3,2,"morething", 2, 8);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(orderLine.getItemID());
		assertNotNull(other.getItemName());
		assertNotNull(other.getQuantity());
		assertNotNull(other2.getOrder_id());
		assertNotNull(other2.getCost());

		orderLine.setOrder_id(0);
		assertEquals(0,orderLine.getItemID(), 0);
		orderLine.setItemID(0);
		assertEquals(0,orderLine.getItemID(), 0);
		other.setQuantity((0));
		assertEquals(0, other.getQuantity(), 0);
		other.setItemName(null);
		assertNull(other.getItemName());
		orderLine.setCost(0);
		assertEquals(0, orderLine.getCost(), 0);

	}
	
	@Test
	public void toStringTest() {
		String toString = "Item ID: 2, Item name: morething, Quantity: 2, Total cost: 8.0";
		assertEquals(toString, other2.toString());
	}
//	
	@Test public void equalsTest() {
		OrderLine orderLine2=new OrderLine(2,"anotherthing",2,2);
		assertTrue(other.equals(orderLine2));
		assertFalse(other.equals(orderLine));
	}

}
