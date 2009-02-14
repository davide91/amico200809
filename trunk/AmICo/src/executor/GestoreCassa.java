package executor;

import java.util.Iterator;

import calculator.Formattatore;

import boundary.AccedereCassa;
import boundary.AccedereCondominioAperto;
import boundary.DriverFileSystem;
import boundary.RegistraMovimento;
import datatype.DatiMovimentoCassa;
import datatype.DatiPianoPagamenti;
import datatype.Euro;
import datatype.Report;
import datatype.list.VociBilancio;
import enumeration.FormatoFile;
import enumeration.StatiGestoreCassa;
import enumeration.StatoBilancio;
import enumeration.TipoReportCassa;
import store.POJO.Bilancio;
import store.POJO.Cassa;
import store.POJO.MovimentoCassa;
import store.POJO.PianoPagamenti;
import store.POJO.VoceBilancio;

public class GestoreCassa implements BaseExecutor {

	private Cassa m_cassa;
	private DatiMovimentoCassa m_datiMovimento;
	private AccedereCassa m_accedereCassa;
	private StatiGestoreCassa m_state;
	private VoceBilancio voceBilancio;
	
	public GestoreCassa(Cassa cassa,AccedereCondominioAperto ACA) {
		m_cassa = cassa;
		m_accedereCassa = new AccedereCassa(this, cassa);
		ACA.getPannello().add(m_accedereCassa);
		m_state = StatiGestoreCassa.gestoreCassa;	
	}
	
	public void registraMovimentoCassa(DatiMovimentoCassa datiMovimento)
	{
		m_datiMovimento = datiMovimento;
	
		Iterator<Bilancio> bilancioIter = m_cassa.getCondominio().getBilanci().iterator();
		VociBilancio voci = new VociBilancio();
		
		while(bilancioIter.hasNext())
		{
			if (bilancioIter.next().getDati().getStato() != StatoBilancio.inEsercizio)
				continue;
			
			Iterator<VoceBilancio> voceIter = bilancioIter.next().getVoci().iterator();
			
			while (voceIter.hasNext())
			{
				voci.inserisciVoceBilancio(voceIter.next());
			}
				
		}
		
		m_accedereCassa.aggiornaVociBilancio(voci);
		m_state = StatiGestoreCassa.attesaCausale;
	}
	
	public void passaVoceBilancio(VoceBilancio voceBilancio, DatiMovimentoCassa dati)
	{
		this.voceBilancio = voceBilancio;
		m_datiMovimento = dati; // riga aggiunta da federico
	//	m_datiMovimento = preparaProspetto(voceBilancio);
		m_state = StatiGestoreCassa.attesaConferma;
		m_accedereCassa.aggiornaProspetto(m_datiMovimento);
	}
	
	public void generaReport(TipoReportCassa tipo, FormatoFile formato)
	{
		DriverFileSystem.getInstance().salva(
				Formattatore.converti(preparaReportCassa(tipo), formato),
				DriverFileSystem.getInstance().getDefaultSavePath(),
				this);
		m_state = StatiGestoreCassa.creazioneReport;
	}
	
	private Report preparaReportCassa(TipoReportCassa tipoReport)
	{
		// TODO 
		return new Report();
	}

	private DatiMovimentoCassa preparaProspetto(PianoPagamenti piano, int numRata)
	{
		/* FIXME : Da ricontrollare
		 * context preparaProspetto(pianoPagamento: PianoPagamento, numRata: intero) : DatiMovimentoCassa
		 * let p = pianoPagamento.dati in 
		 *   return create(DatiMovimentoCassa(p.importo * p.percentuali.at(numRata)))
		 */
		
		DatiPianoPagamenti datiPiano = piano.getDati();
		DatiMovimentoCassa datiMovimento = new DatiMovimentoCassa();
		Euro importo = new Euro(datiPiano.getImporto().getEuro() * datiPiano.getPercentuali() * (float)numRata);
		datiMovimento.setImportoMovimento(importo);
		
		return datiMovimento;
	}
	
	private DatiMovimentoCassa preparaProspetto(VoceBilancio voce)
	{
		/* FIXME : Da ricontrollare
		 * context preparaProspetto(voce: VoceBilancio) : DatiMovimentoCassa
		 *   return creaDatiMovimentoCassa(voce.importo)
		 */
		
		DatiMovimentoCassa datiMovimento = new DatiMovimentoCassa();
		datiMovimento.setImportoMovimento(voce.getDati().getImporto());
		return datiMovimento;
	}
	
	public void operazioneAnnullata() {
		// Inutilizzata
	}

	public void operazioneTerminata() {
		
		/* FIXME :
		 * Richiesto un sigleton per GCA (GCA NON HA SINGLETON!)
		 *
		 * GestoreCondominioAperto.getInstance().operazioneTerminata();
		 * 
		 */
		
	}

	public void procedi(boolean procedere) {
		switch ( m_state )
		{
		case attesaConferma :
				if ( !procedere ) 
				{
					m_state = StatiGestoreCassa.gestoreCassa;
					break;
				}
				MovimentoCassa m = new MovimentoCassa(m_datiMovimento);
				m.setRelativoAVoce(voceBilancio);
				m_cassa.registraMovimentoCassa(m);
				voceBilancio.contabilizzaMovimento(m);
				m_accedereCassa.aggiornaCassa(m_cassa);
				m_state = StatiGestoreCassa.gestoreCassa;
				break;
		}
	}
	
	//fatto da federico
	public void registraMovimentoCassa()
	{		
		Iterator<Bilancio> bilancioIter = m_cassa.getCondominio().getBilanci().iterator();
		VociBilancio voci = new VociBilancio();
		VoceBilancio v;
		Iterator<VoceBilancio> voceIter; 
		while(bilancioIter.hasNext())
		{
		//	if (bilancioIter.next().getDati().getStato() != StatoBilancio.inEsercizio)
		//		continue;
			
			voceIter = bilancioIter.next().getVoci().iterator();

			
			while (voceIter.hasNext())
			{
				v=voceIter.next();
				if(v.getContabilizzata()==null)
					voci.inserisciVoceBilancio(v);
			}
		}

		m_accedereCassa.aggiornaVociBilancio(voci);
		m_state = StatiGestoreCassa.attesaCausale;
	}

}
