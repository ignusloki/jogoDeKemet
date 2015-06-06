package com.felipe;

import java.io.IOException;
import java.util.Random;

public class RandomDI {

	static String[] historicoCSV;
	static int[] numeros;

	public static int[] gerarDI() {

		int numeroDI[] = new int[3];

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		numeroDI[0] = rand.nextInt((35 - 1) + 1) + 1;
		numeroDI[1] = rand.nextInt((35 - 1) + 1) + 1;

		while (numeroDI[0] == numeroDI[1]) {
			numeroDI[1] = rand.nextInt((35 - 1) + 1) + 1;
		}

		numeroDI[2] = rand.nextInt((35 - 1) + 1) + 1;

		while (numeroDI[0] == numeroDI[2] || numeroDI[1] == numeroDI[2]) {
			numeroDI[2] = rand.nextInt((35 - 1) + 1) + 1;
		}

		// System.out.println(numeroDI[0] + " - " + numeroDI[1] + " - "
		// + numeroDI[2]);

		return numeroDI;

	}

	public static int[] validarDI() throws IOException {

		int numeroDIs[] = new int[3];
		boolean procurarDI = true;
		boolean DIDuplicada = true;

		// Carrega o csv na memoria
		OperarCSV csv = new OperarCSV();
		historicoCSV = csv.carregarCSV(historicoCSV);

		while (procurarDI) {

			// Gera os numeros
			numeroDIs = gerarDI();
			DIDuplicada = compareArrays(numeroDIs, historicoCSV);

			if (DIDuplicada) {
				procurarDI = true;
			} else {
				procurarDI = false;
			}

		}
		return numeroDIs;
	}

	public static boolean compareArrays(int[] array1, String[] array2) {

		boolean DIDuplicada = false;

		for (int i = 0; i < array2.length; i++) {

			for (int k = 0; k < array1.length; k++) {

				if (Integer.valueOf(array2[i]) == array1[k]) {
					DIDuplicada = true;
				}
			}
		}

		return DIDuplicada;

	}
}
