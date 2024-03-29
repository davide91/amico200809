package executor;

import java.io.File;
import java.util.Iterator;

import calculator.Formattatore;
import datatype.DatiPianoPagamenti;
import datatype.Documento;
import datatype.Report;
import enumeration.FormatoFile;
import enumeration.StatiGestorePagamenti;
import enumeration.TipoReportPagamenti;
import boundary.AccederePagamenti;
import boundary.DriverFileSystem;
import store.POJO.Bilancio;
import store.POJO.Condominio;
import store.POJO.Pagamento;
import store.POJO.Persona;
import store.POJO.PianoPagamentiOrdinario;
import store.POJO.PianoPagamentiStraordinario;
import store.POJO.TabellaMillesimale;

public class GestorePagamenti implements BaseExecutor {

	private AccederePagamenti m_accederePagamenti;
	private Bilancio m_bilancio;
	private Condominio m_condominio;
	
	private DatiPianoPagamenti m_datiPP;
	private String m_defaultSavePath = "";
	private Documento m_documento;
	
	private FormatoFile m_formato;
	private TabellaMillesimale m_ripartizione;
	private StatiGestorePagamenti m_state;
	private TipoReportPagamenti m_tipoReport;
	
	public GestorePagamenti(Condominio condominio) {
		m_condominio = condominio;	
		m_accederePagamenti = new AccederePagamenti(this);
		m_state = StatiGestorePagamenti.gestorePagamenti;
	}
	
	public void generaPianoPagamentiOrdinario(Bilancio bilancio, DatiPianoPagamenti dati)
	{
		m_bilancio = bilancio;
		m_datiPP = dati;
	
		Iterator<TabellaMillesimale> tabIter = m_condominio.recuperaTabelleMillesimali().getTabelle().iterator();
		
		while (tabIter.hasNext())
		{
			if (tabIter.next().getDati().getNome() == "Proprietà")
			{
				m_ripartizione = tabIter.next();
				break;
			}
		}
		m_accederePagamenti.ammissibile(true);
	}
	
	public void generaPianoPagamentiStraordinario(Bilancio bilancio, DatiPianoPagamenti dati, TabellaMillesimale tabella)
	{
		m_bilancio = bilancio;
		m_datiPP = dati;
		m_ripartizione = tabella;
		m_state = StatiGestorePagamenti.attesaConfermaPianoPagamentiStraordinari;
		m_accederePagamenti.ammissibile(true);
	}
	
	
	public void generaReport(TipoReportPagamenti tipo, FormatoFile formato)
	{
		File reportFile = Formattatore.converti(preparaReportPagamenti(m_tipoReport), formato);
		DriverFileSystem.getInstance().salva(reportFile, m_defaultSavePath, this);
	}
	
	public void generaRichiestaPagamento(Persona persona, Pagamento pagamento, FormatoFile formato)
	{
		@SuppressWarnings("unused")
		Documento doc = preparaRichiestaPagamento(persona,pagamento);
		m_formato = formato;
		
	}
	
	public void generaSollecito(Persona persona, Pagamento pagamento, FormatoFile formato)
	{
		m_documento = preparaSollecito(persona, pagamento, formato);
		m_accederePagamenti.aggiornaDocumento(m_documento);
		m_state = StatiGestorePagamenti.attesaConfermaDocumentoSollecito;
	}
	
	public void inserisciFraseAdHoc(String frase)
	{
		if ( m_documento == null ) return;
		
		m_documento.inserisciFrase(frase);
		m_accederePagamenti.aggiornaDocumento(m_documento);
	}

	public void operazioneAnnullata() {
		
	}
	
	public void operazioneTerminata() {
		if ( m_state == StatiGestorePagamenti.gestorePagamenti )
		{
			return;
		}
		
		m_state = StatiGestorePagamenti.gestorePagamenti;
		m_accederePagamenti.fatto();
	}
	
	private Report preparaReportPagamenti(TipoReportPagamenti tipo)
	{
		// TODO
		return new Report();
	}
	
	private Documento preparaRichiestaPagamento(Persona persona, Pagamento pagamento) 
	{
		// TODO
		return new Documento();
	}

	private Documento preparaSollecito(Persona persona, Pagamento pagamento, FormatoFile formato) 
	{
		// TODO
		return new Documento();
	}

	public void procedi(boolean procedere) {
		
		if (!procedere) {
			m_state = StatiGestorePagamenti.gestorePagamenti;
			return;
		}
		
		File docFile;
		
		switch (m_state)
		{
		case attesaConfermaPianoPagamentiStraordinari :
			PianoPagamentiStraordinario pianoPStraordinario = 
				new PianoPagamentiStraordinario();
			pianoPStraordinario.creaPianoPagamenti(m_datiPP, m_ripartizione);
			m_bilancio.inserisciPianoPagamenti(pianoPStraordinario);
			break;
		case attesaConfermaDocumentoSollecito :
			docFile = Formattatore.converti(m_documento, m_formato);
			DriverFileSystem.getInstance().salva(docFile, m_defaultSavePath, this);
			m_state = StatiGestorePagamenti.creazioneDocumentoSollecito;
			break;
		case attesaConfermaPianoPagamentiPareggio :
			PianoPagamentiOrdinario piano = new PianoPagamentiOrdinario();
			piano.creaPianoPagamenti(m_datiPP);
			m_bilancio.inserisciPianoPagamenti(piano);
			break;
		case attesaConfermaDocumentoRichiestaPagamento :
			docFile = Formattatore.converti(m_documento, m_formato);
			DriverFileSystem.getInstance().salva(docFile, m_defaultSavePath, this);
			m_state = StatiGestorePagamenti.creazioneDocumentoRichiestaPagamento;
			break;
		}
	}
}