/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class CodiceFiscale {

	private String codiceFis; 
	
	public CodiceFiscale()
	{
		
	}

	public CodiceFiscale(String cod)
	{
		this.codiceFis = cod;
	}
	
	public boolean controlla()
	{
		// pattern di controllo come per la mail
		return false;
	}
	
	public String getCodiceFis() {
		return codiceFis;
	}

	public void setCodiceFis(String codiceFis) {
		this.codiceFis = codiceFis;
	}
	
}
