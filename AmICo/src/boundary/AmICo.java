//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.Condominio;
import datatype.DatiCondominio;
import datatype.Indirizzo;
import datatype.Path;
import datatype.list.Condomini;
import enumeration.Comune;
import enumeration.Provincia;
import enumeration.StatiAmICo;
import executor.GestoreCondomini;
import extras.ImagePanel;

/**
 * @author Federico
 *
 */
public class AmICo extends JFrame implements BaseBoundary{

	public static AmICo amico;
	public static AmICo getInstance(){
		if (amico==null) 
			amico=new AmICo();
		return amico;
	}
	
	private Condomini condomini;
	private StatiAmICo state;
	private ImagePanel logoPanel;
	
	
	private static final long serialVersionUID = 1L;
	private JButton binserisci;
	private JButton bapri;
	private JButton bEsci;
	private JLabel jLabel0;
	private JList lista;
	private JScrollPane jScrollPane0;
	private JLabel jLabel1;
	private JSeparator jSeparator0;
	private JButton jButton0;
	private ButtonGroup buttonGroup1;
	private JSeparator jSeparator1;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AmICo() {
		state=StatiAmICo.base;
		
		initComponents();
		
		//add(getLogoPanel(), new Constraints(new Trailing(12, 329, 10, 10), new Leading(117, 374, 48, 48)));
		
		
		setResizable(false);
	}


	public void apriCondominio(Condominio condominio){
		GestoreCondomini.getInstance().apriCondominio(condominio);
		state=StatiAmICo.inserimentoCondominio;
		
	}
	
	public void  inserisciCondominio() {
		GestoreCondomini.getInstance().inserisciCondominio();
		state=StatiAmICo.inserimentoCondominio;
	}
	
	public void  importaCondominio() {
		//AMM.richiediSelezioneFile();
		state = StatiAmICo.importazioneCondominio;
	}
	
	public void aggiornaCondomini(Condomini condomini){
		this.condomini=condomini;
		
		DefaultListModel listModel = new DefaultListModel();
		
		for (Condominio c : condomini.getCondomini())
		{
			listModel.addElement( c.getDatiC().getId() );
			//System.out.println(c.getDatiC().getId());
		}
		lista.setModel(listModel);
		setTitle("AmICo");
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
	public void selezioneFile(Path path) {
	//	GestoreCondomini.getInstance().importaCondominio(path);
		state=StatiAmICo.selezionePath;
	}
	
	public void esciDaAmICo(){
		GestoreCondomini.getInstance().esciDaAmico();
	}
	
	private void binserisciMouseMouseClicked(MouseEvent event) {
		// FIXME 
		this.inserisciCondominio();
//		InserireNuovoCondominio INC=new InserireNuovoCondominio();
//		INC.setVisible(true);
	}

	private void bapriMouseMouseClicked(MouseEvent event) {
		
		if(lista.getSelectedIndex()>-1) 	
		{
			for (Condominio c : condomini.getCondomini())
			{
				if( c.getDatiC().getId().equals((String)lista.getSelectedValue() ) ) {
					apriCondominio(c);
					setVisible(false);
				}
					
			}
		}
		else JOptionPane.showMessageDialog(this, "devi selezionare un condominio");
		
	}

	private void jButton0MouseMouseClicked(MouseEvent event) {
		GestoreCondomini.getInstance().esciDaAmico();
		this.dispose();
	}
	
	public void ammissibile(Boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void annulla() {
		// TODO Auto-generated method stub
		
	}

	public void fallito() {
		switch (state) {
		case selezionePath:
			//AMM.mostra(CondominioImportatoKO)
			break;
		case inserimentoCondominio:
			// AMM.mostra(condominioInseritoKO);
		default:
			break;
		}
		state=StatiAmICo.base;
		//AMM.mostraCondomini(condomini);
		
	}

	public void fatto() {
		
		switch (state) {
		case condominioAperto:
			//AMM.mostra(condominio chiuso);	
			break;
		case inserimentoCondominio: 
			//AMM.mostra(condominioinseritoOK);
			break;
		case selezionePath:
			//AMM.mostra(CondominioImportatoOK);
			state=StatiAmICo.condominioAperto;
			return;
		default:
			break;
		}
		state=StatiAmICo.base;
		//AMM.mostraCondomini(condomini);
		
		
	}

	public void finito() {
		// TODO Auto-generated method stub
		
	}

	public void ko() {
		// TODO Auto-generated method stub
		
	}

	public void ok() {
		// TODO Auto-generated method stub
		
	}

	

	
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel1(), new Constraints(new Trailing(12, 97, 12, 12), new Leading(14, 44, 10, 10)));
		add(getJSeparator0(), new Constraints(new Bilateral(14, 12, 474), new Leading(64, 10, 78, 78)));
		add(getJLabel0(), new Constraints(new Leading(14, 146, 12, 12), new Leading(80, 12, 12)));
		add(getLogoPanel(), new Constraints(new Trailing(12, 306, 10, 10), new Leading(110, 209, 10, 10)));
		add(getJSeparator1(), new Constraints(new Bilateral(12, 12, 720), new Leading(497, 10, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Bilateral(12, 330, 22), new Leading(110, 381, 48, 48)));
		add(getBapri(), new Constraints(new Trailing(113, 104, 358, 595), new Leading(359, 10, 10)));
		add(getBinserisci(), new Constraints(new Trailing(112, 105, 358, 595), new Leading(393, 10, 10)));
		add(getJButton0(), new Constraints(new Trailing(112, 105, 358, 595), new Leading(429, 10, 10)));
		add(getBEsci(), new Constraints(new Trailing(12, 83, 19, 18), new Leading(503, 12, 12)));
		initButtonGroup1();
		setSize(744, 532);
	}


	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}


