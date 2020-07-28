package bText8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;

public class FileHand {
	
	protected File file = null;//Holds file details, e.g. meta data
	private Path filePath;//Path to the file, used by writer and reader
	
	private final Charset charset = Charset.forName("US-ASCII");//Character set, ability to change via GUI planned
	
	
	public FileHand(JFileChooser fc, String args) {//Using a provider file chooser from gui, lets the user choose file
		if(fc != null) {
			int returnVal = fc.showOpenDialog(null);
		    if (returnVal == JFileChooser.APPROVE_OPTION) {//If they actually chose one
		        file = (File) fc.getSelectedFile();
		        filePath = file.toPath();
		    }
		}
	    else if(args != null) {
	    	file  = new File(args);
	    	filePath = file.toPath();
	    }
	}
	
	
	
	public String open() {
		String ret = "";//String to return
		try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {//reader
		    String line = null;
		    while ((line = reader.readLine()) != null) {//Reader, has to add \n to show the new lines
		        ret = ret + line + "\n";
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);//O shit, this borked
		    return null;
		}
		return ret;
	}
	
	public boolean save(String out) {
		try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset)) {//writer
		    writer.write(out, 0, out.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);//O shit, this borked
		    return false;
		}
		return true;//We worked
	}
}
