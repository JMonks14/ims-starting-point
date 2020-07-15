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

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderServices;


@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private OrderServices orderService;

	/**
	 * Spy is used because i want to mock some methods inside the order I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy // for the methods in customerController
	@InjectMocks // for any classes our customerController calls (in this case customerService)
	private OrderController orderController;

	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderService);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L));
		orders.add(new Order(2L, 1L));
		orders.add(new Order(3L,2L, "This", "Guy", 20.0));
		Mockito.when(orderService.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

	@Test
	public void createTest() {
		long cust_id=1;
		Mockito.doReturn(cust_id).when(orderController).getValidId();
		Order order = new Order(cust_id);
		Order savedOrder = new Order(1);
		Mockito.when(orderService.create(order)).thenReturn(savedOrder);
		Order checkOrder = orderController.create();
		assertEquals(savedOrder, checkOrder);
	}
//
//	/**
//	 *
//	 */
	@Test
	public void updateTest() {
		long id = 1;
		long cust_id =1;
		Mockito.doReturn(id).when(orderController).getValidId();
		Mockito.doReturn(cust_id).when(orderController).getLong();
		Order order = new Order(id, cust_id);
		Mockito.when(orderService.update(order)).thenReturn(order);
		assertEquals(order, orderController.update());
	}
//
//	/**
//	 * Delete doesn't return anything, so we can just verify that it calls the
//	 * delete method
//	 */
	@Test
	public void deleteTest() {
		long id = 1;
		Mockito.doReturn(id).when(orderController).getValidId();
		orderController.delete();
		Mockito.verify(orderService, Mockito.times(1)).delete(1L);
	}


}
