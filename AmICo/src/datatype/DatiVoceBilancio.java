/**
 * 
 */
package datatype;

import java.sql.Date;

import enumeration.TipoVoce;

/**
 * @author bruno
 *
 */
public class DatiVoceBilancio {

	private String titolo;
	private TipoVoce tipo;
	private String descrizione;
	private Euro importo;
	private Data dataPrevista;
	
	public DatiVoceBilancio(String tit, TipoVoce tipo, String descr, Euro e, Data d)
	{
		this.titolo=tit;
		this.tipo=tipo;
		this.descrizione=descr;
		this.importo=e;
		this.dataPrevista=d;
	}
	
	public DatiVoceBilancio()
	{
		
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void impostaDataPrevista(Data d)
	{
		dataPrevista = d;
	}
	
	public TipoVoce getTipo() {
		return tipo;
	}

	public void setTipo(TipoVoce tipo) {
		this.tipo = tipo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Euro getImporto() {
		return importo;
	}

	public void setImporto(Euro importo) {
		this.importo = importo;
	}

	public Date getDataPrevista() {
		return new Date(dataPrevista.getCalendar().getTime().getTime());
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = new Data(dataPrevista);
	}
}
