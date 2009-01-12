/**
 * 
 */
package store.POJO;

/**
 * @author bruno
 *
 */
public class VoceBilancio {

	private DatiVoceBialncio dati;
	private Data dataContabilitazione;
	//manca il link a movimento cassa
	
	
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

	public DatiVoceBialncio getDati() {
		return dati;
	}

	public void setDati(DatiVoceBialncio dati) {
		this.dati = dati;
	}

	public Data getDataContabilitazione() {
		return dataContabilitazione;
	}

	public void setDataContabilitazione(Data dataContabilitazione) {
		this.dataContabilitazione = dataContabilitazione;
	}
}
