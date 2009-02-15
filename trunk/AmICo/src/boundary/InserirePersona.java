//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Persona;
import store.POJO.PersonaFisica;
import datatype.CodiceFiscale;
import datatype.DatiPersona;
import datatype.DatiPersonaFisica;
import datatype.DatiPersonaGiuridica;
import datatype.Email;
import datatype.EsitoControlloDatiPersona;
import datatype.Indirizzo;
import datatype.PartitaIva;
import datatype.list.Persone;
import datatype.list.PersoneFisiche;
import enumeration.Provincia;
import executor.GestorePersone;

/**
 * @author Federico
 *
 */
public class InserirePersona extends JFrame implements BaseBoundary{

	private GestorePersone GP;
	private ButtonGroup group;
	private Persone personeFisiche;
	private boolean conRiferimento = false;
	
	public InserirePersona(Persone p) {
		this.GP=GestorePersone.getInstance();
		initComponents();
		initgroup();
		aggiornaPersone(p);
		this.setLocationRelativeTo(null);
		this.setTitle("Inserire Nuova Persona");
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
		   String regex = "[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]";   
		   if (datiP instanceof DatiPersonaFisica){
			   if (!Pattern.matches(regex, ((DatiPersonaFisica)datiP).getCf().getCodiceFis()))
			   {
				   JOptionPane.showMessageDialog(this,"Dati errati");
			   }
			   else GP.inserisciDatiPersona(datiP);
	 	   }
		   else if (datiP instanceof DatiPersonaGiuridica)
		   {
			   
				   String pi=((DatiPersonaGiuridica)datiP).getpIva().getPartIva();
			   
				    int i;
				    if( pi.length() == 0 ) 
				    if( pi.length() != 11 )
				        JOptionPane.showMessageDialog(this, "La lunghezza della partita IVA non e';\n"
				        + "corretta: la partita IVA dovrebbe essere lunga\n"
				        + "esattamente 11 caratteri.\n");
				    for( i=0; i<11; i++ ){
				        if( pi.charAt(i) < '0' || pi.charAt(i) > '9' )
				        {
				            JOptionPane.showMessageDialog(this, "La partita IVA contiene dei caratteri non ammessi:\n"
				            + "la partita IVA dovrebbe contenere solo cifre.\n");
				            break;
				        }
				    }
				    
		   }
		   else GP.inserisciDatiPersona(datiP);
			   
		   
	}
	
	public void ammissibile(Boolean b) {
		if(b){
			JOptionPane.showMessageDialog(this, "persona inserita");
			GP.procedi(true);
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(this, "persona non inserita");
			GP.procedi(false);
		}
	}

	public void ammissibile(EsitoControlloDatiPersona personaGiaInserita) {
		
		
	}
	public void  annulla() {
		GP.annullato();
	}

	public void fatto() {
			
	}

	public void fallito() {
		
	}

