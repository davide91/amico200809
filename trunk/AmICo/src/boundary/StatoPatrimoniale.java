//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.VoceBilancio;
import datatype.list.VociBilancio;
import enumeration.StatoBilancio;
import enumeration.TipoVoce;

/**
 * @author Federico
 *
 */
public class StatoPatrimoniale extends JPanel {

	private AccedereBilancioAperto ABA;
	private VociBilancio vb;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	public StatoPatrimoniale() {
		initComponents();
	}
	
	public StatoPatrimoniale(AccedereBilancioAperto aba) {
		ABA=aba;
		initComponents();
	}
	
	public void aggiorna(VociBilancio vociBilancio)
	{
		this.vb= vociBilancio;
		
		buttonGroup = new ButtonGroup();
		int countAttivo=0;
		int countPassivo=0;
		
		DefaultTableModel dmAttivo = new DefaultTableModel();
		DefaultTableModel dmPassivo = new DefaultTableModel();
		
		dmAttivo.setDataVector(new String[][]{},new String[]{ "Nome Voce","Importo(euro)","Seleziona" });
		dmPassivo.setDataVector(new String[][]{},new String[]{ "Nome Voce","Importo(euro)","Seleziona" });
		
		for (VoceBilancio v : vb.getVoci())
		{
			if(v.getDati().getTipo() == TipoVoce.incasso)
			{
				dmAttivo.addRow(new Object[]{v.getDati().getTitolo(),v.getDati().getImporto().toString(),new JRadioButton()});
				buttonGroup.add((JRadioButton)dmAttivo.getValueAt(countAttivo, 2));
				countAttivo++;
			}
			else
			{
				dmPassivo.addRow(new Object[]{v.getDati().getTitolo(),v.getDati().getImporto().toString(),new JRadioButton()});
				buttonGroup.add((JRadioButton)dmPassivo.getValueAt(countPassivo, 2));
				countPassivo++;
			}
		}
		
		jTablePassivo.setModel(dmPassivo);
		jTableAttivo.setModel(dmAttivo);
		
		jTablePassivo.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		jTablePassivo.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));
		jTablePassivo.setAutoCreateRowSorter(true);
		
		jTableAttivo.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		jTableAttivo.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));
		jTableAttivo.setAutoCreateRowSorter(true);
	}
	
	
	private void bInserisciVoceBilancioMouseMouseClicked(MouseEvent event) {
		ABA.inserisci();
	}

	private void bEliminaVoceBilancioMouseMouseClicked(MouseEvent event) {
		
		//cerco la voce di bilancio selezionata
		int i;
		boolean trovato = false;
		Enumeration<AbstractButton> e= buttonGroup.getElements();
		for (i=0; e.hasMoreElements();i++ )
	       if ( ((JRadioButton)e.nextElement()).getModel() == buttonGroup.getSelection()) 
	       {
	    	   trovato = true;
	       	   ABA.eliminaVoceBilancio(vb.getVoci().get(i));
	       	   break;
	       }
		
		if(!trovato)
			JOptionPane.showMessageDialog(this, "Selezionare prima la voce desiderata");
	}

