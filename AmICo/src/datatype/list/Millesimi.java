/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.Millesimo;

/**
 * @author bruno
 *
 */
public class Millesimi {

	private List<Millesimo> listaMillesimi = new ArrayList<Millesimo>();
	
	public float somma() {
		float ret=0;
		
		for (Millesimo m : listaMillesimi) {
			ret +=m.getQuota();
		}
		return ret;
	}
	
	public void inserisciMillesimo(Millesimo m)
	{
		listaMillesimi.add(m);
	}
	
	public void inserisciMillesimoAt(int index, Millesimo f)
	{
		listaMillesimi.add(index,f);
	}
	
	public void removeAt(int index)
	{
		listaMillesimi.remove(index);
	}

	public List<Millesimo> getListaMillesimi() {
		return listaMillesimi;
	}

	public void setListaMillesimi(List<Millesimo> listaMillesimi) {
		this.listaMillesimi = listaMillesimi;
	}
}
