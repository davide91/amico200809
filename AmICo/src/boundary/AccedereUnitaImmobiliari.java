//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
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

import store.POJO.UnitaImmobiliare;

import datatype.list.Persone;
import datatype.list.Percentuali;
import datatype.list.UnitaImmobiliari;
import enumeration.StatiAccedereUnitaImmobiliari;
import executor.GestoreCondominioAperto;

/**
 * @author Federico
 *
 */
public class AccedereUnitaImmobiliari extends JPanel implements BaseBoundary{



	private GestoreCondominioAperto GCA;
	private UnitaImmobiliari unita;
	private Persone persone;
	private StatiAccedereUnitaImmobiliari state;
	
	
	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JButton bmodifica;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";


	public AccedereUnitaImmobiliari() {
		initComponents();
		state=StatiAccedereUnitaImmobiliari.base;
	}

	public AccedereUnitaImmobiliari(GestoreCondominioAperto GCA, UnitaImmobiliari unita) {
		this.GCA=GCA;
		this.unita=unita;
		initComponents();
		state = StatiAccedereUnitaImmobiliari.base;
		//AMM.mostraUnitaImmobiliati(unita);
	}
	
	public void modificaProprieta(UnitaImmobiliare unita) {
		GCA.modificaProprieta(unita);
		state=StatiAccedereUnitaImmobiliari.modificaProprieta;
		//AMM.mostraPersone(persone);
	}

	public void aggiornaPersone(Persone persone) {
		this.persone=persone;
		//AMM.mostraPersone(persone);
		
	}

	public void  inserisciNuovaPersona() {
		GCA.inserisciNuovaPersona();
		state=StatiAccedereUnitaImmobiliari.inserimentoNuovaPersona;
	}
	
	public void specificaProprieta(Persone nuovePersone, Percentuali nuoveQuote) {
		GCA.passaProprieta(nuovePersone, nuoveQuote);
		state = StatiAccedereUnitaImmobiliari.controlloProprieta;
	}
	
	public void ammissibile(Boolean b) {
		if (b){
			//AMM.richiediConferma();
			state=StatiAccedereUnitaImmobiliari.attesaConferma;
		}
		else {
			state=StatiAccedereUnitaImmobiliari.modificaProprieta;
			//AMM.mostra(proprietaKO);
		}
	}

	public void annulla() {
		GCA.operazioneAnnullata();
		state=StatiAccedereUnitaImmobiliari.base;
	}

	public void fallito() {
		// TODO Auto-generated method stub
		
	}

	public void fatto() {
		state=StatiAccedereUnitaImmobiliari.modificaProprieta;
	}

	public void finito() {
		GCA.operazioneTerminata();
		
	}

	public void ko() {
		GCA.procedi(false);
		//AMM.mostra(unitaImmobiliareModificataKO);
		state=StatiAccedereUnitaImmobiliari.base;
	}

	public void ok() {

		GCA.procedi(true);
		//AMM.mostra(unitaImmobiliareModificataOK);
		state=StatiAccedereUnitaImmobiliari.base;
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(0, 0, 22), new Leading(0, 192, 12, 12)));
		add(getBmodifica(), new Constraints(new Leading(107, 186, 10, 10), new Leading(282, 10, 10)));
		setSize(404, 341);
	}

	private JButton getBmodifica() {
		if (bmodifica == null) {
			bmodifica = new JButton();
			bmodifica.setText("Modifica proprieta unita");
		}
		return bmodifica;
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
			jTable0.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "ID", "Categoria catastale", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable0;
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
				frame.setTitle("AccedereUnitaImmobiliari");
				AccedereUnitaImmobiliari content = new AccedereUnitaImmobiliari();
				content.setPreferredSize(content.getSize());
				frame.add(content, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	
	
}
