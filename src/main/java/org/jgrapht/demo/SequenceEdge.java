package org.jgrapht.demo;

import org.jgrapht.graph.DefaultEdge;
/**
 * 
 * This class defines the edge that connects the start and end nodes of the same sequence. (see MatchEdge also)
 * Sequences must be the same.
 *
 */

public class SequenceEdge extends DefaultEdge
{

	/**
	 * default generate serializable ID 
	 */
	private static final long serialVersionUID = 1L;
	
	Type name;
	String seqID; //This edge connects nodes of the same sequence sequenceType


	//Edge labels
	public enum Type 
	{
		NEXT_MATCH,                  //edge sequenceType that connects two sequential matches together with no gap between them
		NEXT_MATCH_GAP,                //edge sequenceType that connects two sequential matches together with a gap between them
		CORRESPONDS_TO                      //edge that connect a query match node to its corresponding subject node
	}


	public SequenceEdge(Type name)
	{
		this.name = name;
	}

	public Type getName()
	{
		return name;
	}
	
	
	public String toString()
	{
		//String s = "\nSequenceEdge: " + getStartNode() + "\n\t===\t\n    " + name + "\n\t===" + getEndNode(); 
		return ""+ getName();
	}

		
	
}
