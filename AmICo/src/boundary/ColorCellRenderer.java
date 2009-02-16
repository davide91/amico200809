/**
 * 
 */
package boundary;

/**
 * @author Federico
 *
 */

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


@SuppressWarnings("serial")
public class ColorCellRenderer extends JLabel  implements TableCellRenderer 
{


	public ColorCellRenderer() {
		setOpaque(true);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,  boolean isSelected, boolean hasFocus, int row, int column)
	{
	   if(column == 0)
	   {
		   try{
			  if(Float.parseFloat((String)value)<0) 
			  {
			      this.setBackground(new Color(255,102,102));
			  }
			  else  if(Float.parseFloat((String)value)>0)
				  this.setBackground(new Color(128,255,83));
			  else this.setBackground(Color.gray);
		   }
		   catch(NumberFormatException nfe){}
	   }
	   this.setText((String)value);
	    return this;
	}
	
}