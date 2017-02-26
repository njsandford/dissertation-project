package org.jgrapht.demo;


import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInputListener implements ActionListener
{
	JPanel userDefinedInput = new JPanel();
	JLabel lab = new JLabel("Range:  ");
	JLabel lab2 = new JLabel("  to  ");
	String pattern = "-\\d*";
	JFormattedTextField min = new JFormattedTextField(NumberFormat.getInstance());
	JFormattedTextField max = new JFormattedTextField(NumberFormat.getInstance());
	JTextField searchRange = new JTextField();
	JPanel panel;
	int posMin, posMax;
	boolean numbersEntered;

	public UserInputListener(JPanel panel)
	{
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{											


		min.setColumns(15);
		//min.setText("Min");
		max.setColumns(15);
		//	max.setText("Max");
		userDefinedInput.add(lab);
		userDefinedInput.add(min);
		userDefinedInput.add(lab2);
		userDefinedInput.add(max);
			
			searchRange.setEditable(false);
			searchRange.setFont(new Font("Ariel", Font.BOLD, 12));
			searchRange.setBorder(null);
			//searchRange.setBackground(new Color(237, 237, 237));
			searchRange.setText("Range: "); 
			searchRange.setText("Searching between alignment position " + posMin + " and " + posMax);
			GridBagConstraints c = new GridBagConstraints(); 
			c.insets = new Insets(16,155,16,150);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 1;
			c.gridy = 1;
			panel.add(searchRange, c);
			
			int result = JOptionPane.showConfirmDialog(null, userDefinedInput, "Specify a range of the sequence you wish to search in : ", 
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION)
			{	
				try{
					numbersEntered = true;
					if(min.getText().matches(pattern) || max.getText().matches(pattern)) //number is negative
					{
						showErrorDialogueBox();
					}
					posMin =  Integer.parseInt(min.getText());
					posMax =  Integer.parseInt(max.getText());
				}catch(NumberFormatException e)
				{
					showErrorDialogueBox();
				}
				
				if((posMin>posMax) && numbersEntered)
				{
					JOptionPane.showMessageDialog(null, "Upper bound value (Max) must exceed the lower bound value (Min).", "INPUT ERROR: ", 	JOptionPane.ERROR_MESSAGE);
				}			

		}      			 

	}
	
	private void showErrorDialogueBox()
	{
		JOptionPane.showMessageDialog(null, "Values entered must be positive whole numbers.", "INPUT ERROR: ", 	JOptionPane.ERROR_MESSAGE);
		numbersEntered = false;
	}

	public int getPosMin() {return posMin; }

	public void setPosMin(int pMin) {posMin = pMin;}

	public int getPosMax() {return posMax; }

	public void setPosMax(int pMax) {posMax = pMax;}

	public void removeText()
	{
		searchRange.setText("");

	}

}
