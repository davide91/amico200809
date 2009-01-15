/**
 * 
 */
package test.testStore;

import datatype.CodiceFiscale;
import datatype.DatiPersonaFisica;
import datatype.Email;
import datatype.Indirizzo;
import datatype.list.Persone;
import enumeration.Comune;
import enumeration.Provincia;
import store.TuttePersone;
import junit.framework.TestCase;

/**
 * @author bruno
 *
 */
public class TestSet02_TuttePersone extends TestCase {

	public TestSet02_TuttePersone(String name)
	{
		super(name);
	}
	
	public void testPERSONE()
	{
		TuttePersone tp = new TuttePersone();
		tp.inizializza();
		Persone persone = TuttePersone.PERSONE;
		
		//inizialmente il DB è vuoto
		assertEquals(0, persone.recuperaPersone().size());
		
		//cinserisco una persona
		DatiPersonaFisica dpf = new DatiPersonaFisica(new CodiceFiscale("codFisc"),"bruno","mazzarello","328-4724731",(new Indirizzo("adua","3",Comune.AGLIE,Provincia.Alessandria,"15060")),"0143-50187",new Email("mazzibruno@libero.it"),"ff");
		tp.inserisciPersona(dpf);
	//	assertEquals(1, persone.recuperaPersone().size());
	}
}
