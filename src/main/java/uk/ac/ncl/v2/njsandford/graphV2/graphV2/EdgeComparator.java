package uk.ac.ncl.v2.njsandford.graphV2.graphV2;

import com.sun.org.apache.regexp.internal.RE;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.specifics.DirectedEdgeContainer;

import java.util.Comparator;

/**
 * Created by Natalie on 16/04/2017.
 */
public class EdgeComparator implements Comparator<DefaultEdge> {

    public int compare(DefaultEdge o1, DefaultEdge o2) {
        if (o1 instanceof CorrespondingEdge && o2 instanceof CorrespondingEdge) {
            return 0;
        } else if (o1 instanceof DirectedEdge && o2 instanceof DirectedEdge) {
            if (((DirectedEdge) o1).getSequenceType() == ((DirectedEdge) o2).getSequenceType()) {
                if (o1 instanceof Region && o2 instanceof Region) {
                    if (((Region) o1).getDirection() == ((Region) o2).getDirection()) {
                        return 0;
                    }
                }
                else if (((DirectedEdge) o1).edgeType == ((DirectedEdge) o2).edgeType) {
                    return 0;
                }
            }
        }
        return -1;
    }
}
