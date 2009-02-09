//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.Condominio;
import store.POJO.Pagamento;
import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import datatype.Avviso;
import datatype.BilancioStatoAllerta;
import datatype.CassaSottoSogliaMinima;
import datatype.CondominiMorosi;
import datatype.DatiCondominio;
import datatype.DatiPagamento;
import datatype.DatiVoceBilancio;
import datatype.EsitoEliminabile;
import datatype.Euro;
import datatype.PagamentoInScadenza;
import datatype.PagamentoScaduto;
import datatype.Preferenze;
import datatype.SpeseDaPagare;
import datatype.list.Avvisi;
import enumeration.StatiAccedereCondominioAperto;
import executor.GestoreCondominioAperto;


/**
 * @author Federico
 *
 */
public class AccedereCondominioAperto extends JFrame implements BaseBoundary{

	private Condominio condominio;
	private GestoreCondominioAperto GCA;
	private Avvisi avvisiCorrenti;
	private StatiAccedereCondominioAperto state;
	
	private static final long serialVersionUID = 1L;
	private JMenuItem jMenuItem0;
	private JMenu jMenu0;
	private JMenuBar jMenuBar0;
	private JButton bdaticondominio;
	private JButton bdaticondomini;
	private JButton bbilanci;
	private JButton bcassa;
	private JButton bpagamenti;
	private JButton breport;
	private JButton barchiviobilanci;
	private JPanel pannello;
	private JScrollPane campoavvisi;
	private JTextPane campoavvisiText;
	private JTextField scrittaavvisi;
	private JButton beliminacondominio;
	private JButton bchiudicondominio;
	private JButton besportarecondominio;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	public AccedereCondominioAperto(GestoreCondominioAperto gca,Condominio condominio) {
		GCA=gca;
		this.condominio=condominio;

		initComponents();
		setTitle(condominio.getDatiC().getId());
		setLocationRelativeTo(null);
		setVisible(true);
		
		state=StatiAccedereCondominioAperto.base;
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBchiudicondominio(), new Constraints(new Leading(365, 10, 10), new Trailing(12, 167, 507)));
		add(getBeliminacondominio(), new Constraints(new Leading(42, 10, 10), new Trailing(12, 167, 507)));
		add(getBesportarecondominio(), new Constraints(new Leading(694, 10, 10), new Trailing(12, 167, 507)));
		
