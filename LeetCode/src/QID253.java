import java.util.Arrays;

// https://leetcode.com/problems/meeting-rooms-ii/
public class QID253 {

    // https://discuss.leetcode.com/topic/35253/explanation-of-super-easy-java-solution-beats-98-8-from-pinkfloyda
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals.length;
        }

        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0;
        int idxEnd = 0; // indicates the release of a room
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[idxEnd]) {
                rooms ++;
            } else {
                idxEnd ++;
            }
        }

        return rooms;
    }

    /*
    // version 1: minheap
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
    */
}
