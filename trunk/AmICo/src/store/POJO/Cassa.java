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
	
	private Euro saldo;
	private Set<MovimentoCassa> movimentiDiCassa = new HashSet<MovimentoCassa>();
	
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
	
	

}
