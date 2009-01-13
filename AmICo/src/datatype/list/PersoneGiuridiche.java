/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.PersonaGiuridica;

/**
 * @author bruno
 *
 */
public class PersoneGiuridiche extends Persone{

	private List<PersonaGiuridica> personeG = new ArrayList<PersonaGiuridica>();
	
	public PersoneGiuridiche()
	{
		
	}
	
	public void inserisciPersonaGiuridica(PersonaGiuridica pg)
	{
		personeG.add(pg);
	}

	public List<PersonaGiuridica> getPersoneG() {
		return personeG;
	}

	public void setPersoneG(List<PersonaGiuridica> personeG) {
		this.personeG = personeG;
	}
}