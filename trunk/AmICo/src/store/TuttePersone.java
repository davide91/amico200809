/**
 * 
 */
package store;

import java.util.List;

import org.hibernate.Session;

import store.POJO.Persona;
import store.util.HibernateUtil;
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

	public static Persone PERSONE = new Persone();
	
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		List persone = session.createQuery("from Persona").list();
		
		Persone tuttePersone = new Persone();
		for (int i = 0; i < persone.size(); i++) {
			
			
			Persona p = (Persona) persone.get(i);
			tuttePersone.inserisciPersona(p);  
		}
		session.getTransaction().commit();

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
