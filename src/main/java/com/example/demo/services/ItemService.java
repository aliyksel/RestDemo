package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Item;
import com.example.demo.mongodb.CustomerRepositoryCustom;


@Service
public class ItemService {
	
	@Autowired
	private CustomerRepositoryCustom customerRepositoryCustom;

	public void updateItem(String customerId, long billId, long itemId, Item item) throws Exception {
		customerRepositoryCustom.updateItem(customerId, billId, itemId, item);
	}

	public void deleteItem(String customerId, long billId, long barCode) {
		customerRepositoryCustom.removeItemFromCustomer(customerId, billId, barCode);
		
	}



}
