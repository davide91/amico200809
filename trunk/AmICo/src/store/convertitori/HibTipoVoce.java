/**
 * 
 */
package store.convertitori;

import enumeration.TipoVoce;
import store.util.CharEnumUserType;

/**
 * @author bruno
 *
 */
public class HibTipoVoce extends CharEnumUserType<TipoVoce>{

	public HibTipoVoce() {
		super(TipoVoce.class, TipoVoce.values());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected char toChar(Enum e) {
		TipoVoce t = (TipoVoce)e;
		switch (t) {
		case incasso:
			return 'i';
		case spesa:
			return 's';
		}
		return 0;
	}
}
