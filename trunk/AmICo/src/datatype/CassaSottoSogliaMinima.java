/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class CassaSottoSogliaMinima extends Avviso{

	private Euro valore = new Euro();
	
	public CassaSottoSogliaMinima(Euro e)
	{
		valore = e;
	}

	public Euro getValore() {
		return valore;
	}

	public void setValore(Euro valore) {
		this.valore = valore;
	}
}
