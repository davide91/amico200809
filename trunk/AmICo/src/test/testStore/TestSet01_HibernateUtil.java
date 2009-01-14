/**
 * 
 */
package test.testStore;

import store.util.HibernateUtil;
import junit.framework.TestCase;

/**
 * @author bruno
 *
 */
public class TestSet01_HibernateUtil extends TestCase {
	/**
	 * @param name
	 */
	public TestSet01_HibernateUtil(String name) {
		super(name);
	}

	/**
	 * Questo test istanzia la classe HibernateUtil, il cui codice proviene
	 * dalla documentazione ufficiale di Hibernate. La classe statica
	 * HibernateUtil, quando istanziata, carica Hibernate e provoca la lettura
	 * dei files di configurazione e di mappatura, mostrando su stdout eventuali
	 * errori in questa fase. La classe HibernateUtil è statica ed implementa
	 * mediante pattern singleton il recupero della factory di Hibernate (*),
	 * unico punto di accesso esterno a tutte le funzionalità del framework.
	 * 
	 * (*) Hibernate è realizzato con architettura a componenti e pattern
	 * factory.
	 * 
	 */
	public void testFactory() {
		HibernateUtil.getSessionFactory();
	}
}
