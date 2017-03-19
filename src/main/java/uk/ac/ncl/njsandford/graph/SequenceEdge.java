package uk.ac.ncl.njsandford.graph;

import org.jgrapht.graph.DefaultEdge;

/**
 * Created by Natalie on 19/03/2017.
 */
public class SequenceEdge extends DefaultEdge {

    protected Type edgeType;

    public SequenceEdge(Type edgeType) {
        setEdgeType(edgeType);
    }

    public enum Type {

        GAP, NO_GAP, MATCH, INVERSION;
    }

    public void setEdgeType(Type edgeType) {
        this.edgeType = edgeType;
    }

    protected Type getEdgeType() {
        return edgeType;
    }


    public String toString()
    {
        return getEdgeType().toString();
    }
}
