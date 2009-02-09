/**
 * 
 */
package executor;

import store.TuttePersone;
import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import boundary.AccedentiPersone;
import boundary.AccedereCondominioAperto;
import boundary.InserirePersona;
import boundary.ModificarePersona;
import datatype.DatiPersona;
import datatype.DatiPersonaFisica;
import datatype.DatiPersonaGiuridica;
import datatype.list.Persone;
import enumeration.StatiGestorePersone;

/**
 * @author Pietro
 *
 */
public class GestorePersone implements BaseExecutor {
	
	public static GestorePersone GP;
	
	public static GestorePersone getInstance() {
		if (GP == null) {
			GP = new GestorePersone();
		}
		return GP;
	}
	
	private DatiPersona datiPersona;
	private InserirePersona IP;
	private ModificarePersona MP;
	private Persona personaMod;
	private AccedentiPersone RICH;
	private TuttePersone TP;
	private StatiGestorePersone state; 
		
	public GestorePersone(){
	 TP=new TuttePersone();
	 state=StatiGestorePersone.base;
	 
	}
	
	
	public void inserisciDatiPersona(DatiPersona datiP){
		datiPersona=datiP;
		state=StatiGestorePersone.attesaConfermaInserimento;
		
		if (datiP instanceof DatiPersonaFisica) {
			IP.ammissibile(!personaGiaInserita((DatiPersonaFisica)datiP));
			return;
		}
		else if (datiP instanceof DatiPersonaGiuridica)
			IP.ammissibile(!personaGiaInserita((DatiPersonaGiuridica) datiP));
	}
	
	public void inserisciPersona(AccedentiPersone richiedente){
		
		RICH=richiedente;
		state=StatiGestorePersone.inserimentoPersona;
		IP=new InserirePersona();

	}
	
	public void modificaDatiPersona(DatiPersona datiP) {
		datiPersona=datiP;
		state=StatiGestorePersone.attesaConfermaModifica;
		if (datiP instanceof DatiPersonaFisica) {
			MP.ammissibile(!personaGiaInserita((DatiPersonaFisica)datiP));	
		if (datiP instanceof DatiPersonaGiuridica) {
			MP.ammissibile(!personaGiaInserita((DatiPersonaGiuridica) datiP));
			}
		}
	}
	
	
	public void modificaPersona(AccedentiPersone richiedente, Persona persona,AccedereCondominioAperto aca) {
		RICH=richiedente;
		personaMod=persona;
		state=StatiGestorePersone.modificaPersona;
		MP=new ModificarePersona(persona,aca);
		
	
	}
	
	public void operazioneAnnullata() {
		
	}

	
	private boolean personaGiaInserita(DatiPersona datiP) {
		Persone pf = TuttePersone.getInstance().recuperaPersone();
		
		if(datiP instanceof DatiPersonaFisica)
		{
			for (Persona p : pf.getPersone()) {
				if(p instanceof PersonaFisica)
				{
					if(((PersonaFisica)p).getDati().equals((DatiPersonaFisica)datiP))
					{
						return true;
					}
				}
			}
		}
		else
		{
			for (Persona p : pf.getPersone()) {
				if(p instanceof PersonaGiuridica)
				{
					if(((PersonaGiuridica)p).getDati().equals((DatiPersonaGiuridica)datiP))
					{
						return true;
					}
				}
			}
			
		}
		return false;
	}
	
  

	public void annullato() {
		RICH.aggiornaPersone(TP.recuperaPersone());
	}
	
	
	public void operazioneTerminata() {
		//  Auto-generated method stub
		
	}

public void procedi(boolean b) {
	switch (state) {
	case attesaConfermaInserimento: 
		if (b){
			TP.inserisciPersona(datiPersona);
			IP.fatto();
		}
		else IP.fallito();	
		RICH.aggiornaPersone(TP.recuperaPersone()); //lo fa in entrambi i casi
		
		break;
	case attesaConfermaModifica:
		if (b){
			personaMod.modificaDati(datiPersona);
			MP.fatto();
		}
		else MP.fallito();
		RICH.aggiornaPersona(personaMod); //lo fa in entrambi i casi
	
		break;
	}
	state=StatiGestorePersone.base;

	
}


}
	
	

