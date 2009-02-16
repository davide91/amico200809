//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.TuttePersone;
import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import datatype.CodiceFiscale;
import datatype.DatiPersona;
import datatype.DatiPersonaFisica;
import datatype.DatiPersonaGiuridica;
import datatype.Email;
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
public class ModificarePersona extends JFrame implements BaseBoundary{
	

	private GestorePersone GP;
	private Persona persona;
	private Persone personeFisiche = new Persone();
	private AccedereCondominioAperto ACA;
	private boolean conRiferimento = false;
	
	public ModificarePersona(Persona p,AccedereCondominioAperto aca){
		ACA=aca;
		this.GP=GestorePersone.getInstance();
		
		for (Persona pers : TuttePersone.getInstance().recuperaPersone().getPersone()) {
			if(pers instanceof PersonaFisica)
				personeFisiche.inserisciPersona(pers);
		}
		
		persona=p;
		initComponents();
		
		
		if(persona instanceof PersonaFisica)
		{
			bPersonaDiRiferimento.setEnabled(false);
			personaDiRiferimento.setEnabled(false);
			
			ragioneSociale.setEditable(false);
			partitaIVA.setEditable(false);
			indirizzoFiscale.setEditable(false);
			
			cognome.setEditable(true);
			cognome.setText(((PersonaFisica) persona).getDati().getCognome());
			
			nome.setEditable(true);
			nome.setText(((PersonaFisica) persona).getDati().getNome());
			
			codiceFiscale.setEditable(true);
			codiceFiscale.setText(((PersonaFisica) persona).getDati().getCf().getCodiceFis());

			domicilio.setEditable(true);
			domicilio.setText(((PersonaFisica) persona).getDati().getDomicilio().getVia());
			
			comune.setEditable(true);
			comune.setText(((PersonaFisica) persona).getDati().getDomicilio().getComune());
			
			cap.setEditable(true);
			cap.setText(((PersonaFisica) persona).getDati().getDomicilio().getCap());
			
			provincia.setEditable(true);
			provincia.setSelectedIndex(((PersonaFisica) persona).getDati().getDomicilio().getProvincia().ordinal());
			
			interno.setEditable(true);
			interno.setText(((PersonaFisica) persona).getDati().getDomicilio().getInterno());
			
			cellulare.setEditable(true);
			cellulare.setText(((PersonaFisica) persona).getDati().getCell());
			
			eMail.setEditable(true);
			eMail.setText(((PersonaFisica) persona).getDati().getMail().getEmail());
			
			telefono.setEditable(true);
			telefono.setText(((PersonaFisica) persona).getDati().getTel());
			
			fax.setEditable(true);
			fax.setText(((PersonaFisica) persona).getDati().getFax());
		}
		else if (persona instanceof PersonaGiuridica)
		{
			bPersonaDiRiferimento.setEnabled(true);
			personaDiRiferimento.setEnabled(true);
			
			PersonaGiuridica per = (PersonaGiuridica)persona;
			
			ragioneSociale.setEditable(true);
			ragioneSociale.setText((per.getDati().getRagioneSociale()));
			
			partitaIVA.setEditable(true);
			partitaIVA.setText(per.getDati().getpIva().getPartIva());
			
			indirizzoFiscale.setEditable(true);
			indirizzoFiscale.setText(per.getDati().getIndFiscale().getVia());
			
			cognome.setEditable(false);
			cognome.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getCognome());
			
			nome.setEditable(false);
			nome.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getNome());
			
