/**
 * 
 */
package store.POJO;

import datatype.DatiPianoPagamenti;

/**
 * @author bruno
 *
 */
public class PianoPagamenti {

	protected DatiPianoPagamenti dati;
	
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
}
