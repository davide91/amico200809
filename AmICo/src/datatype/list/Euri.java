/**
 * 
 */
package datatype.list;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import datatype.Euro;

/**
 * @author bruno
 *
 */
public class Euri {
	private List<Euro> euri = new ArrayList<Euro>();
	
	public Euri()
	{
		
	}
	
	public void inserisciEuro(Euro e)
	{
		euri.add(e);
	}

	public List<Euro> getEuri() {
		return euri;
	}

	public void setEuri(List<Euro> euri) {
		this.euri = euri;
	}
}
