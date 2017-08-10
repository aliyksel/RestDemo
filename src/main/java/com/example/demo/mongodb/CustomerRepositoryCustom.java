package com.example.demo.mongodb;

import java.math.BigDecimal;

import com.example.demo.models.Item;

public interface CustomerRepositoryCustom {
	
	public boolean removeBillFromCustomer(String customerId, long billId);
	
	public boolean removeItemFromCustomer(String customerId, long billId, long barcode);
	
	public boolean updateItem(String customerId, long billId, long barcode, Item item) throws Exception;
	
}
