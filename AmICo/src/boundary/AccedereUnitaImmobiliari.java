//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Persona;
import store.POJO.UnitaImmobiliare;
import datatype.list.Percentuali;
import datatype.list.Persone;
import datatype.list.UnitaImmobiliari;
import enumeration.StatiAccedereUnitaImmobiliari;
import executor.GestoreCondominioAperto;
import executor.GestorePersone;

/**
 * @author Federico
 *
 */
public class AccedereUnitaImmobiliari extends JPanel implements BaseBoundary,AccedentiPersone{

	private GestoreCondominioAperto GCA;
	private AccedereProprietari2 AP;
	private UnitaImmobiliari unita;
	private Persone persone;
	@SuppressWarnings("unused")
	private StatiAccedereUnitaImmobiliari state;
	
	private ButtonGroup group;
	

	public AccedereUnitaImmobiliari() {
		initComponents();
		state=StatiAccedereUnitaImmobiliari.base;
	}

	public AccedereUnitaImmobiliari(GestoreCondominioAperto GCA, UnitaImmobiliari unita,Persone p) {
		this.GCA=GCA;
		this.persone=p;
		initComponents();
		state = StatiAccedereUnitaImmobiliari.base;
		aggiornaUnitaImmobiliari(unita);
	}

	public void modificaProprieta(UnitaImmobiliare unita) {
		state=StatiAccedereUnitaImmobiliari.modificaProprieta;
		GCA.passaAUnitaImmobiliari();
		GCA.modificaProprieta(unita);
	}

	public void aggiornaPersone(Persone persone) {
		this.persone=persone;
		if(AP!=null) AP.aggiornaPersone(persone);
		
	}

	public void  inserisciNuovaPersona() {
		GestorePersone.getInstance().inserisciPersona(this);
		state=StatiAccedereUnitaImmobiliari.inserimentoNuovaPersona;
	}
	
	public void specificaProprieta(Persone nuovePersone, Percentuali nuoveQuote) {
		state = StatiAccedereUnitaImmobiliari.controlloProprieta;
	}
	
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
		if(cont!=0)
			for(int i=0;i<cont;i++)
				group.add((JRadioButton)dm.getValueAt(i,5));

		table.setModel(dm);
		table.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		table.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));
	}
	
	
	public void ammissibile(Boolean b) {
		if (b){
			int c = JOptionPane.showConfirmDialog(this, "Modificare le Proprieta'?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
			if (c==0){
				GCA.procedi(true);
				finito();
			}
			else {
				GCA.procedi(false);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Impossibile Modificare le Proprietà");
		}
		state=StatiAccedereUnitaImmobiliari.modificaProprieta;
	}

	public void annulla() {
		GCA.operazioneTerminata();
		AP=null;
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
		setVisible(true);
	}

	public void ko() {
		JOptionPane.showMessageDialog(this, "operazione annullata dall'utente");
		finito();
		state=StatiAccedereUnitaImmobiliari.base;
	}

	public void ok() {
		GCA.procedi(true);
		JOptionPane.showMessageDialog(this, "Proprieta' modificata");
		finito();
		state=StatiAccedereUnitaImmobiliari.base;
	}
	
	@SuppressWarnings("unchecked")
	private void mouseMouseClicked(MouseEvent event) {
		int i;
		Enumeration e=group.getElements();
		for (i=0; e.hasMoreElements();i++ )
	           if ( ((JRadioButton)e.nextElement()).getModel() == group.getSelection()) 
	           {
	        	   state = StatiAccedereUnitaImmobiliari.modificaProprieta;
	   			   modificaProprieta(unita.getImmobili().get(i));
	        	   AP=new AccedereProprietari2(this,unita.getImmobili().get(i),persone);
	        	   break;
	           }
		
		if(i==group.getButtonCount())
			JOptionPane.showMessageDialog(this, "Selezionare prima l'unita");
	}
	

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane jScrollPane0;
	private JButton bVisualizzaProprietari;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(12, 12, 22), new Leading(12, 380, 10, 10)));
		add(getBVisualizzaProprietari(), new Constraints(new Leading(200, 10, 10), new Leading(400, 12, 12)));

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
		    table.setAutoCreateRowSorter(true);
		}

		return table;
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

	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
	}

	public boolean proprietaOK(Persone persone, Percentuali quote) {
		state = StatiAccedereUnitaImmobiliari.attesaConferma;
		GCA.passaProprieta(persone, quote);
		return false;
	}
}
