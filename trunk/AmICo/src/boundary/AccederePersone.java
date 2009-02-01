//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

// questa classe si dovrebbe chiamare AccedereCondomini ma per motivi di ambiguita' si e' cambiato in AccederePersone

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import datatype.list.Percentuali;
import datatype.list.Persone;
import enumeration.StatiAccederePersone;
import executor.GestoreCondominioAperto;
import executor.GestorePersone;

/**
 * @author Federico
 *
 */
public class AccederePersone extends JPanel implements BaseBoundary, AccedentiPersone{

	private GestoreCondominioAperto GCA;
	private Persone persone;
	@SuppressWarnings("unused")
	private StatiAccederePersone state;
	private ButtonGroup group=new ButtonGroup();
	
	public AccederePersone() {
		initComponents();
		state=StatiAccederePersone.base;
	}
	
	public AccederePersone(GestoreCondominioAperto GCA, Persone persone){
		this.GCA=GCA; 
		this.persone=persone;
		//AMM.mostraPersone(persone);
		initComponents();
		state = StatiAccederePersone.base;
		
		JOptionPane.showMessageDialog(this, "Attenzione alla creazione in persone non vengono inserite le proprieta' e le unita' quindi non si possono mostrare");
	}
	
	

	
	public void modificaCondomino(Persona persona) {
		GestorePersone.getInstance().modificaPersona(this, persona);
		state=StatiAccederePersone.modificaPersone;
	}
	
	public void aggiornaCondomini(Persone persone	) {
		
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
		state=StatiAccederePersone.base;
		
	}

	public void finito() {
		GCA.operazioneTerminata();
		
	}

	public void ko() {
		// TODO Auto-generated method stub
		
	}

	public void ok() {
		// TODO Auto-generated method stub
		
	}

	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	public void aggiornaPersone(Persone persone) {
		this.persone =persone;
		//AMM.mostraPersone(persone);
		
	}

	public boolean proprietaOK(Persone persone, Percentuali quote) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private void bvisualizzaMouseMouseClicked(MouseEvent event) {
		int i;
		Enumeration e=group.getElements();
		for (i=0; e.hasMoreElements();i++ )
	           if ( ((JRadioButton)e.nextElement()).getModel() == group.getSelection()) 
	        	   new ModificarePersona(persone.getPersone().get(i));
	}
	
	
	
	
	private static final long serialVersionUID = 1L;
	private JButton bvisualizza;
	private JLabel jLabel0;
	private JTable table;
	private JScrollPane jScrollPane0;

	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";


	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBvisualizza(), new Constraints(new Trailing(12, 12, 12), new Trailing(12, 80, 236)));
		add(getJLabel0(), new Constraints(new Bilateral(110, 109, 181), new Leading(12, 48, 48)));
		add(getJScrollPane0(), new Constraints(new Bilateral(12, 12, 22), new Bilateral(42, 54, 26)));
		setSize(543, 300);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getTable());
		}
		return jScrollPane0;
	}

	private JTable getTable () {
		if (table == null) {
			table  = new JTable();
			
			int cont=0;
		    DefaultTableModel dm = new DefaultTableModel();
		    dm.setDataVector(
		      new Object[][]{},
		      new Object[]{"Nome e Cognome","Unita' posseduta","Quota posseduta","Seleziona"}
		      );

		    
		    
		    
		    for (Persona p : persone.getPersone())
		    {
		    	cont++;
		    	if(p instanceof PersonaFisica)
					dm.addRow(new Object[]{
						((PersonaFisica)p).getDati().getNome()+" "+((PersonaFisica)p).getDati().getCognome(),
						"",
						"",
						new JRadioButton()  });
				
				else if(p instanceof PersonaGiuridica)
					dm.addRow(new Object[]{
						((PersonaGiuridica)p).getDati().getpIva(),
						"",
						"",
						new JRadioButton()  });
		    
					/*for(Proprieta p2: p.getProprieta() )    non lega le persone alle unita'
					{
						if(p instanceof PersonaFisica)
							dm.addRow(new Object[]{
								((PersonaFisica)p).getDati().getNome()+" "+((PersonaFisica)p).getDati().getCognome(),
								p2.getUnitaImmobiliare().getDatiUnitaImmobiliare().getId(),
								p2.getQuota(),
								new JRadioButton()  });
						
						else if(p instanceof PersonaGiuridica)
							dm.addRow(new Object[]{
								((PersonaGiuridica)p).getDati().getpIva(),
								p2.getUnitaImmobiliare().getDatiUnitaImmobiliare().getId(),
								p2.getQuota(),
								new JRadioButton()  });
					}*/
			}
		    
		    for(int i=0;i<cont;i++)
		    	group.add((JRadioButton)dm.getValueAt(i,3));
		    
		    
		    table = new JTable(dm)
		    {
				private static final long serialVersionUID = 1L;

				public void tableChanged(TableModelEvent e)
			    {
					super.tableChanged(e);
			        repaint();
			    }
		    };   
		    table.setModel(dm);
		    
		    table.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		    table.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));
		}
		return table ;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabel0.setText("Elenco dei condomini");
		}
		return jLabel0;
	}

	private JButton getBvisualizza() {
		if (bvisualizza == null) {
			bvisualizza = new JButton();
			bvisualizza.setText("Dati anagrafici");
			bvisualizza.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bvisualizzaMouseMouseClicked(event);
				}
			});
		}
		return bvisualizza;
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
				frame.setTitle("AccedereCondomini");
				AccederePersone content = new AccederePersone();
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
