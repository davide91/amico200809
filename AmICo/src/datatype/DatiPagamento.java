/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class DatiPagamento {

	private Euro mora;
	private Euro importo;
	private String codice;
	private Data scadenza;
	private Data data;
	
	public DatiPagamento()
	{
		
	}
	
	public boolean controlla()
	{
		return false;
	}

	public Euro getMora() {
		return mora;
	}

	public void setMora(Euro mora) {
		this.mora = mora;
	}

	public Euro getImporto() {
		return importo;
	}

	public void setImporto(Euro importo) {
		this.importo = importo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Data getScadenza() {
		return scadenza;
	}

	public void setScadenza(Data scadenza) {
		this.scadenza = scadenza;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
}

