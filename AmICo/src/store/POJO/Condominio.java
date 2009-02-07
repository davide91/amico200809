package store.POJO;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import store.util.HibernateUtil;
import datatype.DatiCondominio;
import datatype.Euro;
import datatype.Preferenze;
import datatype.list.Bilanci;
import datatype.list.Pagamenti;
import datatype.list.Persone;
import datatype.list.TabelleMillesimali;
import datatype.list.UnitaImmobiliari;
import enumeration.StatoBilancio;
import enumeration.StatoCondominio;

public class Condominio {

	private long id;
	private DatiCondominio datiC;
	private Preferenze preferenze = new Preferenze((float)1.4,10,new Euro((float)1500.0));
	private StatoCondominio statoCondominio;

	protected Set<Cassa> cassa = new HashSet<Cassa>();
	protected Set<TabellaMillesimale> tabelleMillesimali  = new HashSet<TabellaMillesimale>();
	protected Set<Bilancio> bilanci = new HashSet<Bilancio>();
	protected Set<UnitaImmobiliare> unitaImmobiliari = new HashSet<UnitaImmobiliare>();
	protected Set<Persona> persone = new HashSet<Persona>();
	
	private Session session;
	
	public Condominio() // costruttore per Hibernate
	{
		Cassa c = new Cassa();
		c.setCondominio(this);
		cassa.add(c);
	}
	
	public void CreaCondominio()
	{
		Cassa c = new Cassa();
		c.setCondominio(this);
		cassa.add(c);
	}
	
	public void modificaDati(DatiCondominio dCond)
	{	
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			this.setDatiC(dCond);
			session.update(this);
		session.getTransaction().commit();
	}
	
	public void inserisciPersona(Persona p)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			link(p);
		session.getTransaction().commit();
	}

	private void link(Persona p)
	{
		persone.add(p);
		session.update(this);		
	}
	
	public void rimuoviPersona(Persona p)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			persone.remove(p);
			session.update(this);
		session.getTransaction().commit();
	}
	
	public void inserisciUnitaImmobiliare(UnitaImmobiliare uImm)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		link(uImm);
		session.update(this);
		session.getTransaction().commit();
	}
	
	private void link(UnitaImmobiliare ui)
	{
		ui.setCondominio(this);
		unitaImmobiliari.add(ui);
		session.persist(ui);
	}
	
	public void eliminaUnitaImmobiliare(UnitaImmobiliare uImm)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
			for (Proprieta p : uImm.getQuoteDiPossesso()) {
				Persona pers = p.getProprietario();
				pers.unlink(p);
				//session.delete(p);
			}
		//	uImm.getQuoteDiPossesso().clear();
			unitaImmobiliari.remove(uImm);
			session.delete(uImm);
		//	session.update(this);
						
		session.getTransaction().commit();
	}
	
	public void inserisciTabellaMillesimale(TabellaMillesimale tab)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			link(tab);
		session.getTransaction().commit();
	}
	
	public void link(TabellaMillesimale tab)
	{
		tab.setCondominio(this);
		tabelleMillesimali.add(tab);
		session.update(this);
	}
	
	public void inserisciBilancio(Bilancio b)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			link(b);
		session.getTransaction().commit();
	}

	 
	private void link(Bilancio b)
	{
		b.setCondominio(this);
		bilanci.add(b);
		session.update(this);
	}
	
	public Bilanci recuperaBilanci()
	{
		Bilanci bil = new Bilanci();
		
		for (Bilancio b : bilanci) {
			bil.inserisciBilancio(b);
		}
		return bil;
	}
	
	public Bilanci recuperaBilanciInEsercizio()
	{
		Bilanci bil = new Bilanci();
		
		for (Bilancio b : bilanci) {
			if(b.getDati().getStato().equals(StatoBilancio.inEsercizio))
				bil.inserisciBilancio(b);
		}
		return bil;
	}
	
	public Cassa recuperaCassa()
	{
		if(!cassa.isEmpty())
		{
			for (Cassa cas : cassa) {
				return cas;
			}
		}
		return new Cassa();
	}
	
	public DatiCondominio recuperaDatiCondominio()
	{
		return datiC;
	}
	
	public Persone recuperaCondomini()
	{
		Persone ret = new Persone();
		
		for (Persona p : persone) {
			ret.inserisciPersona(p);
		}
		return ret;
	}
	
	public UnitaImmobiliari recuperaUnitaImmobiliari()
	{
		UnitaImmobiliari ret = new UnitaImmobiliari();
		for (UnitaImmobiliare ui : unitaImmobiliari) {
			ret.inserisciUnitaImmobiliare(ui);
		}
		
		return ret;
	}
	
	public TabelleMillesimali recuperaTabelleMillesimali()
	{
		TabelleMillesimali ret = new TabelleMillesimali();
		
		for (TabellaMillesimale t : tabelleMillesimali) {
			ret.inserisciTabellaMillesimale(t);
		}
		return ret;
	}
	
	public Pagamenti recuperaPagamenti()
	{
		return new Pagamenti();
	}
	
	public void modificaPreferenze(Preferenze pref)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
	
			this.preferenze = pref;
	
		session.update(this);
		session.getTransaction().commit();
	}
	
	public void modificaStato(StatoCondominio st)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			statoCondominio = st;
		session.update(this);
		session.getTransaction().commit();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj ==null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Condominio other = (Condominio)obj;
		
		if(!(other.getDatiC().equals(getDatiC())))
			return false;
		if(!(other.getPreferenze().equals(getPreferenze())))
			return false;
		if(!(other.getStatoCondominio() == (getStatoCondominio())))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		 int result;
		 result = this.getDatiC().hashCode();
		 result = 29 * result + this.getPreferenze().hashCode();
		 result = 29 * result + this.getStatoCondominio().hashCode();
		 return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DatiCondominio getDatiC() {
		return datiC;
	}

	public void setDatiC(DatiCondominio datiC) {
		this.datiC = datiC;
	}

	public Preferenze getPreferenze() {
		return preferenze;
	}

	public void setPreferenze(Preferenze preferenze) {
		this.preferenze = preferenze;
	}

	public StatoCondominio getStatoCondominio() {
		return statoCondominio;
	}

	public void setStatoCondominio(StatoCondominio statoCondominio) {
		this.statoCondominio = statoCondominio;
	}

	public Set<Cassa> getCassa() {
		return cassa;
	}

	public void setCassa(Set<Cassa> cassa) {
		this.cassa = cassa;
	}

	public Set<TabellaMillesimale> getTabelleMillesimali() {
		return tabelleMillesimali;
	}

	public void setTabelleMillesimali(Set<TabellaMillesimale> tabelleMillesimali) {
		this.tabelleMillesimali = tabelleMillesimali;
	}

	public Set<Bilancio> getBilanci() {
		return bilanci;
	}

	public void setBilanci(Set<Bilancio> bilanci) {
		this.bilanci = bilanci;
	}

	public Set<UnitaImmobiliare> getUnitaImmobiliari() {
		return unitaImmobiliari;
	}

	public void setUnitaImmobiliari(Set<UnitaImmobiliare> unitaImmobiliari) {
		this.unitaImmobiliari = unitaImmobiliari;
	}

	public Set<Persona> getPersone() {
		return persone;
	}

	public void setPersone(Set<Persona> persone) {
		this.persone = persone;
	}

}
