/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
		clear();
		
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// You fill this in //
		removeAll();
		dispList.clear();
		dispIndex.clear();
		colorIndex = 0;
		setup();
	}
	
	
	private void setup(){
		int height = getHeight();
		int width = getWidth();
		GLine upperLine = new GLine(0,GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE);
		add(upperLine);
		
		GLine lowerLine = new GLine(0, height-GRAPH_MARGIN_SIZE, width,height-GRAPH_MARGIN_SIZE);
		add(lowerLine);
		
		for(int i=0; i<NDECADES; i++){
			GLine gridLine = new GLine(i*(width/NDECADES), 0, i*(width/NDECADES), height);
			add(gridLine);
			//String str = "";
			String str = ""+(START_DECADE+i*10);
			GLabel label = new GLabel(str);
			
			add(label, i*(width/NDECADES), height-5);
		}
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		String name = entry.getName().toLowerCase();
		if (!dispList.containsKey(name)){
			dispList.put(name, entry);
			dispIndex.put(name,colorIndex);
			colorIndex++;
		}
		
	}
	
	/* Method: deleteEntry(entry) */
	/**
	 * Deletes a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void deleteEntry(NameSurferEntry entry) {
		String name = entry.getName().toLowerCase();
		if( dispList.containsKey(name)) {
			dispList.remove(name);
			dispIndex.remove(name);
		}
	}
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		// You fill this in //
		removeAll();
		setup();
		Iterator<String> iter = dispList.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			//get corresponding color of that entry
			Color color = getColor(dispIndex.get(key));
			NameSurferEntry entry = dispList.get(key);
			//draw the line
			plotEntry(entry, color);
		}
		/*
		for(int i=0; i<dispIndex.size(); i++){
			String key = dispIndex.get(i);
			NameSurferEntry entry = dispList.get(key);
			Color color = getColor(i);
			plotEntry(entry, color);
		}
		*/
	}
	
	/*
	 * plot the corresponding curve of an entry
	 */
	private void plotEntry(NameSurferEntry entry, Color color){
		String name = entry.getName();
		int width = getWidth();
		int height = getHeight();
		
		//draw a line between every decade
		//and draw label for every decade
		for (int i=0; i<(NDECADES-1); i++){
			int formerRank = entry.getRank(i);    //rank of former year
			int laterRank = entry.getRank(i+1);   //rank of later year
			double x1 = i*(width/NDECADES);
			double y1 = height-GRAPH_MARGIN_SIZE;
			double x2 = (i+1)*(width/NDECADES);
			double y2 = height-GRAPH_MARGIN_SIZE;
			String str = "";
			if (formerRank<1||formerRank>MAX_RANK){
				str = str+name+"*";
				GLabel label = new GLabel(str);
				label.setColor(color);
				add(label, x1, height-GRAPH_MARGIN_SIZE);
				if (laterRank>0 && laterRank<=1000){
					y2 = GRAPH_MARGIN_SIZE + (laterRank)*(height-2*GRAPH_MARGIN_SIZE)/MAX_RANK;
				}
			} else {
				//x1 = i*(width/NDECADES);
				y1 = GRAPH_MARGIN_SIZE + (formerRank)*(height-2*GRAPH_MARGIN_SIZE)/MAX_RANK;
				//x2 = (i+1)*(width/NDECADES);
				y2 = GRAPH_MARGIN_SIZE + (laterRank)*(height-2*GRAPH_MARGIN_SIZE)/MAX_RANK;
				if(laterRank<1||laterRank>MAX_RANK){
					y2 = height-GRAPH_MARGIN_SIZE;
				}
				str = str+name+formerRank;
				GLabel label = new GLabel(str);
				label.setColor(color);
				add(label, x1, y1);
			}
			
			GLine line = new GLine(x1,y1, x2, y2);
			line.setColor(color);
			add(line);
		}
		
		int lastRank = entry.getRank(NDECADES-1);
		if (lastRank<1||lastRank>MAX_RANK){
			String str = name+"*";
			GLabel label = new GLabel(str);
			label.setColor(color);
			add(label, 11*(width/NDECADES), height-GRAPH_MARGIN_SIZE);
		} else {
			String str = name+lastRank;
			GLabel label = new GLabel(str);
			label.setColor(color);
			add(label, 11*(width/NDECADES), GRAPH_MARGIN_SIZE + (lastRank)*(height-2*GRAPH_MARGIN_SIZE)/MAX_RANK);
		}
	}
	
	/*
	 * decide which color to use draw the line 
	 */
	private Color getColor(int i){
		if (i%4 == 0){
			return Color.blue;
		} else if (i%4 == 1) {
			return Color.red;
		} else if (i%4 ==2 ) {
			return Color.green;
		} else {
			return Color.cyan;
		}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private HashMap<String, NameSurferEntry> dispList = new HashMap<String, NameSurferEntry>();
	private HashMap<String, Integer> dispIndex = new HashMap<String, Integer>();
	private int colorIndex = 0;
}
