package org.lessons.java.gestoreeventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ProgrammaEventi {
	private String titolo;
	private List<Evento> eventi;

//	COSTRTTORE
	public ProgrammaEventi(String titolo) {
		this.setTitolo(titolo);
		this.eventi = new ArrayList<>();
	}

//	METODO PER AGGIUNGERE UN EVENTO
	public void aggiungiEvento(Evento evento) {
		eventi.add(evento);
	}

//	METODO PER CERCARE UN EVENTO IN UNA DATA SPECIFICA
	public List<Evento> eventiInData(LocalDate data) {
		List<Evento> eventiInData = new ArrayList<>();
		for (Evento evento : eventi) {
			if (evento.getData().equals(data)) {
				eventiInData.add(evento);
			}
		}
		return eventiInData;
	}

//	METODO PER CONOSCERE QUANTI SONO GLI EVENTI
	public int numeroEventi() {
		return eventi.size();
	}

//	METODO PER CANCELLARE GLI EVENTI DALLA LISTA
	public void svuotaEventi() {
		eventi.clear();
	}

//	METODO PER STAMPARE GLI EVENTI IN ORDINE DI DATA, CON DATA E TITOLO PER OGNI EVENTO PIU' ORA E PREZZO PER I CONCERTI
	public String visualizzaEventiOrdinatiPerData() {
		StringBuilder sb = new StringBuilder();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// Ordina gli eventi per data
		Collections.sort(eventi, (e1, e2) -> e1.getData().compareTo(e2.getData()));
		// Costruisce la stringa formattata
		for (Evento evento : eventi) {
            sb.append(evento.getData().format(formatter))
              .append(" - ")
              .append(evento.getTitolo());

            if (evento instanceof Concerto) {
                Concerto concerto = (Concerto) evento;
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                sb.append(": inizio concerto ore ")
                  .append(concerto.getOra().format(timeFormatter))
                  .append(" - prezzo: ")
                  .append(concerto.getPrezzoFormattato());
                
            }

            sb.append("\n");
        }
        return sb.toString();
    }

//	GETTER DI TITOLO
	public String getTitolo() {
		return titolo;
	}

//	SETTER DI TITOLO
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
}