/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.type.OrderedSetType;

import datatype.DatiUnitaImmobiliare;
import datatype.liste.Persone;
import datatype.liste.QuoteProprietà;

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
