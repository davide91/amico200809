/**
 * 
 */
package store;

import store.POJO.Persona;
import datatype.DatiPersona;
import datatype.Indirizzo;
import datatype.PartitaIva;
import datatype.list.Persone;
import datatype.list.PersoneFisiche;
import datatype.list.PersoneGiuridiche;

/**
 * @author bruno
 *
 */
public class TuttePersone {

	private static Persone PERSONE = new Persone();
	
	public TuttePersone()
	{
	}

	public void inizializza()
	{
		//carico dal DB tutte le persone		
	}
	
	public void inserisciPersona(DatiPersona dati)
	{
			
	}
	
	public void eliminaPersona(Persona pers)
	{
		
	}
	
	public Persone recuperaPersone()
	{
		return null;
	}
	
	public PersoneFisiche recuperaPersone(String nome, String cognome)
	{
		return null;
	}
	
	public PersoneFisiche recuperaPersone(Indirizzo ind)
	{
		return null;
	}
	
	public PersoneGiuridiche recuperaPersone(PartitaIva pIva)
	{
		return null;
	}
}
