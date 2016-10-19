/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.util.*;
import java.io.*;

import acm.util.ErrorException;


public class NameSurferDataBase implements NameSurferConstants {
	
	
	private HashMap<String, String> nameDataBase = new HashMap<String, String>();
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while(true){
				String line = br.readLine();
				if (line == null) break;
				
				NameSurferEntry oneName = new NameSurferEntry(line);
				String name = oneName.getName().toLowerCase();
				
				if(!nameDataBase.containsKey(name)){
					nameDataBase.put(name, line);
				}				
			}
			br.close();
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		name = name.toLowerCase();
		if(nameDataBase.containsKey(name)){
			String data = nameDataBase.get(name);
			NameSurferEntry nameData = new NameSurferEntry(data);
			return nameData;
		}
		else{
			return null;
		}
		
	}
}

