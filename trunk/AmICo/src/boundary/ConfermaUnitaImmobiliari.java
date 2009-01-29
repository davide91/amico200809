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

import store.TuttePersone;
import store.POJO.Persona;
import store.POJO.UnitaImmobiliare;
import datatype.DatiUnitaImmobiliare;
import datatype.list.Percentuali;
import datatype.list.Persone;
import datatype.list.UnitaImmobiliari;
import enumeration.StatiConfermaUnitaImmobiliari;
import enumeration.StatiInserireNuovoCondominio;
import executor.GestoreCondomini;
import executor.GestoreCondominioAperto;
import executor.GestorePersone;

/**
 * @author Federico
 *
 */
public class ConfermaUnitaImmobiliari extends JFrame implements AccedentiPersone {

	private ButtonGroup group;
//	private GestoreCondominioAperto GCA;
	private UnitaImmobiliari unita;
	private Persone persone;
	private InserireNuovoCondominio INC;
	private AccedereProprietari AP;
	private StatiConfermaUnitaImmobiliari state;
	private InserireUnitaImmobiliare IUI;
	
	public ConfermaUnitaImmobiliari(InserireNuovoCondominio INC, UnitaImmobiliari u) {
		state = StatiConfermaUnitaImmobiliari.base;
		initComponents();
		initGroup();
		persone=new Persone();// provvisorio TODO
		this.INC=INC;
		unita=u;
		aggiornaUnitaImmobiliari(u);
		this.setVisible(true);
		this.setTitle("Inserimento unita' immobiliari");
	}
	
	public ConfermaUnitaImmobiliari()
	{
		state = StatiConfermaUnitaImmobiliari.base;
		initComponents();
	}
	
	public void inserisciNuovaPersona(){
		state= StatiConfermaUnitaImmobiliari.inserimentoNuovaPersona;
		GestorePersone.getInstance().inserisciPersona(this);
	}
	

	
	public void specificaProprietari(Persone persone, Percentuali percentuali){
		if (proprietaOK(persone, percentuali)){
			state=StatiConfermaUnitaImmobiliari.attesaConfermaProprieta;
			GestoreCondomini.getInstance().passaProprieta(persone, percentuali);
			//AMM.richiestaConferma();
		}
		else {
			//AMM.mostra(proprietaKO);
		}
	}

	public void creaAccedereProprietari()
	{
		state = StatiConfermaUnitaImmobiliari.inserimentoProprietari;
		AP = new AccedereProprietari(this,persone);
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
	
	public void ok() {
		if(state==StatiConfermaUnitaImmobiliari.attesaConfermaProprieta)
		{
			state=StatiConfermaUnitaImmobiliari.base;
			GestoreCondomini.getInstance().procedi(true);
			JOptionPane.showMessageDialog(this, "unita e quote inserite correttamente");
		}
	}
	
	public void ko() {
		if(state==StatiConfermaUnitaImmobiliari.attesaConfermaProprieta)
		{
			state=StatiConfermaUnitaImmobiliari.base;
			GestoreCondomini.getInstance().procedi(false);
			JOptionPane.showMessageDialog(this, "unita e quote non inserite");
		}
	}
	

	public void aggiornaPersone(Persone persone) {
		this.persone=persone;
		AP.aggiornaPersone(persone);
	}

	public boolean proprietaOK(Persone proprietari, Percentuali quote) {
		state=StatiConfermaUnitaImmobiliari.attesaConfermaProprieta;
		GestoreCondomini.getInstance().passaProprieta(proprietari, quote);
		
		return false;
	}

	public void inserisciDatiUnitaUImmobiliare(DatiUnitaImmobiliare dati) {
		state = StatiConfermaUnitaImmobiliari.attesaConfermaDatiUnitaImmobiliare;
		GestoreCondomini.getInstance().passaDatiUnitaImmobliare(dati);
	}

	public void ammissibile(boolean b) {
		if(state == StatiConfermaUnitaImmobiliari.attesaConfermaDatiUnitaImmobiliare)
		{
			IUI.ammissibile(b);
		}
	}

	public void fatto() {
		IUI.fatto();
		
	}
	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}
	
	private void bContinuaMouseMouseClicked(MouseEvent event) {
		if(group.getButtonCount()<2)
			JOptionPane.showMessageDialog(this, "devi inserire almeno 2 unita' immobiliari");
		else {
			int c = JOptionPane.showConfirmDialog(this, "Attenzione stai terminando l'operazione di inserimento di unita' immobiliari dopo non sara' piu' possibile aggiungerne altre /n vuoi continuare?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(c==0)
			{
				INC.finito();
				this.dispose();
			}
		}
	}

	public void bInserisciUnitaImmobiliareMouseMouseClicked(MouseEvent event) {
		state = StatiConfermaUnitaImmobiliari.attesaConfermaDatiUnitaImmobiliare;
		INC.inserisciUnitaImmobiliare();
		IUI = new InserireUnitaImmobiliare(this/*,persone*/);
		this.setVisible(false);
	}
	
	private void bAggiungiPropietariMouseMouseClicked(MouseEvent event) {
	/*	int i;
		Enumeration e=group.getElements();
		for (i=0; e.hasMoreElements();i++ )
	           if ( ((JRadioButton)e.nextElement()).getModel() == group.getSelection()) 
	        	  AP = new AccedereProprietari(this,unita.getImmobili().get(i),persone);
	*/	
	}

	private void bEliminaUnitaMouseMouseClicked(MouseEvent event) {
		int i;
		Enumeration e=group.getElements();
		for (i=0; e.hasMoreElements();i++ )
	           if ( ((JRadioButton)e.nextElement()).getModel() == group.getSelection()) 
	        	   JOptionPane.showMessageDialog(this, ""+(i));
		//new ModificaUnitaImmobiliare(unita.getImmobili().get(i))
		
	}
	

	
	private JTable table;
	private JScrollPane jScrollPane0;
	private JButton bContinua;
	private JButton bAnnulla;
	private JButton bInserisciUnitaImmobiliare;
	private static final long serialVersionUID = 1L;
	private JButton bAggiungiPropietari;
	private JButton bEliminaUnita;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(12, 12, 22), new Leading(12, 183, 10, 10)));
		add(getBInserisciUnitaImmobiliare(), new Constraints(new Leading(16, 10, 10), new Leading(219, 12, 12)));
		add(getBEliminaUnita(), new Constraints(new Trailing(12, 327, 327), new Leading(219, 12, 12)));
		add(getBAggiungiPropietari(), new Constraints(new Leading(280, 10, 10), new Leading(219, 12, 12)));
		add(getBContinua(), new Constraints(new Leading(20, 10, 10), new Leading(288, 10, 10)));
		add(getBAnnulla(), new Constraints(new Leading(200, 10, 10), new Leading(288, 10, 10)));
		initGroup();
		setSize(612, 358);
	}

	private JButton getBEliminaUnita() {
		if (bEliminaUnita == null) {
			bEliminaUnita = new JButton();
			bEliminaUnita.setText("Elimina unita'");
			bEliminaUnita.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bEliminaUnitaMouseMouseClicked(event);
				}
			});
		}
		return bEliminaUnita;
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
		int c = JOptionPane.showConfirmDialog(this, "Annullare operazione?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (c==0){
			INC.annulla();	
			this.dispose();
		}
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
