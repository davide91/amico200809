/**
 * 
 */
package calculator;

import store.POJO.Bilancio;
import datatype.Euro;

/**
 * @author bruno
 *
 */
public class CalcoliFinanziari {

	
	public static Euro calcolaMora(Euro euro, int giorni, float interessi)
	{
		return new Euro((float)euro.getEuro()*giorni*interessi);
	}
	
	// ritorna RapportoPagamenti
	public static Object calcolaSpeseDaPagare(Bilancio bilancio)
	{
		return null;
	}
}
