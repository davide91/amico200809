/**
 * 
 */
package datatype;

import datatype.list.Euri;
import datatype.list.Persone;
import datatype.list.VociBilancio;

/**
 * @author bruno
 *
 */
public class RapportoPagamenti {

	
	private Euro totale;
	private VociBilancio voci = new VociBilancio();
	private Persone insolventi = new Persone();
	private Euri dovuto = new Euri();
	//le tre liste voci, insolventi e douto devono avere la stessa lunghezza ed essere "ordinate"
	
	private void calcolaTotale()
	{
		//calcolo dinamico del totale, visto che è un dato derived
		long derived=0;
		
		for (Euro e : dovuto.getEuri()) {
			derived+=e.getEuro();
		}
		totale.setEuro(derived);
	}

	public Euro getTotale() {
		calcolaTotale();
		return totale;
	}

	public void setTotale(Euro totale) {
		this.totale = totale;
	}

	public VociBilancio getVoci() {
		return voci;
	}

	public void setVoci(VociBilancio voci) {
		this.voci = voci;
	}

	public Persone getInsolventi() {
		return insolventi;
	}

	public void setInsolventi(Persone insolventi) {
		this.insolventi = insolventi;
	}

	public Euri getDovuto() {
		return dovuto;
	}

	public void setDovuto(Euri dovuto) {
		this.dovuto = dovuto;
	}
}
