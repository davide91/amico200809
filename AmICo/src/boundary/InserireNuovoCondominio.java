//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import datatype.DatiCondominio;
import datatype.DatiTabellaMillesimale;
import datatype.Indirizzo;
import datatype.list.Millesimi;
import datatype.list.Percentuali;
import datatype.list.UnitaImmobiliari;
import enumeration.Provincia;
import enumeration.StatiInserireNuovoCondominio;
import executor.GestoreCondomini;



public class InserireNuovoCondominio extends JFrame implements BaseBoundary {
	@SuppressWarnings("unused")
	private UnitaImmobiliari unitaImmobiliari;
	private DatiCondominio datiCondominio = new DatiCondominio();
	@SuppressWarnings("unused")
	private Percentuali tabellaGenerale;
	private StatiInserireNuovoCondominio state;
	private int c;
	
	public InserireNuovoCondominio() {
		state=StatiInserireNuovoCondominio.base;
		initComponents();
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	public void inserisciDatiCondominio(DatiCondominio datiCondominio){
		state=StatiInserireNuovoCondominio.controlloDatiCondominio;
		GestoreCondomini.getInstance().passaDatiCondominio(datiCondominio);
		
	}

	
	public void inserisciUnitaImmobiliare(){
		state = StatiInserireNuovoCondominio.inserimentoUnitaImmobiliare;
		GestoreCondomini.getInstance().inserisciUnitaImmobiliare();
	}
	
	
	public void inserisciTabellaMillesimaleProprieta(DatiTabellaMillesimale dati, Millesimi millesimi) {
		GestoreCondomini.getInstance().passaTabellaMillesimaleProprieta(dati,millesimi); 
		state=StatiInserireNuovoCondominio.controlloTabellaMillesimaleProprieta;
	}
	
	
	public void ammissibile(Boolean b) {
		switch (state) {
		case controlloDatiCondominio:
			if (b)
			{
				state=StatiInserireNuovoCondominio.attesaConfermaDatiCondominio;
				
				c = JOptionPane.showConfirmDialog(this, "Sei sicuro?\nSe si conferma i dati verranno inseriti nel sistema.", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if (c==0){
					state=StatiInserireNuovoCondominio.inserimentoUnitaImmobiliari;
					GestoreCondomini.getInstance().procedi(true);
				}
				else {
					state=StatiInserireNuovoCondominio.base;
					GestoreCondomini.getInstance().procedi(false);	
				}
			}
			else
			{
				state=StatiInserireNuovoCondominio.base;	
				JOptionPane.showMessageDialog(this, "Il condominio che si vuole inserire e' gia' presente");
			}
			break;
		case controlloTabellaMillesimaleProprieta:

			if (b)
			{
				state=StatiInserireNuovoCondominio.attesaConfermaTabellaMillesimale;
				c = JOptionPane.showConfirmDialog(this, "Sei sicuro?\nSe si conferma i dati verranno inseriti nel sistema.", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
			else
			{
				state=StatiInserireNuovoCondominio.base;
				JOptionPane.showMessageDialog(this, "ATTENZIONE :\nI dati inseriti non sono validi.");
			}
			break;
			
		
		default:
			break;
		}
	}

	public void annulla() {
		GestoreCondomini.getInstance().operazioneAnnullata();
		this.dispose();
		
	}

	public void fallito() {
		// TODO Auto-generated method stub
	}

	public void fatto() {
		state=StatiInserireNuovoCondominio.inserimentoUnitaImmobiliari;
		dispose();
	}

	public void finito() {
		state = StatiInserireNuovoCondominio.inserimentoTabellaMillesimale;
	
		GestoreCondomini.getInstance().operazioneTerminata();
	}

	public void ko() {
		if (state==StatiInserireNuovoCondominio.attesaConfermaTabellaMillesimale)
			JOptionPane.showMessageDialog(this, "Il condominio non e' stato inserito.");

		GestoreCondomini.getInstance().procedi(false);
		state=StatiInserireNuovoCondominio.base;
			
	}

	public void ok() {
		switch (state) {
		case attesaConfermaDatiCondominio:
			state= StatiInserireNuovoCondominio.inserimentoUnitaImmobiliari;
			GestoreCondomini.getInstance().procedi(true);
			
			break;
		case attesaConfermaTabellaMillesimale:
			GestoreCondomini.getInstance().procedi(true);
			JOptionPane.showMessageDialog(this, "Condominio inserito correttamente");
			break;
		default:
			break;
		}
	}

	private void inserisciMouseMouseClicked(MouseEvent event)
	{
		try {
		if(via.getText().equals(""))
			JOptionPane.showMessageDialog(this,"Inserimento Via obbligatorio");
		else if(comune.getText().equals(""))
			JOptionPane.showMessageDialog(this, "Inserimento Comune obbligatorio");

		else 
			if(cap.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Inserimento CAP obbligatorio");
			else if (cap.getText().length()!=5 )
				JOptionPane.showMessageDialog(this, "Il CAP inserito non e' valido");
			
		else
		{
			Integer.parseInt(cap.getText());
			Indirizzo indirizzo=new Indirizzo();
			indirizzo.setCap(cap.getText());
			indirizzo.setComune(comune.getText());
			indirizzo.setProvincia((Provincia)provincia.getSelectedItem());
			indirizzo.setVia(via.getText());
			indirizzo.setInterno(null);
			datiCondominio.setId(via.getText()+" "+cap.getText());
			datiCondominio.setIndirizzo(indirizzo);
			this.inserisciDatiCondominio(datiCondominio);
		}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Il CAP inserito non e' valido");
		}
	}

	private void annullaMouseMouseClicked(MouseEvent event) {
		this.annulla();
		this.dispose();
	}


	private static final long serialVersionUID = 1L;
	private JTextField via;
	private JTextField comune;
	private JTextField cap;
	private JComboBox provincia;
	private JLabel scrittavia;
	private JButton inserisci;
	private JLabel scrittaprovincia;
	private JLabel scrittacomune;
	private JLabel scrittacap;
	private JButton annulla;
	private JSeparator jSeparator0;


	private void initComponents() {
		setTitle("Inserire Nuovo Condominio");
		setMinimumSize(new Dimension(300, 200));
		setPreferredSize(new Dimension(300, 200));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getScrittavia(), new Constraints(new Leading(12, 29, 12, 12), new Leading(12, 12, 12)));
		add(getScrittacap(), new Constraints(new Leading(12, 12, 12), new Leading(62, 18, 12, 12)));
		add(getCap(), new Constraints(new Leading(97, 67, 12, 12), new Leading(62, 12, 12)));
		add(getScrittaprovincia(), new Constraints(new Leading(12, 12, 12), new Leading(32, 22, 12, 12)));
		add(getInserisci(), new Constraints(new Leading(284, 10, 10), new Trailing(12, 67, 67)));
		add(getAnnulla(), new Constraints(new Leading(382, 12, 12), new Trailing(12, 67, 67)));
		add(getJSeparator0(), new Constraints(new Bilateral(12, 12, 452), new Trailing(42, 7, 67, 67)));
		add(getVia(), new Constraints(new Bilateral(97, 12, 4), new Leading(10, 12, 12)));
		add(getComune(), new Constraints(new Bilateral(306, 12, 147), new Leading(31, 55, 61)));
		add(getScrittacomune(), new Constraints(new Leading(243, 171, 171), new Leading(31, 22, 55, 61)));
		add(getProvincia(), new Constraints(new Leading(97, 134, 165, 165), new Leading(32, 22, 12, 12)));
		setSize(492, 275);
		this.addWindowListener(new WindowAdapter() {  
			 public void windowClosing(WindowEvent we) {  
				 
				 chiudiFinestra();
				 
			 	}  
			 });  
	}

	private void chiudiFinestra()
	{
		this.annulla();
		this.dispose(); 
	}

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}


	private JButton getAnnulla() {
		if (annulla == null) {
			annulla = new JButton();
			annulla.setText("Annulla");
			annulla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					annullaMouseMouseClicked(event);
				}
			});
		}
		return annulla;
	}

