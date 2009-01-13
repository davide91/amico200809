/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class DatiMovimentoCassa {

	private Euro importo;
	private String motivazione;
	
	public DatiMovimentoCassa()
	{
		
	}
	
	public void creaDatiMovimentoCassa(Euro e)
	{
		importo = e;
	}
	
	public boolean controlla()
	{
		
		return !importo.equals(new Euro(new Float(0.0)));
	}

	public Euro getImporto() {
		return importo;
	}

	public void setImporto(Euro importo) {
		this.importo = importo;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
}
