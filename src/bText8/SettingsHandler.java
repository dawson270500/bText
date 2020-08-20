package bText8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class SettingsHandler {
	public Boolean wordWarp = true;
	public Boolean show = true;
	public int tabLen = 4;
	
	private File file = new File("./.btext.conf");
	private Path set;
	
	
	SettingsHandler(){
		set = file.toPath();
		try (BufferedReader reader = Files.newBufferedReader(set, Charset.forName("UTF-8"))) {//reader
		    String line = null;
		    while ((line = reader.readLine()) != null) {//Reader, has to add \n to show the new lines
		        if(!line.contains("?") && !line.contains("[")) {
		        	if(line.contains("word")) {
		        		
		        		if(line.toLowerCase().contains("false")) {
		        			wordWarp = false;
		        		}else {
		        			wordWarp = true;
		        		}
		        	}
		        	else if(line.contains("showMB")) {
		        		if(line.toLowerCase().contains("false")) {
		        			show = false;
		        		}else {
		        			show = true;
		        		}
		        	}
		        	else if(line.contains("tabL")) {
		        		String[] num = line.split(" ");
		        		num[1].replace(" ", "");
		        		try{
		        			tabLen = Integer.parseInt(num[1]);
		        		}catch(Exception e) {
		        			tabLen = 4;
		        		}
		        	}
		        }
		    }
		} catch (IOException x) {
			try (BufferedWriter writer = Files.newBufferedWriter(set, Charset.forName("UTF-8"))) {//writer
			    writer.write("word- true\nshowMB- true\ntabL- 4", 0, 0);
			} catch (IOException q) {
			    System.err.format("IOException: Settings File Creation Failed", q);//O shit, this borked		
			    System.exit(10);
			}
		}
	}
	
	protected void saveSet() {
		String out = "word- "+ wordWarp + "\nshowMB" + show + "\ntabL- " + tabLen;
		try (BufferedWriter writer = Files.newBufferedWriter(set, Charset.forName("UTF-8"))) {//writer
		    writer.write(out);
		} catch (IOException q) {
		    System.err.format("IOException: Settings File Creation Failed", q);//O shit, this borked		
		    System.exit(10);
		}
	}
}
