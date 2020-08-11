package bText8;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Space {
	protected String text;
	protected CustomItem menuItem;
	
	protected FileHand f;
	
	protected int pressCount = 0;
	protected ArrayList<String> undoBufffer = new ArrayList<String>();
	
	Space(String args, int x){		
		f = new FileHand(args);
		text  = f.open();
		if(text != null) {
			menuItem = new CustomItem(f.name, x);
		}
	}
	
	Space(JFileChooser fc, int x){
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {//If they actually chose one
	        File file = (File) fc.getSelectedFile();
			f = new FileHand(file);
			text  = f.open();
			if(text != null) {
				menuItem = new CustomItem(f.name, x);
			}
		}else {
			menuItem = null;
		}
	}
	
	Space(int x){
		text = "";
			
		f = new FileHand();
		menuItem = new CustomItem("- Untitled -", x);
	}
	
	void setSelected() {
		if(f.name != null) {
			menuItem.setText("- "+f.name+" -");
			Main.win.frame.setTitle("bText - " + f.name);
		}else {
			menuItem.setText("- Untitled -");
			Main.win.frame.setTitle("bText - Untitled");
		}
	}
	void unsetSelected() {
		if(f.name != null) {
			menuItem.setText(f.name);
		}else {
			menuItem.setText("Untitled");
		}
	}
	
	int save(String s, JFileChooser fc) {
		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {//If they actually chose one
	        File file = (File) fc.getSelectedFile();
			f = new FileHand(file);
			menuItem.setText(f.name);
			Boolean ret = f.save(s);
			if(ret == true) {
				return 1;
			}
			return -1;
		}	
		return 0;
		
	}
	public boolean save(String s) {
		return f.save(s);
	}
}
