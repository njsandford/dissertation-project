package uk.ac.ncl.v1.njsandford.isomorphism;

import uk.ac.ncl.v1.njsandford.graph.Node;

import java.util.Comparator;

/**
 * Created by Natalie on 10/04/2017.
 */
public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node node1, Node node2) {
//        if (node1.getSequenceType() == null || node2.getSequenceType() == null)
//        {
//            return 1;
//        }
//        else if (node1.getSequenceType() != node2.getSequenceType()) {
//            return -1;
//        }
//        return 0;
        return node1.getNodeType().compareTo(node2.getNodeType());
    }
}
