/**
 * 
 */
package datatype.list;

import java.util.ArrayList;
import java.util.List;

import datatype.Data;

/**
 * @author bruno
 *
 */
public class Date {

	private List<Data> date = new ArrayList<Data>();
	
	public Date()
	{
		
	}
	
	public void inserisciData(Data d)
	{
		date.add(d);
	}

	public List<Data> getDate() {
		return date;
	}

	public void setDate(List<Data> date) {
		this.date = date;
	}
}
