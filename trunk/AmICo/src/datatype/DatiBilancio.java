package datatype;

import java.sql.Date;

import enumeration.StatoBilancio;
import enumeration.TipoBilancio;

public class DatiBilancio {

	private TipoBilancio tipo;
	private Data inizio;
	private Data fine = new Data();
	private String titolo;
	private String descrizione;
	private StatoBilancio stato;
	
	public DatiBilancio(TipoBilancio tb, Data iniz, String tit, String descr, StatoBilancio stat)
	{
		tipo = tb;
		inizio = iniz;
		titolo = tit;
		descrizione = descr;
		stato = stat;
	}
	
	public DatiBilancio()
	{
	}
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof DatiBilancio))
	   return false;
	 final DatiBilancio o = (DatiBilancio) other;
	 if (!o.getDescrizione().equals(getDescrizione()))
		 return false;
	 if (!o.getFine().equals(getFine()))
		 return false;
	 if (!o.getInizio().equals(getInizio()))
		 return false;
	 if (!o.getStato().equals(getStato()))
		 return false;
	 if (!o.getTipo().equals(getTipo()))
		 return false;
	 if (!o.getTitolo().equals(getTitolo()))
		 return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getDescrizione().hashCode();
	 result = 29 * result + this.getTitolo().hashCode();
	 result = 29 * result + this.getInizio().hashCode();
	 result = 29 * result + this.getStato().hashCode();
	 result = 29 * result + this.getTipo().hashCode();
	 return result;
	}
	
	public void impostaDataFine(Data d)
	{
		fine = d;
	}
	
	public void impostaDataInizio(Data d)
	{
		inizio = d;
	}

	public TipoBilancio getTipo() {
		return tipo;
	}
	public void setTipo(TipoBilancio tipo) {
		this.tipo = tipo;
	}
	public Date getInizio() {
		return new Date(inizio.getCalendar().getTime().getTime());
	}
	public void setInizio(Date inizio) {
		this.inizio = new Data(inizio);
	}

	public Date getFine() {
		return new Date(fine.getCalendar().getTime().getTime());
	}

	public void setFine(Date fine) {
		this.fine = new Data(fine);
	}

	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public StatoBilancio getStato() {
		return stato;
	}
	public void setStato(StatoBilancio stato) {
		this.stato = stato;
	}
}
