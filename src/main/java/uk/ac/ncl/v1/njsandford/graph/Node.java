package uk.ac.ncl.v1.njsandford.graph;

import java.util.ArrayList;

/**
 * Created by Natalie on 19/03/2017.
 */
public class Node {
    protected Type nodeType;
    protected String seqId;
    protected int start;
    protected int end;
    protected int alignmentLength;
    protected double identity;
    protected double eValue;
    protected double bitScore;
    protected Direction direction;
    protected ArrayList<SequenceEdge> sequenceEdges;

    public Node(Type nodeType, String seqId, int start, int end, int alignmentLength, double identity, double eValue, double bitScore) {
        setNodeType(nodeType);
        setSeqId(seqId);
        setStart(start);
        setEnd(end);
        setAlignmentLength(alignmentLength);
        setIdentity(identity);
        seteValue(eValue);
        setBitScore(bitScore);
        setDirection();
        sequenceEdges = new ArrayList<>();
    }

    public enum Direction {
        FORWARDS, BACKWARDS
    }

    public enum Type {

        QUERY, SUBJECT
    }

    public Type getNodeType() {
        return nodeType;
    }

    protected void setNodeType(Type nodeType) {
        this.nodeType = nodeType;
    }

    public String getSeqId() {
        return seqId;
    }

    protected void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public int getStart() {
        return start;
    }

    protected void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    protected void setEnd(int end) {
        this.end = end;
    }

    public int getAlignmentLength() {
        return alignmentLength;
    }

    protected void setAlignmentLength(int alignmentLength) {
        this.alignmentLength = alignmentLength;
    }

    public double getIdentity() {
        return identity;
    }

    protected void setIdentity(double identity) {
        this.identity = identity;
    }

    public double geteValue() {
        return eValue;
    }

    protected void seteValue(double eValue) {
        this.eValue = eValue;
    }

    public double getBitScore() {
        return bitScore;
    }

    protected void setBitScore(double bitScore) {
        this.bitScore = bitScore;
    }

    public void addSequenceEdge(SequenceEdge edge) {
        sequenceEdges.add(edge);
    }

    private void setDirection() {
        if (getStart() < getEnd()) {
            this.direction = Direction.FORWARDS;
        }
        else this.direction = Direction.BACKWARDS;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public ArrayList<SequenceEdge> getSequenceEdges() {
        return sequenceEdges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (getStart() != node.getStart()) return false;
        if (getEnd() != node.getEnd()) return false;
        if (getAlignmentLength() != node.getAlignmentLength()) return false;
        if (Double.compare(node.getIdentity(), getIdentity()) != 0) return false;
        if (Double.compare(node.geteValue(), geteValue()) != 0) return false;
        if (Double.compare(node.getBitScore(), getBitScore()) != 0) return false;
        if (getNodeType() != node.getNodeType()) return false;
        if (!getSeqId().equals(node.getSeqId())) return false;
        return getSequenceEdges() != null ? getSequenceEdges().equals(node.getSequenceEdges()) : node.getSequenceEdges() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getNodeType().hashCode();
        result = 31 * result + getSeqId().hashCode();
        result = 31 * result + getStart();
        result = 31 * result + getEnd();
        result = 31 * result + getAlignmentLength();
        temp = Double.doubleToLongBits(getIdentity());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(geteValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getBitScore());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getSequenceEdges() != null ? getSequenceEdges().hashCode() : 0);
        return result;
    }


    @Override
    public String toString()
    {
        return /*getSeqId() + */ getNodeType().toString() + ": " + start + " - "  + end;
    }
}
