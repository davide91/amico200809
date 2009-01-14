package datatype;

import enumeration.StatoBilancio;
import enumeration.TipoBilancio;

public class DatiBilancio {

	private TipoBilancio tipo;
	private Data inizio;
	private Data fine;
	private String titolo;
	private String descrizione;
	private StatoBilancio stato;
	
	public TipoBilancio getTipo() {
		return tipo;
	}
	public void setTipo(TipoBilancio tipo) {
		this.tipo = tipo;
	}
	public Data getInizio() {
		return inizio;
	}
	public void setInizio(Data inizio) {
		this.inizio = inizio;
	}
	public Data getFine() {
		return fine;
	}
	public void setFine(Data fine) {
		this.fine = fine;
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
