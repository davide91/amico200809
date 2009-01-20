/**
 * 
 */
package boundary;

import store.POJO.Persona;
import datatype.DatiCorretti;
import datatype.DatiErrati;
import datatype.DatiPersona;
import datatype.EsitoControlloDati;
import datatype.EsitoControlloDatiPersona;
import datatype.list.Persone;
import executor.GestorePersone;

/**
 * @author peregrino
 *
 */
public class ModificarePersona implements AccedentiPersone, BaseBoundary{

	private GestorePersone GP;


	public ModificarePersona(){}
	
	
	public void creaModificarePersona(GestorePersone GP, Persona persona){
		this.GP=GP;
		//AMM.mostraPersona(persona);
	}
	
	
	
	public void inserisciNuoviDatiPersona(DatiPersona datiP) {
		EsitoControlloDati esito= datiP.controlla();
		 if (esito instanceof DatiErrati) 
			//AMM.mostra(esito);
			 System.out.println("");
		 if(esito instanceof DatiCorretti) {
			GP.inserisciDatiPersona(datiP);
		 }
		 
		
	}

	public void ammissibile(EsitoControlloDatiPersona 	controlloDati) {
		//AMM.richiediConferma(controlloDati);
	}
	public void ammissibile(Boolean b) {
		
	}
	
	public void annulla() {
		GP.annullato();
	}

	
	public void ok() {
		GP.procedi(true);
	}
	
	

	public void ko() {
		GP.procedi(false);
		//AMM.mostra(DatiPersonaModificatiKO);
	}

	public void fatto() {
		//AMM.mostra(DatiPersonaModificatiOK);
		
	}


	public void fallito() {
		// TODO Auto-generated method stub
		
	}


	public void finito() {
		// TODO Auto-generated method stub
		
	}


	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}


	public void aggiornaPersone(Persone persone) {
		// TODO Auto-generated method stub
		
	}
}
