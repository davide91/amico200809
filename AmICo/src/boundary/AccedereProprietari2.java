//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import store.POJO.Persona;
import store.POJO.PersonaFisica;
import store.POJO.PersonaGiuridica;
import store.POJO.Proprieta;
import store.POJO.UnitaImmobiliare;
import datatype.list.Percentuali;
import datatype.list.Persone;

/**
 * @author Federico
 *
 */
public class AccedereProprietari2 extends JFrame {
	
	private Persone persone;
	private ButtonGroup group;
	private UnitaImmobiliare unita;
	private AccedereUnitaImmobiliari AUI;
	private InserireProprietario IP;
	private boolean aggiungibile;
	private boolean click;
	
	private Persone proprietari = new Persone();
	private Percentuali quote = new Percentuali();
	
	
	public AccedereProprietari2(AccedereUnitaImmobiliari aui,UnitaImmobiliare u,Persone p) {
		initComponents();
		initGroup();
		persone=p;
		AUI=aui;
		unita=u;
		click=true;
		aggiungibile=true;
		nomeUnita.setText(unita.getDatiUnitaImmobiliare().getId());
		
		for (Proprieta prop : unita.getQuoteDiPossesso()) {
			quote.inserisciReale(prop.getQuota());
			proprietari.inserisciPersona(prop.getProprietario());
		}
		 aggiornaTabella(null,0);
		 
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
		this.setTitle("Proprietari");
	}

	private void initGroup() {
		group = new ButtonGroup();
	}
	
	public void inserisciNuovaPersona(){	
		AUI.inserisciNuovaPersona();
	}

