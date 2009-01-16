/**
 * 
 */
package executor;

import boundary.DriverFileSystem;
import store.TuttePersone;
import store.TuttiCondomini;
import datatype.list.Condomini;
import datatype.list.Persone;

/**
 * @author thewally
 *
 */
public class Avvio  {
	private static Avvio m_singleAvvio;
	
	public static Avvio getInstance() {
		if ( m_singleAvvio == null )
			m_singleAvvio = new Avvio();
		return m_singleAvvio;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Avvio avvioAmiCO = Avvio.getInstance();
	}
	private Condomini m_condomini;
	private GestoreCondomini m_gestoreCondomini;

	private GestorePersone m_gestorePersone;
	private DriverFileSystem m_driverDriverFileSystem;

	private Persone m_persone;	

	private Avvio() {
		inizializzaAmICo();
	}

	public void esciDaAmICo() {

	}
	
	private void inizializzaAmICo() {
		m_condomini = TuttiCondomini.CONDOMINI;
		m_persone = TuttePersone.PERSONE;
		
		m_gestoreCondomini = GestoreCondomini.getInstance();
		m_gestoreCondomini.impostaAvvio(this);
		m_gestorePersone = GestorePersone.getInstance();
		m_driverDriverFileSystem = new DriverFileSystem();

	}
	
}
