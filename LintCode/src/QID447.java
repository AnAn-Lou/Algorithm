// http://www.lintcode.com/en/problem/search-in-a-big-sorted-array/
/**
 * Definition of ArrayReader:
 * 
 * class ArrayReader {
 *      // get the number at index, return -1 if not exists.
 *      public int get(int index);
 * }
 */
public class QID447 {

    // otherwise report error in IDE
    private class ArrayReader {
        // get the number at index, return -1 if not exists.
         public int get(int index){
             return -1;
         }
    }
    /**
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return : An integer which is the index of the target number
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
        int index = 0;
        while (reader.get(index) != -1 && reader.get(index) < target) {
            index = index * 2 + 1;
        }
        
        /* wrong
         * test case: arr = [1,2,3,4,5,6] target = 6
        if (reader.get(index) == Integer.MAX_VALUE) {
            return -1;
        }
        */ 
        
        int low = (index - 1) / 2;
        int high = index;
        int mid;
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (reader.get(mid) >= target){
                high = mid;
            } else {
                low = mid;
            }
        }
        
        if (reader.get(low) == target) {
            return low;
        } else if (reader.get(high) == target){
            return high;
        } else {
            return -1;
        }
    }
}
