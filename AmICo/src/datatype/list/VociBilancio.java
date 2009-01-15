/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.VoceBilancio;

/**
 * @author bruno
 *
 */
public class VociBilancio {

	private List<VoceBilancio> voci = new ArrayList<VoceBilancio>();
	
	public VociBilancio()
	{
		
	}
	
	public void inserisciVoceBilancio(VoceBilancio vb)
	{
		voci.add(vb);
	}

	public List<VoceBilancio> getVoci() {
		return voci;
	}

	public void setVoci(List<VoceBilancio> voci) {
		this.voci = voci;
	}
}
