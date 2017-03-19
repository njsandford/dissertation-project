package uk.ac.ncl.njsandford.isomorphism;

import org.jgrapht.graph.ListenableDirectedGraph;
import uk.ac.ncl.njsandford.graph.*;

/**
 * Created by Natalie on 28/02/2017.
 */
public class SubGraphs {

    public ListenableDirectedGraph<Node, SequenceEdge> match() {
        ListenableDirectedGraph<Node, SequenceEdge> match = new ListenableDirectedGraph<Node, SequenceEdge>(SequenceEdge.class);

        QueryNode query = new QueryNode("qStart", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode subject = new SubjectNode("sStart", 0, 0, 0, 0.0, 0.0, 0.0);

        match.addVertex(query);
        match.addVertex(subject);

        SequenceEdge edge = new SequenceEdge(SequenceEdge.Type.MATCH);

        match.addEdge(query, subject, edge);

        return match;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> variation() {
        ListenableDirectedGraph<Node, SequenceEdge> variation = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode qStart = new QueryNode("qStart", 0, 0, 0, 0.0, 0.0, 0.0);
        QueryNode qEnd = new QueryNode("qEnd", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode sStart = new SubjectNode("sStart", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode sEnd = new SubjectNode("sEnd", 0, 0, 0, 0.0, 0.0, 0.0);

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

        QueryNode qStart = new QueryNode("qStart", 0, 0, 0, 0.0, 0.0, 0.0);
        QueryNode qEnd = new QueryNode("qEnd", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode sStart = new SubjectNode("sStart", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode sEnd = new SubjectNode("sEnd", 0, 0, 0, 0.0, 0.0, 0.0);

        insertion.addVertex(qStart);
        insertion.addVertex(qEnd);
        insertion.addVertex(sStart);
        insertion.addVertex(sEnd);

        SequenceEdge qGap = new SequenceEdge(SequenceEdge.Type.GAP);
        SequenceEdge sMatch = new SequenceEdge(SequenceEdge.Type.NO_GAP);
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

        QueryNode qStart = new QueryNode("qStart", 0, 0, 0, 0.0, 0.0, 0.0);
        QueryNode qEnd = new QueryNode("qEnd", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode sStart = new SubjectNode("sStart", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode sEnd = new SubjectNode("sEnd", 0, 0, 0, 0.0, 0.0, 0.0);

        deletion.addVertex(qStart);
        deletion.addVertex(qEnd);
        deletion.addVertex(sStart);
        deletion.addVertex(sEnd);

        SequenceEdge qMatch = new SequenceEdge(SequenceEdge.Type.NO_GAP);
        SequenceEdge sGap = new SequenceEdge(SequenceEdge.Type.GAP);
        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        deletion.addEdge(qStart, qEnd, qMatch);
        deletion.addEdge(sStart, sEnd, sGap);
        deletion.addEdge(qStart, sStart, startEdge);
        deletion.addEdge(qEnd, sEnd, endEdge);

        return deletion;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> inversion() {
        ListenableDirectedGraph<Node, SequenceEdge> inversion = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode query = new QueryNode("query", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode subject = new SubjectNode("subject", 0, 0, 0, 0.0, 0.0, 0.0);

        inversion.addVertex(query);
        inversion.addVertex(subject);

        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        inversion.addEdge(query, subject, startEdge);
        inversion.addEdge(query, subject, endEdge);

        return inversion;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> duplicationInQuery() {
        ListenableDirectedGraph<Node, SequenceEdge> duplication = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode query1 = new QueryNode("query", 0, 0, 0, 0.0, 0.0, 0.0);
        QueryNode query2 = new QueryNode("query", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode subject = new SubjectNode("subject", 0, 0, 0, 0.0, 0.0, 0.0);

        duplication.addVertex(query1);
        duplication.addVertex(query2);
        duplication.addVertex(subject);

        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        duplication.addEdge(query1, subject, startEdge);
        duplication.addEdge(query2, subject, endEdge);

        return duplication;
    }

    public ListenableDirectedGraph<Node, SequenceEdge> duplicationInSearch() {
        ListenableDirectedGraph<Node, SequenceEdge> duplication = new ListenableDirectedGraph<>(SequenceEdge.class);

        QueryNode query = new QueryNode("query", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode subject1 = new SubjectNode("subject1", 0, 0, 0, 0.0, 0.0, 0.0);
        SubjectNode subject2 = new SubjectNode("subject2", 0, 0, 0, 0.0, 0.0, 0.0);

        duplication.addVertex(query);
        duplication.addVertex(subject1);
        duplication.addVertex(subject2);

        SequenceEdge startEdge = new SequenceEdge(SequenceEdge.Type.MATCH);
        SequenceEdge endEdge = new SequenceEdge(SequenceEdge.Type.MATCH);

        duplication.addEdge(query, subject1, startEdge);
        duplication.addEdge(query, subject2, endEdge);

        return duplication;
    }
}
