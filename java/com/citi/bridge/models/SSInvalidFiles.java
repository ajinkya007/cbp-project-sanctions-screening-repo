package com.citi.bridge.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection = "invalidrecords")
@JsonIgnoreProperties(allowGetters = true)
public class SSInvalidFiles {

	@Id
	private String transactionRef;

	private String valueDate;

	private String payerName;
	private String payerAccount;
	private String payeeName;
	private String payeeAccount;
	private double amount;
	private String fileName;

	public String getTransactionRef() {
		return transactionRef;
	}

	public void setTransactionRef(String transactionRef) {
		this.transactionRef = transactionRef;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerAccount() {
		return payerAccount;
	}

	public void setPayerAccount(String payerAccount) {
		this.payerAccount = payerAccount;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "SSInvalidFiles [transactionRef=" + transactionRef + ", valueDate=" + valueDate + ", payerName="
				+ payerName + ", payerAccount=" + payerAccount + ", payeeName=" + payeeName + ", payeeAccount="
				+ payeeAccount + ", amount=" + amount + ", fileName=" + fileName + "]";
	}


}
