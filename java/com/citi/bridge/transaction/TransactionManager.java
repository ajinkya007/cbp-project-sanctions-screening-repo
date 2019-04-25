/**
 * 
 */
package com.citi.bridge.transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lenovo
 *
 */
public class TransactionManager {

	final Logger logger = LoggerFactory.getLogger(TransactionManager.class);

	static TransactionManager t = null;

	public static TransactionManager getInstance() {
		if (t == null) {
			t = new TransactionManager();
		}
		return t;
	}

	ArrayList<Transaction> validTransactions = new ArrayList<>();
	ArrayList<Transaction> invalidTransactions = new ArrayList<>();

	public void readTransactions(String lines[]) {
		int flag = 0;
		for (int i = 0; i < lines.length; i++) {
			logger.info(lines[i]);

			String refNumber = lines[i].substring(0, 12);
			String date = lines[i].substring(12, 20);
			int firstIndex = lines[i].indexOf(' '), lastIndex, midIndex;
			midIndex = lines[i].substring(firstIndex + 1).indexOf(' ') + firstIndex + 1;
			lastIndex = lines[i].lastIndexOf(' ');
			String payerName = lines[i].substring(20, firstIndex);
			logger.info(payerName);
			String payerAccount = lines[i].substring(firstIndex + 1, firstIndex + 13);
			logger.info(payerAccount);
			String payeeName = lines[i].substring(firstIndex + 13, midIndex);
			logger.info(payeeName);
			String payeeAccount = lines[i].substring(midIndex + 1, midIndex + 13);
			logger.info(payeeAccount);
			double amount = Double.parseDouble(lines[i].substring(lastIndex + 1));

			logger.info(refNumber);
			if (uniqueReferenceId(refNumber)) {

				logger.info(date);
				if (presentDate(date)) {

					logger.info("Amount:" + amount);
					validTransactions.add(new Transaction(refNumber, date, payerName, payerAccount, payeeName,
							payeeAccount, amount, 1));
					flag = 1;

				}
			}

			if (flag == 0) {
				invalidTransactions.add(
						new Transaction(refNumber, date, payerName, payerAccount, payeeName, payeeAccount, amount, 0));
			}
			flag = 0;
		}
	}

	public ArrayList<Transaction> getValidTransaction() {
		return this.validTransactions;
	}

	public ArrayList<Transaction> getinvalidTransaction() {
		return this.invalidTransactions;
	}

	public boolean uniqueReferenceId(String refNumber) {
		for (int i = 0; i < validTransactions.size(); i++) {
			if (validTransactions.get(i).getRefNumber().equals(refNumber)) {
				return false;
			}
		}
		return true;
	}

	public boolean presentDate(String date) {
		logger.info(date);
		int daysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
		Calendar calobj = Calendar.getInstance();
		String s = df.format(calobj.getTime());
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(2, 4));
		int year = Integer.parseInt(date.substring(4));
		logger.info(day + "\t" + month + "\t" + year);
		// logger.info(Integer.parseInt(s.substring(4)));
		if (month <= 12 && year == Integer.parseInt(s.substring(4))) {
			if (year % 4 == 0) {
				if (year % 100 == 0) {
					if (year % 400 == 0)
						daysInMonth[1] = 29;
				} else
					daysInMonth[1] = 29;
			}

			if (day <= daysInMonth[month - 1])
				return true;
		}
		return false;

	}

}