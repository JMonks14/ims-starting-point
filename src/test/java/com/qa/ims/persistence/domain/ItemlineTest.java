package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemlineTest {
	
	private Itemline itemLine;
	private Itemline other;
	
	@Before
	public void setUp() {
		
		itemLine = new Itemline(1,1);
		other = new Itemline(2,2,2);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(itemLine.getID());
		assertNotNull(other.getItemID());
		assertNotNull(other.getQuantity());

		
		itemLine.setID(0);
		assertEquals(0,itemLine.getID(), 0);
		itemLine.setItemID(0);
		assertEquals(0,itemLine.getItemID(), 0);
		other.setQuantity((0));
		assertEquals(0, other.getQuantity(), 0);
				
	}
	
//	@Test
//	public void toStringTest() {
//		String toString = "Itemline ID: 4, Customer Id: 5, Customer Name: Some Guy, Cost: 75.0";
//		assertEquals(toString, other3.toString());
//	}
//	
	@Test public void equalsTest() {
		Itemline itemLine2=new Itemline(2L, 2L, 2);
		assertTrue(other.equals(itemLine2));
		assertFalse(other.equals(itemLine));
	}

}
