package executor;

import java.net.URL;

import calculator.CalcolaAvvisi;
import calculator.FormatoAmICo;

import boundary.AccedereCondomini;
import boundary.AccedereCondominioAperto;

import boundary.AccederePersone;
import boundary.AccedereTabelleMillesimali;

import boundary.AccedereCondomini;
import boundary.AccedereTabelleMillesimali;
import boundary.AccedereUnitaImmobiliari;

import boundary.DriverFileSystem;
import datatype.DatiTabellaMillesimale;
import datatype.Preferenze;
import datatype.list.Avvisi;
import datatype.list.Bilanci;
import datatype.list.Condomini;
import datatype.list.Persone;
import datatype.list.QuoteProprieta;
import datatype.list.Reali;
import enumeration.FormatoFile;
import enumeration.StatiGestoreCondominioAperto;
import store.TuttePersone;
import store.TuttiCondomini;
import store.POJO.Bilancio;
import store.POJO.Condominio;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;

public class GestoreCondominioAperto implements BaseExecutor {

	private AccedereCondominioAperto m_accedereCondominioAperto;
	private AccederePersone m_accedereCondomini;
	private AccedereTabelleMillesimali m_accedereTabelleMillesimali;
	private AccedereUnitaImmobiliari m_accedereUnitaImmobiliari;
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
		m_dbCondomini.inizializzaPersone();
		m_condominio = condominio;
		m_accedereCondominioAperto = new AccedereCondominioAperto();
		m_accedereCondominioAperto.creaAccedereCondominioAperto(this, m_condominio);
		m_avvisi = CalcolaAvvisi.calcolaAvvisi(m_condominio);
		m_accedereCondominioAperto.passaAvvisi(m_avvisi);
		m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
		m_driverFileSystem = new DriverFileSystem();
	}
	
	public void chiudiCondominio() {
		GestoreCondomini.getInstance().operazioneTerminata();
	}
	
	public boolean eliminabile() {
		for ( Bilancio bilancio : m_condominio.recuperaBilanciInEsercizio().getBilanci() ) {
			if (!bilancio.terminabile()) return false;
		}
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
	
	public void inserisciTabellaMillesimale(DatiTabellaMillesimale datiTabellaMillesimale, Reali millesimi) {
		m_datiTabellaMillesimale = datiTabellaMillesimale;
		m_condominio.inserisciTabellaMillesimale(new TabellaMillesimale(datiTabellaMillesimale));
		m_accedereTabelleMillesimali.aggiornaTabelleMillesimali(m_condominio.recuperaTabelleMillesimali());
	}
	
	public void modificaPreferenze(Preferenze preferenze) {
		
	}
	
	public void modificaProprieta(UnitaImmobiliare unitaImmobliare) {
		m_unitaImmobiliare = unitaImmobliare;
		m_accedereUnitaImmobiliari.aggiornaPersone(TuttePersone.PERSONE);
		m_state = StatiGestoreCondominioAperto.modificaProprieta;
	}
	
	public void modificaTabellaMillesimale(TabellaMillesimale tabellaMillesimale, String descr, QuoteProprieta quote) {
		m_tabellaMillesimale = tabellaMillesimale;
		m_tabellaMillesimale.modificaTabella(descr, quote);
		m_accedereTabelleMillesimali.aggiornaTabelleMillesimali(m_condominio.recuperaTabelleMillesimali());
	}
	
	public void operazioneAnnullata() {
		
	}
	
	public void operazioneTerminata() {
		switch (m_state) {
		default :
				m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
				break;
		}
	}
	
	public void passaABilanci() {
		m_gestoreBilanci = new GestoreBilanci(m_condominio);
		m_state = StatiGestoreCondominioAperto.gestioneBilanci;
	}
	
	public void passaACassa() {
		m_gestoreCassa = new GestoreCassa(m_condominio.recuperaCassa());
		m_state = StatiGestoreCondominioAperto.gestioneCassa;
	}
	
	public void passaADatiCondomini() {
		m_accedereCondomini = new AccedereCondomini(this, m_condominio.recuperaCondomini());
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
		m_accedereTabelleMillesimali = new AccedereTabelleMillesimali(this, 
				m_condominio.recuperaTabelleMillesimali(), 
				m_condominio.recuperaUnitaImmobiliari());
		m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
	}
	
	public void passaAUnitaImmobiliari() {
		m_accedereUnitaImmobiliari = 
			new AccedereUnitaImmobiliari(this, m_condominio.recuperaUnitaImmobiliari());
		m_state = StatiGestoreCondominioAperto.gestioneUnitaImmmobiliari;
	}
	
	
	public void passaProprieta(Persone persone, QuoteProprieta quoteProprieta) {
		
		/* TODO : qui è richiesto quoteProprieta.quoteOk(), ma credo che sia
		 * un sinonimo di quoteProprieta.controlla()
		 */
		if ( !quoteProprieta.controlla() ) {
			m_accedereUnitaImmobiliari.ammissibile(false);
			return;
		}
		m_nuoviProprietari = persone;
		m_nuoveQuote = quoteProprieta;
		m_accedereUnitaImmobiliari.ammissibile(true);
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
				m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
				break;
			}
			m_dbCondomini.eliminaCondominio(m_condominio);
			m_accedereCondomini.aggiornaCondomini(TuttiCondomini.CONDOMINI);
			break;
		case attesaConfermaProprieta :
			if (!procedere) {
				m_state = StatiGestoreCondominioAperto.gestioneUnitaImmmobiliari;
				break;
			}
			m_unitaImmobiliare.modificaProprieta(m_nuoviProprietari, m_nuoveQuote);		
			break;
		}
	}

	public void inserisciNuovaPersona() {
		// TODO Auto-generated method stub
		
	}
	
	
}
