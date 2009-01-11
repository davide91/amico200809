/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class DatiPersonaGiuridica extends DatiPersona {

	private PartitaIva pIva;
	private String ragioneSociale;
	private Indirizzo indFiscale;
	
	public DatiPersonaGiuridica()
	{
		
	}
	
	public DatiPersonaGiuridica(PartitaIva iva, String ragione, Indirizzo ind, String tel, Email mail, String fax)
	{
		super(tel,mail,fax);
		this.pIva = iva;
		this.ragioneSociale = ragione;
		this.indFiscale = ind;
	}

	@Override
	public EsitoControlloDatiPersona controlla()
	{
		//definire il metodo in base a specifiche
		return null;
	}
	
	public PartitaIva getPIva() {
		return pIva;
	}

	public void setPIva(PartitaIva iva) {
		pIva = iva;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Indirizzo getIndFiscale() {
		return indFiscale;
	}

	public void setIndFiscale(Indirizzo indFiscale) {
		this.indFiscale = indFiscale;
	}
	
	
}
