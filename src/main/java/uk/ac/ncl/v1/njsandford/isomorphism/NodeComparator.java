package uk.ac.ncl.v1.njsandford.isomorphism;

import uk.ac.ncl.v1.njsandford.graph.Node;

import java.util.Comparator;

/**
 * Created by Natalie on 10/04/2017.
 */


public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node node1, Node node2) {
        if (node1.getNodeType() == node2.getNodeType()) {
            if (node1.getDirection() == node2.getDirection()) {
                return 0;
            }
        }
        return -1;
    }
}


