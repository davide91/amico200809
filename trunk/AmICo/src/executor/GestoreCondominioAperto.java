package executor;

import java.net.URL;

import calculator.FormatoAmICo;

import boundary.AccedereCondominioAperto;
import boundary.AccederePersone;
import boundary.AccedereTabelleMillesimali;
import boundary.AccedereUnitaImmobiliari;
import boundary.DriverFileSystem;
import datatype.DatiTabellaMillesimale;
import datatype.Preferenze;
import datatype.list.Avvisi;
import datatype.list.Condomini;
import datatype.list.Persone;
import datatype.list.QuoteProprieta;
import enumeration.FormatoFile;
import enumeration.StatiGestoreCondominioAperto;
import store.TuttePersone;
import store.TuttiCondomini;
import store.POJO.Condominio;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;

public class GestoreCondominioAperto implements BaseExecutor {

	private AccedereCondominioAperto m_accedereCondominioAperto;
	private AccederePersone m_accederePersone;
	private AccedereTabelleMillesimali m_accedereTabelleMillesimali;
	private AccedereUnitaImmobiliari m_accedereUnitaImmobliari;
	private Avvisi m_avvisi;
	private Condominio m_condominio;
	private DatiTabellaMillesimale m_datiTabellaMillesimale;
	private TuttiCondomini m_dbCondomini;
	private GestoreBilanci m_gestoreBilanci;
	private GestoreCassa m_gestoreCassa;
	private GestorePagamenti m_gestorePagamenti;
	private QuoteProprieta m_nuoveQuote;
	private Persone m_nuoviProprietari;
	private StatiGestoreCondominioAperto m_state;
	private TabellaMillesimale m_tabellaMillesimale;
	private UnitaImmobiliare m_unitaImmobiliare;
	private DriverFileSystem m_driverFileSystem;
	

	public GestoreCondominioAperto(Condominio condominio) {
		m_dbCondomini.inizializza();
		m_condominio = condominio;
		m_accedereCondominioAperto = new AccedereCondominioAperto(this,m_condominio);
		m_avvisi = CalcolaAvvisi.calcolaAvvisi(m_condominio);
		m_accedereCondominioAperto.passaAvvisi(m_avvisi);
		m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
		m_driverFileSystem = new DriverFileSystem();
	}
	
	public void chiudiCondominio() {
		GestoreCondomini.getInstance().operazioneTerminata();
	}
	
	/* TODO : 
	 * EsitoEliminabile ?
	 * 
	*/
	public boolean eliminabile() {
		return true;
	}
	
	public void eliminaCondominio() {
		m_accedereCondominioAperto.ammissibile(eliminabile());
		m_state = StatiGestoreCondominioAperto.attesaConfermaEliminazione;
	}
	
	public void esportaCondominio(URL path) {
		m_driverFileSystem.salva(FormatoAmICo.daCondominioAFile(m_condominio),
				path, (BaseExecutor)this);
		m_state = StatiGestoreCondominioAperto.exportCondominio;
	}
	
	public void inserisciTabellaMillesimale(DatiTabellaMillesimale datiTabellaMillesimale) {
		m_datiTabellaMillesimale = datiTabellaMillesimale;
		m_accedereTabelleMillesimali.ammissibile(true);
		m_state = StatiGestoreCondominioAperto.attesaConfermaInserimentoTabellaMillesimale;
	}
	
	public void modificaPreferenze(Preferenze preferenze) {
		
	}
	
	public void modificaProprieta(UnitaImmobiliare unitaImmobliare) {
		m_unitaImmobiliare = unitaImmobliare;
		m_accedereUnitaImmobliari.aggiornaPersone(TuttePersone.PERSONE.recuperaPersone());
		m_state = StatiGestoreCondominioAperto.modificaProprieta;
	}
	
	public void modificaTabellaMillesimale(TabellaMillesimale tabellaMillesimale, DatiTabellaMillesimale datiTabellaMillesimale) {
		m_tabellaMillesimale = tabellaMillesimale;
		m_datiTabellaMillesimale = datiTabellaMillesimale;
		m_accedereTabelleMillesimali.ammissibile(true);
		m_state = StatiGestoreCondominioAperto.attesaConfermaModificaTabellaMillesimale;
	}
	
