/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class DatiMovimentoCassa {

	private Euro importoMovimento;
	private String motivazione;
	
	public DatiMovimentoCassa()
	{
		
	}
	
	public void creaDatiMovimentoCassa(Euro e)
	{
		importoMovimento = e;
	}
	
	public boolean controlla()
	{
		
		return !importoMovimento.equals(new Euro(new Float(0.0)));
	}

	public Euro getImportoMovimento() {
		return importoMovimento;
	}

	public void setImportoMovimento(Euro importoMovimento) {
		this.importoMovimento = importoMovimento;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
}
