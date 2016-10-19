/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

//import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line) {
		// You fill this in //
		//rank = {0,0,0,0,0,0,0,0,0,0,0,0,0};
		name = "";
		StringTokenizer st = new StringTokenizer(line," ");
		name = st.nextToken();
		int i = 0;
		while(st.hasMoreTokens()){
			String number = st.nextToken();
			rank[i] = Integer.parseInt(number);
			i++;
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		if(decade<=11 && decade>=0){
			return rank[decade];
		} else {
			return 0;
		}
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String str = "";
		str = str + name;
		for(int i=0; i<rank.length; i++) {
			str = str+" ";
			if (i == 0) str = str+"[";
			str = str + rank[i];
			if (i == 11) str = str +"]";
		}
		return str;
	}
	
	private String name;
	private int[] rank = {0,0,0,0,0,0,0,0,0,0,0,0};
}

