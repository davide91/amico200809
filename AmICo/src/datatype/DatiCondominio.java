/**
 * 
 */
package datatype;

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