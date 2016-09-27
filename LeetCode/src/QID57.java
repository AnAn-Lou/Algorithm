import java.util.List;

// https://leetcode.com/problems/insert-interval/
public class QID57 {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        int toMergeStart; // the start index of the intervals to be merged
        int toMergeEnd; // the end index of the intervals to be merged.

        int newStart; // start of the new (merged) Interval
        int newEnd; // end of the new (merged) Interval

        // find toMergeStart and newStart
        int i;
        for (i = 0; i < intervals.size(); i++) {
            if (newInterval.start < intervals.get(i).start) {
                break;
            }
        }
        if (i > 0 && newInterval.start <= intervals.get(i - 1).end) {
            toMergeStart = i - 1;
            newStart = intervals.get(i - 1).start;
        } else {
            toMergeStart = i;
            newStart = newInterval.start;
        }

        // find toMergeEnd and newEnd
        for (i = Math.max(0, i - 1); i < intervals.size(); i++) {
            if (newInterval.end < intervals.get(i).end) {
                break;
            }
        }
        if (i != intervals.size() && newInterval.end >= intervals.get(i).start) {
            toMergeEnd = i;
            newEnd = intervals.get(i).end;
        } else {
            toMergeEnd = i - 1;
            newEnd = newInterval.end;
        }

        // merge & insert
        for (i = toMergeEnd; i >=toMergeStart; i--) {
            intervals.remove(i);
        }
        newInterval = new Interval(newStart, newEnd);
        intervals.add(toMergeStart, newInterval);

        return intervals;
    }
}
