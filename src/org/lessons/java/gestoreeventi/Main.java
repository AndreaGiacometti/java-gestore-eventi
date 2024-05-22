package org.lessons.java.gestoreeventi;

import java.time.LocalDate;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci i dettagli del nuovo evento:");

//		CHIEDO ALL'UTENTE DI INSERIRE UN TITOLO
		System.out.println("Titolo: ");
		String titolo = scanner.nextLine();

//		CHIEDO ALL'UTENTE DI INSERIRE UNA DATA E LA VALIDO (DEVE ESSERE UN NUMERO E DEVE ESSERE NEL FORMATO GIUSTO)
//		IMPOSTO LA VARIABILE DATA ALL'ESTERNO DEL CICLO WHILE PER POTERLA RICHIAMARE NELLA COSTRUZIONE DELL'OGGETTO
		LocalDate data = null;
//		IMPOSTO UNA VARIABILE BOOLEANA (RESTERA' LA STESSA PER TUTTI I CICLI WHILE)
		boolean success = false;
		while (!success) {
			System.out.print("Data (formato yyyy-MM-dd): ");
			String dataString = scanner.nextLine();
//			NEL BLOCCO TRY CONVERTO LA DATA DA STRINGA A LOCALDATE, SE L'OPERAZIONE RIESCE ESCO DAL CICLO
			try {
				data = LocalDate.parse(dataString);
				success = true;
//			SE LA DATA NON VIENE INSERITA CORRETTAMENTE SI CREA UN'ECCEZZIONE CHE GENERA UN MESSAGGIO DI ERRORE E SI RIPETE IL CICLO
			} catch (java.time.format.DateTimeParseException e) {
				System.out
						.println("Formato data non valido. Assicurati di utilizzare il formato corretto (yyyy-MM-dd).");
			}
		}

//		CHIEDO ALL'UTENTE DI INSERIRE UN NUMERO DI POSTI PER L'EVENTO (DEVE ESSERE UN NUMERO INTERO)
//		IMPOSTO LA VARIABILE ALL'ESTERNO DEL CICLO WHILE PER POTERLA RICHIAMARE NELLA COSTRUZIONE DELL'OGGETTO
		int postiTotali = 0;
//		REIMPOSTO A FALSE LA VARIABILE BOOLEANA
		success = false;
		while (!success) {
			System.out.println("Numero di posti totali: ");
//			SE L'UTENTE INSERISCE UN NUMERO ENTRO NELL'IF
			if (scanner.hasNextInt()) {
//				ASSEGNO IL NUMERO INSERITO ALLA VARIABILE
				postiTotali = scanner.nextInt();
//				SE IL NUMERO E' >= 0 ESCO DAL CICLO
				if (postiTotali >= 0) {
					success = true;
//				ALTRIMENTI AVVISO DI INSERIRE UN NUMERO VALIDO E RIPETO IL CICLO
				} else {
					System.out.println("Inserisci un numero intero positivo.");
				}
//			ALTRIMENTI AVVISO DI INSERIRE UN NUMERO VALIDO E RIPETO IL CICLO
			} else {
				System.out.println("Inserisci un numero intero positivo.");
				scanner.next();
			}
		}

		Evento evento1 = new Evento(titolo, data, postiTotali);

//		STAMPO IL toString CON TITOLO EVENTO E DATA FORMATTATA
		System.out.println("");
		System.out.println(evento1);
		System.out.println("");

//		CHIEDO ALL'UTENTE QUANTI POSTI VUOLE PRENOTARE CON LE STESSE MODALITA' (IF PER NUMERO INTERO E POSITIVO)
		success = false;
		while (!success) {
			try {
				System.out.print("Quanti posti vuoi prenotare? ");
				if (scanner.hasNextInt()) {
					int prenotazioni = scanner.nextInt();
					if (prenotazioni >= 0) {
//						SE LE CONDIZIONI SONO VERE IMPLEMENTO IL METODO PRENOTA
						for (int i = 0; i < prenotazioni; i++) {
							evento1.prenota();
						}
						success = true;
					} else {
						System.out.println("Inserisci un numero intero positivo.");
					}
				} else {
					System.out.println("Inserisci un numero intero positivo.");
					scanner.next();
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				evento1.resetPostiPrenotati();
			}
		}

//		FACCIO LO STESSO PER IL METODO DISDICI
		success = false;
		int disdette = 0;
		while (!success) {
			try {
				System.out.print(
						"Se vuoi disdire dei posti prenotati digita il numero di posti da disdire, altrimenti digita 0 ");
				if (scanner.hasNextInt()) {
					disdette = scanner.nextInt();
					if (disdette >= 0) {
						for (int i = 0; i < disdette; i++) {
							evento1.disdici();
						}
						success = true;
					} else {
						System.out.println("Inserisci un numero intero positivo.");
					}
				} else {
					System.out.println("Inserisci un numero intero positivo.");
					scanner.next();
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				evento1.resetPostiPrenotati();
			}
		}

//		CHIUDO LO SCANNER
		scanner.close();

//		STAMPO A VIDEO I RISULTATI DEGLI INPUT
		System.out.println("");
		System.out.println("Posti prenotati: " + evento1.getPostiPrenotati());
		System.out.println("Posti totali: " + evento1.getPostiTotali());
		System.out.println("Posti disponibili: " + evento1.getPostiDisponibili());
		System.out.println("Posti disdetti: " + disdette);
	}

}
