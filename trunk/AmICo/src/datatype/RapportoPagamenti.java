/**
 * 
 */
package datatype;

import store.POJO.VoceBilancio;
import datatype.list.Euri;
import datatype.list.Persone;
import datatype.list.VociBilancio;

/**
 * @author bruno
 *
 */
public class RapportoPagamenti {

	
	private Euro totale = new Euro();
	private VociBilancio voci = new VociBilancio();
//	private Persone insolventi = new Persone(); non utilizzate perchè non abbiamo il piano pagamenti
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
	
	public void inserisciRapporto(VoceBilancio vb, Euro e)
	{
		voci.inserisciVoceBilancio(vb);
		dovuto.inserisciEuro(e);
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

/*	public Persone getInsolventi() {
		return insolventi;
	}

	public void setInsolventi(Persone insolventi) {
		this.insolventi = insolventi;
	}
*/
	public Euri getDovuto() {
		return dovuto;
	}

	public void setDovuto(Euri dovuto) {
		this.dovuto = dovuto;
	}
}
