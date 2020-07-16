package com.qa.ims.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class ActionTest {
	
	@Test
	public void createTest() {
		Action action = Action.CREATE;
		assertTrue(action.getDescription().toLowerCase().contains("save"));
	}
	
	@Test
	public void itemTest() {
		Action action = Action.READ;
		assertTrue(action.getDescription().toLowerCase().contains("read"));
	}
	
	@Test
	public void orderTest() {
		Action action = Action.UPDATE;
		assertTrue(action.getDescription().toLowerCase().contains("already"));
	}
	
	@Test
	public void stopTest() {
		Action action = Action.DELETE;
		assertTrue(action.getDescription().toLowerCase().contains("remove"));
	}
	@Test
	public void contentsTest() {
		Action action=Action.RETURN;
		assertTrue(action.getDescription().toLowerCase().contains("return"));
	}

}
