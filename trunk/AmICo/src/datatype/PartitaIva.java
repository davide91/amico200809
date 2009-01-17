/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class PartitaIva {

	private String partIva;
	
	public PartitaIva()
	{
		
	}
	
	public PartitaIva(String partIva)
	{
		this.partIva = partIva;
	}
	
	public boolean controlla()
	{
		// pattern di controllo come per la partita iva
		int i, c, s;
	    if( partIva.length() == 0 || partIva.length() != 11)  return false;
	   
	    for( i=0; i<11; i++ ){
	        if( partIva.charAt(i) < '0' || partIva.charAt(i) > '9' )
	            return false;
	    }
	    s = 0;
	    for( i=0; i<=9; i+=2 )
	        s += partIva.charAt(i) - '0';
	    for( i=1; i<=9; i+=2 ){
	        c = 2*( partIva.charAt(i) - '0' );
	        if( c > 9 )  c = c - 9;
	        s += c;
	    }
	    if( ( 10 - s%10 )%10 != partIva.charAt(10) - '0' )
	        return false;
	    return true;
	}

	public String getPartIva() {
		return partIva;
	}

	public void setPartIva(String partIva) {
		this.partIva = partIva;
	}
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof PartitaIva))
	   return false;
	 final PartitaIva pi = (PartitaIva)other;
	 return this.partIva.equals(pi.partIva);
	}
}
