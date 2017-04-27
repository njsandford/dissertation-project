package uk.ac.ncl.v1.njsandford.visualisation;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraphSelectionModel;
import com.mxgraph.view.mxStylesheet;

import org.jgrapht.demo.UserInputListener;
//import org.jgrapht.demo.
import org.jgrapht.demo.JGraphXAdapter;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.graph.SubjectNode;
import uk.ac.ncl.v1.njsandford.utilities.BlastData;
import uk.ac.ncl.v1.njsandford.isomorphism.SubGraphs;
import uk.ac.ncl.v1.njsandford.graph.Node;
import uk.ac.ncl.v1.njsandford.graph.QueryNode;
import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Natalie on 28/02/2017.
 */
public class GUI {

    private JFrame frame;
    private JPanel panel;
    private ListenableDirectedGraph<Node, SequenceEdge> graph;
    private JGraphXAdapter<Node, SequenceEdge> jGraph;
    private List<BlastData> graphData;

    public GUI(ListenableDirectedGraph<Node, SequenceEdge> graph, List<BlastData> graphData) {
        this.graph = graph;
        this.jGraph = new JGraphXAdapter<>(graph);
        this.graphData = graphData;
        graphVisualisation();
        initGUI();
    }

    public void initGUI() {

        this.frame = new JFrame("Rearrangement Types");
        this.panel = new JPanel(new GridBagLayout());

        final mxGraphComponent graphComponent = new mxGraphComponent(jGraph);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.ipady = 400;
        c.weightx = 1.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(graphComponent, c);
        panel.setPreferredSize(new Dimension(getScreenWorkingWidth()-20, 420));

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void setGraphStyle()
    {
        mxStylesheet stylesheet = jGraph.getStylesheet();

        Hashtable<String, Object> nodeStyle = new Hashtable<String, Object>();
        nodeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        nodeStyle.put(mxConstants.STYLE_OPACITY, 80);
        nodeStyle.put(mxConstants.STYLE_FILLCOLOR, "#9999FF");
        nodeStyle.put(mxConstants.STYLE_STROKECOLOR, "#000000");

        stylesheet.putCellStyle("ROUNDED", nodeStyle);

        Map<String, Object> edgeStyle = new HashMap<String, Object>();
        edgeStyle.put(mxConstants.STYLE_LABEL_BACKGROUNDCOLOR, "#EDEDED");

        stylesheet.putCellStyle("LABEL", edgeStyle);

        jGraph.setStylesheet(stylesheet);
    }

    public void graphVisualisation()
    {
        setGraphStyle();

        jGraph.getModel().beginUpdate(); //jGraph.getModel() allow for the jGraph to be changed dynamically

        int counter = 0;
        double x = 20, y = 20;

        for (mxCell cell : jGraph.getVertexToCellMap().values())
        {

            if(cell.getValue() instanceof QueryNode)
            {
                jGraph.getModel().setStyle(cell, "ROUNDED;STYLE_FILLCOLOR=#9999FF");
                y=20;

            }else if(cell.getValue() instanceof SubjectNode)
            {
                jGraph.getModel().setStyle(cell,"ROUNDED;fillColor=#66CC00");
                y = 300;
            }

            if(counter%2==0){x += 180;}

            jGraph.getModel().setGeometry(cell, new mxGeometry(x, y, 85, 55)); //60, 55
            counter++;
        }


        for(mxCell cell2 : jGraph.getEdgeToCellMap().values())
        {
            for (int i = 0; i< jGraph.getVertexToCellMap().values().size(); i++) //mxCell cell : jGraph.getVertexToCellMap().values())
            {
                //cell.getValue();
                if(cell2.getValue().toString().equals("GAP"))
                {
                    jGraph.getModel().setStyle(cell2,"LABEL;dashed=1;strokeColor=#990099;spacingBottom=-15");
                }
            }

            if(cell2.getValue().toString().equals("NO_GAP"))
            {
                jGraph.getModel().setStyle(cell2,"LABEL;strokeColor=#990099;spacingBottom=-15");

            }else if(cell2.getValue().toString().equals("MATCH"))
            {
                jGraph.getModel().setStyle(cell2,"LABEL;strokeColor=#6600FF");
            }

        }

        jGraph.getModel().endUpdate();
        jGraph.setDisconnectOnMove(false);
        jGraph.setEdgeLabelsMovable(false);
        jGraph.setCellsDisconnectable(false);
        jGraph.setCellsEditable(false);
        jGraph.setCellsResizable(false);
        jGraph.setCellsBendable(true);
        jGraph.setConnectableEdges(false);
        jGraph.setAllowDanglingEdges(false);
        jGraph.setCellsMovable(true);


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
