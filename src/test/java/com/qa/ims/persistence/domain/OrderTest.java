package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class OrderTest {
	
	private Order order;
	private Order other;
	private Order other2;
	private Order other3;
	private Order other1;
	@Before
	public void setUp() {
		order = new Order(1L);
		other = new Order(2L,2L);
		other2 = new Order(3L, 3L, 3);
		other3 = new Order(4L, 5L, "Some", "Guy", 75);
		other1 = new Order();
	}
	
	@Test
	public void settersTest() {
		assertNotNull(order.getID());
		assertNotNull(other.getCust_ID());
		assertNotNull(other2.getCost());
		assertNotNull(other3.getCustFirstName());
		assertNotNull(other3.getCustLastName());

		
		order.setID(0);
		assertEquals(0,order.getID(), 0);
		other3.setCust_ID(0);
		assertEquals(0, other3.getCust_ID(), 0);
		other3.setCustFirstName(null);
		assertNull(other3.getCustFirstName());
		other3.setCustLastName(null);
		assertNull(other3.getCustLastName());
		other3.setCost(0);
		assertEquals(0, other3.getCost(), 0);
				
	}
	
	@Test
	public void toStringTest() {
		String toString = "Order ID: 4, Customer Id: 5, Customer Name: Some Guy, Cost: 75.0";
		assertEquals(toString, other3.toString());
	}
//	
	@Test public void equalsTest() {
		Order order2=new Order(2L, 2L);
		assertTrue(other.equals(order2));
		assertFalse(other.equals(other3));
	}

}
