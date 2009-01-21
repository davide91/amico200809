/**
 * 
 */
package test.testStore;

import org.hibernate.Session;

import store.TuttePersone;
import store.TuttiCondomini;
import store.POJO.Condominio;
import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.UnitaImmobiliare;
import store.util.HibernateUtil;
import datatype.CodiceFiscale;
import datatype.DatiCondominio;
import datatype.DatiPersonaFisica;
import datatype.DatiUnitaImmobiliare;
import datatype.Email;
import datatype.Euro;
import datatype.Indirizzo;
import datatype.Preferenze;
import datatype.list.Condomini;
import datatype.list.Persone;
import datatype.list.PersoneFisiche;
import datatype.list.Reali;
import datatype.list.UnitaImmobiliari;
import enumeration.CategoriaCatastale;
import enumeration.Comune;
import enumeration.DestinazioneUso;
import enumeration.Provincia;
import enumeration.StatoCondominio;
import junit.framework.TestCase;

/**
 * @author bruno
 *
 */
public class TestSet03_TuttiCondomini extends TestCase {

	private TuttiCondomini tc;
	private TuttePersone tp;
	
	public TestSet03_TuttiCondomini(String name)
	{
		super(name);
	}
	
	public void testCONDOMINI_Inserimento()
	{
		tc = new TuttiCondomini();
		
		assertEquals(0, tc.recuperaCondomini().getCondomini().size());
		
		Indirizzo ind = new Indirizzo("Papagliano","34",Comune.ALMESE,Provincia.Alessandria,"15100");
		
		Condominio c = new Condominio();
		this.tc.inserisciCondominio(c);
		c.modificaDati(new DatiCondominio("Papigliano1",ind));
		c.modificaPreferenze(new Preferenze((float)3.4,10,new Euro((float)150.0)));
		
		assertEquals(1, tc.recuperaCondomini().getCondomini().size());
	}
	
	
	public void testCONDOMINI_recupero()
	{		
		tc = new TuttiCondomini();
	
		assertEquals(1, tc.recuperaCondomini().getCondomini().size());
		
		Indirizzo ind = new Indirizzo("Papagliano","34",Comune.ALMESE,Provincia.Alessandria,"15100");
		
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		
		assertTrue(cond.getDatiC().equals(new DatiCondominio("Papigliano1",ind)));
		assertTrue(cond.getPreferenze().equals(new Preferenze((float)3.4,10,new Euro((float)150.0))));
		assertTrue(cond.getStatoCondominio().equals(StatoCondominio.inCompilazione));
	}
	

	public void testCONDOMINI_modificaPreferenze()
	{
		tc = new TuttiCondomini();
			
		Condominio c = tc.recuperaCondomini().getCondomini().get(0); // recupero il condominio
		c.modificaPreferenze(new Preferenze((float)4.3,100,new Euro((float)510.0)));

		assertTrue(tc.recuperaCondomini().getCondomini().get(0).getPreferenze().equals(new Preferenze((float)4.3,100,new Euro((float)510.0))));
	}
	
	public void testCONDOMINI_inserireUnitaImmobiliare()
	{
		tc = new TuttiCondomini();
		
		tp = new TuttePersone();
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);

		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 1",CategoriaCatastale.A10,"interna", (float)85,DestinazioneUso.appartamento);
		
