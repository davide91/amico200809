//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

public class InserireNuovoCondomino extends JFrame implements BaseBoundary{

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

	private static final long serialVersionUID = 1L;
	private JTextField via;
	private JComboBox comune;
	private JTextField cap;
	private JComboBox provincia;
	private JMenuItem jMenuItem0;
	private JMenu file;
	private JMenuBar jMenuBar0;
	private JLabel scrittavia;
	private JButton inserisci;
	private JLabel scrittaprovincia;
	private JLabel scrittacomune;
	private JLabel scrittacap;
	private JButton annulla;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public InserireNuovoCondomino() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getVia(), new Constraints(new Leading(105, 117, 10, 10), new Leading(77, 10, 10)));
		add(getScrittacap(), new Constraints(new Leading(48, 10, 10), new Leading(203, 12, 12)));
		add(getInserisci(), new Constraints(new Leading(407, 10, 10), new Leading(311, 10, 10)));
		add(getAnnulla(), new Constraints(new Leading(505, 10, 10), new Leading(311, 12, 12)));
		add(getScrittacomune(), new Constraints(new Leading(293, 10, 10), new Leading(144, 12, 12)));
		add(getCap(), new Constraints(new Leading(107, 114, 12, 12), new Leading(199, 12, 12)));
		add(getProvincia(), new Constraints(new Leading(107, 112, 12, 12), new Leading(136, 22, 12, 12)));
		add(getComune(), new Constraints(new Leading(367, 110, 10, 10), new Leading(135, 12, 12)));
		add(getScrittaprovincia(), new Constraints(new Leading(26, 10, 10), new Leading(144, 12, 12)));
		add(getScrittavia(), new Constraints(new Leading(48, 29, 12, 12), new Leading(79, 12, 12)));
		setJMenuBar(getJMenuBar0());
		setSize(620, 394);
	}

	private JButton getAnnulla() {
		if (annulla == null) {
			annulla = new JButton();
			annulla.setText("Annulla");
		}
		return annulla;
	}

	private JLabel getScrittacap() {
		if (scrittacap == null) {
			scrittacap = new JLabel();
			scrittacap.setText("CAP:");
		}
		return scrittacap;
	}

	private JLabel getScrittacomune() {
		if (scrittacomune == null) {
			scrittacomune = new JLabel();
			scrittacomune.setText("Comune:");
		}
		return scrittacomune;
	}

	private JLabel getScrittaprovincia() {
		if (scrittaprovincia == null) {
			scrittaprovincia = new JLabel();
			scrittaprovincia.setText("Provincia:");
		}
		return scrittaprovincia;
	}

	private JButton getInserisci() {
		if (inserisci == null) {
			inserisci = new JButton();
			inserisci.setText("Inserisci");
		}
		return inserisci;
	}

	private JLabel getScrittavia() {
		if (scrittavia == null) {
			scrittavia = new JLabel();
			scrittavia.setText("Via:");
		}
		return scrittavia;
	}

	private JMenuBar getJMenuBar0() {
		if (jMenuBar0 == null) {
			jMenuBar0 = new JMenuBar();
			jMenuBar0.add(getFile());
		}
		return jMenuBar0;
	}

	private JMenu getFile() {
		if (file == null) {
			file = new JMenu();
			file.setText("File");
			file.setOpaque(false);
			file.add(getJMenuItem0());
		}
		return file;
	}

	private JMenuItem getJMenuItem0() {
		if (jMenuItem0 == null) {
			jMenuItem0 = new JMenuItem();
			jMenuItem0.setText("esci");
		}
		return jMenuItem0;
	}

	private JComboBox getProvincia() {
		if (provincia == null) {
			provincia = new JComboBox();
			provincia.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			provincia.setDoubleBuffered(false);
			provincia.setBorder(null);
		}
		return provincia;
	}

	private JTextField getCap() {
		if (cap == null) {
			cap = new JTextField();
		}
		return cap;
	}

	private JComboBox getComune() {
		if (comune == null) {
			comune = new JComboBox();
			comune.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			comune.setDoubleBuffered(false);
			comune.setBorder(null);
		}
		return comune;
	}

	private JTextField getVia() {
		if (via == null) {
			via = new JTextField();
		}
		return via;
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
				InserireNuovoCondomino frame = new InserireNuovoCondomino();
				frame.setTitle("Inserimento Dati Condominio");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}