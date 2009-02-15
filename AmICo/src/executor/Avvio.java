/**
 * 
 */
package executor;

import java.awt.EventQueue;
import java.util.prefs.Preferences;

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
		Avvio.inizializzaAmICo();
		
//		if(primoAvvio())
//			inserisciDatiIniziali();
		
		EventQueue.invokeLater(new SplashScreenCloser());
	}
	

/*	private static void inserisciDatiIniziali() {
		// TODO Auto-generated method stub
		//dati iniziali utilizzando i metodi di accesso TuttePersone e TuttiCondomini
	}

	private static boolean primoAvvio() {
		// TODO Auto-generated method stub
		Preferences prefs = Preferences.userNodeForPackage(getClass());
		boolean installazioneOk = prefs.getBoolean("InstallazioneOk", true);
		if(installazioneOk)
			prefs.putBoolean("InstallazioneOk", false);
		return installazioneOk;
	}
*/
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
		fSplashScreen = new SplashScreen("images/splash-AmICo.jpg");
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
