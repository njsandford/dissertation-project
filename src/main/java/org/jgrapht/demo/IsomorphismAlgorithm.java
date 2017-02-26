package org.jgrapht.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.graph.ListenableDirectedGraph;


public class IsomorphismAlgorithm 
{
	ListenableDirectedGraph<Node, SequenceEdge> g1; 
	ListenableDirectedGraph<Node, SequenceEdge> g2;
	Set<QueryNode> querySet1 = new HashSet<QueryNode>(); //prune search space
	Set<QueryNode> querySet2 = new HashSet<QueryNode>();
	ArrayList<Node> tempDS = new ArrayList<Node>();
	ArrayList<MatchType> matchList = new ArrayList<MatchType>();
	ArrayList<MatchType> matchListd = new ArrayList<MatchType>();
	boolean matchFound = false;
	String rearrangmentType;
	String s = "";

	public IsomorphismAlgorithm(ListenableDirectedGraph<Node, SequenceEdge> g1, ListenableDirectedGraph<Node, SequenceEdge> g2, String rearrangmentType)
	{
		this.g1 = g1;
		this.g2 = g2;
		this.rearrangmentType = rearrangmentType;
	}


	public void isomorphismSearch(int posMin, int posMax)
	{
		
		pruneSearchSpace();

		if(posMin!=0 && posMax !=0) //if default values have been changed prune search space with user-defined values of alignment position. 
		{
			userPrunedSearchSpace(posMin, posMax);
		}

		matchList.clear();
		matchListd.clear();

		Iterator<QueryNode> itr = querySet2.iterator(); 
		QueryNode n2 = itr.next();

		for(QueryNode n : querySet1)
		{			

			nextNode(n, n2); //recursive function		
			s = ""; //used when checking for cycles
			tempDS.clear();
		
		}
	}

	public void pruneSearchSpace()
	{
		/**Pruning vertex set to reduce vertexes available for candidate selection*/
		for(Node n : g1.vertexSet())
		{
			if(n instanceof QueryNode)
			{
				QueryNode qn = (QueryNode) n;
				querySet1.add(qn);
			}
		}

		for(Node n : g2.vertexSet())
		{
			if(n instanceof QueryNode)
			{
				QueryNode qn = (QueryNode) n;
				querySet2.add(qn);
			}
		}
	}

	public void userPrunedSearchSpace(int posMin, int posMax)
	{
		Iterator<QueryNode> itr = querySet1.iterator();

		while(itr.hasNext())
		{
			QueryNode n = itr.next();
			if(!(n.getStart()>posMin && n.getStart()<posMax))
			{
				itr.remove();
			}	
		}			
	}

