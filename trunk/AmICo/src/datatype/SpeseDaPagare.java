/**
 * 
 */
package datatype;

import java.util.ArrayList;
import java.util.List;
import datatype.DatiVoceBilancio;

/**
 * @author bruno
 *
 */
public class SpeseDaPagare extends Avviso {
	
	static private List<DatiVoceBilancio> listaDati= new ArrayList<DatiVoceBilancio>();
	
	public SpeseDaPagare() 
	{
		
	}
	
	public void inserisciSpesa(DatiVoceBilancio dati)
	{
		listaDati.add(dati);
	}
	
	public List<DatiVoceBilancio> getDatiVociBilancio() 
	{
		return listaDati;
	}
	
	public boolean isEmpty() 
	{
		return listaDati.isEmpty();
	}
}
