//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

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
	private String nome,descrizione;

	
    DefaultTableModel dm = new DefaultTableModel();

	public InserisciTabellaMillesimaleProprieta(UnitaImmobiliari unita)// per ora inserisce solo una tabella e non termina
	{
		this.unita=unita;
		state=StatiAccedereTabelleMillesimali.base;
		initComponents();
		
		setVisible(true);
		this.setTitle("inserimento tabella millesimale");
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
		// TODO Auto-generated method stub
		
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
		//AMM.mostra(TabellaMillesimaleInseritaOK);
		state=StatiAccedereTabelleMillesimali.base;
	}

	private void continuaMouseMouseClicked(MouseEvent event)
	{
		ArrayList<Float> lista = new ArrayList<Float>();
		
		for (int i=0;i<unita.getImmobili().size();i++)
		{
			if(dm.getValueAt(i,1)=="")
			{
				JOptionPane.showMessageDialog(this, "inserire prima tutti i millesimi");
				return;
			}

			lista.add(Float.parseFloat((String)dm.getValueAt(i,1)) );

		}
		m.setListaMillesimi(lista);
		
		if(m.somma()==1000)
		{		
			DatiTabellaMillesimale dati= new DatiTabellaMillesimale();
			dati.setNome(nome);
			dati.setDescrizione(descrizione);
			GestoreCondomini.getInstance().passaTabellaMillesimaleProprieta(dati,m);
		}
		else JOptionPane.showMessageDialog(this, "la somma deve fare 1000 invece di "+m.somma());
			
		
	}
	
	private void annullaMouseMouseClicked(MouseEvent event)
	{
		GestoreCondomini.getInstance().operazioneTerminata();
	}

	
	
	
	
	private static final long serialVersionUID = 1L;
	private JTable tabella;
	private JScrollPane millesimi;
	private JButton bContinua;
	private JSeparator jSeparator0;
	private JButton bAnnulla;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJSeparator0(), new Constraints(new Bilateral(11, 12, 581), new Trailing(42, 10, 50, 250)));
		add(getBAnnulla(), new Constraints(new Leading(455, 96, 10, 10), new Trailing(12, 50, 250)));
		add(getBContinua(), new Constraints(new Leading(123, 10, 10), new Trailing(12, 50, 250)));
		add(getMillesimi(), new Constraints(new Bilateral(12, 6, 22), new Leading(12, 226, 55, 64)));
		setSize(605, 355);
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
		if (tabella == null) {
		//    DefaultTableModel dm = new DefaultTableModel();
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
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new InserisciTabellaMillesimaleProprieta(new UnitaImmobiliari());

			}
		});
	}

	 
}