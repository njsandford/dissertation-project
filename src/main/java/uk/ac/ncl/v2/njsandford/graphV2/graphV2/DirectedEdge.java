package uk.ac.ncl.v2.njsandford.graphV2.graphV2;

import org.jgrapht.demo.SequenceEdge;
import org.jgrapht.graph.DefaultEdge;

/**
 * Created by Natalie on 16/04/2017.
 */
public class DirectedEdge extends DefaultEdge implements Comparable<> {

    protected Type edgeType;
    protected SequenceType sequenceType;
    protected int startPos;
    protected int endPos;
    protected Direction direction;

    public DirectedEdge(Type edgeType, SequenceType sequenceType, int startPos, int endPos) {
        setType(edgeType);
        setSequenceType(sequenceType);
        setStartPos(startPos);
        setEndPos(endPos);
        setDirection();
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

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    private void setDirection() {
        if (getStartPos() < getEndPos()) { this.direction = Direction.FORWARDS; }
        else this.direction = Direction.BACKWARDS;
    }

    public Direction getDirection() {
        return this.direction;
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

        if (getStartPos() != that.getStartPos()) return false;
        if (getEndPos() != that.getEndPos()) return false;
        if (getEdgeType() != that.getEdgeType()) return false;
        if (getSequenceType() != that.getSequenceType()) return false;
        return getDirection() == that.getDirection();
    }

    @Override
    public int hashCode() {
        int result = getEdgeType().hashCode();
        result = 31 * result + getSequenceType().hashCode();
        result = 31 * result + getStartPos();
        result = 31 * result + getEndPos();
        result = 31 * result + getDirection().hashCode();
        return result;
    }



    @Override
    public String toString() {
        return getEdgeType().toString();
    }
}
