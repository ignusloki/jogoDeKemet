package com.felipe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OperarCSV {

	String csvFile = "D:\\Kemet\\DIs.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	public String[] carregarCSV(String[] DIs) {

		try {

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			DIs = line.split(cvsSplitBy);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
		/*
		 * for (int i = 0; i < DIs.length; i++) {
		 * 
		 * System.out.println(DIs[i]); }
		 */
		return DIs;
	}

	public void escreverNumCSV(int[] DIs) throws IOException {

		FileWriter fw = new FileWriter(csvFile, true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(String.valueOf("," + DIs[0]));
		bw.write(String.valueOf("," + DIs[1]));
		bw.write(String.valueOf("," + DIs[2]));

		bw.flush();
		bw.close();
	}

}