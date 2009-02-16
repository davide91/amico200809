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
	
	private Persona eseguito_da;

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
	 if (!o.getEseguito_da().equals(getEseguito_da()))
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
	 result = 29 * result + this.getEseguito_da().hashCode();
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
	
	public Bilancio getBilancio() {
		return bilancio;
	}

	public void setBilancio(Bilancio bilancio) {
		this.bilancio = bilancio;
	}
	
	public Persona getEseguito_da() {
		return eseguito_da;
	}

	public void setEseguito_da(Persona eseguito_da) {
		this.eseguito_da = eseguito_da;
	}
}
