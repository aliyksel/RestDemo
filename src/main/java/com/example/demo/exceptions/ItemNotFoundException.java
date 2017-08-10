package com.example.demo.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException{

	public ItemNotFoundException(String customerId, long billId, long itemId) {
		super("could not find item '"+itemId+"' under  bill '"+ billId +"' and customer '" + customerId + "'.");
	}

	
}
