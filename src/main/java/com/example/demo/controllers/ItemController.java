package com.example.demo.controllers;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.exceptions.CustomerNotFoundException;
import com.example.demo.models.Item;
import com.example.demo.services.ItemService;

@Controller
@RequestMapping("/customer/{customerId}/bill/{billId}/barCode")
public class ItemController {

	static Log log = LogFactory.getLog(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.PUT, value="/{itemId}")
	public ResponseEntity<?> updateItem(@PathVariable String customerId, @PathVariable long billId, @PathVariable long itemId, @RequestBody Item item) throws Exception{
		itemService.updateItem(customerId, billId, itemId, item);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{itemId}")
	public ResponseEntity<?> deleteItem(@PathVariable String customerId, @PathVariable long billId, @PathVariable long itemId) throws Exception{
		itemService.deleteItem(customerId, billId, itemId);
		return ResponseEntity.ok().build();
	}
}
