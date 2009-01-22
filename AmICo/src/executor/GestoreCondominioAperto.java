package executor;

import java.net.URL;

import store.TuttePersone;
import store.TuttiCondomini;
import store.POJO.Bilancio;
import store.POJO.Condominio;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;
import boundary.AccedereCondominioAperto;
import boundary.AccederePersone;
import boundary.AccedereTabelleMillesimali;
import boundary.AccedereUnitaImmobiliari;
import boundary.AmICo;
import boundary.DriverFileSystem;
import calculator.CalcolaAvvisi;
import calculator.FormatoAmICo;
import datatype.DatiTabellaMillesimale;
import datatype.Preferenze;
import datatype.list.Avvisi;
import datatype.list.Persone;
import datatype.list.Percentuali;
import enumeration.StatiGestoreCondominioAperto;

public class GestoreCondominioAperto implements BaseExecutor {

	private AccedereCondominioAperto m_accedereCondominioAperto;
	private AccederePersone m_accederePersone;
	private AccedereTabelleMillesimali m_accedereTabelleMillesimali;
	private AccedereUnitaImmobiliari m_accedereUnitaImmobiliari;
	private AmICo m_amico;
	private Avvisi m_avvisi;
	private Condominio m_condominio;
	private DatiTabellaMillesimale m_datiTabellaMillesimale;
	private TuttiCondomini m_dbCondomini;
	private TuttePersone m_dbPersone;
	private DriverFileSystem m_driverFileSystem;
	private GestoreBilanci m_gestoreBilanci;
	private GestoreCassa m_gestoreCassa;
	private GestorePagamenti m_gestorePagamenti;
	private Percentuali m_nuoveQuote;
	private Persone m_nuoviProprietari;
	private StatiGestoreCondominioAperto m_state;
	private TabellaMillesimale m_tabellaMillesimale;
	private UnitaImmobiliare m_unitaImmobiliare;
	

	{
		m_accedereTabelleMillesimali = new AccedereTabelleMillesimali(this, 
				m_condominio.recuperaTabelleMillesimali(), 
				m_condominio.recuperaUnitaImmobiliari());
		m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
	}
	
	public GestoreCondominioAperto(Condominio condominio) {
		m_condominio = condominio;
		m_accedereCondominioAperto = new AccedereCondominioAperto(this,m_condominio);
		m_avvisi = CalcolaAvvisi.calcolaAvvisi(m_condominio);
		m_accedereCondominioAperto.passaAvvisi(m_avvisi);
		m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
		m_driverFileSystem = DriverFileSystem.getInstance();
		m_amico = AmICo.getInstance();
		m_dbPersone = TuttePersone.getInstance();
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
	
	public void inserisciNuovaPersona() {
		// TODO Auto-generated method stub
		
	}
	
	public void inserisciTabellaMillesimale(DatiTabellaMillesimale datiTabellaMillesimale, Percentuali millesimi) {
		if (!datiTabellaMillesimale.controlla()) {
			m_accedereTabelleMillesimali.ammissibile(false);
			m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
			return;
		}
		
		m_datiTabellaMillesimale = datiTabellaMillesimale;
		m_accedereTabelleMillesimali.ammissibile(true);
		m_state = StatiGestoreCondominioAperto.attesaConfermaInserimentoTabellaMillesimale;
// TODO : from 3.2 -- REMOVEME
//		m_condominio.inserisciTabellaMillesimale(new TabellaMillesimale(datiTabellaMillesimale));
//		m_accedereTabelleMillesimali.aggiornaTabelleMillesimali(m_condominio.recuperaTabelleMillesimali());
	}
	
	public void modificaPreferenze(Preferenze preferenze) {
		
	}
	
	public void modificaProprieta(UnitaImmobiliare unitaImmobliare) {
		m_unitaImmobiliare = unitaImmobliare;
		/* TODO :
		 * Nella SM viene usato TuttePersone.PERSONE, non conviene cercare 
		 * solo le persone di quel condominio? 
		 */
		m_accedereUnitaImmobiliari.aggiornaPersone(m_dbPersone.recuperaPersone());
		m_state = StatiGestoreCondominioAperto.modificaProprieta;
	}
	
	public void modificaTabellaMillesimale(TabellaMillesimale tabellaMillesimale, String descr, Percentuali nuoviMillesimi) {
		m_tabellaMillesimale = tabellaMillesimale;
		m_tabellaMillesimale.modificaTabella(descr, nuoviMillesimi);
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
/*
	public void generaReport(TipoReportCondominio tipoReportCondominio, FormatoFile formatoFile) {
	
		
	}
	*/
	
	public void passaADatiCondomini() {
		m_accederePersone = new AccederePersone(this, m_condominio.recuperaCondomini());
		m_state = StatiGestoreCondominioAperto.gestioneDatiCondomini;
	}
	
	public void passaAPagamenti() {
		m_gestorePagamenti = new GestorePagamenti(m_condominio);
		m_state = StatiGestoreCondominioAperto.gestionePagamenti;
	}
	
	public void passaAPreferenze() {
		m_state = StatiGestoreCondominioAperto.gestionePreferenze;
	}
	
	
	public void passaAUnitaImmobiliari() {
		m_accedereUnitaImmobiliari = 
			new AccedereUnitaImmobiliari(this, m_condominio.recuperaUnitaImmobiliari());
		m_state = StatiGestoreCondominioAperto.gestioneUnitaImmmobiliari;
	}
	
	/* TODO: 
	 * TipoReportCondominio ?
	 * Report ?
	 *
	private Report preparaReportCondominio(TipoReportCondominio tipoReportCondominio) {
		
	}
	*/
	
	public void passaProprieta(Persone persone, Percentuali quoteProprieta) {
		
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

	public void procedi(boolean procedere) {
		switch (m_state) {
		case attesaConfermaInserimentoTabellaMillesimale :
				if (!procedere) {
					m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
					break;
				}
				/* TODO :
				 * Nella SM : codnominio.inserisciTabellaMillesimale(creaTabellaMillesimale(datiT, millesimi).
				 *  - I dati in DatiTabellaMillesimale non comprendono già i millesimi?
				 *  - Se no, come passare i millesimi alla nuova TabellaMillesimale ?
				 * 
				 */
				m_condominio.inserisciTabellaMillesimale(new TabellaMillesimale(m_datiTabellaMillesimale,m_nuoveQuote));
				m_accedereTabelleMillesimali.aggiornaTabelleMillesimali(m_condominio.recuperaTabelleMillesimali());
				m_state = 	StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
				break;	
		case attesaConfermaEliminazione :
			if ( !procedere ) {
				m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
				break;
			}
			m_dbCondomini.eliminaCondominio(m_condominio);
			m_amico.aggiornaCondomini(m_dbCondomini.recuperaCondomini());
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
	
	
}
