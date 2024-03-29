/**
 * 
 */
package store.POJO;


/**
 * @author bruno
 *
 */
public class Proprieta {

	private long id;
	private float quota;
	private UnitaImmobiliare unitaImmobiliare;
	private Persona proprietario;
	
	public Proprieta()
	{
		
	}
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof Proprieta))
	   return false;
	 final Proprieta o = (Proprieta) other;
	 if (!(o.getProprietario() == (getProprietario())))
		   return false;
	 if (!(o.getUnitaImmobiliare().equals(getUnitaImmobiliare())))
		   return false;
	 if (!(o.getQuota() == getQuota()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
		int ritorno = this.getProprietario().hashCode();
		ritorno =  29 * (ritorno + this.getUnitaImmobiliare().hashCode());
		return (int) (31 *(ritorno + this.getQuota()));
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getQuota() {
		return quota;
	}

	public void setQuota(float quota) {
		this.quota = quota;
	}

	public Persona getProprietario() {
		return proprietario;
	}

	public void setProprietario(Persona proprietario) {
		this.proprietario = proprietario;
	}

	public UnitaImmobiliare getUnitaImmobiliare() {
		return unitaImmobiliare;
	}

	public void setUnitaImmobiliare(UnitaImmobiliare unitaImmobiliare) {
		this.unitaImmobiliare = unitaImmobiliare;
	}	
}
