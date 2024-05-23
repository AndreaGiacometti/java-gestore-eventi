package org.lessons.java.gestoreeventi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

//		CHIEDO ALL'UTENTE SE VUOLE CREARE UN EVENTO O UN CONCERTO
		boolean success = false;
		while (!success) {
			System.out.println("Vuoi creare un evento generico o un concerto? (e/c)");
			String scelta = scanner.nextLine();
//			SE L'UTENTE SCEGLI E CREO UN OGGETTO EVENTO CON IL COSTRUTTORE CREAEVENTO CHE VERRA' IMPOSTATO SUCCESSIVAMENTE CON UNA SERIE DI INPUT DA SCANNER
			if (scelta.equalsIgnoreCase("e")) {
				Evento evento = creaEvento(scanner);
				gestisciPrenotazioni(scanner, evento);
				success = true;
//			SE L'UTENTE SCEGLIE C CREO UN OGGETTO CONCERTO CHE VERRA' IMPOSTATO SUCCESSIVAMENTE CON UNA SERIE DI INPUT DA SCANNER
			} else if (scelta.equalsIgnoreCase("c")) {
				Concerto concerto = creaConcerto(scanner);
				gestisciPrenotazioni(scanner, concerto);
				success = true;
			} else {
				System.out.println("Scelta non valida.");
			}
		}
	}

//	IMPLEMENTO IL METODO creaEvento
	private static Evento creaEvento(Scanner scanner) {
//		CHIEDO ALL'UTENTE DI DARE UN TITOLO ALL'EVENTO E LO ASSOCIO VARIABILE titolo
		System.out.println("Inserisci i dettagli del nuovo evento:");

		System.out.print("Titolo: ");
		String titolo = scanner.nextLine();

//		ASSOCIO ALLE ISTANZE DELLA CLASSE Evento I RELATIVI METODI STATICI PER CREARE L'EVENTO CHE VERRANNO IMPLEMENTATI SUCCESSIVAMENTE
		LocalDate data = leggiData(scanner);

		int postiTotali = leggiPostiTotali(scanner);
		
//		CREO L'OGGETTO evento INVOCANDO IL COSTRUTTORE
		Evento evento = new Evento(titolo, data, postiTotali);
		
//		STAMPO A VIDEO IL METODO toString DELLA CLASSE Evento
		System.out.println("");
		System.out.println(evento);
		System.out.println("");
		return evento;
	}

//	IMPLEMENTO IL METODO creaConcerto
	private static Concerto creaConcerto(Scanner scanner) {
//		CHIEDO ALL'UTENTE DI DARE UN TITOLO AL CONCERTO E LO ASSOCIO VARIABILE titolo
		System.out.println("Inserisci i dettagli del nuovo concerto:");

		System.out.print("Titolo: ");
		String titolo = scanner.nextLine();
		
//		ASSOCIO ALLE ISTANZE DELLA CLASSE Concerto I RELATIVI METODI STATICI PER CREARE L'EVENTO CHE VERRANNO IMPLEMENTATI SUCCESSIVAMENTE
		LocalDate data = leggiData(scanner);

		int postiTotali = leggiPostiTotali(scanner);

		scanner.nextLine();

		LocalTime ora = leggiOra(scanner);

		double prezzoBiglietto = leggiPrezzoBiglietto(scanner);

//		CREO L'OGGETTO concerto INVOCANDO IL COSTRUTTORE
		Concerto concerto = new Concerto(titolo, data, postiTotali, ora, prezzoBiglietto);
		
//		STAMPO A VIDEO IL METODO toString DELLA CLASSE Concerto
		System.out.println("");
		System.out.println(concerto);
		System.out.println("");
		return concerto;
	}

