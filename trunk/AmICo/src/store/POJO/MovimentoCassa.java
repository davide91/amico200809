/**
 * 
 */
package store.POJO;

import datatype.DatiMovimentoCassa;

/**
 * @author bruno
 *
 */
public class MovimentoCassa {

	private long id;
	private DatiMovimentoCassa dati;
	private Pagamento relativoAPagamento;
	private Cassa cassa;
	private VoceBilancio relativoAVoce;
	
	public MovimentoCassa()
	{
		
	}
	
	public MovimentoCassa(DatiMovimentoCassa datiMC)
	{
		dati = datiMC;
	}
	
	public void creaMovimentoCassa(DatiMovimentoCassa datiMC)
	{
		dati = datiMC;
	}
	
	public void modificaDati(DatiMovimentoCassa datiMC)
	{
		dati = datiMC;
	}

	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof MovimentoCassa))
	   return false;
	 final MovimentoCassa o = (MovimentoCassa) other;
//	 if (!o.getCassa().equals(getCassa()))
//	   return false;
	 if (!o.getDati().equals(getDati()))
	   return false;
	 if (!o.getRelativoAPagamento().equals(getRelativoAPagamento()))
		   return false;
	 if (!o.getRelativoAVoce().equals(getRelativoAVoce()))
		   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getRelativoAVoce().hashCode();
	 result = 29 * result + this.getDati().hashCode();
	 result = 29 * result + this.getRelativoAPagamento().hashCode();
//	 result = 29 * result + this.getCassa().hashCode();
	 return result;
	}
	
	public DatiMovimentoCassa getDati() {
		return dati;
	}

	public void setDati(DatiMovimentoCassa dati) {
		this.dati = dati;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pagamento getRelativoAPagamento() {
		return relativoAPagamento;
	}

	public void setRelativoAPagamento(Pagamento relativoAPagamento) {
		this.relativoAPagamento = relativoAPagamento;
	}

	public Cassa getCassa() {
		return cassa;
	}

	public void setCassa(Cassa cassa) {
		this.cassa = cassa;
	}

	public VoceBilancio getRelativoAVoce() {
		return relativoAVoce;
	}

	public void setRelativoAVoce(VoceBilancio relativoAVoce) {
		this.relativoAVoce = relativoAVoce;
	}
}
