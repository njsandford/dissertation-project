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
        //ArrayList<BlastData> graphData = parseData.readBlastFile("GP5MD2TG114-Alignment.csv"); // Real Data
        ArrayList<BlastData> graphData = parseData.readBlastFile("oneOfEachMotif3.csv"); // Artificial Data
        System.out.println(graphData);

        GraphHelper graphHelper = new GraphHelper();
        graph = graphHelper.getGraphFromData(graphData);

        SearchAlgorithms searchAlgorithms = new SearchAlgorithms(graph);

        long startTime = System.currentTimeMillis();

        //System.out.println("MATCH:");
        //searchAlgorithms.printMappingSet(searchAlgorithms.findMotif(SubgraphMotif.MATCH));

        long insStart = System.currentTimeMillis();
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.INSERTION), SubgraphMotif.INSERTION);
        long insEnd = System.currentTimeMillis();

        long delStart = System.currentTimeMillis();
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.DELETION), SubgraphMotif.DELETION);
        long delEnd = System.currentTimeMillis();

        long varStart = System.currentTimeMillis();
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.VARIATION), SubgraphMotif.VARIATION);
        long varEnd = System.currentTimeMillis();

        long DQNCStart = System.currentTimeMillis();
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.DUPLICATION_IN_QUERY), SubgraphMotif.DUPLICATION_IN_QUERY);
        long DQNCEnd = System.currentTimeMillis();

        long DSNCStart = System.currentTimeMillis();
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.DUPLICATION_IN_SUBJECT), SubgraphMotif.DUPLICATION_IN_SUBJECT);
        long DSNCEnd = System.currentTimeMillis();

        long DQCStart = System.currentTimeMillis();
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.CON_DUPLICATION_IN_QUERY), SubgraphMotif.CON_DUPLICATION_IN_QUERY);
        long DQCEnd = System.currentTimeMillis();

        long DSCStart = System.currentTimeMillis();
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.CON_DUPLICATION_IN_SUBJECT), SubgraphMotif.CON_DUPLICATION_IN_SUBJECT);
        long DSCEnd = System.currentTimeMillis();

        long invQStart = System.currentTimeMillis();
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.INVERSION_IN_QUERY), SubgraphMotif.INVERSION_IN_QUERY);
        long invQEnd = System.currentTimeMillis();

        long invSStart = System.currentTimeMillis();
        searchAlgorithms.printSearchResults(searchAlgorithms.findMotif(SubgraphMotif.INVERSION_IN_SUBJECT), SubgraphMotif.INVERSION_IN_SUBJECT);
        long invSEnd = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();

        long runTime = endTime - startTime;

        System.out.println("Insertion Run Time: " + (insEnd - insStart));
        System.out.println("Deletion Run Time: " + (delEnd - delStart));
        System.out.println("Variation Run Time: " + (varEnd - varStart));
        System.out.println("DQNC Run Time: " + (DQNCEnd - DQNCStart));
        System.out.println("DSNC Run Time: " + (DSNCEnd - DSNCStart));
        System.out.println("DQC Run Time: " + (DQCEnd - DQCStart));
        System.out.println("DSC Run Time: " + (DSCEnd - DSCStart));
        System.out.println("Inversion Query Run Time: " + (invQEnd - invQStart));
        System.out.println("Inversion Subject Run Time: " + (invSEnd - invSStart));

        System.out.println("Total Run Time: " + runTime);

        GUI gui = new GUI(graph, graphData);

        /*
        // Display each rearramgement motif in separage GUI windows.
        SubGraphs subGraphs = new SubGraphs();
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
}
