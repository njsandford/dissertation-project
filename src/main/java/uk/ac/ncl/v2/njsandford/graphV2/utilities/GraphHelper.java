package uk.ac.ncl.v2.njsandford.graphV2.utilities;

import org.jgrapht.demo.QueryNode;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.utilities.BlastData;
import uk.ac.ncl.v2.njsandford.graphV2.graphV2.*;

import java.io.FileReader;
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

        /*
        // Add all nodes
        for (int i = 0; i < graphData.size(); i++) {
            currentData = graphData.get(i);

            BreakPoint qStartNode = dataToQueryStartNode(currentData);
            BreakPoint qEndNode = dataToQueryEndNode(currentData);
            BreakPoint sStartNode = dataToSubjectStartNode(currentData);
            BreakPoint sEndNode = dataToSubjectEndNode(currentData);

            if (!queryNodes.contains(qStartNode)) { queryNodes.add(qStartNode); graph.addVertex(qStartNode); }
            if (!queryNodes.contains(qEndNode)) { queryNodes.add(qEndNode); graph.addVertex(qEndNode); }
            if (!subjectNodes.contains(sStartNode)) { subjectNodes.add(sStartNode); graph.addVertex(sStartNode); }
            if (!subjectNodes.contains(sEndNode)) { subjectNodes.add(sEndNode); graph.addVertex(sEndNode); }

            if (!graph.containsEdge(dataToQueryRegion(currentData))) { graph.addEdge(qStartNode, qEndNode, dataToQueryRegion(currentData)); }
            if (!graph.containsEdge(dataToSubjectRegion(currentData))) { graph.addEdge(sStartNode, sEndNode, dataToQueryRegion(currentData)); }

            graph.addEdge(qStartNode, sStartNode, new CorrespondingEdge());
            graph.addEdge(qEndNode, sEndNode, new CorrespondingEdge());
        }

        System.out.print("\n\n******** END Nodes *********");

        BreakPoint qNode;
        BreakPoint sNode;


        System.out.print("\n\n******** END Vertical List*********");
        */

        /*
        BreakPoint qNodeNext = null;
        BreakPoint sNodeNext = null;
        int lastElement = graphData.size() - 1;
        //int gap;
        boolean qDuplicate;
        boolean sDuplicate;

        for (int i = 0; i < graphData.size(); i++) {
            qNode = queryNodes.get(i);
            sNode = subjectNodes.get(i);

            if (i != lastElement) {
                qNodeNext = queryNodes.get(i + 1);
                sNodeNext = subjectNodes.get(i + 1);
            }

            qDuplicate = qNode.equals(qNodeNext);
            sDuplicate = sNode.equals(sNodeNext);

            if (!qDuplicate) {  //ensures a horizontal edge isn't added to the duplicated vertices that appear in the arrayList, vertices don't exist in the vertex set anyway.
                graph.addEdge(qNode, qNodeNext, dataToQueryRegion());
            }
            if (!sDuplicate) {
                graph.addEdge(sNode, sNodeNext, new DefaultEdge(gapCheck(sNode, sNodeNext)));
            }
        }

        System.out.print("\n\n******** END Horizontal Edges*********");

        return graph;
    }
*/
    /*
    private SequenceEdge.Type gapCheck(Node lhs, Node rhs) {
        int lhsStart = lhs.getStart();
        int lhsEnd = lhs.getEnd();
        int rhsStart = rhs.getStart();
        int rhsEnd = rhs.getEnd();

        // NO_GAP:
        if ((lhsEnd + 1) == rhsStart || (lhsEnd + 1) == rhsEnd || ((lhsStart + 1) == rhsStart) || ((lhsStart + 1) == rhsEnd)) return SequenceEdge.Type.NO_GAP;
            // GAP
        else if ((lhsEnd + 1) < rhsStart) return SequenceEdge.Type.GAP;
            // OVERLAP:
            //else if ((rhsStart >= lhsStart && rhsStart <= lhsEnd && rhsEnd > lhsStart && rhsEnd < lhsEnd) || (lhsStart < rhsEnd && lhsStart > rhsEnd && lhsEnd > rhsStart && lhsEnd < rhsEnd)) return SequenceEdge.Type.OVERLAP;
            //else if ((lhsEnd + 1) < rhsStart) return SequenceEdge.Type.GAP;
        else return SequenceEdge.Type.OVERLAP;
    }*/

    /* NO LONGER USED!! Inversion, duplication, etc. checks now done in isomorphism algorithm.
    private boolean inversionCheck(QueryNode qNode, SubjectNode sNode) {
        if ((qNode.getEnd() < qNode.getStart()) || (sNode.getEnd() < sNode.getStart())) return true;
        else return false;
    }*/

    /*
    private BreakPoint dataToQueryBreakPoint(BlastData data) {
        return new BreakPoint(data.getQueryId(), data.getQueryStart(), data.getQueryEnd(), data.getAlignmentLength(), data.getIdentity(), data.geteValue(), data.getBitScore());
    }

    private BreakPoint dataToSubjectBreakPoint(BlastData data) {
        return new BreakPoint();
    }*/

    private BreakPoint dataToQueryStartNode(BlastData data) {
        return new BreakPoint(SequenceType.QUERY, data.getQueryId(), data.getQueryStart());
    }

    private BreakPoint dataToQueryEndNode(BlastData data) {
        return new BreakPoint(SequenceType.QUERY, data.getQueryId(), (data.getQueryEnd() + 1));
    }

    private BreakPoint dataToSubjectStartNode(BlastData data) {
        return new BreakPoint(SequenceType.SUBJECT, data.getSubjectId(), data.getSubjectStart());
    }

    private BreakPoint dataToSubjectEndNode(BlastData data) {
        return new BreakPoint(SequenceType.SUBJECT, data.getSubjectId(), (data.getSubjectEnd() + 1));
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
