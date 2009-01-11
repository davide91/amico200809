/**
 * 
 */
package store.POJO;

import datatype.DatiCondominio;

/**
 * @author bruno
 *
 */
public class TabellaMillesimale {

	private DatiTabellaMillesimale dati;
	
	public TabellaMillesimale()
	{
		
	}
	
	public TabellaMillesimale(DatiTabellaMillesimale DTM)
	{
		dati = DTM;
	}
	
	public void creaTabellaMillesimale(DatiTabellaMillesimale DTM)
	{
		dati = DTM;
	}
	
	public void creaTabellaProprietàGenerale(Reali millesimi)
	{
		dati.setMillesimi(millesimi);
	}
	
	public void modificaDati(DatiTabellaMillesimale DTM)
	{
		dati = DTM;
	}

	public DatiTabellaMillesimale getDati() {
		return dati;
	}

	public void setDati(DatiTabellaMillesimale dati) {
		this.dati = dati;
	}
}
