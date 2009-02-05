//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import datatype.DatiBilancio;
import enumeration.StatoBilancio;
import enumeration.TipoBilancio;

public class InserireNuovoBilancio extends JFrame {

	private AccedereBilanci AB;
	
	public InserireNuovoBilancio(AccedereBilanci ab) {
		AB=ab;
		initComponents();
		setTitle("Redigendo bilancio");
		setVisible(true);
	}
	
	
	private void bOkMouseMouseClicked(MouseEvent event) {
		DatiBilancio dati=new DatiBilancio();
	
		
		dati.setDescrizione(descrizione.getText());
		dati.setTitolo(titolo.getText());
		dati.setTipo((TipoBilancio)tipoBilancio.getSelectedItem());
		dati.setStato((StatoBilancio)stato.getSelectedItem());// probabilmente non ci va ci sara' uno stato iniziale

		dati.setInizio(new Date( (new GregorianCalendar()).getTimeInMillis() ) );
	//	JOptionPane.showMessageDialog(this,"" +(new GregorianCalendar()).getTimeInMillis());
		
		AB.inserisciBilancio(dati);
		this.dispose();
	}

	private void bAnnullaMouseMouseClicked(MouseEvent event) {
		this.dispose();
	}

	
	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JTextField titolo;
	private JLabel jLabel1;
	private JTextArea descrizione;
	private JScrollPane jScrollPane0;
	private JLabel jLabel2;
	private JComboBox stato;
	private JComboBox tipoBilancio;
	private JButton bOk;
	private JButton bAnnulla;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	public InserireNuovoBilancio() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(18, 80, 10, 10), new Leading(29, 10, 10)));
		add(getTitolo(), new Constraints(new Leading(95, 126, 12, 12), new Leading(69, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(18, 12, 12), new Leading(73, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(96, 246, 10, 10), new Leading(102, 80, 10, 10)));
		add(getJLabel2(), new Constraints(new Leading(19, 12, 12), new Leading(107, 10, 10)));
		add(getStato(), new Constraints(new Leading(96, 124, 12, 12), new Leading(209, 10, 10)));
		add(getTipoBilancio(), new Constraints(new Leading(95, 126, 12, 12), new Leading(23, 10, 10)));
		add(getBOk(), new Constraints(new Leading(49, 10, 10), new Leading(295, 10, 10)));
		add(getBAnnulla(), new Constraints(new Leading(190, 10, 10), new Leading(295, 12, 12)));
		setSize(396, 382);
	}

	private JButton getBAnnulla() {
		if (bAnnulla == null) {
			bAnnulla = new JButton();
			bAnnulla.setText("Annulla");
			bAnnulla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bAnnullaMouseMouseClicked(event);
				}
			});
		}
		return bAnnulla;
	}

	private JButton getBOk() {
		if (bOk == null) {
			bOk = new JButton();
			bOk.setText("OK");
			bOk.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bOkMouseMouseClicked(event);
				}
			});
		}
		return bOk;
	}

	private JComboBox getTipoBilancio() {
		if (tipoBilancio == null) {
			tipoBilancio = new JComboBox();
			tipoBilancio.setModel(new DefaultComboBoxModel(TipoBilancio.values()));
			tipoBilancio.setDoubleBuffered(false);
			tipoBilancio.setBorder(null);
		}
		return tipoBilancio;
	}

	private JComboBox getStato() {
		if (stato == null) {
			stato = new JComboBox();
			stato.setModel(new DefaultComboBoxModel(StatoBilancio.values()));
			stato.setDoubleBuffered(false);
			stato.setBorder(null);
		}
		return stato;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Descrizione :");
		}
		return jLabel2;
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

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Titolo:");
		}
		return jLabel1;
	}

	private JTextField getTitolo() {
		if (titolo == null) {
			titolo = new JTextField();
		}
		return titolo;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Tipo bilancio:");
		}
		return jLabel0;
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
	
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				InserireNuovoBilancio frame = new InserireNuovoBilancio();
				frame.setTitle("InserireNuovoBilancio");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
 */


}
