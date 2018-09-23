/**
 * 
 */
package com.venkat.camel.pollenrich;

import org.apache.camel.Exchange;
import org.apache.camel.PollingConsumer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileComponent;
import org.apache.camel.component.file.FileEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Venkat
 *
 */
public class PollerEnrichTest extends CamelTestSupport{

	private static final Logger LOGGER=LoggerFactory.getLogger(PollerEnrichTest.class);
	
	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
	
				from("direct:start")
					.routeId("fileComponentTestRoute")
					.log("File name: ${header.CamelFileNameOnly}");
			}
		};
	}
	
	@Test
	public void testJpaEndpoint() throws Exception{
		FileComponent fileComponent=new FileComponent(context());
		FileEndpoint fileEndpoint=(FileEndpoint) fileComponent.createEndpoint("src/test/resources/input/vr");
		PollingConsumer pollingConsumer=fileEndpoint.createPollingConsumer();
		pollingConsumer.start();
		Exchange exchange;
		
		do{
			exchange=pollingConsumer.receive();
			LOGGER.info("File poll: 1+"+exchange.getProperty("CamelBatchIndex"));
			template.sendBodyAndHeaders("direct:start",exchange,exchange.getIn().getHeaders());
		}while( null != exchange && !exchange.getProperty("CamelBatchComplete", Boolean.class));
	}
	
	
	

}
