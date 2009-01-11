/**
 * 
 */
package store;

import org.hibernate.Session;

import store.POJO.Condominio;
import store.util.HibernateUtil;

/**
 * @author bruno
 *
 */
public class TuttiCondomini {

	public TuttiCondomini()
	{
		
	}
	
	public void inizializza()
	{
		
		
	}
	
	public Condomini recuperaCondomini()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		// Attenzione: bisogna usare il nome della classe Java (Corso) e non il
		// nome della tabella sulla quale questa viene mappata (come definito
		// nel relativo file Corso.hbm.xml).
		// Il linguaggio qui utilizzato è HQL, non compatibile con SQL benchè
		// molto simile per favorire l'accettazione del tool Hibernate agli
		// esperti di DB relazionali.l
		List Cond = session.createQuery("from Condomini").list();
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
