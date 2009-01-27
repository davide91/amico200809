//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.UnitaImmobiliare;
import datatype.list.Persone;

/**
 * @author Federico
 *
 */
public class AccedereProprietari extends JFrame {
	
	private UnitaImmobiliare unita;
	private Persone persone;
	
	
	
	
	public AccedereProprietari(UnitaImmobiliare ui,Persone p) {
		initComponents();
		nomeUnita.setText(unita.getDatiUnitaImmobiliare().getId());
		unita=ui;
		persone=p;
	//	this.setVisible(true);		
		
	
	}
	
	
	public AccedereProprietari() {
		initComponents();
	}


	private void bAggiungiProprietarioMouseMouseClicked(MouseEvent event) {
	}


	private void bRimuoviProprietarioMouseMouseClicked(MouseEvent event) {
	}


	private void bInserisciPersonaMouseMouseClicked(MouseEvent event) {
	}


	private void bOKMouseMouseClicked(MouseEvent event) {
	}


	private void bAnnullaMouseMouseClicked(MouseEvent event) {
	}


	private static final long serialVersionUID = 1L;
	private JButton bAnnulla;
	private JButton bOK;
	private JButton bAggiungiProprietario;
	private JButton bRimuoviProprietario;
	private JButton bInserisciPersona;
	private JLabel nomeUnita;
	private JTable table;
	private JScrollPane jScrollPane0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getBAnnulla(), new Constraints(new Leading(234, 10, 10), new Leading(354, 10, 10)));
		add(getNomeUnita(), new Constraints(new Leading(34, 150, 12, 12), new Leading(10, 22, 10, 10)));
		add(getBAggiungiProprietario(), new Constraints(new Leading(38, 10, 10), new Leading(312, 12, 12)));
		add(getBRimuoviProprietario(), new Constraints(new Leading(216, 10, 10), new Leading(312, 12, 12)));
		add(getBInserisciPersona(), new Constraints(new Leading(395, 10, 10), new Leading(312, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(28, 502, 12, 12), new Leading(45, 251, 10, 10)));
		add(getBOK(), new Constraints(new Leading(79, 12, 12), new Leading(354, 12, 12)));
		setSize(581, 426);
	}


	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}


	private JTable getJTable0() {
		if (table == null) {
			table = new JTable();
			table.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return table;
	}


	private JLabel getNomeUnita() {
		if (nomeUnita == null) {
			nomeUnita = new JLabel();
		}
		return nomeUnita;
	}

	private JButton getBInserisciPersona() {
		if (bInserisciPersona == null) {
			bInserisciPersona = new JButton();
			bInserisciPersona.setText("Iinserisci persona");
			bInserisciPersona.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bInserisciPersonaMouseMouseClicked(event);
				}
			});
		}
		return bInserisciPersona;
	}


	private JButton getBRimuoviProprietario() {
		if (bRimuoviProprietario == null) {
			bRimuoviProprietario = new JButton();
			bRimuoviProprietario.setText("Rimuovi proprietario");
			bRimuoviProprietario.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bRimuoviProprietarioMouseMouseClicked(event);
				}
			});
		}
		return bRimuoviProprietario;
	}


	private JButton getBAggiungiProprietario() {
		if (bAggiungiProprietario == null) {
			bAggiungiProprietario = new JButton();
			bAggiungiProprietario.setText("Aggiungi prorpietario");
			bAggiungiProprietario.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bAggiungiProprietarioMouseMouseClicked(event);
				}
			});
		}
		return bAggiungiProprietario;
	}


	private JButton getBOK() {
		if (bOK == null) {
			bOK = new JButton();
			bOK.setText("OK");
			bOK.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bOKMouseMouseClicked(event);
				}
			});
		}
		return bOK;
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
				AccedereProprietari frame = new AccedereProprietari();
				frame.setTitle("ModificaProrprieta");
				//frame.pack();
				frame.setVisible(true);		
				frame.setLocationRelativeTo(null);
			}
		});
	}



}
