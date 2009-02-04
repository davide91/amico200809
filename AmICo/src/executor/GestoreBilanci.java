package executor;

import java.net.URL;

import calculator.CalcoliFinanziari;
import calculator.Formattatore;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.bcel.internal.generic.SALOAD;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import boundary.AccedereBilanci;
import boundary.AccedereBilancioAperto;
import boundary.DriverFileSystem;
import datatype.DatiBilancio;
import datatype.DatiVoceBilancio;
import datatype.RapportoPagamenti;
import datatype.Report;
import enumeration.FormatoFile;
import enumeration.StatiGestoreBilancio;
import enumeration.TipoBilancio;
import enumeration.TipoReportBilancio;
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
		state=StatiGestoreBilancio.base;
		this.condominio = condominio;
		AB=new AccedereBilanci(this, condominio.recuperaBilanci());
		
	}
	public void inserisciBilancio(DatiBilancio datiBilancio) {
		if (datiBilancio.getTipo()==TipoBilancio.ordinario || (datiBilancio.getTipo()==TipoBilancio.straordinario && nomeUnico(datiBilancio))){
			state=StatiGestoreBilancio.attesaConfermaInserimentoBilancio;
			this.datiBilancio=datiBilancio;
			AB.ammissibile(true);
		
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
		if (nomeUnico(datiVoceBilancio)){
			state=StatiGestoreBilancio.attesaConfermaVoceDiBilancio;
			voceBilancio= new VoceBilancio(datiVoceBilancio);
			ABA.ammissibile(true);
		}
		else 
		{
			ABA.ammissibile(false);
		}
	}
	
	public void eliminaVoceBilancio(VoceBilancio voceBilancio) {
		if (voceGiaRegistrataInCassa(voceBilancio)){
			ABA.ammissibile(false);
		}
		else {
			state=StatiGestoreBilancio.attesaConfermaEliminazioneVoceDiBilancio;
			this.voceBilancio=voceBilancio;
			ABA.ammissibile(true);
	
		}
	}
	
	public void mettiInEsercizio(){
		bilancio.mettiInEsercizio();
		
	}
	
	public void terminaEsercizioBilancio() {
		if (terminabile()){
			state=StatiGestoreBilancio.attesaConfermaFineEsercizioOK;
			ABA.ammissibile(true);
		
		}
		else {
			state=StatiGestoreBilancio.attesaConfermaFineEsercizioSpeseNonPagate;
			ABA.aggiornaSpeseDaPagare(CalcoliFinanziari.calcolaSpeseDaPagare(bilancio));
		}
	}
	public void generaReport(TipoReportBilancio tipo, FormatoFile formato) {
		DFS.salva(Formattatore.converti(preparaReportBilancio(), formato),"ReportBilancio-"+tipo, this); //DefaultPath = ReportBilancio-"TipoReportBilancio"
		state=StatiGestoreBilancio.creazioneReport;
	}
	
	public void chiudiBilancio(){
		state=StatiGestoreBilancio.base;
		AB.fatto();
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
				
				state=StatiGestoreBilancio.bilancioAperto;
				condominio.inserisciBilancio(new Bilancio(datiBilancio));
				ABA=new AccedereBilancioAperto(bilancio);
				
			}
			else {
				state=StatiGestoreBilancio.base;
			}
			
			break;
	
		case attesaConfermaVoceDiBilancio:
			state=StatiGestoreBilancio.bilancioAperto;
			if (b){
				bilancio.inserisciVoceBilancio(voceBilancio);
				ABA.fatto();
				ABA.aggiornaVociBilancio(bilancio.recuperaVociBilancio());
			}
			break;
		case attesaConfermaEliminazioneVoceDiBilancio:
			if (b)
			{
				bilancio.eliminaVoceBilancio(voceBilancio);
				ABA.aggiornaVociBilancio(bilancio.recuperaVociBilancio());	
			}
			state=StatiGestoreBilancio.bilancioAperto;
			
			
			break;
		case  attesaConfermaFineEsercizioSpeseNonPagate:
			state=StatiGestoreBilancio.bilancioAperto;
			if(b) {
				preventivaSpeseNonPagate(conti);
				bilancio.terminaEsercizio();
			}
			else {
			
			}
				
			break;
			
		case attesaConfermaFineEsercizioOK:
			state=state.bilancioAperto;
			if (b){
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
		if( bilancio.getDati().getTipo()==TipoBilancio.straordinario)
			return bilancio.saldo()>=0;
			else {
				//in Euri aggiunta operazione tuttiZeri boolean che controlle che tutti gli euro siano a 0
				return bilancio.saldo()==0 && bilancio.getVoci().isEmpty() && bilancio.saldoUnita().tuttiZeri(); //"TuttiZeri";
			}
	}

	private void preventivaSpeseNonPagate(RapportoPagamenti rapportoPagamenti) {
		Bilancio preventivo = new Bilancio();
	//	preventivo
	}
	
	private Report preparaReportBilancio(){
		return null;
	}

}
