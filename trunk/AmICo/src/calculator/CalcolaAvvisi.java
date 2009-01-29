package calculator;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import datatype.Data;
import datatype.DatiPagamento;
import datatype.Euro;
import datatype.Preferenze;
import datatype.list.Avvisi;
import datatype.list.Pagamenti;
import store.POJO.Cassa;
import store.POJO.Condominio;
import store.POJO.MovimentoCassa;
import store.POJO.Pagamento;

public class CalcolaAvvisi {

	private static Condominio m_condominio;
	private static Avvisi m_avvisi;
	
	public static Avvisi calcolaAvvisi(Condominio condominio) {
		
		m_condominio = condominio;
	
		m_avvisi = new Avvisi();
		
		calcolaPagamentiScaduti();
		
		// TODO
		return new Avvisi();
	}
	
	private static void calcolaPagamentiScaduti() {
		Pagamenti pagamenti = m_condominio.recuperaPagamenti();

		Iterator<Pagamento> pagamentiIter = pagamenti.getPagamenti().iterator();

		Data currData = 
			new Data(
					new Date(
							GregorianCalendar.getInstance().getTime().getTime()));
		
		int ritardo = m_condominio.getPreferenze().getRitardoAmmesso();
		
		while(pagamentiIter.hasNext())
		{
			Data scadenza = new Data(
					pagamentiIter.next().getDatiPagamento().getScadenza());
			
			scadenza.add(Calendar.DAY_OF_MONTH, ritardo);
			
			if ( currData.maggioreUguale(scadenza) )  
			{
				
				/*
				 * Pagamento scaduto
				 * 
				 */
			}
		}
	}
	
	private static void calcolaCassaSottoSogliaMinima() {
		Euro sogliaMin = m_condominio.getPreferenze().getSogliaMinimaCassa();
		
		Iterator<Cassa> cassaIter = m_condominio.getCassa().iterator();
		
		while(cassaIter.hasNext())
		{
			if( cassaIter.next().getSaldo().getEuro() < sogliaMin.getEuro() )
			{
				/*	TODO : Cassa sotto soglia minima
				 * 
				 * Perché la cassa ha getCondominio() ? Con m_condominio.getCassa() non accedo solo
				 * alle casse del condominio?
				 * 
				 * E' importante identificare una cassa dall'altra? 
				 * Se si, come?
				 * 
				 */
			}
		}
	}
	

	
}
