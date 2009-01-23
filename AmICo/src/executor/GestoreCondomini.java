package executor;

import java.io.File;
import java.net.URL;

import calculator.FormatoAmICo;

import boundary.AmICo;
import boundary.DriverFileSystem;
import boundary.InserireNuovoCondominio;
import boundary.InserireUnitaImmobiliare;
import boundary.AccederePersone;
import boundary.InserirePersona;
import datatype.DatiCondominio;
import datatype.DatiTabellaMillesimale;
import datatype.DatiUnitaImmobiliare;
import datatype.list.Condomini;
import datatype.list.Persone;
import datatype.list.Percentuali;
import datatype.list.UnitaImmobiliari;
import enumeration.StatiGestoreCondominio;
import store.TuttePersone;
import store.TuttiCondomini;
import store.POJO.Condominio;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;

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
	private TuttiCondomini m_dbCondomini;
	private TuttePersone m_dbPersone;
	private DriverFileSystem m_driverFS;
	private GestoreCondominioAperto m_gestoreCondominioAperto;
	private InserireNuovoCondominio m_inserireNuovoCondominio;
	private InserireUnitaImmobiliare m_inserireUnitaImmobiliare;
	private StatiGestoreCondominio m_state;
	
	private TabellaMillesimale m_tabellaMillesimaleProprieta;
	
	private UnitaImmobiliare m_unitaImmobiliare;
	
	private GestoreCondomini()
	{
		m_amico = AmICo.getInstance();
		m_dbCondomini = TuttiCondomini.getInstance();
		m_dbPersone = TuttePersone.getInstance();
		
		m_amico.aggiornaCondomini(m_dbCondomini.recuperaCondomini());
		
		m_state = StatiGestoreCondominio.gestoreCondomini;
		m_driverFS = DriverFileSystem.getInstance();
		
		
	}
	
	public void apriCondominio(Condominio condominio) {
		m_gestoreCondominioAperto = new GestoreCondominioAperto(condominio);
		m_condominio = condominio;
		m_state = StatiGestoreCondominio.condominioAperto;
	}
	
	private boolean condominioGiaInserito(DatiCondominio datiCondominio) {
		for ( Condominio condominio : m_dbCondomini.recuperaCondomini().getCondomini() )
			if ( condominio.recuperaDatiCondominio().equals(datiCondominio) )
				return true;
		
		return false;
	}
	
	public void esciDaAmico() {
		if (m_state == StatiGestoreCondominio.gestoreCondomini)
			Avvio.esciDaAmICo();
	}
	
	public void finito() {
		m_state = StatiGestoreCondominio.inserimentoTabellaMillesimaleProprieta;
		
		/* FIXME :
		 * AMM.mostra(InserisciTabellaMillesimaleProprietaGenerale)
		 */
	}
	
	public void importaCondominio(URL path) {
		m_driverFS.leggi(path, (BaseExecutor)this);
		m_state = StatiGestoreCondominio.attesaSelezioneFile;
	}
	
	public void inserisciCondominio() {
		m_inserireNuovoCondominio = new InserireNuovoCondominio();
//		m_condominio = new Condominio();
		m_state = StatiGestoreCondominio.inserimentoCondominio;
	}
	
	public void inserisciUnitaImmobiliare() {
		m_state = StatiGestoreCondominio.inserimentoUnitaImmobiliare;
		m_unitaImmobiliare = new UnitaImmobiliare();
		m_condominio.inserisciUnitaImmobiliare(m_unitaImmobiliare);
		m_inserireUnitaImmobiliare = new InserireUnitaImmobiliare();
	}
	
	public void operazioneAnnullata() {
		if ( m_state == StatiGestoreCondominio.inserimentoCondominio ) {
			m_dbCondomini.eliminaCondominio(m_condominio);
			m_state = StatiGestoreCondominio.gestoreCondomini;
		}
	}
	
	public void operazioneTerminata() {
		// Solo per lo stato "condominioAperto"
		m_state = StatiGestoreCondominio.gestoreCondomini;
	}
	
	public void operazioneTerminata(File file) {
		if ( !FormatoAmICo.fileInFormatoAmICo(file) ) {
			m_amico.fallito();
			m_state = StatiGestoreCondominio.gestoreCondomini;
			return;
		}
		m_condominio = FormatoAmICo.daFileACondominio(file);
		m_dbCondomini.inserisciCondominio(m_condominio);
		m_gestoreCondominioAperto = new GestoreCondominioAperto(m_condominio);
		m_amico.fatto();
		m_amico.aggiornaCondomini(m_dbCondomini.recuperaCondomini());
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
		
	public void passaDatiUnitaImmobliare(DatiUnitaImmobiliare datiUnitaImmobliare) {
		if ( unitaImmobiliareGiaInserita(datiUnitaImmobliare) ) {
			m_inserireUnitaImmobiliare.ammissibile(false);
			m_condominio.eliminaUnitaImmobiliare(m_unitaImmobiliare);
			return;
		}
		
		m_unitaImmobiliare.modificaDati(datiUnitaImmobliare);
		m_inserireUnitaImmobiliare.ammissibile(true);
		m_state = StatiGestoreCondominio.inserimentoProprieta;
		m_inserireUnitaImmobiliare.aggiornaPersone(m_dbPersone.recuperaPersone());
	}
		
	public void passaProprieta(Persone persone, Percentuali quoteProprieta) {
		m_unitaImmobiliare.modificaProprieta(persone, quoteProprieta);
/*		Scomparso nel design 3.3, proabile ricomparsa in futuro...	
 * 	
 * 		m_inserireUnitaImmobiliare.ammissibile(true);
 * 
 */
		m_state = StatiGestoreCondominio.attesaConferma;
	}
	
	public void passaTabellaMillesimaleProprieta(DatiTabellaMillesimale datiTabellaMillesimale, Percentuali millesimi) {
		m_tabellaMillesimaleProprieta = new TabellaMillesimale(datiTabellaMillesimale, millesimi);
		m_inserireNuovoCondominio.ammissibile(true);
		m_state = StatiGestoreCondominio.attesaConfermaTabellaMillesimale;
	}

	
	public void procedi(boolean procedere) {
		switch (m_state) {
			case attesaConfermaDatiCondominio :
				if ( !procedere ) {
					m_state = StatiGestoreCondominio.inserimentoCondominio;
					break;
				}
				m_state = StatiGestoreCondominio.inserimentoUnitaImmobiliari;
				m_condominio = new Condominio();
				m_dbCondomini.inserisciCondominio(m_condominio);
				m_condominio.modificaDati(m_datiCondominio);
				inserisciUnitaImmobiliare();
				
				break;
			case attesaConfermaTabellaMillesimale :
				if ( !procedere ) {
					m_state = StatiGestoreCondominio.gestoreCondomini;
					m_dbCondomini.eliminaCondominio(m_condominio);
					break;
				}
				m_condominio.inserisciTabellaMillesimale(m_tabellaMillesimaleProprieta);
				m_amico.aggiornaCondomini(m_dbCondomini.recuperaCondomini());
				m_gestoreCondominioAperto = new GestoreCondominioAperto(m_condominio);
				m_amico.fatto();
				m_state = StatiGestoreCondominio.condominioAperto;
				break;
			case attesaConferma :
				if ( !procedere ) {
					/* Se chiamo IUI.fatto() per caso ottimale, *dovrei* chiamare
					 * IUI.fallito() in caso negativo.
					 * Scomparso nel design 3.3, potrebbe tornare
					 *
					 * m_inserireUnitaImmobiliare.fallito();
					 */					
					m_condominio.eliminaUnitaImmobiliare(m_unitaImmobiliare);
					break;
				}
				m_inserireUnitaImmobiliare.fatto();
				m_inserireNuovoCondominio.aggiornaUnitaImmobiliari(m_condominio.recuperaUnitaImmobiliari());
				break;
		}
	}
	
	
	public void settaCondomini(Condomini condomini) {
		/*
		 * TODO : Totalemente inutilizzata nella SM, che fare?
		 * Rimane anche nel design 3.3, sempre inutilizzata
		 */
	}
	
	private boolean unitaImmobiliareGiaInserita(DatiUnitaImmobiliare datiUnitaImmobliare) {	 
		UnitaImmobiliari uImmobiliari = m_condominio.recuperaUnitaImmobiliari();
		UnitaImmobiliare newUnit = new UnitaImmobiliare();
		newUnit.modificaDati(datiUnitaImmobliare);
		
		for ( UnitaImmobiliare unit : uImmobiliari.getImmobili() )
			if ( unit.equals(newUnit) )
				return false;
		
		return true;
	}
}
