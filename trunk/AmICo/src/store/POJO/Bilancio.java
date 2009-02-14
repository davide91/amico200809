/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import store.util.HibernateUtil;
import datatype.DatiBilancio;
import datatype.Euro;
import datatype.list.Euri;
import datatype.list.PianiPagamenti;
import datatype.list.UnitaImmobiliari;
import datatype.list.VociBilancio;
import enumeration.StatoBilancio;
import enumeration.TipoBilancio;

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
			link(vb);
		session.getTransaction().commit();
	}
	
	private void link(VoceBilancio vb) {
		vb.setBilancio(this);
		voci.add(vb);
		session.update(this);
	}

	public void eliminaVoceBilancio(VoceBilancio vb)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			voci.remove(vb);
			session.update(this);
		session.getTransaction().commit();
	}
	
	public void mettiInEsercizio()
	{	
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			dati.setStato(StatoBilancio.inEsercizio);
			session.update(this);
		session.getTransaction().commit();
	}
	
	public void terminaEsercizio()
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			dati.setStato(StatoBilancio.consuntivo);
		session.getTransaction().commit();
	}
	
	public void inserisciPianoPagamenti(PianoPagamenti pp)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			link(pp);
			
		session.getTransaction().commit();
	}
	
	private void link(PianoPagamenti pp)
	{
		pp.setBilancio(this);
		pianoPagamenti.add(pp);
		session.update(this);
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
		if(this.dati.getTipo().equals(TipoBilancio.straordinario))
			return this.saldo()>= (float)0.0;
		else if(this.saldo() == (float)0.0 && this.vociNonContabilizzate().getVoci().isEmpty())
		{
			for (Euro e : saldoUnita().getEuri()) {
				if(e.getEuro()!=(float)0.0)
					return false;
			}
			return true;
		}
		return false;
	}
	
	//sommo l'importo di ogni voce di bilancio // non presente nel class diagram
	public float saldo()
	{
		float ret = (float)0.0;
		for (VoceBilancio vb : voci) {
			ret += vb.getDati().getImporto().getEuro();
		}
		return ret;
	}
	
	public Euri saldoUnita()
	{
		return null;
	}
	
	public Euri moreUnita()
	{
		@SuppressWarnings("unused")
		UnitaImmobiliari uImm = this.condominio.recuperaUnitaImmobiliari();
		
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
