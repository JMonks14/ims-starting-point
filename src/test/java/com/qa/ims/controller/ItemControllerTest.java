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

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.ItemServices;



	
@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

		/**
		 * The thing I want to fake functionality for
		 */
		@Mock
		private ItemServices itemService;

		/**
		 * Spy is used because i want to mock some methods inside the item I'm testing
		 * InjectMocks uses dependency injection to insert the mock into the customer
		 * controller
		 */
		@Spy // for the methods in customerController
		@InjectMocks // for any classes our customerController calls (in this case customerService)
		private ItemController itemController;

		@Test
		public void readAllTest() {
			ItemController itemController = new ItemController(itemService);
			List<Item> items = new ArrayList<>();
			items.add(new Item("Magic spoon", 15.0, 12));
			items.add(new Item("Magic rock", 10.0, 10));
			items.add(new Item("Magic paste", 8.0, 20));
			Mockito.when(itemService.readAll()).thenReturn(items);
			assertEquals(items, itemController.readAll());
		}

		@Test
		public void createTest() {
			String Name = "Magic spade";
			double price = 25.0;
			int stock = 7;
			Mockito.doReturn(Name).when(itemController).getInput();
			Mockito.doReturn(price).when(itemController).getDouble();
			Mockito.doReturn(stock).when(itemController).getInt();
			Item item = new Item(Name, price, stock);
//			Item mockitem = Mockito.mock(Item.class);
			Item savedItem = new Item("Magic spade", 25.0, 7);
			Mockito.when(itemService.create(item)).thenReturn(savedItem);
			Item checkItem = itemController.create();
			assertEquals(savedItem, checkItem);
		}
//
//		/**
//		 *
//		 */
		@Test
		public void updateTest() {
			long id = 1;
			String name = "Magic Spade";
			double price = 25.0;
			int stock = 7;
			Mockito.doReturn(id).when(itemController).getValidId();
			Mockito.doReturn(name).when(itemController).getInput();
			Mockito.doReturn(price).when(itemController).getDouble();
			Mockito.doReturn(stock).when(itemController).getInt();
			Item item = new Item(id, name, price, stock);
			Mockito.when(itemService.update(item)).thenReturn(item);
			assertEquals(item, itemController.update());
		}
//	//
////		/**
////		 * Delete doesn't return anything, so we can just verify that it calls the
////		 * delete method
////		 */
		@Test
		public void deleteTest() {
			long id = 1;
			Mockito.doReturn(id).when(itemController).getValidId();
			itemController.delete();
			Mockito.verify(itemService, Mockito.times(1)).delete(1L);
		}

}
