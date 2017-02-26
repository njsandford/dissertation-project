package org.jgrapht.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class InsertionListener implements ActionListener
{
	IsomorphismAlgorithm subgraph1, subgraph2;
	JTextArea textBox;
	UserInputListener ui;
	
	public InsertionListener(IsomorphismAlgorithm subgraph1, IsomorphismAlgorithm subgraph2, JTextArea textBox, UserInputListener ui)
	{
		this.subgraph1 = subgraph1;
		this.subgraph2 = subgraph2;
		this.textBox = textBox;
		this.ui = ui;
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		subgraph1.isomorphismSearch(ui.posMin, ui.posMax);
		subgraph2.isomorphismSearch(ui.posMin, ui.posMax);
		String insertionQ = subgraph1.printPositions();
		String insertionS = subgraph2.printPositions();
		String insertionList = insertionQ + insertionS;
    	textBox.setText(insertionList);
		
	}
}
