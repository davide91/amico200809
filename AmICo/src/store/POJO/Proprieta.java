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
	private double quota;
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
	 if (!(o.getQuota() == getQuota()))
	   return false;
	 return true;
	}

	@Override
	public int hashCode() {
		int ritorno = (this.getProprietario().hashCode());
		return (int) (ritorno +(29 * this.getQuota()));
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

	public void setQuota(double quota) {
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
