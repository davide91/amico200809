/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import datatype.DatiUnitaImmobiliare;

/**
 * @author bruno
 *
 */
public class UnitaImmobiliare {
	
	private DatiUnitaImmobiliare datiUnitàImmobiliare;
	
	private Set<Proprieta> quoteDiPossesso = new HashSet<Proprieta>();
	
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
	
	private seqProprietà recuperaProprietà()
	{
		
	}
	
	private void modificaProprietà(persone p, Reali r)
	{
		
	}

}
