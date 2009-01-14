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
	private DatiUnitaImmobiliare datiUnitàImmobiliare;
	
	private SortedSet<Proprieta> quoteDiPossesso = new TreeSet<Proprieta>();
	
	public UnitaImmobiliare()
	{
		
	}
	
	public void creaUnitàImmobiliare()
	{
		datiUnitàImmobiliare = new DatiUnitaImmobiliare();
	}
	
	public void modificaDati(DatiUnitaImmobiliare dui)
	{
		datiUnitàImmobiliare = dui;
	}
	
	private QuoteProprietà recuperaProprietà()
	{
		return null;
	}
	
	private void modificaProprietà(Persone p, float r)
	{
		
	}

	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof UnitaImmobiliare))
	   return false;
	 final UnitaImmobiliare o = (UnitaImmobiliare) other;
	 if (!o.getDatiUnitàImmobiliare().equals(getDatiUnitàImmobiliare()))
	   return false;
	 if (!o.getQuoteDiPossesso().equals(getQuoteDiPossesso()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getDatiUnitàImmobiliare().hashCode();
	 result = 29 * result + this.getQuoteDiPossesso().hashCode();
	 return result;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DatiUnitaImmobiliare getDatiUnitàImmobiliare() {
		return datiUnitàImmobiliare;
	}

	public void setDatiUnitàImmobiliare(DatiUnitaImmobiliare datiUnitàImmobiliare) {
		this.datiUnitàImmobiliare = datiUnitàImmobiliare;
	}

	public SortedSet<Proprieta> getQuoteDiPossesso() {
		return quoteDiPossesso;
	}

	public void setQuoteDiPossesso(SortedSet<Proprieta> quoteDiPossesso) {
		this.quoteDiPossesso = quoteDiPossesso;
	}

}
