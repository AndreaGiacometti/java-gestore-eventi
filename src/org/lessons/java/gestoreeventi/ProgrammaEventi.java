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
//		ORDINARE GLI ELEMENTI PER DATA
		Collections.sort(eventi, (e1, e2) -> e1.getData().compareTo(e2.getData()));
//		COSTRUIRE UNA STRINGA FORMATTATA
		for (Evento evento : eventi) {
            sb.append(evento.getData().format(formatter))
              .append(" - ")
              .append(evento.getTitolo());
            
//            AGGIUNGO STRINGHE SE L'OGGETTO E' DELLA CLASSE CONCERTO
            if (evento instanceof Concerto) {
//            	SE evento E' UN'ISTANZA DI Concerto ALLORA TRASFORMO evento IN UN concerto TRAMITE IL CAST "(Concerto) evento"
                Concerto concerto = (Concerto) evento;
//              FORMATTO L'ORARIO
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//              CREO LA STRINGA DA STAMPARE
                sb.append(" - inizio concerto ore ")
                  .append(concerto.getOra().format(timeFormatter))
                  .append(" - prezzo: ")
                  .append(concerto.getPrezzoFormattato())
                  .append("]");
                
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