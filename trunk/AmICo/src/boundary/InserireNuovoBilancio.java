//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import datatype.Data;
import datatype.DatiBilancio;
import enumeration.StatoBilancio;
import enumeration.TipoBilancio;

public class InserireNuovoBilancio extends JFrame {

	private AccedereBilanci AB;
	private AccedereCondominioAperto ACA;
	private ButtonGroup bGroup = new ButtonGroup();
	
	public InserireNuovoBilancio(AccedereBilanci ab,AccedereCondominioAperto aca) {
		AB=ab;
		ACA=aca;
		initComponents();
		setTitle("Redigendo bilancio");
		setLocationRelativeTo(null);
		setVisible(true);
		bGroup.add(jRadioButtonConsuntivo);
		bGroup.add(jRadioButtonPreventivo);
		jRadioButtonConsuntivo.setSelected(true);
	}
	
	private void bOkMouseMouseClicked(MouseEvent event) {
		DatiBilancio dati=new DatiBilancio();
		dati.setDescrizione(descrizione.getText());
		dati.setTitolo(titolo.getText());
		dati.setTipo((TipoBilancio)tipoBilancio.getSelectedItem());
	
		if(jRadioButtonConsuntivo.isSelected())
			dati.setStato(StatoBilancio.consuntivo);
		else
			dati.setStato(StatoBilancio.preventivo);
		
		dati.impostaDataInizio(new Data());
		
		Data fine = new Data();
		fine.add(GregorianCalendar.DAY_OF_MONTH, -1);
		dati.impostaDataFine(fine);
		
		AB.inserisciBilancio(dati);
		this.dispose();
	}

	private void bAnnullaMouseMouseClicked(MouseEvent event) {
		ACA.setVisible(true);
		this.dispose();
	}

	
	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JTextField titolo;
	private JLabel jLabel1;
	private JTextArea descrizione;
	private JScrollPane jScrollPane0;
	private JLabel jLabel2;
	private JComboBox tipoBilancio;
	private JButton bOk;
	private JButton bAnnulla;
	private JRadioButton jRadioButtonPreventivo;
	private JRadioButton jRadioButtonConsuntivo;
	@SuppressWarnings("unused")
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public InserireNuovoBilancio() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel1(), new Constraints(new Leading(18, 12, 12), new Leading(73, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(18, 118, 12, 12), new Leading(29, 10, 10)));
		add(getTipoBilancio(), new Constraints(new Leading(133, 126, 10, 10), new Leading(25, 12, 12)));
		add(getTitolo(), new Constraints(new Leading(133, 126, 12, 12), new Leading(72, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(131, 246, 10, 10), new Leading(112, 80, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(18, 12, 12), new Leading(114, 12, 12)));
		add(getJRadioButtonPreventivo(), new Constraints(new Leading(133, 8, 8), new Leading(204, 10, 10)));
		add(getJRadioButtonConsuntivo(), new Constraints(new Leading(133, 8, 8), new Leading(229, 10, 10)));
		add(getBOk(), new Constraints(new Leading(64, 12, 12), new Leading(284, 10, 10)));
		add(getBAnnulla(), new Constraints(new Leading(205, 10, 10), new Leading(284, 12, 12)));
		initBGroup();
		setSize(396, 352);
	}

	private void initBGroup() {
		bGroup = new ButtonGroup();
	}

	private JRadioButton getJRadioButtonConsuntivo() {
		if (jRadioButtonConsuntivo == null) {
			jRadioButtonConsuntivo = new JRadioButton();
			jRadioButtonConsuntivo.setSelected(true);
			jRadioButtonConsuntivo.setText("Consuntivo");
		}
		return jRadioButtonConsuntivo;
	}

	private JRadioButton getJRadioButtonPreventivo() {
		if (jRadioButtonPreventivo == null) {
			jRadioButtonPreventivo = new JRadioButton();
			jRadioButtonPreventivo.setSelected(true);
			jRadioButtonPreventivo.setText("Preventivo");
		}
		return jRadioButtonPreventivo;
	}

	private JButton getBAnnulla() {
		if (bAnnulla == null) {
			bAnnulla = new JButton();
			bAnnulla.setText("Annulla");
			bAnnulla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bAnnullaMouseMouseClicked(event);
				}
			});
		}
		return bAnnulla;
	}

	private JButton getBOk() {
		if (bOk == null) {
			bOk = new JButton();
			bOk.setText("OK");
			bOk.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bOkMouseMouseClicked(event);
				}
			});
		}
		return bOk;
	}

	private JComboBox getTipoBilancio() {
		if (tipoBilancio == null) {
			tipoBilancio = new JComboBox();
			tipoBilancio.setModel(new DefaultComboBoxModel(TipoBilancio.values()));
			tipoBilancio.setDoubleBuffered(false);
			tipoBilancio.setBorder(null);
		}
		return tipoBilancio;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Descrizione :");
		}
		return jLabel2;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getDescrizione());
		}
		return jScrollPane0;
	}

	private JTextArea getDescrizione() {
		if (descrizione == null) {
			descrizione = new JTextArea();
		}
		return descrizione;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Titolo:");
		}
		return jLabel1;
	}

	private JTextField getTitolo() {
		if (titolo == null) {
			titolo = new JTextField();
		}
		return titolo;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Tipo bilancio:");
		}
		return jLabel0;
	}
}
