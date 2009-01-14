/**
 * 
 */
package store.convertitori;

import enumeration.CategoriaCatastale;
import store.util.CharEnumUserType;

/**
 * @author bruno
 *
 */
public class HibCategoriaCatastale extends CharEnumUserType<CategoriaCatastale> {

	public HibCategoriaCatastale()
	{// we must give the values of the enum to the parent.
		super(CategoriaCatastale.class, CategoriaCatastale.values());
	}

	@Override
	protected char toChar(Enum e) {
		// TODO Auto-generated method stub
		CategoriaCatastale c = (CategoriaCatastale) e;
		switch (c) {
		case A10:
			return '1';
		case A2:
			return '2';
		case A4:
			return '3';
		case C1:
			return '4';
		case C2:
			return '5';
		}
		return 0;
	}

}
