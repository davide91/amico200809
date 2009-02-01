//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Font;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.Millesimo;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;
import datatype.DatiTabellaMillesimale;
import datatype.list.Percentuali;
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
	@SuppressWarnings("unused")
	private UnitaImmobiliari unita;
	private DatiTabellaMillesimale datiTabella;
	private DefaultTableModel dm = new DefaultTableModel();
	
	
	
	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane millesimi;
	private JLabel jLabel0;
	private JButton bmodificatabella;
	private JList lista;
	private JScrollPane jScrollPane0;
	private JButton binseriscitabella;
	private JSeparator jSeparator0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AccedereTabelleMillesimali(GestoreCondominioAperto GCA, TabelleMillesimali tabelleMillesimali, UnitaImmobiliari unita)
	{
		this.tabelleMillesimali=tabelleMillesimali;
		this.unita=unita;
		this.GCA=GCA;
		
		state=StatiAccedereTabelleMillesimali.base;
		initComponents();

		DefaultListModel listModel = new DefaultListModel();
		
		for (TabellaMillesimale t : this.tabelleMillesimali.getTabelle())
			listModel.addElement(t.getDati().getNome() );

		lista.setModel(listModel);
	}

	public AccedereTabelleMillesimali() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Leading(11, 108, 12, 12), new Leading(39, 199, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(12, 140, 10, 10), new Leading(18, 10, 10)));
		add(getMillesimi(), new Constraints(new Bilateral(131, 12, 22), new Leading(12, 226, 48, 48)));
		add(getJSeparator0(), new Constraints(new Bilateral(11, 12, 581), new Trailing(42, 10, 50, 250)));
		add(getBmodificatabella(), new Constraints(new Trailing(12, 12, 12), new Trailing(12, 50, 250)));
		add(getBinseriscitabella(), new Constraints(new Trailing(152, 12, 12), new Trailing(12, 50, 250)));
		setSize(604, 389);
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
			lista.setModel(listModel);
			lista.addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent event) {
					listaListSelectionValueChanged(event);
				}
			});
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
			jTable0.setModel(new DefaultTableModel(new Object[][] { }, new String[] { "Unita", "Coefficente", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable0;
	}

	@SuppressWarnings("unused")
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
	public void aggiornaTabelleMillesimali(TabelleMillesimali TM)
	{
		this.tabelleMillesimali=TM;
		DefaultListModel listModel = new DefaultListModel();
		
		for (TabellaMillesimale t : this.tabelleMillesimali.getTabelle())
			listModel.addElement(t.getDati().getNome() );

		lista.setModel(listModel);
		
	}
	public void inserisciMillesimi(Percentuali millesimi){
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

	
	
	private void listaListSelectionValueChanged(ListSelectionEvent event) {// TODO da testare
		
		for (TabellaMillesimale t : tabelleMillesimali.getTabelle())
		{
			if( t.getDati().getNome().equals( (String)lista.getSelectedValue() ) )
			{
				dm.setDataVector
				(
						new String[][]{},
						new String[]{ "Unita'", "Coefficente", }
				);
				
				
				Iterator<UnitaImmobiliare> ui=t.getCondominio().getUnitaImmobiliari().iterator();
				
				for (Millesimo m : t.getMillesimi())
					dm.addRow(new String[]{ui.next().getDatiUnitaImmobiliare().getId(),m.getQuota()+""});
				
				jTable0.setModel(dm);
			}
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
 */

}
