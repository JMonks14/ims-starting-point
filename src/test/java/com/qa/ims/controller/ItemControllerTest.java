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

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.services.ItemServices;


public class ItemControllerTest {
	
	@RunWith(MockitoJUnitRunner.class)
	public class CustomerControllerTest {

		/**
		 * The thing I want to fake functionality for
		 */
		@Mock
		private ItemServices itemServices;

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
			CustomerController customerController = new CustomerController(customerServices);
			List<Customer> customers = new ArrayList<>();
			customers.add(new Customer("Chris", "P", "ChrisP", "password1"));
			customers.add(new Customer("Rhys", "T","RhysT", "password2"));
			customers.add(new Customer("Nic", "J", "NicJ", "pw6000"));
			Mockito.when(customerServices.readAll()).thenReturn(customers);
			assertEquals(customers, customerController.readAll());
		}

		@Test
		public void createTest() {
			String firstName = "Chris";
			String surname = "Perrins";
			String username = "ChrisP";
			String password = "password1";
			Mockito.doReturn(firstName, surname).when(customerController).getInput();
			Customer customer = new Customer(firstName, surname, username, password);
			Customer savedCustomer = new Customer(1L, "Chris", "Perrins", "ChrisP", "password");
			Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
			assertEquals(savedCustomer, customerController.create());
		}

		/**
		 *
		 */
		@Test
		public void updateTest() {
			long id = 1;
			String firstName = "Rhys";
			String surname = "Thompson";
			String username = "RhysT";
			String password = "password4";
			Mockito.doReturn(id).when(customerController).getValidId();
			Mockito.doReturn(firstName, surname, username, password).when(customerController).getInput();
			Customer customer = new Customer(1L, firstName, surname, username, password);
			Mockito.when(customerServices.update(customer)).thenReturn(customer);
			assertEquals(customer, customerController.update());
		}
	//
//		/**
//		 * Delete doesn't return anything, so we can just verify that it calls the
//		 * delete method
//		 */
		@Test
		public void deleteTest() {
			long id = 1;
			Mockito.doReturn(id).when(customerController).getValidId();
			customerController.delete();
			Mockito.verify(customerServices, Mockito.times(1)).delete(1L);
		}

}
