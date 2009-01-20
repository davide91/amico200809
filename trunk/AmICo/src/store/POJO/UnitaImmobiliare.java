/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Session;

import store.util.HibernateUtil;

import datatype.DatiUnitaImmobiliare;
import datatype.list.Persone;
import datatype.list.QuoteProprieta;

/**
 * @author bruno
 *
 */
public class UnitaImmobiliare {
	
	private long id;
	private DatiUnitaImmobiliare datiUnitaImmobiliare = new DatiUnitaImmobiliare();
	private Condominio condominio;
	private Set<Proprieta> quoteDiPossesso;
	private Set<Millesimo> millesimo;
	
	private Session session;
	
	public UnitaImmobiliare()
	{
		 quoteDiPossesso = new HashSet<Proprieta>();
		 millesimo = new HashSet<Millesimo>();
	}
	
	public UnitaImmobiliare(DatiUnitaImmobiliare dui)
	{
		quoteDiPossesso = new HashSet<Proprieta>();
		 millesimo = new HashSet<Millesimo>();
		this.datiUnitaImmobiliare = dui;
	}
	
	public void creaUnitaImmobiliare()
	{
		datiUnitaImmobiliare = new DatiUnitaImmobiliare();
	}
	
	public void modificaDati(DatiUnitaImmobiliare dui)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
	
		datiUnitaImmobiliare = dui;
	
		session.update(this);
		session.getTransaction().commit();
	}
	
	public QuoteProprieta recuperaProprieta()
	{
		QuoteProprieta ret = new QuoteProprieta();
		
		for (Proprieta p : quoteDiPossesso) {
			ret.inserisciQuota(p);
		}
		
		return ret;
	}

	public void modificaProprieta(Persone p, QuoteProprieta q)
	{
		
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

	@Override
	public int hashCode() {
	 return this.getDatiUnitaImmobiliare().hashCode();
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

//	@SuppressWarnings("unused")
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public Set<Proprieta> getQuoteDiPossesso() {
		return quoteDiPossesso;
	}

	public void setQuoteDiPossesso(Set<Proprieta> quoteDiPossesso) {
		this.quoteDiPossesso = quoteDiPossesso;
	}

	public Set<Millesimo> getMillesimo() {
		return millesimo;
	}

	public void setMillesimo(Set<Millesimo> millesimo) {
		this.millesimo = millesimo;
	}
}
