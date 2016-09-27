import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/merge-intervals/
public class QID56 {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        List<Interval> result = new ArrayList<>(intervals);
        Collections.sort(result, new Comparator<Interval>() { // anonymous class
            @Override
            public int compare(Interval i1, Interval i2) {
                return Integer.compare(i1.start, i2.start);
            }
        });

        int i = 0;
        int j;
        int len = result.size();
        while (i < len - 1) {
            j = i + 1;
            while (j < len) {
                Interval tmp = mergeHelper(i, j, result);
                if (tmp == null) { // not overlap
                    j++;
                    continue;
                }
                // overlap
                result.remove(j);
                result.remove(i);
                result.add(i, tmp); // maintain order
                len -= 1;
            }
            i++;
        }
        return result;
    }

    // return null if not overlap, return merged interval if overlap
    private Interval mergeHelper(int i, int j, List<Interval> result){
        Interval a = result.get(i);
        Interval b = result.get(j);
        if (a.start > b.end || b.start > a.end) {
            return null;
        }
        return new Interval(Math.min(a.start, b.start), Math.max(a.end, b.end));
    }

}
