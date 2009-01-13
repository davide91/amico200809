/**
 * 
 */
package boundary;

import datatype.EsitoControlloDatiPersona;
import executor.GestorePersone;
import store.POJO.Persona;

/**
 * @author peregrino
 *
 */
public class ModificarePersona {

	private GestorePersone GP;


	public ModificarePersona(){}
	
	
	public void creaModificarePersona(GestorePersone GP, Persona persona){
		this.GP=GP;
		
	}

	public void ammissibile(EsitoControlloDatiPersona personaGiaInserita) {
		// TODO Auto-generated method stub
	}
	public void ammissibile(Boolean b) {
		
	}


	public void fatto() {
		// TODO Auto-generated method stub
		
	}
}
