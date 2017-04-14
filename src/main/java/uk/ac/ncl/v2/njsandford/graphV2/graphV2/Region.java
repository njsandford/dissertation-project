package uk.ac.ncl.v2.njsandford.graphV2.graphV2;

import org.jgrapht.graph.DefaultEdge;

/**
 * Created by Natalie on 13/04/2017.
 */
public class Region extends DefaultEdge {
    protected SequenceType sequenceType;
    protected
    protected String seqId;
    protected int startPos;
    protected int endPos;
    protected int alignmentLength;
    protected double identity;
    protected double eValue;
    protected double bitScore;

    public Region(SequenceType sequenceType, String seqId, int startPos, int endPos, int alignmentLength, double identity, double eValue, double bitScore) {
        setSequenceType(sequenceType);
        setSeqId(seqId);
        setStartPos(startPos);
        setEndPos(endPos);
        setAlignmentLength(alignmentLength);
        setIdentity(identity);
        seteValue(eValue);
        setBitScore(bitScore);
    }

    public enum Label {
        GAP,
    }

    public SequenceType getSequenceType() {
        return sequenceType;
    }

    private void setSequenceType(SequenceType sequenceType) {
        this.sequenceType = sequenceType;
    }

    public String getSeqId() {
        return seqId;
    }

    private void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public int getStartPos() {
        return startPos;
    }

    private void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    private void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    public int getAlignmentLength() {
        return alignmentLength;
    }

    private void setAlignmentLength(int alignmentLength) {
        this.alignmentLength = alignmentLength;
    }

    public double getIdentity() {
        return identity;
    }

    private void setIdentity(double identity) {
        this.identity = identity;
    }

    public double geteValue() {
        return eValue;
    }

    private void seteValue(double eValue) {
        this.eValue = eValue;
    }

    public double getBitScore() {
        return bitScore;
    }

    private void setBitScore(double bitScore) {
        this.bitScore = bitScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (getStartPos() != region.getStartPos()) return false;
        if (getEndPos() != region.getEndPos()) return false;
        if (getAlignmentLength() != region.getAlignmentLength()) return false;
        if (Double.compare(region.getIdentity(), getIdentity()) != 0) return false;
        if (Double.compare(region.geteValue(), geteValue()) != 0) return false;
        if (Double.compare(region.getBitScore(), getBitScore()) != 0) return false;
        if (getSequenceType() != region.getSequenceType()) return false;
        return getSeqId().equals(region.getSeqId());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getSequenceType().hashCode();
        result = 31 * result + getSeqId().hashCode();
        result = 31 * result + getStartPos();
        result = 31 * result + getEndPos();
        result = 31 * result + getAlignmentLength();
        temp = Double.doubleToLongBits(getIdentity());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(geteValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getBitScore());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return getStartPos() + " - " + getEndPos();
    }
}
