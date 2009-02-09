/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public void sortingByID() {
		List<String> listId = new ArrayList<String>();
		
		for(Condominio c : condomini)
			listId.add(c.recuperaDatiCondominio().getId());
		
		Collections.sort(listId, String.CASE_INSENSITIVE_ORDER);
		
		List<Condominio> newCondomini = new ArrayList<Condominio>();
		
		for(String id : listId)
			for(Condominio c: condomini)
				if (c.getDatiC().getId().equals(id))
					newCondomini.add(c);
		
		condomini = newCondomini;
	}
}
