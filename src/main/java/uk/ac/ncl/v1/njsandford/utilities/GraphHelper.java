package uk.ac.ncl.v1.njsandford.utilities;

import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.graph.*;
import uk.ac.ncl.v1.njsandford.graph.Node;
import uk.ac.ncl.v1.njsandford.graph.QueryNode;
import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;
import uk.ac.ncl.v1.njsandford.graph.SubjectNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Natalie on 14/03/2017.
 */
public class GraphHelper {

    private ListenableDirectedGraph<Node, SequenceEdge> graph;
    private List<BlastData> graphData;
    private List<Node> queryNodes;
    private List<Node> subjectNodes;

    public ListenableDirectedGraph<Node, SequenceEdge> getGraphFromData(List<BlastData> graphData) {
        this.graphData = graphData;
        graph = new ListenableDirectedGraph<>(SequenceEdge.class);
        queryNodes = new ArrayList<>();
        subjectNodes = new ArrayList<>();

        // Sort data by query positions
        sortGraphData();

        // Add all nodes and matching edges
        addMatchesToGraph();

        // Order both sets of nodes according to the lowest position of each node.
        sortNodes(subjectNodes);
        sortNodes(queryNodes);

        // Add in all horizontal edges
        addHorizontalEdges();

        return graph;
    }

    private void addMatchesToGraph() {
        for (BlastData currentData : graphData) {

            QueryNode qNode = dataToQueryNode(currentData);
            SubjectNode sNode = dataToSubjectNode(currentData);

            queryNodes.add(qNode);
            subjectNodes.add(sNode);

            graph.addVertex(qNode);
            graph.addVertex(sNode);

            // Add match edge
            graph.addEdge(qNode, sNode, new SequenceEdge(SequenceEdge.Type.MATCH));
        }
    }

    private void addHorizontalEdges() {
        Node qNode;
        Node sNode;

        Node qNodeNext = null;
        Node sNodeNext = null;
        int lastElement = graphData.size() - 1;
        boolean qDuplicate;
        boolean sDuplicate;

        for (int i = 0; i < graphData.size(); i++) {
            qNode = queryNodes.get(i);
            sNode = subjectNodes.get(i);

            if (i != lastElement) {
                qNodeNext = queryNodes.get(i+1);
                sNodeNext = subjectNodes.get(i+1);
            }

            qDuplicate = qNode.equals(qNodeNext);
            sDuplicate = sNode.equals(sNodeNext);

            if(!qDuplicate){
                graph.addEdge(qNode, qNodeNext, new SequenceEdge(gapCheck(qNode, qNodeNext)));
            }
            if(!sDuplicate){
                graph.addEdge(sNode, sNodeNext, new SequenceEdge(gapCheck(sNode, sNodeNext)));
            }
        }
    }

    private SequenceEdge.Type gapCheck(Node lhs, Node rhs) {
        int lhsStart = lhs.getStart();
        int lhsEnd = lhs.getEnd();
        int rhsStart = rhs.getStart();
        int rhsEnd = rhs.getEnd();

        // NO_GAP:
        if (((lhsEnd + 1) == rhsStart) || ((lhsEnd + 1) == rhsEnd) ||
                ((lhsStart + 1) == rhsStart) || ((lhsStart + 1) == rhsEnd)) {
            return SequenceEdge.Type.NO_GAP;
        }
        // GAP
        else if ((lhsEnd + 1) < rhsStart) {
            return SequenceEdge.Type.GAP;
        }
        // OVERLAP:
        else return SequenceEdge.Type.OVERLAP;
    }

    private QueryNode dataToQueryNode(BlastData data) {
        return new QueryNode(data.getQueryId(), data.getQueryStart(), data.getQueryEnd(), data.getAlignmentLength(), data.getIdentity(), data.geteValue(), data.getBitScore());
    }

    private SubjectNode dataToSubjectNode(BlastData data) {
        return new SubjectNode(data.getSubjectId(), data.getSubjectStart(), data.getSubjectEnd(), data.getAlignmentLength(), data.getIdentity(), data.geteValue(), data.getBitScore());
    }

    private void sortNodes(List<Node> nodes) {
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Math.min(o1.getStart(), o1.getEnd()) - Math.min(o2.getStart(), o2.getEnd());
            }
        });
    }

    private void sortGraphData() {
        Collections.sort(graphData, new Comparator<BlastData>() {
            @Override public int compare(BlastData sd1, BlastData sd2) {
                //return Math.min(sd1.getSubjectStart(), sd1.getSubjectEnd()) - Math.min(sd2.getSubjectStart(), sd2.getSubjectEnd());
                return sd1.getQueryStart() - sd2.getQueryStart();
            }
        });
    }
}
