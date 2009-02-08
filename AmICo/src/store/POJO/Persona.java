/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import store.util.HibernateUtil;
import datatype.DatiPersona;
import datatype.DatiPersonaFisica;
import datatype.DatiPersonaGiuridica;

/**
 * @author bruno
 *
 */
public class Persona {

	protected long id;
	protected Set<Pagamento> pagamenti = new HashSet<Pagamento>();
	protected Set<Proprieta> proprieta = new HashSet<Proprieta>();
	
	private Session session;

	public Persona(){
		
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
		session.update(this);
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

	public void modificaProprieta(Proprieta vecchia,Proprieta nuova)
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			proprieta.remove(vecchia);
			proprieta.add(nuova);
		session.update(this);
	}

	public void unlink(Proprieta p) {
		// TODO Auto-generated method stub
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			proprieta.remove(p);
		//session.update(this);
	}
	
/*	public void aggiungiProprieta(Proprieta prop) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			this.proprieta.add(prop);
		session.update(this);
		//session.getTransaction().commit(); //chiamata da un metodo con sessione già aperta, se lo chiudo eccezione
	}
*/
}
