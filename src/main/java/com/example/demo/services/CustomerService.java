package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Customer;
import com.example.demo.models.Bill;
import com.example.demo.models.Item;
import com.example.demo.mongodb.CustomerRepository;




@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}

	public Customer getCustomerById(String customerId) {
		return customerRepository.findById(customerId);
	}

	public Customer insertCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
}