		UnitaImmobiliare ui = new UnitaImmobiliare(dui);
		cond.inserisciUnitaImmobiliare(ui);
	}

	public void testCONDOMINI_recuperaUnitaImmobiliare()
	{		
		tc = new TuttiCondomini();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		UnitaImmobiliare unitaRecuperata = c.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		//dati inseriti nell'unità precedente
		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 1",CategoriaCatastale.A10,"interna", (float)85,DestinazioneUso.appartamento);	
		//controllo che i dati inseriti siano uguali
		assertTrue(unitaRecuperata.getDatiUnitaImmobiliare().equals(dui));
	}
	
	public void testCONDOMINI_modificaUnitaImmobiliare()
	{
		tc = new TuttiCondomini();
		
		//recupero il condominio
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		//recupero l'unità immobiliare
		UnitaImmobiliare unita = c.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		//creo dei nuovi dati
		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 14",CategoriaCatastale.A10,"palconata", (float)85,DestinazioneUso.appartamento);

		//modifico i dati dell'unità precedente
		unita.modificaDati(dui);
		
		assertTrue(unita.getDatiUnitaImmobiliare().equals(dui));
	}
	
	public void testCONDOMINI_InserisciPersona()
	{
		tc = new TuttiCondomini();
		
		tp = new TuttePersone();
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		
		//creo la persona e la inserisco
		Indirizzo ind = new Indirizzo("adua","3",Comune.AGLIE,Provincia.Alessandria,"15060");
		DatiPersonaFisica dpf = new DatiPersonaFisica(new CodiceFiscale("codFisc"),"bruno","mazzarello","328-4724731",ind,"0143-50187",new Email("mazzibruno@libero.it"),"ff");
		tp.inserisciPersona(dpf);
		
		Indirizzo ind1 = new Indirizzo("adua","3",Comune.AGLIE,Provincia.Alessandria,"15060");
		DatiPersonaFisica dpf1 = new DatiPersonaFisica(new CodiceFiscale("codFisc1"),"Elena","Bianchi","328-4724731",ind1,"0143-50187",new Email("mazzibruno@libero.it"),"ff");
		tp.inserisciPersona(dpf1);
		
		PersonaFisica p = (PersonaFisica)tp.recuperaPersone(ind).recuperaPersone().get(0);
		PersonaFisica p1 = (PersonaFisica)tp.recuperaPersone(ind).recuperaPersone().get(1);
		
		assertTrue(p.getDati().equals(dpf));
		assertTrue(p1.getDati().equals(dpf1));
		cond.inserisciPersona(p);
		cond.inserisciPersona(p1);
		
		//controllo che nel condominio ci siano effettivamente due persone
		assertEquals(2, tc.recuperaCondomini().getCondomini().get(0).getPersone().size());
	}

	public void testCONDOMINI_InserireProprietàUnitàImmobiliare()
	{
		tc = new TuttiCondomini();
		
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		//recuper l'unità Immobiliare
		UnitaImmobiliare unitaRecuperata = cond.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		//recupero le persone dal condominio
		Persone pers = cond.recuperaCondomini();
		
		Reali quote = new Reali();
		
		if(pers.getPersone().size()==2)
		{
			quote.inserisciReale((float)0.5);
			quote.inserisciReale((float)0.5);
		}
		unitaRecuperata.modificaProprieta(pers, quote);
	}
	
	public void testCONDOMINI_ModificaProprietàUnitàImmobiliare()
	{
		tc = new TuttiCondomini();
		
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		//recuper l'unità Immobiliare
		UnitaImmobiliare unitaRecuperata = cond.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		//recupero le persone dal condominio
		Persona pe = cond.recuperaCondomini().recuperaPersone().get(1);
		
		Persone pers = new Persone();
		pers.inserisciPersona(pe);
		
		Reali quote = new Reali();
			quote.inserisciReale((float)1.0);
	
		unitaRecuperata.modificaProprieta(pers, quote);
	}
	
	public void testCONDOMINI_EliminaPersona()
	{
		tc = new TuttiCondomini();
		
		tp = new TuttePersone();
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		
		//recupero la persona e la elimino		
		PersonaFisica p = (PersonaFisica)(cond.recuperaCondomini().recuperaPersone().get(0));
		
		cond.rimuoviPersona(p);
		
		assertEquals(1, tc.recuperaCondomini().getCondomini().size());
	}

	public void testCONDOMINI_eliminaUnitaImmobiliare()
	{
		tc = new TuttiCondomini();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		
		//prima c'è una unità immobiliare
		assertEquals(1, c.recuperaUnitaImmobiliari().getImmobili().size());
	
		//recupero l'unità immobiliare
		UnitaImmobiliare unita = c.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		c.eliminaUnitaImmobiliare(unita);
		//dopo non c'è nessuna unità immobiliare
		assertEquals(0, c.recuperaUnitaImmobiliari().getImmobili().size());
	}
	
	
	public void testCONDOMINI_eliminaCondominio()
	{
		tc = new TuttiCondomini();
		
		assertEquals(1, tc.recuperaCondomini().getCondomini().size());
		
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		
		tc.eliminaCondominio(cond);
		assertEquals(0, tc.recuperaCondomini().getCondomini().size());
	}

	
}
