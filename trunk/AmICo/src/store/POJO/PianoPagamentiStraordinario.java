/**
 * 
 */
package store.POJO;

import datatype.DatiPianoPagamenti;

/**
 * @author bruno
 *
 */
public class PianoPagamentiStraordinario extends PianoPagamenti{

	private TabellaMillesimale ripartizione;
	
	public PianoPagamentiStraordinario()
	{
		
	}
	
	public void creaPianoPagamenti(DatiPianoPagamenti dpp, TabellaMillesimale tab)
	{
		dati = dpp;
		ripartizione = tab;
	}
	
	public void modificaDati(DatiPianoPagamenti dpp, TabellaMillesimale tab)
	{
		dati = dpp;
		ripartizione = tab;
	}
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof PianoPagamentiStraordinario))
	   return false;
	 final PianoPagamentiStraordinario o = (PianoPagamentiStraordinario) other;
	 if (!o.getDati().equals(this.getDati()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
		return this.getDati().hashCode();
	}

	public TabellaMillesimale getRipartizione() {
		return ripartizione;
	}

	public void setRipartizione(TabellaMillesimale ripartizione) {
		this.ripartizione = ripartizione;
	}
}
