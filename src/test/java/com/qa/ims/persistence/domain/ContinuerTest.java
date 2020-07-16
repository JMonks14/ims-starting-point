package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class ContinuerTest {

	@Test
	public void customerTest() {
		Continuer choice = Continuer.YES;
		assertTrue(choice.getDescription().toLowerCase().contains("return"));
	}
	
	@Test
	public void itemTest() {
		Continuer choice = Continuer.NO;
		assertTrue(choice.getDescription().toLowerCase().contains("stop"));
	}
	
}
