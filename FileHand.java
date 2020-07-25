package bText8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHand {
	static File file;
	static Path filePath;
	
	public FileHand(File f) {
		file = f;
		filePath = file.toPath();
	}
	
	public String open() {
		String ret = "";//String to return
		
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {//reader
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        ret = ret + line + "\n";
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);//O shit, this borked
		}
		return ret;
	}
	
	public boolean save(String out) {
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset)) {//writer
		    writer.write(out, 0, out.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);//O shit, this borked
		    return false;
		}
		return true;
	}
}
