package uk.ac.ncl.njsandford;

import org.jgrapht.demo.Node;
import org.jgrapht.demo.SequenceEdge;
import org.jgrapht.demo.StoreData;
import org.jgrapht.graph.ListenableDirectedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Natalie on 14/03/2017.
 */
public class GraphHelper {

    /*
    public ListenableDirectedGraph<Node, SequenceEdge> getGraphFromData(ArrayList<StoreData> graphData) {
        ListenableDirectedGraph<Node, SequenceEdge> graph;



        return graph;
    }
    */

    public void sortData(ArrayList<StoreData> graphData) {
        Collections.sort(graphData, new Comparator<StoreData>() {
            @Override public int compare(StoreData sd1, StoreData sd2) {
                return sd1.getqStart() - sd2.getqStart();
            }
        });
    }

}
