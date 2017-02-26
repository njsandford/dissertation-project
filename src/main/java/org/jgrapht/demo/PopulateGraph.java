package org.jgrapht.demo;

import java.util.HashSet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.jgrapht.demo.JGraphXAdapter;
import org.jgrapht.demo.Node;
import org.jgrapht.demo.QueryNode;
import org.jgrapht.demo.SequenceEdge;
import org.jgrapht.demo.StoreData;
import org.jgrapht.demo.SubjectNode;
import org.jgrapht.graph.ListenableDirectedGraph;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

import com.mxgraph.view.mxGraphSelectionModel;



public class PopulateGraph 
{
	static ListenableDirectedGraph<Node, SequenceEdge> g = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);
	JGraphXAdapter<Node, SequenceEdge> graph = new JGraphXAdapter<Node, SequenceEdge>(g);


	static ArrayList<StoreData> arrayList = new ArrayList<StoreData>();
	static HashSet<QueryNode> qinversionPos = new HashSet<QueryNode>();
	static HashSet<SubjectNode> sinversionPos = new HashSet<SubjectNode>();
	static ArrayList<QueryNode> queryVertices = new ArrayList<QueryNode>();
	static ArrayList<SubjectNode> subjectVertices = new ArrayList<SubjectNode>();

	static QueryNode qmA;
	static SubjectNode smA;
	QueryNode queryNode;
	SubjectNode subjectNode;
	static int arraySize; // = 8565; // arrayList.size();


	public static void main(String[] args2)
	{
		dataToGraph();
		testWithoutGUI();
		PopulateGraph pg = new PopulateGraph();
		pg.graphVisualisation();
		pg.GUI();

	}


	public static void testWithoutGUI()
	{
		/**Test without using GUI, print isomorphismAlgorithm results to terminal.*/

		boolean quit = false;

		while (!quit){

			System.out.print("\n\nSelect a rearrangment type:" +
					" \n Press 1 for Insertions\n Press 2 for Deletions \n Press 3 for" +
					" Inversions \n Press 4 for Duplications \n Press 5 for Variations " +
					"\nPress 6 to Exit");

			Scanner keyIn = new Scanner(System.in);
			int c = keyIn.nextInt();

			switch (c){  
			case 1:
				/*INSERTION*/
				IsomorphismAlgorithm subgraph1;
				subgraph1 = new IsomorphismAlgorithm(g, insertionInQueryMotif(), "QInsertion");	
				subgraph1.isomorphismSearch(0, 0);
				IsomorphismAlgorithm subgraph2;
				subgraph2 = new IsomorphismAlgorithm(g, insertionInSubjectMotif(), "SInsertion");
				subgraph2.isomorphismSearch(0, 0);
				System.out.print("\n" + subgraph1.printPositions()  +	subgraph2.printPositions());
				break;
			case 2:
				/*DELETION*/
				IsomorphismAlgorithm subgraphd1;
				subgraphd1 = new IsomorphismAlgorithm(g, insertionInQueryMotif(), "QInsertion");
				subgraphd1.isomorphismSearch(0, 0);
				IsomorphismAlgorithm subgraphd2;
				subgraphd2 = new IsomorphismAlgorithm(g, insertionInSubjectMotif(), "SInsertion");
				subgraphd2.isomorphismSearch(0, 0);
				System.out.print("\n" +  subgraphd1.printDelPositions() + subgraphd2.printDelPositions());
				break;
			case 3:
				/*INVERSION*/
				IsomorphismAlgorithm subgraph4;
				subgraph4 = new IsomorphismAlgorithm(g, inversionMotif(),"Inversion");
				subgraph4.isomorphismSearch(0, 0);
				System.out.print("\n" + subgraph4.printPositions());

				break;
			case 4:
				/*DUPLICATIONS*/
				IsomorphismAlgorithm subgraph5;
				subgraph5 = new IsomorphismAlgorithm(g, duplicationInSubjectMotif(),"SDuplication");
				subgraph5.isomorphismSearch(0, 0);
				System.out.print("\n" + subgraph5.printPositions());

				IsomorphismAlgorithm subgraph6;
				subgraph6 = new IsomorphismAlgorithm(g, duplicationInQueryMotif(),"QDuplication");
				subgraph6.isomorphismSearch(0, 0);
				System.out.print("\n" + subgraph6.printPositions());
				break;
			case 5:
				/*VARIATION*/
				IsomorphismAlgorithm subgraph3;
				subgraph3 = new IsomorphismAlgorithm(g, variationMotif(), "Variation");
				subgraph3.isomorphismSearch(0, 0);
				System.out.print("\n" + subgraph3.printPositions());
				break;
			case 6: quit = true;
			break;
			default:
				System.out.println("\nInvalid Entry!");
			}

		}

	}

	public static ListenableDirectedGraph<Node, SequenceEdge>  insertionInQueryMotif()
	{

		ListenableDirectedGraph<Node, SequenceEdge> g2 = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

		QueryNode qn = new QueryNode("qn", 0, 0, 0, 0.0);
		QueryNode qn2 = new QueryNode("qn2", 0, 0, 0, 0.0);
		SubjectNode sn = new SubjectNode("sn", 0, 0, 0, 0.0);
		SubjectNode sn2 = new SubjectNode("sn2", 0, 0, 0, 0.0);

		g2.addVertex(qn);
		g2.addVertex(qn2);
		g2.addVertex(sn);
		g2.addVertex(sn2);

		g2.addEdge(qn, qn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
		g2.addEdge(sn, sn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
		g2.addEdge(qn, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
		g2.addEdge(qn2, sn2, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));

		return g2;

	}


	public static ListenableDirectedGraph<Node, SequenceEdge> insertionInSubjectMotif()
	{
		ListenableDirectedGraph<Node, SequenceEdge> g2 = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

		QueryNode qn = new QueryNode("qn", 0, 0, 0, 0.0);
		QueryNode qn2 = new QueryNode("qn2", 0, 0, 0, 0.0);
		SubjectNode sn = new SubjectNode("sn", 0, 0, 0, 0.0);
		SubjectNode sn2 = new SubjectNode("sn2", 0, 0, 0, 0.0);

		g2.addVertex(qn);
		g2.addVertex(qn2);
		g2.addVertex(sn);
		g2.addVertex(sn2);

		g2.addEdge(qn, qn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
		g2.addEdge(sn, sn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
		g2.addEdge(qn, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
		g2.addEdge(qn2, sn2, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));

		return g2;
	}

	public static ListenableDirectedGraph<Node, SequenceEdge> inversionMotif()
	{
		ListenableDirectedGraph<Node, SequenceEdge> g2 = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

		QueryNode qn = new QueryNode("qn", 0, 0, 0, 0.0);
		SubjectNode sn = new SubjectNode("sn", 0, 0, 0, 0.0);

		g2.addVertex(qn);
		g2.addVertex(sn);

		g2.addEdge(qn, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
		g2.addEdge(sn, qn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));

		return g2;

	}

	public static ListenableDirectedGraph<Node, SequenceEdge> variationMotif()
	{
		ListenableDirectedGraph<Node, SequenceEdge> g2 = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

		QueryNode qn = new QueryNode("qn", 0, 0, 0, 0.0);
		QueryNode qn2 = new QueryNode("qn2", 0, 0, 0, 0.0);
		SubjectNode sn = new SubjectNode("sn", 0, 0, 0, 0.0);
		SubjectNode sn2 = new SubjectNode("sn2", 0, 0, 0, 0.0);

		g2.addVertex(qn);
		g2.addVertex(qn2);
		g2.addVertex(sn);
		g2.addVertex(sn2);

		g2.addEdge(qn, qn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
		g2.addEdge(sn, sn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
		g2.addEdge(qn, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
		g2.addEdge(qn2, sn2, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));

		return g2;
	}

	public static ListenableDirectedGraph<Node, SequenceEdge> duplicationInSubjectMotif()
	{
		ListenableDirectedGraph<Node, SequenceEdge> g2 = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

		QueryNode qn = new QueryNode("qn", 0, 0, 0, 0.0);
		SubjectNode sn = new SubjectNode("sn", 0, 0, 0, 0.0);
		SubjectNode sn2 = new SubjectNode("sn2", 0, 0, 0, 0.0);

		g2.addVertex(qn);
		g2.addVertex(sn);
		g2.addVertex(sn2);

		g2.addEdge(qn, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
		g2.addEdge(sn, sn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
		g2.addEdge(qn, sn2, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));

		return g2;
	}

	public static ListenableDirectedGraph<Node, SequenceEdge> duplicationInQueryMotif()
	{
		ListenableDirectedGraph<Node, SequenceEdge> g2 = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

		SubjectNode sn = new SubjectNode("sn", 0, 0, 0, 0.0);
		QueryNode qn = new QueryNode("qn", 0, 0, 0, 0.0);
		QueryNode qn2 = new QueryNode("qn2", 0, 0, 0, 0.0);

		g2.addVertex(sn);
		g2.addVertex(qn);
		g2.addVertex(qn2);

		g2.addEdge(qn, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
		g2.addEdge(qn, qn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
		g2.addEdge(qn2, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));

		return g2;
	}





	public static void dataToGraph()
	{


		System.out.println("\nMax Memory; " + java.lang.Runtime.getRuntime().maxMemory()); 
		readFile("NC_009641_Newman.fna-NC_002952_MRSA252.fna.blast");  //EDITEDIT.fna.blast //ArtificialDataV2Unordered.txt //NC_009641_Newman.fna-NC_002952_MRSA252.fna.blast
		sortMatches();

		for(StoreData sd: getStoredData())
		{
			System.out.print(sd.toString());
		}



		/*Create Match Nodes*/
		String queryID=arrayList.get(0).queryID;
		String subjectID=arrayList.get(0).subjectID;
		StoreData local;


		for(int i=0; i<arraySize; i++)
		{

			local=arrayList.get(i);
			qmA = new QueryNode(queryID, local.getqStart(),  local.getqEnd(), local.getAlignmentLen(), local.getIdentity());
			smA = new SubjectNode(subjectID, local.getsStart(), local.getsEnd(), local.getAlignmentLen(), local.getIdentity());

			queryVertices.add(qmA);
			subjectVertices.add(smA);

			g.addVertex(qmA);
			g.addVertex(smA);

		}


		System.out.print("\n\n******** END Nodes *********");


		QueryNode qn;
		SubjectNode sn;
		QueryNode qn2 = null;
		SubjectNode sn2 = null;
		int lastElement = arraySize-1;	

		int inv;
		int gap;

		/*Add Vertical edges to unsorted matches*/
		for(int i=0; i<arraySize; i++)
		{
			qn =  queryVertices.get(i);
			sn = subjectVertices.get(i);


			g.addEdge(qn, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));

			inv = checkForInversions(qn, sn);

			if(inv!=0) // if INV equal to 1 or 2...
			{

				g.addEdge(sn, qn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
			}

			/*Adding second CORRESPONDS_TO labelled edge to a duplicate node
			 * Duplicate nodes aren't added to a set so there's no need to manually remove them from the Vertex Set
			 * This is why the arrayList queryVertices and subjectVertices is used to work out how to connect the vertices (as the duplicate node is still present)
			 */
			if(i!=0)
			{
				//If query node equals previous query node
				if(qn.equals(queryVertices.get(i-1)))
				{	
					//g.addEdge(queryVertices.get(i-1), sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
					g.addEdge(qn, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
				}else if(sn.equals(subjectVertices.get(i-1)))
				{
					//g.addEdge(qn, subjectVertices.get(i-1), new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
					g.addEdge(qn, sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
				}
			}
		} //end for

		System.out.print("\n\n******** END Vertical List*********");

		/*Add horizontal edges to sorted query and subject nodes*/
		boolean qduplicate;
		boolean sduplicate;


		for(int i=0; i<arraySize; i++)
		{
			//long check = System.currentTimeMillis();

			qn =  queryVertices.get(i);
			sn = subjectVertices.get(i);

			if(i!=lastElement) //last match in the array doesn't have a following i+1 match
			{
				qn2 = queryVertices.get(i+1);
				sn2 = subjectVertices.get(i+1);
			}
			qduplicate = qn.equals(qn2);
			sduplicate = sn.equals(sn2);

			gap = checkForGap(qn, qn2, sn, sn2);


			switch (gap) {
			case 1: 
				if(!qduplicate){  //ensures a horizontal edge isn't added to the duplicated vertices that appear in the arrayList, vertices don't exist in the vertex set anyway.				
					g.addEdge(qn, qn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
				}
				if(!sduplicate){
					g.addEdge(sn, sn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
				}

				break;
			case 2: 
				if(!qduplicate){
					g.addEdge(qn, qn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
				}
				if(!sduplicate){
					g.addEdge(sn, sn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
				}

				break;
			case 3:  
				if(!qduplicate){
					g.addEdge(qn, qn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
				}
				if(!sduplicate){
					g.addEdge(sn, sn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
				}

				break;
			case 4:
				if(!qduplicate){
					g.addEdge(qn, qn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
				}
				if(!sduplicate){
					g.addEdge(sn, sn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
				}
				break;
			default: 
				if(!qduplicate){
					g.addEdge(qn, qn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
				}
				if(!sduplicate){
					g.addEdge(sn, sn2, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
				}
				break;}

		}//end for




		//System.out.println(System.currentTimeMillis()-check);


		System.out.print("\n\n******** END Horizontal Edges*********");


	}


	public void setGraphStyle()
	{


		mxStylesheet stylesheet = graph.getStylesheet();

		Hashtable<String, Object> nodeStyle = new Hashtable<String, Object>();
		nodeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		nodeStyle.put(mxConstants.STYLE_OPACITY, 80);
		nodeStyle.put(mxConstants.STYLE_FILLCOLOR, "#9999FF");
		nodeStyle.put(mxConstants.STYLE_STROKECOLOR, "#000000");

		stylesheet.putCellStyle("ROUNDED", nodeStyle);

		Map<String, Object> edgeStyle = new HashMap<String, Object>();
		edgeStyle.put(mxConstants.STYLE_LABEL_BACKGROUNDCOLOR, "#EDEDED");

		stylesheet.putCellStyle("LABEL", edgeStyle);

		graph.setStylesheet(stylesheet);
	}

	public void graphVisualisation()
	{
		setGraphStyle();

		graph.getModel().beginUpdate(); //graph.getModel() allow for the graph to be changed dynamically 

		int counter = 0;
		double x = 20, y = 20;

		for (mxCell cell : graph.getVertexToCellMap().values()) 
		{     		        	

			if(cell.getValue() instanceof QueryNode)
			{
				graph.getModel().setStyle(cell, "ROUNDED;STYLE_FILLCOLOR=#9999FF");
				y=20;

			}else if(cell.getValue() instanceof SubjectNode)
			{
				graph.getModel().setStyle(cell,"ROUNDED;fillColor=#66CC00");
				y = 300;
			}

			if(counter%2==0){x = x + 180;}

			graph.getModel().setGeometry(cell, new mxGeometry(x, y, 85, 55)); //60, 55
			counter++;
		}


		for(mxCell cell2 : graph.getEdgeToCellMap().values())
		{
			for (int i=0; i<graph.getVertexToCellMap().values().size(); i++) //mxCell cell : graph.getVertexToCellMap().values()) 
			{
				//cell.getValue();
				if(cell2.getValue().toString().equals("NEXT_MATCH_GAP"))
				{
					graph.getModel().setStyle(cell2,"LABEL;dashed=1;strokeColor=#990099;spacingBottom=-15"); 
				}
			}

			if(cell2.getValue().toString().equals("NEXT_MATCH"))
			{
				graph.getModel().setStyle(cell2,"LABEL;strokeColor=#990099;spacingBottom=-15");  

			}else if(cell2.getValue().toString().equals("CORRESPONDS_TO"))
			{
				graph.getModel().setStyle(cell2,"LABEL;strokeColor=#6600FF"); 
			}

		}

		graph.getModel().endUpdate();
		graph.setDisconnectOnMove(false);
		graph.setEdgeLabelsMovable(false);
		graph.setCellsDisconnectable(false);
		graph.setCellsEditable(false);
		graph.setCellsResizable(false);
		graph.setCellsBendable(false);
		graph.setConnectableEdges(false);
		graph.setAllowDanglingEdges(false);
		//graph.setCellsMovable(false);
		graph.setCellsMovable(true);


	}



	private static void sortMatches()
	{

		Collections.sort(arrayList, new Comparator<StoreData>() {
			@Override public int compare(StoreData sd1, StoreData sd2) {
				return sd1.qStart - sd2.qStart;
			}
		});
	}

	public static void readFile(String fileName)
	{
		String line;
		String[] temp = null; 
		int tempStart;
		QueryNode invQ;
		SubjectNode invS;

		try {

			Scanner scanner = new Scanner(new FileInputStream(fileName));

			while (scanner.hasNextLine()) 
			{
				line = scanner.nextLine();
				temp = line.split("\t"); //split line at tabs and store in a temporary array
				//lines have been parsed in as Strings, convert to required type
				Double identity = Double.parseDouble(temp[2]);
				int alignmentLength = Integer.parseInt(temp[3]);
				int qStart = Integer.parseInt(temp[6]);
				int qEnd = Integer.parseInt(temp[7]);
				int sStart = Integer.parseInt(temp[8]);
				int sEnd = Integer.parseInt(temp[9]);

				/**Inversions*/
				if(qEnd<qStart)
				{
					tempStart = qStart;
					qStart = qEnd;
					qEnd = tempStart;

					invQ = new QueryNode(temp[0], qStart, qEnd, 0, 0);

					qinversionPos.add(invQ);

				}else if(sEnd<sStart)
				{
					tempStart = sStart;
					sStart = sEnd;
					sEnd = tempStart;

					invS = new SubjectNode(temp[1], sStart, sEnd, 0, 0);

					sinversionPos.add(invS);
				}

				//Create an object for each line and add to an ArrayList
				arrayList.add(new StoreData(temp[0], temp[1], identity, alignmentLength, qStart, qEnd, sStart, sEnd));

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		arraySize = arrayList.size();
	}



	//	private void readFile(String fileName)
	//	{
	//		String line;
	//		String[] temp = null;
	//		int tempStart;
	//		QueryNode invQ;
	//		SubjectNode invS;
	//	
	//		try {
	//	
	//			Scanner scanner = new Scanner(new FileInputStream(fileName));
	//	
	//			while (scanner.hasNextLine()) 
	//			{
	//				line = scanner.nextLine();
	//				/*Fields in every line are separated by a tab. Split line where tab spaces are present
	//				 *and store each field in a temporary array */
	//				temp = line.split("\t"); 
	//				/*lines are parsed in as Strings, and convert to the required type where necessary*/
	//				double identity = Double.parseDouble(temp[2]);
	//				int alignmentLength = Integer.parseInt(temp[3]);		
	//				int qStart = Integer.parseInt(temp[4]);
	//				int qEnd = Integer.parseInt(temp[5]);
	//				int sStart = Integer.parseInt(temp[6]);
	//				int sEnd = Integer.parseInt(temp[7]);
	//	
	//				/**Inversions*/
	//				if(qEnd<qStart)
	//				{
	//					tempStart = qStart;
	//					qStart = qEnd;
	//					qEnd = tempStart;
	//	
	//					invQ = new QueryNode(temp[0], qStart, qEnd, alignmentLength, identity);
	//	
	//					qinversionPos.add(invQ);
	//	
	//				}else if(sEnd<sStart)
	//				{
	//					tempStart = sStart;
	//					sStart = sEnd;
	//					sEnd = tempStart;
	//	
	//					invS = new SubjectNode(temp[1], sStart, sEnd, alignmentLength, identity);
	//	
	//					sinversionPos.add(invS);
	//				}
	//	
	//				//Create an object for each line and add to an ArrayList
	//				arrayList.add(new StoreData(temp[0], temp[1], identity, alignmentLength, qStart, qEnd, sStart, sEnd));
	//	
	//			}
	//		} catch (FileNotFoundException e) {
	//			e.printStackTrace();
	//		}
	//	
	//	}

	public static ArrayList<StoreData> getStoredData()
	{	
		return arrayList;
	}

	public static int checkForInversions(QueryNode qn, SubjectNode sn)
	{
		if(qinversionPos.contains(qn))
		{
			return 1; //inverted sequence in QueryNode
		}else if(sinversionPos.contains(sn))
		{
			return 2; //inverted sequence in SubjectNode
		}else
		{
			return 0; //no inverted sequence
		}
	}

	public static int checkForGap(QueryNode qn, QueryNode qn2, SubjectNode sn, SubjectNode sn2)
	{	
		//	int qNum = qn.getEnd()+1;
		//	int sNum = sn.getEnd()+1;

		/**Insertion where there is maximum 6 bases between the deleted position before being considered a variation.*/
		//		ArrayList<Integer> qList = new ArrayList<Integer>();
		//		qList.add(qNum); qList.add(qNum+2); qList.add(qNum+3); qList.add(qNum+4); qList.add(qNum+5); qList.add(qNum+6);
		//		
		//		ArrayList<Integer> sList = new ArrayList<Integer>();
		//		sList.add(sNum); sList.add(sNum+2); sList.add(sNum+3); sList.add(sNum+4); sList.add(sNum+5); sList.add(sNum+6);
		//		
		//		if((!qList.contains(qn2.getStart())) && (sList.contains(sn2.getStart())))  
		//		{			
		//			//GAP in QUERY
		//			return 1;
		//		}else if((qList.contains(qn2.getStart())) && (!sList.contains(sn2.getStart())))
		//		{		
		//			//GAP in SUBJECT
		//			return 2;
		//		}else if((!qList.contains(qn2.getStart())) && (!sList.contains(sn2.getStart())))
		//		{
		//			//GAP IN BOTH QUERY AND SUBJECT
		//			return 3;
		//		}else
		//		{
		//			//NO GAP
		//			return 4;
		//		} 
		/**Insertion with no boundary*/
		int qn2s=qn2.getStart();
		int qne=qn.getEnd()+1;
		int sne=sn.getEnd()+1;
		int sn2s=sn2.getStart();
		if((qn2s!=qne) && (sn2s==sne))
		{			
			//GAP in QUERY
			return 1;
		}else if((qn2s==(qne+1)) && (sn2s!=sne))
		{		
			//GAP in SUBJECT
			return 2;
		}else if((qn2s!=(qne+1)) && (sn2s!=sne))
		{
			//GAP IN BOTH QUERY AND SUBJECT
			return 3;
		}else
		{
			//NO GAP
			return 4;
		} 
	}

	public void GUI()
	{


		JFrame frame = new JFrame("Rearrangement Types");
		final JPanel panel = new JPanel(new GridBagLayout()); 

		//		PopulateGraph pg = new PopulateGraph();
		//		pg.dataToGraph();
		//		//pg.graph();
		//		pg.graphVisualisation();

		// JGraphXAdapter<Node, SequenceEdge> graph = pg.getGraph();
		// ListenableDirectedGraph<Node, SequenceEdge> g = pg.getListenableGraph();

		final mxGraphComponent graphComponent = new mxGraphComponent(graph);


		GridBagConstraints c = new GridBagConstraints(); 
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.ipady = 400;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(graphComponent, c);
		panel.setPreferredSize(new Dimension(getScreenWorkingWidth()-20, getScreenWorkingHeight()-45)); 

		//panel.setBounds(0, 20, getScreenWorkingWidth(), 520);

		JLabel qID = new JLabel(" Query Sequence: " + arrayList.get(0).getQueryID() + " ");
		qID.setOpaque(true);
		qID.setBackground(new Color(153, 153, 255));
		qID.setFont(new Font("Ariel", Font.PLAIN, 14));
		c = new GridBagConstraints(); 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(31,20,2,80);
		c.gridx = 0;
		c.gridy = 1;
		panel.add(qID, c);

		JLabel sID = new JLabel("  Subject Sequence: " + arrayList.get(0).getSubjectID() + "  ");
		sID.setOpaque(true);
		sID.setBackground(new Color(102, 204, 0));
		sID.setFont(new Font("Ariel", Font.PLAIN, 14)); 
		c = new GridBagConstraints(); 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2,20,0,80);
		c.gridx = 0;
		c.gridy = 2;
		panel.add(sID, c);

		JButton refineSearch = new JButton("Refine Search");
		final UserInputListener ui = new UserInputListener(panel);
		refineSearch.addActionListener(ui); 
		c = new GridBagConstraints(); 
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5,148,5,0);
		c.gridx = 0;
		c.gridy = 3;
		panel.add(refineSearch, c);	

		JButton clear = new JButton(" Reset Range ");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ui.setPosMin(0);
				ui.setPosMax(0);
				ui.removeText();
			}
		}); 
		c = new GridBagConstraints(); 
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(5,0,5,215);
		c.gridx = 0;
		c.gridy = 3;
		panel.add(clear, c);	

		final JTextArea textBox = new JTextArea(21, 77); 
		textBox.setEditable(false); 
		textBox.setMinimumSize(textBox.getPreferredSize());
		textBox.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				textBox.setText("");
			}
		});


		/*INSERTION*/
		final IsomorphismAlgorithm subgraph1;
		subgraph1 = new IsomorphismAlgorithm(g, insertionInQueryMotif(), "QInsertion");
		final IsomorphismAlgorithm subgraph2;
		subgraph2 = new IsomorphismAlgorithm(g, insertionInSubjectMotif(), "SInsertion");	
		JButton insertion = new JButton("Insertions"); 
		InsertionListener ins = new InsertionListener(subgraph1, subgraph2, textBox, ui);
		insertion.addActionListener(ins);

		/*DELETION*/
		final IsomorphismAlgorithm subgraphd1;
		subgraphd1 = new IsomorphismAlgorithm(g, insertionInQueryMotif(), "QInsertion");
		final IsomorphismAlgorithm subgraphd2;
		subgraphd2 = new IsomorphismAlgorithm(g, insertionInSubjectMotif(), "SInsertion");
		JButton deletion = new JButton(" Deletions "); 
		DeletionListener del = new DeletionListener(subgraphd1, subgraphd2, textBox, ui);
		deletion.addActionListener(del);


		/*VARIATION*/
		IsomorphismAlgorithm subgraph3;
		subgraph3 = new IsomorphismAlgorithm(g, variationMotif(), "Variation");
		JButton variation = new JButton("Variations"); 
		StartSearchListener var = new StartSearchListener(subgraph3, textBox, ui);
		variation.addActionListener(var);


		/*INVERSION*/
		IsomorphismAlgorithm subgraph4;
		subgraph4 = new IsomorphismAlgorithm(g, inversionMotif(),"Inversion");
		JButton inversion = new JButton(" Inversion "); 
		StartSearchListener inv = new StartSearchListener(subgraph4, textBox, ui);
		inversion.addActionListener(inv);


		/*DUPLICATIONS*/
		IsomorphismAlgorithm subgraph5;
		subgraph5 = new IsomorphismAlgorithm(g, duplicationInSubjectMotif(),"SDuplication");
		IsomorphismAlgorithm subgraph6;
		subgraph6 = new IsomorphismAlgorithm(g, duplicationInQueryMotif(),"QDuplication");
		JButton duplication = new JButton("Duplications"); 
		InsertionListener dup = new InsertionListener(subgraph5, subgraph6, textBox, ui);
		duplication.addActionListener(dup);



		JLabel tblabel = new JLabel("         Search for:          ");

		final JToolBar toolbar = new JToolBar();
		toolbar.add(tblabel);
		Dimension d = new Dimension(15, 0);
		toolbar.add(variation);
		toolbar.addSeparator(d);
		toolbar.add(insertion);
		toolbar.addSeparator(d);
		toolbar.add(deletion);
		toolbar.addSeparator(d);
		toolbar.add(inversion);
		toolbar.addSeparator(d);
		toolbar.add(duplication);
		toolbar.addSeparator(d);

		c = new GridBagConstraints();
		c.insets = new Insets(20,20,20,20);
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 4;
		panel.add(toolbar, c);

		final JTextArea propBox = new JTextArea(5, 19);
		propBox.setEditable(false);
		propBox.setText("Click on a node");
		propBox.setBackground(new Color(255, 238, 177));
		propBox.setMinimumSize(propBox.getPreferredSize());
		Border loweredbevel = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border title2 = BorderFactory.createTitledBorder(loweredbevel, " Node Properties: ");		
		propBox.setBorder(title2);
		GridBagConstraints gc = new GridBagConstraints(); 
		gc.insets = new Insets(16,19,16,16);
		gc.ipadx = 10;
		gc.ipady = 1;
		gc.anchor = GridBagConstraints.EAST;
		gc.gridheight = 2;
		gc.gridx = 0;
		gc.gridy = 1;
		panel.add(propBox, gc);

		graph.getSelectionModel().addListener(mxEvent.CHANGE, new mxIEventListener(){
			public void invoke(Object sender, mxEventObject arg1) 
			{				
				if (sender instanceof mxGraphSelectionModel) 
				{
					for (Object cell : ((mxGraphSelectionModel)sender).getCells()) 
					{
						int i = graph.getCellToVertexMap().get(cell).getAligmentLength();
						double d = graph.getCellToVertexMap().get(cell).getIdentity();
						String s =  "   Alignment Pos: " + graph.getLabel(cell) + "\n   Percent Identity: " + d +
								"%\n   Alignment Length: " + i; 

						System.out.print("\nNode: " + s);
						propBox.setText(s);
					}}
			}});





		//TextBox
		Border raised = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED); 
		Border title = BorderFactory.createTitledBorder(
				raised, " Query Results: ");
		textBox.setBorder(title);
		JScrollPane sp = new JScrollPane(textBox); 
		sp.setMinimumSize(textBox.getPreferredSize());
		c = new GridBagConstraints(); 
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridheight = 4;
		c.gridx = 1;
		c.gridy = 2;
		panel.add(sp, c);  //textBox

		//Display the window.  

		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setSize(getScreenWorkingWidth(), 520); 
		frame.setVisible(true);



	}

	public static int getScreenWorkingWidth() 
	{
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int getScreenWorkingHeight() 
	{
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}


}
