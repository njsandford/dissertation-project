package uk.ac.ncl.v2.njsandford.graphV2.visualisation;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraphSelectionModel;
import com.mxgraph.view.mxStylesheet;
import org.jgrapht.demo.JGraphXAdapter;
import org.jgrapht.demo.UserInputListener;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
import uk.ac.ncl.v1.njsandford.utilities.BlastData;
import uk.ac.ncl.v1.njsandford.isomorphism.SubGraphs;
import uk.ac.ncl.v2.njsandford.graphV2.graphV2.BreakPoint;
import uk.ac.ncl.v2.njsandford.graphV2.graphV2.SequenceType;

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
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> graph;
    private JGraphXAdapter<BreakPoint, DefaultEdge> jGraph;
    private List<BlastData> graphData;

    public GUI(ListenableDirectedGraph<BreakPoint, DefaultEdge> graph, List<BlastData> graphData) {
        this.graph = graph;
        this.jGraph = new JGraphXAdapter<>(graph);
        this.graphData = graphData;
        graphVisualisation();
        initGUI();
    }

    public void initGUI() {
        /*frame = new JFrame("Rearrangement Types");
        final JPanel panel = new JPanel(new GridBagLayout());

        JGraphXAdapter<Node, SequenceEdge> jGraph = new JGraphXAdapter<Node, SequenceEdge>(graph);
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
        panel.setPreferredSize(new Dimension(getScreenWorkingWidth() - 20, getScreenWorkingHeight() - 45));
        frame.add(panel);*/


        JFrame frame = new JFrame("Rearrangement Types");
        final JPanel panel = new JPanel(new GridBagLayout());

        //		PopulateGraph pg = new PopulateGraph();
        //		pg.dataToGraph();
        //		//pg.graph();
        //		pg.graphVisualisation();

        // JGraphXAdapter<Node, SequenceEdge> graph = pg.getGraph();
        // ListenableDirectedGraph<Node, SequenceEdge> g = pg.getListenableGraph();

        //JGraphXAdapter<Node, SequenceEdge> jGraph = new JGraphXAdapter<Node, SequenceEdge>(graph);
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
        panel.setPreferredSize(new Dimension(getScreenWorkingWidth()-20, getScreenWorkingHeight()-45));

        //panel.setBounds(0, 20, getScreenWorkingWidth(), 520);

        JLabel qID = new JLabel(" Query Sequence: " + graphData.get(0).getQueryId() + " ");
        qID.setOpaque(true);
        qID.setBackground(new Color(153, 153, 255));
        qID.setFont(new Font("Ariel", Font.PLAIN, 14));
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(31,20,2,80);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(qID, c);

        JLabel sID = new JLabel("  Subject Sequence: " + graphData.get(0).getSubjectId() + "  ");
        sID.setOpaque(true);
        sID.setBackground(new Color(102, 204, 0));
        sID.setFont(new Font("Ariel", Font.PLAIN, 14));
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(2,20,0,80);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(sID, c);

        JButton refineSearch = new JButton("Refine Search");
        final UserInputListener ui = new UserInputListener(panel);
        refineSearch.addActionListener(ui);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5,148,5,0);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(refineSearch, c);

        JButton clear = new JButton(" Reset Range ");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ui.setPosMin(0);
                ui.setPosMax(0);
                ui.removeText();
            }
        });
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(5,0,5,215);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(clear, c);

        final JTextArea textBox = new JTextArea(21, 77);
        textBox.setEditable(false);
        textBox.setMinimumSize(textBox.getPreferredSize());
        textBox.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                textBox.setText("");
            }
        });

        SubGraphs subGraphs = new SubGraphs();
/*

		*/
/*INSERTION*//*

        final IsomorphismAlgorithm subgraph1;
        subgraph1 = new IsomorphismAlgorithm(graph, subGraphs.deletion(), "QInsertion");
        final IsomorphismAlgorithm subgraph2;
        subgraph2 = new IsomorphismAlgorithm(graph, subGraphs.insertion(), "SInsertion");
        JButton insertion = new JButton("Insertions");
        InsertionListener ins = new InsertionListener(subgraph1, subgraph2, textBox, ui);
        insertion.addActionListener(ins);

		*/
/*DELETION*//*

        final IsomorphismAlgorithm subgraphd1;
        subgraphd1 = new IsomorphismAlgorithm(graph, subGraphs.deletion(), "QInsertion");
        final IsomorphismAlgorithm subgraphd2;
        subgraphd2 = new IsomorphismAlgorithm(graph, subGraphs.insertion(), "SInsertion");
        JButton deletion = new JButton(" Deletions ");
        DeletionListener del = new DeletionListener(subgraphd1, subgraphd2, textBox, ui);
        deletion.addActionListener(del);


		*/
/*VARIATION*//*

        IsomorphismAlgorithm subgraph3;
        subgraph3 = new IsomorphismAlgorithm(graph, subGraphs.variation(), "Variation");
        JButton variation = new JButton("Variations");
        StartSearchListener var = new StartSearchListener(subgraph3, textBox, ui);
        variation.addActionListener(var);


		*/
