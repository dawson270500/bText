package bText8;//File handler, everything to do with files

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHand {
	
	protected File file = null;//Holds file details, e.g. meta data
	protected Path filePath;//Path to the file, used by writer and reader
	protected String name;
	
	private final Charset charset = Charset.forName("US-ASCII");//Character set, ability to change via GUI planned
	
	
	public FileHand(String args) {//Using file provided by CLI
	    if(args != null) {
	    	file  = new File(args);
	    	filePath = file.toPath();
	    	name = file.getName();
	    }
	}
	
	public FileHand(File f) {//Using file provided by a fileChooser
		file = f; 
	    filePath = file.toPath();
	    name = file.getName();
	}
	
	public FileHand() {
		name = null;
	}



	public String open() {
		String ret = "";//String to return
		try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {//reader
		    String line = null;
		    while ((line = reader.readLine()) != null) {//Reader, has to add \n to show the new lines
		        ret = ret + line + "\n";
		    }
		} catch (IOException x) {
			try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset)) {//writer
			    writer.write("", 0, 0);
			} catch (IOException q) {
			    System.err.format("IOException: File Creation Failed", q);//O shit, this borked
			    return null;
			}
			ret = "";
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
