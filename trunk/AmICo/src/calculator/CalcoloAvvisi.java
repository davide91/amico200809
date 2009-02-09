package calculator;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;
import datatype.Avviso;
import datatype.BilancioStatoAllerta;
import datatype.CassaSottoSogliaMinima;
import datatype.CondominiMorosi;
import datatype.Data;
import datatype.DatiVoceBilancio;
import datatype.Euro;
import datatype.PagamentoInScadenza;
import datatype.PagamentoScaduto;
import datatype.SpeseDaPagare;
import datatype.list.Avvisi;
import datatype.list.Pagamenti;
import store.POJO.Bilancio;
import store.POJO.Cassa;
import store.POJO.Condominio;
import store.POJO.Pagamento;
import store.POJO.VoceBilancio;

public class CalcoloAvvisi {

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
							Calendar.getInstance().getTime().getTime()));
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
			sogliaMin=new Euro(5000,0);
		}
		
		Euro totaleCasse = new Euro(0,0);
		Iterator<Cassa> cassaIter = m_condominio.getCassa().iterator();
		while(cassaIter.hasNext())
		{
			Cassa c = cassaIter.next();
			totaleCasse.somma(c.getSaldo());
			if( c.getSaldo().minoreDi(sogliaMin) )
			{
				/* FIXME : Cassa.toString() per identificare una cassa dall'altra */
				try{
					CassaSottoSogliaMinima avviso = new CassaSottoSogliaMinima(c.toString(),	c.getSaldo(), sogliaMin);
					m_avvisi.add(avviso);
				}
				catch(NoSuchElementException nsee)
				{
					CassaSottoSogliaMinima avviso = new CassaSottoSogliaMinima("Cassa creata da eccezione",	new Euro((float)500.0),new Euro((float)1500.0) );
				}
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
		
		bilancioIter = m_condominio.getBilanci().iterator();
		
		Euro totaleImporti = new Euro();
		
		while(bilancioIter.hasNext())
		{
			Iterator<VoceBilancio> vociIter = 
				bilancioIter.next().vociNonContabilizzate().getVoci().iterator();
			
			while(vociIter.hasNext())
			{
				DatiVoceBilancio dati = vociIter.next().getDati();
				totaleImporti.somma(dati.getImporto());
			}
		}
		
		if (totaleImporti.getEuro() > totaleCasse.getEuro())
		{
			Euro diff = new Euro(totaleImporti.getEuro()-totaleCasse.getEuro());
			m_avvisi.add(new BilancioStatoAllerta(diff));
		}

		return m_avvisi;
	}
	
}
