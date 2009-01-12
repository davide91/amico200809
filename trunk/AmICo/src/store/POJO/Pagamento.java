/**
 * 
 */
package store.POJO;

/**
 * @author bruno
 *
 */
public class Pagamento {

	private long id;
	private DatiPagamento datiPagamento;
//	private Bilancio bilancio;
	private MovimentoCassa pagato;
	private PianoPagamenti pagamenti;
	private Persona eseguitoDa;
	
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

	public Pagamento()
	{
		
	}
	
	public void modificaDati(DatiPagamento dp)
	{
		datiPagamento = dp;
	}
}
