package executor;

import java.io.File;

import store.TuttePersone;
import store.TuttiCondomini;
import store.POJO.Condominio;
import store.POJO.Persona;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;
import boundary.AmICo;
import boundary.ConfermaUnitaImmobiliari;
import boundary.DriverFileSystem;
import boundary.InserireNuovoCondominio;
import boundary.InserisciTabellaMillesimaleProprieta;
import calculator.FormatoAmICo;
import datatype.DatiCondominio;
import datatype.DatiTabellaMillesimale;
import datatype.DatiUnitaImmobiliare;
import datatype.list.Millesimi;
import datatype.list.Percentuali;
import datatype.list.Persone;
import enumeration.StatiGestoreCondominio;

public class GestoreCondomini implements BaseExecutor {

	public static GestoreCondomini m_gestoreCondominio;
	
	public static GestoreCondomini getInstance()
	{		
		if ( m_gestoreCondominio == null ) 
			m_gestoreCondominio = new GestoreCondomini();
		return m_gestoreCondominio;
	}
	
	private  AmICo m_amico;
	private Condominio m_condominio;
	private DatiCondominio m_datiCondominio;
	private DriverFileSystem m_driverFS;
	@SuppressWarnings("unused")
	private GestoreCondominioAperto m_gestoreCondominioAperto;
	private InserireNuovoCondominio m_inserireNuovoCondominio;
	@SuppressWarnings("unused")
	private InserisciTabellaMillesimaleProprieta m_inserisciTabelleMillesimali;
	private StatiGestoreCondominio m_state;
	private ConfermaUnitaImmobiliari m_confermaUnitaImmobiliari;

	private TabellaMillesimale m_tabellaMillesimaleProprieta;
	
	private UnitaImmobiliare m_unitaImmobiliare;
	
	private GestoreCondomini()
	{
		m_amico = AmICo.getInstance();
		
		m_amico.aggiornaCondomini(TuttiCondomini.getInstance().recuperaCondomini());
		
		m_state = StatiGestoreCondominio.gestoreCondomini;
		m_driverFS = DriverFileSystem.getInstance();
	}
	
	public void apriCondominio(Condominio condominio) {
		m_condominio = TuttiCondomini.getInstance().recuperaCondominio(condominio.getDatiC().getId());
		m_gestoreCondominioAperto = new GestoreCondominioAperto(m_condominio);
		
		m_state = StatiGestoreCondominio.condominioAperto;
	}
	
	private boolean condominioGiaInserito(DatiCondominio datiCondominio) {
		for ( Condominio condominio : TuttiCondomini.getInstance().recuperaCondomini().getCondomini() )
			if (condominio.recuperaDatiCondominio().equals(datiCondominio) || condominio.recuperaDatiCondominio().getId().equals(datiCondominio.getId()))
				return true;
		
		return false;
	}
	
	public void esciDaAmico() {
		if (m_state == StatiGestoreCondominio.gestoreCondomini)
			Avvio.esciDaAmICo();
	}
	
	public void importaCondominio(String path) {
		m_driverFS.leggi(path, (BaseExecutor)this);
		m_state = StatiGestoreCondominio.attesaSelezioneFile;
	}
	
	public void inserisciCondominio() {
		m_inserireNuovoCondominio = new InserireNuovoCondominio();	
		m_state = StatiGestoreCondominio.inserimentoCondominio;
		m_condominio = null;
	}
	
	public void inserisciUnitaImmobiliare() {
		m_state = StatiGestoreCondominio.inserimentoUnitaImmobiliare;
		m_unitaImmobiliare = new UnitaImmobiliare();	
		m_condominio.inserisciUnitaImmobiliare(m_unitaImmobiliare);	
	}
	
