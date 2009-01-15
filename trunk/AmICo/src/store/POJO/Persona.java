/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import datatype.DatiPersona;

/**
 * @author bruno
 *
 */
public class Persona {

	protected long id;
	protected Set<Pagamento> pagamenti = new HashSet<Pagamento>();
	
	protected Set<Pagamento> getPagamenti() {
		return pagamenti;
	}

	protected void setPagamenti(Set<Pagamento> pagamenti) {
		this.pagamenti = pagamenti;
	}

	public Persona()
	{
		
	}

	public void modificaDati(DatiPersona datiPersona) {
		// TODO Auto-generated method stub
	}
	
	protected long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

}
