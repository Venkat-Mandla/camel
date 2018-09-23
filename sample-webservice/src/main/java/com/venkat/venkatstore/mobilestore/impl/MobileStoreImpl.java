package com.venkat.venkatstore.mobilestore.impl;


import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.venkat.venkatstore.types.mobilestore.BuyNewMobielRequest;
import com.venkat.venkatstore.types.mobilestore.BuyNewMobielResponse;


/**
 * 
 * @author VenkaT
 *
 */
public class MobileStoreImpl implements Processor {

	
	
	
	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getOut().setBody(buyNewMobile(exchange.getIn().getBody(BuyNewMobielRequest.class)));
		System.out.println("response prepared");
	}

	public BuyNewMobielResponse buyNewMobile(BuyNewMobielRequest request) {
		
		BuyNewMobielResponse response=new BuyNewMobielResponse();
		response.setAddress(request.getAddress());
		response.setMobileColor(request.getMobileColor());
		response.setMobileModel("");
		response.setMobileName(null);
		response.setPrice(new Random(500).nextInt());
		response.setReponseMessage("Your request processed successfully\nWe will deliver your Mobile to mentioned Address");
		return response;
	}
	
	

    
}
