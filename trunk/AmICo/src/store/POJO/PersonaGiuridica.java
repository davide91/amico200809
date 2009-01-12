/**
 * 
 */
package store.POJO;

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
