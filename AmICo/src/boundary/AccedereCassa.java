//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Cassa;
import datatype.DatiMovimentoCassa;
import datatype.list.VociBilancio;
import store.POJO.MovimentoCassa;
import executor.GestoreCassa;

/**
 * @author Federico
 *
 */
public class AccedereCassa extends JPanel implements BaseBoundary  {

	private Cassa m_cassa;
	private GestoreCassa m_gestoreCassa;
	
	public AccedereCassa(GestoreCassa gestoreCassa, Cassa cassa)
	{
		GregorianCalendar g=new GregorianCalendar();
		
		m_cassa = cassa;
		m_gestoreCassa = gestoreCassa;
		
		initComponents();
		
		scritta.setText("Saldo cassa al "+g.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(g.get(GregorianCalendar.MONTH)+1)+"/"+g.get(GregorianCalendar.YEAR) );
		saldo.setText(cassa.getSaldo().getEuroIntero()+","+cassa.getSaldo().getCent());
	}
	
	public AccedereCassa() {
		GregorianCalendar g=new GregorianCalendar();
		initComponents();
		scritta.setText("Saldo cassa al "+g.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(g.get(GregorianCalendar.MONTH)+1)+"/"+g.get(GregorianCalendar.YEAR) );
	}
	
	public void aggiornaProspetto(DatiMovimentoCassa movimento) {
		// TODO Auto-generated method stub
		
	}

	public void aggiornaVociBilancio(VociBilancio voci) {
	
	}
	
	public void aggiornaTabella()
	{
		DefaultTableModel dm = new DefaultTableModel();
		

		dm.setDataVector(new String[][]{},new String[]{"Importo", "Tipo","Data" ,"Motivazione"});
		dm.addRow(new Object[]{"-50","","",""});
		dm.addRow(new Object[]{"30","","",""});
		dm.addRow(new Object[]{"0","","",""});
		
		for(MovimentoCassa m :m_cassa.getMovimentiDiCassa())
		{
			dm.addRow(new String[]{
					m.getDati().getImportoMovimento().toString(),
					m.getRelativoAVoce().getDati().getTipo().toString(),
					m.getRelativoAPagamento().getDatiPagamento().getData().toString(),
					m.getDati().getMotivazione()});
		}
		
		table.setModel(dm);
		ColorCellRenderer ccr=new ColorCellRenderer();
		table.getColumn("Data").setCellRenderer(ccr);
		table.getColumn("Tipo").setCellRenderer(ccr);
		table.getColumn("Importo").setCellRenderer(ccr);
		table.getColumn("Motivazione").setCellRenderer(ccr);
		
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
		// TODO Auto-generated method stub
	}

	public void finito() {
		// TODO Auto-generated method stub
	}

	public void ko() {
		// TODO Auto-generated method stub
	}

	public void ok() {
		// TODO Auto-generated method stub
	}


	private static final long serialVersionUID = 1L;
	private JButton bRegistraMovimento;
	private JTable table;
	private JScrollPane jScrollPane0;
	private JLabel scritta;
	private JTextField saldo;
	private JButton bReportCassa;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Leading(11, 484, 10, 10), new Leading(44, 275, 10, 10)));
		add(getScritta(), new Constraints(new Leading(22, 170, 10, 10), new Leading(12, 20, 12, 12)));
		add(getSaldo(), new Constraints(new Leading(210, 72, 12, 12), new Leading(12, 20, 12, 12)));
		add(getBReportCassa(), new Constraints(new Leading(350, 135, 10, 10), new Leading(8, 12, 12)));
		add(getBRegistraMovimento(), new Constraints(new Leading(149, 195, 12, 12), new Leading(333, 10, 10)));
		setSize(500, 385);
	}

	private JButton getBReportCassa() {
		if (bReportCassa == null) {
			bReportCassa = new JButton();
			bReportCassa.setText("Report cassa");
			bReportCassa.setEnabled(false);
		}
		return bReportCassa;
	}

	private JTextField getSaldo() {
		if (saldo == null) {
			saldo = new JTextField();
		}
		return saldo;
	}

	private JLabel getScritta() {
		if (scritta == null) {
			scritta = new JLabel();
		}
		return scritta;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getTable());
		}
		return jScrollPane0;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {  "Importo", "Tipo","Data","Motivazione" }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, String.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
			
			/*
			table.setModel(dm);
			table.getColumn("Data").setCellRenderer(ccr);
			table.getColumn("Tipo").setCellRenderer(ccr);
			table.getColumn("Importo").setCellRenderer(ccr);
			table.getColumn("Motivazione").setCellRenderer(ccr);*/
		}
		return table;
	}

	private JButton getBRegistraMovimento() {
		if (bRegistraMovimento == null) {
			bRegistraMovimento = new JButton();
			bRegistraMovimento.setText("Registra movimento");
		}
		return bRegistraMovimento;
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
	
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("AccedereCassa");
				AccedereCassa content = new AccedereCassa();
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
