//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

/**
 * @author Federico
 *
 */
public class SpostamentiDiCassa extends JPanel {
	
	private AccedereBilancioAperto ABA;
	
	public SpostamentiDiCassa(AccedereBilancioAperto aba) {
		ABA=aba;
		initComponents();
	}
	
	public SpostamentiDiCassa() {
		initComponents();
	}

	private void bInserisciVoceBilancioMouseMouseClicked(MouseEvent event) {
		ABA.inserisci();
	}

//	private void bModificaVoceBilancioMouseMouseClicked(MouseEvent event) {
//	}

	private void bEliminaVoceBilancioMouseMouseClicked(MouseEvent event) {
	}

	private void bChiudiBilancioMouseMouseClicked(MouseEvent event) {
		ABA.chiudi();
	}
	
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JSeparator jSeparator0;
	private JSeparator jSeparator1;
	private JButton bInserisciVoceBilancio;
	private JButton bEliminaVoceBilancio;
	private JButton bModificaVoceBilancio;
	private JButton bChiudiBilancio;
	private JTable incassi;
	private JScrollPane jScrollPane0;
	private JTable spese;
	private JScrollPane jScrollPane1;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void initComponents() {
		setBackground(Color.white);
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Leading(6, 350, 12, 12), new Leading(43, 313, 12, 12)));
		add(getJScrollPane1(), new Constraints(new Leading(380, 350, 12, 12), new Leading(43, 312, 12, 12)));
	//	add(getBModificaVoceBilancio(), new Constraints(new Leading(456, 212, 10, 10), new Leading(379, 12, 12)));
		add(getBChiudiBilancio(), new Constraints(new Leading(456, 212, 12, 12), new Leading(421, 12, 12)));
		add(getBInserisciVoceBilancio(), new Constraints(new Leading(75, 212, 12, 12), new Leading(379, 12, 12)));
		add(getBEliminaVoceBilancio(), new Constraints(new Leading(75, 212, 12, 12), new Leading(421, 12, 12)));
		add(getJSeparator0(), new Constraints(new Leading(368, 8, 12, 12), new Leading(21, 340, 12, 12)));
		add(getJSeparator1(), new Constraints(new Leading(4, 726, 12, 12), new Leading(362, 18, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(511, 111, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(137, 120, 10, 10), new Leading(13, 22, 12, 12)));
		setSize(805, 468);
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBackground(Color.white);
			jScrollPane1.setViewportView(getSpese());
		}
		return jScrollPane1;
	}

	private JTable getSpese() {
		if (spese == null) {
			spese = new JTable();
			spese.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		spese.setAutoCreateRowSorter(true);
		return spese;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBackground(Color.white);
			jScrollPane0.setViewportView(getIncassi());
		}
		return jScrollPane0;
	}

	private JTable getIncassi() {
		if (incassi == null) {
			incassi = new JTable();
			incassi.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return incassi;
	}

	private JButton getBChiudiBilancio() {
		if (bChiudiBilancio == null) {
			bChiudiBilancio = new JButton();
			bChiudiBilancio.setText("Chiudi bilancio");
			bChiudiBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bChiudiBilancioMouseMouseClicked(event);
				}
			});
		}
		return bChiudiBilancio;
	}

/*	private JButton getBModificaVoceBilancio() {
		if (bModificaVoceBilancio == null) {
			bModificaVoceBilancio = new JButton();
			bModificaVoceBilancio.setText("Modifica voce bilancio");
			bModificaVoceBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bModificaVoceBilancioMouseMouseClicked(event);
				}
			});
		}
		return bModificaVoceBilancio;
	}
*/
	private JButton getBEliminaVoceBilancio() {
		if (bEliminaVoceBilancio == null) {
			bEliminaVoceBilancio = new JButton();
			bEliminaVoceBilancio.setText("Elimina voce bilancio");
			bEliminaVoceBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bEliminaVoceBilancioMouseMouseClicked(event);
				}
			});
		}
		return bEliminaVoceBilancio;
	}

	private JButton getBInserisciVoceBilancio() {
		if (bInserisciVoceBilancio == null) {
			bInserisciVoceBilancio = new JButton();
			bInserisciVoceBilancio.setText("Inserisci voce bilancio");
			bInserisciVoceBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bInserisciVoceBilancioMouseMouseClicked(event);
				}
			});
		}
		return bInserisciVoceBilancio;
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
			jSeparator0.setOrientation(SwingConstants.VERTICAL);
		}
		return jSeparator0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
			jLabel1.setText("SPESE");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
			jLabel0.setText("INCASSI");
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
}
