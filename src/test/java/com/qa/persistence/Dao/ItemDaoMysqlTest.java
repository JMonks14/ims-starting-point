package com.qa.persistence.Dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.apache.log4j.Logger;
import com.qa.ims.Ims;
import com.qa.ims.persistence.dao.ItemDoaMysql;
import com.qa.ims.persistence.domain.Item;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ItemDaoMysqlTest {
	
	public static final Logger LOGGER = Logger.getLogger(ItemDaoMysqlTest.class);

	@BeforeClass
	public static void aInit() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://localhost:3306/", "root", "root",
				"src/test/resources/sql-schema.sql");
//	}
	
//	@BeforeClass
//	public static void setup() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Items (item_name, quant_in_stock, price) VALUES('nolongerexists',0,0)");
			statement.executeUpdate("Update Items set item_ID=0");

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
		@Test
		public void bCreateTest() {
			ItemDoaMysql itemDaoMysql = new ItemDoaMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			String Name = "Steak";
			int quantity = 5;
			double price = 15.0;
			Item item = new Item(2L, Name, price, quantity);
			String Name2 = "Burger";
			int quantity2 = 15;
			double price2 = 5.75;
			Item item2 = new Item(3L, Name2, price2, quantity2);
			String Name3 = "Hot dog";
			int quantity3 = 19;
			double price3 = 3.85;
			Item item3 = new Item(4L, Name3, price3, quantity3);
			assertEquals(item, itemDaoMysql.create(item));
			assertEquals(item2, itemDaoMysql.create(item2));
			assertEquals(item3, itemDaoMysql.create(item3));
		}
		
		
		
		@Test
		public void cReadAllTest() {
			ItemDoaMysql itemDaoMysql = new ItemDoaMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			List<Item> items = new ArrayList<>();
			String Name = "Steak";
			int quantity = 5;
			double price = 15.0;
			Item item = new Item(2L, Name, price, quantity);
			String Name2 = "Burger";
			int quantity2 = 15;
			double price2 = 5.75;
			Item item2 = new Item(3L, Name2, price2, quantity2);
			String Name3 = "Hot dog";
			int quantity3 = 19;
			double price3 = 3.85;
			Item item3 = new Item(4L, Name3, price3, quantity3);
			items.add(item);
			items.add(item2);
			items.add(item3);

			assertEquals(items, itemDaoMysql.readAll());
		}
		@Test
		public void dUpdateTest() {
			ItemDoaMysql itemDaoMysql = new ItemDoaMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			Long id = 2L;
			String Name = "Rib-eye Steak";
			int quantity = 8;
			double price = 16.0;
			Item item = new Item(id , Name, price, quantity);
			assertEquals(item, itemDaoMysql.update(item));
		}
		
		@Test
		public void eDeleteTest() {
			ItemDoaMysql itemDaoMysql = new ItemDoaMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			String id = "2";
			itemDaoMysql.delete(Long.parseLong(id));
			List<Item> items = new ArrayList<>();
			String Name2 = "Burger";
			int quantity2 = 15;
			double price2 = 5.75;
			Item item2 = new Item(3L, Name2, price2, quantity2);
			String Name3 = "Hot dog";
			int quantity3 = 19;
			double price3 = 3.85;
			Item item3 = new Item(4L, Name3, price3, quantity3);
			items.add(item2);
			items.add(item3);
			assertEquals(items, itemDaoMysql.readAll());
		}
		
		@AfterClass
		public static void cleanDB() {

			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("drop table Order_details");
				statement.executeUpdate("drop table Orders");
				statement.executeUpdate("drop table Items");
				
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		}

}
