package com.camel.learnngs.domain;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Link;

@Link
public class Address {
	
	@DataField(pos=5)
	private String lineOne;
	@DataField(pos=6)
	private String city;
	@DataField(pos=7)
	private String state;
	@DataField(pos=8)
	private String zipCode;
	
	public String getLineOne() {
		return lineOne;
	}
	public void setLineOne(String lineOne) {
		this.lineOne = lineOne;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "Address [lineOne=" + lineOne + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + "]";
	}
}
