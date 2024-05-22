package org.lessons.java.gestoreeventi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
	private String titolo;
	private LocalDate data;
	private int postiTotali;
	private int postiPrenotati;
	
	public Evento(String titolo, LocalDate data, int postiTotali) {
		this.setTitolo(titolo);
		this.setData(data);
		this.postiTotali = postiTotali;
		this.postiPrenotati = 0;
	}
	
	public String getTitolo() {
		return this.titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public LocalDate getData() {
		return this.data;
	}
	public void setData(LocalDate data) {
		if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("L'evento deve svolgersi in una data futura");
        }
		this.data = data;
	}
	
	public int getPostiTotali() {
		if (postiTotali <=0) {
			throw new IllegalArgumentException("I posti totali devono essere maggiori di 0");
		}
		return this.postiTotali;
	}
	
	public int getPostiPrenotati() {
		return this.postiPrenotati;
	}
	
	public int getPostiDisponibili() {
        return this.postiTotali - this.postiPrenotati;
    }
	
	public int prenota() {
		if (this.postiPrenotati >= this.postiTotali) {
			throw new IllegalArgumentException("Peccato! Non ci sono abbastanza posti.");
		} else {
			this.postiPrenotati++;
			return this.postiPrenotati;
		}
	}
	
	  public void resetPostiPrenotati() {
	        this.postiPrenotati = 0;
	    }
	
	public int disdici() {
		if (this.postiPrenotati < 0) {
			throw new IllegalArgumentException("Stai cercando di disdire più prenotazioni di quante ce ne siano!");
		} else {
			this.postiPrenotati--;
			return this.postiPrenotati;
		}
	}
	
	@Override
    public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormattata = data.format(formatter);
        titolo = titolo.toUpperCase();
        return "Evento in programma: " + titolo + ", previsto per il giorno " + dataFormattata + ".";
	}
}



