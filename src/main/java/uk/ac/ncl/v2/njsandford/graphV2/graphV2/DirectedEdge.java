package uk.ac.ncl.v2.njsandford.graphV2.graphV2;

import org.jgrapht.demo.SequenceEdge;
import org.jgrapht.graph.DefaultEdge;

/**
 * Created by Natalie on 16/04/2017.
 */
public class DirectedEdge extends DefaultEdge {

    protected Type edgeType;
    protected SequenceType sequenceType;

    public DirectedEdge(Type edgeType, SequenceType sequenceType) {
        setType(edgeType);
        setSequenceType(sequenceType);
    }

    public enum Type {
        REGION, GAP, OVERLAP
    }

    public enum Direction {
        FORWARDS, BACKWARDS
    }

    private void setType(Type edgeType) {
        this.edgeType = edgeType;
    }

    public Type getEdgeType() {
        return this.edgeType;
    }

    private void setSequenceType(SequenceType sequenceType) {
        this.sequenceType = sequenceType;
    }

    public SequenceType getSequenceType() {
        return this.sequenceType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectedEdge that = (DirectedEdge) o;

        if (getEdgeType() != that.getEdgeType()) return false;
        return getSequenceType() == that.getSequenceType();
    }

    @Override
    public int hashCode() {
        int result = getEdgeType().hashCode();
        result = 31 * result + getSequenceType().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return getEdgeType().toString();
    }
}
