package store.POJO;

import java.util.HashSet;
import java.util.Set;

import datatype.DatiCondominio;
import datatype.Indirizzo;
import datatype.PersoneConStessoIndirizzo;
import datatype.Preferenze;
import enumeration.StatoCondominio;

public class Condominio {

	private long id;
	private DatiCondominio datiCond;
	private Preferenze preferenze;
	private StatoCondominio statoCondominio;

	private Set<Cassa> cassa = new HashSet<Cassa>();
	private Set<TabellaMillesimale> tabelleMillesimali = new HashSet<TabellaMillesimale>(); 
	private Set<Bilancio> bilanci = new HashSet<Bilancio>();
	private Set<UnitaImmobiliare> unitaImmobiliari = new HashSet<UnitaImmobiliare>();
	
	/* ANCHE 
	 * persona (in persona, persona giuridica e persona fisica)
	 * */
	
	public Condominio() // costruttore per Hibernate
	{
		
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
		
		if(!(other.getDatiCond().equals(getDatiCond())))
			return false;
		if(!(other.getPreferenze().equals(getPreferenze())))
			return false;
		if(!(other.getStatoCondominio().equals(getStatoCondominio())))
			return false;
		return true;
	}
	
	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.datiCond == null) ? 0 : this.datiCond.hashCode())
								+ ((this.preferenze == null) ? 0 : this.preferenze.hashCode())
								+ ((this.statoCondominio == null) ? 0 : this.statoCondominio.hashCode());
		return result;
	}
	
	public void CreaCondominio()
	{
		
	}
	
	public void modificaDati(DatiCondominio dCond)
	{
		datiCond = dCond;
	}
	
	public void inserisciPersona(Persona p)
	{
		
	}
	
	public void inserisciUnitàImmobiliare(UnitaImmobiliare uImm)
	{
		unitaImmobiliari.add(uImm);
	}
	
	public void eliminaUnitàImmobiliare(UnitaImmobiliare uImm)
	{
		unitaImmobiliari.remove(uImm);
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
		return bilanci;
	}
	
	public Bilanci recuperaBilanciInEsercizio()
	{
		for (Bilancio b : bilanci) {
			if(b.isInEsercizio())
				//aggiungi alla lista di quelli da restituire
		}
	}
	
	public Cassa recuperaCassa()
	{
		
	}
	
	public DatiCondominio recuperaDatiCondominio()
	{
		
	}
	
	public Persone recuperaCondomini()
	{
		
	}
	
	public UnitàImmobiliari recuperaUnitàImmobiliari()
	{
		
	}
	
	public TabelleMillesimali recuperaTabelleMillesimali()
	{
		
	}
	
	public Pagamenti recuperaPagamenti()
	{
		
	}
	
	public void modificaPreferenze(Preferenze pref)
	{
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public DatiCondominio getDatiCond() {
		return datiCond;
	}

	public void setDatiCond(DatiCondominio datiCond) {
		this.datiCond = datiCond;
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
}
