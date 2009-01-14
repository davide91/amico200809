/**
 * 
 */
package store.POJO;

import javax.print.attribute.standard.MediaSize.Other;

import datatype.Data;
import datatype.DatiVoceBilancio;

/**
 * @author bruno
 *
 */
public class VoceBilancio {

	private long id;
	private DatiVoceBilancio dati;
	private Data dataContabilitazione;
	private MovimentoCassa contabilizzata;
	
	
	
	public VoceBilancio()
	{
		
	}
	
	public void creaVoceBilancio(DatiVoceBilancio dvb)
	{
		dati = dvb;
	}
	
	public void modificaDati(DatiVoceBilancio dvb)
	{
		dati = dvb;
	}
	
	public void ripartisci(TabellaMillesimale tab)
	{
		
	}

	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof VoceBilancio))
	   return false;
	 final VoceBilancio o = (VoceBilancio) other;
	 if (!o.getContabilizzata().equals(getContabilizzata()))
	   return false;
	 if (!o.getDataContabilitazione().equals(getDataContabilitazione()))
	   return false;
	 if(!o.getDati().equals(getDati()))
		return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = getContabilizzata().hashCode();
	 result = 29 * result + this.getDataContabilitazione().hashCode();
	 result = 29 * result + this.getDati().hashCode();
	 return result;
	}

	
	public DatiVoceBilancio getDati() {
		return dati;
	}

	public void setDati(DatiVoceBilancio dati) {
		this.dati = dati;
	}

	public Data getDataContabilitazione() {
		return dataContabilitazione;
	}

	public void setDataContabilitazione(Data dataContabilitazione) {
		this.dataContabilitazione = dataContabilitazione;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MovimentoCassa getContabilizzata() {
		return contabilizzata;
	}

	public void setContabilizzata(MovimentoCassa contabilizzata) {
		this.contabilizzata = contabilizzata;
	}
}
