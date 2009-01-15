/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import datatype.DatiPersona;
import datatype.Indirizzo;
import datatype.PartitaIva;

import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;

/**
 * @author bruno
 *
 */
public class Persone {

	private List<Persona> persone = new ArrayList<Persona>();
	
	public void inserisciPersona(DatiPersona datiPersona) {
		// TODO Auto-generated method stub
		
	}
	
	public void inserisciPersona(Persona p) {
		// TODO Auto-generated method stub
		persone.add(p);
	}

	public List<Persona> recuperaPersone() {
		// TODO Auto-generated method stub
		return persone;
	}

	public Persone recuperaPersone(String nome, String cognome) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Persone recuperaPersone(Indirizzo domicilio) {
		Persone persInd = new Persone();
		
		for (Persona p : persone) {
			if (p instanceof PersonaFisica) {
				PersonaFisica pf = (PersonaFisica) p;
				
				if(pf.getDati().getDomicilio().equals(domicilio))
					persInd.inserisciPersona(pf);
			}
		}
		return persInd;
	}

	public Persone recuperaPersone(PartitaIva iva) {
		Persone persIva = new Persone();
		
		for (Persona p : persone) {
			if (p instanceof PersonaGiuridica) {
				PersonaGiuridica pg = (PersonaGiuridica) p;
				
				if(pg.getDati().getpIva().equals(iva))
					persIva.inserisciPersona(pg);
			}
		}
		return persIva;	
	}
	
	
}
