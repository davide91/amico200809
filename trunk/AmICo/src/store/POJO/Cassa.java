/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import datatype.Euro;

/**
 * @author bruno
 *
 */
public class Cassa {
	
	private long id;
	private Euro saldo;
	private Set<MovimentoCassa> movimentiDiCassa = new HashSet<MovimentoCassa>();
	private Condominio condominio;
	
	public Cassa()
	{
		
	}
	
	public void registraMovimentoCassa(MovimentoCassa movC)
	{
		movimentiDiCassa.add(movC);
	}

	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof Cassa))
	   return false;
	 final Cassa o = (Cassa) other;
//	 if (!o.getCondominio().equals(getCondominio()))
//	   return false;
	 if (!o.getMovimentiDiCassa().equals(getMovimentiDiCassa()))
	   return false;
	 if (!o.getSaldo().equals(getSaldo()))
		   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getSaldo().hashCode();
	 result = 29 * result + this.getMovimentiDiCassa().hashCode();
	// result = 29 * result + this.getCondominio().hashCode();
	 return result;
	}
	
	public Euro getSaldo() {
		return saldo;
	}

	public void setSaldo(Euro saldo) {
		this.saldo = saldo;
	}

	public Set<MovimentoCassa> getMovimentiDiCassa() {
		return movimentiDiCassa;
	}

	public void setMovimentiDiCassa(Set<MovimentoCassa> movimentiDiCassa) {
		this.movimentiDiCassa = movimentiDiCassa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
}
