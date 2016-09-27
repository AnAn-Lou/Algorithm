import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/meeting-rooms-ii/
public class QID253 {
    public int minMeetingRooms(Interval[] intervals) {

        if (intervals == null || intervals.length <= 1) {
            return intervals.length;
        }

        // sort, to handle cases like [[4,9], [12,14], [9,12]]
        Arrays.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        List<Integer> roomEndList = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int j;
            for (j = 0; j < roomEndList.size(); j++) {
                if (intervals[i].start >= roomEndList.get(j)) { // if old room available, update this room schedule
                    roomEndList.remove(j);
                    roomEndList.add(intervals[i].end);
                    break;
                }
            }

            // no old room available, add a new room
            if (j == roomEndList.size()) {
                roomEndList.add(intervals[i].end);
            }
        }

        return roomEndList.size();
    }
}
