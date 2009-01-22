/**
 * 
 */
package datatype;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author bruno
 *
 */
public class Data {
	
	private GregorianCalendar data = new GregorianCalendar();
	
	public Data(Date data)  // prende una data in java.sql.date e crea l'istnza
	{
		data.setTime(data.getTime());
	}
	
	public Data(int day, int month, int year)
	{
		data.set(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
	}
	
	public void creaData(int gg,int mm,int aa)
	{
		data = new GregorianCalendar(aa,mm,gg);
	}
	public Calendar getCalendar() {
		return data;
	}
	
	public Data(String s)
	{
		try{
			String[] ss=s.split("/");
			data.set(Integer.valueOf(ss[2]), (Integer.valueOf(ss[1])-1), Integer.valueOf(ss[0]));
		}
		catch (Exception e)
		{
			data.set(0, 0, 0);
		}
	}

	public Data() {
		
	}

	public void setCalendar(GregorianCalendar calendar) {
		this.data = calendar;
	}	
	
	public boolean equals(Data d)
	{
		if( this.data.get(GregorianCalendar.DAY_OF_MONTH)==d.getCalendar().get(GregorianCalendar.DAY_OF_MONTH) &&
			this.data.get(GregorianCalendar.MONTH)==(d.getCalendar().get(GregorianCalendar.MONTH)) &&
			this.data.get(GregorianCalendar.YEAR)==d.getCalendar().get(GregorianCalendar.YEAR))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return data.hashCode();
	}
	
	public boolean minoreUguale(Data d)
	{
		return this.data.before(d.getCalendar());	
	}
	
	public boolean maggioreUguale(Data d)//this maggiore di d
	{
		return this.data.after(d.getCalendar());
	}

	public void creaCurrenDate() {
		data = new GregorianCalendar();
	}
}
