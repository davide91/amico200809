/**
 * 
 */
package store;

import java.util.List;

import org.hibernate.Session;

import datatype.list.Condomini;
import datatype.list.Persone;
import enumeration.StatoCondominio;

import store.POJO.Condominio;
import store.POJO.Persona;
import store.util.HibernateUtil;

/**
 * @author bruno
 *
 */
public class TuttiCondomini {

	private Condomini CONDOMINI;
	private Session session;
	
	public TuttiCondomini()
	{
		CONDOMINI = new Condomini();
	}
	
	public Condomini recuperaCondomini()
	{	
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		List Cond = session.createQuery("from Condominio").list();
		

		CONDOMINI.setCondomini(Cond);
		/*for (int i = 0; i < Cond.size(); i++) {	
			CONDOMINI.inserisciCondominio((Condominio)Cond.get(i));
		}*/
		session.getTransaction().commit();
		return CONDOMINI;
	}
	
	public void inserisciCondominio(Condominio c)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();		
			c.setStatoCondominio(StatoCondominio.inCompilazione);
			CONDOMINI.inserisciCondominio(c);
			session.persist(c);
		session.getTransaction().commit();
	}

	public void eliminaCondominio(Condominio c)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			CONDOMINI.elimina(c);
			session.delete(c);
		session.getTransaction().commit();
	}
}
