/**
 * 
 */
package datatype;

import datatype.list.*;
/**
 * @author bruno
 *
 */
public class DatiErrati extends EsitoControlloDati{

	private Stringhe campiErrati = new Stringhe();
	
	public DatiErrati()
	{
		
	}
	
	public void aggiungiCampo(String s)
	{
		campiErrati.getLista().add(s);
	}
	
	public void creaDatiErrati(Stringhe errati)
	{
		campiErrati = errati;
	}

	public Stringhe getCampiErrati() {
		return campiErrati;
	}

	public void setCampiErrati(Stringhe campiErrati) {
		this.campiErrati = campiErrati;
	}
	
}
