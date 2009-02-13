/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import datatype.Avviso;

/**
 * @author bruno
 *
 */
public class Avvisi {

	List<Avviso> listaAvvisi = new ArrayList<Avviso>();
	
	public void add(Avviso avviso) {
		listaAvvisi.add(avviso);
	}
	
	public  List<Avviso> getListaAvvisi() {
		return listaAvvisi;
	}
}
