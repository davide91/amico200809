/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Session;

import store.util.HibernateUtil;

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

	private Set<VoceBilancio> voci = new HashSet<VoceBilancio>(); 
	private Set<Pagamento> pagamenti = new HashSet<Pagamento>();
	private Set<PianoPagamenti> pianoPagamenti = new HashSet<PianoPagamenti>();
	private Condominio condominio;
		
	private Session session;
	
	public Bilancio()
	{
		
	}
	
	public Bilancio(DatiBilancio db)
	{
		dati = db;
	}
	
	public void creaBilancio(DatiBilancio db)
	{	session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			dati = db;
		session.update(this);
		session.getTransaction().commit();
	}
	
	public void modificaDati(DatiBilancio db)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			dati = db;
		session.update(this);
		session.getTransaction().commit();
	}
	
	public void inserisciVoceBilancio(VoceBilancio vb)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			voci.add(vb);
		session.update(this);
		session.getTransaction().commit();
		
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
		pianoPagamenti.add(pp);
	}
	
	public VociBilancio recuperaVociBilancio()
	{
		VociBilancio ret = new VociBilancio();
		
		for (VoceBilancio vb : voci) {
			ret.inserisciVoceBilancio(vb);
		}
		return ret;
	}
	
	public PianiPagamenti recuperaPianiPagamenti()
	{
		PianiPagamenti ret = new PianiPagamenti();
		
		for (PianoPagamenti pp : pianoPagamenti) {
			ret.inserisciPianoPagamenti(pp);
		}
		return ret;
	}
	
	public VociBilancio vociNonContabilizzate()
	{
		VociBilancio ret = new VociBilancio();
		
		for (VoceBilancio vb : voci) {
			if(vb.getContabilizzata() == null)
				ret.inserisciVoceBilancio(vb);
		}
		return ret;
	}
	
	public boolean terminabile()
	{
		return false;
	}
	
	public Euri saldoUnita()
	{
		return null;
	}
	
	public Euri moreUnita()
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
}