/*INVERSION*//*

        IsomorphismAlgorithm subgraph4;
        subgraph4 = new IsomorphismAlgorithm(graph, subGraphs.inversion(),"Inversion");
        JButton inversion = new JButton(" Inversion ");
        StartSearchListener inv = new StartSearchListener(subgraph4, textBox, ui);
        inversion.addActionListener(inv);


		*/
/*DUPLICATIONS*//*

        IsomorphismAlgorithm subgraph5;
        subgraph5 = new IsomorphismAlgorithm(graph, subGraphs.duplicationInSearch(),"SDuplication");
        IsomorphismAlgorithm subgraph6;
        subgraph6 = new IsomorphismAlgorithm(graph, subGraphs.duplicationInQuery(),"QDuplication");
        JButton duplication = new JButton("Duplications");
        InsertionListener dup = new InsertionListener(subgraph5, subgraph6, textBox, ui);
        duplication.addActionListener(dup);

*/


        JLabel tblabel = new JLabel("         Search for:          ");

        final JToolBar toolbar = new JToolBar();
        toolbar.add(tblabel);
/*        Dimension d = new Dimension(15, 0);
        toolbar.add(variation);
        toolbar.addSeparator(d);
        toolbar.add(insertion);
        toolbar.addSeparator(d);
        toolbar.add(deletion);
        toolbar.addSeparator(d);
        toolbar.add(inversion);
        toolbar.addSeparator(d);
        toolbar.add(duplication);
        toolbar.addSeparator(d);
*/
        c = new GridBagConstraints();
        c.insets = new Insets(20,20,20,20);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(toolbar, c);

        final JTextArea propBox = new JTextArea(5, 19);
        propBox.setEditable(false);
        propBox.setText("Click on a node");
        propBox.setBackground(new Color(255, 238, 177));
        propBox.setMinimumSize(propBox.getPreferredSize());
        Border loweredbevel = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border title2 = BorderFactory.createTitledBorder(loweredbevel, " Node Properties: ");
        propBox.setBorder(title2);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(16,19,16,16);
        gc.ipadx = 10;
        gc.ipady = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.gridheight = 2;
        gc.gridx = 0;
        gc.gridy = 1;
        panel.add(propBox, gc);

        jGraph.getSelectionModel().addListener(mxEvent.CHANGE, new mxEventSource.mxIEventListener(){
            public void invoke(Object sender, mxEventObject arg1)
            {
                if (sender instanceof mxGraphSelectionModel)
                {
                    for (Object cell : ((mxGraphSelectionModel)sender).getCells())
                    {
                        //int i = jGraph.getCellToVertexMap().get(cell).getAlignmentLength();
                        //double d = jGraph.getCellToVertexMap().get(cell).getIdentity();
                        //String s =  "   Alignment Pos: " + jGraph.getLabel(cell) + "\n   Percent Identity: " + d +
                        //        "%\n   Alignment Length: " + i;

                        //System.out.print("\nNode: " + s);
                        //propBox.setText(s);
                    }}
            }});





        //TextBox
        Border raised = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border title = BorderFactory.createTitledBorder(
                raised, " Query Results: ");
        textBox.setBorder(title);
        JScrollPane sp = new JScrollPane(textBox);
        sp.setMinimumSize(textBox.getPreferredSize());
        c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = 4;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(sp, c);  //textBox

        //Display the window.

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(getScreenWorkingWidth(), 520);
        frame.setVisible(true);

    }

    public void openGUI() {
        graphVisualisation();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
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

            if(cell.getValue() instanceof BreakPoint && ((BreakPoint)cell.getValue()).getType() == SequenceType.QUERY)
            {
                jGraph.getModel().setStyle(cell, "ROUNDED;STYLE_FILLCOLOR=#9999FF");
                y=20;

            }else if(cell.getValue() instanceof BreakPoint && ((BreakPoint)cell.getValue()).getType() == SequenceType.SUBJECT)
            {
                jGraph.getModel().setStyle(cell,"ROUNDED;fillColor=#66CC00");
                y = 300;
            }

            if(counter%2==0){x = x + 180;}

            jGraph.getModel().setGeometry(cell, new mxGeometry(x, y, 85, 55)); //60, 55
            counter++;
        }


        for(mxCell cell2 : jGraph.getEdgeToCellMap().values())
        {
            for (int i = 0; i< jGraph.getVertexToCellMap().values().size(); i++) //mxCell cell : jGraph.getVertexToCellMap().values())
            {
                //cell.getValue();
                if(cell2.getValue().toString().equals("NEXT_MATCH_GAP"))
                {
                    jGraph.getModel().setStyle(cell2,"LABEL;dashed=1;strokeColor=#990099;spacingBottom=-15");
                }
            }

            if(cell2.getValue().toString().equals("NEXT_MATCH"))
            {
                jGraph.getModel().setStyle(cell2,"LABEL;strokeColor=#990099;spacingBottom=-15");

            }else if(cell2.getValue().toString().equals("CORRESPONDS_TO"))
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
        //jGraph.setCellsMovable(false);
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
