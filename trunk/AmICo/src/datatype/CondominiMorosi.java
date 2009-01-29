/**
 * 
 */
package datatype;

import store.POJO.Persona;
import datatype.list.Persone;

/**
 * @author bruno
 *
 */
public class CondominiMorosi extends Avviso{
	
	private Persone condominiMorosi = new Persone();

	public CondominiMorosi(Persone p)
	{
		condominiMorosi = p;
	}
	
	public void inserisciCondominoMoroso(Persona p)
	{
		condominiMorosi.inserisciPersona(p);
	}
	
	public void eliminaCondominoMoroso(Persona p)
	{
		condominiMorosi.elimina(p);
	}

	public Persone getCondominiMorosi() {
		return condominiMorosi;
	}

	public void setCondominiMorosi(Persone condominiMorosi) {
		this.condominiMorosi = condominiMorosi;
	}
}
