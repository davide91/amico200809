package boundary;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
public class NewJPanel extends javax.swing.JPanel {
	private JLabel labelTipoPersona;
	private ButtonGroup GroupTipoPersona;
	private JRadioButton radioPersonaGiuridica;
	private JLabel jLabel3;
	private JTextField textFieldCAP;
	private JTable tablePreferenze;
	private JLabel labelPreferenze;
	private JTextField textFieldFAX;
	private JTextField textFieldEmail;
	private JTextField textFieldCellulare;
	private JTextField textFieldTelefono;
	private JLabel labelFAX;
	private JLabel labelEmail;
	private JLabel labelCellulare;
	private JLabel labelTelefono;
	private JLabel jLabel1;
	private JTextField textFieldCodiceFiscale;
	private JComboBox comboBoxNazione;
	private JLabel labelNazione;
	private JTextField textFieldProvincia;
	private JLabel labelProvincia;
	private JComboBox comboBoxComune;
	private JLabel labelComune;
	private JTextField textFieldCittà;
	private JLabel labelCitta;
	private JLabel labelViaNumero;
	private JTextField textFieldNome;
	private JTextField textFieldDomicilio;
	private JTextField textFieldCognome;
	private JLabel labelCAP;
	private JLabel jLabel2;
	private JLabel labelNome;
	private JLabel labelCognome;
	private ButtonGroup buttonGroup;
	private ButtonGroup buttonGroup1;
	private JRadioButton radioPersonaFisica;
	
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new NewJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public NewJPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		
			this.setPreferredSize(new java.awt.Dimension(618, 503));
			this.setLayout(null);
			this.setName("textFieldNome");
			{
				labelTipoPersona = new JLabel();
				this.add(labelTipoPersona);
				labelTipoPersona.setName("labelTipoPersona");
				labelTipoPersona.setBounds(12, 12, 240, 15);
				labelTipoPersona.setText("Inserire il tipo di persona");
			}
			
			
			
			
			
			{
				radioPersonaFisica = new JRadioButton();
				this.add(radioPersonaFisica);
				radioPersonaFisica.setBounds(410, 10, 99, 19);
				radioPersonaFisica.setName("radioPersonaFisica");
				radioPersonaFisica.setText("Persona Fisica");
			}
			{
				radioPersonaGiuridica = new JRadioButton();
				this.add(radioPersonaGiuridica);
				radioPersonaGiuridica.setBounds(410, 38, 119, 19);
				radioPersonaGiuridica.setName("radioPersonaGiuridica");
				radioPersonaGiuridica.setText("Persona Giuridica");
			}
				GroupTipoPersona = new ButtonGroup();
				GroupTipoPersona.add(radioPersonaFisica);
				GroupTipoPersona.add(radioPersonaGiuridica);
				
			
			
