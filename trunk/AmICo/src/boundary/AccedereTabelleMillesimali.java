//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.TabellaMillesimale;
import datatype.DatiTabellaMillesimale;
import datatype.list.Reali;
import datatype.list.TabelleMillesimali;
import datatype.list.UnitaImmobiliari;
import enumeration.StatiAccedereTabelleMillesimali;
import executor.GestoreCondominioAperto;

/**
 * @author Federico
 *
 */
public class AccedereTabelleMillesimali extends JPanel implements BaseBoundary{

	private TabelleMillesimali tabelleMillesimali;
	private GestoreCondominioAperto GCA;
	private StatiAccedereTabelleMillesimali state;
	private UnitaImmobiliari unita;
	private DatiTabellaMillesimale datiTabella;
	
	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane millesimi;
	private JLabel jLabel0;
	private JButton bmodificatabella;
	private JList lista;
	private JScrollPane jScrollPane0;
	private JButton binseriscitabella;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AccedereTabelleMillesimali() {
		initComponents();
		state=StatiAccedereTabelleMillesimali.base;
		
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(12, 104, 10, 10), new Leading(18, 10, 10)));
		add(getMillesimi(), new Constraints(new Leading(137, 287, 10, 10), new Leading(18, 220, 10, 10)));
		add(getBinseriscitabella(), new Constraints(new Leading(11, 12, 12), new Leading(258, 10, 10)));
		add(getJScrollPane0(), new Constraints(new Leading(11, 108, 12, 12), new Leading(39, 199, 12, 12)));
		add(getBmodificatabella(), new Constraints(new Leading(223, 10, 10), new Leading(258, 12, 12)));
		setSize(452, 300);
	}

	private JButton getBinseriscitabella() {
		if (binseriscitabella == null) {
			binseriscitabella = new JButton();
			binseriscitabella.setText("Inserisci tabella");
		}
		return binseriscitabella;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getLista());
		}
		return jScrollPane0;
	}

	private JList getLista() {
		if (lista == null) {
			lista = new JList();
			DefaultListModel listModel = new DefaultListModel();
			listModel.addElement("item0");
			listModel.addElement("item1");
			listModel.addElement("item2");
			listModel.addElement("item3");
			lista.setModel(listModel);
		}
		return lista;
	}

	private JButton getBmodificatabella() {
		if (bmodificatabella == null) {
			bmodificatabella = new JButton();
			bmodificatabella.setText("Modifica tabella");
		}
		return bmodificatabella;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
			jLabel0.setText("Nome Tabella");
		}
		return jLabel0;
	}

	private JScrollPane getMillesimi() {
		if (millesimi == null) {
			millesimi = new JScrollPane();
			millesimi.setViewportView(getJTable0());
		}
		return millesimi;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Unita", "Coefficente", }) {
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

	
	public void creaAccedereTabelleMillesimali(GestoreCondominioAperto GCA, TabelleMillesimali tabelleMillesimali, UnitaImmobiliari unita)
	{
		this.tabelleMillesimali=tabelleMillesimali;
		this.unita=unita;
		this.GCA=GCA;
		//AMM.mostraTabelleMillesimali(tabelleMillesimali);
	}
	
	public void inserisciTabellaMillesimale(DatiTabellaMillesimale DTM){
		datiTabella=DTM;
		//AMM.mostraUnitaImmobiliar(unita);
		state=StatiAccedereTabelleMillesimali.attesaMillesimi;
	}
	public void modificaTabellaMillesimale(TabellaMillesimale TM , DatiTabellaMillesimale DTM, Reali millesimi)
	{
		if (millesimi.somma()==1000){
			//AMM.richiestaConferma();
			state=StatiAccedereTabelleMillesimali.attesaConfermaInserimento;
			
			
		}
		
	}
	public void aggiornaTabelleMillesimali(TabelleMillesimali TM)
	{
		this.tabelleMillesimali=TM;
	}
	public void inserisciMillesimi(Reali millesimi){
		if (millesimi.somma()==1000){
			GCA.inserisciTabellaMillesimale(datiTabella, millesimi);
			state=StatiAccedereTabelleMillesimali.attesaControlloMillesimi;
		}
	}
	

	public void ammissibile(Boolean b) {
		if (b){
			//AMM.richiestaConferma();
			state=StatiAccedereTabelleMillesimali.attesaConfermaMillesimi;
			
		}
		else {
			//AMM.mostra(NomeTabellaNonUnico);
			state=StatiAccedereTabelleMillesimali.base;
		}
		
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
		GCA.operazioneTerminata();
	}

	public void ko() {
		switch (state) {
		case attesaConfermaInserimento:
		//	GCA.modificaTabellaMillesimale(tabellaMillesimale, datiTabella.getDescrizione(), )
			break;
		case attesaConfermaMillesimi:
			GCA.procedi(false);
			//AMM.mostra(TabellaMillesimaleInseritaKO);
			break;
		default:
			break;
		}
		state=StatiAccedereTabelleMillesimali.base;
		
	}

	public void ok() {
		GCA.procedi(true);
		//AMM.mostra(TabellaMillesimaleInseritaOK);
		state=StatiAccedereTabelleMillesimali.base;
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
				frame.setTitle("AccedereTabelleMillesimali");
				AccedereTabelleMillesimali content = new AccedereTabelleMillesimali();
				content.setPreferredSize(content.getSize());
				frame.add(content, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}
