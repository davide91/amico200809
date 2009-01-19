/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.type.OrderedSetType;

import store.util.HibernateUtil;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import datatype.DatiPersona;
import datatype.DatiPersonaFisica;
import datatype.DatiPersonaGiuridica;

/**
 * @author bruno
 *
 */
public class Persona {

	protected long id;
	protected Set<Pagamento> pagamenti;
	protected Set<Proprieta> proprieta;
	
	
	private Session session;

	public Persona()
	{
		pagamenti = new HashSet<Pagamento>();
		proprieta = new TreeSet<Proprieta>();
	}

	public void modificaDati(DatiPersona datiPersona) 
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		if (datiPersona instanceof DatiPersonaFisica) {
			modificaDatiPersonaFisica((DatiPersonaFisica)datiPersona);
		}
		else{
			modificaDatiPersonaGiuridica((DatiPersonaGiuridica)datiPersona);	
		}
		session.getTransaction().commit();
	}
	
	private void modificaDatiPersonaFisica(DatiPersonaFisica dpf)
	{
		PersonaFisica pf = (PersonaFisica) this;
		pf.modificaDati(dpf);
	}
	
	private void modificaDatiPersonaGiuridica(DatiPersonaGiuridica dpg)
	{
		PersonaGiuridica pg = (PersonaGiuridica) this;
		pg.modificaDati(dpg);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Pagamento> getPagamenti() {
		return pagamenti;
	}

	public void setPagamenti(Set<Pagamento> pagamenti) {
		this.pagamenti = pagamenti;
	}

	public Set<Proprieta> getProprieta() {
		return proprieta;
	}

	public void setProprieta(Set<Proprieta> proprieta) {
		this.proprieta = proprieta;
	}
}
