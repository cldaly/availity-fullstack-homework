package com.availity.cldaly.models;

import static com.availity.cldaly.util.Constants.CSV_SEPARATOR;

public class EnrolledUser {
	private String userId;
	private String firstName;
	private String lastName;
	private Integer version;
	private String insuranceCompany;

	public EnrolledUser() {}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	@Override
	public String toString() {
		return '\'' + userId + "', '" + firstName + "', '" + lastName + "', " + version + ", '" + insuranceCompany + '\'';
	}

	public String printAsCsv() {
		return userId + CSV_SEPARATOR +
				firstName + CSV_SEPARATOR +
				lastName + CSV_SEPARATOR +
				version + CSV_SEPARATOR +
				insuranceCompany + System.lineSeparator();
	}
}
