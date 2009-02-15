//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.Millesimo;
import store.POJO.UnitaImmobiliare;
import datatype.DatiTabellaMillesimale;
import datatype.list.Millesimi;
import datatype.list.UnitaImmobiliari;
import enumeration.StatiAccedereTabelleMillesimali;
import executor.GestoreCondomini;

/**
 * @author Federico
 *
 */
public class InserisciTabellaMillesimaleProprieta extends JFrame implements BaseBoundary{

	private StatiAccedereTabelleMillesimali state;
	private UnitaImmobiliari unita;
	private Millesimi m;

    private DefaultTableModel dm = new DefaultTableModel();

	public InserisciTabellaMillesimaleProprieta(UnitaImmobiliari unita)
	{
		this.unita=unita;
		state=StatiAccedereTabelleMillesimali.base;
		initComponents();
		
		setLocationRelativeTo(null);
		setVisible(true);
		this.setTitle("inserimento tabella millesimale");
		m=new Millesimi();
	}

	public InserisciTabellaMillesimaleProprieta() {
		initComponents();
	}

	/*
	public void inserisciMillesimi(Millesimi millesimi){
		if (millesimi.somma()==1000){
			state=StatiAccedereTabelleMillesimali.attesaControlloMillesimi;
			GestoreCondomini.getInstance().passaTabellaMillesimaleProprieta(millesimi);
			
		}
	}
	*/

	public void ammissibile(Boolean b) {
		if (b){
			//AMM.richiestaConferma();
			state=StatiAccedereTabelleMillesimali.attesaConfermaMillesimi;
			
		}
		else {
			//AMM.mostra(NomeTabellaNonUnico);
			state=StatiAccedereTabelleMillesimali.base;
		}
		
	}

	public void annulla() {
		GestoreCondomini.getInstance().operazioneAnnullata();
		this.dispose();
	}

	public void fallito() {
		// TODO Auto-generated method stub
		
	}

	public void fatto() {
		// TODO Auto-generated method stub
		
	}

	public void finito() {
		GestoreCondomini.getInstance().operazioneTerminata();
	}

	public void ko() {
		switch (state) {
		case attesaConfermaInserimento:
		//	GestoreCondomini.getInstance().modificaTabellaMillesimale(tabellaMillesimale, datiTabella.getDescrizione(), )
			break;
		case attesaConfermaMillesimi:
			GestoreCondomini.getInstance().procedi(false);
			//AMM.mostra(TabellaMillesimaleInseritaKO);
			break;
		default:
			break;
		}
		state=StatiAccedereTabelleMillesimali.base;
		
	}

	public void ok() {
		GestoreCondomini.getInstance().procedi(true);
		state=StatiAccedereTabelleMillesimali.base;
	}

