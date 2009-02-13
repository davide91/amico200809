//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import store.POJO.Millesimo;
import store.POJO.TabellaMillesimale;
import store.POJO.UnitaImmobiliare;
import datatype.DatiTabellaMillesimale;
import datatype.list.Millesimi;
import datatype.list.UnitaImmobiliari;
import enumeration.StatiAccedereTabelleMillesimali;
import executor.GestoreCondomini;
import executor.GestoreCondominioAperto;

/**
 * @author Federico
 *
 */
public class InserisciModificaTabellaMillesimale extends JFrame implements BaseBoundary{

	private StatiAccedereTabelleMillesimali state;
	private UnitaImmobiliari unita;
	private Millesimi m;
	private TabellaMillesimale tabellaMillesimale = null;
	private AccedereTabelleMillesimali ATM;
	private GestoreCondominioAperto GCA;

    private DefaultTableModel dm = new DefaultTableModel();

	public InserisciModificaTabellaMillesimale(AccedereTabelleMillesimali atm, UnitaImmobiliari unita,GestoreCondominioAperto gca)
	{
		GCA=gca;
		ATM=atm;
		this.unita=unita;
		state=StatiAccedereTabelleMillesimali.base;
		initComponents();
		
		setLocationRelativeTo(null);
		setVisible(true);
		this.setTitle("inserimento tabella millesimale");
		m=new Millesimi();
	}

	public InserisciModificaTabellaMillesimale(AccedereTabelleMillesimali atm,UnitaImmobiliari unita,TabellaMillesimale t,GestoreCondominioAperto gca)
	{
		GCA=gca;
		ATM=atm;
		this.unita=unita;
		this.tabellaMillesimale=t;
		state=StatiAccedereTabelleMillesimali.base;
		initComponents();
		
		nome.setText( t.getDati().getNome());
		nome.setEditable(false);
		
		descrizione.setText(t.getDati().getDescrizione());
		
		setLocationRelativeTo(null);
		setVisible(true);
		this.setTitle("modifica tabella millesimale");
		m=new Millesimi();
	}
	
	

	public void ammissibile(Boolean b) {
		if (b){
			state=StatiAccedereTabelleMillesimali.attesaConfermaMillesimi;
			
			if(tabellaMillesimale==null)
			{	
				int c = JOptionPane.showConfirmDialog(this, "Sei sicuro?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if (c==0)
				{
					ATM.procedi(true);
					ATM.finito();
					JOptionPane.showMessageDialog(this, "Tabella Immobiliare Inserita");
					GCA.visibile(true);
				}
				else
				{
					ATM.procedi(false);
						JOptionPane.showMessageDialog(this, "Tabella Immobiliare Non Inserita");
						GCA.visibile(true);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Tabella Immobiliare Modificata");
				ATM.finito();
			}
		}
		else {
			state=StatiAccedereTabelleMillesimali.base;
			JOptionPane.showMessageDialog(this, "Tabella Immobiliare Non Modificata");
		}
		this.dispose();
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
	//	GestoreCondomini.getInstance().operazioneTerminata();
	}

	public void ko() {
		switch (state) {
		case attesaConfermaInserimento:
		//	GestoreCondomini.getInstance().modificaTabellaMillesimale(tabellaMillesimale, datiTabella.getDescrizione(), )
			break;
		case attesaConfermaMillesimi:
			GestoreCondomini.getInstance().procedi(false);
			break;
		default:
			break;
		}
		state=StatiAccedereTabelleMillesimali.base;
		
	}

	public void ok() {
	//	GestoreCondomini.getInstance().procedi(true);
		state=StatiAccedereTabelleMillesimali.base;
	}

	private void continuaMouseMouseClicked(MouseEvent event)
	{
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
		
		if(nome.getText()=="")
				JOptionPane.showMessageDialog(this, "inserire prima il nome");
			else if (descrizione.getText()=="") 				
				JOptionPane.showMessageDialog(this, "inserire prima la descrizione della tabella millesimale");
			else
			{
				DatiTabellaMillesimale dati= new DatiTabellaMillesimale();
				dati.setNome(nome.getText());
				dati.setDescrizione(descrizione.getText());
				
				int c = JOptionPane.showConfirmDialog(this, "La somma delle tabelle risulta "+m.somma()+". \n Inserire?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if (c==0)
				{
					if(tabellaMillesimale==null)
						ATM.inserisciTabellaMillesimale(dati,m);
					else
						ATM.modificaTabellaMillesimale(tabellaMillesimale, getDescrizione().getText(), lista);
					
				}
			}
	}
	
	private void annullaMouseMouseClicked(MouseEvent event)
	{
		GCA.visibile(true);
		ATM.finito();
		this.dispose();
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
			
			if(tabellaMillesimale==null)
				bContinua.setText("Inserisci");
			else bContinua.setText("Modifica");
			
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

	private JTable getTabella()
	{
		if (tabella == null) 
		{
		    dm.setDataVector(
		      new String[][]{},
		      new String[]{ "Unita'", "Coefficente", }
		      );
		    
		    if(tabellaMillesimale != null)
		    {
		    	Iterator<Millesimo> mill=tabellaMillesimale.getMillesimi().iterator();
    
			    for (UnitaImmobiliare u : unita.getImmobili()) 
			    		dm.addRow(new String[]{u.getDatiUnitaImmobiliare().getId(),""+mill.next().getQuota()});
		    }
		    else
		    	for (UnitaImmobiliare u : unita.getImmobili()) 
		    		dm.addRow(new String[]{u.getDatiUnitaImmobiliare().getId(),""});
		    tabella = new JTable(dm);
		}
		return tabella;
	}
	
	
}

	
