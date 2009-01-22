/**
 * 
 */
package store;

import java.util.List;

import org.hibernate.Session;

import store.POJO.Condominio;
import store.util.HibernateUtil;
import datatype.list.Condomini;
import enumeration.StatoCondominio;
import executor.Avvio;

/**
 * @author bruno
 *
 */
public class TuttiCondomini {

	private static TuttiCondomini CONDOMINI = null;
	private Session session;
	
	public static TuttiCondomini inizializzaCondomini()
	{
		if ( CONDOMINI == null )
			CONDOMINI = new TuttiCondomini();
		return CONDOMINI;
	}

	public Condomini recuperaCondomini()
	{	
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		List Cond = session.createQuery("from Condominio").list();
		
//		CONDOMINI.setCondomini(Cond);
		session.getTransaction().commit();
		Condomini c = new Condomini();
		c.setCondomini(Cond);
		return c;
	}
	
	public void inserisciCondominio(Condominio c)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();		
			c.setStatoCondominio(StatoCondominio.inCompilazione);
	//		CONDOMINI.inserisciCondominio(c);
			session.persist(c);
		session.getTransaction().commit();
	}

	public void eliminaCondominio(Condominio c)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//	CONDOMINI.elimina(c);
			session.delete(c);
		session.getTransaction().commit();
	}
}
