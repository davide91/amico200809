/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class DatiTabellaMillesimale {

	private String nome;
	private String descrizione;
	private float millesimi; // va messe come sequenza di float ordinata
	
	public DatiTabellaMillesimale()
	{
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getMillesimi() {
		return millesimi;
	}

	public void setMillesimi(float millesimi) {
		this.millesimi = millesimi;
	}
	
	public boolean controlla()
	{
		return false;
	}
}
