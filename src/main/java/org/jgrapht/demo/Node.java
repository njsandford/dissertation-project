package org.jgrapht.demo;
import java.util.ArrayList;


public class Node // implements Comparable<Node>
{
	
	String seqID;
	int  start, end, alignmentLength;
	double identity;
	ArrayList<SequenceEdge> seqEdge = new ArrayList<SequenceEdge>();
	//ArrayList<LinkEdge> linkEdge = new ArrayList<LinkEdge>();


	//Constants
	/*public enum Type 
	{
		MATCH, INSERTION, VARIATION
	}*/

	public Node(String seqID, int start, int end, int alignmentLength, double identity)
	{

		this.seqID = seqID;
		this.start = start;
		this.end = end;
		this.alignmentLength = alignmentLength;
		this.identity = identity;
		//genes found in the region information
	}


	public String getSeqID()
	{
		return seqID;
	}

	public int getStart()
	{
		return start;
	}

	public int getEnd()
	{
		return end;
	}
	
	public int getAligmentLength()
	{
		return alignmentLength;
	}

	public Double getIdentity()
	{
		return identity;
	}

	public void addSequenceEdge(SequenceEdge e)    
	{	
		seqEdge.add(e);	
	}

	public ArrayList<SequenceEdge> getSequenceEdge()    
	{	
		return seqEdge;	
	}

//	public Boolean equalsN(Node n)
//	{
//		if((this.seqID == n.seqID) && (this.start == n.start) && (this.end == n.end))
//		{
//			return true;
//		}else return false;
//		
//	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (end != other.end)
			return false;
	
		if (seqID == null)
		{
			if (other.seqID != null)
				return false;
		} else if (!seqID.equals(other.seqID))
			return false;
		if (start != other.start)
			return false;
		return true;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + ((seqID == null) ? 0 : seqID.hashCode());
		result = prime * result + start;
		return result;
	}


	
}
