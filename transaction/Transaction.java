package com.citi.bridge.transaction;

import java.io.Serializable;

import com.citi.bridge.constants.Status;

public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1954845403106985758L;
	private String refNumber;
	private String date;
	private String payerName;
	private String payerAccount;
	private String payeeName;
	private String payeeAccount;
	private double amount;
	
	private Status status = Status.Uploading;

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	Transaction(String refNumber, String date, String payerName, String payerAccount, String payeeName,
			String payeeAccount, double amount, int status) {
		this.refNumber = refNumber;
		this.date = date;
		this.payerName = payerName;
		this.payerAccount = payerAccount;
		this.payeeName = payeeName;
		this.payeeAccount = payeeAccount;
		this.amount = amount;
		if (status == 1) {
			this.status = Status.Validate_Pass;
		} else if (status == 0) {
			this.status = Status.Validate_Fail;
		}
	}

	public String toString() {
		return refNumber + " " + date + " " + payerName + " " + payeeAccount + " " + payeeName + " " + payeeAccount
				+ " " + amount + " " + status + " ";
	}

}