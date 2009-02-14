//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
import datatype.DatiUnitaImmobiliare;
import enumeration.CategoriaCatastale;
import enumeration.DestinazioneUso;
import executor.GestoreCondomini;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class InserireUnitaImmobiliare extends JFrame{

	private ConfermaUnitaImmobiliari CUI;
	
	public InserireUnitaImmobiliare(ConfermaUnitaImmobiliari conf)
	{
		this.CUI=conf;
		initComponents();
		setLocationRelativeTo(null);
	}
	
	public void inserisciDatiUnitaUImmobiliare(DatiUnitaImmobiliare dati){
		CUI.inserisciDatiUnitaUImmobiliare(dati);
	}
	
	public void ammissibile(Boolean b) {
		if (b){
			CUI.creaAccedereProprietari();
		}
		else {
			JOptionPane.showMessageDialog(this, "Unita immobiliare gia inserita");
			CUI.setVisible(true);
		} 	
		this.dispose();
	}

	public void annulla() {
		CUI.setVisible(true);
		dispose();
	}

	public void fallito() {
		// TODO Auto-generated method stub implements AccedentiPersone 
		
	}

	public void fatto() {
				
	}

	public void finito() {
		// TODO Auto-generated method stub
		
	}

	public void ko() {
		GestoreCondomini.getInstance().procedi(false);
	
	}

	public void ok() {
		GestoreCondomini.getInstance().procedi(true);
	}

	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
	}

	protected void confermaMouseMouseClicked(MouseEvent event) {
		try
		{
			if (getId().getText().equals("") || getPosizioneInterna().getText().equals(""))
				JOptionPane.showMessageDialog(this, "Inserire ID unità  e posizione interna validi");
			else inserisciDatiUnitaUImmobiliare(new DatiUnitaImmobiliare(getId().getText(), (CategoriaCatastale)getCategoria().getSelectedItem(), getPosizioneInterna().getText(), Float.parseFloat(getMetratura().getText()), (DestinazioneUso)getDestinazione().getSelectedItem()));
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(this, "Formato metratura errato! Utilizzare solo cifre \n Utilizzare il formato XXX.YY ");
		}
	
	}
	
	private void annullaMouseMouseClicked(MouseEvent event) {
		annulla();
	}
	
	private static final long serialVersionUID = 1L;
	private JLabel labelId=new JLabel("Id Unita'");
	private JLabel labelCategoria=new JLabel("Cat. Catastale");
	private JLabel labelPosizioneInterna=new JLabel("Posizione Interna");
	private JLabel labelMetratura=new JLabel("Metratura");
	private JLabel labelDestinazione=new JLabel("Destinazione d'uso");
	private JTextField id;
	private JComboBox destinazione;
	private JTextField metratura;
	private JTextField posizioneInterna;
	private JButton bConferma;
	private JButton bAnnulla;
	private JComboBox categoria;

	private void initComponents() {
		setTitle("Inserire Unita' Immobiliare");
		setLayout(new GroupLayout());
		add(labelId,  new Constraints(new Leading(16, 116, 10, 10), new Leading(20, 23, 12, 12)));
		add(labelCategoria,  new Constraints(new Leading(149, 120, 10, 10), new Leading(20, 23, 12, 12)));
		add(labelDestinazione,  new Constraints(new Leading(280, 180, 12, 12), new Leading(20, 23, 12, 12)));
		add(labelMetratura,  new Constraints(new Leading(480, 100, 10, 10), new Leading(20, 23, 12, 12)));
		add(labelPosizioneInterna,  new Constraints(new Leading(600, 160, 10, 10), new Leading(20, 23, 12, 12)));
		
		
		add(getBConferma(), new Constraints(new Leading(200, 10, 10), new Leading(93, 10, 10)));
		add(getBAnnulla(), new Constraints(new Leading(400, 10, 10), new Leading(93, 12, 12)));
		add(getCategoria(), new Constraints(new Leading(149, 120, 10, 10), new Leading(50, 22, 12, 12)));
		add(getId(), new Constraints(new Leading(16, 116, 10, 10), new Leading(50, 23, 12, 12)));
		add(getDestinazione(), new Constraints(new Leading(280, 180, 12, 12), new Leading(50,22, 12, 12)));
		add(getMetratura(), new Constraints(new Leading(480, 100, 10, 10), new Leading(50, 22, 12, 12)));
		add(getPosizioneInterna(), new Constraints(new Leading(600, 160, 10, 10), new Leading(50, 23, 12, 12)));
		this.setSize(792, 182);
		setVisible(true);
		this.addWindowListener(new WindowAdapter() {  
	   		 @Override  
	   		 public void windowClosing(WindowEvent we) {  
	   			CUI.setVisible(true);
	   			dispose();
	   		 	}  
	   		 }); 
		
		
	}

	private JComboBox getCategoria() {
		if (categoria == null) {
			categoria = new JComboBox();
			categoria.setModel(new DefaultComboBoxModel( CategoriaCatastale.values() ) );
			categoria.setDoubleBuffered(false);
			categoria.setBorder(null);
		}
		return categoria;
	}

	private JButton getBAnnulla() {
		if (bAnnulla == null) {
			bAnnulla = new JButton();
			bAnnulla.setText("Annulla");
			
			bAnnulla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					annullaMouseMouseClicked(event);
				}
			});
		}
		return bAnnulla;
	}

	private JButton getBConferma() {
		if (bConferma == null) {
			bConferma = new JButton();
			bConferma.setText("Conferma");
			
			bConferma.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					confermaMouseMouseClicked(event);
				}
			});
		}
		return bConferma;
	}

	private JTextField getPosizioneInterna() {
		if (posizioneInterna == null) {
			posizioneInterna = new JTextField();
		}
		return posizioneInterna;
	}

	private JTextField getMetratura() {
		if (metratura == null) {
			metratura = new JTextField();
		}
		return metratura;
	}

	private JComboBox getDestinazione() {
		if (destinazione == null) {
			destinazione = new JComboBox();
			destinazione.setModel(new DefaultComboBoxModel( DestinazioneUso.values() ) );
			destinazione.setDoubleBuffered(false);
			destinazione.setBorder(null);
		}
		return destinazione;
	}

	private JTextField getId() {
		if (id == null) {
			id = new JTextField();
		}
		return id;
	}

	

}


