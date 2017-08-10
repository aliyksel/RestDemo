package com.example.demo.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	static Log log = LogFactory.getLog(CustomerController.class);

	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Customer> getCustomer(){

		return customerService.getCustomers();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{customerId}")
	public @ResponseBody Customer getCustomerById(@PathVariable String customerId){

		return customerService.getCustomerById(customerId);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> insertCustomer(@RequestBody Customer customer){

		String customerId = customerService.insertCustomer(customer).getId();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(customerId).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{customerId}")
	public void updatecustomer(@PathVariable String customerId, @RequestBody Customer Customer) throws Exception{
	   log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		throw new CustomerNotFoundException(customerId);
	}
}
