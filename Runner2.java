import java.awt.Font;
public class Runner2 implements Runnable {
	// only in developer mode
		@Override
		public void run() {
			// Startet das Hauptprogramm mit Splash und GUI
			SplashScreenDemo2 ssd2 = new SplashScreenDemo2 ();
			 ssd2.runsplashscreen();

			 rc_main.setUIFont (new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));	
			//new rc_main().runthisjavacode (); // aufruf über diese methode, damit konvert in c++ einfach möglich mit jni - noch testen.
				rc_main.runthisjavacode ();
		}
}
