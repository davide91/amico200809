/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class DatiPersona {

	private String tel;
	private Email mail;
	private String fax;
	
	public DatiPersona()
	{
		
	}
	
	public DatiPersona(String tel, Email mail, String fax)
	{
		this.tel = tel;
		this.mail = mail;
		this.fax = fax;
	}
	
	public EsitoControlloDatiPersona controlla()
	{
		return null;
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
