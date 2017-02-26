package org.jgrapht.demo;

public class Match
{
	
	SubjectNode queryStart, queryEnd, subjectStart,  subjectEnd;
	QueryNode query, subject;

	public Match(SubjectNode queryStart, SubjectNode queryEnd, SubjectNode subjectStart, SubjectNode subjectEnd, QueryNode query, QueryNode subject)
	{
		this.queryStart = queryStart;
		this.queryEnd = queryEnd;
		this.subjectStart = subjectStart;
		this.query = query;
		this.subject = subject;
	}
	
	
}
