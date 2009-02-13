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
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof Preferenze))
	   return false;
	 final Preferenze o = (Preferenze) other;
	 if (!o.getInteressiMora().equals(getInteressiMora()))
	   return false;
	 if (!o.getRitardoAmmesso().equals(getRitardoAmmesso()))
	   return false;
	 if (!o.getSogliaMinimaCassa().equals(getSogliaMinimaCassa()))
		   return false;
	 return true;
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
