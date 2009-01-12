/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import datatype.DatiBilancio;
import datatype.DatiCondominio;
import enumeration.StatoBilancio;

/**
 * @author bruno
 *
 */
public class Bilancio {

	private DatiBilancio dati;
	
	private Set<VoceBilancio> voci = new HashSet<VoceBilancio>(); 
	//manca piano pagamenti
	
	public Bilancio()
	{
		
	}
	
	public Bilancio(DatiBilancio db)
	{
		dati = db;
	}
	
	public void creaBilancio(DatiBilancio db)
	{
		dati = db;
	}
	
	public void modificaDati(DatiBilancio db)
	{
		dati = db;
	}
	
	public void inserisciVoceBilancio(VoceBilancio vb)
	{
		voci.add(vb);
	}
	
	public void eliminaVoceBilancio(VoceBilancio vb)
	{
		voci.remove(vb);
	}
	
	public void mettiInEsercizio()
	{
		dati.setStato(StatoBilancio.inEsercizio);
	}
	
	public void terminaEsercizio()
	{
		dati.setStato(StatoBilancio.)
	}
	
	public void inserisciPianoPagamenti(PianoPagamenti pp)
	{
		
	}
	
	public VociBilancio recuperaVociBilancio()
	{
		
	}
	
	public PianiPagamenti recuperaPianiPagamenti()
	{
		
	}
	
	public VociBilancio vociNonContabilizzate()
	{
		
	}
	
	public boolean terminabile()
	{
		
	}
	
	public Euri saldoUnità()
	{
		
	}
	
	public Euri moreUnità()
	{
		
	}
	
	
	
	
}
