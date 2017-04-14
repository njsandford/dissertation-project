package uk.ac.ncl.v2.njsandford.graphV2.graphV2;

import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;

/**
 * Created by Natalie on 13/04/2017.
 */
public class BreakPoint {
    protected String seqId;
    protected SequenceType type;
    protected int position;
    protected ArrayList<DefaultEdge> sequenceEdges;

    public BreakPoint(SequenceType type, String seqId, int position) {
        setType(type);
        setSeqId(seqId);
        setPosition(position);
        sequenceEdges = new ArrayList<>();
    }

    public SequenceType getType() {
        return type;
    }

    protected void setType(SequenceType type) {
        this.type = type;
    }

    public String getSeqId() {
        return seqId;
    }

    protected void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public int getPosition() {
        return position;
    }

    protected void setPosition(int start) {
        this.position = position;
    }

    public void addSequenceEdge(DefaultEdge edge) {
        sequenceEdges.add(edge);
    }

    public ArrayList<DefaultEdge> getSequenceEdges() {
        return sequenceEdges;
    }

    @Override
    public String toString()
    {
        return getSeqId() + /* getSequenceType().toString() + */ ": " + position;
    }
}