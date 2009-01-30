package calculator;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import datatype.Avviso;
import datatype.CassaSottoSogliaMinima;
import datatype.CondominiMorosi;
import datatype.Data;
import datatype.DatiPagamento;
import datatype.Euro;
import datatype.PagamentoInScadenza;
import datatype.PagamentoScaduto;
import datatype.Preferenze;
import datatype.SpeseDaPagare;
import datatype.list.Avvisi;
import datatype.list.Pagamenti;
import datatype.list.VociBilancio;
import store.POJO.Bilancio;
import store.POJO.Cassa;
import store.POJO.Condominio;
import store.POJO.MovimentoCassa;
import store.POJO.Pagamento;
import store.POJO.VoceBilancio;

public class CalcolaAvvisi {

	private static Condominio m_condominio;
	private static Avvisi m_avvisi;
	private static int preavviso = 7; // Giorni di preavviso
	
	public static Avvisi calcolaAvvisi(Condominio condominio) {
		
		m_condominio = condominio;
	
		
		m_avvisi = new Avvisi();

		/*
		 * PagamentiScaduti
		 * PagamentiInScadenza
		 * CondominiMorosi
		 * 
		 */
		
		Pagamenti pagamenti = m_condominio.recuperaPagamenti();
		Iterator<Pagamento> pagamentiIter = pagamenti.getPagamenti().iterator();
		Pagamenti scaduti = new Pagamenti();
		Pagamenti inScadenza = new Pagamenti();
		CondominiMorosi morosi = new CondominiMorosi();
		
		Data currData = 
			new Data(
					new Date(
							GregorianCalendar.getInstance().getTime().getTime()));
		int ritardo;
		try {
		 ritardo= m_condominio.getPreferenze().getRitardoAmmesso();
		}
		catch (NullPointerException npe){
			ritardo=0;
		}
		while(pagamentiIter.hasNext())
		{
			Data dataScadenza = new Data(
					pagamentiIter.next().getDatiPagamento().getScadenza());
						
			dataScadenza.add(Calendar.DAY_OF_MONTH, ritardo);
			
			if ( currData.maggioreUguale(dataScadenza) )  
			{
				morosi.inserisciPagamento(pagamentiIter.next().getEseguito_da(), pagamentiIter.next());
				
				scaduti.inserisciPagamento(pagamentiIter.next());
			}
			else 
			{
				dataScadenza.add(Calendar.DAY_OF_MONTH, -(preavviso+ritardo));
				
				if ( currData.maggioreUguale(dataScadenza) )
					inScadenza.inserisciPagamento(pagamentiIter.next());
			}	
		}
		
		PagamentoScaduto avvisoScaduti = new PagamentoScaduto(scaduti);
		PagamentoInScadenza avvisoInScadenza = new PagamentoInScadenza(inScadenza);		
		
		if (!scaduti.getPagamenti().isEmpty())
			m_avvisi.add(avvisoScaduti);
		
		if (!inScadenza.getPagamenti().isEmpty())
			m_avvisi.add(avvisoInScadenza);
		
		if (!morosi.isEmpty())
			m_avvisi.add(morosi);

		/* 
		 * 
		 * Cassa sotto soglia minima
		 * 
		 */
		
		Euro sogliaMin; 
		try{
		sogliaMin= m_condominio.getPreferenze().getSogliaMinimaCassa();
		}
		catch (NullPointerException npe) {
			sogliaMin=new Euro((float)0.0);
		}
		Iterator<Cassa> cassaIter = m_condominio.getCassa().iterator();
		while(cassaIter.hasNext())
		{
			if( cassaIter.next().getSaldo().getEuro() < sogliaMin.getEuro() )
			{
				/* FIXME : Cassa.toString() per identificare una cassa dall'altra */
				CassaSottoSogliaMinima avviso = 
					new CassaSottoSogliaMinima(cassaIter.next().toString(),
							cassaIter.next().getSaldo(), sogliaMin);
				m_avvisi.add((Avviso)avviso);
			}
		}

		/*
		 * Spese da pagare
		 * 
		 */
		
		SpeseDaPagare daPagare = new SpeseDaPagare();
		Iterator<Bilancio> bilancioIter = m_condominio.getBilanci().iterator();
		
		while(bilancioIter.hasNext())
		{
			Iterator<VoceBilancio> vociIter = bilancioIter.next().recuperaVociBilancio().getVoci().iterator();
			
			while (vociIter.hasNext())
			{
				if ( vociIter.next().getDataContabilitazione() == null )
					daPagare.inserisciSpesa(vociIter.next().getDati());
			}
		}

		if (!daPagare.isEmpty())
			m_avvisi.add(daPagare);
		
		/*
		 * TODO :
		 * 
		 * BilancioStatoAllerta
		 * 
		 * Possibile significato : 
		 * la somma degli importi non contabilizzati da pagare è superiore alla somma delle casse
		 * 
		 */

		return m_avvisi;
	}
	
}
