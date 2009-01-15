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
	private GestorePersone m_gestorePersone;
	// private DriverFileSystem m_driverFileSystem;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Avvio avvioAmiCO = Avvio.getInstance();
	}

	public static Avvio getInstance() {
		if ( m_singleAvvio == null )
			m_singleAvvio = new Avvio();
		return m_singleAvvio;
	}
	
	/*
	 * Inizializza Amico 
	 */
	
	private Avvio() {
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
