/**
 * 
 */
package store.POJO;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author bruno
 *
 */
public class Proprieta {

	private long id;
	private float quota;
	private UnitaImmobiliare unitaImmobiliare;
	private SortedSet<Persona> proprietario = new TreeSet<Persona>();
	
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
	 if (!(o.getQuota() == getQuota()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
		return (int) (29 * this.getQuota());
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getQuota() {
		return quota;
	}

	public void setQuota(float quota) {
		this.quota = quota;
	}

	public SortedSet<Persona> getProprietario() {
		return proprietario;
	}

	public void setProprietario(SortedSet<Persona> proprietario) {
		this.proprietario = proprietario;
	}

	public UnitaImmobiliare getUnitaImmobiliare() {
		return unitaImmobiliare;
	}

	public void setUnitaImmobiliare(UnitaImmobiliare unitaImmobiliare) {
		this.unitaImmobiliare = unitaImmobiliare;
	}	
}
