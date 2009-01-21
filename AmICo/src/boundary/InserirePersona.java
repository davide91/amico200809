//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Persona;
import datatype.DatiCorretti;
import datatype.DatiErrati;
import datatype.DatiPersona;
import datatype.EsitoControlloDati;
import datatype.EsitoControlloDatiPersona;
import datatype.list.Persone;
import executor.GestorePersone;

/**
 * @author Federico
 *
 */
public class InserirePersona extends JFrame implements BaseBoundary{

	private GestorePersone GP;
	
	public void creaInserirePersona(){
		this.GP=GestorePersone.getInstance();
		//AMM.richiediDatiPersona()
	}
	
	
	public void ok() {
		GP.procedi(true);
	}
	
	

	public void ko() {
		GP.procedi(false);
		//AMM.mostra(PersonaInseritaKO);
	}
	
	public void inserisciDatiPersona(DatiPersona datiP) {
		EsitoControlloDati esito= datiP.controlla();
		 if (esito instanceof DatiErrati) {
			//AMM.mostra(esito);
			 System.out.println("");
		 if(esito instanceof DatiCorretti) {
			GP.inserisciDatiPersona(datiP);
		 }
		 
		}
	}
	
	public void ammissibile(Boolean b) {
		
	}

	public void ammissibile(EsitoControlloDatiPersona personaGiaInserita) {
		//AMM.richiediConferma(controlloDati);
		
		
	}
	public void  annulla() {
		GP.annullato();
		
	}

	public void fatto() {
		//Amm.mostra(PersonaInseritaOK);
		
	}

	public void fallito() {
		// TODO Auto-generated method stub
		
	}

	public void finito() {
		// TODO Auto-generated method stub
		
	}

	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	public void aggiornaPersone(Persone persone) {
		// TODO Auto-generated method stub
		
	}
	
