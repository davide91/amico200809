/**
 * 
 */
package executor;

import boundary.BaseBoundary;
import boundary.InserirePersona;
import datatype.DatiPersona;
import datatype.EsitoControlloDatiPersona;
import enumeration.StatiGestorePersone;
import store.TuttePersone;
import store.POJO.Persona;

/**
 * @author Pietro
 *
 */
public class GestorePersone {
	
	public static GestorePersone GP;
	private BaseBoundary RICH;
	private InserirePersona IP;
	private Persona personaMod;
	private DatiPersona datiPersona;
	private StatiGestorePersone state; 
	
	
	
	public GestorePersone(){
	 GP=this;
	 state=StatiGestorePersone.base;
	}
	
	public void inserisciPersona(BaseBoundary richiedente){
		RICH=richiedente;
		IP=new InserirePersona();
		IP.creaInserirePersona();
		state=StatiGestorePersone.inserimentoPersona;
	}
	
	
	public void inserisciDatiPersona(DatiPersona datiP){
		datiPersona=datiP;
		IP.ammissibile(personaGiaInserita(datiP));
		state=StatiGestorePersone.attesaConfermaInserimento;
		
	}
	
	public void procedi(Boolean procedere) {
		switch (state) {
		case attesaConfermaInserimento: 
			if (procedere){
				
				
			}	
			break;

		default:
			break;
		}
	}
	
	private EsitoControlloDatiPersona personaGiaInserita(DatiPersona datiP) {
		return new EsitoControlloDatiPersona();
	}
	
	
}
