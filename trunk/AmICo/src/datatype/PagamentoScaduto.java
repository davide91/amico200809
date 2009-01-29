/**
 * 
 */
package datatype;

import store.POJO.Pagamento;
import datatype.list.Pagamenti;

/**
 * @author bruno
 *
 */
public class PagamentoScaduto extends Avviso{
	
	private Pagamenti pagamentiScaduti = new Pagamenti();
	
	public PagamentoScaduto(Pagamenti p)
	{
		pagamentiScaduti = p;
	}
	
	public void inserisciPagamento(Pagamento p)
	{
		pagamentiScaduti.inserisciPagamento(p);
	}

	public Pagamenti getPagamentiScaduti() {
		return pagamentiScaduti;
	}

	public void setPagamentiScaduti(Pagamenti pagamentiScaduti) {
		this.pagamentiScaduti = pagamentiScaduti;
	}
}
