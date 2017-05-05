package uk.ac.ncl.v2.njsandford.graphV2.utilities;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.utilities.BlastData;
import uk.ac.ncl.v2.njsandford.graphV2.graphV2.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by Natalie on 14/03/2017.
 */
public class GraphHelper {

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> getGraphFromData(ArrayList<BlastData> graphData) {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> graph = new ListenableDirectedGraph<BreakPoint, DefaultEdge>(DefaultEdge.class);

        sortData(graphData);

        ArrayList<BreakPoint> queryNodes = new ArrayList<>();
        ArrayList<BreakPoint> subjectNodes = new ArrayList<>();

        BreakPoint queryStart;
        BreakPoint queryEnd;
        BreakPoint subjectStart;
        BreakPoint subjectEnd;

        DefaultEdge queryRegion;
        DefaultEdge subjectRegion;

        DefaultEdge startCorrespond;
        DefaultEdge endCorrespond;

        for (BlastData currentData : graphData) {

            // Add break point nodes to graph and array lists.
            //queryStart = dataToQueryStartNode(currentData);
            int queryStartPos = currentData.getQueryStart();
            int queryEndPos = currentData.getQueryEnd();
            int maxQuery = Math.max(queryStartPos, queryEndPos);
            if (queryStartPos == maxQuery) { queryStartPos++; }
            else { queryEndPos++; }
            queryStart = new BreakPoint(SequenceType.QUERY, currentData.getQueryId(), queryStartPos);
            if (!queryNodes.contains(queryStart)) {
                queryNodes.add(queryStart);
                graph.addVertex(queryStart);
            }
            else {
                System.out.println("node exists!");
            }
            //queryEnd = dataToQueryEndNode(currentData);
            queryEnd = new BreakPoint(SequenceType.QUERY, currentData.getQueryId(), queryEndPos);
            if (!queryNodes.contains(queryEnd)) {
                queryNodes.add(queryEnd);
                graph.addVertex(queryEnd);
            }
            else {
                System.out.println("node exists!");
            }
            int subjectStartPos = currentData.getSubjectStart();
            int subjectEndPos = currentData.getSubjectEnd();
            int maxSubject = Math.max(subjectStartPos, subjectEndPos);
            if (subjectStartPos == maxSubject) { subjectStartPos++; }
            else { subjectEndPos++; }
            //subjectStart = dataToSubjectStartNode(currentData);
            subjectStart = new BreakPoint(SequenceType.SUBJECT, currentData.getSubjectId(), subjectStartPos);
            if (!subjectNodes.contains(subjectStart)) {
                subjectNodes.add(subjectStart);
                graph.addVertex(subjectStart);
            }
            else {
                System.out.println("node exists!");
            }
            //subjectEnd = dataToSubjectEndNode(currentData);
            subjectEnd = new BreakPoint(SequenceType.SUBJECT, currentData.getSubjectId(), subjectEndPos);
            if (!subjectNodes.contains(subjectEnd)) {
                subjectNodes.add(subjectEnd);
                graph.addVertex(subjectEnd);
            }
            else {
                System.out.println("node exists!");
            }

            // Add all region edges.
            queryRegion = dataToQueryRegion(currentData);
            if (!graph.containsEdge(queryRegion) && !graph.containsEdge(queryStart, queryEnd)) {
                graph.addEdge(queryStart, queryEnd, queryRegion);
            }
            else {
                System.out.println("region exists!" + queryRegion.toString());
            }
            subjectRegion = dataToSubjectRegion(currentData);
            if (!graph.containsEdge(subjectRegion) && !graph.containsEdge(subjectStart, subjectEnd)) {
                graph.addEdge(subjectStart, subjectEnd, subjectRegion);
            }
            else {
                System.out.println("region exists!" + subjectRegion.toString());
            }

            // Add corresponding edges.
            if (!graph.containsEdge(queryStart, subjectStart)) {
                graph.addEdge(queryStart, subjectStart, new CorrespondingEdge());
            }
            else {
                System.out.println("corresponding edge exists!");
            }
            if (!graph.containsEdge(queryEnd, subjectEnd)) {
                graph.addEdge(queryEnd, subjectEnd, new CorrespondingEdge());
            }
            else {
                System.out.println("corresponding edge exists!");
            }
        }

        // Order nodes by sequence position.
        sortNodes(queryNodes);
        sortNodes(subjectNodes);

        // Add all gap and overlap edges.
        BreakPoint currentNode;
        BreakPoint nextNode;
        // Loop through all nodes except the last, which will not have a 'next node' to be connected to.
        for (int i = 0; i < (queryNodes.size() - 1); i++) {
            currentNode = queryNodes.get(i);
            nextNode = queryNodes.get(i + 1);
            connectBreakPoints(graph, currentNode, nextNode);
        }
        for (int i = 0; i < (subjectNodes.size() - 1); i++) {
            currentNode = subjectNodes.get(i);
            nextNode = subjectNodes.get(i + 1);
            connectBreakPoints(graph, currentNode, nextNode);
        }

        return graph;
    }

    private void connectBreakPoints(ListenableDirectedGraph<BreakPoint, DefaultEdge> graph, BreakPoint currentNode, BreakPoint nextNode) {
        // If the two nodes are not already connected, connect them with either a GAP or an OVERLAP edge.
        if (!graph.containsEdge(currentNode, nextNode) && !graph.containsEdge(nextNode, currentNode)) {
            SequenceType currentType = currentNode.getType();
            // Check the existing edges of the two nodes, to see if they overlap.
            boolean gap = true;
            for (DefaultEdge edge : graph.outgoingEdgesOf(currentNode)) {
                if ((graph.getEdgeTarget(edge).getType() == currentType) && (graph.getEdgeTarget(edge).getPosition() > nextNode.getPosition())) {
                    gap = false;
                }
            }
            if (gap) {
                graph.addEdge(currentNode, nextNode, new DirectedEdge(DirectedEdge.Type.GAP, currentType, currentNode.getPosition(), nextNode.getPosition())); // set sequence type!!
            }
            else {
                graph.addEdge(currentNode, nextNode, new DirectedEdge(DirectedEdge.Type.OVERLAP, currentType, currentNode.getPosition(), nextNode.getPosition())); // set sequence type!!
            }
        }
    }

    private Region dataToQueryRegion(BlastData data) {
        return new Region(SequenceType.QUERY, data.getQueryId(), data.getQueryStart(), data.getQueryEnd(), data.getAlignmentLength(), data.getIdentity(), data.geteValue(), data.getBitScore());
    }

    private Region dataToSubjectRegion(BlastData data) {
        return new Region(SequenceType.SUBJECT, data.getSubjectId(), data.getSubjectStart(), data.getSubjectEnd(), data.getAlignmentLength(), data.getIdentity(), data.geteValue(), data.getBitScore());
    }

    private void sortNodes(ArrayList<BreakPoint> nodes) {
        Collections.sort(nodes, new Comparator<BreakPoint>() {
            @Override
            public int compare(BreakPoint o1, BreakPoint o2) {
                return o1.getPosition() - o2.getPosition();
            }
        });
    }

    private void sortData(ArrayList<BlastData> graphData) {
        Collections.sort(graphData, new Comparator<BlastData>() {
            @Override public int compare(BlastData sd1, BlastData sd2) {
                return sd1.getQueryStart() - sd2.getQueryStart();
            }
        });
    }
}
