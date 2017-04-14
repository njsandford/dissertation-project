package uk.ac.ncl.v1.njsandford.graph;

/**
 * Created by Natalie on 19/03/2017.
 */
public class SubjectNode extends Node {

    public SubjectNode(String seqID, int start, int end, int alignmentLength, double identity, double eValue, double bitScore)
    {
        super(Node.Type.SUBJECT, seqID, start, end, alignmentLength, identity, eValue, bitScore);

    }
}
