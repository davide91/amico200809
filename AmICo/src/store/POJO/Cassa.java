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
