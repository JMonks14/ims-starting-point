package com.qa.ims.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrderOptionTest {
	
	@Test
	public void createTest() {
		OrderOption option = OrderOption.CREATE;
		assertTrue(option.getDescription().toLowerCase().contains("item"));
	}
	
	@Test
	public void itemTest() {
		OrderOption option = OrderOption.READ;
		assertTrue(option.getDescription().toLowerCase().contains("list"));
	}
	
	@Test
	public void orderTest() {
		OrderOption option = OrderOption.UPDATE;
		assertTrue(option.getDescription().toLowerCase().contains("change"));
	}
	
	@Test
	public void stopTest() {
		OrderOption option = OrderOption.DELETE;
		assertTrue(option.getDescription().toLowerCase().contains("delete"));
	}


}
