package com.availity.cldaly.services;

import com.availity.cldaly.models.EnrolledUser;
import com.availity.cldaly.models.InsuranceGrouping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.*;

public class EnrollmentDataProcessor {

	public static void processInsuranceGroup(File origin, List<InsuranceGrouping> insGroups) throws IOException {
		for (InsuranceGrouping group : insGroups) {
			group.setEnrolees(sortAndCleanEnrolleesForGroup(group.getEnrolees()));
			writeGroupToFile(origin, group);
		}
	}

	private static List<EnrolledUser> sortAndCleanEnrolleesForGroup(List<EnrolledUser> users) {
		if (users == null || users.size() == 0) return users;

		users = new ArrayList<>(users.stream()
				.collect(groupingBy(EnrolledUser::getUserId,
						collectingAndThen(maxBy(Comparator.comparing(EnrolledUser::getVersion)),
								user -> user.orElse(null)))).values());
		users.sort((u1, u2) -> {
			String fn1 = u1.getLastName() + ", " + u1.getFirstName();
			String fn2 = u2.getLastName() + ", " + u2.getFirstName();
			return fn1.equals(fn2) ? Math.max(u1.getVersion(), u2.getVersion()) : fn1.compareTo(fn2);
		});
		return users;
	}

	private static void writeGroupToFile(File origin, InsuranceGrouping group) {
		String dir = origin.getParentFile().getAbsolutePath()
				.substring(0, origin.getParentFile().getAbsolutePath().lastIndexOf('/'))
				+ "/output-files/" + origin.getName().substring(0, origin.getName().lastIndexOf('.')) + "/";
		File f = new File(dir + group.getInsuranceCompany().replaceAll(" ", "_") + ".csv");

		try {
			if (f.getParentFile().mkdirs()) System.out.println("  Processing: " + origin.getName() + "\n  Writing to: /" + dir + f.getName());
			BufferedWriter writer = new BufferedWriter(new FileWriter(f.getAbsoluteFile()));
			writer.append(group.printHeaders());
			for (EnrolledUser user : group.getEnrolees()) writer.append(user.printAsCsv());
			writer.close();
		} catch (IOException e) {
			System.out.println("Exception while writing:: " + group);
			e.printStackTrace();
		}
	}
}
