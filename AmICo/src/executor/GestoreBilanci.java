package executor;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import boundary.AccedereBilanci;
import boundary.AccedereBilancioAperto;
import boundary.DriverFileSystem;
import datatype.DatiBilancio;
import datatype.DatiVoceBilancio;
import enumeration.StatiGestoreBilancio;
import enumeration.TipoBilancio;
import store.POJO.Bilancio;
import store.POJO.Condominio;
import store.POJO.VoceBilancio;

public class GestoreBilanci implements BaseExecutor {

	private Condominio condominio;
	private DatiBilancio datiBilancio;
	private Bilancio bilancio;
	private StatiGestoreBilancio state;
	private AccedereBilanci AB;
	private AccedereBilancioAperto ABA;
	private DriverFileSystem DFS;
	private VoceBilancio voceBilancio;

	public GestoreBilanci(Condominio condominio) {
		this.condominio = condominio;
		AB=new AccedereBilanci(this, condominio.recuperaBilanci());
		state=StatiGestoreBilancio.base;
	}
	public void inserisciBilancio(DatiBilancio datiBilancio) {
		if (datiBilancio.getTipo()==TipoBilancio.ordinario || (datiBilancio.getTipo()==TipoBilancio.straordinario && nomeUnico(datiBilancio))){
			this.datiBilancio=datiBilancio;
			AB.ammissibile(true);
			state=StatiGestoreBilancio.attesaConfermaInserimentoBilancio;
		}
		else {
			AB.ammissibile(false);
		}
	}
	
	public void apriBilancio(Bilancio bilancio) {
		this.bilancio=bilancio;
		state = StatiGestoreBilancio.bilancioAperto;
		ABA=new AccedereBilancioAperto(bilancio);
	}
	
	public void inserisciVoceBilancio(DatiVoceBilancio datiVoceBilancio) {
		
	}
	
	public void eliminaVoceBilancio(VoceBilancio voceBilancio) {
		if (voceGiaRegistrataInCassa(voceBilancio)){
			ABA.ammissibile(false);
		}
		else {
			ABA.ammissibile(true);
			state=StatiGestoreBilancio.attesaConfermaEliminazioneVoceDiBilancio;
		}
	}
	
	public void mettiInEsercizio(){
		bilancio.mettiInEsercizio();
		
	}
	
	public void terminaEsercizioBilancio() {
		if (terminabile()){
			ABA.ammissibile(true);
			state=StatiGestoreBilancio.attesaConfermaFineEsercizioSpeseNonPagate;
		}
		else {
			ABA.aggiornaSpeseDaPagare(CalcoliFinanziari.calcolaSpeseDaPagare(bilancio));
			state=StatiGestoreBilancio.attesaConfermaFineEsercizioOK;
			
		}
	}
	public void generaReport(TipoReportBilancio tipo, FormatoFile formato) {
		DFS.salva(Formattore.converti(preparaReportBilancio(), formato), Default, this	);
		state=StatiGestoreBilancio.creazioneReport;
	}
	
	public void chiudiBilancio(){
		
	}
	
	public void operazioneAnnullata() {
		// TODO Auto-generated method stub
		
	}

	public void operazioneTerminata() {
		switch (state) {
		case creazioneReport:
			ABA.fatto();
			state=StatiGestoreBilancio.bilancioAperto;
			break;

		case base:
			//GestoreCondominioAperto.
			break;
		}
		
	}

	public void procedi(boolean b) {
		switch (state) {
		case attesaConfermaInserimentoBilancio:
			if (b){
				condominio.inserisciBilancio(new Bilancio(datiBilancio));
				ABA=new AccedereBilancioAperto(bilancio);
				state=StatiGestoreBilancio.bilancioAperto;
			}
			else {}
			
			break;
			
		case attesaConfermaEliminazioneVoceDiBilancio:
			if (b)
				bilancio.eliminaVoceBilancio(voceBilancio);
			ABA.aggiornaVociBilancio(bilancio.recuperaVociBilancio());	
			state=StatiGestoreBilancio.bilancioAperto;
			
			break;
		case  attesaConfermaFineEsercizioSpeseNonPagate:
			if(b) bilancio.terminaEsercizio();
			break;
			
		case attesaConfermaFineEsercizioOK:
			if (b){
				preventivaSpeseNonPagate(conti);
				bilancio.terminaEsercizio();
			}
			break;
		}
		
		
	}

	
	private boolean nomeUnico(DatiBilancio datiBilancio){
		return false;
	}
	
	private boolean nomeUnico(DatiVoceBilancio datiVoceBilancio){
		return false;
	}
	
	private boolean voceGiaRegistrataInCassa(VoceBilancio voceBilancio){
		return false;
	}
	
	private boolean terminabile() {
		return false;
	}

	private void preventivaSpeseNonPagate(RapportoPagamenti rapportoPagamenti) {
		
	}
	private Report preparaReportBilancio(){
		return null;
	}

}
