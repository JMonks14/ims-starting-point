package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.qa.ims.persistence.domain.Itemline;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.utils.Utils;

public class OrderDaoMysql implements Dao<Order>{
	
	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims_project";
		this.username = username;
		this.password = password;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_ID");
		long cust_id = resultSet.getLong("fk_cust_ID");
		String custFirstName= resultSet.getString("first_name");
		String custLastName= resultSet.getString("last_name");		
		double cost = resultSet.getDouble("total_cost");
		return new Order(id, cust_id, custFirstName, custLastName, cost);
	}
	
	OrderLine orderLineFromResultSet(ResultSet resultSet) throws SQLException {
		long itemID = (resultSet.getLong("item_ID"));
		String itemName = resultSet.getString("item_name");
		int quantity= resultSet.getInt("quantity");
		double cost = resultSet.getDouble("total_price");
		return new OrderLine(itemID, itemName, quantity, cost);
	}
	
	public Itemline checkQuant (Itemline itemLine) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery("SELECT quant_in_stock FROM Items WHERE item_ID =" + itemLine.getItemID());) {
			result.next();
			int stockQuant = result.getInt("quant_in_stock");
			while (itemLine.getQuantity() > stockQuant || itemLine.getQuantity() < 0) {
				if (itemLine.getQuantity() > stockQuant) {
				LOGGER.info("Sorry, there is only " + stockQuant + " of that item in stock currently, please enter a value no greater than " + stockQuant);
				itemLine.setQuantity(Integer.parseInt(Utils.getInput()));
				}
				if (itemLine.getQuantity() < 0) {
					LOGGER.info("Please enter a number equal to 0 or greater");
					itemLine.setQuantity(Integer.parseInt(Utils.getInput()));
				}
			}
			return itemLine;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}return null;
				
	}
	