//	private void bModificaVoceBilancioMouseMouseClicked(MouseEvent event) {
//	}

	private void chiudiMouseMouseClicked(MouseEvent event) {
		ABA.chiudi();
	}

	private void bMettiInEsercizioMouseMouseClicked(MouseEvent event) {
		ABA.mettiInEsercizio();
	}

	private void bTerminaEsercizioMouseMouseClicked(MouseEvent event) {
		ABA.terminaEsercizioBilancio();
	}
	
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JSeparator jSeparator0;
	private JSeparator jSeparator1;
	private JTable jTableAttivo;
	private JScrollPane jScrollPane0;
	private JTable jTablePassivo;
	private JScrollPane jScrollPane1;
	private JButton bInserisciVoceBilancio;
	private JButton bEliminaVoceBilancio;
	private JButton bChiudiBilancio;
	private JButton bTerminaEsercizio;
	private JButton bMettiInEsercizio;
	private void initComponents() {
		setBackground(Color.white);
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Leading(6, 350, 12, 12), new Leading(43, 313, 12, 12)));
		add(getJScrollPane1(), new Constraints(new Leading(380, 350, 12, 12), new Leading(43, 312, 12, 12)));
		add(getJSeparator0(), new Constraints(new Leading(368, 8, 12, 12), new Leading(21, 340, 12, 12)));
		add(getJSeparator1(), new Constraints(new Leading(4, 726, 12, 12), new Leading(362, 18, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(511, 111, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(137, 120, 10, 10), new Leading(13, 22, 12, 12)));
		add(getBEliminaVoceBilancio(), new Constraints(new Leading(31, 212, 10, 10), new Leading(421, 12, 12)));
		add(getBInserisciVoceBilancio(), new Constraints(new Leading(31, 212, 12, 12), new Leading(379, 12, 12)));
		add(getBChiudiBilancio(), new Constraints(new Leading(266, 212, 12, 12), new Leading(421, 12, 12)));
		add(getBTerminaEsercizio(), new Constraints(new Leading(508, 202, 10, 10), new Leading(421, 12, 12)));
		add(getBMettiInEsercizio(), new Constraints(new Leading(508, 202, 12, 12), new Leading(379, 12, 12)));
		initButtonGroup();
		setSize(805, 516);
	}

	private void initButtonGroup() {
		buttonGroup = new ButtonGroup();
	}

	private JButton getBMettiInEsercizio() {
		if (bMettiInEsercizio == null) {
			bMettiInEsercizio = new JButton();
			bMettiInEsercizio.setText("Metti In Esercizio");
			bMettiInEsercizio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bMettiInEsercizioMouseMouseClicked(event);
				}
			});
		}
		return bMettiInEsercizio;
	}

	private JButton getBTerminaEsercizio() {
		if (bTerminaEsercizio == null) {
			bTerminaEsercizio = new JButton();
			bTerminaEsercizio.setText("Termina Esercizio");
			bTerminaEsercizio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bTerminaEsercizioMouseMouseClicked(event);
				}
			});
		}
		return bTerminaEsercizio;
	}

	private JButton getBChiudiBilancio() {
		if (bChiudiBilancio == null) {
			bChiudiBilancio = new JButton();
			bChiudiBilancio.setText("Chiudi bilancio");
			bChiudiBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					chiudiMouseMouseClicked(event);
				}
			});
		}
		return bChiudiBilancio;
	}

	private JButton getBEliminaVoceBilancio() {
		if (bEliminaVoceBilancio == null) {
			bEliminaVoceBilancio = new JButton();
			bEliminaVoceBilancio.setText("Elimina voce bilancio");
			bEliminaVoceBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bEliminaVoceBilancioMouseMouseClicked(event);
				}
			});
		}
		return bEliminaVoceBilancio;
	}
	
	private JButton getBInserisciVoceBilancio() {
		if (bInserisciVoceBilancio == null) {
			bInserisciVoceBilancio = new JButton();
			bInserisciVoceBilancio.setText("Inserisci voce bilancio");
			bInserisciVoceBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bInserisciVoceBilancioMouseMouseClicked(event);
				}
			});
		}
		return bInserisciVoceBilancio;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}
	 private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	 private JTable getJTable0() {
			if (jTableAttivo == null) {

				DefaultTableModel dm = new DefaultTableModel();
				
				dm.setDataVector(new String[][]{},new String[]{ "Nome Voce","Importo","Seleziona" });
				
				jTableAttivo = new JTable(dm)
			    {
					private static final long serialVersionUID = 1L;

					public void tableChanged(TableModelEvent e)
				    {
						super.tableChanged(e);
				        repaint();
				        if(jTablePassivo != null)
				        	jTablePassivo.repaint();
				    }
			    };   
			    jTableAttivo.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
			    jTableAttivo.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));

			}
			
			
			
			return jTableAttivo;
		}
	 
	 
	 private JTable getJTable1() {
			if (jTablePassivo == null) {

				DefaultTableModel dm = new DefaultTableModel();
				
				dm.setDataVector(new String[][]{},new String[]{ "Nome Voce","Importo","Seleziona" });
				
				jTablePassivo = new JTable(dm)
			    {
					private static final long serialVersionUID = 1L;

					public void tableChanged(TableModelEvent e)
				    {
						super.tableChanged(e);
				        repaint();
				        if(jTableAttivo != null)
				        	jTableAttivo.repaint();
				    }
			    };   
			    jTablePassivo.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
			    jTablePassivo.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));

			}
			
			
			
			return jTablePassivo;
		}
	 
	 private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
			jSeparator0.setOrientation(SwingConstants.VERTICAL);
		}
		return jSeparator0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
			jLabel1.setText("PASSIVO");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
			jLabel0.setText("ATTIVO");
		}
		return jLabel0;
	}
/**
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

	
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("StatoPatrimoniale");
				StatoPatrimoniale content = new StatoPatrimoniale();
				content.setPreferredSize(content.getSize());
				frame.add(content, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
 
*/



}
