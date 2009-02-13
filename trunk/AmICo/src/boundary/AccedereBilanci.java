//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Bilancio;
import datatype.DatiBilancio;
import datatype.list.Bilanci;
import executor.GestoreBilanci;

/**
 * @author Federico
 *
 */
public class AccedereBilanci extends JPanel implements BaseBoundary {

	private	GestoreBilanci GB;
	private Bilanci bilanci;
	private AccedereCondominioAperto ACA;

	public AccedereBilanci(GestoreBilanci gb, Bilanci b,AccedereCondominioAperto aca) {
		GB=gb;
		ACA=aca;
		initComponents();
		aggiornaBilanci(b);

	}

	public AccedereBilanci() {
		initComponents();
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
			JOptionPane.showMessageDialog(this, "nome bilancio straordinario non unico");
		}
	}

	public void annulla() {
		// TODO Auto-generated method stub
		
	}

	public void fallito() {
		// TODO Auto-generated method stub
		
	}

	public void fatto() {
		// TODO Auto-generated method stub
		
	}

	public void finito() {
		GB.operazioneTerminata();
		
	}

	public void ko() {
		GB.procedi(false);
		JOptionPane.showMessageDialog(this, "bilancio non inserito");
		ACA.setVisible(true);	
	}

	public void ok() {
		GB.procedi(true);
		JOptionPane.showMessageDialog(this, "bilancio inserito");
		ACA.setVisible(true);
	}
	
	public void apriBilancio(Bilancio b)
	{
		GB.apriBilancio(b);
	}
	
	public void inserisciBilancio(DatiBilancio dati)
	{	
		GB.inserisciBilancio(dati);
	}
	
	
	@SuppressWarnings("deprecation")
	public void aggiornaBilanci(Bilanci bil)
	{
			bilanci=bil;
			DefaultListModel listModel = new DefaultListModel();
			
			for (Bilancio b : bilanci.getBilanci()) 
				listModel.addElement(b.getDati().getTitolo()+" "+b.getDati().getTipo().toString()+" (0"+(b.getDati().getInizio().getYear()-100)+")" );

			listaBilanci.setModel(listModel);
	}
	
	private void listaListSelectionValueChanged(ListSelectionEvent event)
	{
	}
	@SuppressWarnings("deprecation")
	private void bApriBilancioMouseMouseClicked(MouseEvent event) {
		for (Bilancio b : bilanci.getBilanci()) {
			if( (b.getDati().getTitolo()+" "+b.getDati().getTipo().toString()+" (0"+(b.getDati().getInizio().getYear()-100)+")").equals( (String)listaBilanci.getSelectedValue()) )
			{
				apriBilancio(b);
			}
		}
	}

/*	private void bChiudiBilancioMouseMouseClicked(MouseEvent event) {	
	}
*/
	private void bRedigereBilancioMouseMouseClicked(MouseEvent event) {
		ACA.setVisible(false);
		new InserireNuovoBilancio(this,ACA);
	}


	private static final long serialVersionUID = 1L;
	private JList listaBilanci;
	private JScrollPane jScrollPane0;
	private JButton bApriBilancio;
//	private JButton bChiudiBilancio;
	private JSeparator jSeparator0;
	private JButton bRedigereBilancio;
	private JLabel jLabel0;
	private JLabel jLabel1;
//	private JButton jButton0;
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel1(), new Constraints(new Leading(22, 428, 10, 10), new Leading(37, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(22, 334, 12, 12), new Leading(352, 10, 10)));
		add(getBRedigereBilancio(), new Constraints(new Leading(419, 147, 12, 12), new Leading(347, 12, 12)));
		add(getJSeparator0(), new Constraints(new Leading(12, 607, 12, 12), new Leading(325, 9, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(22, 293, 10, 10), new Leading(57, 250, 12, 12)));
	//	add(getBMettiInEsercizio(), new Constraints(new Leading(421, 146, 12, 12), new Leading(253, 39, 10, 10)));
	//	add(getBTerminaBilancio(), new Constraints(new Leading(421, 146, 12, 12), new Leading(147, 10, 10)));
		add(getBApriBilancio(), new Constraints(new Leading(419, 146, 12, 12), new Leading(90, 10, 10)));
		setSize(635, 456);
	}

/*	private JButton getBMettiInEsercizio() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("<html><body>Metti In <br>Esercizio</body></html>");
		}
		return jButton0;
	}
*/
	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Elenco dei bilanci in esercizio presenti nel sistema:");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Iinserisci nuovo bilancio in esercizio:");
		}
		return jLabel0;
	}

	private JButton getBRedigereBilancio() {
		if (bRedigereBilancio == null) {
			bRedigereBilancio = new JButton();
			bRedigereBilancio.setText("Redigere bilancio");
			bRedigereBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bRedigereBilancioMouseMouseClicked(event);
				}
			});
		}
		return bRedigereBilancio;
	}

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}

/*	private JButton getBTerminaBilancio() {
		if (bChiudiBilancio == null) {
			bChiudiBilancio = new JButton();
			bChiudiBilancio.setText("<html><body>Termina Esercizio<br> Bilancio</body></html>");
			bChiudiBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bChiudiBilancioMouseMouseClicked(event);
				}
			});
		}
		return bChiudiBilancio;
	}
*/
	private JButton getBApriBilancio() {
		if (bApriBilancio == null) {
			bApriBilancio = new JButton();
			bApriBilancio.setText("Apri bilancio");
			bApriBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bApriBilancioMouseMouseClicked(event);
				}
			});
		}
		return bApriBilancio;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getListaBilanci());
		}
		return jScrollPane0;
	}

	private JList getListaBilanci() {
		if (listaBilanci == null) {
			listaBilanci = new JList();
			
			DefaultListModel listModel = new DefaultListModel();
			listaBilanci.setModel(listModel);
			listaBilanci.addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent event) {
					listaListSelectionValueChanged(event);
				}
			});
		}
		return listaBilanci;
	}

	/**
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
				frame.setTitle("AccedereBilanci");
				AccedereBilanci content = new AccedereBilanci();
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
