package uk.ac.ncl.njsandford.test;

import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.njsandford.utilities.BlastData;
import uk.ac.ncl.njsandford.isomorphism.SearchAlgorithms;
import uk.ac.ncl.njsandford.isomorphism.SubGraphs;
import uk.ac.ncl.njsandford.utilities.GraphHelper;
import uk.ac.ncl.njsandford.utilities.ParseData;
import uk.ac.ncl.njsandford.visualisation.GUI;
import uk.ac.ncl.njsandford.graph.Node;
import uk.ac.ncl.njsandford.graph.SequenceEdge;

import java.util.ArrayList;

/**
 * Created by Natalie on 26/02/2017.
 */
public class TestGraph {

    private static ListenableDirectedGraph<Node, SequenceEdge> graph;

    public static void main(String[] args) {
        //populateGraph = new PopulateGraph();
        //graph = populateGraph.getGraph();

        //TestGraph testGraph = new TestGraph();
        //testGraph.testParseData("testBlast.txt");

        ParseData parseData = new ParseData();
        ArrayList<BlastData> graphData = parseData.readBlastFile("CV86YM68113-Alignment-HitTable.csv");//"test-sequence.txt");//"testBlast.txt");//"NC_009641_Newman.fna-NC_002952_MRSA252.fna.blast");
        System.out.println(graphData);

        GraphHelper graphHelper = new GraphHelper();
        graph = graphHelper.getGraphFromData(graphData);

        SearchAlgorithms searchAlgorithms = new SearchAlgorithms();
        SubGraphs subGraphs = new SubGraphs();


        ArrayList<ListenableDirectedGraph<Node, SequenceEdge>> foundMatches = searchAlgorithms.subgraphSearch(graph, subGraphs.match());
        System.out.println("\nFound Matches: " + foundMatches);

        GUI gui = new GUI(graph, graphData);
        //gui.openGUI();
/**/

        //populateGraph.run();
        //initialiseGraph();
        //testGraph.graph = testGraph.populateGraph.getGraph();
        //testGraph.printGraph();
        //testGraph.populateGraph.graphVisualisation();
        //GUI();
    }

    public void testAlgorithm() {
        SearchAlgorithms searchAlgorithms = new SearchAlgorithms();

        ListenableDirectedGraph<uk.ac.ncl.njsandford.graph.Node, SequenceEdge> searchGraph = new ListenableDirectedGraph<>(SequenceEdge.class);
        //searchGraph.addVertex(new SubjectNode())

    }

    public void testParseData(String fileName) {
        ParseData parseData = new ParseData();
        ArrayList<BlastData> blastData = parseData.readBlastFile(fileName);
        System.out.println(blastData);
    }

}
