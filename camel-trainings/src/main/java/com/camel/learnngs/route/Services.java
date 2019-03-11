package com.camel.learnngs.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import com.camel.learnings.data.domain.Customer;
import com.camel.learnngs.domain.Address;
import com.camel.learnngs.domain.Company;
import com.camel.learnngs.exception.ValidationException;
import com.camel.learnngs.processor.GetCustomerResponseProcessor;
import com.camel.learnngs.processor.GetCustomersResponseProcessor;

public class Services extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		

		/**
		 * GET
		 * POST
		 * PUT
		 * DELETE
		 */
		
		onException(ValidationException.class)
			.handled(true)
			.log("Exception while processing the request.......${exception.stacktrace}")
			.setHeader(Exchange.CONTENT_TYPE).simple("text/html")
			.setHeader(Exchange.HTTP_RESPONSE_CODE).constant("400")
			.setBody().constant("Bad Request")
		.end();
		
		onException(Exception.class)
			.handled(true)
			.log("Exception while processing the request.......${exception.stacktrace}")
			.setHeader(Exchange.CONTENT_TYPE).simple("text/html")
			.setHeader(Exchange.HTTP_RESPONSE_CODE).constant("500")
			.setBody().constant("Internal server error")
		.end();
		
		restConfiguration().component("restlet").port("9091").host("localhost")
			.bindingMode(RestBindingMode.auto).dataFormatProperty("mustBeJAXBElement", "false")
			.endpointProperty("handlers", "securityHandler");
		
		rest("/cust-service")
			.get("/get-customer/{custId}")
				.to("direct:getCustomer")
			.get("/get-customers")
				.to("direct:getCustomers")
			.post("/register-customer").type(Customer.class)
				.to("direct:registerCustomer")
			.get("/get-company-details/{custId}")
				.to("direct:getCompanyDetails")
			;
		
		
		from("direct:getCustomer").routeId("getCustomer")
			.log("getCustomer operation invoked...")
			.process(new GetCustomerResponseProcessor())
			.log("Response back to client: ${body}")
		.end();
		
		from("direct:getCustomers").routeId("getCustomers")
			.log("getCustomers operation invoked...")
			.process(new GetCustomersResponseProcessor())
			.log("Response back to client for opoeraton: ${header.operationName}, : ${body}")
		.end();
		
		from("direct:registerCustomer").routeId("registerCustomer")
			.log("post registerCustomer operation invoked...")
			.setBody().simple("Succesfully register the customer....cust is : ${body.customer.custId}")
			.log("Response back to client for opoeraton: ${header.operationName}, : ${body}")
		.end();
		
		
		from("direct:getCompanyDetails")
			.log("Request received to get the compay details for custId ${header.custId}")
			.process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					Company company=new Company();
					company.setName("NTT");
					company.setId(exchange.getIn().getHeader("custId",String.class));
					Address address = new Address();
					address.setCity("HYD");
					company.setAddress(address);
					exchange.getIn().setBody(company);
					
				}
			})
			.log("Response process is done...");
	}

	
}
