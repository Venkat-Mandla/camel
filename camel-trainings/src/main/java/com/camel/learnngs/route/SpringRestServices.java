package com.camel.learnngs.route;

import org.apache.camel.CamelContext;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camel.learnings.data.domain.Customer;
import com.camel.learnings.data.domain.CustomerType;

@RestController
@RequestMapping(path="/service-api")
public class SpringRestServices {
	
	@Autowired
	private CamelContext camelContext;
	
	public SpringRestServices() {
		System.out.println("Hello SpringRestServices bean is instantiated....>>>>>>>>>>>>>>>>>>>");
	}
	
	@Get
	@RequestMapping(path="/get-details",produces="application/json")
	public Customer getDetails() {
		Customer customer = new Customer();
		CustomerType custType = new CustomerType();
		custType.setCustId("1234");
		custType.setCustName("ABC...");
		customer.setCustomer(custType);
		return customer;
	}
	

	@Post
	@RequestMapping(path="/create-details",produces="application/json",consumes="application/json")
	public String create(@RequestBody Customer cust) {
		String response=camelContext.createProducerTemplate().requestBody("direct:convertObjToXml",cust, String.class);
		return "Hello I'm here...."+response;
	}

}
