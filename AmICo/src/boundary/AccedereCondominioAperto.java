//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.Condominio;
import datatype.DatiCondominio;
import datatype.EsitoEliminabile;
import datatype.Preferenze;
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
	private JTextField campoavvisi;
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
		add(getBcassa(), new Constraints(new Leading(389, 96, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBpagamenti(), new Constraints(new Leading(491, 96, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBreport(), new Constraints(new Leading(593, 96, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBarchiviobilanci(), new Constraints(new Leading(694, 159, 12, 12), new Leading(12, 58, 48, 48)));
		add(getBbilanci(), new Constraints(new Leading(287, 96, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBdaticondomini(), new Constraints(new Leading(158, 124, 10, 10), new Leading(12, 58, 48, 48)));
		add(getBdaticondominio(), new Constraints(new Leading(22, 130, 10, 10), new Leading(12, 58, 48, 48)));
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

	private JTextField getAvvisi() {
		if (campoavvisi == null) {
			campoavvisi = new JTextField();
			campoavvisi.setEditable(false);
			campoavvisi.setToolTipText("gli avvisi");
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
			barchiviobilanci.setText("Archivio Bilanci");
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
			bdaticondomini.setText("Dati Condomini");
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
			bdaticondominio.setText("Dati Condominio");
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
		this.avvisiCorrenti=avvisi;
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

