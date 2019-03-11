package com.camel.learnngs.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.camel.learnings.data.domain.Address;
import com.camel.learnings.data.domain.Customer;
import com.camel.learnings.data.domain.CustomerType;

/**
 * 
 * @author VenkaT
 *
 */
public class GetCustomerResponseProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Address address = new Address();
		address.setCity("Pune");
		address.setLine1("XYZ");
		address.setState("MH");
		address.setZipCode("89787");
		
		CustomerType custType=new CustomerType();
		custType.setAddress(address);
		String custId = exchange.getIn().getHeader("custId",String.class);
		custType.setCustId(custId);
		custType.setCustName("ABC");
		Customer customer = new Customer();
		customer.setCustomer(custType);
		exchange.getIn().setBody(customer);
	}

}
