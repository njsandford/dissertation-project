package uk.ac.ncl.v2.njsandford.graphV2.isomorphism;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import org.jgrapht.GraphMapping;
import org.jgrapht.alg.isomorphism.VF2SubgraphIsomorphismInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v2.njsandford.graphV2.graphV2.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Natalie on 17/04/2017.
 */
public class SubgraphMatching {

    private ListenableDirectedGraph<BreakPoint, DefaultEdge> graph;

    private VF2SubgraphIsomorphismInspector isomorphismInspector;

    //private BreakPointComparator breakPointComparator;
    //private EdgeComparator edgeComparator;

    private ListenableDirectedGraph<BreakPoint, DefaultEdge> match;
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> variation;
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> deletion;
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> insertion;
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInQueryNonConsecutive;
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInSubjectNonConsecutive;
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInQueryConsecutive;
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInSubjectConsecutive;
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> inversionInQuery;
    private ListenableDirectedGraph<BreakPoint, DefaultEdge> inversionInSubject;

    public SubgraphMatching(ListenableDirectedGraph<BreakPoint, DefaultEdge> graph) {
        this.graph = graph;
        //this.nodeComparator = new NodeComparator();
        //this.edgeComparator = new EdgeComparator();
        match = match();
        variation = variation();
        deletion = deletion();
        insertion = insertion();
        duplicationInQueryNonConsecutive = duplicationInQueryNonConsecutive();
        duplicationInSubjectNonConsecutive = duplicationInSubjectNonConsecutive();
        duplicationInQueryConsecutive = duplicationInQueryConsecutive();
        duplicationInSubjectConsecutive = duplicationInSubjectConsecutive();
        inversionInQuery = inversionInQuery();
        inversionInSubject = inversionInSubject();
    }

