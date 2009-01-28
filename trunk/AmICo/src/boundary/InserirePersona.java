//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Persona;
import datatype.CodiceFiscale;
import datatype.DatiCorretti;
import datatype.DatiErrati;
import datatype.DatiPersona;
import datatype.DatiPersonaFisica;
import datatype.DatiPersonaGiuridica;
import datatype.Email;
import datatype.EsitoControlloDati;
import datatype.EsitoControlloDatiPersona;
import datatype.Indirizzo;
import datatype.PartitaIva;
import datatype.list.Persone;
import enumeration.Provincia;
import executor.GestorePersone;

/**
 * @author Federico
 *
 */
public class InserirePersona extends JFrame implements BaseBoundary{

	private GestorePersone GP;
	private ButtonGroup group;
	
	public InserirePersona() {
		initComponents();
		initgroup();
		
		this.GP=GestorePersone.getInstance();
		this.setVisible(true);
	}

	

	private void initgroup()
	{
		group=new ButtonGroup();
		group.add(radioPF);
		group.add(radioPG);
		radioPF.setSelected(true);
		radioPFMouseMouseClicked(null);
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
	/*	 if (esito instanceof DatiErrati){
				JOptionPane.showMessageDialog(this,"Dati errati");
			//	System.out.println("");
		 }
		 
		 if(esito instanceof DatiCorretti) {*/
			 GP.inserisciDatiPersona(datiP);
	//	 }
		 
		
	}
	
