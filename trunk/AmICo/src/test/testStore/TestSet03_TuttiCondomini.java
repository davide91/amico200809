/**
 * 
 */
package test.testStore;

import store.TuttiCondomini;
import store.POJO.Condominio;
import datatype.list.Condomini;
import junit.framework.TestCase;

/**
 * @author bruno
 *
 */
public class TestSet03_TuttiCondomini extends TestCase {

	public TestSet03_TuttiCondomini(String name)
	{
		super(name);
	}
	
	public void testCONDOMINI()
	{
		TuttiCondomini tc = new TuttiCondomini();
		tc.inizializza();
		
		assertEquals(0, tc.CONDOMINI.getCondomini().size());
		
		Condominio c = new Condominio();
		tc.inserisciCondominio(c);
		assertEquals(1, tc.CONDOMINI.getCondomini().size());
		
	}
}
