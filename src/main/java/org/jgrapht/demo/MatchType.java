package org.jgrapht.demo;


public class MatchType 
{

	QueryNode qn, qn2;
	SubjectNode sn, sn2;
	Rearrangment type;
	String nodeType =  "Query";

	public MatchType(QueryNode qn, QueryNode qn2, SubjectNode sn, SubjectNode sn2, Rearrangment type)
	{
		this.qn = qn;
		this.qn2 = qn2;
		this.sn = sn;
		this.sn2 = sn2;
		this.type = type;
	}

	public enum Rearrangment 
	{
		QINSERTION, SINSERTION, QDELETION, SDELETION, VARIATION, INVERSION, SDUPLICATION, QDUPLICATION, UNCLASSIFIED;
	}


	public Rearrangment getType()
	{
		return type;
	}

	public String toString()
	{
		String s = "";

		if(qn != null && sn != null) 
		{
			
			switch (type) 
			{
			case QINSERTION:  
				
					s = s + "  Insertion found in query sequence at position " + qn.end + " ... " + qn2.start;
				
			break;
			case SINSERTION:
			
					s = s + "  Insertion found in subject sequence at position " + sn.end + " ... " + sn2.start; 
			
			break;
			case QDELETION: 
				if(qn2!=null)
				{
					s = s + "  Deletion found in query sequence at position "  + qn.end + " ... " + qn2.start;
				}
			break;
			case SDELETION: 
				
					s = s + "  Deletion found in subject sequence at position " + sn.end + " ... " + sn2.start;
				
			break;
			case VARIATION: 
				
					s = s + "  Variation found at position " + qn.end + " ... " + qn2.start + " in query sequence and position " +  sn.end + " ... " + sn2.start + " in subject sequence";
				
			break;
			case INVERSION: s = s + "  Inversion found in query sequence positon " + qn + " and subject sequence position " + sn;  
			break;
			case QDUPLICATION: 
				
					s = s + "  Duplication found in query sequence in position " + qn + " and then next in position " + qn2;
				
				break;
			case SDUPLICATION: 
				
					s = s + " Duplication found in query sequence in position " + sn + " and then next in position " + sn2;
				
				break;
			case UNCLASSIFIED: s = s + "  Unclassified rearrangment found";
			break;
			default:
				break;
			}
		}

		return s; 
	}

}
