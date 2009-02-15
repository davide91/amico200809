/**
 * 
 */
package calculator;

import store.POJO.Bilancio;
import store.POJO.VoceBilancio;
import datatype.Euro;
import datatype.RapportoPagamenti;
import enumeration.TipoBilancio;
import enumeration.TipoVoce;


public class CalcoliFinanziari {

	
	public static Euro calcolaMora(Euro euro, int giorni, float interessi)
	{
		return new Euro((float)euro.getEuro()*giorni*interessi);
	}
	
	// ritorna RapportoPagamenti
	public static RapportoPagamenti calcolaSpeseDaPagare(Bilancio bilancio)
	{
		RapportoPagamenti r = new RapportoPagamenti();
		
		for (VoceBilancio vb : bilancio.recuperaVociBilancio().getVoci()) {
			if(vb.getContabilizzata()==null) 	// se la voce non è stata contabilizzata in cassa
				if(vb.getDati().getTipo().equals(TipoVoce.incasso))
					r.inserisciRapporto(vb, vb.getDati().getImporto());
				else
					r.inserisciRapporto(vb, vb.getDati().getImporto().ritornaNegativo());
		}
		
		return r;
	}
}
