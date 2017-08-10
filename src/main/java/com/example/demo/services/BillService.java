package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mongodb.CustomerRepositoryCustom;

@Service
public class BillService {

	@Autowired
	CustomerRepositoryCustom customerRepositoryCustom;

	public void deleteBill(String customerId, long billId) {
		customerRepositoryCustom.removeBillFromCustomer(customerId, billId);
		
	}
}
