package extras;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	private Image m_image;
	private int imgWidth;
	private int imgHeight;
	private boolean toPaint;
	
	public void setImage(String path) 
	{
		 MediaTracker tracker=new MediaTracker(this);
		   Image image=getToolkit().getImage(path);
		   tracker.addImage(image,1);
		   try
		   {
		       tracker.waitForAll();
		   }
		   catch (InterruptedException ie)
		   {
			  toPaint = false;
			  return;
		   }
		   toPaint = true;
			imgWidth = image.getWidth(null);
			imgHeight = image.getHeight(null);
			m_image = image;
		setSize(getSize());
	}
	
	public void paintComponent(Graphics g)
	{
		if(!toPaint) return;
		
		super.paintComponent(g);
		
		g.drawImage(m_image,
				(getWidth() - imgWidth)/2, 
				(getHeight() - imgHeight)/2, 
				imgWidth, imgHeight, null );
	}
	
	public Dimension getSize() {
		return new Dimension(imgWidth, imgHeight);
	}
}
