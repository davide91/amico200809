/**
 * 
 */
package store.convertitori;

import store.util.CharEnumUserType;
import enumeration.TipoPianoPagamenti;
import enumeration.TipoVoce;

/**
 * @author bruno
 *
 */
public class HibTipoPianoPagamenti extends CharEnumUserType<TipoPianoPagamenti>{

	public HibTipoPianoPagamenti() {
		super(TipoPianoPagamenti.class, TipoPianoPagamenti.values());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected char toChar(Enum e) {
		TipoPianoPagamenti t = (TipoPianoPagamenti)e;
		switch (t) {
		case ordinario:
			return 'o';
		case straordinario:
			return 's';
		}
		return 0;
	}
}