	public void ammissibile(Boolean b) {
		if(b)
			JOptionPane.showMessageDialog(this, "persona inserita");

		else
			JOptionPane.showMessageDialog(this, "persona non inserita");
		
		GP.procedi(true);
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
	private void radioPFMouseMouseClicked(MouseEvent event)
	{
		ragioneSociale.setEditable(false);
		partitaIVA.setEditable(false);
		indirizzoFiscale.setEditable(false);
		bPersonaDiRiferimento.setEnabled(false);
		cognome.setEditable(true);
		nome.setEditable(true);
		codiceFiscale.setEditable(true);
		domicilio.setEditable(true);
		comune.setEditable(true);
		cap.setEditable(true);
		provincia.setEditable(true);
		cellulare.setEditable(true);
		interno.setEditable(true);
	}

	private void radioPGMouseMouseClicked(MouseEvent event)
	{
		ragioneSociale.setEditable(true);
		partitaIVA.setEditable(true);
		indirizzoFiscale.setEditable(true);
		bPersonaDiRiferimento.setEnabled(true);
		cognome.setEditable(false);
		nome.setEditable(false);
		codiceFiscale.setEditable(false);
		domicilio.setEditable(false);
		comune.setEditable(false);
		cap.setEditable(false);
		provincia.setEditable(false);
		cellulare.setEditable(false);
		interno.setEditable(false);
	}


	private void bannullaMouseMouseClicked(MouseEvent event) {
		annulla();
		this.dispose();
	}

	private void bPersonaDiRiferimentoMouseMouseClicked(MouseEvent event) {
	}
	
	private void bokMouseMouseClicked(MouseEvent event) {

		if(radioPF.isSelected())
		{
			DatiPersonaFisica datiP=new DatiPersonaFisica();
			datiP.setFax(fax.getText());
			Email mail =new Email(eMail.getText());
			datiP.setMail(mail);
			datiP.setTel(telefono.getText());
			datiP.setNome(nome.getText());
			datiP.setCognome(cognome.getText());
			datiP.setCell(cellulare.getText());
			CodiceFiscale cf=new CodiceFiscale();
			cf.setCodiceFis(codiceFiscale.getText());
			datiP.setCf(cf);
			datiP.setDomicilio(new Indirizzo(domicilio.getText(),interno.getText(),comune.getText(),(Provincia)provincia.getSelectedItem(),cap.getText()) );
			inserisciDatiPersona(datiP);
		}
		else if(radioPF.isSelected())
		{
			DatiPersonaGiuridica datiP=new DatiPersonaGiuridica();
			datiP.setFax(fax.getText());
			Email mail =new Email(eMail.getText());
			datiP.setMail(mail);
			datiP.setTel(telefono.getText());
			datiP.setpIva(new PartitaIva(partitaIVA.getText()));
			datiP.setRagioneSociale(ragioneSociale.getText());
			Indirizzo i=new Indirizzo();
			i.setVia(indirizzoFiscale.getText());
			datiP.setIndFiscale(i);
			inserisciDatiPersona(datiP);
		}

}

	
	private static final long serialVersionUID = 1L;
	private JTextField nome;
	private JTextField cognome;
	private JTextField domicilio;
	private JTextField codiceFiscale;
	private JTextField telefono;
	private JRadioButton radioPF;
	private JRadioButton radioPG;
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
	private JTextField partitaIVA;
	private JTextField indirizzoFiscale;
	private JButton bok;
	private JButton bannulla;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField cap;
	private JTextField comune;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel14;
	private JLabel jLabel13;
	private JLabel jLabel12;
	private JLabel jLabel15;
	private JLabel jLabel16;
	private JLabel jLabel17;
	private JLabel jLabel18;
	private JLabel jLabel19;
	private JLabel jLabel20;
	private JLabel jLabel21;
	private JLabel jLabel22;
	private JButton bPersonaDiRiferimento;

	private JTextField interno;

	private JLabel jLabel23;

	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getRadioPF(), new Constraints(new Leading(479, 10, 10), new Leading(12, 8, 8)));
		add(getRadioPG(), new Constraints(new Leading(479, 8, 8), new Leading(38, 8, 8)));
		add(getNome(), new Constraints(new Leading(108, 110, 12, 12), new Leading(44, 12, 12)));
		add(getCognome(), new Constraints(new Leading(108, 110, 12, 12), new Leading(14, 12, 12)));
		add(getDomicilio(), new Constraints(new Leading(108, 110, 12, 12), new Leading(104, 12, 12)));
		add(getProvincia(), new Constraints(new Leading(313, 104, 10, 10), new Leading(134, 12, 12)));
		add(getCodiceFiscale(), new Constraints(new Leading(108, 110, 12, 12), new Leading(72, 10, 10)));
		add(getTelefono(), new Constraints(new Leading(108, 309, 12, 12), new Leading(175, 12, 12)));
		add(getCellulare(), new Constraints(new Leading(108, 309, 12, 12), new Leading(211, 12, 12)));
		add(getEMail(), new Constraints(new Leading(108, 308, 12, 12), new Leading(247, 12, 12)));
		add(getFax(), new Constraints(new Leading(108, 308, 12, 12), new Leading(283, 12, 12)));
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
		add(getJLabel0(), new Constraints(new Leading(7, 10, 10), new Leading(16, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(7, 12, 12), new Leading(46, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(7, 12, 12), new Leading(108, 12, 12)));
		add(getComune(), new Constraints(new Leading(108, 112, 12, 12), new Leading(140, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(7, 12, 12), new Leading(144, 12, 12)));
		add(getCap(), new Constraints(new Leading(315, 108, 10, 10), new Leading(104, 12, 12)));
		add(getJLabel5(), new Constraints(new Leading(256, 10, 10), new Leading(108, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(251, 12, 12), new Leading(140, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(7, 12, 12), new Leading(74, 12, 12)));
		add(getJLabel8(), new Constraints(new Leading(7, 50, 12, 12), new Leading(213, 12, 12)));
		add(getJLabel7(), new Constraints(new Leading(7, 12, 12), new Leading(177, 12, 12)));
		add(getJLabel9(), new Constraints(new Leading(7, 12, 12), new Leading(247, 12, 12)));
		add(getJLabel10(), new Constraints(new Leading(7, 12, 12), new Leading(285, 12, 12)));
		add(getJLabel11(), new Constraints(new Leading(14, 12, 12), new Leading(387, 12, 12)));
		add(getJLabel14(), new Constraints(new Leading(12, 12, 12), new Leading(465, 12, 12)));
		add(getJLabel13(), new Constraints(new Leading(12, 12, 12), new Leading(437, 12, 12)));
		add(getJLabel12(), new Constraints(new Leading(12, 12, 12), new Leading(411, 12, 12)));
		add(getJLabel15(), new Constraints(new Leading(185, 12, 12), new Leading(351, 10, 10)));
		add(getJLabel16(), new Constraints(new Leading(240, 12, 12), new Leading(351, 12, 12)));
		add(getJLabel17(), new Constraints(new Leading(296, 12, 12), new Leading(351, 12, 12)));
		add(getJLabel18(), new Constraints(new Leading(335, 12, 12), new Leading(351, 12, 12)));
		add(getJLabel19(), new Constraints(new Leading(396, 25, 12, 12), new Leading(351, 12, 12)));
		add(getJLabel21(), new Constraints(new Leading(12, 12, 12), new Leading(531, 12, 12)));
		add(getJLabel20(), new Constraints(new Leading(12, 12, 12), new Leading(497, 12, 12)));
		add(getJLabel22(), new Constraints(new Leading(12, 12, 12), new Leading(567, 12, 12)));
		add(getBPersonaDiRiferimento(), new Constraints(new Leading(488, 10, 10), new Leading(562, 12, 12)));
		add(getInterno(), new Constraints(new Leading(496, 101, 10, 10), new Leading(102, 12, 12)));
		add(getJLabel23(), new Constraints(new Leading(437, 10, 10), new Leading(100, 22, 12, 12)));
		initGroup();
		setSize(672, 688);
	}

	private JLabel getJLabel23() {
		if (jLabel23 == null) {
			jLabel23 = new JLabel();
			jLabel23.setText("interno:");
		}
		return jLabel23;
	}

	private JTextField getInterno() {
		if (interno == null) {
			interno = new JTextField();
		}
		return interno;
	}

	private void initGroup() {
		group = new ButtonGroup();
		group.add(getRadioPF());
		group.add(getRadioPG());
	}

	private JButton getBPersonaDiRiferimento() {
		if (bPersonaDiRiferimento == null) {
			bPersonaDiRiferimento = new JButton();
			bPersonaDiRiferimento.setText("Persona di riferimento");
			bPersonaDiRiferimento.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bPersonaDiRiferimentoMouseMouseClicked(event);
				}
			});
		}
		return bPersonaDiRiferimento;
	}


	private JLabel getJLabel22() {
		if (jLabel22 == null) {
			jLabel22 = new JLabel();
			jLabel22.setText("Indirizzo fiscale:");
		}
		return jLabel22;
	}


	private JLabel getJLabel21() {
		if (jLabel21 == null) {
			jLabel21 = new JLabel();
			jLabel21.setText("Partita IVA:");
		}
		return jLabel21;
	}


	private JLabel getJLabel20() {
		if (jLabel20 == null) {
			jLabel20 = new JLabel();
			jLabel20.setText("Ragione sociale:");
		}
		return jLabel20;
	}


	private JLabel getJLabel19() {
		if (jLabel19 == null) {
			jLabel19 = new JLabel();
			jLabel19.setText("FAX");
		}
		return jLabel19;
	}


	private JLabel getJLabel18() {
		if (jLabel18 == null) {
			jLabel18 = new JLabel();
			jLabel18.setText("Telefono");
		}
		return jLabel18;
	}


	private JLabel getJLabel17() {
		if (jLabel17 == null) {
			jLabel17 = new JLabel();
			jLabel17.setText("Mail");
		}
		return jLabel17;
	}


	private JLabel getJLabel16() {
		if (jLabel16 == null) {
			jLabel16 = new JLabel();
			jLabel16.setText("SMS");
		}
		return jLabel16;
	}


	private JLabel getJLabel15() {
		if (jLabel15 == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("Postel");
		}
		return jLabel15;
	}


	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("Verbali assemblea");
		}
		return jLabel12;
	}


	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("Convocazioni assemblea");
		}
		return jLabel13;
	}


	private JLabel getJLabel14() {
		if (jLabel14 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("Avvisi");
		}
		return jLabel14;
	}


	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("Comunicazioni");
		}
		return jLabel11;
	}


	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("Fax:");
		}
		return jLabel10;
	}


	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("E Mail");
		}
		return jLabel9;
	}


	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Cellulare:");
		}
		return jLabel8;
	}


	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Telefono:");
		}
		return jLabel7;
	}


	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Provincia:");
		}
		return jLabel6;
	}


	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("CAP:");
		}
		return jLabel5;
	}


	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Comune:");
		}
		return jLabel4;
	}


	private JTextField getComune() {
		if (comune == null) {
			comune = new JTextField();
		}
		return comune;
	}


	private JTextField getCap() {
		if (cap == null) {
			cap = new JTextField();
		}
		return cap;
	}


	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Domicilio");
		}
		return jLabel3;
	}


	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Codice fiscale:");
		}
		return jLabel2;
	}


	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Nome:");
		}
		return jLabel1;
	}


	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Cognome:");
		}
		return jLabel0;
	}


	private JButton getBannulla() {
		if (bannulla == null) {
			bannulla = new JButton();
			bannulla.setText("Annulla");
			bannulla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bannullaMouseMouseClicked(event);
				}
			});
		}
		return bannulla;
	}


	private JButton getBok() {
		if (bok == null) {
			bok = new JButton();
			bok.setText("OK");
			bok.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bokMouseMouseClicked(event);
				}
			});
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
			provincia.setModel(new DefaultComboBoxModel(Provincia.values() ));
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
			radioPG.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					radioPGMouseMouseClicked(event);
				}
			});
		}
		return radioPG;
	}

	private JRadioButton getRadioPF() {
		if (radioPF == null) {
			radioPF = new JRadioButton();
			radioPF.setText("Persona Fisica");
			radioPF.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					radioPFMouseMouseClicked(event);
				}
			});
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
