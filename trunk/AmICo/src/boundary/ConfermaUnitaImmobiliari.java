//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.UnitaImmobiliare;
import datatype.list.Persone;
import datatype.list.UnitaImmobiliari;
import executor.GestoreCondomini;
import executor.GestoreCondominioAperto;

/**
 * @author Federico
 *
 */
public class ConfermaUnitaImmobiliari extends JFrame {

	private ButtonGroup group;
	private GestoreCondominioAperto GCA;
	private UnitaImmobiliari unita;
//	private Persone persone;
	InserireNuovoCondominio INC;
	
	private JTable table;
	private JScrollPane jScrollPane0;
	private JButton bContinua;
	private JButton bAnnulla;
	private JButton bInserisciUnitaImmobiliare;
	private static final long serialVersionUID = 1L;
	private JButton bAggiungiPropietari;
	private JButton bModificaUnita;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	public ConfermaUnitaImmobiliari(InserireNuovoCondominio INC, UnitaImmobiliari u) {
		initComponents();
		initGroup();
		this.INC=INC;
		unita=u;
		aggiornaUnitaImmobiliari(u);
		this.setVisible(true);
	}
	
	public ConfermaUnitaImmobiliari()
	{
		initComponents();
	}
	
	public void aggiornaUnitaImmobiliari(UnitaImmobiliari unita)
	{
		int cont=0;
		
		this.unita=unita;
		initGroup();
		if(unita != null)
		{
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
			    			new JRadioButton()});
			    }
			    for(int i=0;i<cont;i++)
			    	group.add((JRadioButton)dm.getValueAt(i,5));
	
			    table.setModel(dm);
			    table.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
			    table.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));
		}
	}
	
	private void bContinuaMouseMouseClicked(MouseEvent event) {
		if(group.getButtonCount()<2)
			JOptionPane.showMessageDialog(this, "devi inserire almeno 2 unita' immobiliari");
		else {
			INC.finito();
		}
	}

	private void bInserisciUnitaImmobiliareMouseMouseClicked(MouseEvent event) {
		INC.inserisciUnitaImmobiliare();
	}
	
	private void bAggiungiPropietariMouseMouseClicked(MouseEvent event) {
		int i;
		Enumeration e=group.getElements();
		for (i=0; e.hasMoreElements();i++ )
	           if ( ((JRadioButton)e.nextElement()).getModel() == group.getSelection()) 
	        	   JOptionPane.showMessageDialog(this, ""+(i));
		//new AggiungiProprietari(unita.getImmobili().get(i),persone)
		
	}

	private void bModificaUnitaMouseMouseClicked(MouseEvent event) {
		int i;
		Enumeration e=group.getElements();
		for (i=0; e.hasMoreElements();i++ )
	           if ( ((JRadioButton)e.nextElement()).getModel() == group.getSelection()) 
	        	   JOptionPane.showMessageDialog(this, ""+(i));
		//new ModificaUnitaImmobiliare(unita.getImmobili().get(i))
		
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(12, 12, 22), new Leading(12, 183, 10, 10)));
		add(getBInserisciUnitaImmobiliare(), new Constraints(new Leading(16, 10, 10), new Leading(219, 12, 12)));
		add(getBModificaUnita(), new Constraints(new Trailing(12, 327, 327), new Leading(219, 12, 12)));
		add(getBAggiungiPropietari(), new Constraints(new Leading(280, 10, 10), new Leading(219, 12, 12)));
		add(getBContinua(), new Constraints(new Leading(20, 10, 10), new Leading(288, 10, 10)));
		add(getBAnnulla(), new Constraints(new Leading(200, 10, 10), new Leading(288, 10, 10)));
		initGroup();
		setSize(612, 358);
	}

	private JButton getBModificaUnita() {
		if (bModificaUnita == null) {
			bModificaUnita = new JButton();
			bModificaUnita.setText("Modifica unita'");
			bModificaUnita.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bModificaUnitaMouseMouseClicked(event);
				}
			});
		}
		return bModificaUnita;
	}

	private void initGroup() {
		group = new ButtonGroup();
	}

	private JButton getBAggiungiPropietari() {
		if (bAggiungiPropietari == null) {
			bAggiungiPropietari = new JButton();
			bAggiungiPropietari.setText("Aggiungi proprietari");
			bAggiungiPropietari.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bAggiungiPropietariMouseMouseClicked(event);
				}
			});
		}
		return bAggiungiPropietari;
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

	private JButton getBInserisciUnitaImmobiliare() {
		if (bInserisciUnitaImmobiliare == null) {
			bInserisciUnitaImmobiliare = new JButton();
			bInserisciUnitaImmobiliare.setText("Inserisci unita'  immobiliare");
			bInserisciUnitaImmobiliare.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bInserisciUnitaImmobiliareMouseMouseClicked(event);
				}
			});
		}
		return bInserisciUnitaImmobiliare;
	}

	private JButton getBContinua() {
		if (bContinua == null) {
			bContinua = new JButton();
			bContinua.setText("Continua");
			bContinua.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bContinuaMouseMouseClicked(event);
				}
			});
		}
		return bContinua;
	}
	
	private JButton getBAnnulla() {
		if (bAnnulla == null) {
			bAnnulla = new JButton();
			bAnnulla.setText("Annulla");
			bAnnulla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bAnnullaMouseMouseClicked(event);
				}
			});
		}
		return bAnnulla;
	}

	protected void bAnnullaMouseMouseClicked(MouseEvent event) {
	  INC.annulla();
		
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
				ConfermaUnitaImmobiliari frame = new ConfermaUnitaImmobiliari();
				frame.setTitle("ConfermaUnitaImmobiliari");
			//	frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
