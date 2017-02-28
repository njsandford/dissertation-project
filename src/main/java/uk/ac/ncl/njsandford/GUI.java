package uk.ac.ncl.njsandford;

import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.demo.JGraphXAdapter;
import org.jgrapht.demo.Node;
import org.jgrapht.demo.SequenceEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Natalie on 28/02/2017.
 */
public class GUI {

    private JFrame frame;
    private ListenableDirectedGraph<Node, SequenceEdge> graph;

    public GUI(ListenableDirectedGraph<Node, SequenceEdge> graph) {
        this.graph = graph;
        initGUI();
    }

    public void initGUI() {
        frame = new JFrame("Rearrangement Types");
        final JPanel panel = new JPanel(new GridBagLayout());

        //		PopulateGraph pg = new PopulateGraph();
        //		pg.dataToGraph();
        //		//pg.graph();
        //		pg.graphVisualisation();

        // JGraphXAdapter<Node, SequenceEdge> graph = pg.getGraph();
        // ListenableDirectedGraph<Node, SequenceEdge> g = pg.getListenableGraph();

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
        panel.setPreferredSize(new Dimension(getScreenWorkingWidth() - 20, getScreenWorkingHeight() - 45));
        frame.add(panel);
    }

    public void openGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public int getScreenWorkingWidth()
    {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }

    public int getScreenWorkingHeight()
    {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }


}
