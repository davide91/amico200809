package executor;

import store.POJO.Bilancio;
import store.POJO.Condominio;
import store.POJO.VoceBilancio;
import boundary.AccedereBilanci;
import boundary.AccedereBilancioAperto;
import boundary.AccedereCondominioAperto;
import boundary.DriverFileSystem;
import calculator.CalcoliFinanziari;
import calculator.Formattatore;
import datatype.DatiBilancio;
import datatype.DatiVoceBilancio;
import datatype.RapportoPagamenti;
import datatype.Report;
import enumeration.FormatoFile;
import enumeration.StatiGestoreBilancio;
import enumeration.TipoBilancio;
import enumeration.TipoReportBilancio;

public class GestoreBilanci implements BaseExecutor {

	private Condominio condominio;
	private DatiBilancio datiBilancio;
	private Bilancio bilancio;
	private StatiGestoreBilancio state;
	private AccedereBilanci AB;
	private AccedereBilancioAperto ABA;
	private AccedereCondominioAperto ACA;
	private DriverFileSystem DFS;
	private VoceBilancio voceBilancio;

	public GestoreBilanci(Condominio condominio, AccedereCondominioAperto ACA) {
		state=StatiGestoreBilancio.base;
		this.ACA=ACA;
		this.condominio = condominio;
		AB=new AccedereBilanci(this, condominio.recuperaBilanci(),ACA);
		ACA.getPannello().add(AB);


		
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
		ABA=new AccedereBilancioAperto(this,bilancio,ACA);
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
		ACA.getPannello().removeAll();
		ACA.getPannello().add(AB);
		ACA.getPannello().revalidate();
		ACA.getPannello().repaint();
		
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
		switch (state) 
		{
			case attesaConfermaInserimentoBilancio:
			if (b){
				
				state=StatiGestoreBilancio.bilancioAperto;
				condominio.inserisciBilancio(new Bilancio(datiBilancio));
				AB.aggiornaBilanci(condominio.recuperaBilanci());
				ABA=new AccedereBilancioAperto(this,bilancio,ACA);
				
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
				//commentato perchè dava errore e non potevo inserire un bilancio
				//preventivaSpeseNonPagate(CalcoliFinanziari.calcolaSpeseDaPagare(bilancio));
				bilancio.terminaEsercizio();
			}
			else {
			
			}
				
			break;
			
			case attesaConfermaFineEsercizioOK:
			state=StatiGestoreBilancio.bilancioAperto;
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
		
		for (VoceBilancio vb : bilancio.getVoci()) {
			if(vb.getDati().equals(datiVoceBilancio))
				return false;
		}
		return true;
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
