package org.jgrapht.demo;

/**
 *  14/11/2012
 * @author Helen Mamalaki
 *
 *
 */
public class SubjectNode extends Node
{

	public SubjectNode(String seqID, int start, int end, int alignmentLength, double identity)
	{
		super(seqID, start, end, alignmentLength, identity);
	
	}
	
	@Override
	public String toString()
	{
		String s;
		
		//s = "\nSubject "+  name + " node: sStart " + start + " <---> sEnd "  + end; 
		
		s =  start + " - "  + end; 
		
		 return s;
	}
	
}
