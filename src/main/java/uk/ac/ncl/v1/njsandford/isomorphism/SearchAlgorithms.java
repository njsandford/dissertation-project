package uk.ac.ncl.v1.njsandford.isomorphism;

import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.graph.Node;
import uk.ac.ncl.v1.njsandford.graph.QueryNode;
import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;
import uk.ac.ncl.v1.njsandford.graph.SubjectNode;
import uk.ac.ncl.v2.njsandford.graphV2.isomorphism.SubgraphMatching;

import java.util.*;

/**
 * Created by Natalie on 02/03/2017.
 */
public class SearchAlgorithms
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
    private ListenableDirectedGraph<Node, SequenceEdge> conDuplicationInQuery;
    private ListenableDirectedGraph<Node, SequenceEdge> conDuplicationInSubject;
    private ListenableDirectedGraph<Node, SequenceEdge> inversionInQuery;
    private ListenableDirectedGraph<Node, SequenceEdge> inversionInSubject;

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
        conDuplicationInQuery = subGraphs.consecutiveDuplicationInQuery();
        conDuplicationInSubject = subGraphs.consecutiveDuplicationInSearch();
        inversionInQuery = subGraphs.inversionInQuery();
        inversionInSubject = subGraphs.inversionInSubject();
    }

    public Set<ListenableDirectedGraph<Node, SequenceEdge>> findMotif(SubgraphMotif motif) {

        switch (motif) {
            case MATCH:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, match, nodeComparator, edgeComparator, false);
                break;
            case VARIATION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, variation, nodeComparator, edgeComparator, false);
                break;
            case DELETION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, deletion, nodeComparator, edgeComparator, false);
                break;
            case INSERTION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, insertion, nodeComparator, edgeComparator, false);
                break;
            case DUPLICATION_IN_QUERY:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, duplicationInQuery, nodeComparator, edgeComparator, false);
                break;
            case DUPLICATION_IN_SUBJECT:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, duplicationInSubject, nodeComparator, edgeComparator, false);
                break;
            case CON_DUPLICATION_IN_QUERY:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, conDuplicationInQuery, nodeComparator, edgeComparator, false);
                break;
            case CON_DUPLICATION_IN_SUBJECT:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, conDuplicationInSubject, nodeComparator, edgeComparator, false);
                break;
            case INVERSION_IN_QUERY:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, inversionInQuery, nodeComparator, edgeComparator, false);
                break;
            case INVERSION_IN_SUBJECT:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, inversionInSubject, nodeComparator, edgeComparator, false);
                break;
        }
        Iterator<GraphMapping<Node, SequenceEdge>> mappingIterator = isomorphismInspector.getMappings();

        return mappingToSet(mappingIterator);
    }

    public void printMappingList(List<ListenableDirectedGraph<Node, SequenceEdge>> mappingList) {
        int count = 1;
        for (ListenableDirectedGraph<Node, SequenceEdge> subgraph : mappingList) {
            System.out.println(count++ + ": " + subgraph);
        }
    }

    public void printMappingSet(Set<ListenableDirectedGraph<Node, SequenceEdge>> mappingSet) {
        int count = 1;
        for (ListenableDirectedGraph<Node, SequenceEdge> subgraph : mappingSet) {
            System.out.println(count++ + ": " + subgraph);
        }
    }

    public void printSearchResults(Set<ListenableDirectedGraph<Node, SequenceEdge>> mappingSet, SubgraphMotif motifType) {
        int count = 1;
        System.out.println("\nIdentified " + mappingSet.size() + " instances of " + motifType.toString() + ":");

        for (ListenableDirectedGraph<Node, SequenceEdge> subgraph : mappingSet) {
            System.out.print(count++ + ": ");// + motifType.toString() + " found:");
            boolean print = false;
//            for (Node node2: subgraph.vertexSet()) {
//                if (node2.getStart() == 472687 || node2.getEnd() == 472687 || node2.getStart() == 472822 || node2.getEnd() == 472822) {
//                    print = true;
//                }
//            }
            switch (motifType) {
                case VARIATION:
                    printFourNodePosition(subgraph, motifType);
                    break;
                case DELETION:
                    printFourNodePosition(subgraph, motifType);
                    break;
                case INSERTION:
                    printFourNodePosition(subgraph, motifType);
                    break;
                case DUPLICATION_IN_QUERY:
                    printDuplicationQueryPosition(subgraph, motifType);
                    break;
                case DUPLICATION_IN_SUBJECT:
                    printDuplicationSubjectPosition(subgraph, motifType);
                    break;
                case CON_DUPLICATION_IN_QUERY:
                    printDuplicationQueryPosition(subgraph, motifType);
                    break;
                case CON_DUPLICATION_IN_SUBJECT:
                    printDuplicationSubjectPosition(subgraph, motifType);
                    break;
                case INVERSION_IN_QUERY:
                    printTwoNodePosition(subgraph, motifType);
                    break;
                case INVERSION_IN_SUBJECT:
                    printTwoNodePosition(subgraph, motifType);
                    break;
            }
        }
    }

    private void printFourNodePosition(ListenableDirectedGraph<Node, SequenceEdge> subgraph, SubgraphMotif motifType) {
        Node qTemp = null;
        Node sTemp = null;
        String qPosition = "";
        String sPosition = "";
        for (Node node: subgraph.vertexSet()) {
            if (node.getNodeType() == Node.Type.QUERY) {
                if (qTemp != null) {
                    qPosition = "query [" + Math.min(qTemp.getEnd(), node.getEnd()) + " - " + Math.max(qTemp.getStart(), node.getStart()) + "]";
                }
                else qTemp = node;
            }
            else {
                if (sTemp != null) {
                    sPosition = "subject [" + Math.min(sTemp.getEnd(), node.getEnd()) + " - " + Math.max(sTemp.getStart(), node.getStart()) + "]";
                }
                else sTemp = node;
            }
        }
        System.out.println(motifType.toString() + " found in " + qPosition + " and " + sPosition);
    }

    private void printDuplicationQueryPosition(ListenableDirectedGraph<Node, SequenceEdge> subgraph, SubgraphMotif motifType) {
        String q1Position = "";
        String q2Position = "";
        String sPosition = "";
        for (Node node: subgraph.vertexSet()) {
            if (node.getNodeType() == Node.Type.QUERY) {
                if (q1Position != "") {
                    q2Position = "query [" + node.getStart() + " - " + node.getEnd() + "]";
                }
                else q1Position = "query [" + node.getStart() + " - " + node.getEnd() + "]";
            }
            else sPosition = "subject [" + node.getStart() + " - " + node.getEnd() + "]";
        }
        System.out.println(motifType.toString() + " found in " + sPosition + ", " + q1Position + " and " + q2Position);
    }

    private void printDuplicationSubjectPosition(ListenableDirectedGraph<Node, SequenceEdge> subgraph, SubgraphMotif motifType) {
        String qPosition = "";
        String s1Position = "";
        String s2Position = "";
        for (Node node: subgraph.vertexSet()) {
            if (node.getNodeType() == Node.Type.QUERY) {
                qPosition = "query [" + node.getStart() + " - " + node.getEnd() + "]";
            }
            else {
                if (s1Position != "") {
                    s2Position = "subject [" + node.getStart() + " - " + node.getEnd() + "]";
                }
                else s1Position = "subject [" + node.getStart() + " - " + node.getEnd() + "]";
            }
        }
        System.out.println(motifType.toString() + " found in " + qPosition + ", " + s1Position + " and " + s2Position);
    }

    private void printTwoNodePosition(ListenableDirectedGraph<Node, SequenceEdge> subgraph, SubgraphMotif motifType) {
        String qPosition = "";
        String sPosition = "";
        for (Node node: subgraph.vertexSet()) {
            if (node.getNodeType() == Node.Type.QUERY) {
                qPosition = "query [" + node.getStart() + " - " + node.getEnd() + "]";
            }
            else sPosition = "subject [" + node.getStart() + " - " + node.getEnd() + "]";
        }
        System.out.println(motifType.toString() + " found in " + qPosition + " and " + sPosition);
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

    public Set<ListenableDirectedGraph<Node, SequenceEdge>> mappingToSet(Iterator<GraphMapping<Node, SequenceEdge>> mappingIterator) {
        Set<ListenableDirectedGraph<Node, SequenceEdge>> mappingSet = new HashSet<>();

        if (isomorphismInspector.isomorphismExists()) {
            for (Iterator<GraphMapping<Node, SequenceEdge>> iter = mappingIterator; iter.hasNext();) {
                ListenableDirectedGraph<Node, SequenceEdge> subgraphMatch = getMappingSubgraph(iter.next());
                mappingSet.add(subgraphMatch);
            }
        }

        return mappingSet;
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
}
