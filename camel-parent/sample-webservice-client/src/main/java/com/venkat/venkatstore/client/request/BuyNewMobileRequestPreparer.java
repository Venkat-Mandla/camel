package com.venkat.venkatstore.client.request;

import org.apache.camel.Exchange;

import com.venkat.venkatstore.types.mobilestore.AccountDetails;
import com.venkat.venkatstore.types.mobilestore.Address;
import com.venkat.venkatstore.types.mobilestore.BuyNewMobielRequest;
import com.venkat.venkatstore.types.mobilestore.ObjectFactory;

/**
 * 
 * @author VenkaT
 *
 */
public class BuyNewMobileRequestPreparer {
	
	
	public BuyNewMobielRequest prepareNewMobileRequest(Exchange exchange){
		ObjectFactory factory=new ObjectFactory();
		BuyNewMobielRequest request=factory.createBuyNewMobielRequest();
		
		
		request.setMobileColor("black");
		request.setMobileModel("1233");
		request.setMobileName("MOTO");
		
		AccountDetails accountDetails=factory.createAccountDetails();
		accountDetails.setAccNumber("12344");
		accountDetails.setBankName("SBI");
		accountDetails.setBranch("Chinnamandem");
		accountDetails.setPinNumber(516214);
		
		
		request.setAccountDetails(accountDetails);
		
		
		Address address=factory.createAddress();
		
		address.setCity("Kadapa");
		address.setLine1("Kalibanda");
		address.setLine2("KLD");
		
		address.setPin(516216);
		
		address.setState("AP");
		request.setAddress(address);
		
		return request;
		
		
	}

}
