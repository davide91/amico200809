package store.POJO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import store.util.HibernateUtil;

import datatype.DatiCondominio;
import datatype.Preferenze;
import datatype.list.Bilanci;
import datatype.list.Pagamenti;
import datatype.list.Persone;
import datatype.list.TabelleMillesimali;
import datatype.list.UnitàImmobiliari;
import enumeration.StatoBilancio;
import enumeration.StatoCondominio;

public class Condominio {

	private long id;
	private DatiCondominio datiC;
	private Preferenze preferenze;
	private StatoCondominio statoCondominio;

	protected Set<Cassa> cassa = new HashSet<Cassa>();
	protected Set<TabellaMillesimale> tabelleMillesimali = new HashSet<TabellaMillesimale>(); 
	protected Set<Bilancio> bilanci = new HashSet<Bilancio>();
	protected Set<UnitaImmobiliare> unitaImmobiliari = new HashSet<UnitaImmobiliare>();
	protected Set<Persona> persone = new HashSet<Persona>();
	
	private Session session;
	
	public Condominio() // costruttore per Hibernate
	{
			
	}
	
	public void CreaCondominio()
	{
		
	}
	
	public void modificaDati(DatiCondominio dCond)
	{		
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			datiC = dCond;
			session.flush();
	//	session.update(this);
		session.getTransaction().commit();
	}
	
	public void inserisciPersona(Persona p)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
			p.setCondominio(this);
			persone.add(p);
		session.getTransaction().commit();
	}
	
	public void inserisciUnitàImmobiliare(UnitaImmobiliare uImm)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		link(this,uImm);
	
		session.getTransaction().commit();
	}
	
	private void link(Condominio c, UnitaImmobiliare ui)
	{
		ui.setCondominio(c);
		c.unitaImmobiliari.add(ui);
		session.persist(ui);
	}
	
	public void eliminaUnitàImmobiliare(UnitaImmobiliare uImm)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//	unitaImmobiliari.remove(uImm);
			session.delete(uImm);
		session.getTransaction().commit();
	}
	
	public void inserisciTabellaMillesimale(TabellaMillesimale tab)
	{
		tabelleMillesimali.add(tab);
	}
	
	public void inserisciBilancio(Bilancio b)
	{
		bilanci.add(b);
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
		return null;
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
	
	public UnitàImmobiliari recuperaUnitàImmobiliari()
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();	
		session.beginTransaction();
		
		UnitàImmobiliari ret = new UnitàImmobiliari();
	
		List UnitImm = session.createQuery("from UnitaImmobiliare where condominio = :cond").setParameter("cond", this).list();
		session.getTransaction().commit();

		for (Object o : UnitImm) {
			ret.inserisciUnitaImmobiliare((UnitaImmobiliare)o);
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
		return null;
	}
	
	public void modificaPreferenze(Preferenze pref)
	{
		preferenze = pref;
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
