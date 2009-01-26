//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

/**
 * @author Federico
 *
 */
public class ConfermaUnitaImmobiliari extends JFrame {

	
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JButton bContinua;
	private JButton bInserisciUnitaImmobiliare;
	private static final long serialVersionUID = 1L;
	private static final String PREFERRED_LOOK_AND_FEEL = null;

	public ConfermaUnitaImmobiliari() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(12, 12, 22), new Leading(12, 183, 10, 10)));
		add(getBContinua(), new Constraints(new Leading(53, 10, 10), new Leading(243, 10, 10)));
		add(getBInserisciUnitaImmobiliare(), new Constraints(new Leading(185, 10, 10), new Leading(243, 12, 12)));
		setSize(400, 300);
	}
	
	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() 
	{
		if (jTable0 == null)
		{
		   TableCellRenderer defaultRenderer;

		   jTable0= new JTable();
				   
		   defaultRenderer = jTable0.getDefaultRenderer(JButton.class);
		   jTable0.setDefaultRenderer(JButton.class,new RadioButtonRenderer());	   
				   
		   jTable0.setModel( new AbstractTableModel()
			{
				private static final long serialVersionUID = 1L;
						
				public int getColumnCount() { return 6; }
				public int getRowCount() { return 0; }
				public Object getValueAt (int row, int col){return  "";}
				public Class getColumnClass (int column) { return getValueAt(0, column).getClass();}
				public String getColumnName (int column) { if(column==0) return "Identificatore";
															else if(column==1) return "Categoria";
															else if(column==2) return "Destinazione";
															else if(column==3) return "Metratura";
															else if(column==4) return "Posizione";
															else return "proprietari";}
			} );


		}
		
		return jTable0;
	}
	
	private JButton getBInserisciUnitaImmobiliare() {
		if (bInserisciUnitaImmobiliare == null) {
			bInserisciUnitaImmobiliare = new JButton();
			bInserisciUnitaImmobiliare.setText("Inserisci unita'  immobiliare");
		}
		return bInserisciUnitaImmobiliare;
	}

	private JButton getBContinua() {
		if (bContinua == null) {
			bContinua = new JButton();
			bContinua.setText("Continua");
		}
		return bContinua;
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
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