		add(getBdaticondominio(), new Constraints(new Leading(22, 110, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBdaticondomini(), new Constraints(new Leading(142, 110, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBbilanci(), new Constraints(new Leading(262, 110, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBcassa(), new Constraints(new Leading(382, 110, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBpagamenti(), new Constraints(new Leading(502, 110, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBreport(), new Constraints(new Leading(622, 110, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBarchiviobilanci(), new Constraints(new Leading(742, 110, 12, 12), new Leading(12, 58, 48, 48)));
		
		add(getAvvisi(), new Constraints(new Leading(208, 531, 10, 10), new Leading(76, 68, 54, 315)));
		add(getScrittaavvisi(), new Constraints(new Leading(119, 41, 10, 10), new Leading(92, 36, 54, 315)));
		add(getPannello(), new Constraints(new Leading(50,800, 10, 10), new Bilateral(155, 42, 10)));
		setJMenuBar(getJMenuBar0());
		setSize(874, 800);
		
		this.addWindowListener(new WindowAdapter() {  
		 @Override  
		 public void windowClosing(WindowEvent we) {  
			 	chiudiCondominio(); 
		 	}  
		 });   
	}

	private void chiudiDaFinestra() {
		this.chiudiCondominio();
		this.dispose();
	}
	
	private JButton getBesportarecondominio() {
		if (besportarecondominio == null) {
			besportarecondominio = new JButton();
			besportarecondominio.setText("Esportare Condominio");
			besportarecondominio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					besportarecondominioMouseMouseClicked(event);
				}
			});
		}
		return besportarecondominio;
	}

	private JButton getBchiudicondominio() {
		if (bchiudicondominio == null) {
			bchiudicondominio = new JButton();
			bchiudicondominio.setText("Chiudi Condominio");
			bchiudicondominio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bchiudicondominioMouseMouseClicked(event);
				}
			});
		}
		return bchiudicondominio;
	}

	private JButton getBeliminacondominio() {
		if (beliminacondominio == null) {
			beliminacondominio = new JButton();
			beliminacondominio.setText("Elimina Condominio");
			beliminacondominio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					beliminacondominioMouseMouseClicked(event);
				}
			});
		}
		return beliminacondominio;
	}

	private JTextField getScrittaavvisi() {
		if (scrittaavvisi == null) {
			scrittaavvisi = new JTextField();
			scrittaavvisi.setEditable(false);
			scrittaavvisi.setText("AVVISI");
		}
		return scrittaavvisi;
	}

	private JScrollPane getAvvisi() {
		if (campoavvisi == null) {
			campoavvisiText = new JTextPane();
			campoavvisiText.setEditable(false);
			campoavvisiText.setContentType("text/html");
			campoavvisiText.setText("Nessun avviso.");
			campoavvisi = new JScrollPane(campoavvisiText);
			campoavvisi.setToolTipText("Gli avvisi");
		}
		return campoavvisi;
	}

	public JPanel getPannello() {
		if (pannello == null) {
			pannello = new JPanel(new FlowLayout());
		}
		return pannello;
	}

	private JButton getBarchiviobilanci() {
		if (barchiviobilanci == null) {
			barchiviobilanci = new JButton();
			barchiviobilanci.setText("<html><body>Archivio<br>Bilanci</body></html>");
			barchiviobilanci.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					barchiviobilanciMouseMouseClicked(event);
				}
			});
		}
		return barchiviobilanci;
	}

	private JButton getBreport() {
		if (breport == null) {
			breport = new JButton();
			breport.setText("Report");
			breport.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					breportMouseMouseClicked(event);
				}
			});
		}
		return breport;
	}

	private JButton getBpagamenti() {
		if (bpagamenti == null) {
			bpagamenti = new JButton();
			bpagamenti.setText("Pagamenti");
			bpagamenti.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bpagamentiMouseMouseClicked(event);
				}
			});
		}
		return bpagamenti;
	}

	private JButton getBcassa() {
		if (bcassa == null) {
			bcassa = new JButton();
			bcassa.setText("Cassa");
			bcassa.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bcassaMouseMouseClicked(event);
				}
			});
		}
		return bcassa;
	}

	private JButton getBbilanci() {
		if (bbilanci == null) {
			bbilanci = new JButton();
			bbilanci.setText("Bilanci");
			bbilanci.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bbilanciMouseMouseClicked(event);
				}
			});
		}
		return bbilanci;
	}

	private JButton getBdaticondomini() {
		if (bdaticondomini == null) {
			bdaticondomini = new JButton();
			bdaticondomini.setText("<html><body>Dati<br>Condomini</body></html>");
			bdaticondomini.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bdaticondominiMouseMouseClicked(event);
				}
			});
		}
		return bdaticondomini;
	}

	private JButton getBdaticondominio() {
		if (bdaticondominio == null) {
			bdaticondominio = new JButton();
			bdaticondominio.setText("<html><body>Dati<br>Condominio</body></html>");
			bdaticondominio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bdaticondominioMouseMouseClicked(event);
				}
			});
		}
		return bdaticondominio;
	}

	private JMenuBar getJMenuBar0() {
		if (jMenuBar0 == null) {
			jMenuBar0 = new JMenuBar();
			jMenuBar0.add(getJMenu0());
		}
		return jMenuBar0;
	}

	private JMenu getJMenu0() {
		if (jMenu0 == null) {
			jMenu0 = new JMenu();
			jMenu0.setText("File");
			jMenu0.setOpaque(false);
			jMenu0.add(getJMenuItem0());
		}
		return jMenu0;
	}

	private JMenuItem getJMenuItem0() {
		if (jMenuItem0 == null) {
			jMenuItem0 = new JMenuItem();
			jMenuItem0.setText("esci");
		}
		return jMenuItem0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	
	private void bdaticondominioMouseMouseClicked(MouseEvent event) {
		pannello.removeAll();
		GCA.inserisciTabbedPanel();
		
		pannello.revalidate();
		pannello.repaint();
	}

	private void bdaticondominiMouseMouseClicked(MouseEvent event) {
		pannello.removeAll();
		this.passaADatiCondomini(); 
		pannello.revalidate();
		pannello.repaint();
		
	}

	private void bbilanciMouseMouseClicked(MouseEvent event) {
		pannello.removeAll();
		
		this.passaABilanci();
		pannello.revalidate();
		pannello.repaint();
		
	}

	private void bcassaMouseMouseClicked(MouseEvent event) {
		pannello.removeAll();
		this.passaACassa();
		pannello.revalidate();
		pannello.repaint();
	}

	private void bpagamentiMouseMouseClicked(MouseEvent event) {
		pannello.removeAll();
		this.passaAPagamenti();
		pannello.revalidate();
		pannello.repaint();
		
	}

	private void breportMouseMouseClicked(MouseEvent event) {
		pannello.removeAll();
		pannello.revalidate();
		pannello.repaint();
		// TODO
	}

	private void barchiviobilanciMouseMouseClicked(MouseEvent event) {
		pannello.removeAll();
		pannello.revalidate();
		pannello.repaint();
		// TODO
	}

	private void beliminacondominioMouseMouseClicked(MouseEvent event) {
		this.eliminaCondominio();
	}

	private void bchiudicondominioMouseMouseClicked(MouseEvent event) {
		this.chiudiCondominio();
		this.dispose();
	}

	private void besportarecondominioMouseMouseClicked(MouseEvent event) {
		this.esportaCondominio();
	}
	
	public void creaAccedereCondominioAperto(GestoreCondominioAperto GCA,Condominio C){
		condominio=C;
		this.GCA=GCA;
	}



	public void ammissibile(Boolean b) {
		if(b)
		{
			int c = JOptionPane.showConfirmDialog(this, "vuoi eliminare?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
			if (c==0)ok();

			else ko();
		}
	}
	
	public void ammissibile(EsitoEliminabile esito){
		//AMM.richiediConferma(esito);
		state=StatiAccedereCondominioAperto.attesaConfermaEliminazione;
	}

	public void annulla() {
		// TODO Auto-generated method stub
		
	}

	public void fallito() {
		// TODO Auto-generated method stub
		
	}

	public void fatto() {
		if (state==StatiAccedereCondominioAperto.chiusuraCondominio){
			//AMM.mostra(condominioChiuso);
		}
		else state=StatiAccedereCondominioAperto.base;
	}

	public void finito() {
		// TODO Auto-generated method stub
		
	}

	public void ko() {
		GCA.procedi(false);
		JOptionPane.showMessageDialog(this, "impossibile eliminare condominio");
		state=StatiAccedereCondominioAperto.base;
	}

	public void ok() {
		GCA.procedi(true);
		JOptionPane.showMessageDialog(this, "condominio eliminato");
		this.dispose();
	}

	
	
	public void passaAvvisi(Avvisi avvisi){
		List<String> toDisplay = new ArrayList<String>();

		for(Avviso avviso : avvisi.getListaAvvisi()) {
			if(avviso instanceof PagamentoScaduto) {
				PagamentoScaduto pagamentoA = (PagamentoScaduto)avviso;
				for(Pagamento pagamento : pagamentoA.getPagamentiScaduti().getPagamenti()) {
					DatiPagamento dati = pagamento.getDatiPagamento();
					toDisplay.add(
							"<b>PAGAMENTO SCADUTO</b>:<br>Il pagamento con codice " + dati.getCodice() + " ed importo " +
							dati.getImporto().toString() + " euro, e' scaduto in data " +
							dati.getScadenza().toString() + ". (Condomino: " + pagamento.getEseguito_da().getId() + ")"
							); 
				}
				continue;
			}
			if(avviso instanceof CondominiMorosi) {
				CondominiMorosi morosiA = (CondominiMorosi)avviso;
				
				String line = new String();
				line += "<b>";
				if (morosiA.getPersone().getPersone().size() != 1)
					line+="CONDOMINI MOROSI";
				else
					line+="CONDOMINO MOROSO";
				
				line+= "</b>:<br><ul>";
				
				for(Persona persona :  morosiA.getPersone().getPersone())
				{
					List<String> pagamentiCode = new ArrayList<String>();
					Euro somma = new Euro(0,0);
					
					for(Pagamento pagamento : morosiA.getPagamenti(persona).getPagamenti())
					{
						DatiPagamento dati = pagamento.getDatiPagamento();
						pagamentiCode.add(dati.getCodice());
						somma.somma(dati.getMora());
					}
					
					line +="<li>";
					
					if (persona instanceof PersonaFisica)
						 line += ((PersonaFisica)persona).getDati().getNome() + " " +
						 		 ((PersonaFisica)persona).getDati().getCognome();
					else
						line += ((PersonaGiuridica)persona).getDati().getRagioneSociale();

					line += " è in mora di " + somma.toString() + " euro. (Codice pagamenti: ";
					for(String codice : pagamentiCode)
						if (pagamentiCode.get(pagamentiCode.size()-1).equals(codice))
							line += codice + ")";
						else
							line += codice + ", ";
					line += "</li>";
				}
				
				line+="</ul>";
				
				toDisplay.add(line);
				
				continue;
			}
			if(avviso instanceof PagamentoInScadenza) {
				PagamentoInScadenza pagamentoA = (PagamentoInScadenza)avviso;
				
				
				String line = new String();
				line += "<b>";
				if (pagamentoA.getPagamentiInScadenza().getPagamenti().size() != 1)
					line+="PAGAMENTI";
				else
					line+="PAGAMENTO";
				
				line+= " IN SCADENZA</b>:<br><ul>";
				
				for(Pagamento pagamento :  pagamentoA.getPagamentiInScadenza().getPagamenti())
				{
					line+="<li>Il giorno <b>"+ pagamento.getDatiPagamento().getScadenza().getTime().toString()+"</b> scade il pagamento " + 
						  "con codice " + pagamento.getDatiPagamento().getCodice() + 
						  " e importo" + pagamento.getDatiPagamento().getImporto().toString() + " euro.</li>";					
				}
				
				line+="</ul>";
				
				toDisplay.add(line);
				
				continue;
			}
			if(avviso instanceof SpeseDaPagare) {
				SpeseDaPagare speseA = (SpeseDaPagare)avviso;
				
				String line = new String();
				line += "<b>";
				if (speseA.getDatiVociBilancio().size() != 1)
					line+="SPESA";
				else
					line+="SPESE";
				
				line+= " DA PAGARE</b>:<br><ul>";
				
				for(DatiVoceBilancio datiVoce :  speseA.getDatiVociBilancio())
				{	
					line+="<li>Il pagamento di \""+ datiVoce.getDescrizione() +"\", con importo " + datiVoce.getImporto().toString() +
						  " euro, e' previsto per il giorno " + datiVoce.getDataPrevista().toString() + "</li>";					
				}
				
				line+="</ul>";
				
				toDisplay.add(line);
				
				
				continue;
			}
			if(avviso instanceof CassaSottoSogliaMinima) {
				CassaSottoSogliaMinima cassaA = (CassaSottoSogliaMinima)avviso;
				toDisplay.add(
						"<b>CASSA SOTTO SOGLIA MINIMA</b>:<br>La cassa è sotto la soglia minima di " + 
						cassaA.getSoglia().toString() + " euro. (Valore attuale: " + cassaA.getAttuale().toString() + " euro)"
				);
				continue;
			}
			if(avviso instanceof BilancioStatoAllerta) {
				BilancioStatoAllerta bilancioA = (BilancioStatoAllerta)avviso;
				toDisplay.add(
						"<b>BILANCIO IN STATO DI ALLERTA</b>: differenza " + bilancioA.getDifferenza().toString() + " euro."
				);
				continue;
			}
		}
		
		String readyToDisplay = new String();
		
		
		for(String line : toDisplay) {
			if (readyToDisplay.isEmpty())
				readyToDisplay += "<html>" + "<font size=\"65%\">" + line + "</font>";
			else
				readyToDisplay += "<hr>" + "<font size=\"65%\">" + line + "</font>";
		}
		readyToDisplay += "</html>";
	
		campoavvisiText.setText(readyToDisplay);
	}
	
	public void passaAUnitaImmobiliari(){
		GCA.passaAUnitaImmobiliari();
		state=StatiAccedereCondominioAperto.gestioneUnitaImmobiliari;
	}
	
	public void passaATabelleMillesimali(){
		GCA.passaATabelleMillesimali();
		state=StatiAccedereCondominioAperto.gestioneTabelleMillesimali;
	}
	
	public void passaADatiCondomini(){
		GCA.passaADatiCondomini();
		state=StatiAccedereCondominioAperto.gestioneDatiCondomini;
	}
	
	public void passaABilanci(){
		GCA.passaABilanci();
		state= StatiAccedereCondominioAperto.gestioneBilanci;
	}
	
	public void passaACassa(){
		GCA.passaACassa();
		state=StatiAccedereCondominioAperto.gestioneCassa;
	}
	
	public void passaAPreferenze(){
		GCA.passaAPreferenze();
		state=StatiAccedereCondominioAperto.gestionePreferenze;
	}
	
	public void passaAPagamenti(){
		GCA.passaAPagamenti();
		state=StatiAccedereCondominioAperto.gestionePagamenti;
	}
	
	public void modificaPreferenze(Preferenze p){
		// TODO
	}
	
	public void passaPreferenze(Preferenze p){
		// TODO
	}

	/*public void faiReportCondominio(TipoReportCondominio trc,FormatoFile ff){	
	}*/
	
	public void chiudiCondominio(){
		GCA.chiudiCondominio();
		state=StatiAccedereCondominioAperto.chiusuraCondominio;
	}
	
	public void esportaCondominio(){	
		//GCA.esportaCondominio(); TODO
		state=StatiAccedereCondominioAperto.esportazioneCondominio;
	}
	
	public void eliminaCondominio(){
		GCA.eliminaCondominio();
		state=StatiAccedereCondominioAperto.controlloEliminabilita;
	}
}

