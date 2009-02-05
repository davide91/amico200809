/**
 * 
 */
package store;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import store.POJO.Condominio;
import store.util.HibernateUtil;
import datatype.list.Condomini;
import enumeration.StatoCondominio;

/**
 * @author bruno
 *
 */
public class TuttiCondomini {

	private static TuttiCondomini CONDOMINI = null;
	private Session session;
	
	public static TuttiCondomini getInstance()
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
		
		session.getTransaction().commit();
		Condomini c = new Condomini();
		c.setCondomini(Cond);
		return c;
	}
	
	//scritta per permettere di fare un aggiornamento negli executor
	public Condominio recuperaCondominio(String id)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		Condominio Cond = (Condominio)session.createQuery("from Condominio where ID=:id").setParameter("id", id).uniqueResult();
		
		session.getTransaction().commit();
		
		return Cond;
	}
	
	public void inserisciCondominio(Condominio c)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();		
			c.setStatoCondominio(StatoCondominio.inCompilazione);
			session.persist(c);
		session.getTransaction().commit();
	}

	public void eliminaCondominio(Condominio c)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			session.delete(c);
		session.getTransaction().commit();
	}
}
