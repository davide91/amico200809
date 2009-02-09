	package executor;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.JTabbedPane;

import store.TuttePersone;
import store.TuttiCondomini;
import store.POJO.Bilancio;
import store.POJO.Condominio;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;
import boundary.AccedereCondominioAperto;
import boundary.AccederePersone;
import boundary.AccederePreferenze;
import boundary.AccedereTabelleMillesimali;
import boundary.AccedereUnitaImmobiliari;
import boundary.AmICo;
import boundary.DatiGenerali;
import boundary.DriverFileSystem;
import calculator.CalcoloAvvisi;
import calculator.FormatoAmICo;
import datatype.DatiTabellaMillesimale;
import datatype.Preferenze;
import datatype.list.Avvisi;
import datatype.list.Millesimi;
import datatype.list.Persone;
import datatype.list.Percentuali;
import datatype.list.UnitaImmobiliari;
import enumeration.StatiGestoreCondominioAperto;

public class GestoreCondominioAperto implements BaseExecutor {

	private AccedereCondominioAperto m_accedereCondominioAperto;
	private AccederePersone m_accederePersone;
	private AccedereTabelleMillesimali m_accedereTabelleMillesimali;
	private AccedereUnitaImmobiliari m_accedereUnitaImmobiliari;
	private Avvisi m_avvisi;
	private Condominio m_condominio;
	private DatiTabellaMillesimale m_datiTabellaMillesimale;
	private GestoreBilanci m_gestoreBilanci;
	private GestoreCassa m_gestoreCassa;
	private GestorePagamenti m_gestorePagamenti;
	private Percentuali m_nuoveQuote;
	private Millesimi m_nuoviMillesimi;
	private Persone m_nuoviProprietari;
	private StatiGestoreCondominioAperto m_state;
	private TabellaMillesimale m_tabellaMillesimale;
	private UnitaImmobiliare m_unitaImmobiliare;
	
	public GestoreCondominioAperto(Condominio condominio) {
		m_condominio = condominio;
		m_accedereCondominioAperto = new AccedereCondominioAperto(this,m_condominio);
		m_avvisi = CalcoloAvvisi.calcolaAvvisi(m_condominio);
		m_accedereCondominioAperto.passaAvvisi(m_avvisi);
		m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;

		inserisciTabbedPanel();	
	}
	
	
	public void chiudiCondominio() {
		AmICo.getInstance().setVisible(true);
		m_condominio = null;
		GestoreCondomini.getInstance().operazioneTerminata();	
	}
	
	public boolean eliminabile() {
		for ( Bilancio bilancio : m_condominio.recuperaBilanci().getBilanci() ) {
			if (!bilancio.terminabile()) return false;
		}
		return true;
	}	
	
	public void eliminaCondominio() {
		if(eliminabile()) {
			m_state = StatiGestoreCondominioAperto.attesaConfermaEliminazione;
			m_accedereCondominioAperto.ammissibile(true);
		}
		else 
			m_accedereCondominioAperto.ammissibile(false);
		
	}
	
	public void esportaCondominio(String path) {
		DriverFileSystem.getInstance().salva(FormatoAmICo.daCondominioAFile(m_condominio),
				path, (BaseExecutor)this);
		m_state = StatiGestoreCondominioAperto.exportCondominio;
	}
	
	public Condominio getCondominio() {
		return m_condominio;
	}
	
	public void inserisciTabbedPanel(){

		JTabbedPane pannelloTab = new JTabbedPane();
		m_accedereUnitaImmobiliari= new AccedereUnitaImmobiliari(this,m_condominio.recuperaUnitaImmobiliari(),TuttePersone.getInstance().recuperaPersone());
		m_accedereTabelleMillesimali=new AccedereTabelleMillesimali(this,m_condominio.recuperaTabelleMillesimali(),m_condominio.recuperaUnitaImmobiliari());
		pannelloTab.addTab("Dati Generali",new DatiGenerali(m_condominio));
		pannelloTab.addTab("Unita' Immobiliari",m_accedereUnitaImmobiliari);
		pannelloTab.addTab("Tabelle Millesimali",m_accedereTabelleMillesimali);
		pannelloTab.addTab("Preferenze", new AccederePreferenze(m_condominio.getPreferenze(),this));
		m_accedereCondominioAperto.getPannello().add(pannelloTab);

	}
	
	public void inserisciTabellaMillesimale(DatiTabellaMillesimale datiTabellaMillesimale,Millesimi millesimi) {
		m_datiTabellaMillesimale = datiTabellaMillesimale;
		m_nuoviMillesimi = millesimi;
		m_state = StatiGestoreCondominioAperto.attesaConfermaInserimentoTabellaMillesimale;
	}
	
	public void modificaPreferenze(Preferenze preferenze) {
		m_condominio.modificaPreferenze(preferenze);	
		m_accedereCondominioAperto.passaPreferenze(preferenze);
	}
	
	public void modificaProprieta(UnitaImmobiliare unitaImmobliare) {
		m_unitaImmobiliare = unitaImmobliare;
		m_accedereUnitaImmobiliari.aggiornaPersone(TuttePersone.getInstance().recuperaPersone());
		m_state = StatiGestoreCondominioAperto.modificaProprieta;
	}
	
