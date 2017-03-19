package uk.ac.ncl.njsandford.isomorphism;

import org.jgrapht.ListenableGraph;
import org.jgrapht.experimental.isomorphism.*;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.njsandford.graph.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Natalie on 02/03/2017.
 */
public class SearchAlgorithms {

    public ArrayList<ListenableDirectedGraph<Node, SequenceEdge>> subgraphSearch(ListenableDirectedGraph<Node, SequenceEdge> searchGraph, ListenableDirectedGraph<Node, SequenceEdge> subGraph) {
        ArrayList<ListenableDirectedGraph<Node, SequenceEdge>> foundMatches = new ArrayList<>();

        Set<Node> nodes = searchGraph.vertexSet();
        Set<SequenceEdge> edges;

        for (Node node: nodes) {
            edges = searchGraph.edgesOf(node);

            ListenableDirectedGraph<Node, SequenceEdge> match;

            if (node.getSequenceEdges().size() >= 1) {
                for (SequenceEdge edge: edges) {
                    Node matchNode = searchGraph.getEdgeSource(edge);
                    match = new ListenableDirectedGraph<>(SequenceEdge.class);

                    match.addVertex(node);
                    match.addVertex(matchNode);

                    match.addEdge(node, matchNode, edge);

                    foundMatches.add(match);
                }
            }
        }

        return foundMatches;
    }

}
