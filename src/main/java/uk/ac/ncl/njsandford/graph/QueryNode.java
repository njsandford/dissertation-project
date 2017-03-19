package uk.ac.ncl.njsandford.graph;

import uk.ac.ncl.njsandford.graph.Node;

/**
 * Created by Natalie on 19/03/2017.
 */
public class QueryNode extends Node {

    public QueryNode(String seqID, int start, int end, int alignmentLength, double identity, double eValue, double bitScore)
    {
        super(Node.Type.QUERY, seqID, start, end, alignmentLength, identity, eValue, bitScore);

    }
}
