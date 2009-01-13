/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import datatype.DatiBilancio;
import datatype.liste.Euri;
import datatype.liste.PianiPagamenti;
import datatype.liste.VociBilancio;
import enumeration.StatoBilancio;

/**
 * @author bruno
 *
 */
public class Bilancio {

	private long id;
	private DatiBilancio dati;
	private Set<VoceBilancio> voci = new HashSet<VoceBilancio>(); 
	private Set<Pagamento> pagamenti = new HashSet<Pagamento>();
	private Set<PianoPagamenti> pianoPagamenti = new HashSet<PianoPagamenti>();
	private Condominio condominio;
		
	public DatiBilancio getDati() {
		return dati;
	}

	public void setDati(DatiBilancio dati) {
		this.dati = dati;
	}

	public Set<VoceBilancio> getVoci() {
		return voci;
	}

	public void setVoci(Set<VoceBilancio> voci) {
		this.voci = voci;
	}

	public Set<Pagamento> getPagamenti() {
		return pagamenti;
	}

	public void setPagamenti(Set<Pagamento> pagamenti) {
		this.pagamenti = pagamenti;
	}

	public Set<PianoPagamenti> getPianoPagamenti() {
		return pianoPagamenti;
	}

	public void setPianoPagamenti(Set<PianoPagamenti> pianoPagamenti) {
		this.pianoPagamenti = pianoPagamenti;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

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
		dati.setStato(StatoBilancio.consuntivo);
	}
	
	public void inserisciPianoPagamenti(PianoPagamenti pp)
	{
		
	}
	
	public VociBilancio recuperaVociBilancio()
	{
		return null;
	}
	
	public PianiPagamenti recuperaPianiPagamenti()
	{
		return null;
	}
	
	public VociBilancio vociNonContabilizzate()
	{
		return null;
	}
	
	public boolean terminabile()
	{
		return false;
	}
	
	public Euri saldoUnità()
	{
		return null;
	}
	
	public Euri moreUnità()
	{
		return null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
