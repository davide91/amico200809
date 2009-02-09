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
public class PagamentoInScadenza extends Avviso{
	
	private Pagamenti pagamentiInScadenza = new Pagamenti();
	
	public PagamentoInScadenza(Pagamenti p)
	{
		pagamentiInScadenza = p;
	}
	
	public void inserisciPagamento(Pagamento p)
	{
		pagamentiInScadenza.inserisciPagamento(p);
	}

	public Pagamenti getPagamentiInScadenza() {
		return pagamentiInScadenza;
	}

	public void setPagamentiInScadenza(Pagamenti pagamentiScaduti) {
		this.pagamentiInScadenza = pagamentiScaduti;
	}
}
