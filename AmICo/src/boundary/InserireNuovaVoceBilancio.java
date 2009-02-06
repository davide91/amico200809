//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

/**
 * @author bruno
 *
 */
public class InserireNuovaVoceBilancio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton bAnnulla;
	private JButton bOk;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField importo;
	private JTextArea descrizione;
	private JScrollPane jScrollPane0;
	private JComboBox tipo;
	private JTextField mese;
	private JTextField giorno;
	private JTextField anno;
	private JLabel jLabel4;
	private JTextField titolo;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public InserireNuovaVoceBilancio() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBAnnulla(), new Constraints(new Leading(247, 10, 10), new Leading(371, 10, 10)));
		add(getBOk(), new Constraints(new Leading(49, 10, 10), new Leading(371, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(9, 109, 10, 10), new Leading(304, 26, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(132, 19, 10, 10), new Leading(304, 26, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(267, 40, 10, 10), new Leading(304, 26, 12, 12)));
		add(getMese(), new Constraints(new Leading(224, 38, 10, 10), new Leading(306, 12, 12)));
		add(getGiorno(), new Constraints(new Leading(151, 40, 10, 10), new Leading(305, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(201, 15, 10, 10), new Leading(304, 26, 12, 12)));
		add(getAnno(), new Constraints(new Leading(314, 68, 10, 10), new Leading(305, 12, 12)));
		add(getImporto(), new Constraints(new Leading(151, 92, 12, 12), new Leading(238, 10, 10)));
		add(getJScrollPane0(), new Constraints(new Leading(150, 130, 10, 10), new Leading(131, 80, 10, 10)));
		add(getTipo(), new Constraints(new Leading(151, 128, 12, 12), new Leading(70, 12, 12)));
		add(getTitolo(), new Constraints(new Leading(151, 128, 12, 12), new Leading(27, 12, 12)));
		add(getJLabel7(), new Constraints(new Leading(12, 83, 12, 12), new Leading(237, 26, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(11, 107, 12, 12), new Leading(129, 26, 10, 10)));
		add(getJLabel5(), new Constraints(new Leading(17, 65, 10, 10), new Leading(73, 26, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(15, 65, 10, 10), new Leading(26, 26, 12, 12)));
		setSize(400, 419);
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Tipo:");
		}
		return jLabel5;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Descrizione:");
		}
		return jLabel6;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Importo:");
		}
		return jLabel7;
	}

	private JTextField getTitolo() {
		if (titolo == null) {
			titolo = new JTextField();
		}
		return titolo;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Titolo:");
		}
		return jLabel4;
	}

	private JTextField getAnno() {
		if (anno == null) {
			anno = new JTextField();
		}
		return anno;
	}

	private JTextField getGiorno() {
		if (giorno == null) {
			giorno = new JTextField();
		}
		return giorno;
	}

	private JTextField getMese() {
		if (mese == null) {
			mese = new JTextField();
		}
		return mese;
	}

	private JComboBox getTipo() {
		if (tipo == null) {
			tipo = new JComboBox();
			tipo.setModel(new DefaultComboBoxModel(new Object[] {}));
			tipo.setDoubleBuffered(false);
			tipo.setBorder(null);
		}
		return tipo;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getDescrizione());
		}
		return jScrollPane0;
	}

	private JTextArea getDescrizione() {
		if (descrizione == null) {
			descrizione = new JTextArea();
		}
		return descrizione;
	}

	private JTextField getImporto() {
		if (importo == null) {
			importo = new JTextField();
		}
		return importo;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("anno");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("M");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("G");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Data prevista:");
		}
		return jLabel0;
	}


	private JButton getBOk() {
		if (bOk == null) {
			bOk = new JButton();
			bOk.setText("OK");
		}
		return bOk;
	}

	private JButton getBAnnulla() {
		if (bAnnulla == null) {
			bAnnulla = new JButton();
			bAnnulla.setText("Annulla");
		}
		return bAnnulla;
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
				InserireNuovaVoceBilancio frame = new InserireNuovaVoceBilancio();
				frame.setTitle("InserireNuovaVoceBilancio");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
