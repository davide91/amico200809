package executor;

import datatype.DatiCondominio;
import datatype.DatiUnitaImmobiliare;
import datatype.list.Condomini;
import datatype.list.Persone;
import datatype.list.QuoteProprietà;
import store.POJO.Condominio;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;

public class GestoreCondominio extends BaseExecutor {

	private Condominio m_condominio;
	private UnitaImmobiliare m_unitaImmobiliare;
	private TabellaMillesimale m_tabellaMillesimaleProprieta;
	
	public static GestoreCondominio m_gestoreCondominio;
	
	public static GestoreCondominio getInstance()
	{
		if ( m_gestoreCondominio == null ) 
			m_gestoreCondominio = new GestoreCondominio();
		return m_gestoreCondominio;
	}
	
	private GestoreCondominio()
	{
	}
	
	public void inserisciCondominio() {
		
	}
	
	public void passaDatiCondominio(DatiCondominio datiCondominio) {
		
	}
	
	private boolean condominioGiaInserito(DatiCondominio datiCondominio) {
		return true;
	}
	
	public void inserisciUnitaImmobliare() {
		
	}
	
	public void passaDatiUnitaImmobliare(DatiUnitaImmobiliare datiUnitaImmobliare) {
		
	}
	
	private boolean unitaImmobiliareGiaInserita(DatiUnitaImmobiliare datiUnitaImmobliare) {	
		return true;
	}
	
	public void passaProprieta(Persone persone, QuoteProprietà quoteProprieta) {
		
	}
	
	/*
	 * TODO: Reali ?
	 * 
	public void passaTabellaMillesimaleProprieta(Reali) {
	
	}
	
	*/
	
	public void apriCondominio(Condominio condominio) {
	
	}
	
	/* TODO:
	 * Path potrebbe essere cambiato in java.net.URL
	
	public void importaCondominio(Path path) {
	
	}
	*/
	
	/* TODO:
	 * File?
	public void operazioneTerminata(File) {
	}
	*/
	
	public void esciDaAmico() {
	}
	
	public void settaCondomini(Condomini condomini) {
		
	}
	
	
	
	
	
}
