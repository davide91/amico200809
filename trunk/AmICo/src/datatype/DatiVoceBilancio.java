/**
 * 
 */
package datatype;

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
	
	public DatiVoceBilancio()
	{
		
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
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

	public Data getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Data dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
}
