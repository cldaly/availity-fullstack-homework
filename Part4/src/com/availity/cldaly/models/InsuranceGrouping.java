package com.availity.cldaly.models;

import java.util.List;

import static com.availity.cldaly.util.Constants.CSV_SEPARATOR;

public class InsuranceGrouping {
	private String insuranceCompany;
	private String[] headers;
	private List<EnrolledUser> enrolees;

	public InsuranceGrouping() {}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public List<EnrolledUser> getEnrolees() {
		return enrolees;
	}

	public void setEnrolees(List<EnrolledUser> enrolees) {
		this.enrolees = enrolees;
	}

	public String printHeaders() {
		StringBuilder str = new StringBuilder();
		for (String col : headers) {
			str.append(col).append(CSV_SEPARATOR);
		}
		str.replace(str.length() -1, str.length(), System.lineSeparator());
		return str.toString();
	}
}
