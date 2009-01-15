/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.UnitaImmobiliare;

/**
 * @author bruno
 *
 */
public class UnitàImmobiliari {
	
	private List<UnitaImmobiliare> immobili = new ArrayList<UnitaImmobiliare>();
	
	public UnitàImmobiliari()
	{
		
	}
	
	public void inserisciUnitaImmobiliare(UnitaImmobiliare ui)
	{
		immobili.add(ui);
	}

	public List<UnitaImmobiliare> getImmobili() {
		return immobili;
	}

	public void setImmobili(List<UnitaImmobiliare> immobili) {
		this.immobili = immobili;
	}
	
}
