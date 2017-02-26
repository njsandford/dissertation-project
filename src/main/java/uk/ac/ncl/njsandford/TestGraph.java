package uk.ac.ncl.njsandford;

import org.jgrapht.demo.Node;
import org.jgrapht.demo.PopulateGraph;
import org.jgrapht.demo.SequenceEdge;
import org.jgrapht.ext.StringEdgeNameProvider;
import org.jgrapht.graph.ListenableDirectedGraph;

/**
 * Created by Natalie on 26/02/2017.
 */
public class TestGraph {


    public static void main(String[] args) {
        PopulateGraph populateGraph = new PopulateGraph();
        ListenableDirectedGraph<Node, SequenceEdge> graph = populateGraph.getGraph();
        System.out.println(graph);
        populateGraph.graphVisualisation();
        populateGraph.GUI();
    }
}
