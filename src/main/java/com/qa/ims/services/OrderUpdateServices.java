package com.qa.ims.services;

import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Itemline;

public class OrderUpdateServices {
	
	private OrderDaoMysql orderDetailDao;

	public OrderUpdateServices(OrderDaoMysql orderDetailDao) {
		super();
		this.orderDetailDao = orderDetailDao;
	}
	
	public Itemline addItem(Itemline itemLine) {
		return orderDetailDao.addItem(itemLine);
	}
	
	public Itemline delItem(Itemline itemLine) {
		return orderDetailDao.delItem(itemLine);
	}
	public Itemline changeQuant(Itemline itemLine) {
		return orderDetailDao.changeQuant(itemLine);
	}

}
