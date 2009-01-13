/**
 * 
 */
package executor;

import datatype.list.Condomini;
import datatype.list.Persone;

/**
 * @author thewally
 *
 */
public class Avvio  {
	private static Avvio m_singleAvvio;
	
	private Condomini m_condomini;
	private Persone m_persone;
	// private GestoreCondomini m_gestoreCondomini;
	// private GestorePersone m_gestorePersone;
	// private DriverFileSystem m_driverFileSystem;
	
	private static void beenStarted() {
		/*
		 * TODO: AmICo già in esecuzione, mostra messaggio ed esci.
		 */
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (m_singleAvvio != null)
			beenStarted();
		else
			m_singleAvvio = new Avvio();
	}

	/*
	 * Inizializza Amico 
	 */
	
	public Avvio() {
		inizializzaAmICo();
	}

	public void esciDaAmICo() {
		/*
		 * TODO:
		 * destroy(GP); 
		 * destroy(DFS);
		 */
	}
	
	private void inizializzaAmICo() {
		/*
		 * TODO:
		 * CONDOMINI = recuperaCondomini(); 
		 * PERSONE = recuperaPersone(); 
		 * GC = creaGestoreCondomini(); 
		 * GP = creaGestorePersone(); 
		 * DFS = creaDFS();
		 */
	}
	
}