	/**Recursive function*/
	private void nextNode(Node node, Node node2)
	{

		boolean call = false;

		

		loop:
			for(SequenceEdge se : g1.edgesOf(node))
			{

				

				for(SequenceEdge se2 : g2.edgesOf(node2))
				{
				
					if(se.getName().equals(se2.getName()))
					{
						if(tempDS.isEmpty())
						{
							nodeTypeTest(node, node2);
						}
						//CycleDetector<Node, SequenceEdge> cycle = new CycleDetector<Node, SequenceEdge>(g1);

						//						if(cycle.detectCyclesContainingVertex(node))
						//						{
						//							System.out.print("\nAll nodes in inverted match added - add node set to Match List");
						//							addToMatchList();
						//							matchFound=true;
						//							System.out.print("\n MatchFound = = " + matchFound);
						//							break loop; 
						//
						//						}
						if(rearrangmentType.equals("Inversion"))
						{
							if(se.name.equals(SequenceEdge.Type.CORRESPONDS_TO) && cycle(node, se))
							{		
					
								addToMatchList();


								break loop; 

							}
						}

						if (rearrangmentType.equals("SDuplication")||rearrangmentType.equals("QDuplication"))
						{

							if(node.equals(g1.getEdgeTarget(se))&& (se.name.equals(SequenceEdge.Type.CORRESPONDS_TO)) && backToStart(g1.getEdgeSource(se)))
							{
								//last edge added will not be a corresponds To edge, since it is always chosen/ added first and is therefore added third. 
								if(vertexSize(tempDS))
								{
									
									addToMatchList();
									call = false;
									break loop; 

								}
							}
						}

					

						if(node.equals(g1.getEdgeTarget(se))&& !(se.name.equals(SequenceEdge.Type.CORRESPONDS_TO)) && backToStart(g1.getEdgeSource(se)))
						{
							 
							if(vertexSize(tempDS))
							{
								
								addToMatchList();
								call = false;
								break loop;

							}
						}
						else if((node.equals(g1.getEdgeTarget(se)))&&(node2.equals(g2.getEdgeTarget(se2))) &&(se.name.equals(SequenceEdge.Type.CORRESPONDS_TO)) && (!beenThere(g1.getEdgeSource(se))))
						{

						
							{
								node = g1.getEdgeSource(se);
								node2 = g2.getEdgeSource(se2);

								
								if(targetNodeTypeTest(node, node2))
								{								
									if(!(tempDS.size()>g2.vertexSet().size()))
									{									
										call = true;
										
										break loop;
									}	
								}
							}	

						}else if((node.equals(g1.getEdgeSource(se)) && node2.equals(g2.getEdgeSource(se2))) && (se.name.equals(SequenceEdge.Type.CORRESPONDS_TO)) && (!beenThere(g1.getEdgeTarget(se))))
						{
							node = g1.getEdgeTarget(se);
							node2 = g2.getEdgeTarget(se2);


							
							if(targetNodeTypeTest(node, node2))
							{								
								if(!(tempDS.size()>=g2.vertexSet().size()))
								{									
									call = true;
								
									break loop;
								}	
							}

						}else if(node.equals(g1.getEdgeSource(se))&& !(se.name.equals(SequenceEdge.Type.CORRESPONDS_TO)))
						{
							node = g1.getEdgeTarget(se);
							node2 = g2.getEdgeTarget(se2);

							if(targetNodeTypeTest(node, node2))
							{								
								if(!(tempDS.size()>g2.vertexSet().size()))
								{									
									call = true;
									
									break loop;
								}	
							}
						}
					}
					
				}
				
			}	

	
		if(call)
		{
			nextNode(node, node2);
		}



	}

	private void nodeTypeTest(Node node, Node node2)
	{
		String className = node2.getClass().getSimpleName();
		
		if(node instanceof QueryNode && className.equals("QueryNode")) 
		{
			node = (QueryNode) node;
			tempDS.add(node);
			

		}else if(node instanceof SubjectNode && className.equals("SubjectNode"))
		{
			node = (SubjectNode) node;
			tempDS.add(node);
			
		}else
		{

		}
	}

	
	private boolean targetNodeTypeTest(Node node, Node node2)
	{
		String className = node2.getClass().getSimpleName();
		
		if(node instanceof QueryNode && className.equals("QueryNode")) 
		{
			node = (QueryNode) node;
			tempDS.add(node);
	
			return true;

		}else if(node instanceof SubjectNode && className.equals("SubjectNode"))
		{
			node = (SubjectNode) node;
			tempDS.add(node);
			//System.out.print("\n TARGET node is of the correct type (Subject) " + node + " is also added");		
			return true;
		}

		return false;
	}

	public Boolean cycle(Node node, SequenceEdge se)
	{

		if((node.equals(g1.getEdgeTarget(se)) && (node instanceof SubjectNode))) //|| (node.equals(g1.getEdgeSource(se)) && (node instanceof QueryNode))) ) 
		{s=s+"DOWN";
	
		}

		if((node.equals(g1.getEdgeTarget(se)) && (node instanceof QueryNode)))// || (node.equals(g1.getEdgeSource(se)) && (node instanceof SubjectNode)) )
		{s=s+"UP";
		
		}
		
		if(s.equals("DOWNUP") || s.equals("UPDOWN"))
		{
			return true;
		}

		return false;
	}



	public Boolean beenThere(Node node)
	{
		for(Node n : tempDS)
		{
			if((n.getClass().isInstance(node)))
			{
				if((n.getStart() == node.getStart())&& (n.getEnd() == node.getEnd()))
				{
					return true;
				}
			}
		}
		return false;
	}

