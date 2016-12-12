// https://leetcode.com/problems/utf-8-validation/
public class QID393 {
    // assume all input numbers <= 255
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }

        // data = {197, 130, 1} -> leads = {2, 1, 0}
        int[] leads = new int[data.length];
        for (int i = 0; i < leads.length; i++) {
            leads[i] = countLeadingSetBits(data[i]);
        }

        int i = 0;
        while (i < leads.length){
            if (leads[i] == 0) {
                i ++;
            } else if (leads[i] == 1) {
                return false;
            } else {
                int follow = leads[i] - 1; // the number of 10xxxxxx following data[i]
                if (i + follow >= leads.length) return false;
                for (int j = 0; j < follow; j ++) {
                    if (leads[i + j + 1] != 1) return false;
                }
                i += (follow + 1);
            }
        }
        return true;
    }

    // return the number of leading set bits of num
    // possible return values are 0, 1, 2, 3, 4
    private int countLeadingSetBits(int num){
        if (num >= 240) return 4;
        if (num >= 224) return 3;
        if (num >= 192) return 2;
        if (num >= 128) return 1;
        return 0;
    }

    public static void main(String[] args) {
        QID393 driver = new QID393();
        int[] data = {240,162,138,147};
        driver.validUtf8(data);
    }
}