    public List<ListenableDirectedGraph<BreakPoint, DefaultEdge>> findMotif(ListenableDirectedGraph<BreakPoint, DefaultEdge> graph, Motif motif) {

        switch (motif) {
            case MATCH:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, match);//, breakPointComparator, edgeComparator);
                break;
            case VARIATION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, variation);//, breakPointComparator, edgeComparator);
                break;
            case DELETION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, deletion);//, breakPointComparator, edgeComparator);
                break;
            case INSERTION:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, insertion);//, breakPointComparator, edgeComparator);
                break;
            case DUPLICATION_IN_QUERY:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, duplicationInQueryNonConsecutive);//, breakPointComparator, edgeComparator);
                break;
            case DUPLICATION_IN_SUBJECT:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, duplicationInSubjectConsecutive);//, breakPointComparator, edgeComparator);
                break;
            case DUPLICATION_IN_QUERY_CON:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, duplicationInQueryNonConsecutive);//, breakPointComparator, edgeComparator);
                break;
            case DUPLICATION_IN_SUBJECT_CON:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, duplicationInSubjectConsecutive);//, breakPointComparator, edgeComparator);
                break;
            case INVERSION_IN_QUERY:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, inversionInQuery);//, breakPointComparator, edgeComparator);
                break;
            case INVERSION_IN_SUBJECT:
                isomorphismInspector = new VF2SubgraphIsomorphismInspector<>(graph, inversionInSubject);//, breakPointComparator, edgeComparator);
                break;
        }

        Iterator<GraphMapping<BreakPoint, DefaultEdge>> mappingIterator = isomorphismInspector.getMappings();

        return mappingToList(mappingIterator);
    }

    public void printMappingList(List<ListenableDirectedGraph<BreakPoint, DefaultEdge>> mappingList) {
        int count = 1;
        for (ListenableDirectedGraph<BreakPoint, DefaultEdge> subgraph : mappingList) {
            System.out.println(count++ + ": " + subgraph);
        }
    }

    public List<ListenableDirectedGraph<BreakPoint, DefaultEdge>> mappingToList(Iterator<GraphMapping<BreakPoint, DefaultEdge>> mappingIterator) {
        List<ListenableDirectedGraph<BreakPoint, DefaultEdge>> mappingList = new ArrayList<>();

        if (isomorphismInspector.isomorphismExists()) {
            for (Iterator<GraphMapping<BreakPoint, DefaultEdge>> iter = mappingIterator; iter.hasNext();) {
                ListenableDirectedGraph<BreakPoint, DefaultEdge> subgraphMatch = getMappingSubgraph(iter.next());
                mappingList.add(subgraphMatch);
            }
        }

        return mappingList;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> getMappingSubgraph(GraphMapping<BreakPoint, DefaultEdge> mapping) {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> subgraph = new ListenableDirectedGraph<>(DefaultEdge.class);

        Set<BreakPoint> vertexSet = graph.vertexSet();

        // For each node in the set of vertices, if a mapping exists for that node, put the node into the subgraph
        for (BreakPoint node : vertexSet) {
            BreakPoint u = mapping.getVertexCorrespondence(node, true);

            if (u != null) {
                subgraph.addVertex(node);
            }
        }

        // Add edges, if they exist.
        for (BreakPoint node : subgraph.vertexSet()) {
            for (BreakPoint node2 : subgraph.vertexSet()) {
                if (graph.containsEdge(node, node2)) {
                    subgraph.addEdge(node, node2, graph.getEdge(node, node2));
                }
            }
        }

        return subgraph;
    }

    public enum Motif {

        MATCH, VARIATION, INSERTION, DELETION, DUPLICATION_IN_QUERY_CON, DUPLICATION_IN_SUBJECT_CON, DUPLICATION_IN_QUERY, DUPLICATION_IN_SUBJECT, INVERSION_IN_QUERY, INVERSION_IN_SUBJECT;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> match() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> match = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 3);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 3);

        match.addVertex(qStart);
        match.addVertex(qEnd);
        match.addVertex(sStart);
        match.addVertex(sEnd);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        match.addEdge(qStart, sStart, startCorrespond);
        match.addEdge(qEnd, sEnd, endCorrespond);

        DirectedEdge queryRegion = new Region(SequenceType.QUERY, qStart.getSeqId(), 1, 2, 0, 0, 0, 0);
        DirectedEdge subjectRegion = new Region(SequenceType.SUBJECT, sStart.getSeqId(), 1, 2, 0, 0, 0, 0);

        match.addEdge(qStart, qEnd, queryRegion);
        match.addEdge(sStart, sEnd, subjectRegion);

        return match;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> variation() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> variation = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 3);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 3);

        variation.addVertex(qStart);
        variation.addVertex(qEnd);
        variation.addVertex(sStart);
        variation.addVertex(sEnd);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        variation.addEdge(qStart, sStart, startCorrespond);
        variation.addEdge(qEnd, sEnd, endCorrespond);

        DirectedEdge queryGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.QUERY, 1, 2);
        DirectedEdge subjectGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.SUBJECT, 1, 2);

        variation.addEdge(qStart, qEnd, queryGap);
        variation.addEdge(sStart, sEnd, subjectGap);

        return variation;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> insertion() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> insertion = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 3);

        insertion.addVertex(qStart);
        insertion.addVertex(sStart);
        insertion.addVertex(sEnd);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        insertion.addEdge(qStart, sStart, startCorrespond);
        insertion.addEdge(qStart, sEnd, endCorrespond);

        DirectedEdge subjectGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.SUBJECT, 1, 2);

        insertion.addEdge(sStart, sEnd, subjectGap);

        return insertion;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> deletion() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> deletion = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 3);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);

        deletion.addVertex(qStart);
        deletion.addVertex(qEnd);
        deletion.addVertex(sStart);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        deletion.addEdge(qStart, sStart, startCorrespond);
        deletion.addEdge(qStart, sStart, endCorrespond);

        DirectedEdge queryGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.QUERY, 1, 2);

        deletion.addEdge(qStart, qEnd, queryGap);

        return deletion;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInQueryNonConsecutive() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> duplication = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStartLhs = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEndLhs = new BreakPoint(SequenceType.QUERY, "queryTest", 3);
        BreakPoint qStartRhs = new BreakPoint(SequenceType.QUERY, "queryTest", 4);
        BreakPoint qEndRhs = new BreakPoint(SequenceType.QUERY, "queryTest", 6);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 3);

        duplication.addVertex(qStartLhs);
        duplication.addVertex(qEndLhs);
        duplication.addVertex(qStartRhs);
        duplication.addVertex(qEndRhs);
        duplication.addVertex(sStart);
        duplication.addVertex(sEnd);

        CorrespondingEdge startCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge startCorrespondRhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondRhs = new CorrespondingEdge();

        duplication.addEdge(qStartLhs, sStart, startCorrespondLhs);
        duplication.addEdge(qStartLhs, sStart, endCorrespondLhs);
        duplication.addEdge(qStartRhs, sStart, startCorrespondRhs);
        duplication.addEdge(qEndRhs, sEnd, endCorrespondRhs);

        DirectedEdge queryRegionLhs = new Region(SequenceType.QUERY, "queryTest", 1, 2, 0,0, 0, 0);
        DirectedEdge queryRegionRhs = new Region(SequenceType.QUERY, "queryTest", 4, 5, 0, 0, 0, 0);
        DirectedEdge subjectRegion = new Region(SequenceType.SUBJECT, "subjectTest", 1, 2, 0, 0, 0, 0);

        duplication.addEdge(qStartLhs, qEndLhs, queryRegionLhs);
        duplication.addEdge(qStartRhs, qEndRhs, queryRegionRhs);
        duplication.addEdge(sStart, sEnd, subjectRegion);

        return duplication;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInQueryConsecutive() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> duplication = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qLeft = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qMiddle = new BreakPoint(SequenceType.QUERY, "queryTest", 3);
        BreakPoint qRight = new BreakPoint(SequenceType.QUERY, "queryTest", 5);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 3);

        duplication.addVertex(qLeft);
        duplication.addVertex(qMiddle);
        duplication.addVertex(qRight);
        duplication.addVertex(sStart);
        duplication.addVertex(sEnd);

        CorrespondingEdge startCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge startCorrespondRhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondRhs = new CorrespondingEdge();

        duplication.addEdge(qLeft, sStart, startCorrespondLhs);
        duplication.addEdge(qMiddle, sEnd, endCorrespondLhs);
        duplication.addEdge(qMiddle, sStart, startCorrespondRhs);
        duplication.addEdge(qRight, sEnd, endCorrespondRhs);

        DirectedEdge queryRegionLhs = new Region(SequenceType.QUERY, "queryTest", 1, 2, 0,0, 0, 0);
        DirectedEdge queryRegionRhs = new Region(SequenceType.QUERY, "queryTest", 3, 4, 0, 0, 0, 0);
        DirectedEdge subjectRegion = new Region(SequenceType.SUBJECT, "subjectTest", 1, 2, 0, 0, 0, 0);

        duplication.addEdge(qLeft, qMiddle, queryRegionLhs);
        duplication.addEdge(qMiddle, qRight, queryRegionRhs);
        duplication.addEdge(sStart, sEnd, subjectRegion);

        return duplication;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInSubjectNonConsecutive() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> duplication = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 3);

        BreakPoint sStartLhs = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEndLhs = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 3);
        BreakPoint sStartRhs = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 4);
        BreakPoint sEndRhs = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 6);

        duplication.addVertex(qStart);
        duplication.addVertex(qEnd);
        duplication.addVertex(sStartLhs);
        duplication.addVertex(sEndLhs);
        duplication.addVertex(sStartRhs);
        duplication.addVertex(sEndRhs);

        CorrespondingEdge startCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge startCorrespondRhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondRhs = new CorrespondingEdge();

        duplication.addEdge(qStart, sStartLhs, startCorrespondLhs);
        duplication.addEdge(qEnd, sEndLhs, endCorrespondLhs);
        duplication.addEdge(qStart, sStartRhs, startCorrespondRhs);
        duplication.addEdge(qEnd, sEndRhs, endCorrespondRhs);

        DirectedEdge queryRegion = new Region(SequenceType.QUERY, "queryTest", 1, 2, 0, 0, 0, 0);
        DirectedEdge subjectRegionLhs = new Region(SequenceType.SUBJECT, "subjectTest", 1, 2, 0, 0, 0, 0);
        DirectedEdge subjectRegionRhs = new Region(SequenceType.SUBJECT, "subjectTest", 4, 5, 0, 0, 0, 0);

        duplication.addEdge(qStart, qEnd, queryRegion);
        duplication.addEdge(sStartLhs, sEndLhs, subjectRegionLhs);
        duplication.addEdge(sStartRhs, sEndRhs, subjectRegionRhs);

        return duplication;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInSubjectConsecutive() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> duplication = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 3);

        BreakPoint sLeft = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sMiddle = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 3);
        BreakPoint sRight = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 5);

        duplication.addVertex(qStart);
        duplication.addVertex(qEnd);
        duplication.addVertex(sLeft);
        duplication.addVertex(sMiddle);
        duplication.addVertex(sRight);

        CorrespondingEdge startCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge startCorrespondRhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondRhs = new CorrespondingEdge();

        duplication.addEdge(qStart, sLeft, startCorrespondLhs);
        duplication.addEdge(qEnd, sMiddle, endCorrespondLhs);
        duplication.addEdge(qStart, sMiddle, startCorrespondRhs);
        duplication.addEdge(qEnd, sRight, endCorrespondRhs);

        DirectedEdge queryRegionLhs = new Region(SequenceType.QUERY, "queryTest", 1, 2, 0,0, 0, 0);
        DirectedEdge queryRegionRhs = new Region(SequenceType.SUBJECT, "subjectTest", 1, 2, 0, 0, 0, 0);
        DirectedEdge subjectRegion = new Region(SequenceType.SUBJECT, "subjectTest", 3, 4, 0, 0, 0, 0);

        duplication.addEdge(qStart, qEnd, queryRegionLhs);
        duplication.addEdge(sLeft, sMiddle, queryRegionRhs);
        duplication.addEdge(sMiddle, sRight, subjectRegion);

        return duplication;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> inversionInQuery() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> inversion = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 3);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 1);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 3);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);

        inversion.addVertex(qStart);
        inversion.addVertex(qEnd);
        inversion.addVertex(sStart);
        inversion.addVertex(sEnd);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        inversion.addEdge(qStart, sStart, startCorrespond);
        inversion.addEdge(qStart, sStart, endCorrespond);

        DirectedEdge queryRegion = new Region(SequenceType.QUERY, "testQuery", 2, 1, 0, 0, 0, 0);
        DirectedEdge subjectRegion = new Region(SequenceType.SUBJECT, "testSubject", 1, 2, 0 ,0 ,0 ,0);

        inversion.addEdge(qStart, qEnd, queryRegion);
        inversion.addEdge(sStart, sEnd, subjectRegion);

        return inversion;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> inversionInSubject() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> inversion = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 3);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 3);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);

        inversion.addVertex(qStart);
        inversion.addVertex(qEnd);
        inversion.addVertex(sStart);
        inversion.addVertex(sEnd);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        inversion.addEdge(qStart, sStart, startCorrespond);
        inversion.addEdge(qStart, sStart, endCorrespond);

        DirectedEdge queryRegion = new Region(SequenceType.QUERY, "testQuery", 1, 2, 0, 0, 0, 0);
        DirectedEdge subjectRegion = new Region(SequenceType.SUBJECT, "testSubject", 2, 1, 0 ,0 ,0 ,0);

        inversion.addEdge(qStart, qEnd, queryRegion);
        inversion.addEdge(sStart, sEnd, subjectRegion);

        return inversion;
    }
}
