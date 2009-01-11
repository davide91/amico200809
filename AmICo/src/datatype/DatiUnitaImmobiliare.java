/**
 * 
 */
package datatype;

import enumeration.CategoriaCatastale;
import enumeration.DestinazioneUso;

/**
 * @author bruno
 *
 */
public class DatiUnitaImmobiliare {

	private String id;
	private CategoriaCatastale catCatastale;
	private String posizioneInterna;
	private Float metriQ;
	private DestinazioneUso destUso;
	
	public DatiUnitaImmobiliare()
	{
		
	}
	
	public DatiUnitaImmobiliare(String id, CategoriaCatastale cat, String pos, Float metri, DestinazioneUso dest)
	{
		this.id = id;
		this.catCatastale = cat;
		this.posizioneInterna = pos;
		this.metriQ = metri;
		this.destUso = dest;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CategoriaCatastale getCatCatastale() {
		return catCatastale;
	}

	public void setCatCatastale(CategoriaCatastale catCatastale) {
		this.catCatastale = catCatastale;
	}

	public String getPosizioneInterna() {
		return posizioneInterna;
	}

	public void setPosizioneInterna(String posizioneInterna) {
		this.posizioneInterna = posizioneInterna;
	}

	public Float getMetriQ() {
		return metriQ;
	}

	public void setMetriQ(Float metriQ) {
		this.metriQ = metriQ;
	}

	public DestinazioneUso getDestUso() {
		return destUso;
	}

	public void setDestUso(DestinazioneUso destUso) {
		this.destUso = destUso;
	}
}
