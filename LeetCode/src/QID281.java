import java.util.List;

// https://leetcode.com/problems/zigzag-iterator/
public class QID281 {

    // maintain 0 <= p1 - p2 <= 1 all the time, unless p1 == v1.size() or p2 == v2.size()
    // use a queue when extended to k 1d vectors
    int p1;
    int p2;
    List<Integer> v1;
    List<Integer> v2;

    public QID281(List<Integer> v1, List<Integer> v2) {
        p1 = 0;
        p2 = 0;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        int val;
        int size1 = v1.size();
        int size2 = v2.size();

        if (p1 >= size1) { // v1 exhausted
            val = v2.get(p2);
            p2 ++;
        } else if (p2 >= size2) { // v2 exhausted
            val = v1.get(p1);
            p1 ++;
        } else if (p1 == p2) {
            val = v1.get(p1);
            p1 ++;
        } else {
            val = v2.get(p2);
            p2 ++;
        }

        return val;
    }

    public boolean hasNext() {
        if (p1 < v1.size() || p2 < v2.size()) {
            return true;
        }
        return false;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
