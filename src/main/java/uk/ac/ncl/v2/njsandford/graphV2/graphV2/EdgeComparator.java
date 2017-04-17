package uk.ac.ncl.v2.njsandford.graphV2.graphV2;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.specifics.DirectedEdgeContainer;

import java.util.Comparator;

/**
 * Created by Natalie on 16/04/2017.
 */
public class EdgeComparator implements Comparator<DefaultEdge> {

    public int compare(DirectedEdge o1, DirectedEdge o2) {
        return (DirectedEdge)o1.compareTo((DirectedEdge)o2);
    }
}
