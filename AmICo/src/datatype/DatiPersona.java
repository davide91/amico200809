/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class DatiPersona {

	protected String tel = "";
	protected Email mail = new Email();
	protected String fax = "";
	
	public DatiPersona()
	{
		
	}
	
	public DatiPersona(String tel, Email mail, String fax)
	{
		this.tel = tel;
		this.mail = mail;
		this.fax = fax;
	}
	
	public EsitoControlloDati controlla()
	{
		return new DatiCorretti();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Email getMail() {
		return mail;
	}

	public void setMail(Email mail) {
		this.mail = mail;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
}
