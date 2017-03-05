package uk.ac.ncl.njsandford;

import org.jgrapht.experimental.isomorphism.*;

/**
 * Created by Natalie on 02/03/2017.
 */
public class SearchAlgorithms {

    public void subgraphSearch() {
        GraphIsomorphismInspector gii = new GraphIsomorphismInspector() {
            @Override
            public boolean isIsomorphic() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
        };
    }

}
