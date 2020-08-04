package bText8;

import javax.swing.JOptionPane;

public class Common {
	public static void save() {
		Boolean done = Main.win.files.get(Main.win.curFile).save(Main.win.ta.getText());
		if(done) {
			JOptionPane.showMessageDialog(null, "Saved Successfully");
		}else {
			JOptionPane.showMessageDialog(null, "Saving Failed");
		}
	}
	
	public static void saveAs() {
		Boolean done = Main.win.files.get(Main.win.curFile).save(Main.win.ta.getText(), Main.win.fc);
		if(done) {
			JOptionPane.showMessageDialog(null, "Saved Successfully");
			//Main.win.files.get(arg0)
		}else {
			JOptionPane.showMessageDialog(null, "Saving Failed");
		}
	}
	
	public static void open() {
		if(Main.win.ta.getText() == "") {
			Main.win.files.set(Main.win.curFile, new Space(Main.win.fc, Main.win.curFile));
		}else {
			Main.win.files.get(Main.win.curFile).text = Main.win.ta.getText();
			
			Main.win.curFile = Main.win.files.size();
			Main.win.files.add(new Space(Main.win.fc, Main.win.curFile));
			if(Main.win.files.get(Main.win.curFile).menuItem != null) {
				Main.win.ta.setText(Main.win.files.get(Main.win.curFile).text);
				Main.win.m3.add(Main.win.files.get(Main.win.curFile).menuItem);
				Main.win.files.get(Main.win.curFile).menuItem.addActionListener(Main.win);
			}else {
				Main.win.files.remove(Main.win.curFile);
				Main.win.curFile -= 1;
			}
		}
	}
	
	public static void newFile() {
		Main.win.files.get(Main.win.curFile).text = Main.win.ta.getText();
		
		Main.win.curFile = Main.win.files.size();
		Main.win.files.add(new Space(Main.win.curFile));
		Main.win.ta.setText("");
		Main.win.m3.add(Main.win.files.get(Main.win.curFile).menuItem);
		Main.win.files.get(Main.win.curFile).menuItem.addActionListener(Main.win);
	}
}
