/**
 * 
 */
package store.POJO;

import java.util.SortedSet;

import datatype.DatiPersonaGiuridica;

/**
 * @author bruno
 *
 */
public class PersonaGiuridica extends Persona{

	private DatiPersonaGiuridica dati;
	private PersonaFisica personaDiRiferimento;
	
	public PersonaGiuridica()
	{
		
	}
	
	public void creaPersonaGiuridica(DatiPersonaGiuridica dpg)
	{
		dati = dpg;
	}
	
	public void modificaDati(DatiPersonaGiuridica dpg)
	{
		dati = dpg;
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
	 if (!o.getPersonaDiRiferimento().equals(this.getPersonaDiRiferimento()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getDati().hashCode();
	 result = 29 * result + this.getPersonaDiRiferimento().hashCode();
	 return result;
	}
	
	public DatiPersonaGiuridica getDati() {
		return dati;
	}

	public void setDati(DatiPersonaGiuridica dati) {
		this.dati = dati;
	}

	public PersonaFisica getPersonaDiRiferimento() {
		return personaDiRiferimento;
	}

	public void setPersonaDiRiferimento(PersonaFisica personaDiRiferimento) {
		this.personaDiRiferimento = personaDiRiferimento;
	}
}
