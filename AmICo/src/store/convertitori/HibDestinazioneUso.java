/**
 * 
 */
package store.convertitori;

import store.util.CharEnumUserType;
import enumeration.CategoriaCatastale;
import enumeration.DestinazioneUso;

/**
 * @author bruno
 *
 */
public class HibDestinazioneUso extends CharEnumUserType<DestinazioneUso> {

	public HibDestinazioneUso()
	{
		super(DestinazioneUso.class,DestinazioneUso.values());
	}

	@Override
	protected char toChar(Enum e) {
		// TODO Auto-generated method stub
		DestinazioneUso d = (DestinazioneUso) e;
		switch (d) {
		case appartamento:
			return 'a';
		case garage:
			return 'g';
		case magazzino:
			return 'm';
		case negozio:
			return 'n';
		case terreno:
			return 't';
		case ufficio:
			return 'u';
		}
		return 0;
	}
}
