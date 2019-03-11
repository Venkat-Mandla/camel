package com.camel.learnngs.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * 
 * @author VenkaT
 *
 */
public class DataProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
			System.out.println("Exchange body: "+exchange.getIn().getBody());
			
			@SuppressWarnings("unchecked")
			List<List<String>> csvData=exchange.getIn().getBody(List.class);
			
			for(int index=0; index<csvData.size();index++) {
				List<String> record=csvData.get(index);
				if(index==0) {
					System.out.println("Header info: "+record);
					continue;
				}
				
				System.out.println("Record data: "+record);
				System.out.println("Emp Id: "+record.get(0)+", JOB : "+record.get(2));
				
			}
			
	}

	
}
