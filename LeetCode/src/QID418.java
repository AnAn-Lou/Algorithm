// https://leetcode.com/problems/sentence-screen-fitting/

public class QID418 {

    public static void main(String[] args) {
        String[] sentence = {"a", "b"};
        int rows = 20000;
        int cols = 20000;
        QID418 driver = new QID418();
        System.out.println(driver.wordsTyping(sentence, rows, cols));
    }

    public int wordsTyping(String[] sentence, int rows, int cols) {

        if (sentence == null || sentence.length == 0 || rows <= 0 || cols <= 0) {
            return 0;
        }

        int counter = 0; // the number of sentences fit onto the screen
        int row = 0; // row < rows
        int col = 0; // col < cols
        int i = 0; // index of next word

        while (true) {

            if (row == rows) {
                break;
            }

            int len = sentence[i].length();
            col = col + len;
            if (col <= cols) {
                i ++;
                if (i == sentence.length) {
                    counter ++;
                    i = 0;
                }

                // Two consecutive words in a line must be separated by a single space.
                if (col == cols || col == cols - 1) {
                    row ++;
                    col = 0;
                } else {
                    col += 1;
                }

            } else {
                row ++;
                col = 0;
            }

        } // while

        return counter;
    }
}
