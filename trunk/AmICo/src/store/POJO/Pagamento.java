/**
 * 
 */
package store.POJO;

import datatype.DatiPagamento;

/**
 * @author bruno
 *
 */
public class Pagamento {

	private long id;
	private DatiPagamento datiPagamento;
	private MovimentoCassa pagato;
	private Bilancio bilancio;
	private PianoPagamenti pagamenti;
	private Persona eseguitoDa;
	
	public Pagamento()
	{
	
	}
	
	public void modificaDati(DatiPagamento dp)
	{
		datiPagamento = dp;
	}
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof Pagamento))
	   return false;
	 final Pagamento o = (Pagamento) other;
	 if (!o.getDatiPagamento().equals(getDatiPagamento()))
	   return false;
	 if (!o.getEseguitoDa().equals(getEseguitoDa()))
	   return false;
	 if (!o.getPagamenti().equals(getPagamenti()))
		   return false;
	 if (!o.getPagato().equals(getPagato()))
		   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getDatiPagamento().hashCode();
	 result = 29 * result + this.getEseguitoDa().hashCode();
	 result = 29 * result + this.getPagamenti().hashCode();
	 result = 29 * result + this.getPagato().hashCode();
	 return result;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DatiPagamento getDatiPagamento() {
		return datiPagamento;
	}

	public void setDatiPagamento(DatiPagamento datiPagamento) {
		this.datiPagamento = datiPagamento;
	}

	public MovimentoCassa getPagato() {
		return pagato;
	}

	public void setPagato(MovimentoCassa pagato) {
		this.pagato = pagato;
	}

	public PianoPagamenti getPagamenti() {
		return pagamenti;
	}

	public void setPagamenti(PianoPagamenti pagamenti) {
		this.pagamenti = pagamenti;
	}

	public Persona getEseguitoDa() {
		return eseguitoDa;
	}

	public void setEseguitoDa(Persona eseguitoDa) {
		this.eseguitoDa = eseguitoDa;
	}

	public Bilancio getBilancio() {
		return bilancio;
	}

	public void setBilancio(Bilancio bilancio) {
		this.bilancio = bilancio;
	}
}
