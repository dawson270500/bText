package bText8;//Gui class

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Gui implements ActionListener{
	protected JFrame frame;//window 
		
	protected JMenuBar mb;//Menu bar
	private JMenu m1;//File menu
		private CustomItem m11;//New
		private CustomItem m12;//Open
		private CustomItem m13;//Save
		private CustomItem m14;//save as
	private JMenu m2;//View menu
		private CustomItem m21;//Word warp
		private CustomItem m22;//Help in future
		private CustomItem m23;//Hide menu bar
	protected JMenu m3;//Open files menu
		protected CustomItem m31;//Close file
		protected ArrayList<Space> files= new ArrayList<Space>();;//list of menu items for files open
	protected JMenu m4;//charsets
		protected CustomItem m41;
		protected CustomItem m42;
		protected CustomItem m43;
		protected CustomItem m44;
		
	protected JTextArea ta;
	
	JScrollPane scroll;
	WindowListener exit;
	protected int curFile = 0;//File handling will need a massive change for the mutliple file tabs thing. Maybe make this an array?
	public final JFileChooser fc = new JFileChooser();
	public Gui() {//Creates the window
		frame = new JFrame("bText - Untitled");//Window
	    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    
	    frame.setSize(500,500);
	    
	    exit = new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				if(checkClose()) {
					Main.settings.saveSet();
					System.exit(0);
				}else {
					if(JOptionPane.showConfirmDialog(null, "You haven't saved, are you sure?", "Not Saved", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						Main.settings.saveSet();
						System.exit(0);
					}					
				}
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    };
	    frame.addWindowListener(exit);
	    
	    //Anything with a minus uses the new flag system, everything will be changed over soon enough
	    mb = new JMenuBar();//Menu bar
        m1 = new JMenu("File");//File menu
	        m11 = new CustomItem("New", -1);
	        m12 = new CustomItem("Open", -3);
	        m13 = new CustomItem("Save", -4);
	        m14 = new CustomItem("Save As", -4);
        m2 = new JMenu("View");//View menu
        	if(Main.settings.wordWarp) {
        		m21 = new CustomItem("Word Warp Off", -5);
        	}else {
        		m21 = new CustomItem("Word Warp On", -5);
        	}
        	m22 = new CustomItem("Help", -6);
        	m23 = new CustomItem("Hide Menu", -7);
        m3 = new JMenu("Tabs");//tabs menu
        	m31 = new CustomItem("Close File", -2);
        m4 = new JMenu("Charset");//charset select, works on a file by file basis
        	m41 = new CustomItem("UTF-8", -10);
        	m42 = new CustomItem("US-ASCII", -10);
        	m43 = new CustomItem("ISO-8859", -10);
        	m44 = new CustomItem("UTF-16", -10);
        //add them in the order you wish to see them
        mb.add(m1);//File menu
	        m1.add(m11);
	        m1.add(m12);
	        m1.add(m13);
	        m1.add(m14);
	    mb.add(m3);//tabs menu
        	m3.add(m31);
        mb.add(m2);//view menu
	        m2.add(m21);
	        m2.add(m23);
	        m2.add(m22);
	    mb.add(m4);//Charset menu
	    	m4.add(m41);
	    	m4.add(m42);
	    	m4.add(m43);
	    	m4.add(m44);        
        mb.setVisible(Main.settings.show);
	    	
        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        m14.addActionListener(this);
        m21.addActionListener(this);
        m22.addActionListener(this);
        m23.addActionListener(this);
        m31.addActionListener(this);
        m41.addActionListener(this);
        m42.addActionListener(this);
        m43.addActionListener(this);
        m44.addActionListener(this);

        
        ta = new JTextArea();//text area
        ta.setLineWrap(true);
        ta.setWrapStyleWord(Main.settings.wordWarp);
        ta.setTabSize(Main.settings.tabLen);
        
        scroll = new JScrollPane(ta); //Scrollbar
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 
	    frame.getContentPane().add(BorderLayout.NORTH, mb); //Adds everything to the window
	    frame.getContentPane().add(BorderLayout.CENTER, scroll);
	    frame.setVisible(true);
	    
	}
	
	private Boolean checkClose() {
		for(int i = 0; i < files.size(); i++) {
			if(files.get(i).saveSinceChange == false) {
				return false;
			}
		}
		return true;
		
	}
	
	@Override//Action Handler
	public void actionPerformed(ActionEvent e) {
		CustomItem sou = (CustomItem) e.getSource();
		if(sou.val == 0) {
			for(int i = 0; i < files.size(); i++) {
				if(sou.getText() == files.get(i).menuItem.getText()) {
					files.get(curFile).unsetSelected();
					files.get(i).setSelected();
					files.get(curFile).text = ta.getText();
					curFile = i;
					ta.setText(files.get(curFile).text);
				}
			}
		}
		if(sou.val == -2){//close a file
			System.out.print("1");
			
			if(files.size() == 1) {
				m3.remove(files.get(curFile).menuItem);
				files.remove(curFile);
				Common.newFile();
				ta.setText("");
			}else {
				System.out.print("Selected " + curFile + "\n");
				m3.remove(files.get(curFile).menuItem);
				files.remove(curFile);
				curFile = 0;
				ta.setText(files.get(0).text);
				files.get(0).setSelected();
			}
		}else if(sou.val == -10){//Change charset
			String com = e.getActionCommand();
			
			Common.openChar(com);
		}else if(sou.val == -1) {//New file
			Common.newFile();
		}
		else if(sou.val == -3) {//Open file
			files.get(curFile).text = ta.getText();
			Common.open();          
		}else if(sou.val == -4) {//Save file
			String com = e.getActionCommand();
			if(com == "Save") {
				if(files.get(curFile).f != null) {
					files.get(curFile).text = ta.getText();
					Common.save();
				}else {
					files.get(curFile).text = ta.getText();
					Common.saveAs();
				}
			}else if(com == "Save As"){
				files.get(curFile).text = ta.getText();
				Common.saveAs();
			}
		}
		else if(sou.val == -5) {//Word warp off
			String com = e.getActionCommand();
			if(com == "Word Warp Off") {
				ta.setLineWrap(false);
				ta.setWrapStyleWord(false);
				Main.settings.wordWarp = false;
				Main.settings.saveSet();
				m21.setText("Word Warp On");
			}else if(com == "Word Warp On") {//word warp on
				ta.setLineWrap(true);
				ta.setWrapStyleWord(true);
				Main.settings.wordWarp = true;
				Main.settings.saveSet();
				m21.setText("Word Warp Off");	
			}
		}else if(sou.val == -7) {
			mb.setVisible(false);
			Main.settings.show = false;
			Main.settings.saveSet();
		}else if(sou.val == -6) {
			if(JOptionPane.showConfirmDialog(null, "This requires an internet connection, is that okay", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
		        	Desktop.getDesktop().browse(new URL("https://github.com/dawson270500/bText/blob/master/HelpDoc.md").toURI());
		    	} catch (Exception e1) {
		        	e1.printStackTrace();
		    	}
			}
		}
	}
}
