//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
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
public class AccederePreferenze extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSpinner jSpinner0;
	private JTextField jTextField1;
	private JTextField jTextField0;
	private JLabel jLabel0;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JButton bmodpref;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AccederePreferenze() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(34, 10, 10), new Leading(25, 10, 10)));
		add(getBmodpref(), new Constraints(new Leading(135, 12, 12), new Leading(213, 44, 10, 10)));
		add(getJSpinner0(), new Constraints(new Leading(262, 54, 10, 10), new Leading(102, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(262, 54, 12, 12), new Leading(66, 12, 12)));
		add(getJTextField1(), new Constraints(new Leading(261, 56, 12, 12), new Leading(146, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(85, 12, 12), new Leading(70, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(10, 12, 12), new Leading(108, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(28, 12, 12), new Leading(144, 10, 10)));
		setSize(400, 300);
	}

	private JButton getBmodpref() {
		if (bmodpref == null) {
			bmodpref = new JButton();
			bmodpref.setText("Modifica dati preferenze");
		}
		return bmodpref;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Soglia minima della cassa (in euro) :");
		}
		return jLabel3;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Tasso % interessi mora:");
		}
		return jLabel1;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Giorni di ritardo ammessi nei pagamenti:");
		}
		return jLabel2;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
			jLabel0.setText("Preferenze finanziarie");
		}
		return jLabel0;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
		}
		return jTextField1;
	}

	private JSpinner getJSpinner0() {
		if (jSpinner0 == null) {
			jSpinner0 = new JSpinner();
		}
		return jSpinner0;
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
				frame.setTitle("Preferenze");
				AccederePreferenze content = new AccederePreferenze();
				content.setPreferredSize(content.getSize());
				frame.add(content, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
