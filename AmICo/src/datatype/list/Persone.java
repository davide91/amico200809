/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

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
	
	public void inserisciPersona(Persona p) {
		persone.add(p);
	}

	public List<Persona> recuperaPersone() {
		return persone;
	}

	public boolean isEmpty() {
		if(persone.size()==0)
			return true;
		return false;
	}

	public PersoneFisiche recuperaPersone(Indirizzo domicilio) {
		Persone persInd = new PersoneFisiche();
		
		for (Persona p : persone) {
			if (p instanceof PersonaFisica) {
				PersonaFisica pf = (PersonaFisica) p;
				
				if(pf.getDati().getDomicilio().equals(domicilio))
					persInd.inserisciPersona(pf);
			}
		}
		return (PersoneFisiche)persInd;
	}

	public Persone recuperaPersone(PartitaIva iva) {
		PersoneGiuridiche persIva = new PersoneGiuridiche();
		
		for (Persona p : persone) {
			if (p instanceof PersonaGiuridica) {
				PersonaGiuridica pg = (PersonaGiuridica) p;
				
				if(pg.getDati().getpIva().equals(iva))
					persIva.inserisciPersona(pg);
			}
		}
		return (PersoneGiuridiche)persIva;	
	}
	
	public PersoneFisiche recuperaPersone(String nome, String cognome)
	{
		PersoneFisiche persInd = new PersoneFisiche();
		
		for (Persona p : persone) {
			if (p instanceof PersonaFisica) {
				PersonaFisica pf = (PersonaFisica) p;
				
				if(pf.getDati().getNome().equals(nome) && pf.getDati().getCognome().equals(cognome))
					persInd.inserisciPersona(pf);
			}
		}
		return (PersoneFisiche)persInd;
	}
	
	public void elimina(Persona p)
	{
		this.persone.remove(p);
	}

	public List<Persona> getPersone() {
		return persone;
	}

	public void setPersone(List<Persona> persone) {
		this.persone = persone;
	}
}
