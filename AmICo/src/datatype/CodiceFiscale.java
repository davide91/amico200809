/**
 * 
 */
package datatype;

import store.POJO.PersonaGiuridica;

/**
 * @author bruno
 * 
 */
public class CodiceFiscale {

	private String codiceFis; 
	
	public CodiceFiscale()
	{
		
	}

	public CodiceFiscale(String cod)
	{
		this.codiceFis = cod;
	}
	
	public boolean controlla()
	{	// pattern di controllo come per la mail
		int i, s, c;
	    String cf2;
	    int setdisp[] = {1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20,
	        11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23 };
	    if( codiceFis.length() == 0 || codiceFis.length() != 16) return false;
	   
	    cf2 = codiceFis.toUpperCase();
	    for( i=0; i<16; i++ ){
	        c = cf2.charAt(i);
	        if( ! ( c>='0' && c<='9' || c>='A' && c<='Z' ) )
	            return false;
	    }
	    s = 0;
	    for( i=1; i<=13; i+=2 ){
	        c = cf2.charAt(i);
	        if( c>='0' && c<='9' )
	            s = s + c - '0';
	        else
	            s = s + c - 'A';
	    }
	    for( i=0; i<=14; i+=2 ){
	        c = cf2.charAt(i);
	        if( c>='0' && c<='9' )     c = c - '0' + 'A';
	        s = s + setdisp[c - 'A'];
	    }
	    if( s%26 + 'A' != cf2.charAt(15) )
	        return false;
	    return true;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other)
			   return true;
		if (!(other instanceof CodiceFiscale))
			   return false;
		final CodiceFiscale o = (CodiceFiscale) other;
		return this.codiceFis.equals(o.codiceFis);
	}

	@Override
	public int hashCode() {
		return this.codiceFis.hashCode();
	}

	
	public String getCodiceFis() {
		return codiceFis;
	}

	public void setCodiceFis(String codiceFis) {
		this.codiceFis = codiceFis;
	}
	
}
