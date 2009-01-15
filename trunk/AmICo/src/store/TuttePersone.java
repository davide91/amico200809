/**
 * 
 */
package store;

import java.util.List;

import org.hibernate.Session;

import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import store.util.HibernateUtil;
import datatype.DatiPersona;
import datatype.DatiPersonaFisica;
import datatype.DatiPersonaGiuridica;
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

	public static Persone PERSONE = new Persone();
	
	public TuttePersone()
	{
		
	}

	public void inizializza()
	{		
		//carico dal DB tutte le persone	
		PERSONE = recuperaPersone();	
	}
	
	public void inserisciPersona(DatiPersona dati)
	{	Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		
		if (dati instanceof DatiPersonaFisica) {
			DatiPersonaFisica dpf = (DatiPersonaFisica) dati;
			PersonaFisica pf = new PersonaFisica();
			pf.creaPersonaFisica(dpf);
			PERSONE.inserisciPersona(pf);
			session.save(pf);
		}else
		{
			DatiPersonaGiuridica dpg = (DatiPersonaGiuridica) dati;
			PersonaGiuridica pg = new PersonaGiuridica();
			pg.creaPersonaGiuridica(dpg);
			PERSONE.inserisciPersona(pg);
			session.save(pg);
		}
		session.getTransaction().commit();
	}
	
	public void eliminaPersona(Persona pers)
	{
		
	}
	
	public Persone recuperaPersone()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		List persone = session.createQuery("from Persona").list();
		session.getTransaction().commit();
		
		Persone tuttePersone = new Persone();
		for (int i = 0; i < persone.size(); i++) {
			Persona p = (Persona) persone.get(i);
			tuttePersone.inserisciPersona(p);  
		}
		return tuttePersone;
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