	public void aggiornaPersone(Persone persone) {
		this.persone=persone;
		if(IP!=null) IP.aggiornaPersone(persone);
	}

	
	public void aggiornaTabella(Persona pers, float quota)
	{
		int cont=0;
		
		if(quota!=0 || pers!=null)
		{
			proprietari.inserisciPersona(pers);
			quote.inserisciReale(quota);
		}
		
		click=true;
		if(quote.somma()==100) {
			aggiungibile=false;
			getBOK().setEnabled(true);
			getBAggiungiProprietario().setEnabled(false);
		}
		else
		{
			aggiungibile=true;
			getBOK().setEnabled(false);
			getBAggiungiProprietario().setEnabled(true);
		}
	
		initGroup();
		
		Iterator<Persona> p =this.proprietari.getPersone().iterator();
		Persona perso;
		
		Iterator<Float> q=this.quote.getListaQuote().iterator();
		float quo;
		
			somma.setText("Al 100% manca "+ (100-quote.somma())+"%");
			DefaultTableModel dm = new DefaultTableModel();
			
		    dm.setDataVector(
		      new Object[][]{},
		      new Object[]{"Nome","Cognome","Quota","Seleziona"}
		      );

		    while(p.hasNext())
		    {
		    	perso=p.next();
		    	quo = q.next();
		    	
		    	if(perso instanceof PersonaFisica)
		    	{
		    		dm.addRow(new Object[]{
		    				((PersonaFisica)perso).getDati().getNome(),
		    				((PersonaFisica)perso).getDati().getCognome(),
		    				quo,
		    				new JRadioButton() });
		    		cont++;
		    	}
		    	else if(perso  instanceof PersonaGiuridica)
		    	{
		    		dm.addRow(new Object[]{
		    				((PersonaGiuridica)perso).getDati().getpIva().getPartIva(),
		    				"",
		    				quo,
		    				new JRadioButton() });
		    		cont++;
		    	}
		    		
		    }
		    if(cont!=0)
		    	for(int i=0;i<cont;i++)
		    		group.add((JRadioButton)dm.getValueAt(i,3));

		    table.setModel(dm);
		    table.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		    table.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));
	}

	private void bAggiungiProprietarioMouseMouseClicked(MouseEvent event) {
		if(click && aggiungibile)
		{
			click=false;
			IP=new InserireProprietario(this,persone);
		}
	}


	@SuppressWarnings("unchecked")
	private void bRimuoviProprietarioMouseMouseClicked(MouseEvent event) {
		if(click)
		{
			int i=0;
			if (group!=null)
			{
				Enumeration e=group.getElements();
				for (i=0; e.hasMoreElements();i++ )
			           if ( ((JRadioButton)e.nextElement()).getModel() == group.getSelection()) 
			           {
			        	   List<Persona> x;
			        	   quote.removeAt(i);
			        	   x=proprietari.getPersone();
			        	   x.remove(i);
			        	   proprietari.setPersone(x);
			        	   this.aggiornaTabella(null,0);
			        	   
			           }
			
				if( i == group.getButtonCount())
					JOptionPane.showMessageDialog(this, "Selezionare prima il proprietario");
			}
		}
		
	}

	private void bOKMouseMouseClicked(MouseEvent event) {
		if(click)
		{
			if (quote.somma()==100.0)
			{
				AUI.proprietaOK(proprietari, quote);
				this.dispose();
			}
			else JOptionPane.showMessageDialog(this, "La somma delle quote deve essere 100 %");
		}
	}


	private void bAnnullaMouseMouseClicked(MouseEvent event) {
		if(click)
		{
			AUI.annulla();
			this.dispose();
		}
	}


	private static final long serialVersionUID = 1L;
	private JButton bAnnulla;
	private JButton bOK;
	private JButton bAggiungiProprietario;
	private JButton bRimuoviProprietario;
	private JLabel nomeUnita;
	private JTable table;
	
	private JLabel somma = new JLabel("Al 100% manca 100 %");
	private JScrollPane jScrollPane0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	private void initComponents() {
		
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getNomeUnita(), new Constraints(new Leading(34, 150, 12, 12), new Leading(10, 22, 10, 10)));
		add(getBAggiungiProprietario(), new Constraints(new Leading(38, 10, 10), new Leading(312, 12, 12)));
		add(getBRimuoviProprietario(), new Constraints(new Leading(216, 10, 10), new Leading(312, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(28, 502, 12, 12), new Leading(45, 251, 10, 10)));
		add(getBAnnulla(), new Constraints(new Leading(470, 10, 10), new Leading(312, 12, 12)));
		add(getBOK(), new Constraints(new Leading(375, 10, 10), new Leading(312, 12, 12)));
		add(somma, new Constraints(new Leading(350, 10, 10), new Leading(20, 12, 12)));
		setSize(581, 383);
	}


	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}


	private JTable getJTable0() {
		if (table == null)
		{
		    DefaultTableModel dm = new DefaultTableModel();
		    dm.setDataVector(
		      new Object[][]{},
		      new Object[]{"Nome","Cognome","Quota","Seleziona"}
		      );

		    table = new JTable(dm)
		    {
				private static final long serialVersionUID = 1L;

				public void tableChanged(TableModelEvent e)
			    {
					super.tableChanged(e);
			        repaint();
			    }
		    };   
		    table.getColumn("Seleziona").setCellRenderer(new RadioButtonRenderer());
		    table.getColumn("Seleziona").setCellEditor(new RadioButtonEditor(new JCheckBox()));

		}

		return table;
	}


	private JLabel getNomeUnita() {
		if (nomeUnita == null) {
			nomeUnita = new JLabel();
		}
		return nomeUnita;
	}



	private JButton getBRimuoviProprietario() {
		if (bRimuoviProprietario == null) {
			bRimuoviProprietario = new JButton();
			bRimuoviProprietario.setText("Rimuovi proprietario");
			bRimuoviProprietario.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bRimuoviProprietarioMouseMouseClicked(event);
				}
			});
		}
		return bRimuoviProprietario;
	}


	private JButton getBAggiungiProprietario() {
		if (bAggiungiProprietario == null) {
			bAggiungiProprietario = new JButton();
			bAggiungiProprietario.setText("Aggiungi proprietario");
			bAggiungiProprietario.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bAggiungiProprietarioMouseMouseClicked(event);
				}
			});
		}
		return bAggiungiProprietario;
	}


	private JButton getBOK() {
		if (bOK == null) {
			bOK = new JButton();
			bOK.setText("Conferma");
			bOK.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bOKMouseMouseClicked(event);
				}
			});
		}
		return bOK;
	}


	private JButton getBAnnulla() {
		if (bAnnulla == null) {
			bAnnulla = new JButton();
			bAnnulla.setText("esci");
			bAnnulla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					bAnnullaMouseMouseClicked(event);
				}
			});
		}
		return bAnnulla;
	}


	@SuppressWarnings("unused")
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


}

