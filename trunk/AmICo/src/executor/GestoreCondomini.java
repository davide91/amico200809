package executor;

import java.io.File;
import java.net.URL;
import java.util.Set;

import store.TuttePersone;
import store.TuttiCondomini;
import store.POJO.Condominio;
import store.POJO.Persona;
import store.POJO.Proprieta;
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
import datatype.list.Condomini;
import datatype.list.Millesimi;
import datatype.list.Percentuali;
import datatype.list.Persone;
import datatype.list.TabelleMillesimali;
import datatype.list.UnitaImmobiliari;
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
	private TuttiCondomini m_dbCondomini;
	private TuttePersone m_dbPersone;
	private DriverFileSystem m_driverFS;
	private GestoreCondominioAperto m_gestoreCondominioAperto;
	private InserireNuovoCondominio m_inserireNuovoCondominio;
	private InserisciTabellaMillesimaleProprieta m_inserisciTabelleMillesimali;
	private StatiGestoreCondominio m_state;
	private ConfermaUnitaImmobiliari m_confermaUnitaImmobiliari;

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
		//siccome l'ID condominio e' univoco anche sul DB risulat che il condominio e' gia'� inserito anche se ha solo l'id uguale
		for ( Condominio condominio : m_dbCondomini.recuperaCondomini().getCondomini() )
			if (condominio.recuperaDatiCondominio().equals(datiCondominio) || condominio.recuperaDatiCondominio().getId().equals(datiCondominio.getId()))
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
	
	public void importaCondominio(String path) {
		m_driverFS.leggi(path, (BaseExecutor)this);
		m_state = StatiGestoreCondominio.attesaSelezioneFile;
	}
	
	public void inserisciCondominio() {
		m_inserireNuovoCondominio = new InserireNuovoCondominio();	
		m_state = StatiGestoreCondominio.inserimentoCondominio;
	}
	
	public void inserisciUnitaImmobiliare() {
		m_state = StatiGestoreCondominio.inserimentoUnitaImmobiliare;
		m_unitaImmobiliare = new UnitaImmobiliare();
		m_condominio.inserisciUnitaImmobiliare(m_unitaImmobiliare);
		
	//	m_inserireUnitaImmobiliare = new InserireUnitaImmobiliare(m_dbPersone.recuperaPersone());
	}
	
	public void operazioneAnnullata() {
	/*	switch (m_state) {
		case inserimentoCondominio :
		{
	*/		m_state = StatiGestoreCondominio.gestoreCondomini;
	
			if (m_condominio != null) TuttiCondomini.getInstance().eliminaCondominio(m_condominio);
			m_amico.aggiornaCondomini(TuttiCondomini.getInstance().recuperaCondomini());
	/*	}
			break;
		
		default: m_state= StatiGestoreCondominio.inserimentoCondominio;
			for (UnitaImmobiliare unita : m_condominio.recuperaUnitaImmobiliari().getImmobili()) {
			m_condominio.eliminaUnitaImmobiliare(unita);
		}
			
			break;
		}
		*/	
	}
	
	public void operazioneTerminata() {
		switch (m_state) {
		case condominioAperto :
			m_state = StatiGestoreCondominio.gestoreCondomini;
			m_condominio = null;
			break;
		case inserimentoProprieta:
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
	// non c'e' nel design serve per eliminare unita' imoobiliare in creazione
	public void eliminaUnitaImmobiliare(String dati)
	{
		for (UnitaImmobiliare ui : m_condominio.getUnitaImmobiliari())
		{
			if( ui.getDatiUnitaImmobiliare().getId().equals(dati) )
				m_condominio.eliminaUnitaImmobiliare(ui);
		}
		m_confermaUnitaImmobiliari.aggiornaUnitaImmobiliari( m_condominio.recuperaUnitaImmobiliari() );
		
	}
	
	public void passaDatiUnitaImmobliare(DatiUnitaImmobiliare datiUnitaImmobliare) {
		if ( unitaImmobiliareGiaInserita(datiUnitaImmobliare) ) {
			m_state=StatiGestoreCondominio.inserimentoUnitaImmobiliari;
			m_confermaUnitaImmobiliari.ammissibile(false);
			m_condominio.eliminaUnitaImmobiliare(m_unitaImmobiliare);
			return;
		}

		m_state = StatiGestoreCondominio.inserimentoProprieta;
		m_unitaImmobiliare.modificaDati(datiUnitaImmobliare);
		m_confermaUnitaImmobiliari.ammissibile(true);
		
		/* Non presente in 3.5.4 */
		m_confermaUnitaImmobiliari.aggiornaUnitaImmobiliari( m_condominio.recuperaUnitaImmobiliari() );
	}
		
	public void passaProprieta(Persone persone, Percentuali quoteProprieta) {
	//	m_state = StatiGestoreCondominio.attesaConferma;
		
		for (Persona  p : persone.getPersone()) {
			m_condominio.inserisciPersona(p);
		}
		
		m_unitaImmobiliare.modificaProprieta(persone, quoteProprieta);
		
	//	Proprieta proprieta = new Proprieta();
		
		/*  PERCHÈ, LO FA GIÀ LA MODIFICAPROPRIETÀ	
		for (int i=0; i < persone.getPersone().size(); i++){
			proprieta.setProprietario(persone.getPersone().get(i));
			proprieta.setUnitaImmobiliare(m_unitaImmobiliare);
			proprieta.setQuota(quoteProprieta.getListaQuote().get(i));
			persone.getPersone().get(i).setProprieta(proprieta);
			m_condominio.inserisciPersona(persone.getPersone().get(i));
			
			
		}
		*/
	}
	
	public void passaTabellaMillesimaleProprieta(DatiTabellaMillesimale datiTabellaMillesimale, Millesimi millesimi) 
	{
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

				/* Riga non presente nel design 3.5.4*/
				m_inserireNuovoCondominio.fatto();
				m_confermaUnitaImmobiliari = new ConfermaUnitaImmobiliari(m_inserireNuovoCondominio, TuttePersone.getInstance().recuperaPersone());
				
				break;
			case attesaConfermaTabellaMillesimale :
				if ( !procedere ) {
					m_state = StatiGestoreCondominio.gestoreCondomini;
					m_dbCondomini.eliminaCondominio(m_condominio);
					break;
				}
				m_condominio.inserisciTabellaMillesimale(m_tabellaMillesimaleProprieta);
				m_amico.aggiornaCondomini(m_dbCondomini.recuperaCondomini());
				m_amico.fatto();
				m_gestoreCondominioAperto = new GestoreCondominioAperto(m_condominio);
				m_state = StatiGestoreCondominio.condominioAperto;
				break;
			case attesaConferma :
				if ( !procedere )	
					m_condominio.eliminaUnitaImmobiliare(m_unitaImmobiliare);
				else {
					m_confermaUnitaImmobiliari.fatto();
					m_confermaUnitaImmobiliari.aggiornaUnitaImmobiliari(m_condominio.recuperaUnitaImmobiliari());
				}
				m_state=StatiGestoreCondominio.inserimentoUnitaImmobiliari;
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
		//siccome l'id è univoco sul db consiedero l'unità già inserita anche se ha solo l'id uguale
		for ( UnitaImmobiliare unit : m_condominio.recuperaUnitaImmobiliari().getImmobili())
			if ( unit.getDatiUnitaImmobiliare().equals(datiUnitaImmobliare) || unit.getDatiUnitaImmobiliare().getId().equals(datiUnitaImmobliare.getId()))
				return true;
		return false;
	}


}
