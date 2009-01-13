/**
 * 
 */
package store.POJO;

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
