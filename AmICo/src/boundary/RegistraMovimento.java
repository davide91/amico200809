//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.VoceBilancio;
import datatype.DatiMovimentoCassa;
import datatype.Euro;
import datatype.list.VociBilancio;
import enumeration.TipoVoce;

/**
 * @author Federico
 *
 */
public class RegistraMovimento extends JFrame {

	private VociBilancio voci;	
	private ButtonGroup buttonGroup; 
	private AccedereCassa AC;
	private boolean ciSono=false;
	
	public RegistraMovimento(AccedereCassa ac) {
		AC=ac;
		initComponents();
		setLocationRelativeTo(null);
		setTitle("Registrando movimento");
		this.setVisible(true);
	}
	
	public void aggiornaVociBilancio(VociBilancio voci) {
		this.voci=voci;
		buttonGroup = new ButtonGroup();
		int count=0;

		DefaultTableModel dm = new DefaultTableModel();
		
		dm.setDataVector(new String[][]{},new String[]{ "Nome Voce","Importo","Seleziona" });
		
		for (VoceBilancio v : voci.getVoci())
		{
			if(v.getDati().getTipo() == TipoVoce.incasso)
			{
				dm.addRow(new Object[]{v.getDati().getTitolo(),v.getDati().getImporto().toString(),new JRadioButton()});
				buttonGroup.add((JRadioButton)dm.getValueAt(count, 2));
				ciSono=true;
			
			}
			else
			{
				dm.addRow(new Object[]{
						v.getDati().getTitolo(),
						"-"+v.getDati().getImporto().toString(),
						new JRadioButton()});
				buttonGroup.add((JRadioButton)dm.getValueAt(count, 2));
				ciSono=true;
			}	
			count++;
		}
		
		vocibilancio.setModel(dm);
		
		vocibilancio.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		vocibilancio.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));
		vocibilancio.setAutoCreateRowSorter(true);
		
		if(!ciSono)
		{
			this.bConferma.setEnabled(false);
		}
	}
	
	private void bAnnullaMouseMouseClicked(MouseEvent event) {
		this.dispose();
	}

	private void bConfermaMouseMouseClicked(MouseEvent event) {
		if(ciSono)
		{
			DatiMovimentoCassa dati=new DatiMovimentoCassa();
			dati.setMotivazione(motivazione.getText());
			
			//cerco la voce di bilancio selezionata
			int i;
			boolean trovato = false;
			Enumeration<AbstractButton> e= buttonGroup.getElements();
			for (i=0; e.hasMoreElements();i++ )
		       if ( ((JRadioButton)e.nextElement()).getModel() == buttonGroup.getSelection()) 
		       {
		    	   trovato = true;
		    	   if(voci.getVoci().get(i).getDati().getTipo() == TipoVoce.spesa)
		    		   dati.setImportoMovimento(new Euro((-1)*voci.getVoci().get(i).getDati().getImporto().recuperaValore()));
		    	   else
		    		   dati.setImportoMovimento(voci.getVoci().get(i).getDati().getImporto());
		    	   break;
		       }
			
			if(!trovato)
				JOptionPane.showMessageDialog(this, "Selezionare prima la voce desiderata");
			
			AC.passaVoceBilancio(voci.getVoci().get(i), dati);
			this.dispose();
		}
	}

	
	private static final long serialVersionUID = 1L;
	private JButton bAnnulla;
	private JButton bConferma;
	private JLabel jLabel0;
	private JTextField motivazione;
	private JTable vocibilancio;
	private JScrollPane jScrollPane0;
	
	public RegistraMovimento() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getBConferma(), new Constraints(new Leading(59, 120, 10, 10), new Leading(363, 12, 12)));
		add(getBAnnulla(), new Constraints(new Leading(384, 122, 10, 10), new Leading(363, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(18, 83, 10, 10), new Leading(26, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(15, 547, 10, 10), new Leading(49, 296, 12, 12)));
		add(getMotivazione(), new Constraints(new Leading(113, 198, 12, 12), new Leading(24, 12, 12)));
		setSize(582, 434);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getVocibilancio());
		}
		return jScrollPane0;
	}

	private JTable getVocibilancio() {
		if (vocibilancio == null) {

			DefaultTableModel dm = new DefaultTableModel();
			
			dm.setDataVector(new String[][]{},new String[]{ "Nome Voce","Importo","Seleziona" });
			
			vocibilancio = new JTable(dm)
		    {
				private static final long serialVersionUID = 1L;

				public void tableChanged(TableModelEvent e)
			    {
					super.tableChanged(e);
			        repaint();
			        if(vocibilancio != null)
			        	vocibilancio.repaint();
			    }
		    };   
		    vocibilancio.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		    vocibilancio.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));

		}
		return vocibilancio;
	}

	private JTextField getMotivazione() {
		if (motivazione == null) {
			motivazione = new JTextField();
		}
		return motivazione;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Motivazione:");
		}
		return jLabel0;
	}

	private JButton getBConferma() {
		if (bConferma == null) {
			bConferma = new JButton();
			bConferma.setText("Conferma");
			bConferma.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bConfermaMouseMouseClicked(event);
				}
			});
		}
		return bConferma;
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
}