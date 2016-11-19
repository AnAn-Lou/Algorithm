// https://leetcode.com/problems/majority-element/
public class QID169 {
    // reference:
    // https://discuss.leetcode.com/topic/17446/6-suggested-solutions-in-c-with-explanations
    // https://www.youtube.com/watch?v=ASSaZ_y-ITQ

    /*
    // Map time O(n) space O(n)
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i],map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
            if (map.get(nums[i]) > nums.length / 2) {
                return nums[i];
            }
        }
        return -1;
    }

    // sort time O(nlogn) space O(1)
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        // assume the majority element always exist in the array
        return nums[nums.length / 2];
    }

    // moore voting algorithm time O(n) space O(1)
    public int majorityElement(int[] nums) {
        int element = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == element) {
                count ++;
            } else {
                if (count > 0) {
                    count --;
                } else {
                    element = nums[i];
                    count = 1;
                }
            }
        }
        return element;
    }
    */
    // bit manipulation time O(n) space O(1)
    public int majorityElement(int[] nums) {
        int majority = 0;
        for (int i = 0, mask = 1; i < 32; i++, mask <<= 1) { // for each bit position, determine if > n/2 elements has '1' instead of '0'
            int countBits = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & mask) == mask) {
                    countBits ++;
                    if (countBits > nums.length / 2) {
                        majority |= mask;
                        break;
                    }
                }
            }// inner-for
        }// outer-for
        return majority;
    }
}
