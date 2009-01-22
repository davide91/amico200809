/**
 * 
 */
package executor;

import store.TuttePersone;
import store.TuttiCondomini;
import boundary.AmICo;
import boundary.DriverFileSystem;
import executor.GestorePersone;

/**
 * @author thewally
 *
 */
public class Avvio  {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Avvio.inizializzaAmICo();
	}
	

	public static void esciDaAmICo() {

	}
	
	public static void inizializzaAmICo() {
		GestoreCondomini.getInstance();
		GestorePersone.getInstance();
		DriverFileSystem.getInstance();
		
		/* TODO : penso ci sia da fare un singleton
		 * in tutte e due le classi
		 */
		
		TuttiCondomini.CONDOMINI;
		TuttePersone.PERSONE;
		
	}
	
}
