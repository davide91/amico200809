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
import boundary.AccederePersone;
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
	private DatiPersona personaRiferimento = null;
	
	
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
		{
			IP.ammissibile(!personaGiaInserita((DatiPersonaGiuridica) datiP));
		}
	}
	
	public void inserisciPersona(AccedentiPersone richiedente){
		
		RICH=richiedente;
		state=StatiGestorePersone.inserimentoPersona;
		IP=new InserirePersona(TP.recuperaPersone());

	}
	
	public void modificaDatiPersona(DatiPersona datiP) {
		datiPersona=datiP;
		state=StatiGestorePersone.attesaConfermaModifica;
		if (datiP instanceof DatiPersonaFisica) {
			MP.ammissibile(!personaGiaInserita((DatiPersonaFisica)datiP));	
		}
		if (datiP instanceof DatiPersonaGiuridica){
			MP.ammissibile(!personaGiaInserita((DatiPersonaGiuridica) datiP));
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
					if(((PersonaGiuridica)p).getDati().equals((DatiPersonaGiuridica)datiP) && ((PersonaGiuridica)p).getPersonaDiRiferimento().equals(personaRiferimento))
					{
						return true;
					}
				}
			}
			
		}
		return false;
	}
	
	public void annullato() {
		if(!(RICH instanceof AccederePersone))
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
				if(personaRiferimento!=null)
				{
					Persona giuridica = recuperaPersona(datiPersona);
					Persona fisica = recuperaPersona(personaRiferimento);
					if(giuridica instanceof PersonaGiuridica)
						((PersonaGiuridica)giuridica).assegnaPersonaDiRiferimento((PersonaFisica)fisica);
				}
				personaRiferimento=null;
				IP.fatto();
			}
			else IP.fallito();	
			RICH.aggiornaPersone(TP.recuperaPersone()); //lo fa in entrambi i casi
			
			break;
		case attesaConfermaModifica:
			if (b){
				personaMod.modificaDati(datiPersona);
				if(personaRiferimento!=null)
				{
					Persona giuridica = recuperaPersona(datiPersona);
					Persona fisica = recuperaPersona(personaRiferimento);
					if(giuridica instanceof PersonaGiuridica)
						((PersonaGiuridica)giuridica).assegnaPersonaDiRiferimento((PersonaFisica)fisica);
				}
				
				personaRiferimento=null;
				MP.fatto();
			}
			else MP.fallito();
			RICH.aggiornaPersone(TP.recuperaPersone()); //lo fa in entrambi i casi
		
			break;
		}
		state=StatiGestorePersone.base;
	}
	
	private Persona recuperaPersona(DatiPersona dp)
	{
		for (Persona p : TuttePersone.getInstance().recuperaPersone().getPersone()) 
		{
			if(p instanceof PersonaFisica && dp instanceof DatiPersonaFisica)
			{
				if(((PersonaFisica)p).getDati().equals((DatiPersonaFisica)dp))
					return (PersonaFisica)p;
			}
			else if(p instanceof PersonaGiuridica && dp instanceof DatiPersonaGiuridica)
			{
				if(((PersonaGiuridica)p).getDati().equals((DatiPersonaGiuridica)dp))
					return (PersonaGiuridica)p;
			}
		}
		return null;
	}

	public boolean inserisciPersonaDiRiferimento(DatiPersonaFisica riferimento)
	{
		if(!personaGiaInserita(riferimento))
		{
			personaRiferimento = riferimento;
			TuttePersone.getInstance().inserisciPersona(riferimento);
			return true;
		}
		else
			return false;
	}
	
	public void inserisciPersonaDiRiferimento(PersonaFisica riferimento)
	{
			personaRiferimento = riferimento.getDati();
	}
}
	
	

