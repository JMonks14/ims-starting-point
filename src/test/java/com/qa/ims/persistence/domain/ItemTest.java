package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class ItemTest {
	
	private Item item;
	private Item other;
	
	@Before
	public void setUp() {
		item = new Item(1L, "Magic rock", 10.00, 10);
		other = new Item("Magic rock", 10.00, 10);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(item.getItem_ID());
		assertNotNull(item.getItem_name());
		assertNotNull(item.getItem_price());
		assertNotNull(item.getQuant_in_stock());
		
		item.setItem_name(null);
		assertNull(item.getItem_name());
		item.setItem_price((5));
		assertEquals("Expected: price=5", 5, item.getItem_price(), 0);
		item.setQuant_in_stock(8);
		assertEquals(8, item.getQuant_in_stock(), 0);
				
	}
	
	@Test
	public void toStringTest() {
		String toString = "ID:1, Name:Magic rock, Price:10.0, Quantity in stock:10";
		assertEquals(toString, item.toString());
	}
	
	@Test public void equalsTest() {
		Item item2 = new Item(1L, "Magic rock", 10.00, 10);
		assertTrue(item.equals(item2));
		assertFalse(other.equals(item2));
	}


}