//	IMPLEMENTO I METODI STATICI CHE HO ASSOCIATO ALLE ISTRANZE DELLE CLASSI
	private static LocalDate leggiData(Scanner scanner) {
		LocalDate data = null;
		boolean success = false;
		while (!success) {
			System.out.print("Data (formato yyyy-MM-dd): ");
			String dataString = scanner.nextLine();
			try {
//				TRASFORMO LA STRINGA IN UN FORMATO LocalDate
				data = LocalDate.parse(dataString);
//				CONTROLLO CHE LA DATA NON SIA PRECEDENTE ALLA DATA DI OGGI
				if (data.isBefore(LocalDate.now())) {
					System.out.println("L'evento deve svolgersi in una data futura. Riprova.");
				} else {
					success = true;
				}
//				SE VIENE INSERITO UN FORMATO DIVERSO DA QUELLO ISO VIENE RICHIAMATO UN ALERT
			} catch (java.time.format.DateTimeParseException e) {
				System.out.println("Formato data non valido. Assicurati di utilizzare il formato corretto (yyyy-MM-dd).");
			}
		}
		return data;
	}

	private static int leggiPostiTotali(Scanner scanner) {
		int postiTotali = 0;
		boolean success = false;
		while (!success) {
			System.out.println("Numero di posti totali: ");
//			VERIFICO CHE IL NUMERO SIA INTERO
			if (scanner.hasNextInt()) {
				postiTotali = scanner.nextInt();
//				VERIFICO CHE IL NUMERO SIA MAGGIORE DI 0
				if (postiTotali > 0) {
					success = true;
				} else {
					System.out.println("Inserisci un numero intero positivo.");
				}
			} else {
				System.out.println("Inserisci un numero intero valido.");
				scanner.next();
			}
		}
		return postiTotali;
	}

	private static LocalTime leggiOra(Scanner scanner) {
		LocalTime ora = null;
		boolean success = false;
		while (!success) {
			System.out.print("Ora (formato HH:mm): ");
			String oraString = scanner.nextLine();
			try {
//				TRASFORMO LA STRINGA IN UN FORMATO LocalTime
				ora = LocalTime.parse(oraString);
				success = true;
//				SE VIENE INSERITO UN FORMATO DIVERSO DA QUELLO ISO SI VERIFICA UN'ECCEZIONE CHE RICHIAMA UN ALERT
			} catch (java.time.format.DateTimeParseException e) {
				System.out.println("Formato ora non valido. Assicurati di utilizzare il formato corretto (HH:mm).");
			}
		}
		return ora;
	}

	private static double leggiPrezzoBiglietto(Scanner scanner) {
		double prezzoBiglietto = 0;
		boolean success = false;
		while (!success) {
			System.out.print("Prezzo del biglietto: ");
//			CONTROLLO CHE VENGA INSERITO UN NUMERO INTERO O CON LA VIRGOLA
			if (scanner.hasNextDouble()) {
				prezzoBiglietto = scanner.nextDouble();
//				CONTROLLO CHE IL NUMERO SIA MAGGIORE DI 0
				if (prezzoBiglietto > 0) {
					success = true;
				} else {
					System.out.println("Inserisci un prezzo maggiore di 0.");
				}
			} else {
				System.out.println("Inserisci un valore numerico valido.");
				scanner.next();
			}
		}
		return prezzoBiglietto;
	}
	
//	IMPLEMENTO IL METODO PER GESTIRE LE PRENOTAZIONI E LE DISDETTE
	private static void gestisciPrenotazioni(Scanner scanner, Evento evento) {
		boolean success = false;
		while (!success) {
			try {
				System.out.print("Quanti posti vuoi prenotare? ");
//				CONTROLLO CHE VENGA INSERITO UN NUMERO INTERO
				if (scanner.hasNextInt()) {
					int prenotazioni = scanner.nextInt();
//					CONTROLLO CHE NON VENGA INSERITO UN NUMERO NEGATIVO
					if (prenotazioni >= 0) {
						for (int i = 0; i < prenotazioni; i++) {
							evento.prenota();
						}
						success = true;
					} else {
						System.out.println("Inserisci un numero intero positivo.");
					}
				} else {
					System.out.println("Inserisci un numero intero positivo.");
					scanner.next();
				}
//				SE I POSTI PRENOTATI SONO MAGGIORI DEI POSTI TOTALI SI VERIFICA UN'ECCEZIONE CHE RICHIAMA UN ALERT
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				evento.resetPostiPrenotati();
			}
		}

		success = false;
		int disdette = 0;
		while (!success) {
			try {
				System.out.print(
						"Se vuoi disdire dei posti prenotati digita il numero di posti da disdire, altrimenti digita 0: ");
//				CONTROLLO CHE VENGA INSERITO UN NUMERO INTERO
				if (scanner.hasNextInt()) {
					disdette = scanner.nextInt();
//					CONTROLLO CHE NON VENGA INSERITO UN NUMERO NEGATIVO
					if (disdette >= 0) {
//						CONTROLLO SE CI SONO ABBASTANZA POSTI DA DISDIRE
						if (disdette <= evento.getPostiPrenotati()) {
							for (int i = 0; i < disdette; i++) {
								evento.disdici();
							}
							success = true;
//							SE LE DISDETTE SONO PIU' DEI POSTI PRENOTATI
						} else {
							System.out.println(
									"Non puoi disdire più posti di quanti ne siano prenotati. Posti prenotati attuali: "
											+ evento.getPostiPrenotati());
						}
//						SE VIENE INSERITO UN NUMERO NEGATIVO
					} else {
						System.out.println("Inserisci un numero intero positivo.");
					}
//					SE VIENE INSERITO QUALCOSA CHE NON SIA UN NUMERO
				} else {
					System.out.println("Inserisci un numero intero positivo.");
					scanner.next(); // Consuma l'input non valido
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

//		CHIUDO LO SCANNER
		scanner.close();

//		STAMPO A VIDEO I RISULTATI DEGLI INPUT
		System.out.println("");
		System.out.println("Posti prenotati: " + evento.getPostiPrenotati());
		System.out.println("Posti totali: " + evento.getPostiTotali());
		System.out.println("Posti disponibili: " + evento.getPostiDisponibili());
		System.out.println("Posti disdetti: " + disdette);
		System.out.println("");
		System.out.println("Grazie per la tua prenotazione!");
	}

}
