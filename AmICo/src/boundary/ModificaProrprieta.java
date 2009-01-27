//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

/**
 * @author Federico
 *
 */
public class ModificaProrprieta extends JFrame {
	
	
	
	
	public ModificaProrprieta() {
		initComponents();
	//	this.setVisible(true);		
		this.add(new JButton());

	}
	
	
	
	
	private static final long serialVersionUID = 1L;
	private JButton bAnnulla;
	private JButton bOK;
	private JButton bAggiungiProprietario;
	private JButton bRimuoviProprietario;
	private JButton bInserisciPersona;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getBAnnulla(), new Constraints(new Leading(234, 10, 10), new Leading(354, 10, 10)));
		add(getBOK(), new Constraints(new Leading(85, 10, 10), new Leading(354, 12, 12)));
		add(getBAggiungiProprietario(), new Constraints(new Leading(38, 10, 10), new Leading(287, 12, 12)));
		add(getBRimuoviProprietario(), new Constraints(new Leading(217, 10, 10), new Leading(287, 12, 12)));
		add(getBInserisciPersona(), new Constraints(new Leading(395, 10, 10), new Leading(287, 12, 12)));
		setSize(581, 426);
	}

	private JButton getBInserisciPersona() {
		if (bInserisciPersona == null) {
			bInserisciPersona = new JButton();
			bInserisciPersona.setText("Iinserisci persona");
		}
		return bInserisciPersona;
	}

	private JButton getBRimuoviProprietario() {
		if (bRimuoviProprietario == null) {
			bRimuoviProprietario = new JButton();
			bRimuoviProprietario.setText("Rimuovi proprietario");
		}
		return bRimuoviProprietario;
	}

	private JButton getBAggiungiProprietario() {
		if (bAggiungiProprietario == null) {
			bAggiungiProprietario = new JButton();
			bAggiungiProprietario.setText("Aggiungi prorpietario");
		}
		return bAggiungiProprietario;
	}

	private JButton getBOK() {
		if (bOK == null) {
			bOK = new JButton();
			bOK.setText("OK");
		}
		return bOK;
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
				ModificaProrprieta frame = new ModificaProrprieta();
				frame.setTitle("ModificaProrprieta");
				//frame.pack();
				frame.setVisible(true);		
				frame.setLocationRelativeTo(null);
			}
		});
	}

}
