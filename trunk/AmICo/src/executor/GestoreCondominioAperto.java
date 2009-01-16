package executor;

import java.net.URL;

import boundary.AccedereCondominioAperto;
import datatype.DatiTabellaMillesimale;
import datatype.Preferenze;
import datatype.list.Avvisi;
import datatype.list.Persone;
import datatype.list.QuoteProprietà;
import enumeration.FormatoFile;
import enumeration.StatiGestoreCondominioAperto;
import store.TuttiCondomini;
import store.POJO.Condominio;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;

public class GestoreCondominioAperto implements BaseExecutor {

	private Condominio m_condominio;
/*	private Reali m_nuoveQuote; // TODO: Reali? */
	private Persone m_nuoviProprietari;
	private DatiTabellaMillesimale m_datiTabellaMillesimale;
	private TabellaMillesimale m_tabellaMillesimale;
	private AccedereCondominioAperto m_accedereCondominioAperto;
	private Avvisi m_avvisi;
	private StatiGestoreCondominioAperto m_state;
	private AccederePersone m_accederePersone;
	private GestoreBilanci m_gestoreBilanci;
	private GestoreCassa m_gestoreCassa;
	private GestorePagamenti m_gestorePagamenti;
	

	public GestoreCondominioAperto(Condominio condominio) {
		m_condominio = condominio;
		m_accedereCondominioAperto = new AccedereCondominioAperto(this,m_condominio);
		m_avvisi = CalcolaAvvisi.calcolaAvvisi(m_condominio);
		m_accedereCondominioAperto.passaAvvisi(m_avvisi);
		m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
	}
	
	public void procedi(boolean procedere) {
		switch (m_state) {
		case attesaConfermaEliminazione :
			if ( !procedere ) {
				m_accedereCondominioAperto.fallito();
				m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
				break;
			}
			/*
			 * TODO : CONDOMINI.eliminaCondominio(m_condominio);
			 * 
			 */
			m_accedereCondominioAperto.fatto();
			GestoreCondominio.getInstance().m_accedereCondomini.aggiornaCondomini(TuttiCondomini.CONDOMINI);
			break;
		}
	}
	
	public void operazioneTerminata() {
		switch (m_state) {
		default :
			m_accedereCondominioAperto.fatto();
			break;
				
		}
	}
	
	public void operazioneAnnullata() {
		
	}
	
	public void passaAUnitaImmobiliari() {
		
	}
	
	public void modificaProprieta(UnitaImmobiliare unitaImmobliare) {
		
	}
	
	public void passaProprieta(Persone persone, QuoteProprietà quoteProprieta) {
		
	}
	
	public void passaATabelleMillesimali() {
		
	}
	
	public void inserisciTabellaMillesimale(DatiTabellaMillesimale datiTabellaMIllesimale) {
		
	}
	
	public void modificaTabellaMillesimale(TabellaMillesimale tabellaMillesimale, DatiTabellaMillesimale datiTabellaMillesimale) {
		
	}
	
	public void passaADatiCondomini() {
		m_accederePersone = new AccederePersone(this, m_condominio.recuperaCondomini());
		m_state = StatiGestoreCondominioAperto.gestioneDatiCondomini;
	}
	
	public void passaABilanci() {
		m_gestoreBilanci = new GestoreBilanci(m_condominio);
		m_state = StatiGestoreCondominioAperto.gestioneBilanci;
	}
	
	public void passaACassa() {
		m_gestoreCassa = new GestoreCassa(m_condominio);
		m_state = StatiGestoreCondominioAperto.gestioneCassa;
	}
	
	public void passaAPreferenze() {
		m_state = StatiGestoreCondominioAperto.gestionePreferenze;
	}
	
	public void modificaPreferenze(Preferenze preferenze) {
		
	}
	
	public void passaAPagamenti() {
		m_gestorePagamenti = new GestorePagamenti(m_condominio);
		m_state = StatiGestoreCondominioAperto.gestionePagamenti;
	}
	/* TODO: TipoReportCondominio ? 
	public void generaReport(TipoReportCondominio tipoReportCondominio, FormatoFile formatoFile) {
		
	}
	*/
	
	public void chiudiCondominio() {
		GestoreCondominio.getInstance().operazioneTerminata();
	}
	
	public void eliminaCondominio() {
		m_accedereCondominioAperto.ammissibile(eliminabile());
		m_state = StatiGestoreCondominioAperto.attesaConfermaEliminazione;
	}
	
	
	/* TODO:
	 * Path potrebbe essere cambiato in java.net.URL
	*/
	public void esportaCondominio(URL path) {
		DriverFileSystem.salva(FormatoAmICo.daCondominioAFile(m_condominio),
				path, this);
		m_state = StatiGestoreCondominioAperto.exportCondominio;
	}
	
	/* TODO: 
	 * TipoReportCondominio ?
	 * Report ?
	 *
	private Report preparaReportCondominio(TipoReportCondominio tipoReportCondominio) {
		
	}
	*/
	
	/* TODO : 
	 * EsitoEliminabile ?
	 * 
	*/
	public boolean eliminabile() {
		return true;
	}
	
	
}
