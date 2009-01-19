/**
 * 
 */
package boundary;


import datatype.list.Persone;
import datatype.list.Condomini;
import executor.GestoreCondominioAperto;
import store.POJO.Persona;

/**
 * @author Federico
 *
 */
public class AccedereCondomini implements BaseBoundary
{
	
	private Persone persone;

	AccedereCondomini(GestoreCondominioAperto GCA, Persone P) {
		
	}
	
	public void modificaCondomino(Persona P) // non puo' essere condomino al max persona ma vabbe
	{}
	
	public void aggiornaCondomini(Condomini C)
	{}

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

}