	public Boolean backToStart(Node node)
	{

		if((tempDS.get(0).getClass().isInstance(node)))
		{
			if((tempDS.get(0).getStart() == node.getStart())&& (tempDS.get(0).getEnd() == node.getEnd()))
			{
				return true;
			}
		}

		return false;
	}
	public void addToMatchList()
	{
		Boolean enteredQ = false;
		Boolean enteredS = false;
		QueryNode qn = null;
		QueryNode qn2 = null;
		SubjectNode sn = null;
		SubjectNode sn2 =null;

		for(int i=0; i<tempDS.size(); i++)
		{
			if(tempDS.get(i) instanceof QueryNode)
			{
				if(!enteredQ)
				{
					qn = (QueryNode) tempDS.get(i);
					//System.out.print("enteredQ" + qn);
				}else if(enteredQ)
				{
					qn2 = (QueryNode) tempDS.get(i);
				}

				enteredQ = true;
			}else if(tempDS.get(i) instanceof SubjectNode) 
			{
				if(!enteredS)
				{
					sn = (SubjectNode) tempDS.get(i);
					//System.out.print("enteredS" + sn);
				}else if(enteredS)
				{
					sn2 = (SubjectNode) tempDS.get(i);
				}
				enteredS = true;

			}
		}

		MatchType mt = new MatchType(qn, qn2, sn, sn2, MatchType.Rearrangment.UNCLASSIFIED);
		MatchType mtd = new MatchType(qn, qn2, sn, sn2, MatchType.Rearrangment.UNCLASSIFIED);

		if(rearrangmentType.equals("QInsertion")) 
		{
			System.out.print("\nDeletion in Subject " + qn + " " + qn2 + " " + sn + " " + sn2);
			mt = new MatchType(qn, qn2, sn, sn2, MatchType.Rearrangment.QINSERTION);
			mtd = new MatchType(qn, qn2, sn, sn2, MatchType.Rearrangment.SDELETION);

		}else if(rearrangmentType.equals("SInsertion"))
		{
			//System.out.print("\nSubject Ins" + qn + " " + qn2 + " " + sn + " " + sn2);
			mt = new MatchType(qn, qn2, sn, sn2, MatchType.Rearrangment.SINSERTION);
			mtd = new MatchType(qn, qn2, sn, sn2, MatchType.Rearrangment.QDELETION);
		}else if(rearrangmentType.equals("Variation"))
		{
			mt = new MatchType(qn, qn2, sn, sn2, MatchType.Rearrangment.VARIATION);
		}else if(rearrangmentType.equals("Inversion"))
		{
			mt = new MatchType(qn, qn2, sn, sn2, MatchType.Rearrangment.INVERSION);
		}else if(rearrangmentType.equals("QDuplication"))
		{
			mt = new MatchType(qn, qn2, sn, null, MatchType.Rearrangment.QDUPLICATION);
		}else if(rearrangmentType.equals("SDuplication"))
		{
			mt = new MatchType(qn, null, sn, sn2, MatchType.Rearrangment.SDUPLICATION);
		}

		//System.out.print("\nMatchList: " + mt);
		matchList.add(mt);
		matchListd.add(mtd);

		tempDS.clear();

	}

	public String printPositions()
	{
		String s = "";
		for(MatchType mt: matchList)
		{
			s = s + "\n" + mt;
		}	
		return s;
	}

	public String printDelPositions()
	{
		String d = "";
		for(MatchType mtd: matchListd)
		{
			d = d + "\n" + mtd;
		}	
		return d;
	}


	public ArrayList<MatchType> getDelMatchList()
	{
		return matchListd;
	}

	public ArrayList<MatchType> getMatchList()
	{
		return matchList;
	}


	public Boolean vertexSize(ArrayList<Node> tempDS)
	{
		//tempDS contains the same number of nodes as querySet2
		if (tempDS.size() == g2.vertexSet().size())
		{
			//System.out.print(tempDS);
			return true;

		}else return false;
	}



	public String toString()
	{
		return "\nAlgorithm Finshed";
	}
}




