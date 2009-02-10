//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
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

	private void bModificaVoceBilancioMouseMouseClicked(MouseEvent event) {
	}

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
		add(getJSeparator0(), new Constraints(new Leading(222, 13, 10, 10), new Leading(22, 340, 10, 10)));
		add(getJSeparator1(), new Constraints(new Leading(-4, 456, 10, 10), new Leading(362, 18, 10, 10)));
		add(getJScrollPane0(), new Constraints(new Leading(6, 212, 12, 12), new Leading(43, 313, 12, 12)));
		add(getJLabel1(), new Constraints(new Bilateral(297, 70, 82), new Leading(15, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(72, 80, 158, 158), new Leading(16, 22, 12, 12)));
		add(getJScrollPane1(), new Constraints(new Leading(229, 214, 10, 10), new Leading(44, 312, 12, 12)));
		add(getBEliminaVoceBilancio(), new Constraints(new Leading(6, 212, 12, 12), new Leading(421, 10, 10)));
		add(getBInserisciVoceBilancio(), new Constraints(new Leading(6, 212, 12, 12), new Leading(380, 12, 12)));
		add(getBChiudiBilancio(), new Constraints(new Leading(230, 212, 12, 12), new Leading(421, 12, 12)));
		add(getBModificaVoceBilancio(), new Constraints(new Leading(230, 212, 12, 12), new Leading(379, 12, 12)));
		setSize(457, 468);
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

	private JButton getBModificaVoceBilancio() {
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
			jLabel1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
			jLabel1.setText("Spese");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
			jLabel0.setText("Incassi");
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
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("SpostamentiDiCassa");
				SpostamentiDiCassa content = new SpostamentiDiCassa();
				content.setPreferredSize(content.getSize());
				frame.add(content, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}


}