	private JLabel getScrittacap() {
		if (scrittacap == null) {
			scrittacap = new JLabel();
			scrittacap.setText("CAP");
		}
		return scrittacap;
	}


	private JLabel getScrittacomune() {
		if (scrittacomune == null) {
			scrittacomune = new JLabel();
			scrittacomune.setText("Comune");
		}
		return scrittacomune;
	}


	private JLabel getScrittaprovincia() {
		if (scrittaprovincia == null) {
			scrittaprovincia = new JLabel();
			scrittaprovincia.setText("Provincia");
		}
		return scrittaprovincia;
	}


	private JButton getInserisci() {
		if (inserisci == null) {
			inserisci = new JButton();
			inserisci.setText("Inserisci");
			inserisci.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					inserisciMouseMouseClicked(event);
				}
			});
		}
		return inserisci;
	}

	private JLabel getScrittavia() {
		if (scrittavia == null) {
			scrittavia = new JLabel();
			scrittavia.setText("Via");
		}
		return scrittavia;
	}


	private JComboBox getProvincia() {
		if (provincia == null) {
			provincia = new JComboBox();
			provincia.setModel(new DefaultComboBoxModel(Provincia.values()  ) );
			provincia.setDoubleBuffered(false);
			provincia.setBorder(null);
		}
		return provincia;
	}

	private JTextField getCap() {
		if (cap == null) {
			cap = new JTextField();
		}
		return cap;
	}

	private JTextField getComune() {
		if (comune == null) {
			comune = new JTextField();
		}
		return comune;
	}

	private JTextField getVia() {
		if (via == null) {
			via = new JTextField();
		}
		return via;
	}
}