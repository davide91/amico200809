/**
 * 
 */
package store.POJO;

/**
 * @author bruno
 *
 */
public class VoceBilancio {

	private long id;
	private DatiVoceBialncio dati;
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
