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
import com.qa.ims.persistence.domain.Customer;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoMysqlTest {
	
	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysqlTest.class);

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

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}
		@Test
		public void bCreateTest() {
			CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			String firstName = "Vinesh";
			String surname = "Ghela";
			String username = "trainer1";
			String password = "ago0dpassword";
			Customer customer = new Customer(2L, firstName, surname, username, password);
			String firstName2 = "James";
			String surname2 = "Peach";
			String username2 = "newyorkbound";
			String password2 = "omnomnompeach";
			Customer customer2 = new Customer(3L, firstName2, surname2, username2, password2);
			String firstName3 = "Bob";
			String surname3 = "Perry";
			String username3 = "genericman";
			String password3 = "kevinismyfriend";
			Customer customer3 = new Customer(4L, firstName3, surname3, username3, password3);
			assertEquals(customer, customerDaoMysql.create(customer));
			assertEquals(customer2, customerDaoMysql.create(customer2));
			assertEquals(customer3, customerDaoMysql.create(customer3));
		}
		
		
		
		@Test
		public void cReadAllTest() {
			CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			List<Customer> customers = new ArrayList<>();
			customers.add(new Customer(2L, "Vinesh", "Ghela", "trainer1", "ago0dpassword"));
			customers.add(new Customer(3L, "James", "Peach", "newyorkbound", "omnomnompeach"));
			customers.add(new Customer(4L, "Bob", "Perry", "genericman", "kevinismyfriend"));

			assertEquals(customers, customerDaoMysql.readAll());
		}
		@Test
		public void fUpdateTest() {
			CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			Long id = 2L;
			String firstName = "nolongerexists";
			String surname = "Ghela";
			String username = "QA4lyfe";
			String password = "abe11erpassw0rd";
			Customer customer = new Customer(id, firstName, surname, username, password);
			assertEquals(customer, customerDaoMysql.update(customer));
		}
		
		@Test
		public void gDeleteTest() {
			CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
					"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
			String id = "2";
			customerDaoMysql.delete(Long.parseLong(id));
			List<Customer> customers = new ArrayList<>();
			customers.add(new Customer(3L, "James", "Peach", "newyorkbound", "omnomnompeach"));
			customers.add(new Customer(4L, "Bob", "Perry", "genericman", "kevinismyfriend"));
			assertEquals(customers, customerDaoMysql.readAll());
		}
		
		@AfterClass
		public static void cleanDB() {

			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("drop table Orders");
				statement.executeUpdate("drop table Customers");
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		}
}
