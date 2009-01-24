//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Persona;
import datatype.list.Persone;
import enumeration.DestinazioneUso;

public class InserireUnitaImmobiliare extends JFrame implements AccedentiPersone {

	
	public InserireUnitaImmobiliare() {
		initComponents();
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

	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	public void aggiornaPersone(Persone persone) {
		// TODO Auto-generated method stub
		
	}

	
	
	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JComboBox destinazione;
	private JTextField metratura;
	private JTextField posizioneInterna;
	private JButton bConferma;
	private JButton bAnnulla;
	private JTextField categoria;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBConferma(), new Constraints(new Leading(100, 10, 10), new Leading(93, 10, 10)));
		add(getBAnnulla(), new Constraints(new Leading(326, 10, 10), new Leading(93, 12, 12)));
		add(getCategoria(), new Constraints(new Leading(149, 99, 10, 10), new Leading(36, 22, 12, 12)));
		add(getId(), new Constraints(new Leading(16, 113, 10, 10), new Leading(35, 23, 12, 12)));
		add(getDestinazione(), new Constraints(new Leading(260, 96, 12, 12), new Leading(35, 12, 12)));
		add(getMetratura(), new Constraints(new Leading(374, 79, 10, 10), new Leading(35, 22, 12, 12)));
		add(getPosizioneInterna(), new Constraints(new Leading(462, 79, 10, 10), new Leading(35, 23, 12, 12)));
		setSize(561, 170);
	}

	private JTextField getCategoria() {
		if (categoria == null) {
			categoria = new JTextField();
		}
		return categoria;
	}

	private JButton getBAnnulla() {
		if (bAnnulla == null) {
			bAnnulla = new JButton();
			bAnnulla.setText("Annulla");
		}
		return bAnnulla;
	}

	private JButton getBConferma() {
		if (bConferma == null) {
			bConferma = new JButton();
			bConferma.setText("Conferma");
		}
		return bConferma;
	}

	private JTextField getPosizioneInterna() {
		if (posizioneInterna == null) {
			posizioneInterna = new JTextField();
		}
		return posizioneInterna;
	}

	private JTextField getMetratura() {
		if (metratura == null) {
			metratura = new JTextField();
		}
		return metratura;
	}

	private JComboBox getDestinazione() {
		if (destinazione == null) {
			destinazione = new JComboBox();
			destinazione.setModel(new DefaultComboBoxModel( DestinazioneUso.values() ) );
			destinazione.setDoubleBuffered(false);
			destinazione.setBorder(null);
		}
		return destinazione;
	}

	private JTextField getId() {
		if (id == null) {
			id = new JTextField();
		}
		return id;
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
				InserireUnitaImmobiliare frame = new InserireUnitaImmobiliare();
				frame.setTitle("InserireUnitaImmobiliare");
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	
}