	private void continuaMouseMouseClicked(MouseEvent event)
	{
		//ArrayList<Float> lista = new ArrayList<Float>();
		Millesimi lista = new Millesimi();
		
		for (int i=0;i<unita.getImmobili().size();i++)
		{
			if(dm.getValueAt(i,1)=="")
			{
				JOptionPane.showMessageDialog(this, "Inserire prima tutti i millesimi");
				return;
			}

			Millesimo mill = new Millesimo();
			mill.setQuota(Float.parseFloat((String)dm.getValueAt(i,1)));
			mill.setQuotaDi(unita.getImmobili().get(i));
			lista.inserisciMillesimo(mill);
		} 
		m.setListaMillesimi(lista);
		
		if(!(m.somma()>1000))
		{		
			if(nome.getText()=="")
				JOptionPane.showMessageDialog(this, "inserire prima il nome");
			else if (descrizione.getText()=="") 				
				JOptionPane.showMessageDialog(this, "inserire prima la descrizione della tabella millesimale");
			else
			{
				DatiTabellaMillesimale dati= new DatiTabellaMillesimale();
				dati.setNome(nome.getText());
				dati.setDescrizione(descrizione.getText());
				GestoreCondomini.getInstance().passaTabellaMillesimaleProprieta(dati,m);
			}

			int c = JOptionPane.showConfirmDialog(this, "La somma delle tabelle risulta "+m.somma()+". \n Inserire?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
			if (c==0)
			{
					GestoreCondomini.getInstance().procedi(true);
					
				
			}
			this.dispose();

		}
	else JOptionPane.showMessageDialog(this, "la somma deve fare 1000 invece di "+m.somma());
			
		
	}
	
	private void annullaMouseMouseClicked(MouseEvent event)
	{
		annulla();
	}

	
	private static final long serialVersionUID = 1L;
	private JTable tabella;
	private JScrollPane millesimi;
	private JButton bContinua;
	private JSeparator jSeparator0;
	private JButton bAnnulla;
	
	private JTextPane descrizione;
	private JScrollPane jScrollPane0;
	private JTextField nome;
	private JLabel jLabel0;
	private JLabel jLabel1;
	
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJSeparator0(), new Constraints(new Bilateral(11, 12, 581), new Trailing(42, 10, 50, 250)));
		add(getBAnnulla(), new Constraints(new Leading(455, 96, 10, 10), new Trailing(12, 50, 250)));
		add(getBContinua(), new Constraints(new Leading(123, 10, 10), new Trailing(12, 50, 250)));
		add(getMillesimi(), new Constraints(new Bilateral(12, 150, 22), new Leading(50, 226, 55, 64)));
		
		add(getJLabel1(), new Constraints(new Leading(448, 10, 10), new Leading(14, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Trailing(13, 100, 60, 166), new Leading(14, 271, 10, 10)));
		add(getNome(), new Constraints(new Leading(312, 110, 10, 10), new Leading(14, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(266, 10, 10), new Leading(16, 12, 12)));
		setSize(645, 380);
		this.addWindowListener(new WindowAdapter() {  
			 public void windowClosing(WindowEvent we) {  
				 GestoreCondomini.getInstance().operazioneAnnullata();
				 annulla();
			 	}  
			 });  
	}
	

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Descrizione:");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Nome:");
		}
		return jLabel0;
	}

	private JTextField getNome() {
		if (nome == null) {
			nome = new JTextField();
		}
		return nome;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getDescrizione());
		}
		return jScrollPane0;
	}

	private JTextPane getDescrizione() {
		if (descrizione == null) {
			descrizione = new JTextPane();
		}
		return descrizione;
	}
	
	

	private JButton getBAnnulla() {
		if (bAnnulla == null) {
			bAnnulla = new JButton();
			bAnnulla.setText("Annulla");
			bAnnulla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					annullaMouseMouseClicked(event);
				}
			});
		}
		return bAnnulla;
	}

	private JSeparator getJSeparator0() {
		if (jSeparator0 == null) {
			jSeparator0 = new JSeparator();
		}
		return jSeparator0;
	}

	private JButton getBContinua() {
		if (bContinua == null) {
			bContinua = new JButton();
			bContinua.setText("Continua");
			bContinua.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					continuaMouseMouseClicked(event);
				}
			});
		}
		return bContinua;
	}

	private JScrollPane getMillesimi() {
		if (millesimi == null) {
			millesimi = new JScrollPane();
			millesimi.setViewportView(getTabella());
		}
		return millesimi;
	}

	private JTable getTabella() {
		if (tabella == null) 
		{
		    dm.setDataVector(
		      new String[][]{},
		      new String[]{ "Unita'", "Coefficente", }
		      );

		    
		    for (UnitaImmobiliare u : unita.getImmobili()) {
				dm.addRow(new String[]{u.getDatiUnitaImmobiliare().getId(),""});
			}
		    tabella = new JTable(dm);
		}
		return tabella;
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
				new InserisciTabellaMillesimaleProprieta(new UnitaImmobiliari());

			}
		});
	}
 */
	 
}