/**
 * 
 */
package store.convertitori;

import store.util.CharEnumUserType;
import enumeration.DestinazioneUso;
import enumeration.TipoBilancio;

/**
 * @author bruno
 *
 */
public class HibTipoBilancio extends CharEnumUserType<TipoBilancio>{
	
	public HibTipoBilancio() {
		// TODO Auto-generated constructor stub
		super(TipoBilancio.class,TipoBilancio.values());
	}

	@Override
	protected char toChar(Enum e) {
		// TODO Auto-generated method stub
		TipoBilancio t = (TipoBilancio) e;
		switch (t) {
		case ordinario:
			return 'o';
		case straordinario:
			return 's';
		}
		return 0;
	}
}
