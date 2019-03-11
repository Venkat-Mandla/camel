package com.camel.learnngs.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Link;

@CsvRecord(separator=",",skipFirstLine=true)
public class Employee {
	
	@DataField(pos=1)
	private String id;
	@DataField(pos=2)
	private String name;
	@DataField(pos=3)
	private String doj;
	@DataField(pos=4)
	private String company;
	@Link
	private Address address;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", doj=" + doj + ", company=" + company + ", address="
				+ address + "]";
	}
}
