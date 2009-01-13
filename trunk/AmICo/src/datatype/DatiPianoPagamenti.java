/**
 * 
 */
package datatype;

import enumeration.TipoPianoPagamenti;

/**
 * @author bruno
 *
 */
public class DatiPianoPagamenti {

	private Euro importo;
	private int percentuali;
	private Data scadenze;
	private TipoPianoPagamenti tipo;
	
	public DatiPianoPagamenti()
	{
		
	}
	
	public boolean controllo()
	{
		return false;
	}

	public Euro getImporto() {
		return importo;
	}

	public void setImporto(Euro importo) {
		this.importo = importo;
	}

	public int getPercentuali() {
		return percentuali;
	}

	public void setPercentuali(int percentuali) {
		this.percentuali = percentuali;
	}

	public Data getScadenze() {
		return scadenze;
	}

	public void setScadenze(Data scadenze) {
		this.scadenze = scadenze;
	}

	public TipoPianoPagamenti getTipo() {
		return tipo;
	}

	public void setTipo(TipoPianoPagamenti tipo) {
		this.tipo = tipo;
	}
}
