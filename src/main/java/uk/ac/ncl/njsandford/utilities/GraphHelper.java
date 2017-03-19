package uk.ac.ncl.njsandford.utilities;

//import org.jgrapht.demo.*;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.njsandford.graph.*;

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

        BlastData local;

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
            graph.addEdge(qNode, sNode, new SequenceEdge(SequenceEdge.Type.MATCH));

            // Add inversion edges
            if (inversionCheck(qNode, sNode)) {
                graph.addEdge(sNode, qNode, new SequenceEdge(SequenceEdge.Type.MATCH));
            }

            // Add duplication edge
            if (i != 0) {
                if (qNode.equals(queryNodes.get(i-1)) || sNode.equals(subjectNodes.get(i-1))) {
                    //graph.addEdge(queryVertices.get(i-1), sNode, new SequenceEdge(SequenceEdge.Type.MATCH));
                    //graph.addEdge(qNode, subjectVertices.get(i-1), new SequenceEdge(SequenceEdge.Type.MATCH));
                    graph.addEdge(qNode, sNode, new SequenceEdge(SequenceEdge.Type.MATCH));
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

    private QueryNode dataToQueryNode(BlastData data) {
        return new QueryNode(data.getQueryId(), data.getQueryStart(), data.getQueryEnd(), data.getAlignmentLength(), data.getIdentity(), data.geteValue(), data.getBitScore());
    }

    private SubjectNode dataToSubjectNode(BlastData data) {
        return new SubjectNode(data.getSubjectId(), data.getSubjectStart(), data.getSubjectEnd(), data.getAlignmentLength(), data.getIdentity(), data.geteValue(), data.getBitScore());
    }


    private void sortData(ArrayList<BlastData> graphData) {
        Collections.sort(graphData, new Comparator<BlastData>() {
            @Override public int compare(BlastData sd1, BlastData sd2) {
                return sd1.getQueryStart() - sd2.getQueryStart();
            }
        });
    }

}
