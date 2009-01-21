//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
	private JTextField cellulare;
	private JTextField eMail;
	private JTextField fax;
	private JTextField ragioneSociale;
	private JComboBox personaDiRiferimento;
	private JTextField partitaIVA;
	private JTextField indirizzoFiscale;
	private JButton bok;
	private JButton bannulla;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public InserirePersona() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getRadioPF(), new Constraints(new Leading(479, 10, 10), new Leading(12, 8, 8)));
		add(getRadioPG(), new Constraints(new Leading(479, 8, 8), new Leading(38, 8, 8)));
		add(getNome(), new Constraints(new Leading(108, 110, 12, 12), new Leading(44, 12, 12)));
		add(getCognome(), new Constraints(new Leading(108, 110, 12, 12), new Leading(14, 12, 12)));
		add(getDomicilio(), new Constraints(new Leading(108, 110, 12, 12), new Leading(104, 12, 12)));
		add(getComune(), new Constraints(new Leading(108, 110, 12, 12), new Leading(132, 12, 12)));
		add(getProvincia(), new Constraints(new Leading(313, 104, 10, 10), new Leading(134, 12, 12)));
		add(getCodiceFiscale(), new Constraints(new Leading(108, 110, 12, 12), new Leading(72, 10, 10)));
		add(getTelefono(), new Constraints(new Leading(108, 309, 12, 12), new Leading(175, 12, 12)));
		add(getCellulare(), new Constraints(new Leading(108, 309, 12, 12), new Leading(211, 12, 12)));
		add(getEMail(), new Constraints(new Leading(108, 308, 12, 12), new Leading(247, 12, 12)));
		add(getFax(), new Constraints(new Leading(108, 308, 12, 12), new Leading(283, 12, 12)));
		add(getPersonaDiRiferimento(), new Constraints(new Leading(554, 10, 10), new Leading(516, 12, 12)));
		add(getComP(), new Constraints(new Leading(189, 10, 10), new Leading(384, 10, 10)));
		add(getVerbaliP(), new Constraints(new Leading(189, 10, 10), new Leading(408, 10, 10)));
		add(getConvP(), new Constraints(new Leading(189, 10, 10), new Leading(434, 10, 10)));
		add(getAvvisiP(), new Constraints(new Leading(189, 10, 10), new Leading(459, 10, 10)));
		add(getAvvisiS(), new Constraints(new Leading(240, 10, 10), new Leading(459, 10, 10)));
		add(getConvS(), new Constraints(new Leading(240, 10, 10), new Leading(434, 10, 10)));
		add(getVerbaliS(), new Constraints(new Leading(240, 10, 10), new Leading(408, 10, 10)));
		add(getComS(), new Constraints(new Leading(240, 10, 10), new Leading(384, 10, 10)));
		add(getComM(), new Constraints(new Leading(295, 10, 10), new Leading(384, 10, 10)));
		add(getComT(), new Constraints(new Leading(346, 10, 10), new Leading(384, 10, 10)));
		add(getComF(), new Constraints(new Leading(396, 8, 8), new Leading(384, 10, 10)));
		add(getVerbaliF(), new Constraints(new Leading(396, 8, 8), new Leading(408, 10, 10)));
		add(getVerbaliT(), new Constraints(new Leading(346, 10, 10), new Leading(408, 10, 10)));
		add(getVerbaliM(), new Constraints(new Leading(295, 10, 10), new Leading(408, 10, 10)));
		add(getConvM(), new Constraints(new Leading(295, 10, 10), new Leading(434, 10, 10)));
		add(getConvT(), new Constraints(new Leading(346, 10, 10), new Leading(434, 10, 10)));
		add(getConvF(), new Constraints(new Leading(396, 8, 8), new Leading(434, 10, 10)));
		add(getAvvisiF(), new Constraints(new Leading(396, 8, 8), new Leading(459, 10, 10)));
		add(getAvvisiT(), new Constraints(new Leading(346, 10, 10), new Leading(459, 10, 10)));
		add(getAvvisiM(), new Constraints(new Leading(295, 10, 10), new Leading(459, 10, 10)));
		add(getRagioneSociale(), new Constraints(new Leading(108, 309, 12, 12), new Leading(495, 10, 10)));
		add(getPartitaIVA(), new Constraints(new Leading(108, 308, 12, 12), new Leading(529, 10, 10)));
		add(getIndirizzoFiscale(), new Constraints(new Leading(108, 308, 12, 12), new Leading(565, 12, 12)));
		add(getBok(), new Constraints(new Leading(138, 10, 10), new Leading(613, 10, 10)));
		add(getBannulla(), new Constraints(new Leading(432, 10, 10), new Leading(613, 12, 12)));
		setSize(672, 688);
	}


	private JButton getBannulla() {
		if (bannulla == null) {
			bannulla = new JButton();
			bannulla.setText("Annulla");
		}
		return bannulla;
	}


	private JButton getBok() {
		if (bok == null) {
			bok = new JButton();
			bok.setText("OK");
		}
		return bok;
	}


	private JTextField getIndirizzoFiscale() {
		if (indirizzoFiscale == null) {
			indirizzoFiscale = new JTextField();
		}
		return indirizzoFiscale;
	}


	private JTextField getPartitaIVA() {
		if (partitaIVA == null) {
			partitaIVA = new JTextField();
		}
		return partitaIVA;
	}


	private JComboBox getPersonaDiRiferimento() {
		if (personaDiRiferimento == null) {
			personaDiRiferimento = new JComboBox();
			personaDiRiferimento.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			personaDiRiferimento.setDoubleBuffered(false);
			personaDiRiferimento.setBorder(null);
		}
		return personaDiRiferimento;
	}


	private JTextField getRagioneSociale() {
		if (ragioneSociale == null) {
			ragioneSociale = new JTextField();
		}
		return ragioneSociale;
	}


	private JTextField getFax() {
		if (fax == null) {
			fax = new JTextField();
		}
		return fax;
	}


	private JTextField getEMail() {
		if (eMail == null) {
			eMail = new JTextField();
		}
		return eMail;
	}


	private JTextField getCellulare() {
		if (cellulare == null) {
			cellulare = new JTextField();
		}
		return cellulare;
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
