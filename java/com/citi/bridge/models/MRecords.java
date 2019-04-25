package com.citi.bridge.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="maliciousRecords")
@JsonIgnoreProperties(allowGetters = true)
public class MRecords {
	
	String payerName;
	String payeeName;

	
	public String getPayerName() {
		return payerName;
	}
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	@Override
	public String toString() {
		return "MFiles [payerName=" + payerName + ", payeeName=" + payeeName + "]";
	}
	

}
