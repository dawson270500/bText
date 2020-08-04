package bText8;//Just class starter, doesn't do anything else

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class Main {
	static Gui win;
	
	public static void main(String args[]) {
		win = new Gui();
		if(args.length > 0) {
			win.files.add(new Space(args[0],0));
			if(win.files.get(win.curFile).text != null) {
				win.ta.setText(win.files.get(win.curFile).text);
				win.m3.add(win.files.get(win.curFile).menuItem);
				win.files.get(win.curFile).menuItem.addActionListener(win);
			}else {
				JOptionPane.showMessageDialog(null, "Opening failed, check file permissions");
			}
		}else {
			win.files.add(new Space(0));
			win.ta.setText(win.files.get(win.curFile).text);
			win.m3.add(win.files.get(win.curFile).menuItem);
			win.files.get(win.curFile).menuItem.addActionListener(win);
		}
		  win.ta.addKeyListener(new KeyListener() {
		
	        @SuppressWarnings("deprecation")
			@Override
	        public void keyPressed(KeyEvent e) {	        	
	            if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {//Save keybind
	            	if(win.files.get(win.curFile).f.name == null) {
	    				Common.saveAs();
	    			}else {
	    				Common.save();
	    			}
		        }else if ((e.getKeyCode() == KeyEvent.VK_O) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {//Open keybind
		           Common.open();
		        }else if ((e.getKeyCode() == KeyEvent.VK_N) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {//Open keybind
		        	Common.newFile();
		        }
	        }

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
					
			}

		});
	}
}
