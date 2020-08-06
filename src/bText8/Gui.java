package bText8;//Gui class

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
//import javax.swing.text.EditorKit;
//import javax.swing.text.html.HTMLEditorKit;

public class Gui implements ActionListener{
	protected JFrame frame;//window 
		
	private JMenuBar mb;//Menu bar
	private JMenu m1;//File menu
		private CustomItem m11;//New
		private CustomItem m12;//Open
		private CustomItem m13;//Save
		private CustomItem m14;//save as
	private JMenu m2;//View menu
		private CustomItem m21;//Word warp
		private CustomItem m22;//Help in future
	protected JMenu m3;//Open files menu
		protected CustomItem m31;//Close file
		protected List<Space> files= new ArrayList<Space>();;//list of menu items for files open
		
	protected JTextArea ta;
	
	JScrollPane scroll;
	
	protected int curFile = 0;//File handling will need a massive change for the mutliple file tabs thing. Maybe make this an array?
	public final JFileChooser fc = new JFileChooser();
	public Gui() {//Creates the window
		frame = new JFrame("bText - Untitled");//Window
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500,500);
	    
	    mb = new JMenuBar();//Menu bar
        m1 = new JMenu("File");
        m2 = new JMenu("View");
        m3 = new JMenu("Tabs");
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        m11 = new CustomItem("New");
        m12 = new CustomItem("Open");
        m13 = new CustomItem("Save");
        m14 = new CustomItem("Save As");
        m21 = new CustomItem("Word Warp Off");
        m22 = new CustomItem("Help");
        m31 = new CustomItem("Close File", -2);
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        m2.add(m21);
        m2.add(m22);
        m3.add(m31);
        
        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        m14.addActionListener(this);
        m21.addActionListener(this);
        m22.addActionListener(this);
        m31.addActionListener(this);
        
        ta = new JTextArea();//text area
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        
        scroll = new JScrollPane(ta); //Scrollbar
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 
	    frame.getContentPane().add(BorderLayout.NORTH, mb); //Adds everything to the window
	    frame.getContentPane().add(BorderLayout.CENTER, scroll);
	    frame.setVisible(true);
	}
	
	@Override//Action Handler
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		CustomItem sou = (CustomItem) e.getSource();
		if(sou.val >= 0&& sou.val != curFile) {
			files.get(curFile).text = ta.getText();
			files.get(curFile).unsetSelected();
			
			curFile = sou.val;
			ta.setText(files.get(curFile).text);
			files.get(curFile).setSelected();
		}else if(sou.val == -2){
			if((files.size()-1) != 0) {
				m3.remove(files.get(curFile).menuItem);
				files.remove(curFile);
				curFile = 0;
				ta.setText(files.get(curFile).text);
				files.get(curFile).setSelected();
			}else{
				m3.remove(files.get(curFile).menuItem);
				files.remove(curFile);
				Common.newFile();
			}
		}else if(com == "New") {
			Common.newFile();
		}
		else if(com =="Open") {//Open file
			Common.open();          
		}else if(com == "Save") {//Save file
			if(files.get(curFile).f.name != null) {
				Common.save();
			}else {
				Common.saveAs();
			}
		}
		else if(com =="Word Warp Off") {//Word warp off
			ta.setLineWrap(false);
			ta.setWrapStyleWord(false);
			m21.setText("Word Warp On");
		}
		else if(com == "Word Warp On") {//word warp on
			ta.setLineWrap(true);
			ta.setWrapStyleWord(true);
			m21.setText("Word Warp Off");	    
		}else if(com == "Help") {
			if(JOptionPane.showConfirmDialog(null, "This requires an internet connection, is that okay", null, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
		        	Desktop.getDesktop().browse(new URL("https://github.com/dawson270500/bText/blob/master/HelpDoc.md").toURI());
		    	} catch (Exception e1) {
		        	e1.printStackTrace();
		    	}
			}
		}
		
		if(com =="Save As") {//Save as file
			Common.saveAs();
		}
	}
}
