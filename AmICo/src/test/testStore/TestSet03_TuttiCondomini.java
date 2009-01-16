/**
 * 
 */
package test.testStore;

import org.hibernate.Session;

import store.TuttiCondomini;
import store.POJO.Condominio;
import store.POJO.UnitaImmobiliare;
import store.util.HibernateUtil;
import datatype.DatiCondominio;
import datatype.DatiUnitaImmobiliare;
import datatype.Euro;
import datatype.Indirizzo;
import datatype.Preferenze;
import datatype.list.Condomini;
import datatype.list.UnitàImmobiliari;
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

	public TestSet03_TuttiCondomini(String name)
	{
		super(name);
	}
	
	public void testCONDOMINI_Inserimento()
	{
		TuttiCondomini tc = new TuttiCondomini();
		tc.inizializza();
		
		assertEquals(0, tc.CONDOMINI.getCondomini().size());
		
		Indirizzo ind = new Indirizzo("Papagliano","34",Comune.ALMESE,Provincia.Alessandria,"15100");
		
		Condominio c = new Condominio();
		c.modificaDati(new DatiCondominio("Papigliano1",ind));
		c.modificaPreferenze(new Preferenze((float)3.4,10,new Euro((float)150.0)));
		c.setStatoCondominio(StatoCondominio.inserito);
		tc.inserisciCondominio(c);
		assertEquals(1, tc.CONDOMINI.getCondomini().size());
	}
	
	
	public void testCONDOMINI_recupero()
	{
		TuttiCondomini tc = new TuttiCondomini();
		tc.inizializza();
		
		assertEquals(1, tc.CONDOMINI.getCondomini().size());
		
		Indirizzo ind = new Indirizzo("Papagliano","34",Comune.ALMESE,Provincia.Alessandria,"15100");
		
		Condominio c = new Condominio();
		c.modificaDati(new DatiCondominio("Papigliano1",ind));
		c.modificaPreferenze(new Preferenze((float)3.4,10,new Euro((float)150.0)));
		c.setStatoCondominio(StatoCondominio.inserito);
		
		Condominio cond = tc.CONDOMINI.getCondomini().get(0);
		
		assertTrue(cond.equals(c));
	}
	
	public void testCONDOMINI_modifica()
	{
		TuttiCondomini tc = new TuttiCondomini();
		tc.inizializza();
		assertEquals(1, tc.CONDOMINI.getCondomini().size());
		
		Condominio cond = tc.CONDOMINI.getCondomini().get(0); // recupero il condominio
		
		cond.setStatoCondominio(StatoCondominio.inserito);
		
		TuttiCondomini ttc = new TuttiCondomini();
		ttc.inizializza();
		assertEquals(1, ttc.CONDOMINI.getCondomini().size());
		
		Condominio c = ttc.CONDOMINI.getCondomini().get(0); // recupero il condominio
		assertTrue(cond.getStatoCondominio().equals(c.getStatoCondominio()));
	}
	
	public void testCONDOMINI_inserireUnitaImmobiliare()
	{
		TuttiCondomini tc = new TuttiCondomini();
		tc.inizializza();
				
		Indirizzo ind = new Indirizzo("Papagliano","34",Comune.ALMESE,Provincia.Alessandria,"15100");
		
		Condominio c = new Condominio();
		c.modificaDati(new DatiCondominio("Papigliano2",ind));
		c.modificaPreferenze(new Preferenze((float)3.4,10,new Euro((float)150.0)));
		
		tc.inserisciCondominio(c);
		
		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 1",CategoriaCatastale.A10,"interna", (float)85,DestinazioneUso.appartamento);
		
		UnitaImmobiliare ui = new UnitaImmobiliare();
		ui.modificaDati(dui);		
		
		c.inserisciUnitàImmobiliare(ui);
	}
	

	public void testCONDOMINI_recuperaUnitaImmobiliare()
	{
		TuttiCondomini tc = new TuttiCondomini();
		tc.inizializza();
				
	/*	Indirizzo ind = new Indirizzo("Papagliano","34",Comune.ALMESE,Provincia.Alessandria,"15100");
		
		Condominio c = new Condominio();
		c.modificaDati(new DatiCondominio("Papigliano2",ind));
		c.modificaPreferenze(new Preferenze((float)3.4,10,new Euro((float)150.0)));
		
		tc.inserisciCondominio(c);
	*/
		Condominio c = tc.CONDOMINI.getCondomini().get(1);
		
		UnitàImmobiliari uimm = c.recuperaUnitàImmobiliari();
		
		UnitaImmobiliare unitaRecuperata = uimm.getImmobili().get(0);
		
		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 1",CategoriaCatastale.A10,"interna", (float)85,DestinazioneUso.appartamento);
		UnitaImmobiliare ui = new UnitaImmobiliare();
		ui.modificaDati(dui);		
		
		assertTrue(ui.equals(unitaRecuperata));
	}
	
/*	public void testCONDOMINI_elimina()
	{
		TuttiCondomini tc = new TuttiCondomini();
		tc.inizializza();
		
		assertEquals(1, tc.CONDOMINI.getCondomini().size());
		
		Condominio cond = tc.CONDOMINI.getCondomini().get(0);
		
		tc.eliminaCondominio(cond);
		assertEquals(0, tc.CONDOMINI.getCondomini().size());
	}
	*/
}
