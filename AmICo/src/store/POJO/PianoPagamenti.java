/**
 * 
 */
package store.POJO;

import java.util.SortedSet;
import java.util.TreeSet;

import datatype.DatiPianoPagamenti;

/**
 * @author bruno
 *
 */
public class PianoPagamenti {

	protected DatiPianoPagamenti dati;
	protected SortedSet<Pagamento> pagamenti = new TreeSet<Pagamento>();
	protected Bilancio bilancio;
	
	public PianoPagamenti()
	{
		
	}
	
	public PianoPagamenti(DatiPianoPagamenti dpp)
	{
		dati = dpp;
	}
	
	public void modificaDati(DatiPianoPagamenti dpp)
	{
		dati = dpp;
	}

	public DatiPianoPagamenti getDati() {
		return dati;
	}

	public void setDati(DatiPianoPagamenti dati) {
		this.dati = dati;
	}
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof PianoPagamenti))
	   return false;
	 final PianoPagamenti o = (PianoPagamenti) other;
	 if (!o.getDati().equals(this.getDati()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
		return this.getDati().hashCode();
	}
}