	public void modificaTabellaMillesimale(TabellaMillesimale tabellaMillesimale, String descr, Millesimi nuoviMillesimi) {
		m_tabellaMillesimale = tabellaMillesimale;
		m_tabellaMillesimale.modificaTabella(descr, nuoviMillesimi);
		m_accedereTabelleMillesimali.aggiornaTabelleMillesimali(m_condominio.recuperaTabelleMillesimali());
	}
	
	public void operazioneAnnullata() {
		m_accedereCondominioAperto.setVisible(true);
	}
	
	public void operazioneTerminata() {
		
		switch (m_state) {
		default :
				m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
				m_accedereCondominioAperto.setVisible(true);
				break;
		}
	}
	
	public void passaABilanci() {
		m_gestoreBilanci = new GestoreBilanci(m_condominio, m_accedereCondominioAperto);
		m_state = StatiGestoreCondominioAperto.gestioneBilanci;
	}
	
	public void passaACassa() {
		
		m_gestoreCassa = new GestoreCassa(m_condominio.recuperaCassa());
		m_state = StatiGestoreCondominioAperto.gestioneCassa;
	}
	
/*	public void generaReport(TipoReportCondominio tipoReportCondominio, FormatoFile formatoFile) {
	
		
	}
	*/
	
	public void passaADatiCondomini() {
		m_state = StatiGestoreCondominioAperto.gestioneDatiCondomini;
		//recupero di nuovo il condominio
		m_condominio = TuttiCondomini.getInstance().recuperaCondominio(m_condominio.getDatiC().getId());
		m_accederePersone = new AccederePersone(this, m_condominio.recuperaCondomini(),m_accedereCondominioAperto);
		
		/* FIXME : executor VS UI */
		m_accedereCondominioAperto.getPannello().add(m_accederePersone);
	}
	
	public void passaAPagamenti() {
		m_gestorePagamenti = new GestorePagamenti(m_condominio);
		m_state = StatiGestoreCondominioAperto.gestionePagamenti;
	}
	
	public void passaAPreferenze() {
		m_state = StatiGestoreCondominioAperto.gestionePreferenze;
	
		/*** FIXME : inserito dal design 3.6.3 **/
		m_accedereCondominioAperto.passaPreferenze(m_condominio.getPreferenze());
		
	}
	
	
	public void passaATabelleMillesimali()
	{
		/* FIXME : utilizzato nel design 3.6.3 ... chi l'ha commentato e perché ?
		m_accedereTabelleMillesimali = new AccedereTabelleMillesimali(this, 
				m_condominio.recuperaTabelleMillesimali(), 
				m_condominio.recuperaUnitaImmobiliari());
		*/
		m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
	}
	
	/* TODO: 
	 * TipoReportCondominio ?
	 * Report ?
	 *
	private Report preparaReportCondominio(TipoReportCondominio tipoReportCondominio) {
		
	}
	*/
	
	public void passaAUnitaImmobiliari() {
		
	/* FIXME : previsto nel design 3.6.3 -> commentato, ma perchè?	
	 * m_accedereUnitaImmobiliari = 	
			new AccedereUnitaImmobiliari(this, m_condominio.recuperaUnitaImmobiliari());
			
	*/
		m_accedereCondominioAperto.setVisible(false);
		m_state = StatiGestoreCondominioAperto.gestioneUnitaImmmobiliari;
	}

	public void passaProprieta(Persone persone, Percentuali quoteProprieta) {
		if ( quoteProprieta.somma() != 100.0  ) {
			m_state = StatiGestoreCondominioAperto.gestioneUnitaImmmobiliari;
			m_accedereUnitaImmobiliari.ammissibile(false);
			return;
		}
		m_state = StatiGestoreCondominioAperto.attesaConfermaProprieta;	
		m_nuoviProprietari = persone;
		m_nuoveQuote= quoteProprieta;
		m_accedereUnitaImmobiliari.ammissibile(true);	
	}
	
	public void procedi(boolean procedere) {
		switch (m_state) {
		case attesaConfermaInserimentoTabellaMillesimale :
				if (!procedere) {
					m_state = StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
					break;
				}
				m_condominio.inserisciTabellaMillesimale(new TabellaMillesimale(m_datiTabellaMillesimale,m_nuoviMillesimi));
				m_accedereTabelleMillesimali.aggiornaTabelleMillesimali(m_condominio.recuperaTabelleMillesimali());
				m_state = 	StatiGestoreCondominioAperto.gestioneTabelleMillesimali;
				break;	
		case attesaConfermaEliminazione :
			if ( !procedere ) {
				m_state = StatiGestoreCondominioAperto.gestioneCondominioAperto;
				break;
			}
			TuttiCondomini.getInstance().eliminaCondominio(m_condominio);
			
			/* FIXME : Linea non presente dal design 3.5.4 */
			AmICo.getInstance().aggiornaCondomini(TuttiCondomini.getInstance().recuperaCondomini());
			
			GestoreCondomini.getInstance().operazioneTerminata();
			AmICo.getInstance().setVisible(true);
			break;
		case attesaConfermaProprieta :
			m_state = StatiGestoreCondominioAperto.gestioneUnitaImmmobiliari;
			if (procedere) 
				m_unitaImmobiliare.modificaProprieta(m_nuoviProprietari, m_nuoveQuote);		
				
			break;
		}
	}
	
	public void visibile(boolean b)
	{
		m_accedereCondominioAperto.setVisible(b);
	}
	
}
