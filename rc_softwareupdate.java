
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class rc_softwareupdate {
	
	public static void viewupdateframe () {
		
		JFrame softwareupdateframe = new JFrame("RehaCheck Updater");
    	
    			
		softwareupdateframe.setVisible(true);
		softwareupdateframe.setSize(600,200);
		softwareupdateframe.setResizable(false);
		softwareupdateframe.setLocation(200, 60);
		softwareupdateframe.setLayout(null);
		softwareupdateframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
		JLabel updatesearchlabel = new JLabel("Hier können Sie prüfen, ob eine neuere Version von Reha Check vorhanden ist.");
		updatesearchlabel.setBounds(20,20,600,20);
		softwareupdateframe.add(updatesearchlabel);
		
		String version = rc_main.version;
		JLabel myversionlabel = new JLabel("aktuell verwendete RehaCheck Version: "+version );
		myversionlabel.setBounds(20, 45, 420, 20);
		softwareupdateframe.add(myversionlabel);
		
		updatesearchlabel.setBounds(20,20,600,20);
		softwareupdateframe.add(updatesearchlabel);
		
		JTextField updateavailable = new JTextField ();
    	if (rc_act.aktuelleversiononline.contains(version))
    	{
        	updateavailable.setText("Ihr RehaCheck " + rc_main.version + " ist aktuell!");

    	}
    	else
    	{
    	updateavailable.setText("neue Version RehaCheck " + rc_act.aktuelleversiononline + " verfügbar!");
    	JButton searchupdatebutton = new JButton("Update Seite zum Herunterladen des Updates öffnen");
    	searchupdatebutton.setVisible(true);
    	searchupdatebutton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			            	try {
								Desktop.getDesktop().browse(new URL(rc_main.rehacheckurl).toURI());
							} catch (IOException | URISyntaxException eew) {
								JOptionPane.showMessageDialog(null,"Der Link " + rc_main.rehacheckurl + " konnte nicht geladen werden."
										+ "Bitte rufen Sie die Seite manuell auf. \n"
										+ "(catch rc_softwareupdate - Z61 - searchupdatebutton updateDownloadActionListener)");
							}
			            	 
			            		        
			        				
			}});
    	searchupdatebutton.setBounds(120, 90, 400, 30);
    	softwareupdateframe.add(searchupdatebutton);
    	
    	}
    	updateavailable.setBounds(160,140,300,30);
    	softwareupdateframe.add(updateavailable);
 
		//softwareupdateframe.add(searchupdatebutton);
			
	}

}
