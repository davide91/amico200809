/**
 * 
 */
package test.TestDatatype;

import datatype.Euro;
import junit.framework.TestCase;

/**
 * @author bruno
 *
 */
public class TestSet01_Datatype_Euro extends TestCase {

	public TestSet01_Datatype_Euro(String name)
	{
		super(name);
	}


	public void testCreazione()
	{
		Euro e = new Euro();
		assertEquals(0, e.getEuro());
	}
	
	public void testGetCent()
	{
		Euro e = new Euro(4.58);
		assertEquals(58, e.getCent());
	}
	
	public void testGetEuro()
	{
		Euro e = new Euro(55.0);
		assertEquals(55, e.getEuroIntero());
	}
	
	public void testSomma()
	{
		Euro e = new Euro(55.0);
		Euro e1 = new Euro(60.50);
		
		Euro result = e.somma(e1);
		
		assertEquals(115, result.getEuroIntero());
		assertEquals(50, result.getCent());
	}
	
	public void testSottrai()
	{
		Euro e = new Euro(55.0);
		Euro e1 = new Euro(60.50);
		
		Euro result = e1.sottrai(e);
		
		assertEquals(5, result.getEuroIntero());
		assertEquals(50, result.getCent());
	}
	
	public void testMoltiplica(int a)
	{
		Euro e = new Euro(10.0);
		Euro result = e.moltiplica(10);
		assertEquals(100, result.getEuroIntero());
	}
	
	public void testMinoreDi()
	{
		Euro e = new Euro(55.0);
		Euro e1 = new Euro(60.50);
		
		assertTrue(e.minoreDi(e1));
	}
	
	public void testMaggioreDi()
	{
		Euro e = new Euro(55.0);
		Euro e1 = new Euro(60.50);
		
		assertTrue(e1.maggioreDi(e));
	}
	
	public void testUgualeA()
	{
		Euro e = new Euro(60.50);
		Euro e1 = new Euro(60.50);
		
		assertTrue(e1.ugualeA(e));
	}
}