			{
				labelCognome = new JLabel();
				this.add(labelCognome);
				labelCognome.setBounds(12, 65, 54, 15);
				labelCognome.setName("labelCognome");
				labelCognome.setText("Cognome");
			}
			{
				labelNome = new JLabel();
				this.add(labelNome);
				labelNome.setName("labelNome");
				labelNome.setBounds(12, 99, 54, 15);
				labelNome.setText("Nome");
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setName("labelCodiceFiscale");
				jLabel2.setBounds(12, 242, 93, 15);
				jLabel2.setText("Codice Fiscale");
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3);
				this.add(getLabelCAP());
				this.add(getTextFieldCognome());
				this.add(getTextFieldDomicilio());
				this.add(getTextFieldNome());
				this.add(getLabelViaNumero());
				this.add(getTextFieldCAP());
				this.add(getLabelCitta());
				this.add(getTextFieldCittà());
				this.add(getLabelComune());
				this.add(getComboBoxComune());
				this.add(getLabelProvincia());
				this.add(getTextFieldProvincia());
				this.add(getLabelNazione());
				this.add(getComboBoxNazione());
				this.add(getTextFieldCodiceFiscale());
				this.add(getJLabel1());
				this.add(getLabelTelefono());
				this.add(getLabelCellulare());
				this.add(getLabelEmail());
				this.add(getLabelFAX());
				this.add(getTextFieldTelefono());
				this.add(getTextFieldCellulare());
				this.add(getTextFieldEmail());
				this.add(getTextFieldFAX());
				this.add(getLabelPreferenze());
				this.add(getTablePreferenze());
				jLabel3.setName("labelDomicilio");
				jLabel3.setBounds(12, 123, 63, 17);
				jLabel3.setIgnoreRepaint(true);
				jLabel3.setText("Domicilio");

			}
			
			
	}
	
	private ButtonGroup getGroupTipoPersona() {
		if(GroupTipoPersona == null) {
			GroupTipoPersona = new ButtonGroup();
		}
		return GroupTipoPersona;
	}
	
	private ButtonGroup getButtonGroup1() {
		if(buttonGroup1 == null) {
buttonGroup1 = new ButtonGroup();
		}
		return buttonGroup1;
	}
	
	private ButtonGroup getButtonGroup() {
		if(buttonGroup == null) {
	buttonGroup = new ButtonGroup();
		}
		return buttonGroup;
	}
	
	private JLabel getLabelCAP() {
		if(labelCAP == null) {
			labelCAP = new JLabel();
			labelCAP.setBounds(24, 168, 23, 15);
			labelCAP.setName("labelCAP");
			labelCAP.setText("CAP");
		}
		return labelCAP;
	}
	
	public JTextField getTextFieldCognome() {
		if(textFieldCognome == null) {
			textFieldCognome = new JTextField();
			textFieldCognome.setBounds(100, 62, 493, 22);
		}
		return textFieldCognome;
	}

	public JTextField getTextFieldDomicilio() {
		if(textFieldDomicilio == null) {
			textFieldDomicilio = new JTextField();
			textFieldDomicilio.setBounds(100, 142, 493, 22);
		}
		return textFieldDomicilio;
	}
	
	public JTextField getTextFieldNome() {
		if(textFieldNome == null) {
			textFieldNome = new JTextField();
			textFieldNome.setBounds(100, 96, 493, 22);
		}
		return textFieldNome;
	}
	
	private JLabel getLabelViaNumero() {
		if(labelViaNumero == null) {
			labelViaNumero = new JLabel();
			labelViaNumero.setBounds(12, 138, 76, 15);
			labelViaNumero.setName("labelViaNumero");
			labelViaNumero.setText("Via, numero");
		}
		return labelViaNumero;
	}
	
	public JTextField getTextFieldCAP() {
		if(textFieldCAP == null) {
			textFieldCAP = new JTextField();
			textFieldCAP.setBounds(100, 165, 82, 22);
		}
		return textFieldCAP;
	}
	
	private JLabel getLabelCitta() {
		if(labelCitta == null) {
			labelCitta = new JLabel();
			labelCitta.setBounds(200, 168, 38, 15);
			labelCitta.setName("labelCitta");
			labelCitta.setText("Città");
		}
		return labelCitta;
	}
	
	public JTextField getTextFieldCittà() {
		if(textFieldCittà == null) {
			textFieldCittà = new JTextField();
			textFieldCittà.setBounds(235, 165, 131, 22);
		}
		return textFieldCittà;
	}
	
	private JLabel getLabelComune() {
		if(labelComune == null) {
			labelComune = new JLabel();
			labelComune.setBounds(387, 168, 47, 15);
			labelComune.setName("labelComune");
			labelComune.setText("Comune");
		}
		return labelComune;
	}
	
	public JComboBox getComboBoxComune() {
		if(comboBoxComune == null) {
			ComboBoxModel comboBoxComuneModel = 
				new DefaultComboBoxModel(
						new String[] { "Item One", "Item Two" });
			comboBoxComune = new JComboBox();
			comboBoxComune.setModel(comboBoxComuneModel);
			comboBoxComune.setBounds(441, 164, 152, 22);
		}
		return comboBoxComune;
	}
	
	private JLabel getLabelProvincia() {
		if(labelProvincia == null) {
			labelProvincia = new JLabel();
			labelProvincia.setBounds(12, 205, 51, 15);
			labelProvincia.setName("labelProvincia");
			labelProvincia.setText("Provincia");
		}
		return labelProvincia;
	}
	
	public JTextField getTextFieldProvincia() {
		if(textFieldProvincia == null) {
			textFieldProvincia = new JTextField();
			textFieldProvincia.setBounds(100, 202, 64, 22);
		}
		return textFieldProvincia;
	}
	
	private JLabel getLabelNazione() {
		if(labelNazione == null) {
			labelNazione = new JLabel();
			labelNazione.setBounds(182, 205, 47, 15);
			labelNazione.setName("labelNazione");
			labelNazione.setText("Nazione");
		}
		return labelNazione;
	}
	
	public JComboBox getComboBoxNazione() {
		if(comboBoxNazione == null) {
			ComboBoxModel comboBoxNazioneModel = 
				new DefaultComboBoxModel(
						new String[] { "Item One", "Item Two" });
			comboBoxNazione = new JComboBox();
			comboBoxNazione.setModel(comboBoxNazioneModel);
			comboBoxNazione.setBounds(235, 201, 162, 22);
		}
		return comboBoxNazione;
	}
	
	public JTextField getTextFieldCodiceFiscale() {
		if(textFieldCodiceFiscale == null) {
			textFieldCodiceFiscale = new JTextField();
			textFieldCodiceFiscale.setBounds(101, 239, 294, 22);
		}
		return textFieldCodiceFiscale;
	}
	
	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setName("jLabel1");
			jLabel1.setBounds(12, 279, 88, 15);
			jLabel1.setText("Recapiti");
		}
		return jLabel1;
	}
	
	private JLabel getLabelTelefono() {
		if(labelTelefono == null) {
			labelTelefono = new JLabel();
			labelTelefono.setBounds(100, 278, 50, 15);
			labelTelefono.setName("labelTelefono");
			labelTelefono.setText("Telefono");
		}
		return labelTelefono;
	}
	
	private JLabel getLabelCellulare() {
		if(labelCellulare == null) {
			labelCellulare = new JLabel();
			labelCellulare.setBounds(100, 305, 50, 15);
			labelCellulare.setName("labelCellulare");
			labelCellulare.setText("Cellulare");
		}
		return labelCellulare;
	}
	
	private JLabel getLabelEmail() {
		if(labelEmail == null) {
			labelEmail = new JLabel();
			labelEmail.setBounds(100, 332, 31, 15);
			labelEmail.setName("labelEmail");
			labelEmail.setText("Email");
		}
		return labelEmail;
	}
	
	private JLabel getLabelFAX() {
		if(labelFAX == null) {
			labelFAX = new JLabel();
			labelFAX.setBounds(100, 359, 22, 15);
			labelFAX.setName("labelFAX");
			labelFAX.setText("FAX");
		}
		return labelFAX;
	}
	
	public JTextField getTextFieldTelefono() {
		if(textFieldTelefono == null) {
			textFieldTelefono = new JTextField();
			textFieldTelefono.setBounds(182, 275, 213, 22);
		}
		return textFieldTelefono;
	}
	
	public JTextField getTextFieldCellulare() {
		if(textFieldCellulare == null) {
			textFieldCellulare = new JTextField();
			textFieldCellulare.setBounds(182, 302, 213, 22);
		}
		return textFieldCellulare;
	}
	
	public JTextField getTextFieldEmail() {
		if(textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(181, 329, 214, 22);
		}
		return textFieldEmail;
	}
	
	public JTextField getTextFieldFAX() {
		if(textFieldFAX == null) {
			textFieldFAX = new JTextField();
			textFieldFAX.setBounds(181, 356, 214, 22);
		}
		return textFieldFAX;
	}
	
	private JLabel getLabelPreferenze() {
		if(labelPreferenze == null) {
			labelPreferenze = new JLabel();
			labelPreferenze.setText("Preferenza");
			labelPreferenze.setBounds(12, 407, 63, 15);
		}
		return labelPreferenze;
	}
	
	private JTable getTablePreferenze() {
		if(tablePreferenze == null) {
			TableModel tablePreferenzeModel =
				new DefaultTableModel(
						new Object[][] { {new JLabel(), new JLabel("POSTEL"), new JLabel("SMS"),new JLabel("EMAIL"),new JLabel("TELEFONO"),new JLabel("FAX"), }, { "Three", "Four" } },
						new Object[] { "Column 1", "Column 2", "Column 3","Column 4","Column 5","Column 6" });
			tablePreferenze = new JTable();
			tablePreferenze.setModel(tablePreferenzeModel);
			tablePreferenze.setBounds(181, 422, 10, 10);
		}
		return tablePreferenze;
	}

}
