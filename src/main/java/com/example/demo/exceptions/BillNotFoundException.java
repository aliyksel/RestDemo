package com.example.demo.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BillNotFoundException extends RuntimeException{

	public BillNotFoundException(String customerId, long billId) {
		super("could not find bill '"+ billId +"' under customer '" + customerId + "'.");
	}

	
}
