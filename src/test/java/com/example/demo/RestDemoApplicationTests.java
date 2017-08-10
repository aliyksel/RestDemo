package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.BDDAssertions.then;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.models.Customer;
import com.example.demo.models.Bill;
import com.example.demo.models.Item;
import com.example.demo.mongodb.CustomerRepository;
import com.example.demo.mongodb.CustomerRepositoryCustom;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestDemoApplicationTests {

	static final String customerId = "5983954bbb13061a88ca2925";
	static final String billId = "2";
	static final String barcode ="112";
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	

	@Autowired
	CustomerRepository customerRepository;
	
	@Before
    public void setup() throws Exception {
		customerRepository.deleteAll();
		Customer cust1= new Customer("Alice", "Smith");
		cust1.setId(customerId);
		Customer cust2 = new Customer("Bob", "Smith");
		
		Bill bill = new Bill();
		bill.setBillId(1);
		bill.addItem(new Item(111,"apple",80));
		bill.addItem(new Item(112,"pice",50));
		bill.addItem(new Item(113,"bread",120));
		cust1.addBill(bill);
		
		bill = new Bill();
		bill.setBillId(2);
		bill.addItem(new Item(111,"apple 2",80));
		bill.addItem(new Item(112,"pice 2",50));
		bill.addItem(new Item(113,"bread 2",120));
		cust1.addBill(bill);
		
		
		//customerService.addBilltoCustomer(bill);
		
		Customer cc = customerRepository.save(cust1);


		
		customerRepository.save(cust2);
		
		

  }

	@Test
	public void test1() {
		HttpHeaders httpHeaders = testRestTemplate
				  .headForHeaders("http://localhost:8080/customer");
		then(httpHeaders.getContentType().includes(MediaType.APPLICATION_JSON)).isTrue();
	}
	
	@Test
	public void test2() throws Exception{
		
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> entity = this.testRestTemplate.getForEntity(
				"http://localhost:8080/customer", List.class);

		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

	}
	
	@Test
	public void test3() throws Exception{
		@SuppressWarnings("rawtypes")
		ResponseEntity<Customer> entity = this.testRestTemplate.getForEntity(
				"http://localhost:8080/customer/"+customerId, Customer.class);
		
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(entity.getBody().getId()).isEqualTo(customerId);
	}
	
	@Test
	public void test4() {

		Set<HttpMethod> optionsForAllow = testRestTemplate.optionsForAllow("http://localhost:8080/customer");
		HttpMethod[] supportedMethods
		  = {HttpMethod.GET, HttpMethod.POST};
		
		then(optionsForAllow.containsAll(Arrays.asList(supportedMethods))).isTrue();
	}
	
	@Test
	public void test5() {
		Customer customer = new Customer("Ali","Yüksel");
		customer.addBill(new Bill("Asda",new Date(),Arrays.asList(new Item[] {new Item(111,"apple",80),new Item(112,"bread",120)})));
		
		HttpEntity<Customer> request = new HttpEntity<Customer>(customer);
		URI location = testRestTemplate.postForLocation("http://localhost:8080/customer", request);
		then(location).isNotNull();
		
	}
	
	@Test
	public void test6() throws Exception{
		Item newItem =  new Item(Long.parseLong(barcode),"pice 3",150);
		String url = "http://localhost:8080/customer/"+ customerId +"/bill/"+billId+"/barCode/"+barcode ;

		RequestEntity<Item> request = RequestEntity.put(new URI(url)).accept(MediaType.APPLICATION_JSON).body(newItem);
		 ResponseEntity<Void> response = testRestTemplate.exchange(request,  Void.class);
		 then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void test7() throws Exception{
		String url = "http://localhost:8080/customer/"+ customerId +"/bill/"+billId+"/barCode/"+barcode ;
		RequestEntity<Void> request = RequestEntity.delete(new URI(url)).build();
		 ResponseEntity<Void> response = testRestTemplate.exchange(request,  Void.class);
		 then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void test8() throws Exception{
		String url = "http://localhost:8080/customer/"+ customerId +"/bill/"+billId ;
		RequestEntity<Void> request = RequestEntity.delete(new URI(url)).build();
		 ResponseEntity<Void> response = testRestTemplate.exchange(request,  Void.class);
		 then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}	
	
}
