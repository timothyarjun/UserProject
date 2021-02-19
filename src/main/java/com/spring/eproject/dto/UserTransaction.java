package com.spring.eproject.dto;

import java.util.Date;

public class UserTransaction {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private double amount;
	
	private String note;
	
	private Date transactionDate;
	
	private long categoryId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "UserTransaction [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", amount=" + amount + ", note=" + note + ", transactionDate=" + transactionDate
				+ ", categoryId=" + categoryId + "]";
	}
	
	
}
