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
	 int result;
	 result = this.getQuota();
	 return result;
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
}
