package uk.ac.ncl.v2.njsandford.graphV2.isomorphism;

import com.sun.org.apache.regexp.internal.RE;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v2.njsandford.graphV2.graphV2.*;

/**
 * Created by Natalie on 17/04/2017.
 */
public class SubgraphMatching {


    public ListenableDirectedGraph<BreakPoint, DefaultEdge> match() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> match = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 2);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 2);

        match.addVertex(qStart);
        match.addVertex(qEnd);
        match.addVertex(sStart);
        match.addVertex(sEnd);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        match.addEdge(qStart, sStart, startCorrespond);
        match.addEdge(qEnd, sEnd, endCorrespond);

        DirectedEdge queryRegion = new Region(SequenceType.QUERY, qStart.getSeqId(), qStart.getPosition(), qEnd.getPosition(), 0, 0, 0, 0);
        DirectedEdge subjectRegion = new Region(SequenceType.SUBJECT, sStart.getSeqId(), sStart.getPosition(), sEnd.getPosition(), 0, 0, 0, 0);

        match.addEdge(qStart, qEnd, queryRegion);
        match.addEdge(sStart, sEnd, subjectRegion);

        return match;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> variation() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> variation = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 2);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 2);

        variation.addVertex(qStart);
        variation.addVertex(qEnd);
        variation.addVertex(sStart);
        variation.addVertex(sEnd);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        variation.addEdge(qStart, sStart, startCorrespond);
        variation.addEdge(qEnd, sEnd, endCorrespond);

        DirectedEdge queryGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.QUERY, qStart.getPosition(), qEnd.getPosition());
        DirectedEdge subjectGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.SUBJECT, sStart.getPosition(), sEnd.getPosition());

        variation.addEdge(qStart, qEnd, queryGap);
        variation.addEdge(sStart, sEnd, subjectGap);

        return variation;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> insertion() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> insertion = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 2);

        insertion.addVertex(qStart);
        insertion.addVertex(sStart);
        insertion.addVertex(sEnd);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        insertion.addEdge(qStart, sStart, startCorrespond);
        insertion.addEdge(qStart, sEnd, endCorrespond);

        DirectedEdge subjectGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.SUBJECT, sStart.getPosition(), sEnd.getPosition());

        insertion.addEdge(sStart, sEnd, subjectGap);

        return insertion;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> deletion() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> deletion = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStart = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEnd = new BreakPoint(SequenceType.QUERY, "queryTest", 2);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);

        deletion.addVertex(qStart);
        deletion.addVertex(qEnd);
        deletion.addVertex(sStart);

        CorrespondingEdge startCorrespond = new CorrespondingEdge();
        CorrespondingEdge endCorrespond = new CorrespondingEdge();

        deletion.addEdge(qStart, sStart, startCorrespond);
        deletion.addEdge(qStart, sStart, endCorrespond);

        DirectedEdge queryGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.QUERY, qStart.getPosition(), qEnd.getPosition());

        deletion.addEdge(qStart, qEnd, queryGap);

        return deletion;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInQueryNonConsecutive() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> duplication = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStartLhs = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEndLhs = new BreakPoint(SequenceType.QUERY, "queryTest", 2);
        BreakPoint qStartRhs = new BreakPoint(SequenceType.QUERY, "queryTest", 4);
        BreakPoint qEndRhs = new BreakPoint(SequenceType.QUERY, "queryTest", 6);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 2);

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

        // Do not inlude a gap edge in the motif.
        //DirectedEdge queryGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.QUERY, qEndLhs.getPosition(), qStartRhs.getPosition());

        DirectedEdge queryRegionLhs = new Region(SequenceType.QUERY, "queryTest", qStartLhs.getPosition(), qEndLhs.getPosition(), 0,0, 0, 0);
        DirectedEdge queryRegionRhs = new Region(SequenceType.QUERY, "queryTest", qStartRhs.getPosition(), qEndRhs.getPosition(), 0, 0, 0, 0);
        DirectedEdge subjectRegion = new Region(SequenceType.SUBJECT, "subjectTest", sStart.getPosition(), sEnd.getPosition(), 0, 0, 0, 0);

        duplication.addEdge(qStartLhs, qEndLhs, queryRegionLhs);
        duplication.addEdge(qStartRhs, qEndRhs, queryRegionRhs);
        duplication.addEdge(sStart, sEnd, subjectRegion);

        return duplication;
    }

    public ListenableDirectedGraph<BreakPoint, DefaultEdge> duplicationInQueryConsecutive() {
        ListenableDirectedGraph<BreakPoint, DefaultEdge> duplication = new ListenableDirectedGraph<>(DefaultEdge.class);

        BreakPoint qStartLhs = new BreakPoint(SequenceType.QUERY, "queryTest", 1);
        BreakPoint qEndLhsStartRhs = new BreakPoint(SequenceType.QUERY, "queryTest", 3);
        BreakPoint qEndRhs = new BreakPoint(SequenceType.QUERY, "queryTest", 4);

        BreakPoint sStart = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 1);
        BreakPoint sEnd = new BreakPoint(SequenceType.SUBJECT, "subjectTest", 2);

        duplication.addVertex(qStartLhs);
        duplication.addVertex(qEndLhsStartRhs);
        duplication.addVertex(qEndRhs);
        duplication.addVertex(sStart);
        duplication.addVertex(sEnd);

        CorrespondingEdge startCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondLhs = new CorrespondingEdge();
        CorrespondingEdge startCorrespondRhs = new CorrespondingEdge();
        CorrespondingEdge endCorrespondRhs = new CorrespondingEdge();

        duplication.addEdge(qStartLhs, sStart, startCorrespondLhs);
        duplication.addEdge(qStartLhs, sStart, endCorrespondLhs);
        duplication.addEdge(qEndLhsStartRhs, sStart, startCorrespondRhs);
        duplication.addEdge(qEndRhs, sEnd, endCorrespondRhs);

        // Do not inlude a gap edge in the motif.
        //DirectedEdge queryGap = new DirectedEdge(DirectedEdge.Type.GAP, SequenceType.QUERY, qEndLhs.getPosition(), qStartRhs.getPosition());

        //DirectedEdge queryRegionLhs = new Region(SequenceType.QUERY, "queryTest", qStartLhs.getPosition(), qEndLhs.getPosition(), 0,0, 0, 0);
        //DirectedEdge queryRegionRhs = new Region(SequenceType.QUERY, "queryTest", qStartRhs.getPosition(), qEndRhs.getPosition(), 0, 0, 0, 0);
        //DirectedEdge subjectRegion = new Region(SequenceType.SUBJECT, "subjectTest", sStart.getPosition(), sEnd.getPosition(), 0, 0, 0, 0);

        //duplication.addEdge(qStartLhs, qEndLhs, queryRegionLhs);
        //duplication.addEdge(qStartRhs, qEndRhs, queryRegionRhs);
        //duplication.addEdge(sStart, sEnd, subjectRegion);

        return duplication;
    }
}
