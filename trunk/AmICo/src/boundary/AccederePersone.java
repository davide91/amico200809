//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

// questa classe si dovrebbe chiamare AccedereCondomini ma per motivi di ambiguita' si e' cambiato in AccederePersone

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.Persona;
import datatype.list.Persone;
import enumeration.StatiAccederePersone;
import executor.GestoreCondominioAperto;
import executor.GestorePersone;

/**
 * @author Federico
 *
 */
public class AccederePersone extends JPanel implements BaseBoundary, AccedentiPersone{

	private static final long serialVersionUID = 1L;
	private JButton bvisualizza;
	private JLabel jLabel0;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private GestoreCondominioAperto GCA;
	private Persone persone;
	private StatiAccederePersone state;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AccederePersone() {
		initComponents();
		state=StatiAccederePersone.base;
	}
	
	public AccederePersone(GestoreCondominioAperto GCA, Persone persone){
		this.GCA=GCA; 
		this.persone=persone;
		//AMM.mostraPersone(persone);
		initComponents();
		state = StatiAccederePersone.base;
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBvisualizza(), new Constraints(new Trailing(12, 12, 12), new Trailing(12, 80, 236)));
		add(getJLabel0(), new Constraints(new Bilateral(110, 109, 181), new Leading(12, 48, 48)));
		add(getJScrollPane0(), new Constraints(new Bilateral(12, 12, 22), new Bilateral(42, 54, 26)));
		setSize(400, 300);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel0.setText("Elenco dei condomini");
		}
		return jLabel0;
	}

	private JButton getBvisualizza() {
		if (bvisualizza == null) {
			bvisualizza = new JButton();
			bvisualizza.setText("Dati anagrafici");
		}
		return bvisualizza;
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
				frame.setTitle("AccedereCondomini");
				AccederePersone content = new AccederePersone();
				content.setPreferredSize(content.getSize());
				frame.add(content, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	public void modificaCondomino(Persona persona) {
		GestorePersone.getInstance().modificaPersona(this, persona);
		state=StatiAccederePersone.modificaPersone;
	}
	
	public void aggiornaCondomini(Persone persone	) {
		
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
		state=StatiAccederePersone.base;
		
	}

	public void finito() {
		GCA.operazioneTerminata();
		
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
		this.persone =persone;
		//AMM.mostraPersone(persone);
		
	}

}
