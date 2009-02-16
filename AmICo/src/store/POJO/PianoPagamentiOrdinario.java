/**
 * 
 */
package store.POJO;

import datatype.DatiPianoPagamenti;

/**
 * @author bruno
 *
 */
public class PianoPagamentiOrdinario extends PianoPagamenti{

	public PianoPagamentiOrdinario()
	{
		
	}
	
	public void creaPianoPagamenti(DatiPianoPagamenti dpp)
	{
		dati = dpp;
	}
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof PianoPagamentiOrdinario))
	   return false;
	 final PianoPagamentiOrdinario o = (PianoPagamentiOrdinario) other;
	 if (!o.getDati().equals(this.getDati()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
		return this.getDati().hashCode();
	}
}