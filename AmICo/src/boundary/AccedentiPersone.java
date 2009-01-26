/**
 * 
 */
package boundary;

import store.POJO.Persona;
import datatype.list.Percentuali;
import datatype.list.Persone;

/**
 * @author peregrino
 *
 */
public interface AccedentiPersone {
	public void aggiornaPersone(Persone persone);	
	public void aggiornaPersona(Persona persona);
	public boolean proprietaOK(Persone persone, Percentuali quote) ;
}
