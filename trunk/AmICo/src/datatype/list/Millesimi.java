/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bruno
 *
 */
public class Millesimi {

private List<Float> listaMillesimi = new ArrayList<Float>();
	
	public float somma() {
		float ret=0;
		
		for (Float f : listaMillesimi) {
			ret +=f;
		}
		return ret;
	}
	
	public void inserisciReale(Float f)
	{
		listaMillesimi.add(f);
	}
	
	public void inserisciRealeAt(int index, Float f)
	{
		listaMillesimi.add(index,f);
	}
	
	public void removeAt(int index)
	{
		listaMillesimi.remove(index);
	}

	public List<Float> getListaMillesimi() {
		return listaMillesimi;
	}

	public void setListaMillesimi(List<Float> listaMillesimi) {
		this.listaMillesimi = listaMillesimi;
	}
}