	public void operazioneAnnullata() {	
		switch(m_state)
		{
			case inserimentoProprieta:
				m_condominio.eliminaUnitaImmobiliare(m_unitaImmobiliare);
				m_condominio = TuttiCondomini.getInstance().recuperaCondominio(m_condominio.getDatiC().getId());
				m_confermaUnitaImmobiliari.aggiornaUnitaImmobiliari(m_condominio.recuperaUnitaImmobiliari());
				m_state = StatiGestoreCondominio.inserimentoUnitaImmobiliari;
				break;
				
			case inserimentoUnitaImmobiliari:
				m_confermaUnitaImmobiliari.dispose();
				if (m_condominio != null) 
					TuttiCondomini.getInstance().eliminaCondominio(m_condominio);
					m_amico.aggiornaCondomini(TuttiCondomini.getInstance().recuperaCondomini());
				break;
				
			default:
				m_state = StatiGestoreCondominio.gestoreCondomini;
			if (m_condominio != null) 
				TuttiCondomini.getInstance().eliminaCondominio(m_condominio);
				m_amico.aggiornaCondomini(TuttiCondomini.getInstance().recuperaCondomini());
			break;
		}
	}
	
	public void operazioneTerminata() {
		switch (m_state) {
		case condominioAperto :
			m_state = StatiGestoreCondominio.gestoreCondomini;
			m_condominio = null;
			break;
		case inserimentoUnitaImmobiliari :
			m_state = StatiGestoreCondominio.inserimentoTabellaMillesimaleProprieta;
			m_inserisciTabelleMillesimali= new InserisciTabellaMillesimaleProprieta(m_condominio.recuperaUnitaImmobiliari());
			break;
		}
	}

	public void operazioneTerminata(File file) {
		if ( !FormatoAmICo.fileInFormatoAmICo(file) ) {
			m_amico.fallito();
			m_state = StatiGestoreCondominio.gestoreCondomini;
			return;
		}
		m_condominio = FormatoAmICo.daFileACondominio(file);
		TuttiCondomini.getInstance().inserisciCondominio(m_condominio);
		m_gestoreCondominioAperto = new GestoreCondominioAperto(m_condominio);
		m_amico.fatto();
		m_amico.aggiornaCondomini(TuttiCondomini.getInstance().recuperaCondomini());
		m_state = StatiGestoreCondominio.condominioAperto;	
	}
	
	public void passaDatiCondominio(DatiCondominio datiCondominio) {
		if ( condominioGiaInserito(datiCondominio) ) {
			m_inserireNuovoCondominio.ammissibile(false);
		}
		else{
			m_state = StatiGestoreCondominio.attesaConfermaDatiCondominio;
			m_datiCondominio = datiCondominio;
			m_inserireNuovoCondominio.ammissibile(true);
		}
	}
	
	// non c'e' nel design, serve per eliminare unita' imoobiliare in creazione
	public void eliminaUnitaImmobiliare(String dati)
	{
		for (UnitaImmobiliare ui : m_condominio.getUnitaImmobiliari())
		{
			if( ui.getDatiUnitaImmobiliare().getId().equals(dati) )
				m_condominio.eliminaUnitaImmobiliare(ui);
		}
		m_condominio = TuttiCondomini.getInstance().recuperaCondominio(m_condominio.getDatiC().getId());
		m_confermaUnitaImmobiliari.aggiornaUnitaImmobiliari( m_condominio.recuperaUnitaImmobiliari() );
		
	}
	
	public void passaDatiUnitaImmobliare(DatiUnitaImmobiliare datiUnitaImmobliare) {
		if ( unitaImmobiliareGiaInserita(datiUnitaImmobliare) ) {
			
			m_state=StatiGestoreCondominio.inserimentoUnitaImmobiliari;
			m_confermaUnitaImmobiliari.ammissibile(false);
			m_unitaImmobiliare.modificaDati(datiUnitaImmobliare);
			passaProprieta(new Persone(), new Percentuali());
			/*** FIXME : INSERITO CON IL DESIGN 3.6.3 - BEGIN - Rimuovere se crea problemi ***/
			if ( m_unitaImmobiliare != null )
			{
				m_condominio.eliminaUnitaImmobiliare(m_unitaImmobiliare);
				m_unitaImmobiliare = null;
			}
			/*** INSERITO CON IL DESIGN 3.6.3 - END - Rimuovere se crea problemi ***/
			m_condominio = TuttiCondomini.getInstance().recuperaCondominio(m_condominio.getDatiC().getId()); 
			return;
		}

		m_state = StatiGestoreCondominio.inserimentoProprieta;
		m_unitaImmobiliare.modificaDati(datiUnitaImmobliare);
		m_confermaUnitaImmobiliari.ammissibile(true);
		m_confermaUnitaImmobiliari.aggiornaUnitaImmobiliari( m_condominio.recuperaUnitaImmobiliari() );
	}
		
