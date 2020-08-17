package bText8;//Common methods, save me writing them out god knows how many times

import java.nio.charset.Charset;

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
		int done = Main.win.files.get(Main.win.curFile).save(Main.win.ta.getText(), Main.win.fc);
		if(done == 1) {
			JOptionPane.showMessageDialog(null, "Saved Successfully");
			Main.win.files.get(Main.win.curFile).setSelected();
			Main.win.frame.setTitle("bText - "+Main.win.files.get(Main.win.curFile).f.name);
			//Main.win.files.get(arg0)
		}else if(done == -1){
			JOptionPane.showMessageDialog(null, "Saving Failed, check file permissions");
		}
	}
	
	public static void open() {
		if(Main.win.files.get(Main.win.curFile).f == null) {
			System.out.print("2.1 \n");
			if(Main.win.files.get(Main.win.curFile).addFile(Main.win.fc)) {
				Main.win.ta.setText(Main.win.files.get(Main.win.curFile).text);
				Main.win.files.get(Main.win.curFile).setSelected();
			}
		}else {
			System.out.print("3\n");
			Main.win.files.get(Main.win.curFile).text = Main.win.ta.getText();
			Main.win.files.get(Main.win.curFile).unsetSelected();			
			Main.win.curFile = Main.win.files.size();
			Main.win.files.add(new Space(Main.win.fc, Main.win.curFile));
			if(Main.win.files.get(Main.win.curFile).menuItem != null) {
				Main.win.ta.setText(Main.win.files.get(Main.win.curFile).text);
				Main.win.m3.add(Main.win.files.get(Main.win.curFile).menuItem);
				Main.win.files.get(Main.win.curFile).menuItem.addActionListener(Main.win);
				Main.win.files.get(Main.win.curFile).setSelected();
				Main.win.frame.setTitle("bText - "+Main.win.files.get(Main.win.curFile).f.name);
				Main.win.curFile = Main.win.files.size() - 1;
			}else {
				Main.win.files.remove(Main.win.curFile);
				JOptionPane.showMessageDialog(null, "Opening Failed");
			}
		}
	}
	
	public static void openChar(String charset) {
		Main.win.files.get(Main.win.curFile).f.charset = Charset.forName(charset);;
		Main.win.files.get(Main.win.curFile).text = Main.win.files.get(Main.win.curFile).f.open();
	}
	
	public static void newFile() {
		Main.win.files.add(new Space(Main.win.files.size()+1));
		Main.win.files.get(Main.win.curFile).unsetSelected();
		Main.win.curFile = Main.win.files.size()-1;
		Main.win.ta.setText("");
		Main.win.m3.add(Main.win.files.get(Main.win.curFile).menuItem);
		Main.win.files.get(Main.win.curFile).menuItem.addActionListener(Main.win);
		Main.win.files.get(Main.win.curFile).setSelected();
		Main.win.frame.setTitle("bText - Untitled");
	}
}