//	public int checkQuant (long item_id, int quant) {
//		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
//				Statement statement = connection.createStatement();
//				ResultSet result = statement.executeQuery("SELECT quant_in_stock FROM Items WHERE item_ID =" + item_id);) {
//			result.next();
//			int stockQuant = result.getInt("quant_in_stock");
//			while (quant > stockQuant || quant < 0) {
//				if (quant > stockQuant) {
//				LOGGER.info("Sorry, there is only " + stockQuant + " of that item in stock currently, please enter a value that does not take the quantity over the stock value");
//				quant=(Integer.parseInt(Utils.getInput()));
//				}
//				if (quant < 0) {
//					LOGGER.info("Please enter a number equal to 0 or greater");
//					itemLine.setQuantity(Integer.parseInt(Utils.getInput()));
//				}
//			}
//			return itemLine;
//		} catch (SQLException e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getMessage());
//		}return null;

	/**
	 * Reads all items from the database
	 *
	 * @return A list of items
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT o.order_ID, o.fk_cust_ID, c.first_name, c.last_name, o.total_cost FROM Orders o JOIN Customers c on c.cust_ID=o.fk_cust_ID");) {
			ArrayList<Order> orders = new ArrayList<>();
			resultSet.next();
			while (resultSet.next()) {
				orders.add(orderFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public List<OrderLine> readAllitems(long orderId) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT i.item_ID, i.item_name, od.quantity, od.total_price " + 
						"FROM Order_details od join Items i on i.item_ID=od.fk_item_ID " + 
						"WHERE od.fk_order_ID=" + orderId);) {
			ArrayList<OrderLine> order = new ArrayList<>();
			while (resultSet.next()) {
				order.add(orderLineFromResultSet(resultSet));
			}
			return order;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT o.order_ID, o.fk_cust_ID, c.first_name, c.last_name, o.total_cost FROM Orders o JOIN Customers c on c.cust_ID=o.fk_cust_ID ORDER BY order_ID DESC LIMIT 1");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a items in the database
	 *
	 * @param customer - takes in a items object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into Orders(fk_cust_ID) values (" + order.getCust_ID() + ")");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readOrder(Long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Items where order_ID = " + id);) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a item in the database
	 *
	 * @param items - takes in a item object, the id field will be used to
	 *                 update that item in the database
	 * @return
	 */
	@Override
	public Order update(Order order) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE Orders SET fk_cust_ID=" + order.getCust_ID() + " WHERE order_ID" + order.getID());
			return readOrder(order.getID());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Itemline addItem(Itemline itemLine) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO Order_details(fk_order_ID, fk_item_ID, quantity) VALUES(" + itemLine.getID()+ "," + itemLine.getItemID() + ","+ itemLine.getQuantity() + ")");
			statement.executeUpdate("UPDATE Items SET quant_in_stock=quant_in_stock-" + itemLine.getQuantity() +" WHERE item_ID=" + itemLine.getItemID());
			statement.executeUpdate("UPDATE Order_details SET total_price=" + 
					"quantity*(SELECT price FROM Items WHERE item_ID=" + itemLine.getItemID() + ") WHERE fk_order_ID=" + itemLine.getID() +  " AND fk_item_ID=" + itemLine.getItemID());
			statement.executeUpdate("UPDATE Orders SET total_cost=(SELECT SUM(total_price) from Order_details WHERE fk_order_ID=" + itemLine.getID() + ") WHERE order_ID=" + itemLine.getID());
			ResultSet results = statement.executeQuery("SELECT quantity FROM Order_details WHERE fk_item_ID=" + itemLine.getItemID() + " AND fk_order_ID=" + itemLine.getID());
			results.next();
			int newQuant = results.getInt("quantity");
			if (newQuant<=0)
				delItem(itemLine);
			return itemLine;
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public Itemline changeQuant(Itemline itemLine) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			if (itemLine.getQuantity() < 0)
				itemLine.setQuantity(0);
			ResultSet initialrs = statement.executeQuery("SELECT quantity FROM Order_details WHERE fk_item_ID=" + itemLine.getItemID() + " AND fk_order_ID=" + itemLine.getID());
			initialrs.next();
			int inQuant =initialrs.getInt("quantity");
			ResultSet result = statement.executeQuery("SELECT quant_in_stock FROM Items WHERE item_ID =" + itemLine.getItemID());
			result.next();
			int stockQuant = result.getInt("quant_in_stock");
			int diff = inQuant - itemLine.getQuantity();
			int checkDiff = diff*-1;
			int available=inQuant+stockQuant;
			do  {	
			   if (checkDiff > stockQuant) {
					LOGGER.info("Sorry, there is only " + inQuant + " of that item in stock currently, please enter a value no greater than " + available);
					itemLine.setQuantity(Integer.parseInt(Utils.getInput()));
					}
			   if (checkDiff <= 0) {
						LOGGER.info("Please enter a number equal to 0 or greater");
						itemLine.setQuantity(Integer.parseInt(Utils.getInput()));
			   }
			   diff = inQuant - itemLine.getQuantity();
			   checkDiff = diff*-1;
			} while (checkDiff > stockQuant || checkDiff <0);
			statement.executeUpdate("UPDATE Items SET quant_in_stock=quant_in_stock+" + diff + " WHERE item_ID=" + itemLine.getItemID());
			statement.executeUpdate("UPDATE Order_details SET quantity=" + itemLine.getQuantity() + " where fk_item_ID=" + itemLine.getItemID() + " AND fk_order_ID=" + itemLine.getID());		
			statement.executeUpdate("UPDATE Order_details SET total_price=" + 
					"quantity*(SELECT price FROM Items WHERE item_ID=" + itemLine.getItemID() + ") WHERE fk_order_ID=" + itemLine.getID() +  " AND fk_item_ID=" + itemLine.getItemID());
			statement.executeUpdate("UPDATE Orders SET total_cost=(SELECT SUM(total_price) from Order_details WHERE fk_order_ID=" + itemLine.getID() + ") WHERE order_ID=" + itemLine.getID());
			ResultSet results = statement.executeQuery("SELECT quantity FROM Order_details WHERE fk_item_ID=" + itemLine.getItemID() + " AND fk_order_ID=" + itemLine.getID());
			results.next();
			int newQuant = results.getInt("quantity");
			if (newQuant<=0)
				delItem(itemLine);
		}catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public Itemline delItem(Itemline itemLine) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("DELETE FROM Order_details WHERE fk_item_ID=" + itemLine.getItemID() + " AND fk_order_ID=" + itemLine.getID());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	

	/**
	 * Deletes a item in the database
	 *
	 * @param id - id of the customer
	 */
	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("UPDATE Order_details SET fk_order_ID=0 WHERE fk_order_ID=" + id);
			statement.executeUpdate("delete from Orders where order_ID = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

//	@Override
//	public Order update(Order order) {
//		OrderOption.printOrderOptions();
//		OrderOption option = OrderOption.getOption();
//		OrderUpdateController updateControl = new OrderUpdateController();
//		switch(option) {
//		case ADDITEM:
//			Itemline itemLine = updateControl.add();
//			addItem(itemLine, itemLine.getQuantity(), itemLine.getItemID());
//			LOGGER.info("Item added");
//			break;
//		case DELITEM:
//			Itemline itemline = updateControl.del();
//			delItem(itemline.getID(), itemline.getItemID());
//			break;
//		case CHANGE:
//			Itemline itemLine3 = updateControl.change();
//			changeQuant(itemLine3.getID(), itemLine3.getQuantity(), itemLine3.getItemID());
//			break;
//		case CUSTOMER:
//			Order 
//		case RETURN:
//			break;
//		}
//		return null;
//	}


}
