/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.TabellaMillesimale;

/**
 * @author bruno
 *
 */
public class TabelleMillesimali {

	private List<TabellaMillesimale> tabelle = new ArrayList<TabellaMillesimale>();
	
	public TabelleMillesimali()
	{
		
	}
	
	public void inserisciTabellaMillesimale(TabellaMillesimale tab)
	{
		tabelle.add(tab);
	}

	public List<TabellaMillesimale> getTabelle() {
		return tabelle;
	}

	public void setTabelle(List<TabellaMillesimale> tabelle) {
		this.tabelle = tabelle;
	}
}
