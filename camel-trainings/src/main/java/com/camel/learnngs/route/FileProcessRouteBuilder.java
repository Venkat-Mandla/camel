package com.camel.learnngs.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

import com.camel.learnngs.domain.Employee;

public class FileProcessRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		BindyCsvDataFormat empBindyCsvDataFormat=new BindyCsvDataFormat(Employee.class);
		
		
		from("file:work/data/csv?noop=true")
			.log("Received fle : ${file:name}")
			.unmarshal().csv()
			.log("File data convered to java obect ${body}")
			//.process(new DataProcessor())
			.split().body()
				.log("Split data: ${body}")
				.to("direct:processRecord")
			.end()
			.log("end of the split")
		.end();
		
		from("direct:processRecord")
			.log("processRecord route with body: ${body}")
			.split().body()
				.log("Feild data: ${body}")
			.end()
		.end();
		
		
		
		from("file:work/data/csv-bindy?noop=true")
		.log("Received fle : ${file:path}")
		.unmarshal(empBindyCsvDataFormat)
		.log("File data convered to java obect ${body}")
		//.to("direct:splitAndProcessRecords")
		.to("direct:delegateRecordsToMultipleEndpoints")
	.end();
	
		
	from("direct:splitAndProcessRecords")
		.split().body().parallelProcessing(true)
			.log("Split data: ${body}")
			.to("direct:processEmpRecord")
		.end()
		.log("end of the split")
	.end();
	
	from("direct:delegateRecordsToMultipleEndpoints")
		.log("Before multicast: ${body}")
		.multicast().parallelProcessing(true)
			.to("direct:processSetOne")
			.to("direct:processSetTwo")
		.end()
		.log("end of the multicast..")
	.end();
	
	from("direct:processSetOne")
		.log("In setone process: ${body}")
	.end();
	
	from("direct:processSetTwo")
		.log("In settwo process: ${body}")
	.end();
		
	from("direct:processEmpRecord")
		.log("processEmpRecord route with body: ${body}")
		.log("Employee data: EmpId: ${body.id}, EmpName: ${body.name}, JOB: ${body.doj}")
		.choice()
			.when().simple("${body.company} == 'NTT'")
				.log("NTT company employeee data received....")
				.to("direct:processNTTEmployeeData")
			.otherwise()
				.log("Skipping record processing (Not NTT company employeee data}....")
		.end()
	.end();
	
	from("direct:processNTTEmployeeData")
		.log("Employee data received to process")
	.end();
	
		
	}
}
