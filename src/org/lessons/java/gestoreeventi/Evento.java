package org.lessons.java.gestoreeventi;
import java.time.LocalDate;

public class Evento {
	private String titolo;
	private LocalDate data;
	private int postiTotali;
	private int postiPrenotati;
	
	public Evento(String titolo, LocalDate data, int postiTotali) {
		this.setTitolo(titolo);
		this.setData(data);
		this.setPostiTotali(postiTotali);
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
		return this.postiTotali;
	}
	public void setPostiTotali(int postiTotali) {
		if (postiTotali <=0) {
			throw new IllegalArgumentException("I posti totali devono essere maggiori di 0");
		}
		this.postiTotali = postiTotali;
	}
	
	public int getPostiPrenotati() {
		return this.postiPrenotati;
	}
}

