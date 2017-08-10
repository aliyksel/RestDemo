package com.example.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.services.BillService;

@Controller
@RequestMapping(value="/customer/{customerId}/bill")
public class BillController {
	
	@Autowired
	BillService billService;

	@RequestMapping(method=RequestMethod.DELETE, value="/{billId}")
	public ResponseEntity<Void> deleteBill(@PathVariable String customerId,@PathVariable long billId){
		billService.deleteBill(customerId, billId);
		return ResponseEntity.ok().build();
	}
	
}
