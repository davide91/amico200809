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
import datatype.list.Reali;
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
	private Avvio m_avvio;
	private Condominio m_condominio;
	private DatiCondominio m_datiCondominio;
	private GestoreCondominioAperto m_gestoreCondominioAperto;
	private InserireNuovoCondominio m_inserireNuovoCondominio;
	private InserireUnitaImmobiliare m_inserireUnitaImmobiliare;
	private StatiGestoreCondominio m_state;
	private TuttiCondomini m_dbCondomini;
	private DriverFileSystem m_driverFS;
	
	private TabellaMillesimale m_tabellaMillesimaleProprieta;
	
	private UnitaImmobiliare m_unitaImmobiliare;
	
	private GestoreCondomini()
	{
		m_amico = AmICo.getInstance();
		m_dbCondomini=new TuttiCondomini();
		
		m_amico.aggiornaCondomini(m_dbCondomini.recuperaCondomini());
		m_state = StatiGestoreCondominio.gestoreCondomini;
		m_driverFS = new DriverFileSystem();
		
		
		//m_dbCondomini.inizializzaPersone();
		
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
		m_state = StatiGestoreCondominio.inserimentoTabellaMillesimaleProprieta;
	}
	
	public void importaCondominio(URL path) {
		if (m_state == StatiGestoreCondominio.gestoreCondomini) {
			m_driverFS.leggi(path, (BaseExecutor)this);
			m_state = StatiGestoreCondominio.attesaSelezioneFile;
		}
	}
	
	public void inserisciCondominio() {
		m_inserireNuovoCondominio = new InserireNuovoCondominio();
		m_condominio = new Condominio();
		m_dbCondomini.inserisciCondominio(m_condominio);
		m_state = StatiGestoreCondominio.inserimentoCondominio;
	}
	
	public void inserisciUnitaImmobiliare() {
		m_unitaImmobiliare = new UnitaImmobiliare();
		m_condominio.inserisciUnitaImmobiliare(m_unitaImmobiliare);
		m_inserireUnitaImmobiliare = new InserireUnitaImmobiliare();
		m_state = StatiGestoreCondominio.inserimentoUnitaImmobiliare;
	}
	
	public void operazioneAnnullata() {
		if ( m_state == StatiGestoreCondominio.inserimentoCondominio ) {
			m_dbCondomini.eliminaCondominio(m_condominio);
			m_state = StatiGestoreCondominio.gestoreCondomini;
		}
	}
	
	public void operazioneTerminata() {
		if ( m_state == StatiGestoreCondominio.condominioAperto )
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
			m_condominio.eliminaUnitaImmobiliare(m_unitaImmobiliare);
			return;
		}
		
		m_unitaImmobiliare.modificaDati(datiUnitaImmobliare);
		m_inserireUnitaImmobiliare.ammissibile(true);
		m_state = StatiGestoreCondominio.inserimentoProprieta;
		m_inserireUnitaImmobiliare.aggiornaPersone(TuttePersone.PERSONE);
	}
	
	/* TODO : Candidato alla rimozione
	 * 
	 * Non più presente nel design 3.2
	 * 
	 */
//	public void passaFile(File file)
//	{
//		switch (m_state) {
//			case attesaSelezioneFile :
//				if ( ! FormatoAmICo.fileInFormatoAmICo(file) ) {
//					m_amico.fallito();
//					return;
//				}
//				m_condominio = FormatoAmICo.daFileACondominio(file);
//				m_dbCondomini.inserisciCondominio(m_condominio);
//				m_amico.aggiornaCondomini(TuttiCondomini.CONDOMINI);
//				m_amico.fatto();
//				m_state = StatiGestoreCondominio.condominioAperto;
//				break;
//		}
//	}
	
	public void passaProprieta(Persone persone, Reali quoteProprieta) {
		/* TODO:
		 * Nella SM di GestoreCondominio appare quote.controlla(), ma QuoteProprietà 
		 * non contiene il metodo "controlla()"
		 */
	//	if ( ! quoteProprieta.controlla() ) {
			m_inserireUnitaImmobiliare.ammissibile(false);
			return;
		//}
		
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
				m_amico.aggiornaCondomini(m_dbCondomini.recuperaCondomini());
				m_gestoreCondominioAperto = new GestoreCondominioAperto(m_condominio);
				m_amico.fatto();
				m_state = StatiGestoreCondominio.condominioAperto;
				break;
			case attesaConferma :
				if ( !procedere ) {
					m_inserireUnitaImmobiliare.fallito();
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
		 * Rimane anche nel design 3.2, sempre inutilizzata
		 */
	}
	
	private boolean unitaImmobiliareGiaInserita(DatiUnitaImmobiliare datiUnitaImmobliare) {	 
		UnitaImmobiliari uImmobiliari = m_condominio.recuperaUnitaImmobiliari();
		UnitaImmobiliare newUnit = new UnitaImmobiliare();
		newUnit.modificaDati(datiUnitaImmobliare);
		
		return uImmobiliari.getImmobili().contains(newUnit);
	}
}
