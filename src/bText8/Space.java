package bText8;

import java.io.File;

import javax.swing.JFileChooser;

public class Space {
	protected String text;
	protected CustomItem menuItem;
	
	protected FileHand f;
	
	Space(String args, int x){		
		f = new FileHand(args);
		String text  = f.open();
		if(text != null) {
			menuItem = new CustomItem(f.name, x);
		}
	}
	
	Space(JFileChooser fc, int x){
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {//If they actually chose one
	        File file = (File) fc.getSelectedFile();
			f = new FileHand(file);
			String text  = f.open();
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
