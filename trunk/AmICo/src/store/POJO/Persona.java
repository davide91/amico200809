/**
 * 
 */
package store.POJO;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

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
	protected Set<Pagamento> pagamenti = new HashSet<Pagamento>();
	protected Condominio condominio;
	
	private Session session;
	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	protected Set<Pagamento> getPagamenti() {
		return pagamenti;
	}

	protected void setPagamenti(Set<Pagamento> pagamenti) {
		this.pagamenti = pagamenti;
	}

	public Persona()
	{
		
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
	
	
	
	protected long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

}
