/**
 * 
 */
package boundary;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import datatype.list.VociBilancio;
import executor.GestoreBilanci;
import store.POJO.Bilancio;

/**
 * @author Federico
 *
 */

public class AccedereBilancioAperto implements BaseBoundary{
	
		Bilancio bilancio;
		JTabbedPane tab;
		StatoPatrimoniale SP;
		SpostamentiDiCassa SDC;
		AccedereCondominioAperto ACA;
		GestoreBilanci GB;
	
	
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

		public void ammissibile(Boolean b) {
			// TODO Auto-generated method stub
			
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
			// TODO Auto-generated method stub
			
		}

		public void ok() {
			// TODO Auto-generated method stub
			
		}

		public void aggiornaVociBilancio(VociBilancio VociBilancio) {
			// TODO Auto-generated method stub
			
		}

		public void aggiornaSpeseDaPagare(Object calcolaSpeseDaPagare) {
							// dovrebbe prendere un RapportoPagamenti al posto di object
			
			// TODO Auto-generated method stub
			
		}
}