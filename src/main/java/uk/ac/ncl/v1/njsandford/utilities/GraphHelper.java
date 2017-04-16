package uk.ac.ncl.v1.njsandford.utilities;

//import org.jgrapht.demo.*;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.graph.*;
import uk.ac.ncl.v1.njsandford.graph.Node;
import uk.ac.ncl.v1.njsandford.graph.QueryNode;
import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;
import uk.ac.ncl.v1.njsandford.graph.SubjectNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Natalie on 14/03/2017.
 */
public class GraphHelper {


    public ListenableDirectedGraph<Node, SequenceEdge> getGraphFromData(ArrayList<BlastData> graphData) {
        ListenableDirectedGraph<Node, SequenceEdge> graph = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

        sortData(graphData);

        ArrayList<Node> queryNodes = new ArrayList<>();
        ArrayList<Node> subjectNodes = new ArrayList<>();

        BlastData currentData;

        // Add all nodes and matching edges
        for (int i = 0; i < graphData.size(); i++) {
            currentData = graphData.get(i);

            QueryNode qNode = dataToQueryNode(currentData);
            SubjectNode sNode = dataToSubjectNode(currentData);

            queryNodes.add(qNode);
            subjectNodes.add(sNode);

            graph.addVertex(qNode);
            graph.addVertex(sNode);

            // Add match edge
            graph.addEdge(qNode, sNode, new SequenceEdge(SequenceEdge.Type.MATCH));
        }

        System.out.print("\n\n******** END Nodes *********");

        Node qNode;

        Node sNode;

        /*// Add vertical edges
        for (int i = 0; i < graphData.size(); i++) {
            qNode = queryNodes.get(i);
            sNode = subjectNodes.get(i);

            // Add match edge
            graph.addEdge(qNode, sNode, new SequenceEdge(SequenceEdge.Type.MATCH));

            // NO LONGER USED - This is computation that should be done in the isomorphism logic!
            // Add inversion edges
            /*if (inversionCheck(qNode, sNode)) {
                graph.addEdge(sNode, qNode, new SequenceEdge(SequenceEdge.Type.MATCH));
            }*/

            // Seems to be redundant code? duplications are just a sequenceType of match, like inversions!
            // Add duplication edge
            /*if (i != 0) {
                if (qNode.equals(queryNodes.get(i-1)) || sNode.equals(subjectNodes.get(i-1))) {
                    graph.addEdge(queryNodes.get(i-1), sNode, new SequenceEdge(SequenceEdge.Type.MATCH));
                    graph.addEdge(qNode, subjectNodes.get(i-1), new SequenceEdge(SequenceEdge.Type.MATCH));
                    //graph.addEdge(qNode, sNode, new SequenceEdge(SequenceEdge.Type.MATCH));
                }
            }*/
        //}*/

        System.out.print("\n\n******** END Vertical List*********");

        Node qNodeNext = null;
        Node sNodeNext = null;
        int lastElement = graphData.size() - 1;
        //int gap;
        boolean qDuplicate;
        boolean sDuplicate;

        // Order the nodes according to the lowest position of each node.
        sortNodes(subjectNodes);
        sortNodes(queryNodes);

        for (int i = 0; i < graphData.size(); i++) {
            qNode = queryNodes.get(i);
            sNode = subjectNodes.get(i);

            if (i != lastElement) {
                qNodeNext = queryNodes.get(i+1);
                sNodeNext = subjectNodes.get(i+1);
            }

            qDuplicate = qNode.equals(qNodeNext);
            sDuplicate = sNode.equals(sNodeNext);

            if(!qDuplicate){  //ensures a horizontal edge isn't added to the duplicated vertices that appear in the arrayList, vertices don't exist in the vertex set anyway.
                graph.addEdge(qNode, qNodeNext, new SequenceEdge(gapCheck(qNode, qNodeNext)));
            }
            else {
                System.out.println("qDuplicate" + qDuplicate);
            }
            if(!sDuplicate){
                graph.addEdge(sNode, sNodeNext, new SequenceEdge(gapCheck(sNode, sNodeNext)));
            }
            else {
                System.out.println("sDuplicate" + sDuplicate);
            }

            /*
            gap = checkForGap(qNode, qNodeNext, sNode, sNodeNext);
            switch (gap) {
                case 1:
                    if(!qDuplicate){  //ensures a horizontal edge isn't added to the duplicated vertices that appear in the arrayList, vertices don't exist in the vertex set anyway.
                        graph.addEdge(qNode, qNodeNext, new SequenceEdge(SequenceEdge.Type.GAP));
                    }
                    if(!sDuplicate){
                        graph.addEdge(sNode, sNodeNext, new SequenceEdge(SequenceEdge.Type.NO_GAP));
                    }

                    break;
                case 2:
                    if(!qDuplicate){
                        graph.addEdge(qNode, qNodeNext, new SequenceEdge(SequenceEdge.Type.NO_GAP));
                    }
                    if(!sDuplicate){
                        graph.addEdge(sNode, sNodeNext, new SequenceEdge(SequenceEdge.Type.GAP));
                    }

                    break;
                case 3:
                    if(!qDuplicate){
                        graph.addEdge(qNode, qNodeNext, new SequenceEdge(SequenceEdge.Type.GAP));
                    }
                    if(!sDuplicate){
                        graph.addEdge(sNode, sNodeNext, new SequenceEdge(SequenceEdge.Type.GAP));
                    }

                    break;
                default:
                    if(!qDuplicate){
                        graph.addEdge(qNode, qNodeNext, new SequenceEdge(SequenceEdge.Type.NO_GAP));
                    }
                    if(!sDuplicate){
                        graph.addEdge(sNode, sNodeNext, new SequenceEdge(SequenceEdge.Type.NO_GAP));
                    }
                    break;
            }*/
        }

        System.out.print("\n\n******** END Horizontal Edges*********");

        return graph;
    }

