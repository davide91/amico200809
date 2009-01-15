/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.PianoPagamenti;

/**
 * @author bruno
 *
 */
public class PianiPagamenti {

	private List<PianoPagamenti> piani = new ArrayList<PianoPagamenti>();
	
	public PianiPagamenti()
	{
		
	}
	
	public void inserisciPianoPagamenti(PianoPagamenti pp)
	{
		piani.add(pp);
	}

	public List<PianoPagamenti> getPiani() {
		return piani;
	}

	public void setPiani(List<PianoPagamenti> piani) {
		this.piani = piani;
	}
}
