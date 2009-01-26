//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.TuttePersone;
import store.POJO.Persona;
import store.POJO.UnitaImmobiliare;
import datatype.DatiUnitaImmobiliare;
import datatype.list.Percentuali;
import datatype.list.Persone;
import datatype.list.UnitaImmobiliari;
import enumeration.CategoriaCatastale;
import enumeration.DestinazioneUso;
import enumeration.StatiInserireNuovoCondominio;
import enumeration.StatiInserireUnitaImmobiliari;
import executor.GestoreCondomini;
import executor.GestoreCondominioAperto;
import executor.GestorePersone;

public class InserireUnitaImmobiliare extends JFrame implements AccedentiPersone {

//	private UnitaImmobiliari unitaImmobiliari;
	private Persone persone;
	private StatiInserireUnitaImmobiliari state;
	
	public InserireUnitaImmobiliare(Persone persone) {
		state=StatiInserireUnitaImmobiliari.base;
		this.persone=persone;
		initComponents();
	}
	public void inserisciDatiUnitaUImmobiliare(DatiUnitaImmobiliare dati){
		state=StatiInserireUnitaImmobiliari.attesaConfermaDatiUnitaImmobiliare;
		GestoreCondomini.getInstance().passaDatiUnitaImmobliare(dati);
		
	}
	
	public void inserisciNuovaPersona(){
		state= StatiInserireUnitaImmobiliari.inserimentoNuovaPersona;
		GestorePersone.getInstance().inserisciPersona(this);
		
	}
	
	public void specificaProprietari(Persone persone, Percentuali percentuali){
		if (proprietaOK(persone, percentuali)){
			state=StatiInserireUnitaImmobiliari.attesaConfermaProprieta;
			GestoreCondomini.getInstance().passaProprieta(persone, percentuali);
			//AMM.richiestaConferma();
		}
		else {
			//AMM.mostra(proprietaKO);
		}
	}


	public void ammissibile(Boolean b) {
		if (b){
			state=StatiInserireUnitaImmobiliari.inserimentoProprietari;
			//AMM:mostraPersone(persone);
			
		}
		else {
			state=StatiInserireUnitaImmobiliari.base;
			//AMM.mostra(UnitaImmobiliareGiaInserita);
		} 
			
	}

	public void annulla() {
		// TODO Auto-generated method stub
		
	}

	public void fallito() {
		// TODO Auto-generated method stub
		
	}

	public void fatto() {
		state=StatiInserireUnitaImmobiliari.inserimentoProprietari;
		//AMM:mostraPersone(persone);
	}

	public void finito() {
		// TODO Auto-generated method stub
		
	}

	public void ko() {
		GestoreCondomini.getInstance().procedi(false);
		//AMM.mostra(unitaImmobiliareInseritaKO);
		
	}

	public void ok() {
		GestoreCondomini.getInstance().procedi(true);
		//AMM.mostra(unitaImmobiliareInseritaOK);
		
	}

	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	public void aggiornaPersone(Persone persone) {
		this.persone=persone;
		
	}

	
	
	private static final long serialVersionUID = 1L;
	private JTextField id;
	private JComboBox destinazione;
	private JTextField metratura;
	private JTextField posizioneInterna;
	private JButton bConferma;
	private JButton bAnnulla;
	private JComboBox categoria;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	private void initComponents() {
		setTitle("Inserire Unità Immobiliare");
		setLayout(new GroupLayout());
		add(getBConferma(), new Constraints(new Leading(100, 10, 10), new Leading(93, 10, 10)));
		add(getBAnnulla(), new Constraints(new Leading(326, 10, 10), new Leading(93, 12, 12)));
		add(getCategoria(), new Constraints(new Leading(149, 99, 10, 10), new Leading(36, 22, 12, 12)));
		add(getId(), new Constraints(new Leading(16, 113, 10, 10), new Leading(35, 23, 12, 12)));
		add(getDestinazione(), new Constraints(new Leading(260, 96, 12, 12), new Leading(35, 12, 12)));
		add(getMetratura(), new Constraints(new Leading(374, 79, 10, 10), new Leading(35, 22, 12, 12)));
		add(getPosizioneInterna(), new Constraints(new Leading(462, 79, 10, 10), new Leading(35, 23, 12, 12)));
		setSize(561, 170);
		setVisible(true);
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

	protected void confermaMouseMouseClicked(MouseEvent event) {
		inserisciDatiUnitaUImmobiliare(new DatiUnitaImmobiliare(getId().getText(), (CategoriaCatastale)getCategoria().getSelectedItem(), getPosizioneInterna().getText(), Float.parseFloat(getMetratura().getText()), (DestinazioneUso)getDestinazione().getSelectedItem()));
		
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
				InserireUnitaImmobiliare frame = new InserireUnitaImmobiliare(TuttePersone.getInstance().recuperaPersone());
				frame.setTitle("InserireUnitaImmobiliare");
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	public boolean proprietaOK(Persone persone, Percentuali quote) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}


