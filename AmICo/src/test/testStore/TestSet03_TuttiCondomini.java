/**
 * 
 */
package test.testStore;

import java.sql.Date;

import org.hibernate.Session;

import store.TuttePersone;
import store.TuttiCondomini;
import store.POJO.Bilancio;
import store.POJO.Cassa;
import store.POJO.Condominio;
import store.POJO.Millesimo;
import store.POJO.MovimentoCassa;
import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;
import store.POJO.VoceBilancio;
import store.util.HibernateUtil;
import datatype.CodiceFiscale;
import datatype.Data;
import datatype.DatiBilancio;
import datatype.DatiCondominio;
import datatype.DatiMovimentoCassa;
import datatype.DatiPersonaFisica;
import datatype.DatiTabellaMillesimale;
import datatype.DatiUnitaImmobiliare;
import datatype.DatiVoceBilancio;
import datatype.Email;
import datatype.Euro;
import datatype.Indirizzo;
import datatype.Preferenze;
import datatype.list.Condomini;
import datatype.list.Millesimi;
import datatype.list.Persone;
import datatype.list.PersoneFisiche;
import datatype.list.Percentuali;
import datatype.list.UnitaImmobiliari;
import enumeration.CategoriaCatastale;
import enumeration.DestinazioneUso;
import enumeration.Provincia;
import enumeration.StatoBilancio;
import enumeration.StatoCondominio;
import enumeration.TipoBilancio;
import enumeration.TipoVoce;
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
		tc = TuttiCondomini.getInstance();
		
		assertEquals(0, tc.recuperaCondomini().getCondomini().size());
		
		Indirizzo ind = new Indirizzo("Papagliano","34","Comune",Provincia.AG,"15100");
		
		Condominio c = new Condominio();
		this.tc.inserisciCondominio(c);
		c.modificaDati(new DatiCondominio("Papigliano1",ind));
		c.modificaPreferenze(new Preferenze((float)3.4,10,new Euro((float)150.0)));
		
		assertEquals(1, tc.recuperaCondomini().getCondomini().size());
	}
	
	
	public void testCONDOMINI_recupero()
	{		
		tc = TuttiCondomini.getInstance();
	
		assertEquals(1, tc.recuperaCondomini().getCondomini().size());
		
		Indirizzo ind = new Indirizzo("Papagliano","34","Comune",Provincia.AG,"15100");
		
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		
		assertTrue(cond.getDatiC().equals(new DatiCondominio("Papigliano1",ind)));
		assertTrue(cond.getPreferenze().equals(new Preferenze((float)3.4,10,new Euro((float)150.0))));
		assertTrue(cond.getStatoCondominio().equals(StatoCondominio.inCompilazione));
	}
	

	public void testCONDOMINI_modificaPreferenze()
	{
		tc = TuttiCondomini.getInstance();
			
		Condominio c = tc.recuperaCondomini().getCondomini().get(0); // recupero il condominio
		c.modificaPreferenze(new Preferenze((float)4.3,100,new Euro((float)510.0)));

		assertTrue(tc.recuperaCondomini().getCondomini().get(0).getPreferenze().equals(new Preferenze((float)4.3,100,new Euro((float)510.0))));
	}
	
	public void testCONDOMINI_inserireUnitaImmobiliare()
	{
		tc = TuttiCondomini.getInstance();
		
		tp = new TuttePersone();
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);

		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 1",CategoriaCatastale.A10,"interna", (float)85,DestinazioneUso.appartamento);
		
		UnitaImmobiliare ui = new UnitaImmobiliare(dui);
		cond.inserisciUnitaImmobiliare(ui);
		
		assertEquals(1, cond.recuperaUnitaImmobiliari().getImmobili().size());
	}
	
	public void testCONDOMINI_inserireUnitaImmobiliare2()
	{
		tc = TuttiCondomini.getInstance();
		
		tp = new TuttePersone();
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);

		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità2",CategoriaCatastale.A10,"Balcone", (float)85,DestinazioneUso.appartamento);
		
		UnitaImmobiliare ui = new UnitaImmobiliare(dui);
		cond.inserisciUnitaImmobiliare(ui);
		
		assertEquals(2, cond.recuperaUnitaImmobiliari().getImmobili().size());
	}
	

	public void testCONDOMINI_recuperaUnitaImmobiliare()
	{		
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		UnitaImmobiliare unitaRecuperata = c.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		//dati inseriti nell'unità precedente
		DatiUnitaImmobiliare dui = new DatiUnitaImmobiliare("Unità 1",CategoriaCatastale.A10,"interna", (float)85,DestinazioneUso.appartamento);	
		//controllo che i dati inseriti siano uguali
		assertTrue(unitaRecuperata.getDatiUnitaImmobiliare().equals(dui));
	}
	
	public void testCONDOMINI_modificaUnitaImmobiliare()
	{
		tc = TuttiCondomini.getInstance();
		
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
		tc = TuttiCondomini.getInstance();
		
		tp = new TuttePersone();
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		
		//creo la persona e la inserisco
		Indirizzo ind = new Indirizzo("adua","3","Comune",Provincia.AG,"15060");
		DatiPersonaFisica dpf = new DatiPersonaFisica(new CodiceFiscale("codFisc"),"bruno","mazzarello","328-4724731",ind,"0143-50187",new Email("mazzibruno@libero.it"),"ff");
		tp.inserisciPersona(dpf);
		
		Indirizzo ind1 = new Indirizzo("adua","3","Comune",Provincia.AG,"15060");
		DatiPersonaFisica dpf1 = new DatiPersonaFisica(new CodiceFiscale("codFisc1"),"Elena","Bianchi","328-4724731",ind1,"0143-50187",new Email("mazzibruno@libero.it"),"ff");
		tp.inserisciPersona(dpf1);
		
		PersonaFisica p = (PersonaFisica)tp.recuperaPersone().recuperaPersone().get(0);
		PersonaFisica p1 = (PersonaFisica)tp.recuperaPersone().recuperaPersone().get(1);
		
		assertTrue(p.getDati().equals(dpf));
		assertTrue(p1.getDati().equals(dpf1));
		cond.inserisciPersona(p);
		cond.inserisciPersona(p1);
		
		//controllo che nel condominio ci siano effettivamente due persone
		assertEquals(2, tc.recuperaCondomini().getCondomini().get(0).getPersone().size());
	}

	public void testCONDOMINI_InserireProprietàUnitaImmobiliare()
	{
		tc = TuttiCondomini.getInstance();
		
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		//recuper l'unità Immobiliare
		UnitaImmobiliare unitaRecuperata = cond.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		//recupero le persone dal condominio
		Persone pers = cond.recuperaCondomini();
		
		Percentuali quote = new Percentuali();
		
		if(pers.getPersone().size()==2)
		{
			quote.inserisciReale((float)0.5);
			quote.inserisciReale((float)0.5);
		}
		unitaRecuperata.modificaProprieta(pers, quote);
	}
	
	public void testCONDOMINI_ModificaProprietaUnitàImmobiliare()
	{
		tc = TuttiCondomini.getInstance();
		
		
		//recupero il condominio
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		//recuper l'unità Immobiliare
		UnitaImmobiliare unitaRecuperata = cond.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		//recupero le persone dal condominio
		Persona pe = cond.recuperaCondomini().recuperaPersone().get(1);
		
		Persone pers = new Persone();
		pers.inserisciPersona(pe);
		
		Percentuali quote = new Percentuali();
			quote.inserisciReale((float)1.0);
	
		unitaRecuperata.modificaProprieta(pers, quote);
	}
	
