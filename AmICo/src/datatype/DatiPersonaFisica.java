/**
 * 
 */
package datatype;

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

	public CodiceFiscale getCF() {
		return cf;
	}

	public void setCF(CodiceFiscale cf) {
		cf = cf;
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
