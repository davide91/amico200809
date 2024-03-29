/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class DatiPersonaGiuridica extends DatiPersona {

	private PartitaIva pIva = new PartitaIva();
	private String ragioneSociale;
	private Indirizzo indFiscale = new Indirizzo();
	
	public DatiPersonaGiuridica()
	{
		
	}

	public DatiPersonaGiuridica(PartitaIva iva, String ragione, Indirizzo ind, String tel, Email mail, String fax)
	{
		super(tel,mail,fax);
		this.pIva = iva;
		this.ragioneSociale = ragione;
		this.indFiscale = ind;
	}

	@Override
	public EsitoControlloDati controlla()
	{
		boolean codOK = pIva.controlla();
		boolean mailOK = mail.controllaEMail();
		
		if(codOK && mailOK)
			return new DatiCorretti();
		else
		{
			DatiErrati err = new DatiErrati();
			if(!codOK) err.aggiungiCampo("Partita IVA Errata");
			if(!mailOK) err.aggiungiCampo("Indirizzo Mail Errato");
			return err;
		}
	}
	
	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof DatiPersonaGiuridica))
	   return false;
	 final DatiPersonaGiuridica o = (DatiPersonaGiuridica) other;
	 if (!o.getFax().equals(this.getFax()))
	   return false;
	 if (!o.getIndFiscale().equals(this.getIndFiscale()))
		   return false;
	 if (!o.getMail().equals(this.getMail()))
		   return false;
	 if (!o.getpIva().equals(this.getpIva()))
		   return false;
	 if (!o.getRagioneSociale().equals(this.getRagioneSociale()))
		   return false;
	 if (!o.getTel().equals(this.getTel()))
		   return false;
	 return true;
	}

	@Override
	public int hashCode() {
		 int result=0;
		 result = 29 * result + this.getFax().hashCode();
		 result = 29 * result + this.getRagioneSociale().hashCode();
		 result = 29 * result + this.getTel().hashCode();
		 result = 29 * result + this.getIndFiscale().hashCode();
		 result = 29 * result + this.getMail().hashCode();
		 result = 29 * result + this.getpIva().hashCode();		 
		 return result;
	}
	
	public PartitaIva getpIva() {
		return pIva;
	}

	public void setpIva(PartitaIva iva) {
		pIva = iva;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Indirizzo getIndFiscale() {
		return indFiscale;
	}

	public void setIndFiscale(Indirizzo indFiscale) {
		this.indFiscale = indFiscale;
	}
}