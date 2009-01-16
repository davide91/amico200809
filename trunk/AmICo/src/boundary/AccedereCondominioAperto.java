//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;


/**
 * @author Federico
 *
 */
public class AccedereCondominioAperto extends JFrame {

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
		add(getBdaticondomini(), new Constraints(new Leading(148, 124, 10, 10), new Leading(12, 58, 12, 12)));
		add(getBbilanci(), new Constraints(new Leading(277, 96, 10, 10), new Leading(12, 58, 12, 12)));
		add(getBcassa(), new Constraints(new Leading(379, 96, 10, 10), new Leading(12, 58, 12, 12)));
		add(getBpagamenti(), new Constraints(new Leading(481, 96, 10, 10), new Leading(12, 58, 12, 12)));
		add(getBreport(), new Constraints(new Leading(583, 96, 10, 10), new Leading(12, 58, 12, 12)));
		add(getBarchiviobilanci(), new Constraints(new Leading(685, 159, 10, 10), new Leading(12, 58, 12, 12)));
		add(getAvvisi(), new Constraints(new Leading(148, 531, 12, 12), new Leading(78, 68, 10, 10)));
		add(getScrittaavvisi(), new Constraints(new Leading(16, 101, 10, 10), new Leading(88, 36, 10, 10)));
		add(getBdaticondominio(), new Constraints(new Leading(12, 130, 12, 12), new Leading(12, 58, 12, 12)));
		add(getBchiudicondominio(), new Constraints(new Leading(365, 10, 10), new Trailing(12, 167, 507)));
		add(getBeliminacondominio(), new Constraints(new Leading(42, 10, 10), new Trailing(12, 167, 507)));
		add(getBesportarecondominio(), new Constraints(new Leading(694, 10, 10), new Trailing(12, 167, 507)));
		add(getPannello(), new Constraints(new Leading(12, 832, 12, 12), new Bilateral(155, 42, 0)));
		setJMenuBar(getJMenuBar0());
		setSize(874, 481);
	}

	private JButton getBesportarecondominio() {
		if (besportarecondominio == null) {
			besportarecondominio = new JButton();
			besportarecondominio.setText("Esportare Condominio");
		}
		return besportarecondominio;
	}

	private JButton getBchiudicondominio() {
		if (bchiudicondominio == null) {
			bchiudicondominio = new JButton();
			bchiudicondominio.setText("Esportare Condominio");
		}
		return bchiudicondominio;
	}

	private JButton getBeliminacondominio() {
		if (beliminacondominio == null) {
			beliminacondominio = new JButton();
			beliminacondominio.setText("Elimina Condominio");
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
		}
		return avvisi;
	}

	private JPanel getPannello() {
		if (pannello == null) {
			pannello = new JPanel();
			pannello.setLayout(new GroupLayout());
		}
		return pannello;
	}

	private JButton getBarchiviobilanci() {
		if (barchiviobilanci == null) {
			barchiviobilanci = new JButton();
			barchiviobilanci.setText("Archivio Bilanci");
		}
		return barchiviobilanci;
	}

	private JButton getBreport() {
		if (breport == null) {
			breport = new JButton();
			breport.setText("Report");
		}
		return breport;
	}

	private JButton getBpagamenti() {
		if (bpagamenti == null) {
			bpagamenti = new JButton();
			bpagamenti.setText("Pagamenti");
		}
		return bpagamenti;
	}

	private JButton getBcassa() {
		if (bcassa == null) {
			bcassa = new JButton();
			bcassa.setText("Cassa");
		}
		return bcassa;
	}

	private JButton getBbilanci() {
		if (bbilanci == null) {
			bbilanci = new JButton();
			bbilanci.setText("Bilanci");
		}
		return bbilanci;
	}

	private JButton getBdaticondomini() {
		if (bdaticondomini == null) {
			bdaticondomini = new JButton();
			bdaticondomini.setText("Dati Condomini");
		}
		return bdaticondomini;
	}

	private JButton getBdaticondominio() {
		if (bdaticondominio == null) {
			bdaticondominio = new JButton();
			bdaticondominio.setText("Dati Condominio");
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
			jMenuItem0.setText("menu item");
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

}

