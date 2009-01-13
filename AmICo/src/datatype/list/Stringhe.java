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
public class Stringhe {

	private List<String> lista = new ArrayList<String>();
	
	public Stringhe()
	{
		
	}
	
	public void aggiungi(String s)
	{
		lista.add(s);
	}

	public List<String> getLista() {
		return lista;
	}

	public void setLista(List<String> lista) {
		this.lista = lista;
	}
}
