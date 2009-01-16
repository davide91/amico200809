/**
 * 
 */
package datatype;

import store.POJO.UnitaImmobiliare;

/**
 * @author bruno
 *
 */
public class DatiCondominio {

	
	private String id;
	private Indirizzo indirizzo;
	
	public DatiCondominio(String id, Indirizzo ind)
	{
		this.id = id;
		this.indirizzo = ind;
	}
	
	public DatiCondominio()
	{
		
	}

	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof DatiCondominio))
	   return false;
	 final DatiCondominio o = (DatiCondominio) other;
	 if (!o.getIndirizzo().equals(getIndirizzo()))
	   return false;
	 return true;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Indirizzo getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}
}
