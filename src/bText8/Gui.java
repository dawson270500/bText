package bText8;//Gui class

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Gui implements ActionListener{
	JFrame frame;
	JButton button;
	
	JMenuBar mb;//Menu bar
	JMenu m1;
		JMenuItem m11;//Open
		JMenuItem m12;//save
		JMenuItem m13;//Save as
	JMenu m2;
		JMenuItem m21;//Word warp
		JMenuItem m22;//Help in future
		
	JPanel panel;
	public JTextArea ta;
	
	JScrollPane scroll;
	
	public FileHand curF = null;
	public final JFileChooser fc = new JFileChooser();
	public Gui() {//Creates the window
		frame = new JFrame("bText");//Window
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500,500);
	    
	    mb = new JMenuBar();//Menu bar
        m1 = new JMenu("File");
        m2 = new JMenu("View");
        mb.add(m1);
        mb.add(m2);
        m11 = new JMenuItem("Open");
        m12 = new JMenuItem("Save");
        m13 = new JMenuItem("Save As");
        m21 = new JMenuItem("Word Warp Off");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m2.add(m21);
        
        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        m21.addActionListener(this);
        
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
		
		if(com =="Open") {//Open file
			int returnVal = fc.showOpenDialog(frame);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = (File) fc.getSelectedFile();
	            curF = new FileHand(file);
	            String out = curF.open();
	            if(out == null) {
	            	JOptionPane.showMessageDialog(null, "Opening failed, check file permissions");
	            }else {
	            	ta.setText(out);
	            }
	        }
		}
		else if(com == "Save") {//Save file
            if(!curF.save(ta.getText())) {
            	JOptionPane.showMessageDialog(null, "Saving failed, check file permissions");
            }else {
            	JOptionPane.showMessageDialog(null, "Saved successfully");
            }
		}
		else if(com =="Save As") {//Save as file
			int returnVal = fc.showOpenDialog(frame);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = (File) fc.getSelectedFile(); 
	            FileHand fh = new FileHand(file);
	            if(!fh.save(ta.getText())) {
	            	JOptionPane.showMessageDialog(null, "Saving failed, check file permissions");
	            }else {
	            	JOptionPane.showMessageDialog(null, "Saved successfully");
	            }
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
		}
	}
}
