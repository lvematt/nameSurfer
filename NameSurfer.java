/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	public static void main(String[] args) {
		new NameSurfer().start(args);
	}
	
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public NameSurfer() {		
		JLabel name = new JLabel("Name: ");
		add(name, NORTH);
		textField = new JTextField(20);
		textField.addActionListener(this);
		textField.setActionCommand("Graph");
		add(textField, NORTH);
		
		JButton graphButton = new JButton("Graph");
		add(graphButton, NORTH);
		
		JButton deleteButton = new JButton("Delete");
		add(deleteButton, NORTH);
		
		JButton clearButton = new JButton("Clear");
		add(clearButton, NORTH);
		
		addActionListeners();
		graph = new NameSurferGraph();
		add(graph);
		
		dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		String cmd = e.getActionCommand();
		if (cmd.equals("Graph")) {
			//println(textField.getText());
			String inputName = textField.getText().toLowerCase();
			NameSurferEntry nameData = dataBase.findEntry(inputName);
			if(nameData!=null){
				graph.addEntry(nameData);
			}
			graph.update();
		} else if (cmd.equals("Clear")) {
			graph.clear();
		} else if (cmd.equals("Delete")) {
			String inputName = textField.getText().toLowerCase();
			NameSurferEntry nameData = dataBase.findEntry(inputName);
			if(nameData!=null){
				graph.deleteEntry(nameData);
			}
			graph.update();
		}
	}
	
	private JTextField textField;
	private NameSurferDataBase dataBase;
	//private static final String  FILE_NAME = "names-data.txt";
	private NameSurferGraph graph;
}
