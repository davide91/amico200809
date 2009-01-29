/**
 * 
 */
package datatype;

/**
 * @author bruno
 *
 */
public class CassaSottoSogliaMinima extends Avviso{

	private Euro attuale = new Euro();
	private Euro soglia = new Euro();
	private String nomeCassa = new String();
	
	public CassaSottoSogliaMinima(String nomeCassa, Euro attuale, Euro soglia)
	{
		this.attuale = attuale;
		this.soglia = soglia;
		this.nomeCassa = nomeCassa;
	}

	public Euro getAttuale() {
		return attuale;
	}

	public void setAttuale(Euro valore) {
		this.attuale = valore;
	}
	
	public Euro getSoglia() {
		return soglia;
	}
	
	public void setSoglia(Euro valore) {
		soglia = valore;
	}
	
}
