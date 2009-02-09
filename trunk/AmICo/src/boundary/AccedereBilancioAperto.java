/**
 * 
 */
package boundary;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import store.POJO.Bilancio;
import datatype.DatiVoceBilancio;
import datatype.list.VociBilancio;
import enumeration.StatiInserireNuovoCondominio;
import executor.GestoreBilanci;
import executor.GestoreCondomini;

/**
 * @author Federico
 *
 */

public class AccedereBilancioAperto implements BaseBoundary{
	
		private Bilancio bilancio;
		private JTabbedPane tab;
		private StatoPatrimoniale SP;
		private SpostamentiDiCassa SDC;
		private AccedereCondominioAperto ACA;
		private GestoreBilanci GB;
	
		private InserireNuovaVoceBilancio INVB;
	
		public AccedereBilancioAperto(GestoreBilanci gb,Bilancio bilancio,AccedereCondominioAperto aca) {
			this.bilancio=bilancio;
			ACA=aca;
			GB=gb;
			
			SP=new StatoPatrimoniale(this);
			SDC=new SpostamentiDiCassa(this);
			
			tab=new JTabbedPane();
			tab.addTab("Stato partimoniale", SP);
			tab.addTab("Spostamenti di cassa", SDC);
			aca.getPannello().removeAll();
			aca.getPannello().add(tab);
			aca.getPannello().revalidate();
			aca.getPannello().repaint();
		}
		
		public void chiudi()
		{
			GB.chiudiBilancio();
		}

		public void inserisci()
		{
			INVB = new InserireNuovaVoceBilancio(this, GB);
		}
		
		public void ammissibile(Boolean b) {
			if(b)
			{
				int c = JOptionPane.showConfirmDialog(tab, "Sei sicuro?\nSe si conferma i dati verranno inseriti nel sistema.", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				if (c==0){
					ok();
				}
				else {
					ko();
				}
			}	
				else
					JOptionPane.showMessageDialog(tab, "il nome della voce del bilancio e' gia presente");// a base
			
			
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
			// TODO Auto-generated method stub
			
		}

		public void ko() {
			GB.procedi(false);//da attesaConfermaInserimentoDati a base
			JOptionPane.showMessageDialog(tab, "Voce bilancio non inserita");
			
		}

		public void ok() {
			GB.procedi(true);//da attesaConfermaInserimentoDati a base
			JOptionPane.showMessageDialog(tab, "Voce bilancio inserita");
			
		}

		public void aggiornaVociBilancio(VociBilancio VociBilancio) {
			// TODO Auto-generated method stub
			
		}

		public void aggiornaSpeseDaPagare(Object calcolaSpeseDaPagare) {
							// dovrebbe prendere un RapportoPagamenti al posto di object
			
			// TODO Auto-generated method stub
			
		}
		public void inserisciVoceBilancio(DatiVoceBilancio datiVoceBilancio)
		{
			GB.inserisciVoceBilancio(datiVoceBilancio);
		}
		
}