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

	public void inizializzaPersone()
	{	PERSONE.getPersone().clear();
		caricaDalDB();
	}
	
	private void caricaDalDB()
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		List personeF = session.createQuery("from Persona where PERSONA_TYPE = :tipo").setParameter("tipo", "FISICA").list();
				
		for (int i = 0; i < personeF.size(); i++) {
			PERSONE.inserisciPersona((PersonaFisica) personeF.get(i));
		}
		
		List personeG = session.createQuery("from Persona where PERSONA_TYPE = :tipo").setParameter("tipo", "GIURIDICA").list();
		session.getTransaction().commit();
	
		for (int i = 0; i < personeG.size(); i++) {
			PERSONE.inserisciPersona((PersonaGiuridica) personeG.get(i));
		}
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
		if(pers instanceof PersonaFisica){ // se è fisica guardo se è il riferimento di qualche personaGiuridica, nel caso la levo!
			List lista = session.createQuery("from Persona where PERSONA_TYPE = :tipo AND PERSONA_RIFERIMENTO = :rif").setParameter("tipo", "GIURIDICA").setParameter("rif", pers.getId()).list();
			
			if(lista.size() != 0)
			{
				for (Object o : lista) {
					PersonaGiuridica pg = (PersonaGiuridica)o;
					pg.setPersonaDiRiferimento(null);
					session.update(pg);
				}
			}
		}
		PERSONE.elimina(pers);
		session.delete(pers);
		session.getTransaction().commit();
	}
	
	public Persone recuperaPersone()
	{
		return PERSONE;
	}
	
	public Persone recuperaPersone(String nome, String cognome)
	{
		return PERSONE.recuperaPersone(nome, cognome);
	}
	
	public Persone recuperaPersone(Indirizzo ind)
	{
		return PERSONE.recuperaPersone(ind);
	}
	
	public Persone recuperaPersone(PartitaIva pIva)
	{
		return PERSONE.recuperaPersone(pIva);
	}
}
