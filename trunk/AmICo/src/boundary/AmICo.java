//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.TuttiCondomini;
import datatype.list.Condomini;

/**
 * @author Federico
 *
 */
public class AmICo extends JFrame {

	private Condomini condomini;
	
	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane0;
	private JButton binserisci;
	private JButton bapri;
	private JLabel jLabel0;
	private JMenuItem jMenuItem0;
	private JMenuItem jMenuItem1;
	private JMenu file;
	private JMenuBar jMenuBar0;
	private JList elenco;

	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AmICo() {
		
		condomini=TuttiCondomini.CONDOMINI;
		initComponents();
		
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(14, 12, 12), new Leading(28, 10, 10)));
		add(getJScrollPane0(), new Constraints(new Leading(14, 200, 10, 10), new Leading(66, 220, 10, 10)));
		add(getBinserisci(), new Constraints(new Leading(240, 182, 12, 12), new Leading(208, 10, 10)));
		add(getBapri(), new Constraints(new Leading(240, 182, 12, 12), new Leading(92, 10, 10)));
		setJMenuBar(getJMenuBar0());
		setSize(445, 324);
	}

	private JList getElenco() {
		if (elenco == null) {
			elenco = new JList();
			DefaultListModel listModel = new DefaultListModel();
			listModel.addElement("prova");
			elenco.setModel(listModel);
		}
		return elenco;
	}

	private JMenuBar getJMenuBar0() {
		if (jMenuBar0 == null) {
			jMenuBar0 = new JMenuBar();
			jMenuBar0.add(getFile());
		}
		return jMenuBar0;
	}

	private JMenu getFile() {
		if (file == null) {
			file = new JMenu();
			file.setText("File");
			file.setOpaque(false);
			file.add(getJMenuItem0());
			file.add(getJMenuItem1());
		}
		return file;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("esci");
			jMenuItem1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					jMenuItem1MouseMouseClicked(event);
				}
			});
		}
		return jMenuItem1;
	}

	private JMenuItem getJMenuItem0() {
		if (jMenuItem0 == null) {
			jMenuItem0 = new JMenuItem();
			jMenuItem0.setText("Importare condominio");
			jMenuItem0.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					jMenuItem0MouseMouseClicked(event);
				}
			});
		}
		return jMenuItem0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
			jLabel0.setText("Ecco i condomini registrati nel sistema:");
		}
		return jLabel0;
	}

	private JButton getBapri() {
		if (bapri == null) {
			bapri = new JButton();
			bapri.setText("Apri condominio");
			bapri.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bapriMouseMouseClicked(event);
				}
			});
		}
		return bapri;
	}

	private JButton getBinserisci() {
		if (binserisci == null) {
			binserisci = new JButton();
			binserisci.setText("Inserisci nuovo condominio");
			binserisci.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					binserisciMouseMouseClicked(event);
				}
			});
		}
		return binserisci;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getElenco());
		}
		return jScrollPane0;
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
				AmICo frame = new AmICo();
				frame.setTitle("AmICo");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void binserisciMouseMouseClicked(MouseEvent event) {
	}

	private void bapriMouseMouseClicked(MouseEvent event) {
		if(elenco.getSelectedIndex()>-1)
		{
			AccedereCondominioAperto ACA=new AccedereCondominioAperto();
			ACA.setTitle((String)elenco.getSelectedValue());
			ACA.setVisible(true);
			this.setVisible(false);
		}
		
	}
	private void jMenuItem0MouseMouseClicked(MouseEvent event) {
	}
	private void jMenuItem1MouseMouseClicked(MouseEvent event) {
	}




}
