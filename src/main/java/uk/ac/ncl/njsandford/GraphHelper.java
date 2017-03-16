package uk.ac.ncl.njsandford;

import com.sun.awt.SecurityWarning;
import org.jgrapht.demo.*;
import org.jgrapht.graph.ListenableDirectedGraph;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Natalie on 14/03/2017.
 */
public class GraphHelper {


    public ListenableDirectedGraph<Node, SequenceEdge> getGraphFromData(ArrayList<StoreData> graphData) {
        ListenableDirectedGraph<Node, SequenceEdge> graph = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

        sortData(graphData);

        StoreData local;

        ArrayList<QueryNode> queryNodes = new ArrayList<>();
        ArrayList<SubjectNode> subjectNodes = new ArrayList<>();

        // Add all nodes
        for (int i = 0; i < graphData.size(); i++) {
            local = graphData.get(i);

            QueryNode qNode = dataToQueryNode(local);
            SubjectNode sNode = dataToSubjectNode(local);

            queryNodes.add(qNode);
            subjectNodes.add(sNode);

            graph.addVertex(qNode);
            graph.addVertex(sNode);
        }

        System.out.print("\n\n******** END Nodes *********");

        QueryNode qNode;
        SubjectNode sNode;

        // Add vertical edges
        for (int i = 0; i < graphData.size(); i++) {
            qNode = queryNodes.get(i);
            sNode = subjectNodes.get(i);

            // Add match edge
            graph.addEdge(qNode, sNode, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));

            // Add inversion edges
            if (inversionCheck(qNode, sNode)) {
                graph.addEdge(sNode, qNode, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
            }

            // Add duplication edge
            if (i != 0) {
                if (qNode.equals(queryNodes.get(i-1)) || sNode.equals(subjectNodes.get(i-1))) {
                    //g.addEdge(queryVertices.get(i-1), sn, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
                    //g.addEdge(qn, subjectVertices.get(i-1), new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
                    graph.addEdge(qNode, sNode, new SequenceEdge(SequenceEdge.Type.CORRESPONDS_TO));
                }
            }
        }

        System.out.print("\n\n******** END Vertical List*********");

        QueryNode qNodeNext = null;
        SubjectNode sNodeNext = null;
        int lastElement = graphData.size() - 1;
        int gap;
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

            gap = checkForGap(qNode, qNodeNext, sNode, sNodeNext);
            switch (gap) {
                case 1:
                    if(!qDuplicate){  //ensures a horizontal edge isn't added to the duplicated vertices that appear in the arrayList, vertices don't exist in the vertex set anyway.
                        graph.addEdge(qNode, qNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
                    }
                    if(!sDuplicate){
                        graph.addEdge(sNode, sNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
                    }

                    break;
                case 2:
                    if(!qDuplicate){
                        graph.addEdge(qNode, qNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
                    }
                    if(!sDuplicate){
                        graph.addEdge(sNode, sNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
                    }

                    break;
                case 3:
                    if(!qDuplicate){
                        graph.addEdge(qNode, qNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
                    }
                    if(!sDuplicate){
                        graph.addEdge(sNode, sNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH_GAP));
                    }

                    break;
                case 4:
                    if(!qDuplicate){
                        graph.addEdge(qNode, qNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
                    }
                    if(!sDuplicate){
                        graph.addEdge(sNode, sNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
                    }
                    break;
                default:
                    if(!qDuplicate){
                        graph.addEdge(qNode, qNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
                    }
                    if(!sDuplicate){
                        graph.addEdge(sNode, sNodeNext, new SequenceEdge(SequenceEdge.Type.NEXT_MATCH));
                    }
                    break;
            }
        }

        System.out.print("\n\n******** END Horizontal Edges*********");

        return graph;
    }

    private int checkForGap(QueryNode qNode, QueryNode qNodeNext, SubjectNode sNode, SubjectNode sNodeNext) {
        int qnEnd = qNode.getEnd();
        int qnnStart = qNodeNext.getStart();
        int snEnd = sNode.getEnd();
        int snnStart = sNodeNext.getStart();
        // GAP in Query:
        if (qnEnd != qnnStart && snEnd == snnStart) return 1;
        // GAP in Subject:
        else if (qnEnd == qnnStart && snEnd != snnStart) return 2;
        // GAP in both:
        else if (qnEnd != qnnStart && snEnd != snnStart) return 3;
        // No GAP:
        else return 0;
    }

    private boolean inversionCheck(QueryNode qNode, SubjectNode sNode) {
        if ((qNode.getEnd() < qNode.getStart()) || (sNode.getEnd() < sNode.getStart())) return true;
        else return false;
    }

    private QueryNode dataToQueryNode(StoreData data) {
        return new QueryNode(data.getQueryID(), data.getqStart(), data.getqEnd(), data.getAlignmentLen(), data.getIdentity());
    }

    private SubjectNode dataToSubjectNode(StoreData data) {
        return new SubjectNode(data.getSubjectID(), data.getqStart(), data.getqEnd(), data.getAlignmentLen(), data.getIdentity());
    }


    private void sortData(ArrayList<StoreData> graphData) {
        Collections.sort(graphData, new Comparator<StoreData>() {
            @Override public int compare(StoreData sd1, StoreData sd2) {
                return sd1.getqStart() - sd2.getqStart();
            }
        });
    }

}
