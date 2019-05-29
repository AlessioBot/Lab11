package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;

public class Evento implements Comparable<Evento> {
	
	public enum TipoEvento {
		ARRIVO_GRUPPO_CLIENTI,
		USCITA_CLIENTI
	}
	
	public Duration getTempo() {
		return tempo;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	private Duration tempo ;
	private TipoEvento tipo ;
	private int num_persone;
	private Duration durata;
	private double tolleranza;
	
	

	public Evento(Duration tempo, TipoEvento tipo, int num_persone, Duration durata, double tolleranza) {
		super();
		this.tempo = tempo;
		this.tipo = tipo;
		this.num_persone = num_persone;
		this.durata = durata;
		this.tolleranza = tolleranza;
	}

	@Override
	public int compareTo(Evento other) {
		return this.tempo.compareTo(other.tempo);
	}

	public Duration getDurata() {
		return durata;
	}

	public void setDurata(Duration durata) {
		this.durata = durata;
	}

	public int getNum_persone() {
		return num_persone;
	}

	public void setNum_persone(int num_persone) {
		this.num_persone = num_persone;
	}

	public double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(double tolleranza) {
		this.tolleranza = tolleranza;
	}

}
