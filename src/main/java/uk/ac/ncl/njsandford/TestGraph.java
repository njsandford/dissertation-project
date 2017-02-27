package uk.ac.ncl.njsandford;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraphSelectionModel;
import org.jgrapht.demo.*;
import org.jgrapht.graph.ListenableDirectedGraph;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Natalie on 26/02/2017.
 */
public class TestGraph {

    private static PopulateGraph populateGraph;
    private static ListenableDirectedGraph<Node, SequenceEdge> graph;

    public static void main(String[] args) {
        populateGraph = new PopulateGraph();
        initialiseGraph();
        //testGraph.graph = testGraph.populateGraph.getGraph();
        //testGraph.printGraph();
        //testGraph.populateGraph.graphVisualisation();
        //GUI();
    }

    private void printGraph() {
        System.out.println(graph);
    }

    private static void initialiseGraph() {
        populateGraph.dataToGraph();
    }

    private static void GUI() {

        JFrame frame = new JFrame("Rearrangement Types");
        final JPanel panel = new JPanel(new GridBagLayout());

        //		PopulateGraph pg = new PopulateGraph();
        //		pg.dataToGraph();
        //		//pg.graph();
        //		pg.graphVisualisation();

        // JGraphXAdapter<Node, SequenceEdge> graph = pg.getGraph();
        // ListenableDirectedGraph<Node, SequenceEdge> g = pg.getListenableGraph();
/*
        JGraphXAdapter<Node, SequenceEdge> Jgraph = new JGraphXAdapter<Node, SequenceEdge>(graph);
        final mxGraphComponent graphComponent = new mxGraphComponent(Jgraph);


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.ipady = 400;
        c.weightx = 1.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(graphComponent, c);
        panel.setPreferredSize(new Dimension(getScreenWorkingWidth()-20, getScreenWorkingHeight()-45));
        */
        //panel.setBounds(0, 20, getScreenWorkingWidth(), 520);

        //Display the window.

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(getScreenWorkingWidth(), 520);
        frame.pack();
        frame.setVisible(true);


    }


    public static int getScreenWorkingWidth()
    {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }

    public static int getScreenWorkingHeight()
    {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }

}
