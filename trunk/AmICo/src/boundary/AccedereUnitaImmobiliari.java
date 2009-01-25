//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Condominio;
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
	
	private Condominio condominio;
	
	
	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JButton bContinua;
	private JButton bInserisciUnitaImmobiliare;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AccedereUnitaImmobiliari() {
		initComponents();
		state=StatiAccedereUnitaImmobiliari.base;
	}

	public AccedereUnitaImmobiliari(Condominio condominio)
	{
		initComponents2();
		this.condominio=condominio;
		
	}

	public AccedereUnitaImmobiliari(GestoreCondominioAperto GCA, UnitaImmobiliari unita) {
		this.GCA=GCA;
		this.unita=unita;
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
	
	public void specificaProprieta(Persone nuovePersone, Percentuali nuoveQuote) {
		GCA.passaProprieta(nuovePersone, nuoveQuote);
		state = StatiAccedereUnitaImmobiliari.controlloProprieta;
	}
	
	public void aggiornaUnitaImmobiliari(UnitaImmobiliari unita)
	{
		this.unita=unita;

		if(condominio!=null)
		{
			final Iterator<UnitaImmobiliare> ui=this.unita.getImmobili().iterator();
			final UnitaImmobiliari unit=this.unita;

			jTable0.setModel(
			new AbstractTableModel()
			{
				private static final long serialVersionUID = 1L;
						
				public int getColumnCount() { return 6; }
				public int getRowCount() { return unit.getImmobili().size(); }
				public Object getValueAt (int row, int col)
				{
					if(col==0)
					{
						if(ui.hasNext())
							return ui.next().getDatiUnitaImmobiliare().getId();
					}
					else if(col==1)
					{
						if(ui.hasNext())
							return ui.next().getDatiUnitaImmobiliare().getCatCatastale().toString();
					}
					else if(col==2)
					{
						if(ui.hasNext())
							return ui.next().getDatiUnitaImmobiliare().getDestUso().toString();
					}
					else if(col==3)	
					{
						if(ui.hasNext())
							return Float.toString(ui.next().getDatiUnitaImmobiliare().getMetriQ());
					}
					else if(col==4)
					{
						if(ui.hasNext())
							return ui.next().getDatiUnitaImmobiliare().getPosizioneInterna();
					}
					else return new JButton();

					
					return "";
				}
				public Class getColumnClass (int column) { return getValueAt(0, column).getClass();}
				public String getColumnName (int column) { if(column==0) return "Identificatore";
															else if(column==1) return "Categoria";
															else if(column==2) return "Destinazione";
															else if(column==3) return "Metratura";
															else if(column==4) return "Posizione";
															else return "proprietari";}
			}
			);
		}
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
	
	private void initComponents2() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(0, 0, 22), new Leading(0, 192, 12, 12)));
		add(getBContinua(), new Constraints(new Leading(53, 10, 10), new Leading(243, 10, 10)));
		add(getBInserisciUnitaImmobiliare(), new Constraints(new Leading(185, 10, 10), new Leading(243, 12, 12)));
		setSize(404, 341);
	}

	
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(12, 12, 22), new Leading(12, 183, 10, 10)));
		setSize(322, 248);
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
		   jTable0.setDefaultRenderer(JButton.class,new JTableButtonRenderer(defaultRenderer));	   
				   
		   jTable0.setModel( new AbstractTableModel()
			{
				private static final long serialVersionUID = 1L;
						
				public int getColumnCount() { return 6; }
				public int getRowCount() { return 1; }
				public Object getValueAt (int row, int col){return  new JButton();}
				public Class getColumnClass (int column) { return getValueAt(0, column).getClass();}
				public String getColumnName (int column) { if(column==0) return "Identificatore";
															else if(column==1) return "Categoria";
															else if(column==2) return "Destinazione";
															else if(column==3) return "Metratura";
															else if(column==4) return "Posizione";
															else return "proprietari";}
			} );


		}
		jTable0.addMouseListener(new JTableButtonMouseListener(jTable0));
		
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
				AccedereUnitaImmobiliari content = new AccedereUnitaImmobiliari(new Condominio());
				content.setPreferredSize(content.getSize());
				frame.add(content, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
}
