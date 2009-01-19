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
	private Session session;
	
	public TuttePersone()
	{
		
	}

	public void inizializza()
	{		
		//carico dal DB tutte le persone	
		PERSONE = recuperaPersone();	
	}
	
	public void inserisciPersona(DatiPersona dati)
	{	session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		
		if (dati instanceof DatiPersonaFisica) {
			DatiPersonaFisica dpf = (DatiPersonaFisica) dati;
			PersonaFisica pf = new PersonaFisica();
			pf.creaPersonaFisica(dpf);
			PERSONE.inserisciPersona(pf);
			session.persist(pf);
		}else
		{
			DatiPersonaGiuridica dpg = (DatiPersonaGiuridica) dati;
			PersonaGiuridica pg = new PersonaGiuridica();
			pg.creaPersonaGiuridica(dpg);
			PERSONE.inserisciPersona(pg);
			session.persist(pg);
		}
		session.getTransaction().commit();
	}
	
	public void eliminaPersona(Persona pers)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			PERSONE.elimina(pers);
			session.delete(pers);
		session.getTransaction().commit();
	}
	
	public Persone recuperaPersone()
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		List personeF = session.createQuery("from Persona where PERSONA_TYPE = :tipo").setParameter("tipo", "FISICA").list();
		
		
		Persone tuttePersone = new Persone();
		for (int i = 0; i < personeF.size(); i++) {
			PersonaFisica p = (PersonaFisica) personeF.get(i);
			tuttePersone.inserisciPersona(p);  
		}
		
		List personeG = session.createQuery("from Persona where PERSONA_TYPE = :tipo").setParameter("tipo", "GIURIDICA").list();
		session.getTransaction().commit();
		
		
		for (int i = 0; i < personeG.size(); i++) {
			PersonaGiuridica p = (PersonaGiuridica) personeG.get(i);
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
