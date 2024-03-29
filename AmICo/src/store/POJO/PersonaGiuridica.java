/**
 * 
 */
package store.POJO;

import org.hibernate.Session;

import store.util.HibernateUtil;
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
	
	public void assegnaPersonaDiRiferimento(PersonaFisica pf)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			personaDiRiferimento = pf;
		session.update(this);
		session.getTransaction().commit();
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
