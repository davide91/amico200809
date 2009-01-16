/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.Condominio;

/**
 * @author bruno
 *
 */
public class Condomini {
	
	private List<Condominio> condomini;
	
	public Condomini()
	{
		condomini = new ArrayList<Condominio>();
	}
	
	public void inserisciCondominio(Condominio c)
	{
		condomini.add(c);
	}
	
	public void elimina(Condominio c)
	{
		condomini.remove(c);
	}

	public List<Condominio> getCondomini() {
		return condomini;
	}

	public void setCondomini(List<Condominio> condomini) {
		this.condomini = condomini;
	}
}