	public void finito() {
		// TODO Auto-generated method stub
		
	}

	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}
	
	private PersoneFisiche aggiornaPersonaFisiche(Persone persone)
	{
		PersoneFisiche pf=new PersoneFisiche();
		
		for (Persona p : persone.getPersone()) {
			if(p instanceof PersonaFisica)
				pf.inserisciPersona(p);
		}
		
		return pf;
	}

	public void aggiornaPersone(Persone persone) {
		
		personeFisiche = aggiornaPersonaFisiche(persone);
		
		DefaultComboBoxModel x=new DefaultComboBoxModel();
		
		for (Persona p : personeFisiche.getPersone())
				x.addElement(((PersonaFisica)p).getDati().getNome()+" "+((PersonaFisica)p).getDati().getCognome());
		
		
		personaDiRiferimento.setModel(x);
		
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
		conRiferimento = false;
		telefono.setEditable(true);
		eMail.setEditable(true);
		fax.setEditable(true);
		conRiferimento = false;
		personaDiRiferimento.setEnabled(false);
		conRiferimento=false;
	}

	private void radioPGMouseMouseClicked(MouseEvent event)
	{
		ragioneSociale.setEditable(true);
		partitaIVA.setEditable(true);
		indirizzoFiscale.setEditable(true);
		bPersonaDiRiferimento.setEnabled(true);
		fax.setEditable(false);
		eMail.setEditable(false);
		telefono.setEditable(false);
		cognome.setEditable(false);
		nome.setEditable(false);
		codiceFiscale.setEditable(false);
		domicilio.setEditable(false);
		comune.setEditable(false);
		cap.setEditable(false);
		provincia.setEditable(false);
		cellulare.setEditable(false);
		interno.setEditable(false);
	//	conRiferimento = true;
		personaDiRiferimento.setEnabled(true);
	}


	private void bannullaMouseMouseClicked(MouseEvent event) {
		annulla();
		this.dispose();
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
		else if(conRiferimento)
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
		
			if(GP.inserisciPersonaDiRiferimento(pRiferimento))
			{
				DatiPersonaGiuridica datiP=new DatiPersonaGiuridica();
			//	datiP.setFax(fax.getText());
			//	Email mail =new Email(eMail.getText());
			//	datiP.setMail(mail);
			//	datiP.setTel(telefono.getText());
				datiP.setpIva(new PartitaIva(partitaIVA.getText()));
				datiP.setRagioneSociale(ragioneSociale.getText());
				Indirizzo i=new Indirizzo();
				i.setVia(indirizzoFiscale.getText());
				datiP.setIndFiscale(i);
				inserisciDatiPersona(datiP);
			}
			else
				JOptionPane.showMessageDialog(this, "Persona di Riferimento non Inserita");
		}
		else
		{
			GP.inserisciPersonaDiRiferimento((PersonaFisica)personeFisiche.getPersone().get(personaDiRiferimento.getSelectedIndex()));
			
			DatiPersonaGiuridica datiP=new DatiPersonaGiuridica();
			//	datiP.setFax(fax.getText());
			//	Email mail =new Email(eMail.getText());
			//	datiP.setMail(mail);
			//	datiP.setTel(telefono.getText());
				datiP.setpIva(new PartitaIva(partitaIVA.getText()));
				datiP.setRagioneSociale(ragioneSociale.getText());
				Indirizzo i=new Indirizzo();
				i.setVia(indirizzoFiscale.getText());
				datiP.setIndFiscale(i);
				inserisciDatiPersona(datiP);
			
			
		}
	}
	

	protected void bPersonaDiRiferimentoMouseMouseClicked(MouseEvent event) {
		
		/*ragioneSociale.setEditable(false);
		partitaIVA.setEditable(false);
		indirizzoFiscale.setEditable(false);*/
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
		telefono.setEnabled(true);
		fax.setEnabled(true);
		eMail.setEnabled(true);
		conRiferimento=true;
		
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
	private JLabel jLabel20;
	private JLabel jLabel21;
	private JLabel jLabel22;
	private JButton bPersonaDiRiferimento;
	private JTextField interno;
	private JLabel jLabel23;
	private JSeparator jSeparator0;
	private JComboBox personaDiRiferimento;
	private JLabel jLabel11;
	private void initComponents() {
		setTitle("Inserire Nuova Persona");
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
		add(getBok(), new Constraints(new Leading(112, 10, 10), new Leading(458, 10, 10)));
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
		add(getRadioPF(), new Constraints(new Leading(514, 8, 8), new Leading(12, 8, 8)));
		add(getRadioPG(), new Constraints(new Leading(514, 8, 8), new Leading(38, 8, 8)));
		add(getPersonaDiRiferimento(), new Constraints(new Leading(451, 171, 12, 12), new Leading(369, 12, 12)));
		add(getJLabel11(), new Constraints(new Leading(453, 166, 12, 12), new Leading(337, 12, 12)));
		initGroup();
		setSize(690, 530);
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("Persona di riferimento:");
		}
		return jLabel11;
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

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
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

}
