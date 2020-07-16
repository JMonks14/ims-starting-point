package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Itemline;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderUpdateControllerTest {
	
	
	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private OrderDaoMysql updateService;

	/**
	 * Spy is used because i want to mock some methods inside the order I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy // for the methods in customerController
	@InjectMocks // for any classes our customerController calls (in this case customerService)
	private OrderUpdateController updateController;
	@Test
	public void readAllTest() {
		List<OrderLine> order = new ArrayList<>();
		long orderID = 1;
		Mockito.doReturn(orderID).when(updateController).getLong();
		order.add(new OrderLine(1L, "thing", 1, 5.0));
		order.add(new OrderLine(2L, "thing2", 1, 20.0));
		order.add(new OrderLine(1L, "Thing", 5, 20.0));
		Mockito.when(updateService.readAllitems(orderID)).thenReturn(order);
		assertEquals(order, updateController.readAll());
	}
	@Test
	public void createTest() {
		long order_id=1;
		long item_id=1;
		int quantity = 1;
		Mockito.doReturn(order_id, item_id).when(updateController).getValidId();
		Mockito.doReturn(quantity).when(updateController).getInt();
		Itemline itemLine = new Itemline(order_id, item_id, quantity);
		Itemline savedLine = new Itemline(1,1,1);
		Mockito.when(updateService.checkQuant(itemLine)).thenReturn(itemLine);
		Mockito.when(updateService.addItem(itemLine)).thenReturn(savedLine);
		Itemline checkLine = updateController.add();
		assertEquals(savedLine, checkLine);
	}
	@Test
	public void updateTest() {
		long order_id=1;
		long item_id=1;
		int quantity = 1;
		Mockito.doReturn(order_id, item_id).when(updateController).getValidId();
		Mockito.doReturn(quantity).when(updateController).getInt();
		Itemline itemLine = new Itemline(order_id, item_id, quantity);
		Itemline savedLine = new Itemline(1,1,1);
		Mockito.when(updateService.changeQuant(itemLine)).thenReturn(savedLine);
		Itemline checkLine = updateController.change();
		assertEquals(savedLine, checkLine);
	}
	
//
//	/**
//	 * Delete doesn't return anything, so we can just verify that it calls the
//	 * delete method
//	 */
	@Test
	public void deleteTest() {
		long order_id = 1;
		long item_id = 1;
		Itemline line = new Itemline(order_id, item_id);
		Mockito.doReturn(order_id, item_id).when(updateController).getLong();
		updateController.del();
		Mockito.verify(updateService, Mockito.times(1)).delItem(line);
	}

}
