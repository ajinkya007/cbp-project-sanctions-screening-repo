/**
 * 
 */
package com.citi.bridge.transaction;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Lenovo
 *
 */
public class TransactionManager {

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
			// System.out.println(lines[i]);

			String refNumber = lines[i].substring(0, 12);
			String date = lines[i].substring(12, 20);
			int firstIndex = lines[i].indexOf(' '), lastIndex, midIndex;
			midIndex = lines[i].substring(firstIndex + 1).indexOf(' ') + firstIndex + 1;
			lastIndex = lines[i].lastIndexOf(' ');
			String payerName = lines[i].substring(20, firstIndex);
			// System.out.println(payerName);
			String payerAccount = lines[i].substring(firstIndex + 1, firstIndex + 13);
			// System.out.println(payerAccount);
			String payeeName = lines[i].substring(firstIndex + 13, midIndex);
			// System.out.println(payeeName);
			String payeeAccount = lines[i].substring(midIndex + 1, midIndex + 13);
			// System.out.println(payeeAccount);
			double amount = Double.parseDouble(lines[i].substring(lastIndex + 1));

			// System.out.println(refNumber);
			if (uniqueReferenceId(refNumber)) {

				// System.out.println(date);
				if (presentDate(date)) {

					// System.out.println(amount);
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
		System.out.println(date);
		int daysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calobj = Calendar.getInstance();
		String s = df.format(calobj.getTime());
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(3, 5));
		int year = Integer.parseInt(date.substring(6));
		// System.out.println(day+"\t"+month+"\t"+year);
		// System.out.println(Integer.parseInt(s.substring(4)));
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

	public void writeValidatedRecords() {

		int sizeV = validTransactions.size();
		int sizeInvalid = invalidTransactions.size();
		System.out.println("In Write");
		try {

			PrintStream outValid = new PrintStream(new FileOutputStream("D:\\ValidatedRecords\\ValidRecords.txt"));
			PrintStream outInvalid = new PrintStream(new FileOutputStream("D:\\ValidatedRecords\\InvalidRecords.txt"));
			for (int i = 0; i < sizeV; i++) {
				outValid.print(validTransactions.get(i).toString());
				outValid.print("\n");
			}

			for (int i = 0; i < sizeInvalid; i++) {
				outInvalid.print(invalidTransactions.get(i).toString());
				outInvalid.print("\n");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}