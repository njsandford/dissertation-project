package uk.ac.ncl.v1.njsandford.isomorphism;

import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.v1.njsandford.graph.Node;
import uk.ac.ncl.v1.njsandford.graph.QueryNode;
import uk.ac.ncl.v1.njsandford.graph.SequenceEdge;
import uk.ac.ncl.v1.njsandford.graph.SubjectNode;

/**
 * Created by Natalie on 28/02/2017.
 */
public class SubGraphs {

    public ListenableDirectedGraph<Node, SequenceEdge> match() {
        ListenableDirectedGraph<Node, SequenceEdge> match = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

        QueryNode query = new QueryNode("qStart", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode subject = new SubjectNode("sStart", 1, 2, 0, 0.0, 0.0, 0.0);

        match.addVertex(query);
        match.addVertex(subject);

        SequenceEdge edge = new SequenceEdge(SequenceEdge.Type.MATCH);

        match.addEdge(query, subject, edge);

        return match;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> variation() {
        ListenableDirectedGraph<Node, SequenceEdge> variation = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode qStart = new QueryNode("qStart", 1, 2, 0, 0.0, 0.0, 0.0);
        QueryNode qEnd = new QueryNode("qEnd", 3, 4, 0, 0.0, 0.0, 0.0);
        SubjectNode sStart = new SubjectNode("sStart", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode sEnd = new SubjectNode("sEnd", 3, 4, 0, 0.0, 0.0, 0.0);

        variation.addVertex(qStart);
        variation.addVertex(qEnd);
        variation.addVertex(sStart);
        variation.addVertex(sEnd);

        SequenceEdge qGap = new SequenceEdge(SequenceEdge.Type.GAP);
        SequenceEdge sGap = new SequenceEdge(SequenceEdge.Type.GAP);
        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        variation.addEdge(qStart, qEnd, qGap);
        variation.addEdge(sStart, sEnd, sGap);
        variation.addEdge(qStart, sStart, startEdge);
        variation.addEdge(qEnd, sEnd, endEdge);

        return variation;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> insertion() {
        ListenableDirectedGraph<Node, SequenceEdge> insertion = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode qStart = new QueryNode("qStart", 1, 2, 0, 0.0, 0.0, 0.0);
        QueryNode qEnd = new QueryNode("qEnd", 3, 4, 0, 0.0, 0.0, 0.0);
        SubjectNode sStart = new SubjectNode("sStart", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode sEnd = new SubjectNode("sEnd", 3, 4, 0, 0.0, 0.0, 0.0);

        insertion.addVertex(qStart);
        insertion.addVertex(qEnd);
        insertion.addVertex(sStart);
        insertion.addVertex(sEnd);

        SequenceEdge qGap = new SequenceEdge(SequenceEdge.Type.NO_GAP);
        SequenceEdge sMatch = new SequenceEdge(SequenceEdge.Type.GAP);
        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        insertion.addEdge(qStart, qEnd, qGap);
        insertion.addEdge(sStart, sEnd, sMatch);
        insertion.addEdge(qStart, sStart, startEdge);
        insertion.addEdge(qEnd, sEnd, endEdge);

        return insertion;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> deletion() {
        ListenableDirectedGraph<Node, SequenceEdge> deletion = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode qStart = new QueryNode("qStart", 1, 2, 0, 0.0, 0.0, 0.0);
        QueryNode qEnd = new QueryNode("qEnd", 3, 4, 0, 0.0, 0.0, 0.0);
        SubjectNode sStart = new SubjectNode("sStart", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode sEnd = new SubjectNode("sEnd", 3, 4, 0, 0.0, 0.0, 0.0);

        deletion.addVertex(qStart);
        deletion.addVertex(qEnd);
        deletion.addVertex(sStart);
        deletion.addVertex(sEnd);

        SequenceEdge qMatch = new SequenceEdge(SequenceEdge.Type.GAP);
        SequenceEdge sGap = new SequenceEdge(SequenceEdge.Type.NO_GAP);
        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        deletion.addEdge(qStart, qEnd, qMatch);
        deletion.addEdge(sStart, sEnd, sGap);
        deletion.addEdge(qStart, sStart, startEdge);
        deletion.addEdge(qEnd, sEnd, endEdge);

        return deletion;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> inversionInQuery() {
        ListenableDirectedGraph<Node, SequenceEdge> inversion = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode query = new QueryNode("query", 2, 1, 0, 0.0, 0.0, 0.0);
        SubjectNode subject = new SubjectNode("subject", 1, 2, 0, 0.0, 0.0, 0.0);

        inversion.addVertex(query);
        inversion.addVertex(subject);

        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        inversion.addEdge(query, subject, startEdge);

        return inversion;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> inversionInSubject() {
        ListenableDirectedGraph<Node, SequenceEdge> inversion = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode query = new QueryNode("query", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode subject = new SubjectNode("subject", 2, 1, 0, 0.0, 0.0, 0.0);

        inversion.addVertex(query);
        inversion.addVertex(subject);

        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        inversion.addEdge(query, subject, startEdge);

        return inversion;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> duplicationInQuery() {
        ListenableDirectedGraph<Node, SequenceEdge> duplication = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode query1 = new QueryNode("query", 1, 2, 0, 0.0, 0.0, 0.0);
        QueryNode query2 = new QueryNode("query", 3, 4, 0, 0.0, 0.0, 0.0);
        SubjectNode subject = new SubjectNode("subject", 1, 2, 0, 0.0, 0.0, 0.0);

        duplication.addVertex(query1);
        duplication.addVertex(query2);
        duplication.addVertex(subject);

        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        duplication.addEdge(query1, subject, startEdge);
        duplication.addEdge(query2, subject, endEdge);

        return duplication;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> consecutiveDuplicationInQuery() {
        ListenableDirectedGraph<Node, SequenceEdge> duplication = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode query1 = new QueryNode("query", 1, 2, 0, 0.0, 0.0, 0.0);
        QueryNode query2 = new QueryNode("query", 3, 4, 0, 0.0, 0.0, 0.0);
        SubjectNode subject = new SubjectNode("subject", 1, 2, 0, 0.0, 0.0, 0.0);

        duplication.addVertex(query1);
        duplication.addVertex(query2);
        duplication.addVertex(subject);

        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge defaultEdge = new SequenceEdge(SequenceEdge.Type.DEFAULT);

        duplication.addEdge(query1, subject, startEdge);
        duplication.addEdge(query2, subject, endEdge);
        duplication.addEdge(query1, query2, defaultEdge);

        return duplication;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> duplicationInSearch() {
        ListenableDirectedGraph<Node, SequenceEdge> duplication = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode query = new QueryNode("query", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode subject1 = new SubjectNode("subject1", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode subject2 = new SubjectNode("subject2", 3, 4, 0, 0.0, 0.0, 0.0);

        duplication.addVertex(query);
        duplication.addVertex(subject1);
        duplication.addVertex(subject2);

        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        duplication.addEdge(query, subject1, startEdge);
        duplication.addEdge(query, subject2, endEdge);

        return duplication;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> consecutiveDuplicationInSearch() {
        ListenableDirectedGraph<Node, SequenceEdge> duplication = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode query = new QueryNode("query", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode subject1 = new SubjectNode("subject1", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode subject2 = new SubjectNode("subject2", 3, 4, 0, 0.0, 0.0, 0.0);

        duplication.addVertex(query);
        duplication.addVertex(subject1);
        duplication.addVertex(subject2);

        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge defaultEdge = new SequenceEdge(SequenceEdge.Type.DEFAULT);

        duplication.addEdge(query, subject1, startEdge);
        duplication.addEdge(query, subject2, endEdge);
        duplication.addEdge(subject1, subject2, defaultEdge);

        return duplication;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> dummyMotif() {
        ListenableDirectedGraph<Node, SequenceEdge> dummy = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode qStart = new QueryNode("qStart", 1, 2, 0, 0.0, 0.0, 0.0);
        QueryNode qEnd = new QueryNode("qEnd", 3, 4, 0, 0.0, 0.0, 0.0);
        SubjectNode sStart = new SubjectNode("sStart", 1, 2, 0, 0.0, 0.0, 0.0);
        SubjectNode sEnd = new SubjectNode("sEnd", 3, 4, 0, 0.0, 0.0, 0.0);

        dummy.addVertex(qStart);
        dummy.addVertex(qEnd);
        dummy.addVertex(sStart);
        dummy.addVertex(sEnd);

        SequenceEdge qMatch = new SequenceEdge(SequenceEdge.Type.NO_GAP);
        SequenceEdge sGap = new SequenceEdge(SequenceEdge.Type.GAP);
        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge overlap1 = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge overlap2 = new SequenceEdge(SequenceEdge.Type.MATCH);

        dummy.addEdge(qStart, qEnd, qMatch);
        dummy.addEdge(sStart, sEnd, sGap);
        dummy.addEdge(qStart, sStart, startEdge);
        dummy.addEdge(qEnd, sEnd, endEdge);
        dummy.addEdge(qStart, sEnd, overlap1);
        dummy.addEdge(sStart, qEnd, overlap2);

        return dummy;
    }
}
