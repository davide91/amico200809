/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.Bilancio;

/**
 * @author bruno
 *
 */
public class Bilanci {

	private List<Bilancio> bilanci = new ArrayList<Bilancio>();
	
	public Bilanci()
	{
		
	}
	
	public void inserisciBilancio(Bilancio b)
	{
		bilanci.add(b);
	}

	public List<Bilancio> getBilanci() {
		return bilanci;
	}

	public void setBilanci(List<Bilancio> bilanci) {
		this.bilanci = bilanci;
	}
}
