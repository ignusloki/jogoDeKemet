package com.felipe;

import java.io.IOException;
import java.util.Random;

public class RandomDI {

	static String[] historicoCSV;
	static int[] numeros;

	public static int[] gerarDI(int numeroDIsParaGerar) {

		int numeroDI[] = new int[numeroDIsParaGerar];
		boolean DIDuplicada = true;

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive

		while (DIDuplicada) {

			for (int i = 0; i < numeroDI.length; i++) {
				numeroDI[i] = rand.nextInt((35 - 1) + 1) + 1;
			}

			DIDuplicada = false;

			for (int i = 0; i < numeroDI.length; i++) {

				for (int k = 0; k < numeroDI.length; k++) {

					if (numeroDI[k] == numeroDI[i] && i != k) {
						DIDuplicada = true;
						System.out.println("Duplicada achada - re-gerando");
					}
					;
				}
				;
			}
			;
		}

		/*
		 * for (int i = 0; i < numeroDI.length; i++) {
		 * System.out.println(numeroDI[i] + " - gerada"); }
		 */

		return numeroDI;

	}

	public static int[] validarDI(String[] historicoCSVDI,
			int numeroDIsParaGerar) throws IOException {

		int numeroDIs[] = new int[numeroDIsParaGerar];
		boolean procurarDI = true;
		boolean DIDuplicada = true;

		while (procurarDI) {

			// Gera os numeros
			numeroDIs = gerarDI(numeroDIsParaGerar);
			DIDuplicada = compareArrays(numeroDIs, historicoCSVDI);

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
