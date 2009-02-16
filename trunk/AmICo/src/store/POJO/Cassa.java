/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import store.util.HibernateUtil;

import datatype.Euro;

/**
 * @author bruno
 *
 */
public class Cassa {
	
	private long id;
	private Euro saldo = new Euro((float)0.0);
	private Set<MovimentoCassa> movimentiDiCassa = new HashSet<MovimentoCassa>();
	private Condominio condominio;
	
	private Session session;
	
	public Cassa()
	{
		saldo=new Euro((float)0.0);
	}
	
	public void registraMovimentoCassa(MovimentoCassa movC)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			saldo.somma(movC.getDati().getImportoMovimento());
			movC.setCassa(this);
			movimentiDiCassa.add(movC);
		session.update(this);
		session.getTransaction().commit();
	}

	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof Cassa))
	   return false;
	 final Cassa o = (Cassa) other;
	 if (!o.getSaldo().equals(getSaldo()))
		   return false;
	 return true;
	}

	@Override
	public int hashCode() {
	 int result;
	 result = this.getSaldo().hashCode();
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