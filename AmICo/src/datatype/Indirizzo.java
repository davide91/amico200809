package datatype;

import enumeration.Provincia;

public class Indirizzo {
	private String via;
	private String interno;
	private String comune;
	private Provincia provincia;
	private String cap;
	
	public Indirizzo(){
	}
	
	public Indirizzo(String via, String interno, String com, Provincia prov, String cap)
	{
		this.via = via;
		this.interno = interno;
		this.comune = com;
		this.provincia = prov;
		this.cap = cap;
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((this.via == null) ? 0 : this.via.hashCode())
								+ ((this.interno == null) ? 0 : this.interno.hashCode())
								+ ((this.comune == null) ? 0 : this.comune.hashCode())
								+ ((this.provincia == null) ? 0 : this.provincia.hashCode())
								+ ((this.cap == null) ? 0 : this.cap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj ==null)
			return false;
		if(this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		final Indirizzo other = (Indirizzo)obj;
		
		if (this.via == null || this.interno == null || this.comune == null || this.provincia == null || this.cap == null) {
			if (other.via == null || other.interno == null || other.comune == null || other.provincia == null || other.cap == null)
				return false;
		} else if (!(this.via.equals(other.via) && this.interno.equals(other.interno) && this.comune.equals(other.comune) && this.provincia.equals(other.provincia) && this.cap.equals(other.cap)))
			return false;
		return true;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String string) {
		this.via = string;
	}

	public String getInterno() {
		return interno;
	}

	public void setInterno(String interno) {
		this.interno = interno;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}
}
