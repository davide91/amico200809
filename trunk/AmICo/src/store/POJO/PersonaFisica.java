/**
 * 
 */
package store.POJO;

import datatype.DatiPersonaFisica;

/**
 * @author bruno
 *
 */
public class PersonaFisica extends Persona {

	private DatiPersonaFisica dati;
	
	public void creaPersonaFisica(DatiPersonaFisica dpf)
	{
		dati = dpf;
	}
	
	public void modificaDati(DatiPersonaFisica dpf)
	{
		dati = dpf;
	}
	
	public PersonaFisica()
	{
		
	}

	public DatiPersonaFisica getDati() {
		return dati;
	}

	public void setDati(DatiPersonaFisica dati) {
		this.dati = dati;
	}
}
