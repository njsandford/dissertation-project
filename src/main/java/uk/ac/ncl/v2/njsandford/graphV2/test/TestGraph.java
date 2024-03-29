package uk.ac.ncl.v2.njsandford.graphV2.test;

import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.graph.Node;
import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;
import uk.ac.ncl.v1.njsandford.isomorphism.EdgeComparator;
import uk.ac.ncl.v1.njsandford.isomorphism.NodeComparator;
import uk.ac.ncl.v1.njsandford.isomorphism.SearchAlgorithms;
import uk.ac.ncl.v1.njsandford.isomorphism.SubGraphs;
import uk.ac.ncl.v1.njsandford.utilities.BlastData;
//import uk.ac.ncl.v1.njsandford.utilities.GraphHelper;
import uk.ac.ncl.v1.njsandford.utilities.ParseData;
//import uk.ac.ncl.v1.njsandford.visualisation.GUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
//import uk.ac.ncl.v1.njsandford.isomorphism.*;
import uk.ac.ncl.v1.njsandford.utilities.BlastData;
//import uk.ac.ncl.v1.njsandford.utilities.GraphHelper;
import uk.ac.ncl.v1.njsandford.utilities.ParseData;
//import uk.ac.ncl.v1.njsandford.visualisation.GUI;
import uk.ac.ncl.v2.njsandford.graphV2.isomorphism.SubgraphMatching;
import uk.ac.ncl.v2.njsandford.graphV2.visualisation.GUI;
import uk.ac.ncl.v1.njsandford.graph.Node;
import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;
import uk.ac.ncl.v2.njsandford.graphV2.graphV2.BreakPoint;
import uk.ac.ncl.v2.njsandford.graphV2.utilities.GraphHelper;

import java.util.*;

/**
 * Created by Natalie on 26/02/2017.
 */
public class TestGraph {

    private static ListenableDirectedGraph<BreakPoint, DefaultEdge> graph;
    //private static ListenableDirectedGraph<Node, SequenceEdge> graph;

    public static void main(String[] args) {
        //populateGraph = new PopulateGraph();
        //graph = populateGraph.getGraph();

        uk.ac.ncl.v1.njsandford.test.TestGraph testGraph = new uk.ac.ncl.v1.njsandford.test.TestGraph();
        //testGraph.testParseData("testBlast.txt");

        ParseData parseData = new ParseData();
        ArrayList<BlastData> graphData = parseData.readBlastFile("CV86YM68113-Alignment-HitTable.csv"); //"EZSNZXSJ11N-Alignment-HitTable.csv");//"NC_009641_Newman.fna-NC_002952_MRSA252.csv"); //"CV86YM68113-Alignment-HitTable.csv");//"test-sequence.txt");//"testBlast.txt");//"NC_009641_Newman.fna-NC_002952_MRSA252.fna.blast");
        System.out.println(graphData);

        GraphHelper graphHelper = new GraphHelper();
        graph = graphHelper.getGraphFromData(graphData);

        SubgraphMatching subgraphMatching = new SubgraphMatching(graph);
        System.out.println("MATCH:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.MATCH));
        System.out.println("DELETION:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.DELETION));
        System.out.println("INSERTION:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.INSERTION));
        System.out.println("VARIATION:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.VARIATION));
        System.out.println("DUPLICATION_IN_QUERY:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.DUPLICATION_IN_QUERY));
        System.out.println("DUPLICATION_IN_SUBJECT:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.DUPLICATION_IN_SUBJECT));
        System.out.println("DUPLICATION_IN_QUERY_CON:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.DUPLICATION_IN_QUERY_CON));
        System.out.println("DUPLICATION_IN_SUBJECT_CON:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.DUPLICATION_IN_SUBJECT_CON));
        System.out.println("INVERSION_IN_QUERY:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.INVERSION_IN_QUERY));
        System.out.println("INVERSION_IN_SUBJECT:");
        subgraphMatching.printMappingList(subgraphMatching.findMotif(graph, SubgraphMatching.Motif.INVERSION_IN_SUBJECT));
/**//*
        /*SubGraphs subGraphs = new SubGraphs();
        testGraph.testAlgorithm(graph, subGraphs.match(), "Match");
        testGraph.testAlgorithm(graph, subGraphs.variation(), "Variation");
        testGraph.testAlgorithm(graph, subGraphs.deletion(), "Deletion");
        testGraph.testAlgorithm(graph, subGraphs.insertion(), "Insertion");
        testGraph.testAlgorithm(graph, subGraphs.duplicationInQuery(), "Duplication in Query");
        testGraph.testAlgorithm(graph, subGraphs.duplicationInSearch(), "Duplication in Subject");
        testGraph.testAlgorithm(graph, subGraphs.inversion(), "Inversion");
        testGraph.testAlgorithm(graph, subGraphs.dummyMotif(), "Dummy Motif");


        ArrayList<ListenableDirectedGraph<Node, SequenceEdge>> foundMatches = searchAlgorithms.subgraphSearch(graph, subGraphs.match());
        System.out.println("\nFound Matches: " + foundMatches);
        */

//        System.out.println("Graph:\n" + graph.toString());

        GUI gui = new GUI(graph);//, graphData);
/*        GUI match = new GUI(subgraphMatching.match());
        GUI deletion = new GUI(subgraphMatching.deletion());
        GUI insertion = new GUI(subgraphMatching.insertion());
        GUI variation = new GUI(subgraphMatching.variation());
        GUI duplicationInQueryNonConsecutive = new GUI(subgraphMatching.duplicationInQueryNonConsecutive());
        GUI duplicationInSubjectNonConsecutive = new GUI(subgraphMatching.duplicationInSubjectNonConsecutive());
        GUI duplicationInQueryConsecutive = new GUI(subgraphMatching.duplicationInQueryConsecutive());
        GUI duplicationInSubjectConsecutive = new GUI(subgraphMatching.duplicationInSubjectConsecutive());
        GUI inversionInQuery = new GUI(subgraphMatching.inversionInQuery());
        GUI inversionInSubject = new GUI(subgraphMatching.inversionInSubject());
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
