package uk.ac.ncl.v2.njsandford.graphV2.graphV2;

import org.jgrapht.graph.DefaultEdge;

/**
 * Created by Natalie on 16/04/2017.
 */
public class DirectedEdge extends DefaultEdge {

    public Type edgeType;
    public Direction direction;

    public enum Type {
        REGION, GAP, OVERLAP, CORRESPONDS
    }

    public enum Direction {
        FORWARDS, BACKWARDS
    }

    protected void setType(Type edgeType) {
        this.edgeType = edgeType;
    }

    public Type getEdgeType() {
        return this.edgeType;
    }

    public Direction getDirection() {
        if (getSource().getPosition() < getTarget().getPosition()) {
            return Direction.FORWARDS;
        }
        else return Direction.BACKWARDS;
    }

    @Override
    public BreakPoint getSource() {
        return (BreakPoint) super.getSource();
    }

    @Override
    public BreakPoint getTarget() {
        return (BreakPoint) super.getTarget();
    }
}
