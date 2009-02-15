//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Cassa;
import store.POJO.MovimentoCassa;
import store.POJO.VoceBilancio;
import datatype.DatiMovimentoCassa;
import datatype.list.VociBilancio;
import enumeration.StatiInserireNuovoCondominio;
import enumeration.TipoVoce;
import executor.GestoreCassa;
import executor.GestoreCondomini;

/**
 * @author Federico
 *
 */
public class AccedereCassa extends JPanel implements BaseBoundary  {

	private Cassa m_cassa;
	private GestoreCassa m_gestoreCassa;
	private RegistraMovimento RM;
	
	public AccedereCassa(GestoreCassa gestoreCassa, Cassa cassa)
	{
		GregorianCalendar g=new GregorianCalendar();
		
		m_cassa = cassa;
		m_gestoreCassa = gestoreCassa;
		initComponents();
		aggiornaMovimenti();
		
		scritta.setText("Saldo cassa al "+g.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(g.get(GregorianCalendar.MONTH)+1)+"/"+g.get(GregorianCalendar.YEAR) );
		saldo.setText(cassa.getSaldo().getEuroIntero()+","+cassa.getSaldo().getCent());
		saldo.setEditable(false);
		saldo.setEnabled(true);
		if(m_cassa.getSaldo().getEuroIntero()<0)
			saldo.setBackground(Color.red);
		else if(m_cassa.getSaldo().getEuroIntero()>0)
			saldo.setBackground(Color.green);
		else saldo.setBackground(Color.white);
	}
	
	public AccedereCassa() {
		GregorianCalendar g=new GregorianCalendar();
		initComponents();
		scritta.setText("Saldo cassa al "+g.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(g.get(GregorianCalendar.MONTH)+1)+"/"+g.get(GregorianCalendar.YEAR) );
	}
	
	public void aggiornaCassa(Cassa cassa)
	{
		m_cassa=cassa;
		saldo.setText(cassa.getSaldo().getEuroIntero()+","+cassa.getSaldo().getCent());
		aggiornaMovimenti();
	}
	
	public void aggiornaProspetto(DatiMovimentoCassa movimento) {
		
		int c = JOptionPane.showConfirmDialog(this, "Sei sicuro?\nSe si conferma i dati verranno inseriti nel sistema.", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (c==0){
			ok();
		}
		else {
			ko();
		}
		
	}

	public void aggiornaVociBilancio(VociBilancio voci) {
		RM.aggiornaVociBilancio(voci);
	}
	
	
	public void aggiornaMovimenti()
	{
		DefaultTableModel dm = new DefaultTableModel();
		
		saldo.setText(m_cassa.getSaldo().getEuroIntero()+","+m_cassa.getSaldo().getCent());
		if(m_cassa.getSaldo().getEuroIntero()<0)
			saldo.setBackground(Color.red);
		else if(m_cassa.getSaldo().getEuroIntero()>0)
			saldo.setBackground(Color.green);
		else saldo.setBackground(Color.white);
		
		dm.setDataVector(new String[][]{},new String[]{"Importo(euro)","Tipo","Data","Motivazione"});	

		for(MovimentoCassa m :m_cassa.getMovimentiDiCassa())
		{	if(m.getRelativoAVoce()!=null)
			{
				if(m.getRelativoAVoce().getDati().getTipo()==TipoVoce.spesa)
					dm.addRow(new String[]{
							""+m.getDati().getImportoMovimento().recuperaValore(),
							m.getRelativoAVoce().getDati().getTipo().toString(),
							m.getRelativoAVoce().getDati().recuperaDataPrevista().dataInStringa(),
							m.getDati().getMotivazione()});
				else
					dm.addRow(new String[]{
							""+m.getDati().getImportoMovimento().recuperaValore(),
							m.getRelativoAVoce().getDati().getTipo().toString(),
							m.getRelativoAVoce().getDati().recuperaDataPrevista().dataInStringa(),
							m.getDati().getMotivazione()});
			}
			else if(m.getRelativoAPagamento()!=null)
			{
				dm.addRow(new String[]{
						"-"+m.getDati().getImportoMovimento().recuperaValore(),
						"incasso",
						m.getRelativoAPagamento().getDatiPagamento().getData().toString(),
						m.getDati().getMotivazione()});
			}
		}
		
		table.setModel(dm);
		ColorCellRenderer ccr=new ColorCellRenderer();
		table.getColumn("Data").setCellRenderer(ccr);
		table.getColumn("Tipo").setCellRenderer(ccr);
		table.getColumn("Importo(�)").setCellRenderer(ccr);
		table.getColumn("Motivazione").setCellRenderer(ccr);
		table.setAutoCreateRowSorter(true);
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
		m_gestoreCassa.procedi(false);
		JOptionPane.showMessageDialog(this, "operazione annullata dall'utente");
	}

	public void ok() {
		m_gestoreCassa.procedi(true);
		JOptionPane.showMessageDialog(this, "operazione terminata");
	}
	
	public void passaVoceBilancio(VoceBilancio voce, DatiMovimentoCassa dati)
	{
		m_gestoreCassa.passaVoceBilancio(voce,dati);
	}

	private void bRegistraMovimentoMouseMouseClicked(MouseEvent event) {
		RM=new RegistraMovimento(this);
		m_gestoreCassa.registraMovimentoCassa();
	}
	
	public void registraMovimentoCassa(DatiMovimentoCassa dati)
	{
		m_gestoreCassa.registraMovimentoCassa(dati);
	}
	
	
	private static final long serialVersionUID = 1L;
	private JButton bRegistraMovimento;
	private JTable table;
	private JScrollPane jScrollPane0;
	private JLabel scritta;
	private JTextField saldo;
	private JButton bReportCassa;
//	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBReportCassa(), new Constraints(new Leading(350, 135, 10, 10), new Leading(8, 12, 12)));
		add(getSaldo(), new Constraints(new Leading(240, 72, 10, 10), new Leading(12, 20, 12, 12)));
		add(getScritta(), new Constraints(new Leading(22, 215, 10, 10), new Leading(12, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(11, 590, 10, 10), new Leading(44, 363, 10, 10)));
		add(getBRegistraMovimento(), new Constraints(new Leading(196, 195, 10, 10), new Leading(419, 12, 12)));
		setSize(618, 477);
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
			table.setEnabled(false);
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
			bRegistraMovimento.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bRegistraMovimentoMouseMouseClicked(event);
				}
			});
		}
		return bRegistraMovimento;
	}
/*
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
