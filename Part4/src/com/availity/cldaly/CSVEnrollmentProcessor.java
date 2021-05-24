package com.availity.cldaly;

import com.availity.cldaly.models.InsuranceGrouping;
import com.availity.cldaly.services.CsvDigester;
import com.availity.cldaly.services.EnrollmentDataProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CSVEnrollmentProcessor {

	public static void main(String[] args) {
		File inputFolder = new File("input-files");
		File[] files = inputFolder.listFiles();

		if (files == null) {
			System.out.println("Please make sure you have the input csv files in the 'input-files' directory");
			System.out.println("The 'input-files should be located in root dir of project");
			return;
		}

		for (File file : files) {
			if (!isCsvFile(file.getName())) continue;
			System.out.println("Reading from: /" + file.getParentFile().getName() + "/" + file.getName());
			try {
				List<InsuranceGrouping> insGroups = CsvDigester.getInsuranceGroupsFromCSV(file);
				EnrollmentDataProcessor.processInsuranceGroup(file, insGroups);
			} catch (IOException e) {
				System.out.println("Exception in CSVEnrollmentProcessor while processing '" + file.getName() + "'");
				e.printStackTrace();
			}
		}
	}

	private static boolean isCsvFile(String filename) {
		String[] parts = filename.split("\\.");
		return parts.length >= 2 && parts[parts.length - 1].equals("csv");
	}
}
