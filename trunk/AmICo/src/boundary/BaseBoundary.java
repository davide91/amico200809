/**
 * 
 */
package boundary;

/**
 * @author peregrino
 *
 */
public interface BaseBoundary {
	public void ko();
	public void ok();
	public void finito();
	public void annulla();
	public void ammissibile(Boolean b);
	public void fatto();
	public void fallito();

}
