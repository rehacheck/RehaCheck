import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.EventObject;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import armdb.ConnectHost;
import armdb.QueryResult;
import armdb.SQLQuery;
import armdb.SQLQueryException;


public class rc_plugins_add extends JFrame {
		
	static String [] [] data_for_table;
	static String [] [] data;
	
	 static JTable  jTable22 = null;
	
	public static void run ()
	{
		
		JFrame pluginframe = new JFrame (" Plugin Manager");
		
		

		// hole verfügbare Plugins online:
		String fileURL= rc_act.rehacheck_sql_url + "handleSQL.php";	//url of 'handleSQL.php', remember that the 'habdleSQL.php' must be in the same server in which interested database is located
        String host= rc_act.hostname;					//server host name
        String user= rc_act.username1;						//username
        String pass= rc_act.password;						//password
        String DBName= rc_act.dbname;						//database name
        
	ConnectHost con=new ConnectHost(fileURL, host, user, pass, DBName);	//make connection
	System.out.println ("connected abgesetzt rc pluginsadd 288");


	SQLQuery query=new SQLQuery(con);         //SQLQuery object to execute select statement
	QueryResult qr;                           //QueryResult object to store the queried result
	
	
	String testdaten [] [] = null;
	
	 File pluginfile = new File ("pluginlist.txt");

	 JTable table1 = new JTable ();
	
	


	try{
		
		   SQLQuery query_gen =new SQLQuery(con);         //SQLQuery object to execute select statement
			QueryResult qr_gen;   
		   qr_gen=query.statement("SELECT * FROM rc_plugins"); // WHERE aid='" + aid + "'");     //execution of query statement
	        
		   int anzahl_der_plugin_rows_available_online =  qr_gen.numRows();
	  
	   
	    //qr holds the selected rows, let us print the values of some columns 
	    //(say column_1 and column_2) of all rows
	    
	  //data[][]={ {"101","Plugin1","","http://www..de/plugins/","123456789","JA"}, 
		   
		
		   
		   qr=query.statement("SELECT * FROM rc_plugins"); // WHERE ='" +  + "'");     //execution of query statement
	        
		   
		   String data [] [] = null;
			 // lösche ggf. alte Pluginfile, wenn existiert:
			 if (pluginfile.exists())
			 {pluginfile.delete();}
			 
			//Code
			// DefaultTableModel model = (DefaultTableModel) table1.getModel();
			 
			 
			 // Test 27.12. nummer 2: 
			 String SQL = "select * from usernames";


			 
			 String n22 = "",e22 = "";      

			 DefaultTableModel model22;
			 model22 = new DefaultTableModel(); 
			   jTable22 = new  JTable(model22);

			 model22.addColumn("Plugin ID:");
			 model22.addColumn("Pluginname:");
			 model22.addColumn("Pluginbeschreibung:");
			 model22.addColumn("Plugin URL:");
			 model22.addColumn("Plugin Key:");
			 model22.addColumn("Plugin aktiv:");
		
			 
			 
			 
			 
			 
			

	    while(qr.nextFlag()){                               //setting flag to next row till next row exists
	        //print column_1 & column_2 value of row where flag is set
	        String pluginid  =  qr.getValue("pluginid").toString();
	        String pluginname  =  qr.getValue("pluginname").toString();
	        String pluginbeschreibung  =  qr.getValue("pluginbeschreibung").toString();
	        String pluginurl  =  qr.getValue("pluginurl").toString();
	        String pluginkey  =  qr.getValue("pluginkey").toString();
	        String pluginaktiv  =  qr.getValue("pluginaktiv").toString(); // holt oben alle Werte einer Row raus
	     
	          System.out.println (pluginid + "||" + pluginname + "||" + pluginbeschreibung + "||" + pluginurl + "||" + pluginkey + "||" + pluginaktiv + "||\n");
	        
	          String pluginline = pluginid + "||" + pluginname + "||" + pluginbeschreibung + "||" + pluginurl + "||" + pluginkey + "||" + pluginaktiv + "||\n";
	          
			     model22.addRow(new Object[]{pluginid,pluginname,pluginbeschreibung,pluginurl,pluginkey,pluginaktiv});

	          
	          
	          try
	          {
	          FileWriter fw = new FileWriter (pluginfile,true);
	          fw.write(pluginline);
	          fw.flush();
	          fw.close();
	          }
	          catch (Exception alk)
	          {
	        	  JOptionPane.showMessageDialog(null, "Plugin Liste konnte nicht geschrieben werden (rc_plugins_add.java catch pluginlist.txt Exception alk Z. 118");
	          }
		      
		      /*
	          data[i][0] = pluginid;
	          data[i][1] = pluginname;
		      data[i][2] = pluginbeschreibung;
		      data[i][3] = pluginurl;
		      data[i][4] = pluginkey;
		      data[i][5] = pluginaktiv;
		        */
	      
	          /*
	        int pluginid_int = rc_functions.stringtoint(pluginid); // plugin to int to order these in data array correctly via id number
	        
	        data[pluginid_int][0] = pluginid; // packe alle daten für jede Row in einen zugehöriges Array, accessible via pluginid_int, 
	        										// erster array parameter dann später für table
	        data[pluginid_int][1] = pluginname;
	        data[pluginid_int][2] = pluginbeschreibung;
	        data[pluginid_int][3] = pluginurl;
	        data[pluginid_int][4] = pluginkey;
	        data[pluginid_int][5] = pluginaktiv;
	        */
	          
	    }
	          
	          
	    
	    
	}catch(SQLQueryException e){                            //catch exception if occurred
	    System.out.println(e.getMessage());                 //print exception message
	    JOptionPane.showMessageDialog(null, "Oh Shit.... die Plugins konnten nicht geladen werden :(  Sorry! \n"
	    		+ "Internetverbindung? - Ggf. sind keine Plugins auf dem Server vorhanden... \n"
	    		+ "[Exception catch SQLQueryEception e in rc_plugins_add.java Z. 217]");
	}
	
		
		
		
        jTable22.getTableHeader().setReorderingAllowed( false ); // spalten sollen nicht verschoben werden können

       
        jTable22.setRowHeight(30);
        
        jTable22.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        
        // Spalte 0 = Plugin ID:
        
        jTable22.getColumnModel().getColumn( 0 ).setWidth(10);// ID Nummer nicht so breit;
        
        
        // Spalte 1 = Plugin Name:
        
        jTable22.getColumnModel().getColumn( 1 ).setPreferredWidth( 230 );

        
        // Spalte 2 = Plugin Beschreibung:
        jTable22.getColumnModel().getColumn( 2 ).setPreferredWidth( 400 );
        
        
        // Spalte 3 = Plugin URL
        jTable22.getColumnModel().getColumn( 3 ).setPreferredWidth( 350 );
        jTable22.getColumn(jTable22.getColumnName(3)).setCellRenderer(
                new JButtonRenderer());
        jTable22.getColumn(jTable22.getColumnName(3)).setCellEditor(
                new JButtonEditor()); 
        
        
        // Spalte 4 = Plugin Key
        jTable22.getColumnModel().getColumn( 4).setPreferredWidth( 110);
        jTable22.getColumn(jTable22.getColumnName(4)).setWidth(20); // ID Nummer nicht so breit;
        
        // Spalte 5 = Plugin Aktiv:
        jTable22.getColumnModel().getColumn( 5 ).setPreferredWidth( 90 );

   
         
		   
	   JScrollPane sp=new JScrollPane(jTable22);
       sp.setEnabled(false);
       pluginframe.add(sp);  
		
		
		
	        pluginframe.setVisible(true); 
	
	    	pluginframe.setBounds(100,80,rc_main.framewidth, rc_main.frameheight);
			pluginframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) 
    {
        if(columnIndex==0)
            return false;
        if(columnIndex==1 && rowIndex>=1)
            return true;
        else return false;
    }

	 
	public static String [] []  catch_available_plugins ()
	{

		// hole verfügbare Plugins online:
		String fileURL= rc_act.rehacheck_sql_url + "//handleSQL.php";	//url of 'handleSQL.php', remember that the 'habdleSQL.php' must be in the same server in which interested database is located
        String host= rc_act.hostname;					//server host name
        String user= rc_act.username1;						//username
        String pass= rc_act.password;						//password
        String DBName= rc_act.dbname;						//database name
        
	ConnectHost con=new ConnectHost(fileURL, host, user, pass, DBName);	//make connection
	System.out.println ("connected abgesetzt rc pluginsadd 288");


	SQLQuery query=new SQLQuery(con);         //SQLQuery object to execute select statement
	QueryResult qr;                           //QueryResult object to store the queried result
	

		   
	    qr=query.statement("SELECT * FROM rc_plugins"); // WHERE ='" +  + "'");     //execution of query statement
	        
	    //qr holds the selected rows, let us print the values of some columns 
	    //(say column_1 and column_2) of all rows
	    


	    while(qr.nextFlag()){                               //setting flag to next row till next row exists
	        //print column_1 & column_2 value of row where flag is set
	        String pluginid  =  qr.getValue("pluginid").toString();
	        String pluginname  =  qr.getValue("pluginname").toString();
	        String pluginbeschreibung  =  qr.getValue("pluginbeschreibung").toString();
	        String pluginurl  =  qr.getValue("pluginurl").toString();
	        String pluginkey  =  qr.getValue("pluginkey").toString();
	        String pluginaktiv  =  qr.getValue("pluginaktiv").toString(); // holt oben alle Werte einer Row raus
	     
	        //  System.out.println (pluginid + "||" + pluginname + "||" + pluginbeschreibung + "||" + pluginurl + "||" + pluginkey + "||" + pluginaktiv + "||\n");
	        
	          String [] onerow = {pluginid,pluginname,pluginbeschreibung,pluginurl,pluginkey,pluginaktiv};
		        int pluginid_int = rc_functions.stringtoint(pluginid); // plugin to int to order these in data array correctly via id number

		    
	          /*
	        int pluginid_int = rc_functions.stringtoint(pluginid); // plugin to int to order these in data array correctly via id number
	        
	        data[pluginid_int][0] = pluginid; // packe alle daten für jede Row in einen zugehöriges Array, accessible via pluginid_int, 
	        										// erster array parameter dann später für table
	        data[pluginid_int][1] = pluginname;
	        data[pluginid_int][2] = pluginbeschreibung;
	        data[pluginid_int][3] = pluginurl;
	        data[pluginid_int][4] = pluginkey;
	        data[pluginid_int][5] = pluginaktiv;
	        */
	    }
	    
	}catch(SQLQueryException e){                            //catch exception if occurred
	    System.out.println(e.getMessage());                 //print exception message
	    JOptionPane.showMessageDialog(null, "Oh Shit.... die Plugins konnten nicht geladen werden :(  Sorry! \n"
	    		+ "Hat RehaCheck eine Internetverbindung? - Ggf. sind keine Plugins auf dem Server vorhanden... \n"
	    		+ "[Exception catch SQLQueryEception e in rc_plugins_add.java Z. 217]");
	}
	
