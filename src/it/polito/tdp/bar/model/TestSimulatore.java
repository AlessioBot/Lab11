package it.polito.tdp.bar.model;

public class TestSimulatore {
	public static void main(String[] args) {
		Simulatore sim = new Simulatore() ;
		sim.init();
		sim.run();
		System.out.format("%d tot clienti %d clienti soddisfatti, %d insoddisfatti\n",sim.getNumTotaleClienti(), sim.getNumClientiSoddisfatti(), sim.getNumClientiInsoddisfatti()) ;
		
	}
}
