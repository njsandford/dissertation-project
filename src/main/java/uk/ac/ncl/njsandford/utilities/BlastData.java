package uk.ac.ncl.njsandford.utilities;

/**
 * Created by Natalie on 19/03/2017.
 */
public class BlastData {

    private String queryId;
    private String subjectId;
    private double identity;
    private int alignmentLength;
    private int mismatches;
    private int gapOpens;
    private int queryStart;
    private int queryEnd;
    private int subjectStart;
    private int subjectEnd;
    private double eValue;
    private double bitScore;

    public BlastData(String queryId, String subjectId, double identity, int alignmentLength, int mismatches, int gapOpens, int queryStart, int queryEnd, int subjectStart, int subjectEnd, double eValue, double bitScore) {
        setQueryId(queryId);
        setSubjectId(subjectId);
        setIdentity(identity);
        setAlignmentLength(alignmentLength);
        setMismatches(mismatches);
        setGapOpens(gapOpens);
        setQueryStart(queryStart);
        setQueryEnd(queryEnd);
        setSubjectStart(subjectStart);
        setSubjectEnd(subjectEnd);
        seteValue(eValue);
        setBitScore(bitScore);
    }

    public String getQueryId() {

        return queryId;
    }

    private void setQueryId(String queryId) {
        this.queryId = queryId;
    }
    public String getSubjectId() {
        return subjectId;
    }

    private void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public double getIdentity() {
        return identity;
    }

    private void setIdentity(double identity) {
        this.identity = identity;
    }

    public int getAlignmentLength() {
        return alignmentLength;
    }

    private void setAlignmentLength(int alignmentLength) {
        this.alignmentLength = alignmentLength;
    }

    public int getMismatches() {
        return mismatches;
    }

    private void setMismatches(int mismatches) {
        this.mismatches = mismatches;
    }

    public int getGapOpens() {
        return gapOpens;
    }

    private void setGapOpens(int gapOpens) {
        this.gapOpens = gapOpens;
    }

    public int getQueryStart() {
        return queryStart;
    }

    private void setQueryStart(int queryStart) {
        this.queryStart = queryStart;
    }

    public int getQueryEnd() {
        return queryEnd;
    }

    private void setQueryEnd(int queryEnd) {
        this.queryEnd = queryEnd;
    }

    public int getSubjectStart() {
        return subjectStart;
    }

    private void setSubjectStart(int subjectStart) {
        this.subjectStart = subjectStart;
    }

    public int getSubjectEnd() {
        return subjectEnd;
    }

    private void setSubjectEnd(int subjectEnd) {
        this.subjectEnd = subjectEnd;
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
}
