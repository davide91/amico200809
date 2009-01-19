/**
 * 
 */
package store.POJO;

import java.util.SortedSet;

import org.hibernate.Session;

import store.util.HibernateUtil;

import datatype.DatiPersonaFisica;

/**
 * @author bruno
 *
 */
public class PersonaFisica extends Persona {

	private DatiPersonaFisica dati;
	
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
	 if (!(other instanceof PersonaGiuridica))
	   return false;
	 final PersonaGiuridica o = (PersonaGiuridica) other;
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
