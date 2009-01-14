/**
 * 
 */
package store.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * Generic class to map a JDK 1.5 enum to a SMALL INT column in the DB. Beware
 * that the enum.ordinal() relates to the ORDERING of the enum values, so, if
 * your change it later on, all your DB values will return an incorrect value!
 * 
 * @author Benoit Xhenseval
 * @version 1 Codice originale: http://www.hibernate.org/312.html
 * 
 * @version 2 Il codice originale della classe è stato messo a disposizione
 *          dell'autore all'indirizzo http://www.hibernate.org/312.html Il
 *          codice è stato modificato per effettuare la mappatura delle
 *          enumerazioni su un tipo char, dato il limitato numero di valori
 *          delle enumerazioni utilizzate nell'applicazione. Inoltre, grazie ad
 *          un metodo ausiliario ad-hoc che effettua la conversione enum ->
 *          char, che deve essere contenuto nella classe E, viene eliminato il
 *          problema citato dall'autore relativo all'ordinamento, aumentando il
 *          livello di astrazione e di tolleranza alle modifiche del tipo di
 *          dato.
 * 
 */
public abstract class CharEnumUserType<E extends Enum<E>> implements UserType {
	private Class<E> clazz = null;

	private E[] theEnumValues;

	/**
	 * Contrary to the example mapping to a VARCHAR, this would
	 * 
	 * @param c
	 *            the class of the enum.
	 * @param e
	 *            The values of enum (by invoking .values()).
	 */
	public CharEnumUserType(Class<E> c, E[] e) {
		this.clazz = c;
		this.theEnumValues = e;
	}

	private static final int[] SQL_TYPES = { Types.CHAR };

	/**
	 * simple mapping to a SMALLINT.
	 * 
	 * Marco Marsala: la mappatura viene effettuata sul tipo CHAR.
	 */
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	public Class returnedClass() {
		return this.clazz;
	}

	/**
	 * From the SMALLINT in the DB, get the enum. Because there is no
	 * Enum.valueOf(class,int) method, we have to iterate through the given
	 * enum.values() in order to find the correct "int".
	 * 
	 * Marco Marsala: il problema citato è stato eliminato definendo un metodo
	 * ausiliario specifico per ognuna delle enumerazioni da mappare; il metodo
	 * si trova nella classe E.
	 */
	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
			throws HibernateException, SQLException {
		final char val = resultSet.getString(names[0]).charAt(0);
		E result = null;
		if (!resultSet.wasNull())
			try {
				for (int i = 0; (i < this.theEnumValues.length) && (result == null); i++)
					if (toChar(this.theEnumValues[i]) == val)
						result = this.theEnumValues[i];
			} catch (SecurityException e) {
				result = null;
			} catch (IllegalArgumentException e) {
				result = null;
			}
		return result;
	}

	/**
	 * Fornisce una conversione dai valori di un tipo enumerazione in singoli
	 * caratteri. Il metodo va ridefinito nella classe convertitore, che deve
	 * estendere la presente CharEnumUserType.
	 */
	protected abstract char toChar(Enum e);

	/**
	 * set the SMALLINT in the DB based on enum.ordinal() value, BEWARE this
	 * could change.
	 * 
	 * Marco Marsala: problema eliminato.
	 */
	public void nullSafeSet(PreparedStatement preparedStatement, Object value,
			int index) throws HibernateException, SQLException {
		if (null == value)
			preparedStatement.setNull(index, Types.CHAR);
		else
			preparedStatement.setString(index, String
					.valueOf(toChar(((Enum) value))));
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value; // le enumerazioni sono value types in Java 5
	}

	public boolean isMutable() {
		return false; // le enumerazioni sono value types in Java 5
	}

	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) // le enumerazioni sono value types in Java 5
			return true;
		if ((null == x) || (null == y))
			return false;
		return x.equals(y); // obiettivo riuso: supporto per eventuali
		// ridefinizioni del metodo equals
	}
}
