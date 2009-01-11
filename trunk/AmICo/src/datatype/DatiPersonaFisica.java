/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class DatiPersonaFisica extends DatiPersona{

	private CodiceFiscale cF;
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
		this.cF = cf;
		this.nome = nome;
		this.cognome = cogn;
		this.cell = cell;
		this.domicilio = dom;
	}
	
	@Override
	public EsitoControlloDatiPersona controlla()
	{
		//definire il metodo in base a specifiche
		return null;
	}

	public CodiceFiscale getCF() {
		return cF;
	}

	public void setCF(CodiceFiscale cf) {
		cF = cf;
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
