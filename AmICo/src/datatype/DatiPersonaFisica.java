/**
 * 
 */
package datatype;

import store.POJO.PersonaGiuridica;

/**
 * @author bruno
 *
 */
public class DatiPersonaFisica extends DatiPersona{

	private CodiceFiscale cf;
	private String nome;
	private String cognome;
	private String cell;
	private Indirizzo domicilio;
	
	public DatiPersonaFisica()
	{
		
	}
	
	public DatiPersonaFisica(CodiceFiscale cf, String nome, String cogn, String cell,Indirizzo dom, String tel, Email mail, String fax)
	{
		super(tel,mail,fax);
		this.cf = cf;
		this.nome = nome;
		this.cognome = cogn;
		this.cell = cell;
		this.domicilio = dom;
	}
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof DatiPersonaFisica))
	   return false;
	 final DatiPersonaFisica o = (DatiPersonaFisica) other;
	 if (!o.getCell().equals(this.getCell()))
	   return false;
	 if (!o.getCf().equals(this.getCf()))
		   return false;
	 if (!o.getCognome().equals(this.getCognome()))
		   return false;
	 if (!o.getDomicilio().equals(this.getDomicilio()))
		   return false;
	 if (!o.getFax().equals(this.getFax()))
		   return false;
	 if (!o.getMail().equals(this.getMail()))
		   return false;
	 if (!o.getNome().equals(this.getNome()))
		   return false;
	 if (!o.getTel().equals(this.getTel()))
		   return false;
	 return true;
	}
	
	@Override
	public int hashCode() {
		 int result;
		 result = this.getCell().hashCode();
		 result = 29 * result + this.getCognome().hashCode();
		 result = 29 * result + this.getFax().hashCode();
		 result = 29 * result + this.getNome().hashCode();
		 result = 29 * result + this.getTel().hashCode();
		 result = 29 * result + this.getCf().hashCode();
		 result = 29 * result + this.getDomicilio().hashCode();
		 result = 29 * result + this.getMail().hashCode();		 
		 return result;
	}
	
	@Override
	public EsitoControlloDati controlla()
	{	//definire il metodo in base a specifiche
		boolean codOK = cf.controlla();
		boolean mailOK = mail.controllaEMail();
		
		if(codOK && mailOK)
			return new DatiCorretti();
		else
		{
			DatiErrati err = new DatiErrati();
			if(!codOK) err.aggiungiCampo("Codice Fiscale Errato");
			if(!mailOK) err.aggiungiCampo("Indirizzo Mail Errato");
			return err;
		}
	}

	public CodiceFiscale getCf() {
		return cf;
	}

	public void setCf(CodiceFiscale cf) {
		this.cf = cf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public Indirizzo getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Indirizzo domicilio) {
		this.domicilio = domicilio;
	}
}
