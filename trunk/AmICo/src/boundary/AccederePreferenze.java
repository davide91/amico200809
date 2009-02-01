//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import datatype.Euro;
import datatype.Preferenze;
import executor.GestoreCondominioAperto;

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
	private JSeparator jSeparator0;
	private JSeparator jSeparator1;
	private JLabel jLabel4;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	private Preferenze preferenze;
	private Preferenze oldPreferenze;
	private GestoreCondominioAperto m_GestoreCondominioAperto;
	

	public AccederePreferenze() {
		initComponents();
	}

	public AccederePreferenze(Preferenze pref, GestoreCondominioAperto gCA) {
		m_GestoreCondominioAperto = gCA;
		preferenze = pref;
		initComponents();
	}

	private void initComponents() {
		setMinimumSize(new Dimension(399, 176));
		setMaximumSize(new Dimension(800, 300));
		setRequestFocusEnabled(false);
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
		add(getJSeparator0(), new Constraints(new Bilateral(12, 12, 454), new Leading(36, 10, 12, 12)));
		add(getBmodpref(), new Constraints(new Trailing(12, 73, 10, 10), new Trailing(12, 21, 127, 127)));
		add(getJSeparator1(), new Constraints(new Bilateral(12, 12, 459), new Trailing(39, 10, 127, 127)));
		add(getJLabel1(), new Constraints(new Bilateral(12, 111, 146), new Leading(50, 18, 45, 45)));
		add(getJLabel3(), new Constraints(new Bilateral(12, 111, 225), new Leading(97, 18, 10, 10)));
		add(getJTextField1(), new Constraints(new Trailing(9, 93, 10, 10), new Leading(97, 10, 10)));
		add(getJLabel2(), new Constraints(new Bilateral(12, 111, 256), new Leading(73, 18, 10, 10)));
		add(getJSpinner0(), new Constraints(new Trailing(9, 93, 286, 297), new Leading(73, 10, 10)));
		add(getJLabel4(), new Constraints(new Trailing(10, 14, 268, 268), new Leading(48, 20, 52, 61)));
		add(getJTextField0(), new Constraints(new Trailing(36, 66, 274, 274), new Leading(50, 45, 45)));
		setSize(485, 172);
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("%");
			jLabel4.setMinimumSize(new Dimension(11, 14));
			jLabel4.setPreferredSize(new Dimension(11, 14));
			jLabel4.setMaximumSize(new Dimension(11, 14));
		}
		return jLabel4;
	}

	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}

	private JButton getBmodpref() {
		if (bmodpref == null) {
			bmodpref = new JButton();
			bmodpref.setText("Salva");
			bmodpref.setToolTipText("Salva le preferenze finanziarie");
			bmodpref.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bsalvaPreferenzeMouseClicked(event);
				}
			});
		}
		return bmodpref;
	}

	protected void bsalvaPreferenzeMouseClicked(MouseEvent event) {
		
		oldPreferenze = preferenze;
		try{
			preferenze = new Preferenze();
			preferenze.setInteressiMora(Float.parseFloat(jTextField0.getText()));
			preferenze.setRitardoAmmesso((Integer)(jSpinner0.getValue()));
			preferenze.setSogliaMinimaCassa(new Euro(Float.parseFloat(jTextField1.getText())));
			
			int c = JOptionPane.showConfirmDialog(this, "Modificare Preferenze?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
			if (c==0)
				ok();
			else 
				ko();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, "Dati Preferenze non corretti!");
		}
	}

	private void ko() {
		preferenze = oldPreferenze;
		jTextField0.setText(""+preferenze.getInteressiMora());
		jTextField1.setText(""+preferenze.getSogliaMinimaCassa().getEuro());
		jSpinner0.setValue(preferenze.getRitardoAmmesso());
	}

	private void ok() {
		m_GestoreCondominioAperto.modificaPreferenze(preferenze);
		revalidate();
		repaint();
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Soglia minima della cassa (in euro) :");
			jLabel3.setFocusable(false);
		}
		return jLabel3;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Tasso interessi di mora :");
			jLabel1.setFocusable(false);
		}
		return jLabel1;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Giorni di ritardo ammessi nei pagamenti :");
			jLabel2.setFocusable(false);
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
			jTextField0.setMaximumSize(new Dimension(600, 400));
		//	Field tasso interessi mora
			jTextField0.setText(""+preferenze.getInteressiMora());
		}
		return jTextField0;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			//soglia minima cassa
			jTextField1.setText(""+preferenze.getSogliaMinimaCassa().getEuro());
		}
		return jTextField1;
	}

	private JSpinner getJSpinner0() {
		if (jSpinner0 == null) {
			jSpinner0 = new JSpinner();
			jSpinner0.setValue(preferenze.getRitardoAmmesso());
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
