//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

/**
 * @author Federico
 *
 */
public class DatiGenerali extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField identificatore;
	private JTextField via;
	private JTextField comune;
	private JTextField provincia;
	private JTextField cap;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public DatiGenerali() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getIdentificatore(), new Constraints(new Leading(68, 260, 10, 10), new Leading(38, 10, 10)));
		add(getVia(), new Constraints(new Leading(68, 260, 12, 12), new Leading(65, 10, 10)));
		add(getComune(), new Constraints(new Leading(68, 87, 10, 10), new Leading(91, 10, 10)));
		add(getCap(), new Constraints(new Leading(279, 49, 12, 12), new Leading(91, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(243, 10, 10), new Leading(93, 12, 12)));
		add(getProvincia(), new Constraints(new Leading(68, 86, 12, 12), new Leading(117, 10, 10)));
		add(getJLabel4(), new Constraints(new Leading(9, 12, 12), new Leading(121, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(14, 12, 12), new Leading(95, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(38, 12, 12), new Leading(69, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(42, 12, 12), new Leading(42, 12, 12)));
		setSize(400, 300);
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Provincia:");
		}
		return jLabel4;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("CAP:");
		}
		return jLabel3;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Comune:");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Via:");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("ID:");
		}
		return jLabel0;
	}

	private JTextField getCap() {
		if (cap == null) {
			cap = new JTextField();
		}
		return cap;
	}

	private JTextField getProvincia() {
		if (provincia == null) {
			provincia = new JTextField();
		}
		return provincia;
	}

	private JTextField getComune() {
		if (comune == null) {
			comune = new JTextField();
		}
		return comune;
	}

	private JTextField getVia() {
		if (via == null) {
			via = new JTextField();
		}
		return via;
	}

	private JTextField getIdentificatore() {
		if (identificatore == null) {
			identificatore = new JTextField();
		}
		return identificatore;
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
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("DatiGenerali");
				DatiGenerali content = new DatiGenerali();
				content.setPreferredSize(content.getSize());
				frame.add(content, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
