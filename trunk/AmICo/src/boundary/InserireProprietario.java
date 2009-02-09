//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import datatype.list.Persone;

/**
 * @author Federico
 *
 */
public class InserireProprietario extends JFrame {
	
	private Persone persone;
	private AccedereProprietari AP;
	private AccedereProprietari2 AP2;
	private boolean click;
	
	public InserireProprietario(AccedereProprietari ap,Persone p) {
		persone=p;
		AP=ap;
		AP2=null;
		click=true;
		initComponents();
		this.setVisible(true);
		this.setTitle("Inserire proprietario");
	}
	
	
	public InserireProprietario(AccedereProprietari2 ap,Persone p) {
		persone=p;
		AP2=ap;
		AP=null;
		click=true;
		initComponents();
		this.setVisible(true);
		this.setTitle("Inserire proprietario");
	}
	
	public void aggiornaPersone(Persone per)
	{
		persone=per;
		click=true;
		
		DefaultComboBoxModel x=new DefaultComboBoxModel();

		for (Persona p : persone.getPersone()) {
			if(p instanceof PersonaFisica)
				x.addElement(((PersonaFisica)p).getDati().getNome()+" "+((PersonaFisica)p).getDati().getCognome());
				else if(p instanceof PersonaGiuridica)
					x.addElement(((PersonaGiuridica)p).getDati().getpIva().getPartIva());
		}
		persona.setModel(x);
	}
	

	private void bInserisciMouseMouseClicked(MouseEvent event) {
		if(click)
		{
			try
			{
				if (Float.parseFloat(quota.getText()) <=100 && Float.parseFloat(quota.getText()) > 0)
				{
					if(AP!=null)
						AP.aggiornaTabella(persone.getPersone().get(persona.getSelectedIndex()),Float.parseFloat(quota.getText()));
					if(AP2!=null)
						AP2.aggiornaTabella(persone.getPersone().get(persona.getSelectedIndex()),Float.parseFloat(quota.getText()));
						
					this.dispose();
				}		
				else JOptionPane.showMessageDialog(this, "La quota deve essere compresa tra 1 e 100");
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(this, "Formato quote di possesso errato!\n Utilizzare solo cifre ");
			}
		}
	}


	private void bAnnullaMouseMouseClicked(MouseEvent event) {
		if(click)
		{
			if(AP!=null)
				AP.aggiornaTabella(null,0);
			if(AP2!=null)
				AP2.aggiornaTabella(null,0);
			this.dispose();
		}
	}


	private void bInserisciNuovaPersonaMouseMouseClicked(MouseEvent event) {
		if(click)
		{
			click=false;
			if(AP!=null)
				AP.inserisciNuovaPersona();
			if(AP2!=null)
				AP2.inserisciNuovaPersona();
		}
	}

	private static final long serialVersionUID = 1L;
	private JComboBox persona;
	private JTextField quota;
	private JButton bInserisci;
	private JButton bAnnulla;
	private JButton bInserisciNuovaPersona;
	private JLabel jLabel0;
	private JLabel jLabel1;
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
				
				for (Persona p : persone.getPersone()) {
					if(p instanceof PersonaFisica)
						x.addElement(((PersonaFisica)p).getDati().getNome()+" "+((PersonaFisica)p).getDati().getCognome());
						else if(p instanceof PersonaGiuridica)
							x.addElement(((PersonaGiuridica)p).getDati().getpIva().getPartIva());
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
			bInserisciNuovaPersona.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bInserisciNuovaPersonaMouseMouseClicked(event);
				}
			});
		}
		return bInserisciNuovaPersona;
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


	private JButton getBInserisci() {
		if (bInserisci == null) {
			bInserisci = new JButton();
			bInserisci.setText("Inserisci");
			bInserisci.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bInserisciMouseMouseClicked(event);
				}
			});
		}
		return bInserisci;
	}


	private JTextField getQuota() {
		if (quota == null) {
			quota = new JTextField();
		}
		return quota;
	}



}
