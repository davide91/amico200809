//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.TabellaMillesimale;
import datatype.DatiTabellaMillesimale;
import datatype.list.Millesimi;
import datatype.list.Percentuali;
import datatype.list.TabelleMillesimali;
import datatype.list.UnitaImmobiliari;
import enumeration.StatiAccedereTabelleMillesimali;
import executor.GestoreCondomini;

/**
 * @author Federico
 *
 */
public class InserisciTabellaMillesimaleProprieta extends JFrame implements BaseBoundary{

	private StatiAccedereTabelleMillesimali state;
	private UnitaImmobiliari unita;
	private DatiTabellaMillesimale datiTabella;


	public InserisciTabellaMillesimaleProprieta(UnitaImmobiliari unita)
	{
		this.unita=unita;
		state=StatiAccedereTabelleMillesimali.base;
		initComponents();
	}

	public InserisciTabellaMillesimaleProprieta() {
		initComponents();
	}
	
	
	
	public void inserisciTabellaMillesimale(DatiTabellaMillesimale DTM){
		datiTabella=DTM;
		//AMM.mostraUnitaImmobiliar(unita);
		state=StatiAccedereTabelleMillesimali.attesaMillesimi;
	}
	public void modificaTabellaMillesimale(TabellaMillesimale TM , DatiTabellaMillesimale DTM, Percentuali millesimi)
	{
		if (millesimi.somma()==1000){
			//AMM.richiestaConferma();
			state=StatiAccedereTabelleMillesimali.attesaConfermaInserimento;
			
			
		}
		
	}
	
	public void inserisciMillesimi(Millesimi millesimi){
		if (millesimi.somma()==1000){
			state=StatiAccedereTabelleMillesimali.attesaControlloMillesimi;
			GestoreCondomini.getInstance().passaTabellaMillesimaleProprieta(millesimi);
			
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
		GestoreCondomini.getInstance().operazioneTerminata();
	}

	public void ko() {
		switch (state) {
		case attesaConfermaInserimento:
		//	GestoreCondomini.getInstance().modificaTabellaMillesimale(tabellaMillesimale, datiTabella.getDescrizione(), )
			break;
		case attesaConfermaMillesimi:
			GestoreCondomini.getInstance().procedi(false);
			//AMM.mostra(TabellaMillesimaleInseritaKO);
			break;
		default:
			break;
		}
		state=StatiAccedereTabelleMillesimali.base;
		
	}

	public void ok() {
		GestoreCondomini.getInstance().procedi(true);
		//AMM.mostra(TabellaMillesimaleInseritaOK);
		state=StatiAccedereTabelleMillesimali.base;
	}

	
	

	
	
	
	
	private static final long serialVersionUID = 1L;
	private JTable tabella;
	private JScrollPane millesimi;
	private JLabel jLabel0;
	private JButton bmodificatabella;
	private JButton binseriscitabella;
	private JSeparator jSeparator0;
	private JButton bContinua;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(12, 140, 10, 10), new Leading(18, 10, 10)));
		add(getMillesimi(), new Constraints(new Bilateral(131, 12, 22), new Leading(12, 226, 48, 48)));
		add(getJSeparator0(), new Constraints(new Bilateral(11, 12, 581), new Trailing(42, 10, 50, 250)));
		add(getBContinua(), new Constraints(new Leading(455, 96, 10, 10), new Trailing(12, 50, 250)));
		add(getBinseriscitabella(), new Constraints(new Leading(123, 10, 10), new Trailing(12, 50, 250)));
		add(getBmodificatabella(), new Constraints(new Leading(294, 10, 10), new Trailing(12, 50, 250)));
		setSize(604, 389);
		setVisible(true);
	}

	private JButton getBContinua() {
		if (bContinua == null) {
			bContinua = new JButton();
			bContinua.setText("Annulla");
		}
		return bContinua;
	}

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}

	private JButton getBinseriscitabella() {
		if (binseriscitabella == null) {
			binseriscitabella = new JButton();
			binseriscitabella.setText("Inserisci tabella");
		}
		return binseriscitabella;
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
			millesimi.setViewportView(getTabella());
		}
		return millesimi;
	}

	private JTable getTabella() {
		if (tabella == null) {
			tabella = new JTable();
			tabella.setModel(new DefaultTableModel(new Object[][] { }, new String[] { "Unita", "Coefficente", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return tabella;
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
				
				TabelleMillesimali tm=new TabelleMillesimali();
				TabellaMillesimale tab2=new TabellaMillesimale();
				DatiTabellaMillesimale dati2=new DatiTabellaMillesimale();
				
				dati2.setNome("ciao2");
				
				tab2.setDati(dati2);
				

				TabellaMillesimale tab=new TabellaMillesimale();
				DatiTabellaMillesimale dati=new DatiTabellaMillesimale();
				
				
				dati.setNome("ciao");
				
				tab.setDati(dati);
				
				tm.inserisciTabellaMillesimale(tab);
				tm.inserisciTabellaMillesimale(tab2);
				
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("AccedereTabelleMillesimali");
				AccedereTabelleMillesimali content = new AccedereTabelleMillesimali(null,tm,null);
				content.setPreferredSize(content.getSize());
				frame.add(content, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}


}