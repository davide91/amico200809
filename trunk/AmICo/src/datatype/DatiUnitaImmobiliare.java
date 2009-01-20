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
	private float metriQ;
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

	@Override
	public boolean equals(Object other) {
	 if (this == other)
	   return true;
	 if (!(other instanceof DatiUnitaImmobiliare))
	   return false;
	 final DatiUnitaImmobiliare o = (DatiUnitaImmobiliare) other;
	 if (!o.getCatCatastale().equals(getCatCatastale()))
	   return false;
	 if (!o.getDestUso().equals(getDestUso()))
		   return false;
	 if (!(o.getMetriQ() == getMetriQ()))
		   return false;
	 if (!o.getPosizioneInterna().equals(getPosizioneInterna()))
		   return false;
	 return true;
	}
	
	@Override
	public int hashCode() {
		int result;
		 result = this.getPosizioneInterna().hashCode();
		 result = 29 * result + this.getCatCatastale().hashCode();
		 result = 29 * result + this.getDestUso().hashCode();
		 return result;
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

	public float getMetriQ() {
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
