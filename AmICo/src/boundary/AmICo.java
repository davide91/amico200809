//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Condominio;
import datatype.DatiCondominio;
import datatype.Path;
import datatype.list.Condomini;
import enumeration.StatiAmICo;
import executor.GestoreCondomini;

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
	private GestoreCondomini GC;
	
	
	private static final long serialVersionUID = 1L;
	private JButton binserisci;
	private JButton bapri;
	private JLabel jLabel0;
	private JMenuItem jMenuItem0;
	private JMenuItem jMenuItem1;
	private JMenu file;
	private JMenuBar jMenuBar0;
	private JList lista;
	private JScrollPane jScrollPane0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public AmICo() {
	//	initComponents();
	}

	public void creaAmICo(Condomini condomini, GestoreCondomini GC){
		this.condomini=condomini;
		this.GC=GC;
		initComponents();
		state=StatiAmICo.base;
		this.aggiornaCondomini(condomini);

	}
	
	
	
	public void apriCondominio(Condominio condominio){
		GC.apriCondominio(condominio);
		state=StatiAmICo.inserimentoCondominio;
		
	}
	
	public void  inserisciCondominio() {
		GC.inserisciCondominio();
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
			System.out.println(c.getDatiC().getId());
		}
		lista.setModel(listModel);
		
		
	}
	
	public void selezioneFile(Path path) {
	//	GC.importaCondominio(path);
		state=StatiAmICo.selezionePath;
	}
	
	public void esciDaAmICo(){
		GC.esciDaAmico();
	}
	
	

	private void binserisciMouseMouseClicked(MouseEvent event) {
		this.inserisciCondominio();
	}

	private void bapriMouseMouseClicked(MouseEvent event) {
		
		if(lista.getSelectedIndex()>-1) {
			// temporaneo
			AccedereCondominioAperto ACA =new AccedereCondominioAperto(null,null);
			ACA.setTitle((String)lista.getSelectedValue());
			ACA.setVisible(true);/* si cancella quello prima e si mette quello dopo per integrazione TODO
			for (Condominio c : condomini.getCondomini())
			{
				if( c.getDatiC().getId().equals((String)lista.getSelectedValue() ) )
					this.apriCondominio(c);
			}*/
			this.dispose();
		}
		
		
	}
	private void jMenuItem0MouseMouseClicked(MouseEvent event) {
		this.dispose();
	}
	private void jMenuItem1MouseMouseClicked(MouseEvent event) {
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
		add(getBinserisci(), new Constraints(new Leading(240, 182, 12, 12), new Leading(179, 10, 10)));
		add(getBapri(), new Constraints(new Leading(238, 182, 10, 10), new Leading(108, 10, 10)));
		add(getJScrollPane0(), new Constraints(new Leading(27, 146, 10, 10), new Leading(82, 189, 10, 10)));
		add(getJLabel0(), new Constraints(new Leading(27, 12, 12), new Leading(28, 10, 10)));
		setJMenuBar(getJMenuBar0());
		setSize(445, 374);
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

	private JMenuBar getJMenuBar0() {
		if (jMenuBar0 == null) {
			jMenuBar0 = new JMenuBar();
			jMenuBar0.add(getFile());
		}
		return jMenuBar0;
	}

	private JMenu getFile() {
		if (file == null) {
			file = new JMenu();
			file.setText("File");
			file.setOpaque(false);
			file.add(getJMenuItem0());
			file.add(getJMenuItem1());
		}
		return file;
	}

	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("esci");
			jMenuItem1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					jMenuItem1MouseMouseClicked(event);
				}
			});
		}
		return jMenuItem1;
	}

	private JMenuItem getJMenuItem0() {
		if (jMenuItem0 == null) {
			jMenuItem0 = new JMenuItem();
			jMenuItem0.setText("Importare condominio");
			jMenuItem0.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					jMenuItem0MouseMouseClicked(event);
				}
			});
		}
		return jMenuItem0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
			jLabel0.setText("Ecco i condomini registrati nel sistema:");
		}
		return jLabel0;
	}

	private JButton getBapri() {
		if (bapri == null) {
			bapri = new JButton();
			bapri.setText("Apri condominio");
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
			binserisci.setText("Inserisci nuovo condominio");
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
				
				c2.CreaCondominio();
				c2.setDatiC(datiC2);
				c2.getDatiC().setId("via merano");
				
				c.CreaCondominio();
				c.setDatiC(datiC);
				c.getDatiC().setId("via gavino");
				
				cond.inserisciCondominio(c);
				cond.inserisciCondominio(c2);
				
				AmICo frame = new AmICo();
				frame.creaAmICo(cond,null);
				frame.setTitle("AmICo");
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}


}
