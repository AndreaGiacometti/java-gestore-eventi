package org.lessons.java.gestoreeventi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

public class Concerto extends Evento {
	private LocalTime ora;
	private double prezzoBiglietto;

	public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, double prezzoBiglietto) {
		super(titolo, data, postiTotali);
		this.setOra(ora);
		this.setPrezzoBiglietto(prezzoBiglietto);
	}

	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public double getPrezzoBiglietto() {
		return prezzoBiglietto;
	}

	public void setPrezzoBiglietto(double prezzoBiglietto) {
		this.prezzoBiglietto = prezzoBiglietto;
	}

	public String getOraFormattata() {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		return ora.format(timeFormatter);
	}

	public String getPrezzoFormattato() {
		NumberFormat formatoEuro = NumberFormat.getCurrencyInstance(Locale.ITALY);
		return formatoEuro.format(prezzoBiglietto);
	}

	@Override
	public String toString() {
		return super.toString() + " Inizio concerto ore " + getOraFormattata() + ", prezzo del biglietto "
				+ getPrezzoFormattato() + ".";
	}
}