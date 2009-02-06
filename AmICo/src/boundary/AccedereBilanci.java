//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dyno.visual.swing.layouts.Bilateral;
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
	private InserireNuovoBilancio INB;

	public AccedereBilanci(GestoreBilanci gb, Bilanci b) {
		GB=gb;
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
		
	}

	public void ok() {
		GB.procedi(true);
		JOptionPane.showMessageDialog(this, "bilancio inserito");
	}
	
	public void apriBilancio(Bilancio b)
	{
		GB.apriBilancio(b);
	}
	
	public void inserisciBilancio(DatiBilancio dati)
	{	
		GB.inserisciBilancio(dati);
	}
	
	
	public void aggiornaBilanci(Bilanci bil)
	{
			bilanci=bil;
			DefaultListModel listModel = new DefaultListModel();
			
			for (Bilancio b : bilanci.getBilanci()) 
				listModel.addElement(b.getDati().getTitolo() );

			listaBilanci.setModel(listModel);
	}
	
	private void listaListSelectionValueChanged(ListSelectionEvent event)
	{
	}
	
	private void bApriBilancioMouseMouseClicked(MouseEvent event) {
		for (Bilancio b : bilanci.getBilanci()) {
			if(b.getDati().getTitolo().equals( (String)listaBilanci.getSelectedValue()) )
			{
				apriBilancio(b);
			}
		}
	}

	private void bChiudiBilancioMouseMouseClicked(MouseEvent event) {
	}

	private void bRedigereBilancioMouseMouseClicked(MouseEvent event) {
		INB = new InserireNuovoBilancio(this);
	}


	private static final long serialVersionUID = 1L;
	private JList listaBilanci;
	private JScrollPane jScrollPane0;
	private JButton bApriBilancio;
	private JButton bChiudiBilancio;
	private JSeparator jSeparator0;
	private JButton bRedigereBilancio;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJSeparator0(), new Constraints(new Bilateral(12, 12, 402), new Leading(262, 9, 10, 10)));
		add(getBChiudiBilancio(), new Constraints(new Leading(420, 146, 12, 12), new Leading(171, 10, 10)));
		add(getBApriBilancio(), new Constraints(new Leading(420, 146, 12, 12), new Leading(92, 10, 10)));
		add(getJLabel1(), new Constraints(new Leading(22, 393, 10, 10), new Leading(37, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(22, 293, 10, 10), new Leading(57, 193, 12, 12)));
		add(getBRedigereBilancio(), new Constraints(new Leading(420, 147, 12, 12), new Leading(277, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(26, 334, 10, 10), new Leading(282, 12, 12)));
		setSize(631, 343);
		
		
	}

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

	private JButton getBChiudiBilancio() {
		if (bChiudiBilancio == null) {
			bChiudiBilancio = new JButton();
			bChiudiBilancio.setText("Chiudi bilancio");
			bChiudiBilancio.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bChiudiBilancioMouseMouseClicked(event);
				}
			});
		}
		return bChiudiBilancio;
	}

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
