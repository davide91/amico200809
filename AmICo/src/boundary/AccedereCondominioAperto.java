//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.Condominio;
import datatype.Preferenze;
import datatype.list.Avvisi;
import executor.GestoreCondominioAperto;


/**
 * @author Federico
 *
 */
public class AccedereCondominioAperto extends JFrame implements BaseBoundary{

	private Condominio condominio;
	private GestoreCondominioAperto gca;
	
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
	private JTextField avvisi;
	private JTextField scrittaavvisi;
	private JButton beliminacondominio;
	private JButton bchiudicondominio;
	private JButton besportarecondominio;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AccedereCondominioAperto() {
		initComponents();
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
		add(getPannello(), new Constraints(new Leading(22, 832, 12, 12), new Trailing(42, 261, 118, 158)));
		add(getAvvisi(), new Constraints(new Leading(208, 531, 10, 10), new Leading(76, 68, 54, 315)));
		add(getScrittaavvisi(), new Constraints(new Leading(119, 41, 10, 10), new Leading(92, 36, 54, 315)));
		setJMenuBar(getJMenuBar0());
		setSize(874, 481);
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
			bchiudicondominio.setText("Esportare Condominio");
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
		if (avvisi == null) {
			avvisi = new JTextField();
			avvisi.setEditable(false);
			avvisi.setToolTipText("gli avvisi");
		}
		return avvisi;
	}

	private JPanel getPannello() {
		if (pannello == null) {
			pannello = new JPanel(new FlowLayout());
		//	pannello.setLayout(new GroupLayout());
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

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AccedereCondominioAperto frame = new AccedereCondominioAperto();
				frame.setTitle("AccedereCondominioAperto");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public void ammissibile(Boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void annulla() {
		// TODO Auto-generated method stub
		
	}

	public void fallito() {
		// TODO Auto-generated method stub
		
	}

	public void fatto() {
		// TODO Auto-generated method stub
		
	}

	public void finito() {
		// TODO Auto-generated method stub
		
	}

	public void ko() {
		// TODO Auto-generated method stub
		
	}

	public void ok() {
		// TODO Auto-generated method stub
		
	}

	private void bdaticondominioMouseMouseClicked(MouseEvent event) {
		pannello.removeAll();
		pannello.add(new PannelloTab());
		pannello.revalidate();
		pannello.repaint();
		
		// TODO
	}

	private void bdaticondominiMouseMouseClicked(MouseEvent event) {
		pannello.removeAll();
		pannello.add(new AccedereCondomini());  
		pannello.revalidate();
		pannello.repaint();
		this.passaADatiCondomini();
	}

	private void bbilanciMouseMouseClicked(MouseEvent event) {
		this.passaABilanci();
	}

	private void bcassaMouseMouseClicked(MouseEvent event) {
		this.passaACassa();
	}

	private void bpagamentiMouseMouseClicked(MouseEvent event) {
		this.passaAPagamenti();
	}

	private void breportMouseMouseClicked(MouseEvent event) {
		// TODO
	}

	private void barchiviobilanciMouseMouseClicked(MouseEvent event) {
		// TODO
	}

	private void beliminacondominioMouseMouseClicked(MouseEvent event) {
		this.eliminaCondominio();
	}

	private void bchiudicondominioMouseMouseClicked(MouseEvent event) {
		this.chiudiCondominio();
	}

	private void besportarecondominioMouseMouseClicked(MouseEvent event) {
		this.esportaCondominio();
	}
	
	public void creaAccedereCondominioAperto(GestoreCondominioAperto GCA,Condominio C){
		condominio=C;
		gca=GCA;
	}
	
	public void passaAvvisi(Avvisi a){
		// TODO
	}
	
	public void passaAUnitaImmobiliari(){
		// TODO
	}
	
	public void passaATabelleMillesimali(){
		// TODO
	}
	
	public void passaADatiCondomini(){
		// TODO
	}
	
	public void passaABilanci(){
		// TODO
	}
	
	public void passaACassa(){
		// TODO
	}
	
	public void passaAPreferenze(){
		// TODO
	}
	
	public void passaAPagamenti(){
		// TODO
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
		// TODO
	}
	
	public void esportaCondominio(){	
		// TODO
	}
	
	public void eliminaCondominio(){
		// TODO
	}
	
	/*public void selezionaFile(path){
	}*/
}

