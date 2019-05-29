package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;

import com.sun.javafx.collections.MappingChange.Map;

import it.polito.tdp.bar.model.Evento.TipoEvento;


public class Simulatore {
	
	private PriorityQueue<Evento> queue = new PriorityQueue<>() ;
	

	// Stato del mondo
	TreeMap<Integer,Integer> tavoli;
	
	// Parametri di simulazione
	Duration t = Duration.ofMinutes(0);
	int num_persone;
	Duration durata;
	float tolleranza;
	
	
	
	// Statistiche
	private int numTotaleClienti ;
	private int numClientiSoddisfatti ;
	private int numClientiInsoddisfatti;
	
	
	public void init() {
		queue.clear();
		Random rand = new Random();
		
		for(int i=0 ; i<2000 ; i++) {
			
			 t = t.plus(Duration.ofMinutes(rand.nextInt(10)+1));
			 num_persone = rand.nextInt(10)+1;
			 durata = Duration.ofMinutes(rand.nextInt(61)+60);
			 tolleranza = rand.nextFloat();
			Evento e = new Evento(t, TipoEvento.ARRIVO_GRUPPO_CLIENTI, num_persone, durata, tolleranza);
			queue.add(e);
			System.out.println(e.getTempo()+" "+ e.getNum_persone()+" "+e.getDurata());
		}
		
		// reset mondo
		//tutti i tavoli disponibili
		tavoli = new TreeMap<Integer,Integer>();
		tavoli.put(10, 2);
		tavoli.put(8, 4);
		tavoli.put(6, 4);
		tavoli.put(4, 5);
		
		
		// reset statistiche
		numTotaleClienti  = 0 ;
		numClientiSoddisfatti = 0 ;
		numClientiInsoddisfatti = 0;
	}
	

	public void run() {
		while(!queue.isEmpty()) {
			Evento ev = queue.poll() ;
			
			switch (ev.getTipo()) {
			case ARRIVO_GRUPPO_CLIENTI:
				Random rand = new Random();
				boolean flag = false;
				numTotaleClienti += ev.getNum_persone();
				//assegno tavoli disponibili
				for(int i = 4; i<=10 ; i= i+2) {
					if(ev.getNum_persone()<=i && tavoli.get(i)>0) {
						if(ev.getNum_persone() >= i/2) {
							tavoli.replace(i, tavoli.get(i)-1);
							numClientiSoddisfatti += ev.getNum_persone() ;
							flag = true;
							queue.add(new Evento(ev.getTempo().plus(ev.getDurata()), TipoEvento.USCITA_CLIENTI, ev.getNum_persone(), Duration.ZERO, 0)) ;
						}else if(rand.nextFloat()>ev.getTolleranza()) {
							//vanno al bancone
							numClientiSoddisfatti  += ev.getNum_persone();
						}else {
							numClientiInsoddisfatti += ev.getNum_persone();
						}
					}	
					//else if(rand.nextFloat()>ev.getTolleranza() && i==10 && tavoli.get(i)==0) {
						//vanno al bancone
						//numClientiSoddisfatti += ev.getNum_persone() ;
					//}else if(i==10 && tavoli.get(i)==0) {
						//numClientiInsoddisfatti += ev.getNum_persone();
						//}
					}
				
			
			break;

			case USCITA_CLIENTI:
				for(int i = 4; i<=10 ; i= i+2) {
					if(ev.getNum_persone()<=i) {
						if(ev.getNum_persone() >= i/2) {
							tavoli.replace(i, tavoli.get(i)+1);
						}
					}
				}
				break ;
			}
		}
	}


	public int getNumTotaleClienti() {
		return numTotaleClienti;
	}


	public void setNumTotaleClienti(int numTotaleClienti) {
		this.numTotaleClienti = numTotaleClienti;
	}


	public int getNumClientiSoddisfatti() {
		return numClientiSoddisfatti;
	}


	public void setNumClientiSoddisfatti(int numClientiSoddisfatti) {
		this.numClientiSoddisfatti = numClientiSoddisfatti;
	}


	public int getNumClientiInsoddisfatti() {
		return numClientiInsoddisfatti;
	}


	public void setNumClientiInsoddisfatti(int numClientiInsoddisfatti) {
		this.numClientiInsoddisfatti = numClientiInsoddisfatti;
	}
	
	


	
}

