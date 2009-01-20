//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import datatype.DatiCondominio;
import datatype.list.Reali;
import datatype.list.UnitaImmobiliari;
import enumeration.Comune;
import enumeration.Provincia;
import enumeration.StatiInserireNuovoCondominio;
import executor.GestoreCondomini;



public class InserireNuovoCondominio extends JFrame implements BaseBoundary {
	
	
	private UnitaImmobiliari unitaImmobiliari;
	private DatiCondominio datiCondominio;
	private Reali tabellaGenerale;
	private StatiInserireNuovoCondominio state;
	
	public void creaInserireNuovoCondominio(){
		//AMM.richiediDatiComdominio();
		state=StatiInserireNuovoCondominio.base;
		
	}
	
	public void inserisciDatiCondominio(DatiCondominio datiCondominio){
		GestoreCondomini.getInstance().passaDatiCondominio(datiCondominio);
		state=StatiInserireNuovoCondominio.controlloDatiCondominio;
	}

	
	public void inserisciUnitaImmobiliare(){
		GestoreCondomini.getInstance().inserisciUnitaImmobiliare();
		state= StatiInserireNuovoCondominio.inserimentoUnitaImmobiliare;
	}
	
	
	public void inserisciTabellaMillesimaleProprieta(Reali millesimi) {
//		GestoreCondomini.getInstance().passaTabellaMillesimaleProprieta(millesimi);
		state=StatiInserireNuovoCondominio.controlloTabellaMillesimaleProprieta;
		
	}
	
	
	public void aggiornaUnitaImmobiliari(UnitaImmobiliari unitaImmobiliari) {
		this.unitaImmobiliari=unitaImmobiliari;
		//AMM.mostraUnitaImmobiliari(unitaImmobiliari);
	}
	
	
	public void ammissibile(Boolean b) {
		switch (state) {
		case controlloDatiCondominio:
			
			if (b)
				//AMM.richiediConferma();
				state=StatiInserireNuovoCondominio.attesaConfermaDatiCondominio;
			else
				state=StatiInserireNuovoCondominio.base;
				//AMM.mostra(CondominioInseritoKO):
			break;
		case controlloTabellaMillesimaleProprieta:

			if (b)
				//AMM.richiediConferma();
				state=StatiInserireNuovoCondominio.attesaConfermaTabellaMillesimale;
			else
				state=StatiInserireNuovoCondominio.base;
				//AMM.mostra(TabellaMillesimaleInseritaKO):
			break;
		default:
			break;
		}
		
	}

	public void annulla() {
		GestoreCondomini.getInstance().operazioneAnnullata();
		
	}

	public void fallito() {
		// TODO Auto-generated method stub
		
	}

	public void fatto() {
		state=StatiInserireNuovoCondominio.inserimentoUnitaImmobiliari;
		
	}

	public void finito() {
		state = StatiInserireNuovoCondominio.base;
		
	}

	public void ko() {
		if (state==StatiInserireNuovoCondominio.attesaConfermaTabellaMillesimale)
		{	//AMM.mostra(condominioInseritoKO);
		}
		GestoreCondomini.getInstance().procedi(false);
		state=StatiInserireNuovoCondominio.base;
			
		
		
	}

	public void ok() {
		switch (state) {
		case attesaConfermaDatiCondominio:
			GestoreCondomini.getInstance().procedi(true);
			state= StatiInserireNuovoCondominio.inserimentoUnitaImmobiliari;
			break;
		case attesaConfermaTabellaMillesimale:
			GestoreCondomini.getInstance().procedi(true);
			//AMM.mostra(CondominioInseritoOK);
			break;
		default:
			break;
		}

		
	}
	
	private void inserisciMouseMouseClicked(MouseEvent event) {
	}

	private void annullaMouseMouseClicked(MouseEvent event) {
		//this.annulla();
		this.dispose();
	}


	private static final long serialVersionUID = 1L;
	private JTextField via;
	private JComboBox comune;
	private JTextField cap;
	private JComboBox provincia;
	private JMenuItem jMenuItem0;
	private JMenu file;
	private JMenuBar jMenuBar0;
	private JLabel scrittavia;
	private JButton inserisci;
	private JLabel scrittaprovincia;
	private JLabel scrittacomune;
	private JLabel scrittacap;
	private JButton annulla;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public InserireNuovoCondominio() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getVia(), new Constraints(new Leading(96, 117, 10, 10), new Leading(10, 12, 12)));
		add(getInserisci(), new Constraints(new Leading(100, 10, 10), new Leading(121, 10, 10)));
		add(getAnnulla(), new Constraints(new Leading(315, 10, 10), new Leading(123, 10, 10)));
		add(getScrittavia(), new Constraints(new Leading(18, 29, 12, 12), new Leading(14, 12, 12)));
		add(getProvincia(), new Constraints(new Leading(97, 116, 12, 12), new Leading(34, 22, 12, 12)));
		add(getComune(), new Constraints(new Leading(310, 116, 12, 12), new Leading(33, 12, 12)));
		add(getScrittaprovincia(), new Constraints(new Leading(12, 12, 12), new Leading(40, 16, 12, 12)));
		add(getCap(), new Constraints(new Leading(97, 114, 12, 12), new Leading(65, 12, 12)));
		add(getScrittacap(), new Constraints(new Leading(18, 12, 12), new Leading(67, 12, 12)));
		add(getScrittacomune(), new Constraints(new Leading(245, 10, 10), new Leading(43, 13, 12, 12)));
		setJMenuBar(getJMenuBar0());
		setSize(480, 230);
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
			scrittacap.setText("CAP:");
		}
		return scrittacap;
	}

	private JLabel getScrittacomune() {
		if (scrittacomune == null) {
			scrittacomune = new JLabel();
			scrittacomune.setText("Comune:");
		}
		return scrittacomune;
	}

	private JLabel getScrittaprovincia() {
		if (scrittaprovincia == null) {
			scrittaprovincia = new JLabel();
			scrittaprovincia.setText("Provincia:");
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
			scrittavia.setText("Via:");
		}
		return scrittavia;
	}

	private JMenuBar getJMenuBar0() {
		if (jMenuBar0 == null) {
			jMenuBar0 = new JMenuBar();
			jMenuBar0.add(getFile());
		}
		return jMenuBar0;
	}

	private JMenu getFile() {
		if (file == null) {
			file = new JMenu();
			file.setText("File");
			file.setOpaque(false);
			file.add(getJMenuItem0());
		}
		return file;
	}

	private JMenuItem getJMenuItem0() {
		if (jMenuItem0 == null) {
			jMenuItem0 = new JMenuItem();
			jMenuItem0.setText("esci");
		}
		return jMenuItem0;
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

	private JComboBox getComune() {
		if (comune == null) {
			comune = new JComboBox();
			comune.setModel(new DefaultComboBoxModel( Comune.values() ) );
			comune.setDoubleBuffered(false);
			comune.setBorder(null);
		}
		return comune;
	}

	private JTextField getVia() {
		if (via == null) {
			via = new JTextField();
		}
		return via;
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
				InserireNuovoCondominio frame = new InserireNuovoCondominio();
				frame.setTitle("Inserimento Dati Condominio");
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}


}