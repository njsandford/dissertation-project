package uk.ac.ncl.v1.njsandford.test;

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

    private static ListenableDirectedGraph<Node, SequenceEdge> graph;

    public static void main(String[] args) {

        ParseData parseData = new ParseData();
        ArrayList<BlastData> graphData = parseData.readBlastFile("oneOfEachMotif3.csv");//"GP5MD2TG114-Alignment.csv");////"NC_009641_Newman.fna-NC_002952_MRSA252.csv"); //"oneOfEachMotif2.csv");//"oneOfEachMotif1.csv"); //"CV86YM68113-Alignment-HitTable.csv"); //"NC_009641_Newman.fna-NC_002952_MRSA252.csv");//"test-sequence.txt");//"testBlast.txt");//"NC_009641_Newman.fna-NC_002952_MRSA252.fna.blast");
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

        long startTime = System.currentTimeMillis();

        //System.out.println("MATCH:");
        //searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.MATCH));

        long insStart = System.currentTimeMillis();
        //System.out.println("INSERTION:");
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.INSERTION), SubgraphMotif.INSERTION);
        long insEnd = System.currentTimeMillis();

        long delStart = System.currentTimeMillis();
        //System.out.println("DELETION:");
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.DELETION), SubgraphMotif.DELETION);
        long delEnd = System.currentTimeMillis();

        long varStart = System.currentTimeMillis();
        //System.out.println("VARIATION:");
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.VARIATION), SubgraphMotif.VARIATION);
        long varEnd = System.currentTimeMillis();

        long DQNCStart = System.currentTimeMillis();
        //System.out.println("DUPLICATION_IN_QUERY:");
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.DUPLICATION_IN_QUERY), SubgraphMotif.DUPLICATION_IN_QUERY);
        long DQNCEnd = System.currentTimeMillis();

        long DSNCStart = System.currentTimeMillis();
        //System.out.println("DUPLICATION_IN_SUBJECT:");
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.DUPLICATION_IN_SUBJECT), SubgraphMotif.DUPLICATION_IN_SUBJECT);
        long DSNCEnd = System.currentTimeMillis();

        long DQCStart = System.currentTimeMillis();
        //System.out.println("CON_DUPLICATION_IN_QUERY:");
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.CON_DUPLICATION_IN_QUERY), SubgraphMotif.CON_DUPLICATION_IN_QUERY);
        long DQCEnd = System.currentTimeMillis();

        long DSCStart = System.currentTimeMillis();
        //System.out.println("CON_DUPLICATION_IN_SUBJECT:");
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.CON_DUPLICATION_IN_SUBJECT), SubgraphMotif.CON_DUPLICATION_IN_SUBJECT);
        long DSCEnd = System.currentTimeMillis();

        long invQStart = System.currentTimeMillis();
        //System.out.println("INVERSION_IN_QUERY:");
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.INVERSION_IN_QUERY), SubgraphMotif.INVERSION_IN_QUERY);
        long invQEnd = System.currentTimeMillis();

        long invSStart = System.currentTimeMillis();
        //System.out.println("INVERSION_IN_SUBJECT:");
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.INVERSION_IN_SUBJECT), SubgraphMotif.INVERSION_IN_SUBJECT);
        long invSEnd = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();

        long runTime = endTime - startTime;

        System.out.println(/*"Insertion Run Time: " + */(insEnd - insStart));
        System.out.println(/*"Deletion Run Time: " + */(delEnd - delStart));
        System.out.println(/*"Variation Run Time: " + */(varEnd - varStart));
        System.out.println(/*"DQNC Run Time: " + */(DQNCEnd - DQNCStart));
        System.out.println(/*"DSNC Run Time: " + */(DSNCEnd - DSNCStart));
        System.out.println(/*"DQC Run Time: " + */(DQCEnd - DQCStart));
        System.out.println(/*"DSC Run Time: " + */(DSCEnd - DSCStart));
        System.out.println(/*"Inversion Query Run Time: " + */(invQEnd - invQStart));
        System.out.println(/*"Inversion Subject Run Time: " + */(invSEnd - invSStart));

        System.out.println(/*"Total Run Time: " +*/ runTime);

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

/*
        // Display each rearramgement motif in separage GUI windows.
        GUI match = new GUI(subGraphs.match(), graphData);
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
    }



    public void testParseData(String fileName) {
        ParseData parseData = new ParseData();
        ArrayList<BlastData> blastData = parseData.readBlastFile(fileName);
        System.out.println(blastData);
    }

}
