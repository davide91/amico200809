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
	protected Set<Pagamento> pagamenti;
	protected Set<Proprieta> proprieta;
//	protected Set<Condominio> condomini;
	
	private Session session;

	public Persona()
	{
		pagamenti = new HashSet<Pagamento>();
		proprieta = new HashSet<Proprieta>();
//		condomini = new HashSet<Condominio>();
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
}
