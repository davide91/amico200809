//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.UnitaImmobiliare;
import datatype.list.Percentuali;
import datatype.list.Persone;
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
	
	private ButtonGroup group;
	

	
	public AccedereUnitaImmobiliari() {
		initComponents();
		state=StatiAccedereUnitaImmobiliari.base;
	}

	public AccedereUnitaImmobiliari(GestoreCondominioAperto GCA, UnitaImmobiliari unita) {
		this.GCA=GCA;
	//	this.unita=unita;
		initComponents();
		state = StatiAccedereUnitaImmobiliari.base;
		aggiornaUnitaImmobiliari(unita);

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
		//GCA.inserisciNuovaPersona(); TODO
		state=StatiAccedereUnitaImmobiliari.inserimentoNuovaPersona;
	}
	/*
	public void specificaProprieta(Persone nuovePersone, Percentuali nuoveQuote) {
		GCA.passaProprieta(nuovePersone, nuoveQuote);
		state = StatiAccedereUnitaImmobiliari.controlloProprieta;
	}*/
	
	public void aggiornaUnitaImmobiliari(UnitaImmobiliari unita)
	{
		int cont=0;
		
		this.unita=unita;
		initGroup();
		
		Iterator<UnitaImmobiliare> ui=this.unita.getImmobili().iterator();
		UnitaImmobiliare unit;

		
		DefaultTableModel dm = new DefaultTableModel();
			
		dm.setDataVector(
				new Object[][]{},
				new Object[]{"Identificatore","Categoria","Destinazione","Metratura","Posizione","Seleziona"}
			);

		while(ui.hasNext())
		{
			unit=ui.next();
		   	cont++;
		  	dm.addRow(new Object[]{
		    	unit.getDatiUnitaImmobiliare().getId(),
		    	unit.getDatiUnitaImmobiliare().getCatCatastale().toString(),
		    	unit.getDatiUnitaImmobiliare().getDestUso(),
		    	unit.getDatiUnitaImmobiliare().getMetriQ(),
		    	unit.getDatiUnitaImmobiliare().getPosizioneInterna(),
		   	new JRadioButton() });
		}
		for(int i=0;i<cont;i++)
			group.add((JRadioButton)dm.getValueAt(i,5));

		table.setModel(dm);
		table.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		table.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));
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
	
	private void mouseMouseClicked(MouseEvent event) {
		int i;
		Enumeration e=group.getElements();
		for (i=0; e.hasMoreElements();i++ )
	           if ( ((JRadioButton)e.nextElement()).getModel() == group.getSelection()) 
	        	   JOptionPane.showMessageDialog(this, ""+(i));
	           		//new ModificaProprieta(this,unita.getImmobili().get(i-1),persone)
	}
	
	
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane jScrollPane0;
	private JButton bVisualizzaProprietari;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(12, 12, 22), new Leading(12, 183, 10, 10)));
		add(getBVisualizzaProprietari(), new Constraints(new Leading(177, 10, 10), new Leading(213, 12, 12)));

	
		initGroup();
		setSize(322, 256);
	}

	private void initGroup() {
		group = new ButtonGroup();
	}

	private JButton getBVisualizzaProprietari() {
		if (bVisualizzaProprietari == null) {
			bVisualizzaProprietari = new JButton();
			bVisualizzaProprietari.setText("Visualizza proprietari");
			
			bVisualizzaProprietari.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					mouseMouseClicked(event);
				}
			});
		}
		return bVisualizzaProprietari;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getTable());
		}
		return jScrollPane0;
	}

	private JTable getTable() 
	{
		if (table == null)
		{
		    DefaultTableModel dm = new DefaultTableModel();
		    dm.setDataVector(
		      new Object[][]{},
		      new Object[]{"Identificatore","Categoria","Destinazione","Metratura","Posizione","Seleziona"}
		      );

		    table = new JTable(dm)
		    {
				private static final long serialVersionUID = 1L;

				public void tableChanged(TableModelEvent e)
			    {
					super.tableChanged(e);
			        repaint();
			    }
		    };   
		    table.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		    table.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));
		    
		}

		return table;
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
