package com.camel.learning.route.test;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camel.learnings.data.domain.Customer;
import com.camel.learnings.data.domain.CustomerType;
import com.camel.learnngs.domain.Address;
import com.camel.learnngs.domain.Employee;
import com.camel.learnngs.route.DataFormatRoutBuilder;

public class CamelRoutesTest extends CamelTestSupport{

	private Logger LOG=LoggerFactory.getLogger(CamelRoutesTest.class);
	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new DataFormatRoutBuilder();
	}
	
	@Test
	public void testConvertObjToJsonRoute() {
		Employee emp = buildEmployee();
		String object=template.requestBody("direct:convertObjToJson", emp,String.class);
		Assert.assertNotNull(object);
		LOG.info("Results "+object);
	}

	private Employee buildEmployee() {
		Employee emp=new Employee();
		emp.setId("1");
		emp.setName("Amitha");
		emp.setCompany("NTT");
		emp.setDoj("03/03/2017");
		emp.setAddress(new Address());
		return emp;
	}
	
	@Test
	public void testConvertJsonToObjRoute() {
		Employee emp = buildEmployee();
		String json=template.requestBody("direct:convertObjToJson", emp,String.class);
		Assert.assertNotNull(json);
		LOG.info("Request:  "+json);
		Employee employeeResults=template.requestBody("direct:convertJsonToObj", json,Employee.class);
		assertEquals(emp.getName(), employeeResults.getName());
		LOG.info("Results "+employeeResults);
	}

	
	@Test
	public void testConvertObToXmlRoute() {
		Customer cust = buildCustomer();
		String xml=template.requestBody("direct:convertObjToXml", cust,String.class);
		Assert.assertNotNull(xml);
		LOG.info("Result:  "+xml);
	}
	
	
	@Test
	public void testConvertXmlToObjRoute() {
		Customer cust = buildCustomer();
		String xml=template.requestBody("direct:convertObjToXml", cust,String.class);
		Assert.assertNotNull(xml);
		LOG.info("Result:  "+xml);
		
		Customer custResponse=template.requestBody("direct:convertXmlToObj", xml,Customer.class);
		Assert.assertNotNull(custResponse);
		CustomerType customer = cust.getCustomer();
		assertEquals(customer.getCustId(), customer.getCustId());
		LOG.info("xml to obj results....");
	}

	private Customer buildCustomer() {
		CustomerType cust=new  CustomerType();
		cust.setCustId("123");
		cust.setCustName("abc");
		cust.setAddress(new com.camel.learnings.data.domain.Address());
		Customer customer=new Customer();
		customer.setCustomer(cust);
		return customer;
	}
	

	
}
