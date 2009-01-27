//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import store.POJO.Proprieta;

import datatype.list.Persone;

/**
 * @author Federico
 *
 */
public class InserireProprietario extends JFrame {
	
	Persone persone;
	AccedereProprietari AP;
	
	public InserireProprietario(AccedereProprietari ap,Persone p) {
		persone=p;
		AP=ap;
		initComponents();
		this.setVisible(true);
	}
	
	
	public InserireProprietario() {
		initComponents();
	}
	
	public void aggiornaPersone(Persone p)
	{
		persone=p;
		
		DefaultComboBoxModel x=new DefaultComboBoxModel();
		Iterator<Persona> i= persone.getPersone().iterator();
		while(i.hasNext())
		{
			if(i.next() instanceof PersonaFisica)
			x.addElement(((PersonaFisica)i.next()).getDati().getNome()+((PersonaFisica)i.next()).getDati().getCognome());
			else if(i.next() instanceof PersonaGiuridica)
				x.addElement(((PersonaGiuridica)i.next()).getDati().getpIva());
		}
		
		persona.setModel(x);
		
	}
	

	private static final long serialVersionUID = 1L;
	private JComboBox persona;
	private JTextField quota;
	private JButton bInserisci;
	private JButton bAnnulla;
	private JButton bInserisciNuovaPersona;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";


	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBInserisci(), new Constraints(new Leading(39, 10, 10), new Leading(136, 12, 12)));
		add(getBInserisciNuovaPersona(), new Constraints(new Leading(318, 10, 10), new Leading(136, 12, 12)));
		add(getBAnnulla(), new Constraints(new Leading(185, 10, 10), new Leading(136, 12, 12)));
		add(getQuota(), new Constraints(new Leading(326, 98, 10, 10), new Leading(52, 21, 10, 10)));
		add(getPersona(), new Constraints(new Leading(43, 201, 10, 10), new Leading(51, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(326, 12, 12), new Leading(20, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(43, 179, 12, 12), new Leading(20, 12, 12)));
		setSize(602, 197);
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Quota");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Proprietario");
		}
		return jLabel0;
	}

	private JComboBox getPersona() {
		if (persona == null) {
			persona = new JComboBox();
			
			if(persone!=null)
			{
				DefaultComboBoxModel x=new DefaultComboBoxModel();
				Iterator<Persona> i= persone.getPersone().iterator();
				while(i.hasNext())
				{
					if(i.next() instanceof PersonaFisica)
					x.addElement(((PersonaFisica)i.next()).getDati().getNome()+((PersonaFisica)i.next()).getDati().getCognome());
					else if(i.next() instanceof PersonaGiuridica)
						x.addElement(((PersonaGiuridica)i.next()).getDati().getpIva());
				}
				
				persona.setModel(x);
			}
			persona.setDoubleBuffered(false);
			persona.setBorder(null);
		}
		return persona;
	}

	private JButton getBInserisciNuovaPersona() {
		if (bInserisciNuovaPersona == null) {
			bInserisciNuovaPersona = new JButton();
			bInserisciNuovaPersona.setText("Inserisci nuova persona");
		}
		return bInserisciNuovaPersona;
	}

	private JButton getBAnnulla() {
		if (bAnnulla == null) {
			bAnnulla = new JButton();
			bAnnulla.setText("Annulla");
		}
		return bAnnulla;
	}

	private JButton getBInserisci() {
		if (bInserisci == null) {
			bInserisci = new JButton();
			bInserisci.setText("Inserisci");
		}
		return bInserisci;
	}

	private JTextField getQuota() {
		if (quota == null) {
			quota = new JTextField();
		}
		return quota;
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
				InserireProprietario frame = new InserireProprietario();
				frame.setTitle("InserireProprietario");
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
