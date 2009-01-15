package executor;

import datatype.DatiTabellaMillesimale;
import datatype.Preferenze;
import datatype.list.Persone;
import datatype.list.QuoteProprietà;
import enumeration.FormatoFile;
import store.POJO.Condominio;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;

public class GestoreCondominioAperto extends BaseExecutor {

	private Condominio m_condominio;
/*	private Reali m_nuoveQuote; // TODO: Reali? */
	private Persone m_nuoviProprietari;
	private DatiTabellaMillesimale m_datiTabellaMillesimale;
	private TabellaMillesimale m_tabellaMillesimale;
	
	public GestoreCondominioAperto(Condominio condominio) {
		m_condominio = condominio;
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
		
	}
	
	public void passaABilanci() {
		
	}
	
	public void passaACassa() {
		
	}
	
	public void passaAPreferenze() {
		
	}
	
	public void modificaPreferenze(Preferenze preferenze) {
		
	}
	
	public void passaAPagamenti() {
		
	}
	/* TODO: TipoReportCondominio ? 
	public void generaReport(TipoReportCondominio tipoReportCondominio, FormatoFile formatoFile) {
		
	}
	*/
	
	public void chiudiCondominio() {
		
	}
	
	public void eliminaCondominio() {
		
	}
	
	
	/* TODO:
	 * Path potrebbe essere cambiato in java.net.URL
	
	public void esportaCondominio(Path path) {
		
	}
	
	*/
	
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
	
	public EsitoEliminabile eliminabile() {
		return true;
	}
	
	*/
	
}
