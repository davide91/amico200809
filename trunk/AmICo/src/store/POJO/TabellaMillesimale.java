/**
 * 
 */
package store.POJO;

import datatype.DatiTabellaMillesimale;

/**
 * @author bruno
 *
 */
public class TabellaMillesimale {

	private long id;
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
	
	public void creaTabellaProprietàGenerale(float millesimi)
	{
		dati.setMillesimi(millesimi);
	}
	
	public void modificaDati(DatiTabellaMillesimale DTM)
	{
		dati = DTM;
	}

	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof TabellaMillesimale))
	   return false;
	 final TabellaMillesimale o = (TabellaMillesimale) other;
	 if (!o.getDati().equals(getDati()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getDati().hashCode();
	 return result;
	}
	
	public DatiTabellaMillesimale getDati() {
		return dati;
	}

	public void setDati(DatiTabellaMillesimale dati) {
		this.dati = dati;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