    /*
    private int checkForGap(QueryNode qNode, QueryNode qNodeNext, SubjectNode sNode, SubjectNode sNodeNext) {
        int qnnStart = qNodeNext.getStart();
        int qnEnd = qNode.getEnd();
        int snEnd = sNode.getEnd();
        int snnStart = sNodeNext.getStart();

        // GAP in Query:
        if (((qnEnd + 1) < qnnStart) && ((snEnd + 1) == snnStart)) return 1;
        // GAP in Subject:
        else if (((qnEnd + 1) == qnnStart) && ((snEnd + 1) < snnStart)) return 2;
        // GAP in both:
        else if (((qnEnd + 1) == qnnStart) && ((snEnd + 1) == snnStart)) return 3;
        // OVERLAP:
        //else if () return 4;
        // No GAP:
        else return 0;
    }
    */

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
    }

    /* NO LONGER USED!! Inversion, duplication, etc. checks now done in isomorphism algorithm.
    private boolean inversionCheck(QueryNode qNode, SubjectNode sNode) {
        if ((qNode.getEnd() < qNode.getStart()) || (sNode.getEnd() < sNode.getStart())) return true;
        else return false;
    }*/

    private QueryNode dataToQueryNode(BlastData data) {
        return new QueryNode(data.getQueryId(), data.getQueryStart(), data.getQueryEnd(), data.getAlignmentLength(), data.getIdentity(), data.geteValue(), data.getBitScore());
    }

    private SubjectNode dataToSubjectNode(BlastData data) {
        return new SubjectNode(data.getSubjectId(), data.getSubjectStart(), data.getSubjectEnd(), data.getAlignmentLength(), data.getIdentity(), data.geteValue(), data.getBitScore());
    }

    private void sortNodes(ArrayList<Node> nodes) {
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Math.min(o1.getStart(), o1.getEnd()) - Math.min(o2.getStart(), o2.getEnd());
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
