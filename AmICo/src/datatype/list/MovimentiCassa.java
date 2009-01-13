/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;
import store.POJO.MovimentoCassa;

/**
 * @author bruno
 *
 */
public class MovimentiCassa {

	private List<MovimentoCassa> movimenti = new ArrayList<MovimentoCassa>();
	
	public MovimentiCassa()
	{
		
	}
	
	public void inserisciMovimentoCassa(MovimentoCassa mc)
	{
		movimenti.add(mc);
	}

	public List<MovimentoCassa> getMovimenti() {
		return movimenti;
	}

	public void setMovimenti(List<MovimentoCassa> movimenti) {
		this.movimenti = movimenti;
	}	
}