//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
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
import datatype.list.Millesimi;
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
	private DefaultTableModel dm = new DefaultTableModel();
	
	private InserisciModificaTabellaMillesimale InseriscitabellaMillesimale;
	
	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane millesimi;
	private JLabel jLabel0;
	private JButton bmodificatabella;
	private JList lista;
	private JScrollPane jScrollPane0;
	private JButton binseriscitabella;
	private JSeparator jSeparator0;
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
		add(getJLabel0(), new Constraints(new Leading(12, 500, 10, 10), new Leading(18, 10, 10)));
		add(getJSeparator0(), new Constraints(new Bilateral(11, 12, 581), new Trailing(42, 10, 50, 250)));
		add(getBmodificatabella(), new Constraints(new Trailing(12, 12, 12), new Trailing(12, 50, 250)));
		add(getBinseriscitabella(), new Constraints(new Trailing(152, 12, 12), new Trailing(12, 50, 250)));
		add(getJScrollPane0(), new Constraints(new Leading(12, 150, 10, 10), new Leading(35, 297, 10, 10)));
		add(getMillesimi(), new Constraints(new Leading(168, 461, 12, 12), new Leading(35, 297, 55, 64)));
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
			binseriscitabella.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					binseriscitabellaMouseMouseClicked(event);
				}
			});
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
			bmodificatabella.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bmodificatabellaMouseMouseClicked(event);
				}
			});
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
			jTable0.setEnabled(false);
		}
		return jTable0;
	}

	public void inserisciTabellaMillesimale(DatiTabellaMillesimale DTM,Millesimi millesimi){
		state=StatiAccedereTabelleMillesimali.attesaMillesimi;
		GCA.inserisciTabellaMillesimale(DTM, millesimi);
		InseriscitabellaMillesimale.ammissibile(true);
		
	}
	
	public void modificaTabellaMillesimale(TabellaMillesimale TM , String descrizione, Millesimi millesimi)
	{
		GCA.modificaTabellaMillesimale(TM, descrizione, millesimi);
		InseriscitabellaMillesimale.ammissibile(true);
	}
	
	public void aggiornaTabelleMillesimali(TabelleMillesimali TM)
	{
		this.tabelleMillesimali=TM;
		DefaultListModel listModel = new DefaultListModel();
		
		for (TabellaMillesimale t : this.tabelleMillesimali.getTabelle())
			listModel.addElement(t.getDati().getNome() );

		lista.setModel(listModel);
		
		dm.setDataVector
		(
				new String[][]{},
				new String[]{ "Unita'", "Coefficente", }
		);
		
		jTable0.setModel(dm);
		
	}
	
	public void procedi(boolean b)
	{
		GCA.procedi(b);
	}
	
	public void ammissibile(Boolean b) {
		if (b){
			state=StatiAccedereTabelleMillesimali.attesaConfermaMillesimi;
			InseriscitabellaMillesimale.ammissibile(true);
		}
		else {
			state=StatiAccedereTabelleMillesimali.base;
			InseriscitabellaMillesimale.ammissibile(false);
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
			break;
		case attesaConfermaMillesimi:
			GCA.procedi(false);
			break;
		default:
			break;
		}
		state=StatiAccedereTabelleMillesimali.base;
	}

	public void ok() {
		GCA.procedi(true);
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

	private void binseriscitabellaMouseMouseClicked(MouseEvent event) {
		GCA.visibile(false);
		GCA.passaATabelleMillesimali();
		InseriscitabellaMillesimale =	new InserisciModificaTabellaMillesimale(this,unita,GCA);
	}

	private void bmodificatabellaMouseMouseClicked(MouseEvent event) {
		for (TabellaMillesimale t : tabelleMillesimali.getTabelle())
			if( t.getDati().getNome().equals( (String)lista.getSelectedValue() ) )
			{
				GCA.visibile(false);
				GCA.passaATabelleMillesimali();
				InseriscitabellaMillesimale = new InserisciModificaTabellaMillesimale(this,unita,t,GCA);
			}
	}
}
