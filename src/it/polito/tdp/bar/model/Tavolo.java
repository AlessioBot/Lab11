package it.polito.tdp.bar.model;

public class Tavolo {

	private int numPosti;
	private Boolean occupato;
	public Tavolo(int numPosti, Boolean occupato) {
		super();
		this.numPosti = numPosti;
		this.occupato = occupato;
	}
	public int getNumPosti() {
		return numPosti;
	}
	public void setNumPosti(int numPosti) {
		this.numPosti = numPosti;
	}
	public Boolean getOccupato() {
		return occupato;
	}
	public void setOccupato(Boolean occupato) {
		this.occupato = occupato;
	}
	
	
	
}
