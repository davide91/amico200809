package boundary;

import datatype.Documento;
import executor.GestorePagamenti;

public class AccederePagamenti implements BaseBoundary {
	
	private GestorePagamenti m_gestorePagamenti;

	public AccederePagamenti(GestorePagamenti gestorePagamenti) 
	{
		m_gestorePagamenti = gestorePagamenti;
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

	public void aggiornaDocumento(Documento sollecito) {
		// TODO Auto-generated method stub
		
	}
}
