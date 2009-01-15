/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import datatype.DatiBilancio;
import datatype.list.Euri;
import datatype.list.PianiPagamenti;
import datatype.list.VociBilancio;
import enumeration.StatoBilancio;

/**
 * @author bruno
 *
 */
public class Bilancio {

	private long id;
	private DatiBilancio dati;

	private SortedSet<VoceBilancio> voci = new TreeSet<VoceBilancio>(); 
	private Set<Pagamento> pagamenti = new HashSet<Pagamento>();
	private Set<PianoPagamenti> pianoPagamenti = new HashSet<PianoPagamenti>();
	private Condominio condominio;
		
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

	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof Bilancio))
	   return false;
	 final Bilancio o = (Bilancio) other;
	 if (!o.getDati().equals(getDati()))
	   return false;
	 if (!o.getPagamenti().equals(getPagamenti()))
	   return false;
	 if (!o.getPianoPagamenti().equals(getPianoPagamenti()))
		   return false;
	 if (!o.getVoci().equals(getVoci()))
		   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getPagamenti().hashCode();
	 result = 29 * result + this.getDati().hashCode();
	 result = 29 * result + this.getPianoPagamenti().hashCode();
	 result = 29 * result + this.getVoci().hashCode();
	 return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DatiBilancio getDati() {
		return dati;
	}

	public void setDati(DatiBilancio dati) {
		this.dati = dati;
	}

	public SortedSet<VoceBilancio> getVoci() {
		return voci;
	}

	public void setVoci(SortedSet<VoceBilancio> voci) {
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
}
