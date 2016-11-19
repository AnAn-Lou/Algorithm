// https://leetcode.com/problems/reverse-bits/
public class QID190 {
    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            sum += ((n & (1 << i)) << (31 - 2 * i));
        }
        for (int i = 16; i < 32; i++) {
            sum += ((n & (1 << i)) >> (2 * i - 31));
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(reverseBits(1));
    }
}
