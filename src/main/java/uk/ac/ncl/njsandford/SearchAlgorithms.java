package uk.ac.ncl.njsandford;

import org.jgrapht.ListenableGraph;
import org.jgrapht.demo.Node;
import org.jgrapht.demo.QueryNode;
import org.jgrapht.demo.SequenceEdge;
import org.jgrapht.demo.SubjectNode;
import org.jgrapht.experimental.isomorphism.*;
import org.jgrapht.graph.ListenableDirectedGraph;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Natalie on 02/03/2017.
 */
public class SearchAlgorithms {

    public ArrayList<ListenableDirectedGraph<Node, SequenceEdge>> subgraphSearch(ListenableDirectedGraph<Node, SequenceEdge> searchGraph, ListenableGraph<Node, SequenceEdge> subGraph) {
        ArrayList<ListenableDirectedGraph<Node, SequenceEdge>> foundMatches = new ArrayList<>();

        Set<Node> nodes = searchGraph.vertexSet();
        Set<SequenceEdge> edges;

        for (Node node: nodes) {
            edges = searchGraph.edgesOf(node);

            ListenableDirectedGraph<Node, SequenceEdge> match;

            if (node.getSequenceEdge().size() >= 1) {
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