			codiceFiscale.setEditable(false);
			codiceFiscale.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getCf().getCodiceFis());
			
			domicilio.setEditable(false);
			domicilio.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getDomicilio().getVia());
			
			comune.setEditable(false);
			comune.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getDomicilio().getComune());
			
			cap.setEditable(false);
			cap.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getDomicilio().getCap());
			
			provincia.setEditable(false);
			provincia.setSelectedIndex(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getDomicilio().getProvincia().ordinal());
			
			cellulare.setEditable(false);
			cellulare.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getCell());
			
			interno.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getDomicilio().getInterno());
			
			eMail.setEditable(false);
			eMail.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getMail().getEmail());
			
			telefono.setEditable(false);
			telefono.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getTel());
			
			fax.setEditable(false);
			fax.setText(((PersonaFisica)(per.getPersonaDiRiferimento())).getDati().getFax());
			aggiornaPersone();
		}
		
		setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("Dati Persona");
	}
	
	protected void bPersonaDiRiferimentoMouseMouseClicked(MouseEvent event) {
		
		bPersonaDiRiferimento.setEnabled(false);
		personaDiRiferimento.setEnabled(false);
		cognome.setEditable(true);
		nome.setEditable(true);
		codiceFiscale.setEditable(true);
		domicilio.setEditable(true);
		comune.setEditable(true);
		cap.setEditable(true);
		provincia.setEditable(true);
		cellulare.setEditable(true);
		interno.setEditable(true);
		telefono.setEditable(true);
		fax.setEditable(true);
		eMail.setEditable(true);
		conRiferimento=true;
		
	}
	
	public void inserisciNuoviDatiPersona(DatiPersona datiP) {
			GP.modificaDatiPersona(datiP);
	}

	public void ammissibile(EsitoControlloDatiPersona 	controlloDati) {
	
	}
	
	public void ammissibile(Boolean b) {
		if(b)
		{
			int c = JOptionPane.showConfirmDialog(this, "sei sicuro?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
			if (c==0){
				ok();
			}
			else {
				ko();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "persona gia presente");
		}
	}
	
	public void annulla() {
		GP.annullato();
	}

	
	public void ok() {
		ACA.setVisible(true);
		GP.procedi(true);
		this.dispose();
	}
	
	public void ko() {
		 GP.procedi(false);
		 JOptionPane.showMessageDialog(this, "Dati non modificati");
		 ACA.setVisible(true);
		 this.dispose();
	}

	public void fatto() {

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


	public void aggiornaPersone() {
		DefaultComboBoxModel x=new DefaultComboBoxModel();
		
		int selectedIndex =0;
		int index=-1;
		for (Persona p : personeFisiche.getPersone())
		{
			index++;
				x.addElement(((PersonaFisica)p).getDati().getNome()+" "+((PersonaFisica)p).getDati().getCognome());
				if(((PersonaFisica)p).getDati().equals( ((PersonaGiuridica)persona).getPersonaDiRiferimento().getDati()))
					selectedIndex=index;
		}
		
		personaDiRiferimento.setModel(x);
		personaDiRiferimento.setSelectedIndex(selectedIndex);
	}
	

	private void bModificaMouseMouseClicked(MouseEvent event) {
		if(persona instanceof PersonaFisica)
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
			inserisciNuoviDatiPersona(datiP);
		}
		else if(persona instanceof PersonaGiuridica)
		{
			
			DatiPersonaGiuridica datiPG=new DatiPersonaGiuridica();
			
			if(conRiferimento) 
			{
				DatiPersonaFisica pRiferimento = new DatiPersonaFisica();
				pRiferimento.setFax(fax.getText());
				Email mail =new Email(eMail.getText());
				pRiferimento.setMail(mail);
				pRiferimento.setTel(telefono.getText());
				pRiferimento.setNome(nome.getText());
				pRiferimento.setCognome(cognome.getText());
				pRiferimento.setCell(cellulare.getText());
				CodiceFiscale cf=new CodiceFiscale();
				cf.setCodiceFis(codiceFiscale.getText());
				pRiferimento.setCf(cf);
				pRiferimento.setDomicilio(new Indirizzo(domicilio.getText(),interno.getText(),comune.getText(),(Provincia)provincia.getSelectedItem(),cap.getText()) );
				
				GP.inserisciPersonaDiRiferimento(pRiferimento);
			}
			else{
				GP.inserisciPersonaDiRiferimento((PersonaFisica)personeFisiche.getPersone().get(personaDiRiferimento.getSelectedIndex()));
			}
				
			datiPG.setpIva(new PartitaIva(partitaIVA.getText()));
			datiPG.setRagioneSociale(ragioneSociale.getText());
			Indirizzo i=new Indirizzo();
			i.setVia(indirizzoFiscale.getText());
			datiPG.setIndFiscale(i);
			inserisciNuoviDatiPersona(datiPG);
		}
	}
	

	private void bannullaMouseMouseClicked(MouseEvent event) {
		ACA.setVisible(true);
		GP.annullato();
		this.dispose();
		
	}

	private static final long serialVersionUID = 1L;
	private JTextField nome;
	private JTextField cognome;
	private JTextField domicilio;
	private JTextField codiceFiscale;
	private JTextField telefono;
	private JComboBox provincia;
	private JTextField cellulare;
	private JTextField eMail;
	private JTextField fax;
	private JTextField ragioneSociale;
	private JTextField partitaIVA;
	private JTextField indirizzoFiscale;
	private JButton bModifica;
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
	private JLabel jLabel20;
	private JLabel jLabel21;
	private JLabel jLabel22;
	private JLabel jLabel11;
	private JSeparator jSeparator0;
	private JComboBox personaDiRiferimento;
	private JButton bPersonaDiRiferimento;

	private JTextField interno;

	private JLabel jLabel23;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		
		add(getJLabel0(), new Constraints(new Leading(7, 10, 10), new Leading(16, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(7, 12, 12), new Leading(46, 12, 12)));
		add(getJLabel3(), new Constraints(new Leading(7, 12, 12), new Leading(108, 12, 12)));
		add(getJLabel4(), new Constraints(new Leading(7, 12, 12), new Leading(144, 12, 12)));
		add(getCap(), new Constraints(new Leading(315, 108, 10, 10), new Leading(104, 12, 12)));
		add(getJLabel5(), new Constraints(new Leading(256, 10, 10), new Leading(108, 12, 12)));
		add(getJLabel6(), new Constraints(new Leading(251, 12, 12), new Leading(140, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(7, 12, 12), new Leading(74, 12, 12)));
		add(getJLabel8(), new Constraints(new Leading(7, 50, 12, 12), new Leading(213, 12, 12)));
		add(getJLabel7(), new Constraints(new Leading(7, 12, 12), new Leading(177, 12, 12)));
		add(getJLabel9(), new Constraints(new Leading(7, 12, 12), new Leading(247, 12, 12)));
		add(getJLabel10(), new Constraints(new Leading(7, 12, 12), new Leading(285, 12, 12)));
		add(getBModifica(), new Constraints(new Leading(112, 10, 10), new Leading(458, 10, 10)));
		add(getJLabel22(), new Constraints(new Leading(12, 12, 12), new Leading(416, 12, 12)));
		add(getJLabel21(), new Constraints(new Leading(12, 12, 12), new Leading(378, 12, 12)));
		add(getJLabel20(), new Constraints(new Leading(12, 12, 12), new Leading(336, 12, 12)));
		add(getCodiceFiscale(), new Constraints(new Leading(125, 110, 10, 10), new Leading(72, 12, 12)));
		add(getNome(), new Constraints(new Leading(125, 110, 12, 12), new Leading(44, 12, 12)));
		add(getCognome(), new Constraints(new Leading(125, 110, 12, 12), new Leading(14, 12, 12)));
		add(getDomicilio(), new Constraints(new Leading(123, 110, 10, 10), new Leading(104, 12, 12)));
		add(getComune(), new Constraints(new Leading(123, 112, 12, 12), new Leading(140, 12, 12)));
		add(getTelefono(), new Constraints(new Leading(123, 309, 12, 12), new Leading(176, 12, 12)));
		add(getCellulare(), new Constraints(new Leading(123, 309, 12, 12), new Leading(209, 12, 12)));
		add(getEMail(), new Constraints(new Leading(123, 308, 12, 12), new Leading(247, 12, 12)));
		add(getFax(), new Constraints(new Leading(124, 308, 12, 12), new Leading(281, 12, 12)));
		add(getProvincia(), new Constraints(new Leading(334, 84, 10, 10), new Leading(135, 12, 12)));
		add(getRagioneSociale(), new Constraints(new Leading(125, 309, 12, 12), new Leading(332, 12, 12)));
		add(getPartitaIVA(), new Constraints(new Leading(125, 308, 12, 12), new Leading(374, 12, 12)));
		add(getIndirizzoFiscale(), new Constraints(new Leading(125, 308, 12, 12), new Leading(412, 12, 12)));
		add(getBannulla(), new Constraints(new Leading(444, 10, 10), new Leading(458, 12, 12)));
		add(getJSeparator0(), new Constraints(new Leading(12, 627, 10, 10), new Leading(314, 10, 12, 12)));
		add(getBPersonaDiRiferimento(), new Constraints(new Leading(451, 12, 12), new Leading(411, 12, 12)));
		add(getInterno(), new Constraints(new Leading(507, 101, 10, 10), new Leading(102, 12, 12)));
		add(getJLabel23(), new Constraints(new Leading(446, 10, 10), new Leading(100, 22, 12, 12)));
			add(getPersonaDiRiferimento(), new Constraints(new Leading(451, 171, 12, 12), new Leading(369, 12, 12)));
		add(getJLabel11(), new Constraints(new Leading(453, 166, 12, 12), new Leading(337, 12, 12)));
		setSize(690, 530);
	}
	
	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}
	
	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("Persona di riferimento:");
		}
		return jLabel11;
	}
	
	private JButton getBPersonaDiRiferimento() {
		if (bPersonaDiRiferimento == null) {
			bPersonaDiRiferimento = new JButton();
			bPersonaDiRiferimento.setText("Aggiungi persona di riferimento");
			bPersonaDiRiferimento.setEnabled(false);
			bPersonaDiRiferimento.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bPersonaDiRiferimentoMouseMouseClicked(event);
				}
			});
		}
		return bPersonaDiRiferimento;
	}

	private JComboBox getPersonaDiRiferimento() {
		if (personaDiRiferimento == null) {
			personaDiRiferimento = new JComboBox();
			personaDiRiferimento.setModel(new DefaultComboBoxModel(new Object[] {}));
			personaDiRiferimento.setDoubleBuffered(false);
			personaDiRiferimento.setBorder(null);
		}
		return personaDiRiferimento;
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


	private JButton getBModifica() {
		if (bModifica == null) {
			bModifica = new JButton();
			bModifica.setText("Modifica");
			bModifica.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bModificaMouseMouseClicked(event);
				}
			});
		}
		return bModifica;
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


	private JTextField getCodiceFiscale() {
		if (codiceFiscale == null) {
			codiceFiscale = new JTextField();
		}
		return codiceFiscale;
	}

	private JTextField getNome() {
		if (nome == null) {
			nome = new JTextField();
		}
		return nome;
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
}