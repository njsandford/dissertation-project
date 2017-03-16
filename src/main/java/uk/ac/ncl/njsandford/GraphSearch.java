package uk.ac.ncl.njsandford;

import org.jgrapht.demo.Node;
import org.jgrapht.demo.SequenceEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import java.util.Scanner;

/**
 * Created by Natalie on 16/03/2017.
 */
public class GraphSearch {

    private SubGraphs subGraphs;
    private SearchAlgorithms searchAlgorithms;
    private ListenableDirectedGraph<Node, SequenceEdge> graph;

    public GraphSearch(ListenableDirectedGraph<Node, SequenceEdge> graph) {
        this.subGraphs = new SubGraphs();
        this.searchAlgorithms = new SearchAlgorithms();
        this.graph = graph;
    }

    public void userInputSearch() {

        boolean quit = false;

        while (!quit){

            System.out.print("\n\nSelect a rearrangment type:" +
                    " \n Press 1 for Insertions\n Press 2 for Deletions \n Press 3 for" +
                    " Inversions \n Press 4 for Duplications \n Press 5 for Variations " +
                    "\nPress 6 to Exit");

            Scanner keyIn = new Scanner(System.in);
            int input = keyIn.nextInt();

            switch (input){
                // Insertion
                case 1:

                    break;
                // Deletion
                case 2:

                    break;
                // Inversion
                case 3:

                    break;
                // Duplication
                case 4:

                    break;
                // Variation
                case 5:

                    break;
                // Exit
                case 6: quit = true;
                    break;
                // Default
                default:
                    System.out.println("\nInvalid Entry!");
            }
        }
    }
}
