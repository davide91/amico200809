package boundary;

import datatype.DatiMovimentoCassa;
import datatype.list.VociBilancio;
import executor.GestoreCassa;
import store.POJO.Cassa;

public class AccedereCassa implements BaseBoundary {

	private Cassa m_cassa;
	private GestoreCassa m_gestoreCassa;

	public AccedereCassa(GestoreCassa gestoreCassa, Cassa cassa)
	{
		m_cassa = cassa;
		m_gestoreCassa = gestoreCassa;
	}
	
	public void ammissibile(Boolean b) {
		// TODO Auto-generated method stub

	}

	public void annulla() {
		// TODO Auto-generated method stub

	}

	public void fallito() {
		// TODO Auto-generated method stub

	}

	public void fatto() {
		// TODO Auto-generated method stub

	}

	public void finito() {
		// TODO Auto-generated method stub

	}

	public void ko() {
		// TODO Auto-generated method stub

	}

	public void ok() {
		// TODO Auto-generated method stub

	}

	public void aggiornaVociBilancio(VociBilancio voci) {
		// TODO Auto-generated method stub
		
	}

	public void aggiornaProspetto(DatiMovimentoCassa movimento) {
		// TODO Auto-generated method stub
		
	}

}
