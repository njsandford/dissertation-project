package uk.ac.ncl.v2.njsandford.graphV2.graphV2;

import java.util.Comparator;

/**
 * Created by Natalie on 17/04/2017.
 */
public class BreakPointComparator implements Comparator<BreakPoint> {
s
    @Override
    public int compare(BreakPoint o1, BreakPoint o2) {
        return o1.getType().compareTo(o2.getType());
    }
}
