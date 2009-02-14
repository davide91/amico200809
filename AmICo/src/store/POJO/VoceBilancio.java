/**
 * 
 */
package store.POJO;

import java.sql.Date;

import org.hibernate.Session;

import store.util.HibernateUtil;
import datatype.Data;
import datatype.DatiVoceBilancio;

/**
 * @author bruno
 *
 */
public class VoceBilancio {

	private long id;
	
	private DatiVoceBilancio dati;
	private Data dataContabilitazione;
	
	private Bilancio bilancio;
	private MovimentoCassa contabilizzata = null;
	private TabellaMillesimale ripartizione = null;
	
	private Session session;
	
	public VoceBilancio()
	{
		
	}
	
	public VoceBilancio(DatiVoceBilancio dvb)
	{
		dati = dvb;
		Data d = new Data();
		d.creaCurrenDate();
		dataContabilitazione = d;
	}
	
	public void modificaDati(DatiVoceBilancio dvb)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			dati = dvb;
		session.update(this);
		session.getTransaction().commit();
	}
	
	public void ripartisci(TabellaMillesimale tab)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			ripartizione = tab;
		session.update(this);
		session.getTransaction().commit();
	}
	
	public void contabilizzaMovimento(MovimentoCassa m)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			this.contabilizzata = m;
		session.update(this);
		session.getTransaction().commit();
	}

	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof VoceBilancio))
	   return false;
	 final VoceBilancio o = (VoceBilancio) other;
	 if (!o.getDataContabilitazione().equals(getDataContabilitazione()))
	   return false;
	 if(!o.getDati().equals(getDati()))
		return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getDati().hashCode();
	 result = 29 * result + this.getDataContabilitazione().hashCode();
	 return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DatiVoceBilancio getDati() {
		return dati;
	}

	public void setDati(DatiVoceBilancio dati) {
		this.dati = dati;
	}

	public Data recuperaDataContabilizzazione()
	{
		return dataContabilitazione;
	}
	
	public Date getDataContabilitazione() {
		return new Date(dataContabilitazione.getCalendar().getTime().getTime());
	}

	public void setDataContabilitazione(Date dataContabilitazione) {
		this.dataContabilitazione = new Data(dataContabilitazione);
	}

	public Bilancio getBilancio() {
		return bilancio;
	}

	public void setBilancio(Bilancio bilancio) {
		this.bilancio = bilancio;
	}

	public MovimentoCassa getContabilizzata() {
		return contabilizzata;
	}

	public void setContabilizzata(MovimentoCassa contabilizzata) {
		this.contabilizzata = contabilizzata;
	}

	public TabellaMillesimale getRipartizione() {
		return ripartizione;
	}

	public void setRipartizione(TabellaMillesimale ripartizione) {
		this.ripartizione = ripartizione;
	}
}
