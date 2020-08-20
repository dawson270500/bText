package bText8;//File space, used for holding mutlipe files and simplify the GUI class

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Space {
	protected String text;
	protected CustomItem menuItem;
	
	protected FileHand f;
	
	protected Boolean saveSinceChange = true;
	
	protected int pressCount = 0;
	protected ArrayList<String> undoBufffer = new ArrayList<String>();
	
	Space(String args, int x){	//Opening from command line	
		f = new FileHand(args);
		text  = f.open();
		if(text != null) {
			menuItem = new CustomItem(f.name, 0);
			menuItem.addActionListener(Main.win);
		}
	}
	
	Space(JFileChooser fc, int x){//Opening with GUI
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {//If they actually chose one
	        File file = (File) fc.getSelectedFile();
			f = new FileHand(file);
			text  = f.open();
			if(text != null) {
				menuItem = new CustomItem(f.name, 0);
				menuItem.addActionListener(Main.win);
			}
		}else {
			menuItem = null;
		}
	}
	
	Space(int x){//Empty file
		text = "";
			
		f = null;
		menuItem = new CustomItem("- Untitled -", 0);
	}
	
	void setSelected() {//Set this file as the selected tab
		if(f != null) {
			menuItem.setText("- "+f.name+" -");
			Main.win.frame.setTitle("bText - " + f.name);
		}else {
			menuItem.setText("- Untitled -");
			Main.win.frame.setTitle("bText - Untitled");
		}
	}
	void unsetSelected() {//Unset this file as the selected tab
		if(f != null) {
			menuItem.setText(f.name);
		}else {
			menuItem.setText("Untitled");
		}
	}
	
	Boolean addFile(JFileChooser fc) {
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {//If they actually chose one
	        File file = (File) fc.getSelectedFile();
			f = new FileHand(file);
			menuItem.setText("- "+f.name+" -");
			Main.win.frame.setTitle("bText - "+f.name);
			text = f.open();
			return true;
		}
		return false;
	}
	
	int save(String s, JFileChooser fc) {//Save as | means you've either selected to save as or are saving an empty file
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {//If they actually chose one
	        File file = (File) fc.getSelectedFile();
			f = new FileHand(file);
			menuItem.setText("- "+f.name+" -");
			Main.win.frame.setTitle("bText - "+f.name);
			Boolean ret = f.save(s);
			if(ret == true) {
				saveSinceChange = true;
				return 1;
			}
			return -1;
		}	
		return 0;
		
	}
	public boolean save(String s) {//save
		Boolean saved = f.save(s);
		if(saved == true) {
			saveSinceChange = true;
			return true;
		}else {
			saveSinceChange = false;
			return false;
		}
	}
}
