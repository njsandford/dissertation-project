package uk.ac.ncl.v1.njsandford.test;

import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.isomorphism.*;
import uk.ac.ncl.v1.njsandford.utilities.BlastData;
import uk.ac.ncl.v1.njsandford.utilities.GraphHelper;
import uk.ac.ncl.v1.njsandford.utilities.ParseData;
import uk.ac.ncl.v1.njsandford.visualisation.GUI;
import uk.ac.ncl.v1.njsandford.graph.Node;
import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;

import java.util.*;

/**
 * Created by Natalie on 26/02/2017.
 */
public class TestGraph {

    //private static ListenableDirectedGraph<BreakPoint, DefaultEdge> graph;
    private static ListenableDirectedGraph<Node, SequenceEdge> graph;

    public static void main(String[] args) {
        //populateGraph = new PopulateGraph();
        //graph = populateGraph.getGraph();

        TestGraph testGraph = new TestGraph();
        //testGraph.testParseData("testBlast.txt");

        ParseData parseData = new ParseData();
        ArrayList<BlastData> graphData = parseData.readBlastFile("oneOfEachMotif2.csv");//"oneOfEachMotif1.csv"); //"CV86YM68113-Alignment-HitTable.csv"); //"NC_009641_Newman.fna-NC_002952_MRSA252.csv");//"test-sequence.txt");//"testBlast.txt");//"NC_009641_Newman.fna-NC_002952_MRSA252.fna.blast");
        System.out.println(graphData);

        GraphHelper graphHelper = new GraphHelper();
        graph = graphHelper.getGraphFromData(graphData);


        SearchAlgorithms searchAlgorithms = new SearchAlgorithms(graph);
/*        System.out.println("MATCH:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.MATCH));
        System.out.println("DELETION:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.DELETION));
        System.out.println("INSERTION:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.INSERTION));
        System.out.println("VARIATION:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.VARIATION));
        System.out.println("DUPLICATION_IN_QUERY:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.DUPLICATION_IN_QUERY));
        System.out.println("DUPLICATION_IN_SUBJECT:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.DUPLICATION_IN_SUBJECT));
        System.out.println("CON_DUPLICATION_IN_QUERY:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.CON_DUPLICATION_IN_QUERY));
        System.out.println("CON_DUPLICATION_IN_SUBJECT:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.CON_DUPLICATION_IN_SUBJECT));
        System.out.println("INVERSION_IN_QUERY:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.INVERSION_IN_QUERY));
        System.out.println("INVERSION_IN_SUBJECT:");
        searchAlgorithms.printMappingList(searchAlgorithms.findMotif(SubgraphMotif.INVERSION_IN_SUBJECT));
*/

        System.out.println("MATCH:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.MATCH));
        System.out.println("DELETION:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.DELETION));
        System.out.println("INSERTION:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.INSERTION));
        System.out.println("VARIATION:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.VARIATION));
        System.out.println("DUPLICATION_IN_QUERY:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.DUPLICATION_IN_QUERY));
        System.out.println("DUPLICATION_IN_SUBJECT:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.DUPLICATION_IN_SUBJECT));
        System.out.println("CON_DUPLICATION_IN_QUERY:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.CON_DUPLICATION_IN_QUERY));
        System.out.println("CON_DUPLICATION_IN_SUBJECT:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.CON_DUPLICATION_IN_SUBJECT));
        System.out.println("INVERSION_IN_QUERY:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.INVERSION_IN_QUERY));
        System.out.println("INVERSION_IN_SUBJECT:");
        searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.INVERSION_IN_SUBJECT));
/**//*
        /*SubGraphs subGraphs = new SubGraphs();
        testGraph.testAlgorithm(subGraphs.match(), "Match");
        testGraph.testAlgorithm(subGraphs.variation(), "Variation");
        testGraph.testAlgorithm(subGraphs.deletion(), "Deletion");
        testGraph.testAlgorithm(subGraphs.insertion(), "Insertion");
        testGraph.testAlgorithm(subGraphs.duplicationInQuery(), "Duplication in Query");
        testGraph.testAlgorithm(subGraphs.duplicationInSearch(), "Duplication in Subject");
        testGraph.testAlgorithm(subGraphs.inversion(), "Inversion");
        testGraph.testAlgorithm(subGraphs.dummyMotif(), "Dummy Motif");


        ArrayList<ListenableDirectedGraph<Node, SequenceEdge>> foundMatches = searchAlgorithms.subgraphSearch(graph, subGraphs.match());
        System.out.println("\nFound Matches: " + foundMatches);
        */

        //System.out.println("Graph:\n" + graph.toString());

        SubGraphs subGraphs = new SubGraphs();

        GUI gui = new GUI(graph, graphData);
/*        GUI match = new GUI(subGraphs.match(), graphData);
        GUI deletion = new GUI(subGraphs.deletion(), graphData);
        GUI insertion = new GUI(subGraphs.insertion(), graphData);
        GUI variation = new GUI(subGraphs.variation(), graphData);
        GUI duplicationInQueryNonConsecutive = new GUI(subGraphs.duplicationInQuery(), graphData);
        GUI duplicationInSubjectNonConsecutive = new GUI(subGraphs.duplicationInSearch(), graphData);
        GUI conDuplicationInQueryNonConsecutive = new GUI(subGraphs.consecutiveDuplicationInQuery(), graphData);
        GUI conDuplicationInSubjectNonConsecutive = new GUI(subGraphs.consecutiveDuplicationInSearch(), graphData);
        GUI inversionInQuery = new GUI(subGraphs.inversionInQuery(), graphData);
        GUI inversionInSubject = new GUI(subGraphs.inversionInSubject(), graphData);
        //gui.openGUI();
*/

        //populateGraph.run();
        //initialiseGraph();
        //testGraph.graph = testGraph.populateGraph.getGraph();
        //testGraph.printGraph();
        //testGraph.populateGraph.graphVisualisation();
        //GUI();
    }

    public void testAlgorithm(ListenableDirectedGraph<Node, SequenceEdge> graph, ListenableDirectedGraph<Node, SequenceEdge> subgraph, String motif) {
        //SearchAlgorithms searchAlgorithms = new SearchAlgorithms(graph, subgraph);
        NodeComparator nodeComparator = new NodeComparator();
        EdgeComparator edgeComparator = new EdgeComparator();
        VF2SubgraphIsomorphismInspector<Node, SequenceEdge> vf2SubgraphIsomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, subgraph, nodeComparator, edgeComparator);

        boolean isomorphism = vf2SubgraphIsomorphismInspector.isomorphismExists();

        SubGraphs subGraphs = new SubGraphs();
        //ListenableDirectedGraph<Node, SequenceEdge> subgraph = subGraphs.inversion();
        //subgraph.

        System.out.println(motif + ": " + isomorphism);

        SearchAlgorithms searchAlgorithms = new SearchAlgorithms(graph);

        if (isomorphism) {
            int count = 1;

            Set<Node> nodeSet = graph.vertexSet();
            for (Node node : nodeSet) {

            }

            for (Iterator<GraphMapping<Node, SequenceEdge>> iter = vf2SubgraphIsomorphismInspector.getMappings(); iter.hasNext();) {
                ListenableDirectedGraph<Node, SequenceEdge> subgraphMatch = searchAlgorithms.getMappingSubgraph(iter.next());
                System.out.println(count + ": " + subgraphMatch.toString());
                //System.out.println(printMapping(iter.next()));
                //System.out.println(count++ + ": " + iter.next().toString());
            }
        }


    }
/*
    public String printMapping(GraphMapping<Node, SequenceEdge> mapping) {
        String str = "[";
        Set<Node> vertexSet = graph.vertexSet();
        Map<String, Node> vertexMap = new TreeMap<>();

        for (Node node : vertexSet) {
            vertexMap.put(node.toString(), node);
        }

        int i = 0;
        for (Map.Entry<String, Node> entry : vertexMap.entrySet()) {
            Node u = mapping.getVertexCorrespondence(entry.getValue(), true);
            str += ((i++ == 0) ? "" : " ") + entry.getKey() + "=" + ((u == null) ? "~~" : u);
        }

        return str + "]";
    }
*/
    /*
    public ListenableDirectedGraph<Node, SequenceEdge> getMappingSubgraph(GraphMapping<Node, SequenceEdge> mapping) {
        ListenableDirectedGraph<Node, SequenceEdge> subgraph = new ListenableDirectedGraph<>(SequenceEdge.class);

        String str = "[";
        Set<Node> vertexSet = graph.vertexSet();
        Map<String, Node> vertexMap = new TreeMap<>();

        for (Node node : vertexSet) {
            vertexMap.put(node.toString(), node);
        }

        int i = 0;
        for (Map.Entry<String, Node> entry : vertexMap.entrySet()) {
            Node u = mapping.getVertexCorrespondence(entry.getValue(), true);
            str += ((i++ == 0) ? "" : " ") + entry.getKey() + "=" + ((u == null) ? "~~" : u);

            if (u != null) {
                subgraph.addVertex(entry.getValue());
            }
        }

        // Add edges, if they exist.
        for (Node node : subgraph.vertexSet()) {
            for (Node node2 : subgraph.vertexSet()) {
                if (graph.containsEdge(node, node2)) {
                    subgraph.addEdge(node, node2, graph.getEdge(node, node2));
                }
            }
        }

        return subgraph;
    }*/


    public void testParseData(String fileName) {
        ParseData parseData = new ParseData();
        ArrayList<BlastData> blastData = parseData.readBlastFile(fileName);
        System.out.println(blastData);
    }

}