	public void passaProprieta(Persone persone, Percentuali quoteProprieta) {		
		m_state = StatiGestoreCondominio.inserimentoUnitaImmobiliari;		
		Persone presenti = m_condominio.recuperaCondomini();
		for (Persona  p : persone.getPersone()) {
			if(!(presenti.getPersone().contains(p)))
				m_condominio.inserisciPersona(p);
		}
		m_unitaImmobiliare.modificaProprieta(persone, quoteProprieta);
		m_condominio = TuttiCondomini.getInstance().recuperaCondominio(m_condominio.getDatiC().getId());
	}
	
	public void passaTabellaMillesimaleProprieta(DatiTabellaMillesimale datiTabellaMillesimale, Millesimi millesimi) 
	{
		m_tabellaMillesimaleProprieta = new TabellaMillesimale(datiTabellaMillesimale, millesimi);
		m_inserireNuovoCondominio.ammissibile(true);
		m_state = StatiGestoreCondominio.attesaConfermaTabellaMillesimale;
	}
	
	public void procedi	(boolean procedere) {
		switch (m_state) {
			case attesaConfermaDatiCondominio :
				if ( !procedere ) {
					m_state = StatiGestoreCondominio.inserimentoCondominio;
					m_datiCondominio = null;
				}
				else{
				m_condominio = new Condominio();
				TuttiCondomini.getInstance().inserisciCondominio(m_condominio);
				m_condominio.modificaDati(m_datiCondominio);
				m_inserireNuovoCondominio.fatto();
				m_confermaUnitaImmobiliari = new ConfermaUnitaImmobiliari(m_inserireNuovoCondominio, 
						TuttePersone.getInstance().recuperaPersone());	
				}
				break;
			case attesaConfermaTabellaMillesimale :
				if ( !procedere ) {
					m_state = StatiGestoreCondominio.gestoreCondomini;
					break;
				}
				m_condominio.inserisciTabellaMillesimale(m_tabellaMillesimaleProprieta);
				m_amico.aggiornaCondomini(TuttiCondomini.getInstance().recuperaCondomini());
				m_amico.fatto();
				m_confermaUnitaImmobiliari.finito();
				m_gestoreCondominioAperto = new GestoreCondominioAperto(m_condominio);
				m_state = StatiGestoreCondominio.condominioAperto;
				break;
			case attesaConferma :
				if ( !procedere )	
				{
					m_condominio.eliminaUnitaImmobiliare(m_unitaImmobiliare);
				}
				else {
					m_confermaUnitaImmobiliari.fatto();
					m_confermaUnitaImmobiliari.aggiornaUnitaImmobiliari(m_condominio.recuperaUnitaImmobiliari());
				}
				m_state=StatiGestoreCondominio.inserimentoUnitaImmobiliari;
				break;
		}
	}
	
	private boolean unitaImmobiliareGiaInserita(DatiUnitaImmobiliare datiUnitaImmobliare) {	 	
		for ( UnitaImmobiliare unit : m_condominio.recuperaUnitaImmobiliari().getImmobili())
			if ( unit.getDatiUnitaImmobiliare().equals(datiUnitaImmobliare) || unit.getDatiUnitaImmobiliare().getId().equals(datiUnitaImmobliare.getId()))
				return true;
		return false;
	}
}