package com.felipe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OperarCSV {

	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	public String[] carregarCSV(String[] arrayDeRetorno, String caminhoDoArquivo) {

		try {

			br = new BufferedReader(new FileReader(caminhoDoArquivo));
			line = br.readLine();
			arrayDeRetorno = line.split(cvsSplitBy);

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

		System.out.println("Dados carrgados com sucesso");

		/*
		 * for (int i = 0; i < arrayDeRetorno.length; i++) {
		 * System.out.println(arrayDeRetorno[i]); }
		 */

		return arrayDeRetorno;
	}

	public void escreverNumCSV(int[] DIs, String caminhoDoArquivo)
			throws IOException {

		FileWriter fw = new FileWriter(caminhoDoArquivo, true);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < DIs.length; i++) {
			// System.out.println(DIs[i] + " - para serem gravados no CSV");
			bw.write(String.valueOf("," + DIs[i]));
		}

		bw.flush();
		bw.close();
	}

}