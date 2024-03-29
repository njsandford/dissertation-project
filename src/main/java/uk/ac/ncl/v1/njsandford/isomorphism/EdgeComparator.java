package uk.ac.ncl.v1.njsandford.isomorphism;

import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;

import java.util.Comparator;

/**
 * Created by Natalie on 11/04/2017.
 */
public class EdgeComparator implements Comparator<SequenceEdge> {

    @Override
    public int compare(SequenceEdge edge1, SequenceEdge edge2) {
        if (((edge1.getEdgeType() == SequenceEdge.Type.DEFAULT) && (edge2.getEdgeType() != SequenceEdge.Type.MATCH)) || ((edge2.getEdgeType() == SequenceEdge.Type.DEFAULT) && (edge1.getEdgeType() != SequenceEdge.Type.MATCH))) {
            return 0;
        }
        else return edge1.getEdgeType().compareTo(edge2.getEdgeType());
    }
}
