/**
 * 
 */
package store;

import java.util.List;

import org.hibernate.Session;

import datatype.list.Condomini;
import datatype.list.Persone;

import store.POJO.Condominio;
import store.POJO.Persona;
import store.util.HibernateUtil;

/**
 * @author bruno
 *
 */
public class TuttiCondomini {

	public static Condomini CONDOMINI = new Condomini();
	
	public TuttiCondomini()
	{
		
	}
	
	public void inizializza()
	{
		//carico da DB tutti i condomini...	
		CONDOMINI = recuperaCondomini();
	}
	
	public Condomini recuperaCondomini()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		List Cond = session.createQuery("from Condominio").list();
		session.getTransaction().commit();

		Condomini ret = new Condomini();
		for (int i = 0; i < Cond.size(); i++) {	
			ret.inserisciCondominio((Condominio)Cond.get(i));
		}
		return ret;
	}
	
	public void inserisciCondominio(Condominio c)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();		
		//uso c come Condominio
		CONDOMINI.inserisciCondominio(c);
		session.save(c);
		session.getTransaction().commit();
	}

	public void eliminaCondominio(Condominio c)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(c);
		session.getTransaction().commit();
	}
}
