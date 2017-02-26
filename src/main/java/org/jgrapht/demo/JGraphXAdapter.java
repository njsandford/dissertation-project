package org.jgrapht.demo;



import java.util.LinkedHashMap;



import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

import com.mxgraph.view.mxGraph;



public class JGraphXAdapter<V, E> extends mxGraph implements GraphListener<V, E>
{

	private ListenableGraph<V, E> graphT;
	private LinkedHashMap<V, mxCell>  vertexToCellMap     = new LinkedHashMap<V, mxCell>();
	private LinkedHashMap<E, mxCell>  edgeToCellMap       = new LinkedHashMap<E, mxCell>();
	private LinkedHashMap<mxCell, V>  cellToVertexMap     = new LinkedHashMap<mxCell, V>();
	private LinkedHashMap<mxCell, E>  cellToEdgeMap       = new LinkedHashMap<mxCell, E>();


	/*
	 * CONSTRUCTOR
	 */

	public JGraphXAdapter(final ListenableGraph<V, E> graphT)
	{
		super();
		this.graphT = graphT;
		graphT.addGraphListener(this);
		insertJGraphT(graphT);
	}

	/*
	 * MAIN METHOD
	 */
	

	
	/*
	 * METHODS
	 */

	public void addJGraphTVertex(V vertex)
	{

		getModel().beginUpdate();

		try
		{
			mxCell cell = new mxCell(vertex);
			cell.setVertex(true);
			cell.setId(null);
			addCell(cell, defaultParent);
			vertexToCellMap.put(vertex, cell);
			cellToVertexMap.put(cell, vertex);
		} 
		finally
		{
			getModel().endUpdate();
		}
	}

	public void addJGraphTEdge(E edge)
	{

		getModel().beginUpdate();

		try
		{
			V source = graphT.getEdgeSource(edge);
			V target = graphT.getEdgeTarget(edge);              
			mxCell cell = new mxCell(edge);
			cell.setEdge(true);
			cell.setId(null);
			cell.setValue(edge);  //display edge label in visualization (null = no label)
			cell.setGeometry(new mxGeometry());
			cell.getGeometry().setRelative(true);
			addEdge(cell, defaultParent, vertexToCellMap.get(source),  vertexToCellMap.get(target), null); 
			edgeToCellMap.put(edge, cell);
			cellToEdgeMap.put(cell, edge);
		}
		finally
		{
			getModel().endUpdate();
		}
	}

	public LinkedHashMap<V, mxCell> getVertexToCellMap()
	{
		return vertexToCellMap;
	}

	public LinkedHashMap<E, mxCell> getEdgeToCellMap()
	{
		return edgeToCellMap;
	}

	public LinkedHashMap<mxCell, E> getCellToEdgeMap()
	{
		return cellToEdgeMap;
	}

	public LinkedHashMap<mxCell, V> getCellToVertexMap()
	{
		return cellToVertexMap;
	}

	/*
	 * GRAPH LISTENER
	 */

	@Override
	public void vertexAdded(GraphVertexChangeEvent<V> e) {
		addJGraphTVertex(e.getVertex());
	}

	@Override
	public void vertexRemoved(GraphVertexChangeEvent<V> e) {
		mxCell cell = vertexToCellMap.remove(e.getVertex());
		removeCells(new Object[] { cell } );
	}

	@Override
	public void edgeAdded(GraphEdgeChangeEvent<V, E> e) {
		addJGraphTEdge(e.getEdge());
	}

	@Override
	public void edgeRemoved(GraphEdgeChangeEvent<V, E> e) {
		mxCell cell = edgeToCellMap.remove(e.getEdge());
		removeCells(new Object[] { cell } );
	}

	/*
	 * PRIVATE METHODS
	 */

	private void insertJGraphT(Graph<V, E> graphT) {        
		getModel().beginUpdate();
		try {
			for (V vertex : graphT.vertexSet())
				addJGraphTVertex(vertex);
			for (E edge : graphT.edgeSet())
				addJGraphTEdge(edge);
		} finally {
			getModel().endUpdate();
		}

	}

}


