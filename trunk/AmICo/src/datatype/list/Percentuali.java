/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peregrino
 *
 */
public class Percentuali {

	private List<Float> listaQuote = new ArrayList<Float>();
	
	public float somma() {
		float ret=0;
		
		for (Float f : listaQuote) {
			ret +=f;
		}
		return ret;
	}
	
	public void inserisciReale(Float f)
	{
		listaQuote.add(f);
	}
	
	public void inserisciRealeAt(int index, Float f)
	{
		listaQuote.add(index,f);
	}
	
	public void removeAt(int index)
	{
		listaQuote.remove(index);
	}
	
	public List<Float> recuperaQuote()
	{
		return listaQuote;
	}

	public List<Float> getListaQuote() {
		return listaQuote;
	}

	public void setListaQuote(List<Float> listaQuote) {
		this.listaQuote = listaQuote;
	}
}
