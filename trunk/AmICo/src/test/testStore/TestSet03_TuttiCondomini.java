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
import datatype.list.PersoneFisiche;
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
		tc.inizializza();
		
		assertEquals(0, tc.CONDOMINI.getCondomini().size());
		
		Indirizzo ind = new Indirizzo("Papagliano","34",Comune.ALMESE,Provincia.Alessandria,"15100");
		
		Condominio c = new Condominio();
		this.tc.inserisciCondominio(c);
		c.modificaDati(new DatiCondominio("Papigliano1",ind));
		c.modificaPreferenze(new Preferenze((float)3.4,10,new Euro((float)150.0)));
		
		
		assertEquals(1, TuttiCondomini.CONDOMINI.getCondomini().size());
	}
	
	
	public void testCONDOMINI_recupero()
	{		
		assertEquals(1, TuttiCondomini.CONDOMINI.getCondomini().size());
		
		Indirizzo ind = new Indirizzo("Papagliano","34",Comune.ALMESE,Provincia.Alessandria,"15100");
		
		//Condominio c = new Condominio();
		//c.modificaDati(new DatiCondominio("Papigliano1",ind));
		//c.modificaPreferenze(new Preferenze((float)3.4,10,new Euro((float)150.0)));
		//c.setStatoCondominio(StatoCondominio.inCompilazione);
		
		Condominio cond = tc.CONDOMINI.getCondomini().get(0);
		
		assertTrue(cond.getDatiC().equals(new DatiCondominio("Papigliano1",ind)));
		assertTrue(cond.getPreferenze().equals(new Preferenze((float)3.4,10,new Euro((float)150.0))));
		assertTrue(cond.getStatoCondominio().equals(StatoCondominio.inCompilazione));
	}
	
	public void testCONDOMINI_inserireUnitaImmobiliare()
	{
		tc = new TuttiCondomini();
		tc.inizializza();
		
		Indirizzo ind = new Indirizzo("Papagliano","34",Comune.ALMESE,Provincia.Alessandria,"15100");
		
		Condominio c = new Condominio();
		tc.inserisciCondominio(c);
		c.modificaDati(new DatiCondominio("Papigliano2",ind));
		c.modificaPreferenze(new Preferenze((float)3.4,10,new Euro((float)150.0)));

		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 1",CategoriaCatastale.A10,"interna", (float)85,DestinazioneUso.appartamento);
		
		UnitaImmobiliare ui = new UnitaImmobiliare(dui);
		c.inserisciUnitaImmobiliare(ui);
	}

	public void testCONDOMINI_modificaPreferenzeUnitàImmobiliare()
	{
		Condominio cond = tc.CONDOMINI.getCondomini().get(0); // recupero il condominio
		
		//creo i dati per eseguire la modifica
		TuttiCondomini ttc = new TuttiCondomini();
		ttc.inizializza();
		
		Condominio c = ttc.CONDOMINI.getCondomini().get(0); // recupero il condominio
		c.modificaPreferenze(new Preferenze((float)4.3,100,new Euro((float)510.0)));

		assertTrue(ttc.CONDOMINI.getCondomini().get(0).getPreferenze().equals(new Preferenze((float)4.3,100,new Euro((float)510.0))));
	}

	public void testCONDOMINI_recuperaUnitaImmobiliare()
	{		
		tc = new TuttiCondomini();
		tc.inizializza();
		
		Condominio c = TuttiCondomini.CONDOMINI.getCondomini().get(0);
		UnitaImmobiliare unitaRecuperata = c.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		//dati inseriti nell'unità precedente
		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 1",CategoriaCatastale.A10,"interna", (float)85,DestinazioneUso.appartamento);	
		//controllo che i dati inseriti siano uguali
		assertTrue(unitaRecuperata.getDatiUnitaImmobiliare().equals(dui));
	}
	
	public void testCONDOMINI_modificaUnitaImmobiliare()
	{
		tc = new TuttiCondomini();
		tc.inizializza();
		
		Condominio c = TuttiCondomini.CONDOMINI.getCondomini().get(0);
		//recupero l'unità immobiliare
		UnitaImmobiliare unita = c.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		//creo dei nuovi dati
		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 14",CategoriaCatastale.A10,"palconata", (float)85,DestinazioneUso.appartamento);

		//modifico i dati dell'unità precedente
		unita.modificaDati(dui);
		
		assertTrue(unita.getDatiUnitaImmobiliare().equals(dui));
	}
	
	public void testCONDOMINI_eliminaUnitaImmobiliare()
	{
		tc = new TuttiCondomini();
		tc.inizializza();
		Condominio c = tc.CONDOMINI.getCondomini().get(0);
		
		//prima c'è una unità immobiliare
		assertEquals(1, c.recuperaUnitaImmobiliari().getImmobili().size());
	
		//recupero l'unità immobiliare
		UnitaImmobiliare unita = c.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		c.eliminaUnitaImmobiliare(unita);
		//dopo non c'è nessuna unità immobiliare
		assertEquals(0, c.recuperaUnitaImmobiliari().getImmobili().size());
	}
	
	public void testCONDOMINI_InserisciPersona()
	{
		tc = new TuttiCondomini();
		tc.inizializza();
		
		tp = new TuttePersone();
		tp.inizializza();
		
		//recupero il condominio
		Condominio cond = TuttiCondomini.CONDOMINI.getCondomini().get(0);
		
		//creo la persona e la inserisco
		Indirizzo ind = new Indirizzo("adua","3",Comune.AGLIE,Provincia.Alessandria,"15060");
		DatiPersonaFisica dpf = new DatiPersonaFisica(new CodiceFiscale("codFisc"),"bruno","mazzarello","328-4724731",ind,"0143-50187",new Email("mazzibruno@libero.it"),"ff");
		tp.inserisciPersona(dpf);
		
		PersonaFisica p = (PersonaFisica)tp.recuperaPersone(ind).recuperaPersone().get(0);
		
		assertTrue(p.getDati().equals(dpf));
		cond.inserisciPersona(p);
		
		assertEquals(2, tc.CONDOMINI.getCondomini().size());
	}

/*	public void testCONDOMINI_InserireProprietàUnitàImmobiliare()
	{
		
	}
*/	
	
	
	
	
	
	
	
	public void testCONDOMINI_eliminaCondominio()
	{
		tc = new TuttiCondomini();
		tc.inizializza();
		
		assertEquals(2, TuttiCondomini.CONDOMINI.getCondomini().size());
		
		Condominio cond = TuttiCondomini.CONDOMINI.getCondomini().get(1);
		
		tc.eliminaCondominio(cond);
		assertEquals(1, tc.CONDOMINI.getCondomini().size());
	}
	
}
