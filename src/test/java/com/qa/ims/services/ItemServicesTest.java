package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Item;


@RunWith(MockitoJUnitRunner.class)
public class ItemServicesTest {
	
	@Mock
	private Dao<Item> itemDao;
	
	@InjectMocks
	private ItemServices itemServices;
	
	
	@Test
	public void itemServicesCreate() {
		Item item = new Item("gold coin", 1, 200);
		itemServices.create(item);
		Mockito.verify(itemDao, Mockito.times(1)).create(item);
	}
	
	@Test
	public void itemServicesRead() {
		itemServices.readAll();
		Mockito.verify(itemDao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void itemServicesUpdate() {
		Item item = new Item("gold coin", 1, 200);
		itemServices.update(item);
		Mockito.verify(itemDao, Mockito.times(1)).update(item);
	}
	
	@Test
	public void itemServicesDelete() {
		Item coin = new Item(1L, "gold coin", 1, 200);
		itemServices.delete(coin.getItem_ID());
		Mockito.verify(itemDao, Mockito.times(1)).delete(coin.getItem_ID());
	}

}
