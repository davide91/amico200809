/**
 * 
 */
package boundary;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import store.POJO.Bilancio;
import store.POJO.VoceBilancio;
import datatype.DatiVoceBilancio;
import datatype.RapportoPagamenti;
import datatype.list.VociBilancio;
import enumeration.StatiAccedereBilancioAperto;
import enumeration.StatoBilancio;
import executor.GestoreBilanci;

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
	
		private StatiAccedereBilancioAperto state;
		private InserireNuovaVoceBilancio INVB;
	
		public AccedereBilancioAperto(GestoreBilanci gb,Bilancio bilancio,AccedereCondominioAperto aca) {
			state = StatiAccedereBilancioAperto.base;
			this.bilancio=bilancio;
			ACA=aca;
			GB=gb;
			
			SP=new StatoPatrimoniale(this);
			SDC=new SpostamentiDiCassa(this);
			if(bilancio!=null)
			{
				SP.aggiorna(this.bilancio.recuperaVociBilancio());
				SDC.aggiorna(this.bilancio.recuperaVociBilancio());
			}
			
			tab=new JTabbedPane();
			tab.addTab("Stato partimoniale", SP);
			tab.addTab("Spostamenti di cassa", SDC);
			aca.getPannello().removeAll();
			aca.getPannello().add(tab);
			aca.getPannello().revalidate();
			aca.getPannello().repaint();
		}
		
		public void terminaEsercizioBilancio()
		{
			state = StatiAccedereBilancioAperto.calcoliInCorso;
			GB.terminaEsercizioBilancio();
		}
		
		public void mettiInEsercizio()
		{
			state = StatiAccedereBilancioAperto.mettiInEsercizio;
			GB.mettiInEsercizio();
		}
		
		public void chiudi()
		{
			GB.chiudiBilancio();
		}

		public void inserisci()
		{
			state = StatiAccedereBilancioAperto.controllaDatiVoceBilancio;
			INVB = new InserireNuovaVoceBilancio(this, GB);
		}
		
		public Bilancio recuperaBilancio()
		{
			return bilancio;
		}
		
		public void ammissibile(Boolean b) {
			switch(state)
			{
				case controllaDatiVoceBilancio:
					if(b)
					{
						int c = JOptionPane.showConfirmDialog(tab, "Sei sicuro?\nSe si conferma i dati verranno inseriti nel sistema.", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						
						if (c==0){
							ok();
						}
						else {
							ko();
							JOptionPane.showMessageDialog(tab, "Voce bilancio non inserita");
						}
					}	
					else
						JOptionPane.showMessageDialog(tab, "il nome della voce del bilancio e' gia presente");// a base
				break;
			
				case verificaEliminazione:
					if(b)
					{
						int c = JOptionPane.showConfirmDialog(tab, "Sei sicuro?\nSe si conferma i dati verranno eliminati dal sistema.", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						
						if (c==0){
							ok();
							state = StatiAccedereBilancioAperto.base;
							JOptionPane.showMessageDialog(tab, "Voce di bilancio eliminata");// a base
						}
						else {
							ko();
							state = StatiAccedereBilancioAperto.base;
							JOptionPane.showMessageDialog(tab, "Voce di bilancio non eliminata");// a base
						}
					}
					else
					{
						state = StatiAccedereBilancioAperto.base;
						JOptionPane.showMessageDialog(tab, "Voce di Bilancio gia' registrata in cassa. ");// a base
					}
				break;
				
				case calcoliInCorso:
					if(b)
					{
						state = StatiAccedereBilancioAperto.attesaConfermaChiusuraEsercizio;
						int c = JOptionPane.showConfirmDialog(tab, "Sei sicuro?\nSe si conferma il bilancio sara' terminato.", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						
						if (c==0){
							GB.procedi(true);
							JOptionPane.showMessageDialog(tab, "Esercizio Bilancio Terminato!");// a base
							state = StatiAccedereBilancioAperto.base;
						}
						else {
							GB.procedi(false);
							JOptionPane.showMessageDialog(tab, "Esercizio Bilancio non Terminato!");// a base
							state = StatiAccedereBilancioAperto.base;
						}
					}
					break;
			}
		}

		public void annulla() {
			// TODO Auto-generated method stub
			
		}

		public void fallito() {
			switch(state)
			{
				case mettiInEsercizio:
					JOptionPane.showMessageDialog(tab, "Bilancio già in esercizio");
					state = StatiAccedereBilancioAperto.base;
				break;
			}
		}

		public void fatto() {
			switch(state)
			{
			case controllaDatiVoceBilancio:
				JOptionPane.showMessageDialog(tab, "Voce bilancio inserita");
				INVB.dispose();
				break;
			case mettiInEsercizio:
				JOptionPane.showMessageDialog(tab, "Bilancio In Esercizio");
				state = StatiAccedereBilancioAperto.base;
				break;
			}
		}

		public void finito() {
			// TODO Auto-generated method stub
			
		}

		public void ko() {
			GB.procedi(false);//da attesaConfermaInserimentoDati a base
			if(INVB!=null)
				INVB.dispose();
		}

		public void ok() {
			GB.procedi(true);//da attesaConfermaInserimentoDati a base
		}

		public void aggiornaVociBilancio(VociBilancio VociBilancio) {
			SP.aggiorna(VociBilancio);
			SDC.aggiorna(VociBilancio);
		}

		public void aggiornaSpeseDaPagare(RapportoPagamenti calcolaSpeseDaPagare) {
		
			int c = JOptionPane.showConfirmDialog(tab, "Ci sono delle spesa da pagare per un totale di : "+calcolaSpeseDaPagare.getTotale().recuperaValore()+" Euro! \n Continuare?");
			
			if (c==0){
				GB.procedi(true);
				JOptionPane.showMessageDialog(tab, "Esercizio Bilancio Terminato!");// a base
				state = StatiAccedereBilancioAperto.base;
			}
			else {
				GB.procedi(false);
				JOptionPane.showMessageDialog(tab, "Esercizio Bilancio non Terminato!");// a base
				state = StatiAccedereBilancioAperto.base;
			}	
		}
		public void inserisciVoceBilancio(DatiVoceBilancio datiVoceBilancio)
		{
			GB.inserisciVoceBilancio(datiVoceBilancio);
		}
		
		public void eliminaVoceBilancio(VoceBilancio vb)
		{
			state = StatiAccedereBilancioAperto.verificaEliminazione;
			GB.eliminaVoceBilancio(vb);
		}
		
}