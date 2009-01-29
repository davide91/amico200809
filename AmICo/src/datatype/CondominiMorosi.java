/**
 * 
 */
package datatype;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import store.POJO.Pagamento;
import store.POJO.Persona;
import datatype.list.Persone;
import datatype.list.Pagamenti;

/**
 * @author bruno
 *
 */
public class CondominiMorosi extends Avviso {
	
	private Map<Persona, Pagamenti> m_map = new HashMap<Persona, Pagamenti>();
	
	public CondominiMorosi()
	{
	}
	
	public Persone getPersone() 
	{
		Iterator<Persona> personaIter = m_map.keySet().iterator();
		
		Persone persone = new Persone();
		
		while (personaIter.hasNext()) 
		{
			persone.inserisciPersona(personaIter.next());
		}
		
		return persone;
	}
	
	
	public Pagamenti getPagamenti(Persona persona) 
	{
		if ( !m_map.containsKey(persona) )
			return null;
		
		return m_map.get(persona);
	}

	public void inserisciPagamento(Persona persona, Pagamento pagamento)
	{
		if (m_map.containsKey(persona)) 
		{
			m_map.get(persona).inserisciPagamento(pagamento);
			return;
		}
		
		Pagamenti pagamenti = new Pagamenti();
		pagamenti.inserisciPagamento(pagamento);
		m_map.put(persona, pagamenti);
	}
	
	public boolean isEmpty()
	{
		return m_map.isEmpty();
	}
}
