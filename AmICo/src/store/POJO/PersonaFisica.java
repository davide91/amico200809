/**
 * 
 */
package store.POJO;

import org.hibernate.Session;

import datatype.DatiPersonaFisica;

/**
 * @author bruno
 *
 */
public class PersonaFisica extends Persona {

	private DatiPersonaFisica dati;
	@SuppressWarnings("unused")
	private Session session;
	
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
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof PersonaFisica))
	   return false;
	 final PersonaFisica o = (PersonaFisica) other;
	 if (!o.getDati().equals(this.getDati()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
		return this.getDati().hashCode();
	}

	public DatiPersonaFisica getDati() {
		return dati;
	}

	public void setDati(DatiPersonaFisica dati) {
		this.dati = dati;
	}
}
