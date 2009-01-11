/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class Preferenze {

	private Float interessiMora;
	private Integer ritardoAmmesso;
	private Euro sogliaMinimaCassa;
	
	public Preferenze()
	{
		
	}
	
	public Preferenze(Float interessi, Integer ritardo, Euro soglia)
	{
		this.interessiMora = interessi;
		this.ritardoAmmesso = ritardo;
		this.sogliaMinimaCassa = soglia;
	}

	public Float getInteressiMora() {
		return interessiMora;
	}

	public void setInteressiMora(Float interessiMora) {
		this.interessiMora = interessiMora;
	}

	public Integer getRitardoAmmesso() {
		return ritardoAmmesso;
	}

	public void setRitardoAmmesso(Integer ritardoAmmesso) {
		this.ritardoAmmesso = ritardoAmmesso;
	}

	public Euro getSogliaMinimaCassa() {
		return sogliaMinimaCassa;
	}

	public void setSogliaMinimaCassa(Euro sogliaMinimaCassa) {
		this.sogliaMinimaCassa = sogliaMinimaCassa;
	}
}
