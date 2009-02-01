/**
 * 
 */
package executor;

import java.awt.EventQueue;

import store.TuttePersone;
import store.TuttiCondomini;
import boundary.DriverFileSystem;
import boundary.SplashScreen;

/**
 * @author thewally
 *
 */
public class Avvio  {
	
	private static SplashScreen fSplashScreen;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showSplashScreen();

	//	Thread.sleep(1000);
		   
		Avvio.inizializzaAmICo();
		
		EventQueue.invokeLater(new SplashScreenCloser());
	}
	

	public static void esciDaAmICo() {
		
	}
	
	public static void inizializzaAmICo() {
		GestoreCondomini.getInstance();
		GestorePersone.getInstance();
		DriverFileSystem.getInstance();
		TuttiCondomini.getInstance();
		TuttePersone.getInstance();
	}

	  /**
		 * Show a simple graphical splash screen, as a quick preliminary to the
		 * main screen.
		 */
	private static void showSplashScreen() 
	{
		fSplashScreen = new SplashScreen("images/splash-AmICo.JPG");
		fSplashScreen.splash();
	}
	  

	  /**
		 * Removes the splash screen.
		 * 
		 * Invoke this <code>Runnable</code> using
		 * <code>EventQueue.invokeLater</code>, in order to remove the splash
		 * screen in a thread-safe manner.
		 */
	  private static final class SplashScreenCloser implements Runnable 
	  {
	    public void run(){
	      fSplashScreen.dispose();
	    }
	  }
}
