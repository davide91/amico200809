/**
 * 
 */
package store.POJO;

import java.util.SortedSet;
import java.util.TreeSet;

import datatype.DatiUnitaImmobiliare;
import datatype.list.Persone;
import datatype.list.QuoteProprietà;

/**
 * @author bruno
 *
 */
public class UnitaImmobiliare {
	
	private long id;
	private DatiUnitaImmobiliare datiUnitaImmobiliare;
	private Condominio condominio;
	private SortedSet<Proprieta> quoteDiPossesso = new TreeSet<Proprieta>();
	
	public UnitaImmobiliare()
	{
		
	}
	
	public void creaUnitàImmobiliare()
	{
		datiUnitaImmobiliare = new DatiUnitaImmobiliare();
	}
	
	public void modificaDati(DatiUnitaImmobiliare dui)
	{
		datiUnitaImmobiliare = dui;
	}
	
	public QuoteProprietà recuperaProprietà()
	{
		QuoteProprietà ret = new QuoteProprietà();
		
		for (Proprieta p : quoteDiPossesso) {
			ret.inserisciQuota(p);
		}
		
		return ret;
	}

	public void modificaProprietà(Persone p, float r)
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
	 if (!o.getQuoteDiPossesso().equals(getQuoteDiPossesso()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getDatiUnitaImmobiliare().hashCode();
//	 result = 29 * result + this.getQuoteDiPossesso().hashCode();
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

//	@SuppressWarnings("unused")
	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public SortedSet<Proprieta> getQuoteDiPossesso() {
		return quoteDiPossesso;
	}

	public void setQuoteDiPossesso(SortedSet<Proprieta> quoteDiPossesso) {
		this.quoteDiPossesso = quoteDiPossesso;
	}
}
