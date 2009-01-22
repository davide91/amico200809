/**
 * 
 */
package datatype;

import sun.security.krb5.Realm;

/**
 * @author bruno
 *
 */
public class Euro {

	private float euro;
	
	public Euro()
	{
		
	}
	
	public Euro(float e)
	{
		this.euro = e;
	}

	public float getEuro() {
		return euro;
	}

	public void setEuro(float euro) {
		this.euro = euro;
	}
	
	public void aggiungi(float valuta)
	{
		euro += valuta;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (this == other)
			   return true;
		if (!(other instanceof Euro))
		   return false;
		final Euro o = (Euro) other;
		if (!(o.getEuro() == getEuro()))
		  return false;
		return true;
	}
}
