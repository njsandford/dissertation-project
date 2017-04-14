package uk.ac.ncl.v1.njsandford.isomorphism;

import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.graph.Node;
import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;

import java.util.*;

/**
 * Created by Natalie on 02/03/2017.
 */
public class SearchAlgorithms //extends VF2SubgraphIsomorphismInspector {
{
    private VF2SubgraphIsomorphismInspector<Node, SequenceEdge> isomorphismInspector;
    private ListenableDirectedGraph<Node, SequenceEdge> graph;

    private NodeComparator nodeComparator;
    private EdgeComparator edgeComparator;

    private ListenableDirectedGraph<Node, SequenceEdge> match;
    private ListenableDirectedGraph<Node, SequenceEdge> variation;
    private ListenableDirectedGraph<Node, SequenceEdge> deletion;
    private ListenableDirectedGraph<Node, SequenceEdge> insertion;
    private ListenableDirectedGraph<Node, SequenceEdge> duplicationInQuery;
    private ListenableDirectedGraph<Node, SequenceEdge> duplicationInSubject;
    private ListenableDirectedGraph<Node, SequenceEdge> inversion;

    public SearchAlgorithms(ListenableDirectedGraph<Node, SequenceEdge> graph) {
        this.graph = graph;
        this.nodeComparator = new NodeComparator();
        this.edgeComparator = new EdgeComparator();
        SubGraphs subGraphs = new SubGraphs();
        match = subGraphs.match();
        variation = subGraphs.variation();
        deletion = subGraphs.deletion();
        insertion = subGraphs.insertion();
        duplicationInQuery = subGraphs.duplicationInQuery();
        duplicationInSubject = subGraphs.duplicationInSearch();
        inversion = subGraphs.inversion();
    }

    public List<ListenableDirectedGraph<Node, SequenceEdge>> findMotif(ListenableDirectedGraph<Node, SequenceEdge> graph, SubgraphMotif motif) {

        switch (motif) {
            case MATCH:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, match, nodeComparator, edgeComparator);
                break;
            case VARIATION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, variation, nodeComparator, edgeComparator);
                break;
            case DELETION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, deletion, nodeComparator, edgeComparator);
                break;
            case INSERTION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, insertion, nodeComparator, edgeComparator);
                break;
            case DUPLICATION_IN_QUERY:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, duplicationInQuery, nodeComparator, edgeComparator);
                break;
            case DUPLICATION_IN_SUBJECT:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, duplicationInSubject, nodeComparator, edgeComparator);
                break;
            case INVERSION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, inversion, nodeComparator, edgeComparator);
                break;
        }

        Iterator<GraphMapping<Node, SequenceEdge>> mappingIterator = isomorphismInspector.getMappings();

        return mappingToList(mappingIterator);
    }

    public void printMappingList(List<ListenableDirectedGraph<Node, SequenceEdge>> mappingList) {
        int count = 1;
        for (ListenableDirectedGraph<Node, SequenceEdge> subgraph : mappingList) {
            System.out.println(count++ + ": " + subgraph);
        }
    }

    public List<ListenableDirectedGraph<Node, SequenceEdge>> mappingToList(Iterator<GraphMapping<Node, SequenceEdge>> mappingIterator) {
        List<ListenableDirectedGraph<Node, SequenceEdge>> mappingList = new ArrayList<>();

        if (isomorphismInspector.isomorphismExists()) {
            for (Iterator<GraphMapping<Node, SequenceEdge>> iter = mappingIterator; iter.hasNext();) {
                ListenableDirectedGraph<Node, SequenceEdge> subgraphMatch = getMappingSubgraph(iter.next());
                mappingList.add(subgraphMatch);
            }
        }

        return mappingList;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> getMappingSubgraph(GraphMapping<Node, SequenceEdge> mapping) {
        ListenableDirectedGraph<Node, SequenceEdge> subgraph = new ListenableDirectedGraph<>(SequenceEdge.class);

        Set<Node> vertexSet = graph.vertexSet();

        // For each node in the set of vertices, if a mapping exists for that node, put the node into the subgraph
        for (Node node : vertexSet) {
            Node u = mapping.getVertexCorrespondence(node, true);

            if (u != null) {
                subgraph.addVertex(node);
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
    }
    /*
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
    */
}