	public void operazioneAnnullata() {
		
	}
	
	public void operazioneTerminata() {
		switch (m_state) {
		default :
			m_accedereCondominioAperto.fatto();
			break;
				
		}
	}
	
	public void passaABilanci() {
		m_gestoreBilanci = new GestoreBilanci(m_condominio);
		m_state = StatiGestoreCondominioAperto.gestioneBilanci;
	}
	
	public void passaACassa() {
		m_gestoreCassa = new GestoreCassa(m_condominio);
		m_state = StatiGestoreCondominioAperto.gestioneCassa;
	}
	
	public void passaADatiCondomini() {
		m_accederePersone = new AccederePersone(this, m_condominio.recuperaCondomini());
		m_state = StatiGestoreCondominioAperto.gestioneDatiCondomini;
	}
	
	public void passaAPagamenti() {
		m_gestorePagamenti = new GestorePagamenti(m_condominio);
		m_state = StatiGestoreCondominioAperto.gestionePagamenti;
	}
/*
	public void generaReport(TipoReportCondominio tipoReportCondominio, FormatoFile formatoFile) {
	
		
	}
	*/
	
	public void passaAPreferenze() {
		m_state = StatiGestoreCondominioAperto.gestionePreferenze;
	}
	
	public void passaATabelleMillesimali() {
		m_accedereTabelleMillesimali = new AccedereTabelleMillesimali(self, m_condominio.recuperaTabelleMillesimali());
		m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
	}
	
	public void passaAUnitaImmobiliari() {
		m_accedereUnitaImmobliari = 
			new AccedereUnitaImmobliari(this, m_condominio.recuperaUnitaImmobiliari());
		m_state = StatiGestoreCondominioAperto.gestioneUnitaImmmobiliari;
	}
	
	
	public void passaProprieta(Persone persone, QuoteProprieta quoteProprieta) {
		
		/* TODO : qui è richiesto quoteProprieta.quoteOk(), ma credo che sia
		 * un sinonimo di quoteProprieta.controlla()
		 */
		if ( !quoteProprieta.controlla() ) {
			m_accedereUnitaImmobliari.ammissibile(false);
			return;
		}
		m_nuoviProprietari = persone;
		m_nuoveQuote = quoteProprieta;
		m_accedereUnitaImmobliari.ammissibile(true);
		m_state = StatiGestoreCondominioAperto.attesaConfermaProprieta;	
		
	}
	
	/* TODO: 
	 * TipoReportCondominio ?
	 * Report ?
	 *
	private Report preparaReportCondominio(TipoReportCondominio tipoReportCondominio) {
		
	}
	*/
	
	public void procedi(boolean procedere) {
		switch (m_state) {
		case attesaConfermaEliminazione :
			if ( !procedere ) {
				m_accedereCondominioAperto.fallito();
				m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
				break;
			}
			m_dbCondomini.eliminaCondominio(m_condominio);
			m_accedereCondominioAperto.fatto();
			GestoreCondomini.getInstance().m_accedereCondomini.aggiornaCondomini(TuttiCondomini.CONDOMINI);
			break;
		case attesaConfermaProprieta :
			if (!procedere) {
				m_state = StatiGestoreCondominioAperto.gestioneUnitaImmmobiliari;
				break;
			}
			m_unitaImmobiliare.modificaProprieta(m_nuoviProprietari, m_nuoveQuote);		
			break;
		case attesaConfermaInserimentoTabellaMillesimale :
			if (!procedere) {
				m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
				break;
			}
			m_condominio.inserisciTabellaMillesimale(new TabellaMillesimale(m_datiTabellaMillesimale));
			m_accedereTabelleMillesimali.fatto();
			m_accedereTabelleMillesimali.aggiornaTabelleMillesimali(m_condominio.recuperaTabelleMillesimali());
			m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
			break;
		case attesaConfermaModificaTabellaMillesimale :
			if (!procedere) {
				m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
				break;
			}
			m_tabellaMillesimale.modificaDati(m_datiTabellaMillesimale);
			m_accedereTabelleMillesimali.fatto();
			m_accedereTabelleMillesimali.aggiornaTabelleMillesimali(m_condominio.recuperaTabelleMillesimali());
			m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
			break;
		}
	}
	
	
}
