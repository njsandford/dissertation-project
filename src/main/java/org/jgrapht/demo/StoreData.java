package org.jgrapht.demo;

/*** Class to store the values parsed in from the BLAST file */

public class StoreData 
{
	String queryID;
	String subjectID; 
	double identity; 
	int alignmentLength;
    int qStart;  //query alignment positions
	int qEnd;
	int sStart;  //subject alignment positions 
	int sEnd;

	/**Constructor - store parsed in fields for each match as a StoreData object*/
	public StoreData(String queryID, String subjectID, double identity, int alignmentLength, 
												int qStart, int qEnd, int sStart, int sEnd) {
		this.queryID = queryID;
		this.subjectID = subjectID; 
		this.identity = identity;
		this.alignmentLength = alignmentLength;
		this.qStart = qStart;
		this.qEnd = qEnd;
		this.sStart = sStart;
		this.sEnd = sEnd;
	}
	

	/**Methods to set and get each field*/	
	public void setQueryID(String qID){	
		queryID = qID; 
	}

	public String getQueryID(){
		return queryID; 
	}

	public void setSubjectID(String sID){
		subjectID = sID; 
	}

	public String getSubjectID(){
		return subjectID; 
	}

	public void setIdentity(Double id){
		identity = id;
	}

	public Double getIdentity()
	{
		return identity; 
	}

	public void setAlignmentLen(int alignmentLen)
	{
		alignmentLength = alignmentLen;
	}

	public int getAlignmentLen()
	{
		return alignmentLength; 
	}

	public void setqStart(int qS)
	{
		qStart = qS;
	}

	public int getqStart()
	{
		return qStart;
	}

	public void setqEnd(int qE)
	{
		qEnd = qE;
	}

	public int getqEnd()
	{
		return qEnd;
	}

	public void setsStart(int sS)
	{
		sStart = sS;
	}

	public int getsStart()
	{
		return sStart;
	}

	public void setsEnd(int sE)
	{
		sEnd = sE;
	}

	public int getsEnd()
	{
		return sEnd;
	}

	public String toString()
	{
		return "\n" + queryID + "\t" + subjectID + "\t" + identity + "\t" + alignmentLength + "\t" + qStart +
				"\t" + qEnd + "\t" + sStart +"\t" + sEnd;
	}

}

