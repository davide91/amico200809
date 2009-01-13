/**
 * 
 */
package boundary;

import datatype.DatiCorretti;
import datatype.DatiErrati;
import datatype.DatiPersona;
import datatype.EsitoControlloDati;
import datatype.EsitoControlloDatiPersona;
import executor.GestorePersone;

/**
 * @author Pietro
 *
 */
public class InserirePersona {

	private GestorePersone GP;
	public InserirePersona(){  	}
	
	public void creaInserirePersona(GestorePersone GP){
		this.GP=GP;
		//AMM.richiediDatiPersona()
	}
	
	
	public void ok() {
		
	}
	
	

	public void ko() {
		
	}
	
	public void inserisciDatiPersona(DatiPersona datiP) {
		EsitoControlloDati esito= datiP.controlla();
		 if (esito instanceof DatiErrati) {
			//AMM.mostra(esito);
		 else if(esito instanceof DatiCorretti) {
			
			
		
		}
	}
	public void ammissibile(Boolean b) {
		
	}

	public void ammissibile(EsitoControlloDatiPersona personaGiaInserita) {
		
		
	}

	public void fatto() {
		// TODO Auto-generated method stub
		
	}
	
}