		String[][] daten = {};
	
	
	return daten; // gibt einen array mit allen Daten der Plugins für die Table zur Anzeige zurück für oben
	
	}
	
	


	    
	}







class JButtonRenderer implements TableCellRenderer {

    JButton button = new JButton();

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
        button.setText(value.toString());
        button.setToolTipText("Press " + value.toString());
        
        return button;
    }
}

class JButtonEditor extends AbstractCellEditor implements TableCellEditor {
    JButton button;
    String txt;

    public JButtonEditor() {
        super();
        button = new JButton();
        button.setOpaque(true);
        
        final String buttontexturl = "http://www..de/plugins.php?v=" +  rc_main.version + "?ai" .loremipsumsha;
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	button.addActionListener(new ActionListener () {

            		
					@Override
					public void actionPerformed(ActionEvent arg0) {
					    try {
							Desktop.getDesktop().browse(new URL(buttontexturl).toURI());
						} catch (IOException | URISyntaxException e) {
							JOptionPane.showMessageDialog(null,"Der Link " + buttontexturl + " konnte nicht geladen werden."
									+ "Bitte rufen Sie die Seite manuell auf. \n"
									+ "[catch IO Exception URI SyntaxEception in rc_plugins_add.java Z. 540]");
						}
					}} );
            }
        });
       
    }

    public Object getCellEditorValue() {
        return null;
    }

    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }

    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

    public void cancelCellEditing() {
    }

    public void addCellEditorListener(CellEditorListener l) {
    }

    public void removeCellEditorListener(CellEditorListener l) {
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        txt = (value == null) ? "" : value.toString();
        button.setText(txt);
        return button;
    }
} 
	
	

