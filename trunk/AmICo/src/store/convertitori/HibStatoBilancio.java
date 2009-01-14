/**
 * 
 */
package store.convertitori;

import enumeration.StatoBilancio;
import store.util.CharEnumUserType;

/**
 * @author bruno
 *
 */
public class HibStatoBilancio extends CharEnumUserType<StatoBilancio>{

	public HibStatoBilancio() {
		// TODO Auto-generated constructor stub
		super(StatoBilancio.class,StatoBilancio.values());
	}

	@Override
	protected char toChar(Enum e) {
		// TODO Auto-generated method stub
		StatoBilancio t = (StatoBilancio) e;
		switch (t) {
		case consuntivo:
			return 'c';
		case inCompilazione:
			return 'i';
		case inEsercizio:
			return 'e';
		case preventivo:
			return 'p';
		}
		return 0;
	}
	
}
