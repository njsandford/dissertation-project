package uk.ac.ncl.v1.njsandford.graph;

import org.jgrapht.graph.DefaultEdge;

/**
 * Created by Natalie on 19/03/2017.
 */
public class SequenceEdge extends DefaultEdge {

    protected Type edgeType;

    public SequenceEdge(Type edgeType) {
        super();
        setEdgeType(edgeType);
    }

    public enum Type {

        GAP, NO_GAP, MATCH, OVERLAP, INVERSION;
    }

    @Override
    public Node getSource() {
        return (Node) super.getSource();
    }

    @Override
    public Node getTarget() {
        return (Node) super.getTarget();
    }

    private void setEdgeType(Type edgeType) {
        this.edgeType = edgeType;
    }

    public Type getEdgeType() {
        return edgeType;
    }

    public String toString()
    {
        return getEdgeType().toString();
    }
}
