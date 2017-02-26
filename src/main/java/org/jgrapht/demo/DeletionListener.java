package org.jgrapht.demo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class DeletionListener implements ActionListener
{

		IsomorphismAlgorithm subgraph1, subgraph2;
		JTextArea textBox;
		UserInputListener ui;
		
		public DeletionListener(IsomorphismAlgorithm subgraph1, IsomorphismAlgorithm subgraph2, JTextArea textBox, UserInputListener ui)
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
			String deletionS = subgraph1.printDelPositions();
			String deletionQ = subgraph2.printDelPositions();
			String deletionList = deletionQ + deletionS;
	    	textBox.setText(deletionList);
			
		}
	

}
