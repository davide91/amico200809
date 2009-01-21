/**
 * 
 */
package test.testStore;

import com.sun.java_cup.internal.parse_action;

import datatype.CodiceFiscale;
import datatype.DatiPersonaFisica;
import datatype.DatiPersonaGiuridica;
import datatype.Email;
import datatype.Indirizzo;
import datatype.PartitaIva;
import datatype.list.Persone;
import enumeration.Comune;
import enumeration.Provincia;
import store.TuttePersone;
import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import junit.framework.TestCase;

/**
 * @author bruno
 *
 */
public class TestSet02_TuttePersone extends TestCase {

	private TuttePersone tp;
	
	public TestSet02_TuttePersone(String name)
	{
		super(name);
	}
	
	public void testPERSONE_InserimentoPersonaFisica()
	{
		tp = new TuttePersone();
		Persone persone = tp.recuperaPersone();
		
		//inizialmente il DB è vuoto
		assertEquals(0, persone.recuperaPersone().size());
		
		//cinserisco una persona
		DatiPersonaFisica dpf = new DatiPersonaFisica(new CodiceFiscale("codFisc"),"bruno","mazzarello","328-4724731",(new Indirizzo("adua","3",Comune.AGLIE,Provincia.Alessandria,"15060")),"0143-50187",new Email("mazzibruno@libero.it"),"ff");
		tp.inserisciPersona(dpf);
		assertEquals(1, persone.recuperaPersone().size());
	}
	
	public void testPERSONE_RecuperaPersonaFisica()
	{
		tp = new TuttePersone();
		
		//inizialmente il DB contiene una persona
		assertEquals(1, tp.recuperaPersone().recuperaPersone().size());
		
		//Dati con i quali confronteremo la persona recuperata
		DatiPersonaFisica dpf = new DatiPersonaFisica(new CodiceFiscale("codFisc"),"bruno","mazzarello","328-4724731",(new Indirizzo("adua","3",Comune.AGLIE,Provincia.Alessandria,"15060")),"0143-50187",new Email("mazzibruno@libero.it"),"ff");
		
		//recuperiamo la persona
		Persona p = tp.recuperaPersone().recuperaPersone().get(0);
		
		//controlliamo che sia l'istanza voluta
		assertTrue(p instanceof PersonaFisica);
		//cast a persona Fisica
		PersonaFisica per = (PersonaFisica)p;
		//controllo equal sui dati precedentemente inseriti
		assertTrue(per.getDati().equals(dpf));
	}
	
	public void testPERSONE_modifica()
	{
		tp = new TuttePersone();
		
		Persone persone = tp.recuperaPersone();
		
		//inizialmente il DB contiene una persona
		assertEquals(1, persone.recuperaPersone().size());
		
		//Dati con i quali modificherò la persona recuperata
		DatiPersonaFisica dpf = new DatiPersonaFisica(new CodiceFiscale("codFisc_Mod"),"bruno_Mod","mazzarello_Mod","328-4724731_Mod",(new Indirizzo("adua_Mod","3",Comune.ALMESE,Provincia.Alessandria,"15060_Mod")),"0143-50187",new Email("mazzibruno@libero.it"),"ff");
		
		//recuperiamo la persona
		Persona p = persone.recuperaPersone().get(0);
		
		p.modificaDati(dpf);
		
		assertTrue(((PersonaFisica)tp.recuperaPersone().recuperaPersone().get(0)).getDati().equals(dpf));
	}

	public void testPERSONE_InserimentoPersonaGiuridica()
	{
		tp = new TuttePersone();
		
		Persone persone = tp.recuperaPersone();
		
		//inizialmente il DB ha una persona inserita prima
		assertEquals(1, persone.recuperaPersone().size());
		
		//ci inserisco una persona giuridica
		DatiPersonaGiuridica dpg = new DatiPersonaGiuridica(new PartitaIva("34676253809"),"HoRagionaIO",(new Indirizzo("adua","3",Comune.AGLIE,Provincia.Alessandria,"15060")),"432-5647322",new Email("ciccio@demente.it"),"0287-09825");
		tp.inserisciPersona(dpg);
		
		//deve essere due perchè una era inserita prima
		assertEquals(2, persone.recuperaPersone().size());
	}
	
	public void testPERSONE_RecuperaPersonaGiuridica()
	{
		tp = new TuttePersone();
		
		Persone persone = tp.recuperaPersone();
		
		//inizialmente il DB contiene una persona
		assertEquals(2, persone.recuperaPersone().size());
		
		//Dati con i quali confronteremo la persona recuperata
		DatiPersonaGiuridica dpg = new DatiPersonaGiuridica(new PartitaIva("34676253809"),"HoRagionaIO",(new Indirizzo("adua","3",Comune.AGLIE,Provincia.Alessandria,"15060")),"432-5647322",new Email("ciccio@demente.it"),"0287-09825");
			
		//recuperiamo la persona
		Persona p = persone.recuperaPersone().get(1);
		
		//controlliamo che sia l'istanza voluta		
		assertTrue(p instanceof PersonaGiuridica);
		//cast a persona Fisica
		PersonaGiuridica per = (PersonaGiuridica)p;
		//controllo equal sui dati precedentemente inseriti
		assertTrue(per.getDati().equals(dpg));
	}
	
	public void testPERSONE_PersonaGiuridica_AssegnaPersonaDiRiferimento()
	{
		tp = new TuttePersone();
		
		Persone persone = tp.recuperaPersone();
		
		//inizialmente il DB contiene una persona
		assertEquals(2, persone.recuperaPersone().size());
		
		//Dati con i quali confronteremo la persona recuperata
		DatiPersonaGiuridica dpg = new DatiPersonaGiuridica(new PartitaIva("34676253809"),"HoRagionaIO",(new Indirizzo("adua","3",Comune.AGLIE,Provincia.Alessandria,"15060")),"432-5647322",new Email("ciccio@demente.it"),"0287-09825");
			
		//recuperiamo la persona Fisica
		Persona pf = persone.recuperaPersone().get(0);
		//recuperiamo la persona Giuridica
		Persona pg = persone.recuperaPersone().get(1);
		
		//controlliamo che siano le istanze volute
		if (pf instanceof PersonaFisica && pg instanceof PersonaGiuridica) {
			PersonaGiuridica perG = (PersonaGiuridica) pg;
			PersonaFisica perF = (PersonaFisica) pf;
			
			perG.assegnaPersonaDiRiferimento(perF);
		
			assertTrue(perG.getPersonaDiRiferimento().equals(perF));
		}
	}
	
		public void testPERSONE_EliminazionePersonaFisica()
	{
		tp = new TuttePersone();
		Persone persone = tp.recuperaPersone();
		
		//inizialmente il DB ha una persona
		assertEquals(2, persone.recuperaPersone().size());
		
		//recuperiamo la persona
		Persona p = persone.recuperaPersone().get(0);
		
		tp.eliminaPersona(p);
		
		assertEquals(1, persone.recuperaPersone().size());
	}

	public void testPERSONE_EliminazionePersonaGiuridica()
	{
		tp = new TuttePersone();

		Persone persone = tp.recuperaPersone();
		
		//inizialmente il DB è vuoto
		assertEquals(1, persone.recuperaPersone().size());
		
		//recuperiamo la persona
		Persona p = persone.recuperaPersone().get(0);
		tp.eliminaPersona(p);
		
		assertEquals(0, persone.recuperaPersone().size());
	}
}
