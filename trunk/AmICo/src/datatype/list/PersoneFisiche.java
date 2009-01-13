/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.PersonaFisica;


/**
 * @author bruno
 *
 */
public class PersoneFisiche extends Persone{
	private List<PersonaFisica> personeF = new ArrayList<PersonaFisica>();
	
	public PersoneFisiche() {
		// TODO Auto-generated constructor stub
	}
	
	public void inserisciPersonaFisica(PersonaFisica pf)
	{
		personeF.add(pf);
	}

	public List<PersonaFisica> getPersoneF() {
		return personeF;
	}

	public void setPersoneF(List<PersonaFisica> personeF) {
		this.personeF = personeF;
	}
}