	private static final long serialVersionUID = 1L;
	private JTextField nome;
	private JTextField cognome;
	private JTextField domicilio;
	private JTextField codiceFiscale;
	private JTextField telefono;
	private JRadioButton radioPF;
	private JRadioButton radioPG;
	private JComboBox comune;
	private JComboBox provincia;
	private JCheckBox comS;
	private JCheckBox comM;
	private JCheckBox comT;
	private JCheckBox comP;
	private JCheckBox comF;
	private JCheckBox verbaliP;
	private JCheckBox verbaliS;
	private JCheckBox verbaliM;
	private JCheckBox verbaliT;
	private JCheckBox verbaliF;
	private JCheckBox convP;
	private JCheckBox convS;
	private JCheckBox convM;
	private JCheckBox convT;
	private JCheckBox convF;
	private JCheckBox avvisiP;
	private JCheckBox avvisiS;
	private JCheckBox avvisiM;
	private JCheckBox avvisiT;
	private JCheckBox avvisiF;
	private JTextField jTextField0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	public InserirePersona() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getRadioPF(), new Constraints(new Leading(479, 10, 10), new Leading(12, 8, 8)));
		add(getRadioPG(), new Constraints(new Leading(479, 8, 8), new Leading(38, 8, 8)));
		add(getNome(), new Constraints(new Leading(108, 110, 12, 12), new Leading(44, 12, 12)));
		add(getComS(), new Constraints(new Leading(307, 10, 10), new Leading(342, 10, 10)));
		add(getComM(), new Constraints(new Leading(362, 10, 10), new Leading(342, 8, 8)));
		add(getComT(), new Constraints(new Leading(413, 10, 10), new Leading(342, 8, 8)));
		add(getComP(), new Constraints(new Leading(256, 10, 10), new Leading(342, 8, 8)));
		add(getComF(), new Constraints(new Leading(464, 8, 8), new Leading(342, 8, 8)));
		add(getVerbaliP(), new Constraints(new Leading(256, 8, 8), new Leading(366, 8, 8)));
		add(getVerbaliS(), new Constraints(new Leading(307, 8, 8), new Leading(366, 8, 8)));
		add(getVerbaliM(), new Constraints(new Leading(362, 8, 8), new Leading(366, 8, 8)));
		add(getVerbaliT(), new Constraints(new Leading(413, 8, 8), new Leading(366, 8, 8)));
		add(getVerbaliF(), new Constraints(new Leading(464, 8, 8), new Leading(366, 8, 8)));
		add(getConvP(), new Constraints(new Leading(256, 8, 8), new Leading(392, 8, 8)));
		add(getConvS(), new Constraints(new Leading(307, 8, 8), new Leading(392, 8, 8)));
		add(getConvM(), new Constraints(new Leading(362, 8, 8), new Leading(392, 8, 8)));
		add(getConvT(), new Constraints(new Leading(413, 8, 8), new Leading(392, 8, 8)));
		add(getConvF(), new Constraints(new Leading(464, 8, 8), new Leading(392, 8, 8)));
		add(getAvvisiP(), new Constraints(new Leading(256, 8, 8), new Leading(417, 8, 8)));
		add(getAvvisiS(), new Constraints(new Leading(307, 8, 8), new Leading(417, 8, 8)));
		add(getAvvisiM(), new Constraints(new Leading(362, 8, 8), new Leading(417, 8, 8)));
		add(getAvvisiT(), new Constraints(new Leading(413, 8, 8), new Leading(417, 8, 8)));
		add(getAvvisiF(), new Constraints(new Leading(464, 8, 8), new Leading(417, 8, 8)));
		add(getCognome(), new Constraints(new Leading(108, 110, 12, 12), new Leading(14, 12, 12)));
		add(getDomicilio(), new Constraints(new Leading(108, 110, 12, 12), new Leading(104, 12, 12)));
		add(getComune(), new Constraints(new Leading(108, 110, 12, 12), new Leading(132, 12, 12)));
		add(getProvincia(), new Constraints(new Leading(313, 104, 10, 10), new Leading(134, 12, 12)));
		add(getCodiceFiscale(), new Constraints(new Leading(108, 110, 12, 12), new Leading(72, 10, 10)));
		add(getTelefono(), new Constraints(new Leading(108, 309, 12, 12), new Leading(175, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(108, 309, 12, 12), new Leading(211, 12, 12)));
		setSize(672, 647);
	}


	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		}
		return jTextField0;
	}


	private JTextField getTelefono() {
		if (telefono == null) {
			telefono = new JTextField();
		}
		return telefono;
	}


	private JComboBox getProvincia() {
		if (provincia == null) {
			provincia = new JComboBox();
			provincia.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			provincia.setDoubleBuffered(false);
			provincia.setBorder(null);
		}
		return provincia;
	}


	private JTextField getDomicilio() {
		if (domicilio == null) {
			domicilio = new JTextField();
		}
		return domicilio;
	}


	private JTextField getCognome() {
		if (cognome == null) {
			cognome = new JTextField();
		}
		return cognome;
	}


	private JCheckBox getAvvisiF() {
		if (avvisiF == null) {
			avvisiF = new JCheckBox();
		}
		return avvisiF;
	}


	private JCheckBox getAvvisiT() {
		if (avvisiT == null) {
			avvisiT = new JCheckBox();
		}
		return avvisiT;
	}


	private JCheckBox getAvvisiM() {
		if (avvisiM == null) {
			avvisiM = new JCheckBox();
		}
		return avvisiM;
	}


	private JCheckBox getAvvisiS() {
		if (avvisiS == null) {
			avvisiS = new JCheckBox();
		}
		return avvisiS;
	}


	private JCheckBox getAvvisiP() {
		if (avvisiP == null) {
			avvisiP = new JCheckBox();
		}
		return avvisiP;
	}


	private JCheckBox getConvF() {
		if (convF == null) {
			convF = new JCheckBox();
		}
		return convF;
	}


	private JCheckBox getConvT() {
		if (convT == null) {
			convT = new JCheckBox();
		}
		return convT;
	}


	private JCheckBox getConvM() {
		if (convM == null) {
			convM = new JCheckBox();
		}
		return convM;
	}


	private JCheckBox getConvS() {
		if (convS == null) {
			convS = new JCheckBox();
		}
		return convS;
	}


	private JCheckBox getConvP() {
		if (convP == null) {
			convP = new JCheckBox();
		}
		return convP;
	}


	private JCheckBox getVerbaliF() {
		if (verbaliF == null) {
			verbaliF = new JCheckBox();
		}
		return verbaliF;
	}


	private JCheckBox getVerbaliT() {
		if (verbaliT == null) {
			verbaliT = new JCheckBox();
		}
		return verbaliT;
	}


	private JCheckBox getVerbaliM() {
		if (verbaliM == null) {
			verbaliM = new JCheckBox();
		}
		return verbaliM;
	}


	private JCheckBox getVerbaliS() {
		if (verbaliS == null) {
			verbaliS = new JCheckBox();
		}
		return verbaliS;
	}


	private JCheckBox getVerbaliP() {
		if (verbaliP == null) {
			verbaliP = new JCheckBox();
		}
		return verbaliP;
	}


	private JCheckBox getComF() {
		if (comF == null) {
			comF = new JCheckBox();
		}
		return comF;
	}


	private JCheckBox getComP() {
		if (comP == null) {
			comP = new JCheckBox();
		}
		return comP;
	}


	private JCheckBox getComT() {
		if (comT == null) {
			comT = new JCheckBox();
		}
		return comT;
	}


	private JCheckBox getComM() {
		if (comM == null) {
			comM = new JCheckBox();
		}
		return comM;
	}


	private JCheckBox getComS() {
		if (comS == null) {
			comS = new JCheckBox();
		}
		return comS;
	}


	private JComboBox getComune() {
		if (comune == null) {
			comune = new JComboBox();
			comune.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			comune.setDoubleBuffered(false);
			comune.setBorder(null);
		}
		return comune;
	}


	private JTextField getCodiceFiscale() {
		if (codiceFiscale == null) {
			codiceFiscale = new JTextField();
		}
		return codiceFiscale;
	}


	private JRadioButton getRadioPG() {
		if (radioPG == null) {
			radioPG = new JRadioButton();
			radioPG.setText("Persona Giuridica");
		}
		return radioPG;
	}


	private JRadioButton getRadioPF() {
		if (radioPF == null) {
			radioPF = new JRadioButton();
			radioPF.setText("Persona Fisica");
		}
		return radioPF;
	}


	private JTextField getNome() {
		if (nome == null) {
			nome = new JTextField();
		}
		return nome;
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
				InserirePersona frame = new InserirePersona();
				frame.setTitle("InserirePersona");
			//	frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
