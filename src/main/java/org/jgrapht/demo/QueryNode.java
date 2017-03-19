package org.jgrapht.demo;


public class QueryNode extends Node
{

	public QueryNode(String seqID, int start, int end, int alignmentLength, double identity)
	{
		super(seqID, start, end, alignmentLength, identity);
		
	}

	@Override
	public String toString()
	{
		String s;

		//s = "\nQuery "+  name + " node: qStart " + start + " <---> qEnd " + end;

		s =  start + " - "  + end;

		 return s;
	}

}
