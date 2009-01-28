/**
 * 
 */
package executor;

import store.TuttePersone;
import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import boundary.AccedentiPersone;
import boundary.InserirePersona;
import boundary.ModificarePersona;
import datatype.DatiPagamento;
import datatype.DatiPersona;
import datatype.DatiPersonaFisica;
import datatype.DatiPersonaGiuridica;
import datatype.EsitoControlloDatiPersona;
import datatype.Inseribile;
import datatype.PersoneConStessoIndirizzo;
import datatype.PersoneConStessoNome;
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
		if (datiP instanceof DatiPersonaFisica) {
			IP.ammissibile(personaGiaInserita((DatiPersonaFisica)datiP));	
		if (datiP instanceof DatiPersonaGiuridica) {
			IP.ammissibile(personaGiaInserita((DatiPersonaGiuridica) datiP));
			}
		}
		
		state=StatiGestorePersone.attesaConfermaInserimento;
		
	}
	
	public void inserisciPersona(AccedentiPersone richiedente){
		RICH=richiedente;
		IP=new InserirePersona();
	//	IP.creaInserirePersona();
		state=StatiGestorePersone.inserimentoPersona;
	}
	
	public void modificaDatiPersona(DatiPersona datiP) {
		datiPersona=datiP;
		if (datiP instanceof DatiPersonaFisica) {
			MP.ammissibile(personaGiaInserita((DatiPersonaFisica)datiP));	
		if (datiP instanceof DatiPersonaGiuridica) {
			MP.ammissibile(personaGiaInserita((DatiPersonaGiuridica) datiP));
			}
		}
		
		state=StatiGestorePersone.attesaConfermaModifica;
		
	}
	
	
	public void modificaPersona(AccedentiPersone richiedente, Persona persona) {
		RICH=richiedente;
		personaMod=persona;
		MP=new ModificarePersona(persona);
		
		state=StatiGestorePersone.modificaPersona;
	}
	
	public void operazioneAnnullata() {
		
	}

	
	private boolean personaGiaInserita(DatiPersona datiP) {
		Persone pf = TuttePersone.getInstance().recuperaPersone();
		for (Persona p : pf.getPersone()) {
			if(p instanceof PersonaFisica)
			{
				if(((PersonaFisica)p).getDati().equals((DatiPersonaFisica)datiP))
				{
					return true;
				}
			}
			else
			{
				if(((PersonaGiuridica)p).getDati().equals((DatiPersonaGiuridica)datiP))
				{
					return true;
				}
			}	
		}
		return false;
	}
	
  

public void annullato() {
	// TODO Auto-generated method stub
	
}


public void operazioneTerminata() {
	// TODO Auto-generated method stub
	
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
	
	

