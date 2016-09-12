import java.util.LinkedList;

// http://www.lintcode.com/en/problem/min-stack/
public class QID12 {
    LinkedList<Integer> stack;
    LinkedList<Integer> min;

    public QID12() {
        stack = new LinkedList<>();
        min = new LinkedList<>();
    }

    public void push(int number) {
        stack.push(number);
        if (min.isEmpty() || min.peek() > number) {
            min.push(number);
        } else {
            min.push(min.peek());
        }
    }

    public int pop() {
        min.pop();
        return stack.pop();
    }

    public int min() {
        return min.peek();
    }
}
