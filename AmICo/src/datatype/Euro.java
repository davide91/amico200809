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

	private long euro;

	public Euro() {
		euro = 0;
	}
	
	public Euro(long euro, long cent) {
		if (euro >= 0) {
			euro = euro*100 + cent;
		} else {
			euro = euro*100 - cent;
		}
	}

	public Euro(double d) {
		euro = (int)(d*100);
	}
	
	public long getValore() {
		return euro;
	}
	
	public int getCent() {
		if (euro > 0) {
			String valore_stringa = new Long(euro).toString();
			int lunghezza = valore_stringa.length();
			if (lunghezza >= 3){
				String cent_stringa = valore_stringa.substring(lunghezza-2, lunghezza);
				return  Integer.parseInt(cent_stringa);
			} else {
				return  Integer.parseInt(valore_stringa);
			}
		} if (euro < 0 ) {
			String valore_stringa = new Long(euro).toString();
			int lunghezza = valore_stringa.length();
			if (lunghezza > 3){
				String cent_stringa = valore_stringa.substring(lunghezza-2, lunghezza);
				return  Integer.parseInt(cent_stringa);
			} else {
				return  Integer.parseInt(valore_stringa);
			}
		} else {
			return  0;
		}
	}
	
	public int getEuroIntero() {
		if (euro > 0) {
			String valore_stringa = new Long(euro).toString();
			int lunghezza = valore_stringa.length();
			if (lunghezza >= 3){
				String euro_stringa = valore_stringa.substring(0, lunghezza-2);
				return  Integer.parseInt(euro_stringa);
			} else {
				return  0;
			}
		} else if (euro < 0 ) {
			String valore_stringa = new Long(euro).toString();
			int lunghezza = valore_stringa.length();
			if (lunghezza > 3){
				String euro_stringa = valore_stringa.substring(0, lunghezza-2);
				return  Integer.parseInt(euro_stringa);
			} else {
				return  0;
			}
		} else {
			return  0;
		}	
	}

	public Euro somma(Euro e) {
		this.euro = this.euro + e.getValore();
		return this;
	}

	public Euro sottrai(Euro e){
		this.euro = this.euro - e.getValore();
		return this;
	}

	public Euro moltiplica(int num) {
		Euro result = new Euro();
		for (int i = 1 ; i <= num; i++) {
			result = result.somma(this);
		}
		return result;	
	}

	public boolean minoreDi(Euro e){ 
		if (euro < e.getValore())
				return true;
		else return false;
	}
	
	public boolean maggioreDi(Euro e){
		if (euro > e.getValore())
				return true;
		else return false;
	}
	
	public boolean ugualeA(Euro e){
		if (euro == e.getValore())
				return true;
		else return false;
	}
	
    // restituisce la rappresentazione "stringa"
	public String toString() {
		if (this.getCent() > 0) {
			String euro_stringa = new Integer(this.getEuroIntero()).toString();
			String cent_stringa = new Integer(this.getCent()).toString();
			return euro_stringa + "." + cent_stringa;
		} else if (this.getCent() < 0) {
			int cent = this.getCent() * (-1);
			String euro_stringa = "-0";
			String cent_stringa = new Integer(cent).toString();
			return euro_stringa + "." + cent_stringa;
		} else {
			return new Integer(this.getEuroIntero()).toString();
		}
	}
	
	// dato l'Euro restituisce il double corrispondente
	public double toDouble() {
    	return (double)euro/100;
	}

	public long getEuro() {
		return euro;
	}

	public void setEuro(long euro) {
		this.euro = euro;
	}
	
	/*
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
	*/
}
