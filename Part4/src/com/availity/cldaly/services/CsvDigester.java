package com.availity.cldaly.services;

import com.availity.cldaly.models.EnrolledUser;
import com.availity.cldaly.models.InsuranceGrouping;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.availity.cldaly.util.Constants.CSV_SEPARATOR;

public class CsvDigester {
	private static String[] headers;

	public static List<InsuranceGrouping> getInsuranceGroupsFromCSV(File file) throws IOException {
		List<InsuranceGrouping> list = new ArrayList<>();
		Map<String, List<EnrolledUser>> map = getInsuranceGroupUsersMap(file);
		for (String key : map.keySet()) {
			InsuranceGrouping insGrp = new InsuranceGrouping();
			insGrp.setInsuranceCompany(key);
			insGrp.setHeaders(headers);
			insGrp.setEnrolees(map.get(key));
			list.add(insGrp);
		}
		return list;
	}

	private static Map<String, List<EnrolledUser>> getInsuranceGroupUsersMap(File file) throws IOException {
		Map<String, List<EnrolledUser>> map = new HashMap<>();
		Reader reader = new FileReader(file.getAbsoluteFile());
		BufferedReader br = new BufferedReader(reader);

		headers = null;
		String line;

		while ((line = br.readLine()) != null) {
			if (headers == null) {
				headers = line.split(CSV_SEPARATOR);
				continue;
			}
			String[] values = line.split(CSV_SEPARATOR);
			EnrolledUser user = new EnrolledUser();
			for (int i = 0; i < headers.length; i++) {
				switch (headers[i]) {
					case "user_id":
						user.setUserId(values[i]);
						break;
					case "first_name":
						user.setFirstName(values[i]);
						break;
					case "last_name":
						user.setLastName(values[i]);
						break;
					case "version":
						user.setVersion(Integer.parseInt(values[i]));
						break;
					case "insurance_company":
						user.setInsuranceCompany(values[i]);
						break;
				}
			}
			List<EnrolledUser> list;
			if (!map.containsKey(user.getInsuranceCompany())) {
				list = new ArrayList<>();
			} else {
				list = map.get(user.getInsuranceCompany());
			}
			list.add(user);
			map.put(user.getInsuranceCompany(), list);
		}
		return map;
	}
}
