/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.hibernate.Session;

import store.util.HibernateUtil;
import datatype.DatiUnitaImmobiliare;
import datatype.list.Percentuali;
import datatype.list.Persone;

/**
 * @author bruno
 *
 */
public class UnitaImmobiliare {
	
	private long id;
	private DatiUnitaImmobiliare datiUnitaImmobiliare = new DatiUnitaImmobiliare();
	private Condominio condominio;
	private Set<Proprieta> quoteDiPossesso = new HashSet<Proprieta>();

	private Session session;
	
	public UnitaImmobiliare()
	{

	}
	
	public UnitaImmobiliare(DatiUnitaImmobiliare dui)
	{
		this.datiUnitaImmobiliare = dui;
	}
	
	public void creaUnitaImmobiliare()
	{
	
	}
	
	public void modificaDati(DatiUnitaImmobiliare dui)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
	
			datiUnitaImmobiliare = dui;
	
		session.update(this);
		session.update(this.condominio); //aggiunto per forzare l'update
		session.getTransaction().commit();
	}
	
	public Percentuali recuperaProprieta()
	{
		Percentuali ret = new Percentuali();
		
		for (Proprieta p : quoteDiPossesso) {
			ret.inserisciReale((float)p.getQuota());
		}
		
		return ret;
	}

	public void modificaProprieta(Persone pers, Percentuali quote)
	{
		if(pers.getPersone().size() != quote.getListaQuote().size())
			throw new NoSuchElementException();
	/*
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
			quoteDiPossesso.clear();
			
			for (int i=0;i< pers.getPersone().size();i++) {
				Proprieta prop = new Proprieta();
				prop.setProprietario(pers.getPersone().get(i));  // aggiungo il proprietario
				prop.setQuota(quote.getListaQuote().get(i)); 	//aggiungo la quota
				prop.setUnitaImmobiliare(this); 				//aggiungo l'unità immobiliare
				pers.getPersone().get(i).getProprieta().add(prop);
				session.persist(prop);
				quoteDiPossesso.add(prop);
			}
			
	
		session.update(this);
		session.getTransaction().commit();
	*/	
		
		
		
		if(pers.getPersone().size() != quote.getListaQuote().size())
			throw new NoSuchElementException();
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
			quoteDiPossesso.clear();
			
			for (int i=0;i< pers.getPersone().size();i++) {
				
				if(!(this.condominio.recuperaCondomini().getPersone().contains(pers.getPersone().get(i))))
					condominio.getPersone().add(pers.getPersone().get(i));
				
				Proprieta prop = new Proprieta();
				prop.setProprietario(pers.getPersone().get(i));  // aggiungo il proprietario
				prop.setQuota(quote.getListaQuote().get(i)); 	//aggiungo la quota
				prop.setUnitaImmobiliare(this); 				//aggiungo l'unità immobiliare
				
				boolean found = false;
				
				for (Proprieta p : pers.getPersone().get(i).getProprieta()) {
					if (p.getUnitaImmobiliare().equals(this)) {
						pers.getPersone().get(i).getProprieta().remove(p);
						pers.getPersone().get(i).getProprieta().add(prop);
						//session.update(pers.getPersone().get(i));
						found = true;
					}
				}
				
				if (!found) {
					pers.getPersone().get(i).getProprieta().add(prop);
				}	
				session.persist(prop);
				quoteDiPossesso.add(prop);
			}
		session.update(this);
		session.getTransaction().commit();
	}

	
  @Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof UnitaImmobiliare))
	   return false;
	 final UnitaImmobiliare o = (UnitaImmobiliare) other;
	 if (!o.getDatiUnitaImmobiliare().equals(getDatiUnitaImmobiliare()))
	   return false;
	 return true;
	}

/*	@Override
	public int hashCode() {
	 return this.getDatiUnitaImmobiliare().hashCode();
	}
	*/
	
  @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((condominio == null) ? 0 : condominio.hashCode());
		result = prime
				* result
				+ ((datiUnitaImmobiliare == null) ? 0 : datiUnitaImmobiliare
						.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
  
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DatiUnitaImmobiliare getDatiUnitaImmobiliare() {
		return datiUnitaImmobiliare;
	}

	public void setDatiUnitaImmobiliare(DatiUnitaImmobiliare datiUnitaImmobiliare) {
		this.datiUnitaImmobiliare = datiUnitaImmobiliare;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public Set<Proprieta> getQuoteDiPossesso() {
		return quoteDiPossesso;
	}

	public void setQuoteDiPossesso(Set<Proprieta> quoteDiPossesso) {
		this.quoteDiPossesso = quoteDiPossesso;
	}	
}
