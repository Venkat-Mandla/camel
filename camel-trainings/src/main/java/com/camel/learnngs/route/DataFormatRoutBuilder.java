package com.camel.learnngs.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.springframework.stereotype.Component;

import com.camel.learnngs.domain.Employee;

@Component
public class DataFormatRoutBuilder extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		
		JacksonDataFormat jacksonDataFormat=new JacksonDataFormat(Employee.class);
		jacksonDataFormat.setPrettyPrint(true);
		
		JaxbDataFormat jaxbDataFormat=new JaxbDataFormat(true);
		jaxbDataFormat.setContextPath("com.camel.learnings.data.domain");
		
		from("direct:convertObjToJson").routeId("convertObjToJson")
			.marshal(jacksonDataFormat)
			.log("Employee json: ${body}")
			.to("file:work/data/json?fileName=employee-${date:now:mm-dd-YYYY mm.ss.sss}.json")
		.end();
		
		
		from("direct:convertJsonToObj").routeId("convertJsonToObj")
			.unmarshal(jacksonDataFormat)
			.log("Employee obj: ${body}")
		.end();
		
		from("direct:convertObjToXml").routeId("convertObjToXml")
			.marshal(jaxbDataFormat)
			.log("Customer xml: ${body}")
			.to("file:work/data/xml?fileName=customer-${date:now:mm-dd-YYYY mm.ss.sss}.xml")
		.end();
	
		
		from("direct:convertXmlToObj").routeId("convertXmlToObj")
			.log("Customer xml: ${body}")
			.unmarshal(jaxbDataFormat)
			.log("After unmarshal....${body}")
			.choice()
				.when().simple("${body.customer.custId} == '123'")
					.log("Cust id is valid")
					.inOnly("seda:processCustomer")
					.log("Request given to seda component to process cust data")
				.otherwise()
					.log("Cust id is not valid")
			.end()
			.log("Goning sleep for sometime....")
			.log("Am awak up....")
		.end();
		
		from("seda:processCustomer")
			.log("Cust data received to process")
			.delay(12000)
			.marshal(jacksonDataFormat)
			.log("Cust json: ${body}")
		.end();
		
	}

	
}
