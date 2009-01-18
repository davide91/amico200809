package executor;

import java.io.File;
import java.net.URL;

import boundary.AccederePersone;
import boundary.InserirePersona;
import datatype.DatiCondominio;
import datatype.DatiTabellaMillesimale;
import datatype.DatiUnitaImmobiliare;
import datatype.list.Condomini;
import datatype.list.Persone;
import datatype.list.QuoteProprieta;
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
	
	public  AccederePersone m_AccederePersone;
	private Avvio m_avvio;
	private Condominio m_condominio;
	private DatiCondominio m_datiCondominio;
	private GestoreCondominioAperto m_gestoreCondominioAperto;
	private InserirePersona m_inserireNuovoCondominio;
	private InserireUnitaImmobliare m_inserireUnitaImmobiliare;
	private StatiGestoreCondominio m_state;
	private TuttiCondomini m_dbCondomini;
	
	private TabellaMillesimale m_tabellaMillesimaleProprieta;
	
	private UnitaImmobiliare m_unitaImmobiliare;
	
	private GestoreCondomini()
	{
		m_AccederePersone = new AccederePersone(TuttiCondomini.CONDOMINI);
		m_state = StatiGestoreCondominio.gestoreCondomini;
		m_dbCondomini.inizializza();
	}
	
	public void impostaAvvio(Avvio avvio)
	{
		m_avvio = avvio;
	}
	
	public void apriCondominio(Condominio condominio) {
		if ( m_state != StatiGestoreCondominio.gestoreCondomini ) return;
		
		m_gestoreCondominioAperto = new GestoreCondominioAperto(condominio);
		m_condominio = condominio;
		m_state = StatiGestoreCondominio.condominioAperto;
	}
	
	private boolean condominioGiaInserito(DatiCondominio datiCondominio) {
		Condominio toCheck = new Condominio();
		toCheck.modificaDati(datiCondominio);
		return m_dbCondomini.recuperaCondomini().getCondomini().contains(toCheck);
	}
	
	public void esciDaAmico() {
		if (m_state == StatiGestoreCondominio.gestoreCondomini)
			m_avvio.esciDaAmICo();
	}
	
	public void finito() {
		m_state = StatiGestoreCondominio.inserimentoTabellaMillesimaleProprietà;
	}
	
	public void importaCondominio(URL path) {
		if (m_state == StatiGestoreCondominio.gestoreCondomini) {
			DriverFileSystem.leggi(path);
			m_state = StatiGestoreCondominio.attesaSelezioneFile;
		}
	}
	
	public void inserisciCondominio() {
		if ( m_state != StatiGestoreCondominio.gestoreCondomini ) return;
		
		m_inserireNuovoCondominio = new InserireNuovoCondominio();
		m_condominio = new Condominio();
		m_dbCondomini.inserisciCondominio(m_condominio);
	}
	
	public void inserisciUnitaImmobliare() {
		m_unitaImmobiliare = new UnitaImmobiliare();
		m_condominio.inserisciUnitàImmobiliare(m_unitaImmobiliare);
		m_inserireUnitaImmobiliare = new InserireUnitàImmobliare();
		m_state = StatiGestoreCondominio.inserimentoUnitàImmobliare;
	}
	
	public void operazioneAnnullata() {
		if ( m_state == StatiGestoreCondominio.inserimentoCondominio ) {
			m_dbCondomini.eliminaCondominio(m_condominio);
		}
	}
	
	public void operazioneTerminata() {
		if ( m_state == StatiGestoreCondominio.condominioAperto )
			m_state = StatiGestoreCondominio.gestoreCondomini;
	}
	
	public void passaDatiCondominio(DatiCondominio datiCondominio) {
		if ( condominioGiaInserito(datiCondominio) ) {
			m_inserireNuovoCondominio.ammissibile(false);
			return;
		}
		
		m_inserireNuovoCondominio.ammissibile(true);
		m_datiCondominio = datiCondominio;
		m_state = StatiGestoreCondominio.attesaConfermaDatiCondominio;
	}
		
	public void passaTabellaMillesimaleProprieta(DatiTabellaMillesimale millesimi) {
		m_tabellaMillesimaleProprieta = new TabellaMillesimale(millesimi);
		m_inserireNuovoCondominio.ammissibile(true);
		m_state = StatiGestoreCondominio.attesaConfermaTabellaMillesimale;
	}
		
	public void passaDatiUnitaImmobliare(DatiUnitaImmobiliare datiUnitaImmobliare) {
		if ( unitaImmobiliareGiaInserita(datiUnitaImmobliare) ) {
			m_inserireUnitaImmobiliare.ammissibile(false);
			m_condominio.eliminaUnitàImmobiliare(m_unitaImmobiliare);
			return;
		}
		
		m_unitaImmobiliare.modificaDati(datiUnitaImmobliare);
		m_inserireUnitaImmobiliare.ammissible(true);
		m_state = StatiGestoreCondominio.inserimentoProprietà;
		m_inserireUnitaImmobiliare.aggiornaPersone(TuttePersone.PERSONE);
	}
	
	public void passaFile(File file)
	{
		switch (m_state) {
			case attesaSelezioneFile :
				if ( ! FormatoAmICo.fileInFormatoAmICo(file) ) {
					m_AccederePersone.fallito();
					return;
				}
				m_condominio = FormatoAmICo.daFileACondominio(file);
				m_dbCondomini.inserisciCondominio(m_condominio);
				m_AccederePersone.aggiornaCondomini(TuttiCondomini.CONDOMINI);
				m_AccederePersone.fatto();
				m_state = StatiGestoreCondominio.condominioAperto;
				break;
		}
	}
	
	public void passaProprieta(Persone persone, QuoteProprietà quoteProprieta) {
		/* TODO:
		 * Nella SM di GestoreCondominio appare quote.controlla(), ma QuoteProprietà 
		 * non contiene il metodo "controlla()"
		 */
		if ( ! quoteProprieta.controlla() ) {
			m_inserireUnitaImmobiliare.ammissibile(false);
			return;
		}
		
		/* TODO:
		 * Nella SM di GestoreCondominio appare 
		 * unitàImmobiliare.modificaProprieta(proprietari, quote), ma non compare
		 * nella classe UnitàImmobliare
		 */
		m_unitaImmobiliare.modificaProprieta(persone, quoteProprieta);
		m_inserireUnitaImmobiliare.ammissibile(true);
		m_state = StatiGestoreCondominio.attesaConferma;
	}

	
	public void procedi(boolean procedere) {
		switch (m_state) {
			case attesaConfermaDatiCondominio :
				if ( !procedere ) {
					m_state = StatiGestoreCondominio.inserimentoCondominio;
					break;
				}
				m_condominio.modificaDati(m_datiCondominio);
				m_state = StatiGestoreCondominio.inserimentoUnitaImmobiliari;
				break;
			case attesaConfermaTabellaMillesimale :
				if ( !procedere ) {
					m_state = StatiGestoreCondominio.gestoreCondomini;
					m_dbCondomini.eliminaCondominio(m_condominio);
					break;
				}
				m_condominio.inserisciTabellaMillesimale(m_tabellaMillesimaleProprieta);
				m_AccederePersone.aggiornaCondomini(TuttiCondomini.CONDOMINI);
				m_gestoreCondominioAperto = new GestoreCondominioAperto(m_condominio);
				m_inserireNuovoCondominio.fatto();
				m_AccederePersone.fatto();
				m_state = StatiGestoreCondominio.condominioAperto;
				break;
			case attesaConferma :
				if ( !procedere ) {
					m_inserireUnitaImmobiliare.fallito();
					m_condominio.eliminaUnitàImmobiliare(m_unitaImmobiliare);
					break;
				}
				m_inserireUnitaImmobiliare.fatto();
				m_inserireNuovoCondominio.aggiornaUnitàImmobliari(m_condominio.recuperaUnitàImmobiliari());
				break;
		}
	}
	
	
	public void settaCondomini(Condomini condomini) {
		/*
		 * TODO : Totalemente inutilizzata nella SM, che fare?
		 */
	}
	
	private boolean unitaImmobiliareGiaInserita(DatiUnitaImmobiliare datiUnitaImmobliare) {	 
		UnitàImmobiliari uImmobiliari = m_condominio.recuperaUnitàImmobiliari();
		UnitaImmobiliare newUnit = new UnitaImmobiliare();
		newUnit.modificaDati(datiUnitaImmobliare);
		
		return uImmobiliari.getImmobili().contains(newUnit);
	}
}
