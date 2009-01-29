//VS4E -- DO NOT REMOVE THIS LINE!
package boundary;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.GroupLayout;

import store.POJO.Persona;
import datatype.DatiCorretti;
import datatype.DatiErrati;
import datatype.DatiPersona;
import datatype.EsitoControlloDati;
import datatype.EsitoControlloDatiPersona;
import datatype.list.Persone;
import executor.GestorePersone;

/**
 * @author Federico
 *
 */
public class ModificarePersona extends JFrame implements BaseBoundary{
	

	private GestorePersone GP;
	
	
	public void creaModificarePersona(GestorePersone GP, Persona persona){
		this.GP=GestorePersone.getInstance();
		//AMM.mostraPersona(persona);
	}
	
	
	
	public void inserisciNuoviDatiPersona(DatiPersona datiP) {
		EsitoControlloDati esito= datiP.controlla();
		 if (esito instanceof DatiErrati) 
			//AMM.mostra(esito);
			 System.out.println("");
		 if(esito instanceof DatiCorretti) {
			GP.inserisciDatiPersona(datiP);
		 }
		 
		
	}

	public void ammissibile(EsitoControlloDatiPersona 	controlloDati) {
		//AMM.richiediConferma(controlloDati);
	}
	
	public void ammissibile(Boolean b) {
		
	}
	
	public void annulla() {
		GP.annullato();
	}

	
	public void ok() {
		GP.procedi(true);
	}
	
	

	public void ko() {
		GP.procedi(false);
		//AMM.mostra(DatiPersonaModificatiKO);
	}

	public void fatto() {
		//AMM.mostra(DatiPersonaModificatiOK);
		
	}


	public void fallito() {
		// TODO Auto-generated method stub
		
	}


	public void finito() {
		// TODO Auto-generated method stub
		
	}


	public void aggiornaPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}


	public void aggiornaPersone(Persone persone) {
		// TODO Auto-generated method stub
		
	}

	private static final long serialVersionUID = 1L;
	private static final String PREFERRED_LOOK_AND_FEEL = null;

	public ModificarePersona(Persona persona) {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setSize(400, 300);
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
				ModificarePersona frame = new ModificarePersona(null);
				frame.setTitle("ModificarePersona");
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
