/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class PartitaIva {

	private String partIva;
	
	public PartitaIva()
	{
		
	}
	
	public PartitaIva(String partIva)
	{
		this.partIva = partIva;
	}
	
	public boolean controlla()
	{
		// pattern di controllo come per la mail
		return false;
	}

	public String getPartIva() {
		return partIva;
	}

	public void setPartIva(String partIva) {
		this.partIva = partIva;
	}
	
	
}
