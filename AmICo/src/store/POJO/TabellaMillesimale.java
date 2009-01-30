/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import store.util.HibernateUtil;

import datatype.DatiTabellaMillesimale;
import datatype.list.Millesimi;
import datatype.list.Percentuali;

/**
 * @author bruno
 *
 */
public class TabellaMillesimale {

	private long id;
	private DatiTabellaMillesimale dati;
	private Condominio condominio;
	private Set<Millesimo> millesimi = new HashSet<Millesimo>();
	
	private Session session;
	
	public TabellaMillesimale()
	{
		
	}
	
	public TabellaMillesimale(DatiTabellaMillesimale DTM, Millesimi mill)
	{
		creaTabellaMillesimale(DTM, mill);
	}
	
	public void creaTabellaMillesimale(DatiTabellaMillesimale DTM, Millesimi mille)
	{
		dati = DTM;
		for (int i=0;i< mille.getListaMillesimi().size();i++) {
			Millesimo mill = new Millesimo();
			mill.setQuota(mille.getListaMillesimi().get(i));
			millesimi.add(mill);
		}
	}

	public void creaTabellaProprietaGenerale(Millesimi mille)
	{
		dati = new DatiTabellaMillesimale();
		dati.setNome("Proprietà Generale");
		dati.setDescrizione("Tabella richiesta dal codice civile per dividere le spese generali");
		creaTabellaMillesimale(dati, mille);
	}
	
	public void modificaTabella(String nome, Millesimi mille)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
	
			millesimi.clear();
			
			for (int i=0;i< mille.getListaMillesimi().size();i++) {
				Millesimo mill = new Millesimo();
				mill.setQuota(mille.getListaMillesimi().get(i));
				millesimi.add(mill);
			}
	
		session.update(this);
		session.getTransaction().commit();
	}
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof TabellaMillesimale))
	   return false;
	 final TabellaMillesimale o = (TabellaMillesimale) other;
	 if (!o.getDati().equals(getDati()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getDati().hashCode();
	 return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DatiTabellaMillesimale getDati() {
		return dati;
	}

	public void setDati(DatiTabellaMillesimale dati) {
		this.dati = dati;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	
	public Set<Millesimo> getMillesimi() {
		return millesimi;
	}

	public void setMillesimi(Set<Millesimo> millesimi) {
		this.millesimi = millesimi;
	}
}
