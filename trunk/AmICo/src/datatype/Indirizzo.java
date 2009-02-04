package datatype;

import enumeration.Provincia;

public class Indirizzo {
	private String via=" ";
	private String interno = " ";
	private String comune=" ";
	private Provincia provincia = Provincia.AL;
	private String cap=" ";
	
	public Indirizzo(){
		this.via = "";
		this.comune = "";
		this.provincia = Provincia.AL;
		this.cap = "";
	}
	
	public Indirizzo(String via, String interno, String com, Provincia prov, String cap)
	{
		this.via = via;
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
		final Indirizzo o = (Indirizzo)obj;
		
		if (!o.getCap().equals(getCap()))
			return false;
		if (!o.getComune().equals(getComune()))
			return false;
		if (o.getProvincia()!=getProvincia())
			return false;
		if (!o.getVia().equals(getVia()))
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
