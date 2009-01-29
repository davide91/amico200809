/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class BilancioStatoAllerta extends Avviso{

	private Euro differenza = new Euro();
	
	public BilancioStatoAllerta(Euro e)
	{
		differenza = e;
	}

	public Euro getDifferenza() {
		return differenza;
	}

	public void setDifferenza(Euro differenza) {
		this.differenza = differenza;
	}
}
