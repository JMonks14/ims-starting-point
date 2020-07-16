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
import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.dao.ItemDoaMysql;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Itemline;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderLine;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class OrderDaoMysqlTest {
	
	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysqlTest.class);

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
			statement.executeUpdate("INSERT INTO Customers (first_name, last_name, username, password) VALUES('nolongerexists','nolongerexists','nolongerexists','nolongerexists')");
			statement.executeUpdate("Update Customers set cust_ID=0");
			statement.executeUpdate("INSERT INTO Orders (fk_cust_ID, total_cost) VALUES(0,0)");
			statement.executeUpdate("Update Orders set order_ID=0");
			statement.executeUpdate("INSERT INTO Items (item_name, quant_in_stock, price) VALUES('nolongerexists',0,0)");
			statement.executeUpdate("Update Items set item_ID=0");

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		CustomerDaoMysql custDao = new CustomerDaoMysql("jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC","root", "root");
		custDao.create(new Customer("Vinesh", "Ghela", "trainer1", "ago0dpassword"));
		custDao.create(new Customer("James", "Peach", "newyorkbound", "omnomnompeach"));
		custDao.create(new Customer("Bob", "Perry", "genericman", "kevinismyfriend"));
		
		ItemDoaMysql itemDao = new ItemDoaMysql("jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
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
		itemDao.create(item);
		itemDao.create(item2);
		itemDao.create(item3);
	}
		@Test
		public void bCreateTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			long custID = 2;
			double cost = 0;
			String firstName = "Vinesh";
			String lastName = "Ghela";
			Order order = new Order(2L,custID, firstName, lastName, cost);
			long custID2 = 3;
			double cost2 = 0;
			String firstName2 = "James";
			String lastName2 = "Peach";
			Order order2 = new Order(3L,custID2, firstName2, lastName2, cost2);
			long custID3 = 4;
			double cost3 = 0;
			String firstName3 = "Bob";
			String lastName3 = "Perry";
			Order order3 = new Order(4L,custID3, firstName3, lastName3, cost3);
			
			assertEquals(order, orderDaoMysql.create(order));
			assertEquals(order2, orderDaoMysql.create(order2));
			assertEquals(order3, orderDaoMysql.create(order3));
		}
		
		
		
		@Test
		public void cReadAllTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			List<Order> orders = new ArrayList<>();
			long custID = 2;
			double cost = 0;
			String firstName = "Vinesh";
			String lastName = "Ghela";
			Order order = new Order(2L,custID, firstName, lastName, cost);
			long custID2 = 3;
			double cost2 = 0;
			String firstName2 = "James";
			String lastName2 = "Peach";
			Order order2 = new Order(3L,custID2, firstName2, lastName2, cost2);
			long custID3 = 4;
			double cost3 = 0;
			String firstName3 = "Bob";
			String lastName3 = "Perry";
			Order order3 = new Order(4L,custID3, firstName3, lastName3, cost3);
			orders.add(order);
			orders.add(order2);
			orders.add(order3);

			assertEquals(orders, orderDaoMysql.readAll());
		}
		@Test
		public void dUpdateTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			long custID = 3;
			double cost = 0;
			String firstName = "James";
			String lastName = "Peach";
			Order order = new Order(2L ,custID, firstName, lastName, cost);
			assertEquals(order, orderDaoMysql.update(order));
		}
		
		@Test
		public void eDeleteTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			String id = "2";
			orderDaoMysql.delete(Long.parseLong(id));
			List<Order> orders = new ArrayList<>();
			long custID2 = 3;
			double cost2 = 0;
			String firstName2 = "James";
			String lastName2 = "Peach";
			Order order2 = new Order(3L,custID2, firstName2, lastName2, cost2);
			long custID3 = 4;
			double cost3 = 0;
			String firstName3 = "Bob";
			String lastName3 = "Perry";
			Order order3 = new Order(4L,custID3, firstName3, lastName3, cost3);
			orders.add(order2);
			orders.add(order3);
			assertEquals(orders, orderDaoMysql.readAll());
		}
		
		@Test
		public void fAddItemTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			long orderID = 3;
			long itemID = 2;
			int quant = 1;
			Itemline itemLine = new Itemline(orderID, itemID, quant);
			long orderID2 = 3;
			long itemID2 = 3;
			int quant2 = 1;
			Itemline itemLine2 = new Itemline(orderID2, itemID2, quant2);
			long orderID3 = 3;
			long itemID3 = 4;
			int quant3 = 1;
			Itemline itemLine3 = new Itemline(orderID3, itemID3, quant3);
			assertEquals(itemLine,orderDaoMysql.addItem(itemLine));
			assertEquals(itemLine2,orderDaoMysql.addItem(itemLine2));
			assertEquals(itemLine3,orderDaoMysql.addItem(itemLine3));
		}
		
		@Test
		public void gReadAllItemsTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			long orderID = 3;
			long itemID = 2;
			int quant = 1;
			String Name = "Steak";
			double cost = 15.0;
			OrderLine itemLine = new OrderLine(itemID, Name, quant, cost);
			long itemID2 = 3;
			int quant2 = 1;
			String Name2 = "Burger";
			double cost2 = 5.75;
			OrderLine itemLine2 = new OrderLine(itemID2, Name2, quant2, cost2);
			long itemID3 = 4;
			int quant3 = 1;
			String Name3 = "Hot Dog";
			double cost3 = 3.85;
			OrderLine itemLine3 = new OrderLine(itemID3, Name3, quant3, cost3);
			List<OrderLine> order = new ArrayList<>();
			order.add(itemLine);
			order.add(itemLine2);
			order.add(itemLine3);
			assertEquals(order, orderDaoMysql.readAllitems(orderID));
		}
		
		@Test
		public void hchangeQuantTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			long orderID = 3;
			long itemID = 2;
			int quant = 2;
			Itemline itemLine = new Itemline(orderID, itemID, quant);
			assertEquals(itemLine, orderDaoMysql.changeQuant(itemLine));
			
		}
		
		@Test
		public void iDelItemTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			long orderID = 3;
			long itemID = 2;
			int quant = 2;
			String Name = "Steak";
			double cost = 15.0;
			OrderLine itemLine = new OrderLine(itemID, Name, quant, cost*2);
			long itemID3 = 4;
			int quant3 = 1;
			String Name3 = "Hot Dog";
			double cost3 = 3.85;
			OrderLine itemLine3 = new OrderLine(itemID3, Name3, quant3, cost3);
			Itemline Burgerline= new Itemline(3,3);
			List <OrderLine> order = new ArrayList<>();
			order.add(itemLine);
			order.add(itemLine3);
			orderDaoMysql.delItem(Burgerline);
			assertEquals(order, orderDaoMysql.readAllitems(orderID));
		}
		@Test
		public void jCheckQuantTest() {
			OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			long orderID = 3;
			long itemID = 2;
			int quant = 2;
			Itemline itemLine = new Itemline(orderID, itemID, quant);
			assertEquals(itemLine, orderDaoMysql.checkQuant(itemLine));
		}
		
		@AfterClass
		public static void cleanDB() {

			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("drop table Order_details");
				statement.executeUpdate("drop table Orders");
				statement.executeUpdate("drop table Items");
				statement.executeUpdate("drop table Customers");
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		}

}
;