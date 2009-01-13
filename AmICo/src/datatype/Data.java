/**
 * 
 */
package datatype;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author bruno
 *
 */
public class Data {
	
	private GregorianCalendar data;
	
	public Data()
	{
	
		
		
	}
	
	public Data(int gg,int mm,int aa)
	{
		creaData(gg,mm,aa);
	}
	
	public void creaData(int gg,int mm,int aa)
	{
		data = new GregorianCalendar(aa,mm,gg);
	}
	
	public int anno()
	{
		return data.get(Calendar.YEAR);
	}
	
	public boolean maggioreDi(Data altra)
	{
		return data.after(altra);
	}
	
	public boolean ugualeA(Data altra)
	{
		return data.equals(altra);
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}
}