/*	public void testCONDOMINI_EliminaPersona()
	{
		tc = TuttiCondomini.getInstance();
		
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
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		
		//prima c'è una unità immobiliare
		assertEquals(2, c.recuperaUnitaImmobiliari().getImmobili().size());
	
		//recupero l'unità immobiliare
		UnitaImmobiliare unita = c.recuperaUnitaImmobiliari().getImmobili().get(0);
		
		c.eliminaUnitaImmobiliare(unita);
		//dopo non c'è nessuna unità immobiliare
		assertEquals(1, c.recuperaUnitaImmobiliari().getImmobili().size());
	}
	
/*	public void testCONDOMINI_inserisciTabellaMillesimale()
	{
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		//non ci sono tabelle millesimali
		assertEquals(0, c.recuperaTabelleMillesimali().getTabelle().size());
		
		DatiTabellaMillesimale datiTab = new DatiTabellaMillesimale();
		datiTab.setDescrizione("Divisione Spiazi Pubblici");
		datiTab.setNome("Pubblici");
		
		TabellaMillesimale t = new TabellaMillesimale();
		t.setDati(datiTab);
		c.inserisciTabellaMillesimale(t);
		
		assertEquals(1, c.recuperaTabelleMillesimali().getTabelle().size());
	}
	
	public void testCONDOMINI_modificaMillesimi_TabellaMillesimale()
	{
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		//c'è una sona tabelle millesimale
		assertEquals(1, c.recuperaTabelleMillesimali().getTabelle().size());
		
		//recupero la tabella millesimale
		TabellaMillesimale t = c.recuperaTabelleMillesimali().getTabelle().get(0);
		
		Millesimi mill = new Millesimi();
		mill.inserisciMillesimo(new Millesimo((float)0.3, t));
		mill.inserisciMillesimo(new Millesimo((float)0.3, t));
		mill.inserisciMillesimo(new Millesimo((float)0.2, t));
	
		t.modificaTabella("Pubblici_mod", mill);
		
		assertEquals(1, c.recuperaTabelleMillesimali().getTabelle().size());
	}
	
	public void testCONDOMINI_inserisciBilancio()
	{
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		//non ci sono bilanci
		assertEquals(0, c.recuperaBilanci().getBilanci().size());
		
		DatiBilancio db = new DatiBilancio();
		db.setTitolo("Bilancio di Prova");
		db.setTipo(TipoBilancio.ordinario);
		db.setStato(StatoBilancio.inCompilazione);
		db.impostaDataInizio(new Data(1,0,2009));
		db.impostaDataFine(new Data(31,11,2009));
		db.setDescrizione("Sarò in grado di redigere un bilancio?? Ma!!!");
		Bilancio b = new Bilancio(db);
		
		c.inserisciBilancio(b);
		
		assertEquals(1, c.recuperaBilanci().getBilanci().size());
	}
	
	public void testCONDOMINI_recuperaBilancio_inEsercizio()
	{
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		//non ci sono bilanci in esercizio
		assertEquals(0, c.recuperaBilanciInEsercizio().getBilanci().size());
	}
	
	public void testCONDOMINI_inserisci_VoceBilancio()
	{
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		
		Bilancio b = c.recuperaBilanci().getBilanci().get(0);
		
		Data d = new Data();
		d.creaCurrenDate();
		
		DatiVoceBilancio dvb = new DatiVoceBilancio("Spesa per tetto",TipoVoce.spesa,"Coibentiamo il tetto",new Euro((float)1500.0),d);
		VoceBilancio vb = new VoceBilancio(dvb);
		
		//recupero una tebella millesimale
		TabellaMillesimale t = c.recuperaTabelleMillesimali().getTabelle().get(0);

		b.inserisciVoceBilancio(vb);
		vb.ripartisci(t);
		
		//esiste una voce di bilancio
		assertEquals(1, b.recuperaVociBilancio().getVoci().size());
	}
	
	public void testCONDOMINIO_bilancioTerminabile()
	{
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		
		Bilancio b = c.recuperaBilanci().getBilanci().get(0);
		
		assertTrue(b.terminabile());
		
	}
	
	public void testCONDOMINI_recuperaCassa()
	{
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		//non ci sono bilanci in esercizio
		assertTrue(c.recuperaCassa() instanceof Cassa);
	}
	
	public void testCONDOMINI_registraMovimentoCassa()
	{
		tc = TuttiCondomini.getInstance();
		
		Condominio c = tc.recuperaCondomini().getCondomini().get(0);
		//non ci sono bilanci in esercizio
		Cassa cassa = c.recuperaCassa(); 
		assertTrue(cassa instanceof Cassa);
		
		DatiMovimentoCassa dmc = new DatiMovimentoCassa("Rifacimento Tetto",new Euro((float)-1000.0));
		MovimentoCassa m = new MovimentoCassa(dmc);
		
		DatiMovimentoCassa dmc1 = new DatiMovimentoCassa("Sponsor Pubblicitario",new Euro((float)500.0));
		MovimentoCassa m1 = new MovimentoCassa(dmc1);
		
		cassa.registraMovimentoCassa(m);
		cassa.registraMovimentoCassa(m1);
		
		assertEquals((float)-500, cassa.getSaldo().getEuro());
	}
	
	
	//commentato per vedere se rimangono gli inserimenti... cancellando il condominio, cancella tutti i suoi aggragati
/*	public void testCONDOMINI_eliminaCondominio()
	{
		tc = TuttiCondomini.getInstance();
		
		assertEquals(1, tc.recuperaCondomini().getCondomini().size());
		
		Condominio cond = tc.recuperaCondomini().getCondomini().get(0);
		
		tc.eliminaCondominio(cond);
		assertEquals(0, tc.recuperaCondomini().getCondomini().size());
	}

	*/
}
