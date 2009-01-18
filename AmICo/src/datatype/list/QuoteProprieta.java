/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.Proprieta;

/**
 * @author bruno
 *
 */
public class QuoteProprieta{

	private List<Proprieta> listaQuote = new ArrayList<Proprieta>();
	
	public QuoteProprieta()
	{
		
	}
	
	public void inserisciQuota(Proprieta p)
	{
		listaQuote.add(p);
	}

	public List<Proprieta> getListaQuote() {
		return listaQuote;
	}

	public void setListaQuote(List<Proprieta> listaQuote) {
		this.listaQuote = listaQuote;
	}
}
