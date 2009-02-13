/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import store.POJO.Pagamento;

/**
 * @author bruno
 *
 */
public class Pagamenti {

	private List<Pagamento> pagamenti = new ArrayList<Pagamento>();
	
	public Pagamenti() {
		
	}
	
	public void inserisciPagamento(Pagamento p)
	{
		pagamenti.add(p);
	}

	public List<Pagamento> getPagamenti() {
		return pagamenti;
	}

	public void setBilanci(List<Pagamento> pagamenti) {
		this.pagamenti = pagamenti;
	}
}
