package org.jgrapht.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;


public class StartSearchListener implements ActionListener 
{
	IsomorphismAlgorithm subgraph;
	JTextArea textBox;
	int posMin, posMax;
	UserInputListener ui; 
	
	public StartSearchListener(IsomorphismAlgorithm subgraph, JTextArea textBox, UserInputListener ui)
	{
		this.subgraph = subgraph;
		this.textBox = textBox;
		this.ui = ui;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		subgraph.isomorphismSearch(ui.posMin, ui.posMax);
		String featureList = subgraph.printPositions(); 
    	textBox.setText(featureList);	
	}
	
	

}