	private void initButtonGroup1() {
		buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(getBapri());
		buttonGroup1.add(getBinserisci());
		buttonGroup1.add(getJButton0());
	}


	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Importa");
		}
		return jButton0;
	}


	private JPanel getLogoPanel() {
		if (logoPanel == null) {
			logoPanel = new ImagePanel();
			logoPanel.setAlignmentY(0.5f);
			logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.LINE_AXIS));
			logoPanel.setImage("images/house_logo.png");
		}
		return logoPanel;
	}


	private JButton getBEsci() {
		if (bEsci == null) {
			bEsci = new JButton();
			bEsci.setText("Esci");
			bEsci.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					jButton0MouseMouseClicked(event);
				}
			});
		}
		return bEsci;
	}


	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setFont(new Font("Dialog", Font.BOLD | java.awt.Font.ITALIC, 22));
			jLabel1.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel1.setText("AmICo");
		}
		return jLabel1;
	}


	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getLista());
		}
		return jScrollPane0;
	}

	private JList getLista() {
		if (lista == null) {
			lista = new JList();
		}
		return lista;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Dialog", Font.BOLD | java.awt.Font.ITALIC, 20));
			jLabel0.setText("Condomìni ");
		}
		return jLabel0;
	}


	private JButton getBapri() {
		if (bapri == null) {
			bapri = new JButton();
			bapri.setText("Apri");
			bapri.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bapriMouseMouseClicked(event);
				}
			});
		}
		return bapri;
	}


	private JButton getBinserisci() {
		if (binserisci == null) {
			binserisci = new JButton();
			binserisci.setText("Nuovo");
			binserisci.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					binserisciMouseMouseClicked(event);
				}
			});
		}
		return binserisci;
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
				Condominio c = new Condominio();
				Condominio c2 = new Condominio();
				Condomini cond = new Condomini();
				DatiCondominio datiC= new DatiCondominio();
				DatiCondominio datiC2= new DatiCondominio();
				Indirizzo indi=new Indirizzo();
				
				indi.setCap("16156");
				indi.setComune(Comune.AGLIE);
				indi.setProvincia(Provincia.Alessandria);
				indi.setVia("via gavino");
				
				c2.CreaCondominio();
				c2.setDatiC(datiC2);
				c2.getDatiC().setId("via merano");
				
				
				c.CreaCondominio();
				c.setDatiC(datiC);
				c.getDatiC().setId("via gavino");
				c.getDatiC().setIndirizzo(indi);
				
				cond.inserisciCondominio(c);
				cond.inserisciCondominio(c2);
				
				AmICo frame = new AmICo();
				frame.aggiornaCondomini(cond);
				frame.setTitle("AmICo");
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}
		});
	}


}
