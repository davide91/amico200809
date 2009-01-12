/**
 * 
 */
package store.POJO;

/**
 * @author bruno
 *
 */
public class MovimentoCassa {

	private long id;
	private DatiMovimentoCassa dati;
	private Pagamento relativoAPagamento;
	private Cassa cassa;
	private VoceBilancio relativoAVoce;
	
	public MovimentoCassa()
	{
		
	}
	
	public MovimentoCassa(DatiMovimetoCassa datiMC)
	{
		dati = datiMC;
	}
	
	public void creaMovimentoCassa(DatiMovimetoCassa datiMC)
	{
		dati = datiMC;
	}
	
	public void modificaDati(DatiMovimentoCassa datiMC)
	{
		dati = datiMC;
	}

	public DatiMovimentoCassa getDati() {
		return dati;
	}

	public void setDati(DatiMovimentoCassa dati) {
		this.dati = dati;
	}
}
