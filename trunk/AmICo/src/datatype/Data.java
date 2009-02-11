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
	
	private GregorianCalendar m_data = new GregorianCalendar();
	
	public Data(Date data)  // prende una data in java.sql.date e crea l'istanza
	{
		m_data.setTime(data);
	}
	
	public Data(Data data)
	{
		m_data.setTime(data.getTime());
	}
	
	public Data(int day, int month, int year)
	{
		m_data.set(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
	}
	
	public void creaData(int gg,int mm,int aa)
	{
		m_data = new GregorianCalendar(aa,mm,gg);
	}
	public Calendar getCalendar() {
		return m_data;
	}
	
	public Data(String s)
	{
		try{
			String[] ss=s.split("/");
			m_data.set(Integer.valueOf(ss[2]), (Integer.valueOf(ss[1])-1), Integer.valueOf(ss[0]));
		}
		catch (Exception e)
		{
			m_data.set(0, 0, 0);
		}
	}

	public Data() {
		m_data = new GregorianCalendar();
	}

	public void setCalendar(GregorianCalendar calendar) {
		this.m_data = calendar;
	}	
	
	public boolean equals(Data d)
	{
		if( m_data.get(GregorianCalendar.DAY_OF_MONTH)==d.getCalendar().get(GregorianCalendar.DAY_OF_MONTH) &&
			m_data.get(GregorianCalendar.MONTH)==(d.getCalendar().get(GregorianCalendar.MONTH)) &&
			m_data.get(GregorianCalendar.YEAR)==d.getCalendar().get(GregorianCalendar.YEAR))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return m_data.hashCode();
	}
	
	public boolean minoreUguale(Data d)
	{
		return m_data.before(d.getCalendar());	
	}
	
	public boolean maggioreUguale(Data d)//this maggiore di d
	{
		return m_data.after(d.getCalendar());
	}

	public void creaCurrenDate() {
		m_data = new GregorianCalendar();
	}
	
	public Date getTime()
	{
		return new Date(m_data.getTime().getTime());
	}
	
	public void add(int field, int amount)
	{
		m_data.add(field, amount);
	}
	public String dataInStringa()
	{
		return m_data.get(GregorianCalendar.DAY_OF_MONTH)+"/"+m_data.get(GregorianCalendar.MONTH)+"/"+m_data.get(GregorianCalendar.YEAR);
	}	
}
