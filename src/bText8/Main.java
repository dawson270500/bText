package bText8;//Just class starter, doesnt do anything else

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class Main {
	private static Gui win;
	
	public static void main(String args[]) {
		win = new Gui();
		  win.ta.addKeyListener(new KeyListener() {
		
	        @SuppressWarnings("deprecation")
			@Override
	        public void keyPressed(KeyEvent e) {
	            if ((e.getKeyCode() == KeyEvent.VK_S) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {//Save keybind
	            	if(win.curF != null) {//Save file open
		            	if(!win.curF.save(win.ta.getText())) {
		                	JOptionPane.showMessageDialog(null, "Saving failed, check file permissions");
		                }else {
		                	JOptionPane.showMessageDialog(null, "Saved successfully");
		                }
	            	}else {//Save as
        	            FileHand fh = new FileHand(win.fc);
        	            if(fh.file != null){//If they selected a file
	        	            if(!fh.save(win.ta.getText())) {
	        	            	JOptionPane.showMessageDialog(null, "Saving failed, check file permissions");
	        	            }else {
	        	            	JOptionPane.showMessageDialog(null, "Saved successfully");
	        	            }
        	            }
	            	}
		        }else if ((e.getKeyCode() == KeyEvent.VK_O) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {//Open keybind
		            win.curF = new FileHand(win.fc);
		            if(win.curF.file != null) {//If they selected a file
			            String out = win.curF.open();
			            if(out == null) {
			            	JOptionPane.showMessageDialog(null, "Opening failed, check file permissions");
			            }else {
			            	win.ta.setText(out);
			            }
		            }
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
