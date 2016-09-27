import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/meeting-rooms/
public class QID252 {
    public boolean canAttendMeetings(Interval[] intervals) {

        if (intervals == null || intervals.length <= 1) {
            return true;
        }

        // sort
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) { // according to test cases, [[1,13],[13,15]] return true
                return false;
            }
        }

        return true;
    }
}
