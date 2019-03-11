package com.camel.learnngs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.camel.learnings.data.domain.Address;
import com.camel.learnings.data.domain.Customer;
import com.camel.learnings.data.domain.CustomerType;
import com.camel.learnings.data.domain.Customers;

/**
 * 
 * @author VenkaT
 *
 */
public class GetCustomersResponseProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Customers custs=new Customers();
		
		Address address = new Address();
		address.setCity("Pune");
		address.setLine1("XYZ");
		address.setState("MH");
		address.setZipCode("89787");
		
		CustomerType custType=new CustomerType();
		custType.setAddress(address);
		custType.setCustId("1");
		custType.setCustName("ABC");
		Customer customer = new Customer();
		customer.setCustomer(custType);
		
		custs.getCustomer().add(custType);
		custs.getCustomer().add(custType);
		exchange.getIn().setBody(custs);
	}

}
