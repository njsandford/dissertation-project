package uk.ac.ncl.njsandford;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraphSelectionModel;
import com.mxgraph.view.mxStylesheet;
import org.jgrapht.ListenableGraph;
import org.jgrapht.demo.*;
import org.jgrapht.graph.ListenableDirectedGraph;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Natalie on 26/02/2017.
 */
public class TestGraph {

    private static PopulateGraph populateGraph;
    private static ListenableDirectedGraph<Node, SequenceEdge> graph;

    public static void main(String[] args) {
        //populateGraph = new PopulateGraph();
        //graph = populateGraph.getGraph();
        //GUI gui = new GUI(graph);
        //gui.openGUI();

        TestGraph testGraph = new TestGraph();
        testGraph.testParseData("testBlast.txt");

        /*SearchAlgorithms searchAlgorithms = new SearchAlgorithms();
        SubGraphs subGraphs = new SubGraphs();

        ArrayList<ListenableDirectedGraph<Node, SequenceEdge>> foundMstches = searchAlgorithms.subgraphSearch(graph, subGraphs.match());
        System.out.println("Found Matches: " + foundMstches);
        */

        //populateGraph.run();
        //initialiseGraph();
        //testGraph.graph = testGraph.populateGraph.getGraph();
        //testGraph.printGraph();
        //testGraph.populateGraph.graphVisualisation();
        //GUI();
    }

    public void testAlgorithm() {
        SearchAlgorithms searchAlgorithms = new SearchAlgorithms();

        ListenableDirectedGraph<Node, SequenceEdge> searchGraph = new ListenableDirectedGraph<>(SequenceEdge.class);
        //searchGraph.addVertex(new SubjectNode())

    }

    public void testParseData(String fileName) {
        ParseData parseData = new ParseData();
        ArrayList<StoreData> storeData = parseData.readBlastFile(fileName);
        System.out.println(storeData);
    }

}
